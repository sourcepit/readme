/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.Listable;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Text;

public class DocOMToMarkdownConverterTest
{
   private final DocOMFactory factory = DocOMFactory.eINSTANCE;

   private DocOMToMarkdownConverter converter;

   @Before
   public void setUp()
   {
      converter = new DocOMToMarkdownConverter();
   }

   @Test
   public void testParagraphWithText()
   {
      Literal text = text("Hallo Welt!");

      Paragraph paragraph = p(text);

      Document document = doc(paragraph);

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "Hallo Welt!");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testBold()
   {
      Document document = doc(p(bold(text("Hallo Welt!"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "**Hallo Welt!**");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testItalic()
   {
      Document document = doc(p(italic(text("Hallo Welt!"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "*Hallo Welt!*");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testCode()
   {
      Document document = doc(p(code(text("Hallo Welt!"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "`Hallo Welt!`");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testStrikethrough()
   {
      Document document = doc(p(strikethrough(text("Hallo Welt!"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "~~Hallo Welt!~~");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testNestedEmphases()
   {
      Document document = doc(p(bold(italic(text("Hallo Welt!")))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "***Hallo Welt!***");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testOrderedList() throws Exception
   {
      Document document = doc(ol(li(text("Hallo")), li(text("Welt"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "1.   Hallo");
      appendLine(expected, "2.   Welt");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testNestedOrderedList() throws Exception
   {
      Document document = doc(ol(li(text("Hallo"), ol(li("Blub"))), li("Welt")));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "1.   Hallo");
      appendLine(expected, "     1.   Blub");
      appendLine(expected, "2.   Welt");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testUnorderedList() throws Exception
   {
      Document document = doc(ul(li(text("Hallo")), li(text("Welt"))));

      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "*   Hallo");
      appendLine(expected, "*   Welt");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }

   @Test
   public void testChapter() throws Exception
   {
      Chapter chapter = factory.createChapter();
      Header header = factory.createHeader();
      header.getLiterals().add(text("heading"));
      chapter.setHeader(header);
      chapter.getContent().add(p(text("content")));
      
      Document document = doc(chapter);
      
      String markdown = converter.toMarkdown(document);

      StringBuilder expected = new StringBuilder();
      appendLine(expected, "# heading");
      appendLine(expected);
      appendLine(expected, "content");
      appendLine(expected);

      assertEquals(expected.toString(), markdown);
   }
   
   private List ul(ListItem... lis)
   {
      List list = factory.createList();
      list.setType(ListType.UNORDERED);
      Collections.addAll(list.getItems(), lis);
      return list;
   }

   private List ol(ListItem... lis)
   {
      List list = factory.createList();
      list.setType(ListType.ORDERED);
      Collections.addAll(list.getItems(), lis);
      return list;
   }

   private ListItem li(String text)
   {
      return li(text(text));
   }

   private ListItem li(Listable... listable)
   {
      ListItem listItem = factory.createListItem();
      Collections.addAll(listItem.getContent(), listable);
      return listItem;
   }

   private static void appendLine(StringBuilder sb, String str)
   {
      sb.append(str);
      appendLine(sb);
   }

   private static void appendLine(StringBuilder sb)
   {
      sb.append(System.lineSeparator());
   }

   private Document doc(Structurable structurable)
   {
      Document document = factory.createDocument();
      document.getContent().add(structurable);
      return document;
   }

   private Paragraph p(Literal literal)
   {
      Paragraph paragraph = factory.createParagraph();
      paragraph.getLiterals().add(literal);
      return paragraph;
   }

   private Emphasis bold(Literal literal)
   {
      Emphasis em = factory.createEmphasis();
      em.setType(EmphasisType.BOLD);
      em.getLiterals().add(literal);
      return em;
   }

   private Emphasis italic(Literal literal)
   {
      Emphasis em = factory.createEmphasis();
      em.setType(EmphasisType.ITALIC);
      em.getLiterals().add(literal);
      return em;
   }

   private Emphasis code(Literal literal)
   {
      Emphasis em = factory.createEmphasis();
      em.setType(EmphasisType.CODE);
      em.getLiterals().add(literal);
      return em;
   }

   private Emphasis strikethrough(Literal literal)
   {
      Emphasis em = factory.createEmphasis();
      em.setType(EmphasisType.STRIKETHROUGH);
      em.getLiterals().add(literal);
      return em;
   }

   private Text text(String string)
   {
      Text text = factory.createText();
      text.setText(string);
      return text;
   }

}