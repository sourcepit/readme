/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.Listable;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;
import org.sourcepit.docom.util.DocOMSwitch;
import org.sourcepit.readme.core.DocOMToMarkdownConverter.Renderer;

public class DocOMToMarkdownConverter
{
   private final static class NewLineRenderer<T> implements Renderer<T>
   {
      private final Renderer<T> renderer;

      private NewLineRenderer(Renderer<T> renderer)
      {
         this.renderer = renderer;
      }

      @Override
      public void render(T obj, BufferedWriter w) throws IOException
      {
         renderer.render(obj, w);
      }

      @Override
      public void ident(T obj, Object child, BufferedWriter w) throws IOException
      {
         renderer.ident(obj, child, w);
      }

      @Override
      public void finalize(T obj, BufferedWriter w) throws IOException
      {
         renderer.finalize(obj, w);
         w.newLine();
         if (obj instanceof Paragraph)
         {
            w.newLine();
         }
      }
   }

   public static interface Renderer<T>
   {
      Renderer<? super Object> NOOP = new Renderer<Object>()
      {
         @Override
         public void render(Object obj, BufferedWriter w) throws IOException
         {
         }

         @Override
         public void ident(Object obj, Object child, BufferedWriter w) throws IOException
         {
         }

         @Override
         public void finalize(Object obj, BufferedWriter w) throws IOException
         {
         }
      };

      void render(T obj, BufferedWriter w) throws IOException;

      void ident(T obj, Object child, BufferedWriter w) throws IOException;

      void finalize(T obj, BufferedWriter w) throws IOException;
   }

   public static class ListItemRenderer implements Renderer<ListItem>
   {
      @Override
      public void render(ListItem obj, BufferedWriter w) throws IOException
      {
         final String prefix = getPrefix(obj, (List) obj.eContainer());
         w.append(prefix);
      }

      private String getPrefix(ListItem obj, final List list)
      {
         final String prefix;
         switch (list.getType())
         {
            case ORDERED :
               prefix = list.getItems().indexOf(obj) + 1 + ".   ";
               break;
            case UNORDERED :
               prefix = "*   ";
               break;
            default :
               throw new IllegalStateException();
         }
         return prefix;
      }

      @Override
      public void ident(ListItem obj, Object child, BufferedWriter w) throws IOException
      {
         if (obj.getContent().indexOf(child) > 0)
         {
            final String prefix = getPrefix(obj, (List) obj.eContainer());
            for (int i = 0; i < prefix.length(); i++)
            {
               w.append(' ');
            }
         }
      }

      @Override
      public void finalize(ListItem obj, BufferedWriter w) throws IOException
      {
      }
   }

   public static class TextRenderer implements Renderer<Text>
   {
      @Override
      public void render(Text obj, BufferedWriter w) throws IOException
      {
         w.write(obj.getText());
      }

      @Override
      public void finalize(Text obj, BufferedWriter w) throws IOException
      {
      }

      @Override
      public void ident(Text obj, Object child, BufferedWriter w)
      {
      }
   }

   public static class EmphasisRenderer implements Renderer<Emphasis>
   {
      @Override
      public void render(Emphasis obj, BufferedWriter w) throws IOException
      {
         switch (obj.getType())
         {
            case BOLD :
               w.append("**");
               break;
            case ITALIC :
               w.append("*");
               break;
            case CODE :
               w.append("`");
               break;
            case STRIKETHROUGH :
               w.append("~~");
               break;
            default :
               throw new IllegalStateException();
         }
      }

      @Override
      public void finalize(Emphasis obj, BufferedWriter w) throws IOException
      {
         render(obj, w);
      }

      @Override
      public void ident(Emphasis obj, Object child, BufferedWriter w)
      {
      }
   }

   public String toMarkdown(Document document)
   {
      StringWriter str = new StringWriter();

      BufferedWriter w = new BufferedWriter(str);


      try
      {
         for (EObject eObject : getChildren(document))
         {
            render(eObject, w);
         }
         w.flush();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return str.toString();
   }

   private void render(EObject obj, BufferedWriter w) throws IOException
   {
      final Renderer<EObject> renderer = getRenderer(obj);
      renderer.render(obj, w);
      for (EObject child : getChildren(obj))
      {
         renderer.ident(obj, child, w);
         render(child, w);
      }
      renderer.finalize(obj, w);
   }

   private Collection<? extends EObject> getChildren(EObject obj)
   {
      return new DocOMSwitch<Collection<? extends EObject>>()
      {
         public Collection<? extends EObject> caseStructured(Structured object)
         {
            return object.getContent();
         }

         public Collection<? extends EObject> caseLiteralGroup(LiteralGroup object)
         {
            return object.getLiterals();
         }

         public Collection<? extends EObject> caseList(List object)
         {
            return object.getItems();
         }

         public Collection<? extends EObject> caseListItem(ListItem listItem)
         {
            return listItem.getContent();
         }

         public Collection<? extends EObject> defaultCase(EObject object)
         {
            return Collections.emptyList();
         }
      }.doSwitch(obj);
   }

   @SuppressWarnings("unchecked")
   private <T extends EObject> Renderer<T> getRenderer(T obj)
   {
      final Renderer<T> renderer = new DocOMSwitch<Renderer<T>>()
      {
         public Renderer<T> caseParagraph(Paragraph paragraph)
         {
            return (Renderer<T>) Renderer.NOOP;
         }

         public DocOMToMarkdownConverter.Renderer<T> caseList(List object)
         {
            return (Renderer<T>) Renderer.NOOP;
         }

         public DocOMToMarkdownConverter.Renderer<T> caseListItem(ListItem object)
         {
            return (Renderer<T>) new ListItemRenderer();
         }

         public Renderer<T> caseText(Text text)
         {
            return (Renderer<T>) new TextRenderer();
         }

         public Renderer<T> caseEmphasis(Emphasis emphasis)
         {
            return (Renderer<T>) new EmphasisRenderer();
         }

      }.doSwitch(obj);

      final EObject eContainer = obj.eContainer();
      
      if (obj instanceof Structurable && eContainer instanceof Structured || !(obj instanceof List)
         && obj instanceof Listable && eContainer instanceof ListItem)
      {
         return new NewLineRenderer<T>(renderer);
      }

      return renderer;
   }
}
