/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;
import org.sourcepit.docom.util.DocOMSwitch;

public class DocOMToMarkdownConverter
{

   private final static class NewLineRenderer<T extends EObject> implements Renderer<T>
   {
      private final Renderer<T> renderer;

      private NewLineRenderer(Renderer<T> renderer)
      {
         this.renderer = renderer;
      }

      @Override
      public void render(T obj, Writer w) throws IOException
      {
         renderer.render(obj, w);
      }

      @Override
      public void finalize(T obj, Writer w) throws IOException
      {
         renderer.finalize(obj, w);
      }

      @Override
      public void preNewLine(T obj, Writer w) throws IOException
      {
         renderer.preNewLine(obj, w);
      }

      @Override
      public boolean isAllowLinesBreaks()
      {
         return renderer.isAllowLinesBreaks();
      }
   }

   public static interface Renderer<T>
   {
      Renderer<Object> NOOP = new Renderer<Object>()
      {
         @Override
         public void render(Object obj, Writer w) throws IOException
         {
         }

         @Override
         public void finalize(Object obj, Writer w) throws IOException
         {
         }

         @Override
         public void preNewLine(Object obj, Writer w) throws IOException
         {
         }

         @Override
         public boolean isAllowLinesBreaks()
         {
            return true;
         }
      };

      void render(T obj, Writer w) throws IOException;

      void preNewLine(T obj, Writer w) throws IOException;

      void finalize(T obj, Writer w) throws IOException;

      boolean isAllowLinesBreaks();
   }


   public static class ListItemRenderer implements Renderer<ListItem>
   {
      @Override
      public void render(ListItem obj, Writer w) throws IOException
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
      public void finalize(ListItem obj, Writer w) throws IOException
      {
      }

      @Override
      public void preNewLine(ListItem obj, Writer w) throws IOException
      {
         final String prefix = getPrefix(obj, (List) obj.eContainer());
         for (int i = 0; i < prefix.length(); i++)
         {
            w.append(' ');
         }
      }

      @Override
      public boolean isAllowLinesBreaks()
      {
         return true;
      }
   }

   public static class TextRenderer implements Renderer<Text>
   {
      @Override
      public void render(Text obj, Writer w) throws IOException
      {
         w.write(obj.getText());
      }

      @Override
      public void finalize(Text obj, Writer w) throws IOException
      {
      }

      @Override
      public void preNewLine(Text obj, Writer w) throws IOException
      {
      }

      @Override
      public boolean isAllowLinesBreaks()
      {
         return true;
      }
   }

   public static class EmphasisRenderer implements Renderer<Emphasis>
   {
      @Override
      public void render(Emphasis obj, Writer w) throws IOException
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
      public void finalize(Emphasis obj, Writer w) throws IOException
      {
         render(obj, w);
      }

      @Override
      public void preNewLine(Emphasis obj, Writer w) throws IOException
      {
      }

      @Override
      public boolean isAllowLinesBreaks()
      {
         return true;
      }
   }

   public static class HeaderRenderer implements Renderer<Header>
   {
      @Override
      public void render(Header obj, Writer w) throws IOException
      {
         for (int i = 0; i < getDepth(obj); i++)
         {
            w.append('#');
         }
         w.append(' ');
      }

      private int getDepth(Header obj)
      {
         int depth = 0;

         EObject eContainer = obj.eContainer();
         while (eContainer != null)
         {
            if (eContainer instanceof Chapter)
            {
               depth++;
            }
            eContainer = eContainer.eContainer();
         }

         return depth;
      }

      @Override
      public void finalize(Header obj, Writer w) throws IOException
      {
      }

      @Override
      public void preNewLine(Header obj, Writer w) throws IOException
      {
      }

      @Override
      public boolean isAllowLinesBreaks()
      {
         return false;
      }

   }

   public String toMarkdown(Document document)
   {
      return toMarkdown(document, 80, EOL.system());
   }

   public String toMarkdown(Document document, EOL eol)
   {
      return toMarkdown(document, 80, eol);
   }

