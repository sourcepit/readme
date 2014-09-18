/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import java.util.ArrayList;
import java.util.List;

import org.sourcepit.docom.Document;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;
import org.sourcepit.readme.core.TOCCreator;

public final class TOCUtils
{
   private TOCUtils()
   {
      super();
   }

   public static void injectTOCs(Document doc, int defaultDepth)
   {
      final List<TOC> tocs = new ArrayList<TOC>();
      gatherTOCs(tocs, doc, defaultDepth);

      final TOCCreator tocCreator = TOCCreator.forGitHub();
      for (TOC toc : tocs)
      {
         final Structured parent = toc.parent;
         final Paragraph placeholder = toc.placeholder;
         final int depth = toc.depth;
         
         final int idx = parent.getContent().indexOf(placeholder);
         parent.getContent().set(idx, tocCreator.createTOC(parent, depth));
      }
   }

   private static void gatherTOCs(List<TOC> tocs, Structured chapter, int defaultDepth)
   {
      for (Structurable chapterContent : chapter.getContent())
      {
         if (chapterContent instanceof Paragraph)
         {
            final Paragraph p = (Paragraph) chapterContent;
            if (p.getLiterals().size() == 1 && p.getLiterals().get(0) instanceof Text)
            {
               final String toc = ((Text) p.getLiterals().get(0)).getText();

               if ("[TOC]".equals(toc))
               {
                  tocs.add(new TOC(chapter, p, defaultDepth));
               }
               else if (toc.startsWith("[TOC,") && toc.endsWith("]"))
               {
                  final int depth = Integer.valueOf(toc.substring(5, toc.length() - 1)).intValue();
                  tocs.add(new TOC(chapter, p, depth));
               }
            }
         }
         
         if (chapterContent instanceof Structured)
         {
            gatherTOCs(tocs, (Structured) chapterContent, defaultDepth);
         }
      }
   }

   private static class TOC
   {
      final Structured parent;

      final Paragraph placeholder;

      final int depth;

      public TOC(Structured parent, Paragraph placeholder, int depth)
      {
         this.parent = parent;
         this.placeholder = placeholder;
         this.depth = depth;
      }
   }

}
