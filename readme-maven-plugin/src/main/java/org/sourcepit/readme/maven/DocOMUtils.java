/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;

public final class DocOMUtils
{
   private DocOMUtils()
   {
      super();
   }
   
   public static Document createDocument()
   {
      return getDocOMFactory().createDocument();
   }

   public static Chapter addChapter(Structured structured, String header)
   {
      final DocOMFactory factory = getDocOMFactory();

      final Header h = factory.createHeader();
      addText(h, header);

      final Chapter chapter = factory.createChapter();
      chapter.setHeader(h);

      structured.getContent().add(chapter);

      return chapter;
   }

   public static Paragraph addParagraph(Structured structured)
   {
      final Paragraph p = getDocOMFactory().createParagraph();
      structured.getContent().add(p);
      return p;
   }
   
   public static Paragraph addParagraph(ListItem listItem)
   {
      final Paragraph p = getDocOMFactory().createParagraph();
      listItem.getContent().add(p);
      return p;
   }

   public static List addList(Structured structured)
   {
      final List l = getDocOMFactory().createList();
      structured.getContent().add(l);
      return l;
   }

   public static ListItem addListItem(List list)
   {
      final ListItem i = getDocOMFactory().createListItem();
      list.getItems().add(i);
      return i;
   }
   
   public static void addText(ListItem listItem, String text)
   {
      listItem.getContent().add(createText(text));
   }

   public static void addText(LiteralGroup group, String text)
   {
      group.getLiterals().add(createText(text));
   }

   private static Text createText(String header)
   {
      Text text = getDocOMFactory().createText();
      text.setText(header);
      return text;
   }

   public static DocOMFactory getDocOMFactory()
   {
      return DocOMFactory.eINSTANCE;
   }

}