   public String toMarkdown(Document document, int lineLength, EOL eol)
   {
      final StringWriter str = new StringWriter();

      final Stack<Renderer<EObject>> renderers = new Stack<Renderer<EObject>>();
      final Stack<EObject> objs = new Stack<EObject>();

      final Writer w = new WordWrapppingWriter(str, lineLength, eol)
      {
         @Override
         protected String indent(boolean forced) throws IOException
         {
            final StringWriter nlPrefix = new StringWriter();
            for (int i = 0; i < renderers.size(); i++)
            {
               renderers.get(i).preNewLine(objs.get(i), nlPrefix);
            }
            nlPrefix.close();
            return nlPrefix.toString();
         }

         @Override
         protected boolean writeEOL(boolean forced) throws IOException
         {
            for (Renderer<?> renderer : renderers)
            {
               if (!renderer.isAllowLinesBreaks())
               {
                  return false;
               }
            }
            return super.writeEOL(forced);
         }
      };

      try
      {
         java.util.List<? extends EObject> children = getChildren(document);
         for (EObject eObject : children)
         {
            preChild(document, eObject, w);
            w.flush();
            render(renderers, objs, eObject, w);
         }

         if (!children.isEmpty())
         {
            w.write(eol.asChars());
            w.write(eol.asChars());
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

   private void render(Stack<Renderer<EObject>> renderers, Stack<EObject> objs, EObject obj, Writer w)
      throws IOException
   {
      final Renderer<EObject> renderer = getRenderer(obj);

      renderers.push(renderer);
      objs.push(obj);

      renderer.render(obj, w);
      w.flush();
      for (EObject child : getChildren(obj))
      {
         preChild(obj, child, w);
         w.flush();
         render(renderers, objs, child, w);
         w.flush();
      }
      renderer.finalize(obj, w);
      w.flush();

      objs.pop();
      renderers.pop();
   }

   private void preChild(EObject obj, EObject child, Writer w) throws IOException
   {
      EObject eContainer = obj;

      if (eContainer instanceof Structured || eContainer instanceof ListItem || eContainer instanceof List)
      {
         java.util.List<? extends EObject> children = getChildren(eContainer);
         int idx = children.indexOf(child);
         if (idx > 0)
         {
            w.append('\n');

            EObject previous = children.get(idx - 1);

            if (previous instanceof Paragraph || previous instanceof Header || previous instanceof NewLine)
            {
               w.append('\n');
               return;
            }

            java.util.List<? extends EObject> c = getChildren(previous);
            while (c != null && !c.isEmpty())
            {
               previous = c.get(c.size() - 1);
               if (previous instanceof Paragraph || previous instanceof Header || previous instanceof NewLine)
               {
                  w.append('\n');
                  return;
               }
               c = getChildren(previous);
            }
         }
      }
   }

   private static java.util.List<? extends EObject> getChildren(EObject obj)
   {
      return new DocOMSwitch<java.util.List<? extends EObject>>()
      {
         public java.util.List<? extends EObject> caseChapter(Chapter object)
         {
            java.util.List<EObject> children = new ArrayList<EObject>();
            children.add(object.getHeader());
            children.addAll(object.getContent());
            return children;
         }

         public java.util.List<? extends EObject> caseStructured(Structured object)
         {
            return object.getContent();
         }

         public java.util.List<? extends EObject> caseLiteralGroup(LiteralGroup object)
         {
            return object.getLiterals();
         }

         public java.util.List<? extends EObject> caseList(List object)
         {
            return object.getItems();
         }

         public java.util.List<? extends EObject> caseListItem(ListItem listItem)
         {
            return listItem.getContent();
         }

         public java.util.List<? extends EObject> defaultCase(EObject object)
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

         public Renderer<T> caseChapter(Chapter object)
         {
            return (Renderer<T>) Renderer.NOOP;
         };

         public Renderer<T> caseHeader(Header object)
         {
            return (Renderer<T>) new HeaderRenderer();
         }

      }.doSwitch(obj);

      return new NewLineRenderer<T>(renderer);
   }
}
