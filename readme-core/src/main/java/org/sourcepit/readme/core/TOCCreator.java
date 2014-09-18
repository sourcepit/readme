/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.CodeLiteral;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.Link;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;

public class TOCCreator
{
   private final DocOMFactory eFactory = DocOMFactory.eINSTANCE;

   private final AnchorNameGenerator anchorNameGenerator;
   
   public static TOCCreator forGitHub()
   {
      return new TOCCreator(new GitHubAnchorNameGenerator());
   }

   public TOCCreator(AnchorNameGenerator anchorName)
   {
      this.anchorNameGenerator = anchorName;
   }

   public List createTOC(Structured chapter, int maxDepth)
   {
      return createTOC(chapter.eContents(), 1, maxDepth);
   }
   
   private List createTOC(EList<EObject> contents, int depth, int maxDepth)
   {
      final List toc = eFactory.createList();
      for (EObject eObject : contents)
      {
         if (eObject instanceof Chapter)
         {
            final Chapter chapter = (Chapter) eObject;

            final Link link = createLink(chapter.getHeader());

            final ListItem li = eFactory.createListItem();
            li.getContent().add(link);

            if (maxDepth == -1 || depth < maxDepth)
            {
               final List subChapters = createTOC(chapter.eContents(), depth + 1, maxDepth);
               if (subChapters != null)
               {
                  li.getContent().add(subChapters);
               }
            }

            toc.getItems().add(li);
         }
      }
      return toc.getItems().isEmpty() ? null : toc;
   }

   private Link createLink(final Header header)
   {
      final Link link = eFactory.createLink();
      link.setUrl('#' + anchorNameGenerator.anchorNameFor(header));

      for (Literal literal : header.getLiterals())
      {
         link.getLiterals().add(EcoreUtil.copy(literal));
      }

      return link;
   }

   private static void gatherRawText(StringBuilder res, LiteralGroup group)
   {
      for (Literal lit : group.getLiterals())
      {
         if (lit instanceof LiteralGroup)
         {
            gatherRawText(res, (LiteralGroup) lit);
         }
         else
         {
            if (lit instanceof Text)
            {
               res.append(((Text) lit).getText());
            }
            else if (lit instanceof CodeLiteral)
            {
               res.append(((CodeLiteral) lit).getText());
            }
            else if (lit instanceof NewLine)
            {
               // do nothing
            }
            else
            {
               throw new UnsupportedOperationException("Unknown literal type: " + lit.getClass().getSimpleName());
            }
         }
      }
   }

   public interface AnchorNameGenerator
   {
      String anchorNameFor(Header header);
   }

   public static class GitHubAnchorNameGenerator implements AnchorNameGenerator
   {
      @Override
      public String anchorNameFor(Header header)
      {
         final StringBuilder sb = new StringBuilder();
         gatherRawText(sb, header);
         final String str = sb.toString().toLowerCase();
         char[] reserved = new char[] { ';', '/', '?', ':', '@', '&', '=', '+', '$', ',', '.' };
         char[] chars = str.toCharArray();
         final StringBuilder res = new StringBuilder();
         for (int i = 0; i < chars.length; i++)
         {
            char c = chars[i];
            if (!contains(reserved, c))
            {
               if (c == ' ')
               {
                  c = '-';
               }
               res.append(c);
            }
         }
         return res.toString();
      }

      private boolean contains(char[] chars, char c)
      {
         for (char ch : chars)
         {
            if (ch == c)
            {
               return true;
            }
         }
         return false;
      }
   }

}
