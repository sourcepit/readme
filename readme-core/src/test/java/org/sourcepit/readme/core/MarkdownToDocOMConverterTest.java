/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Code;
import org.sourcepit.docom.CodeLiteral;
import org.sourcepit.docom.Declaration;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.HorizontalLine;
import org.sourcepit.docom.Link;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Quote;
import org.sourcepit.docom.Reference;
import org.sourcepit.docom.Text;

public class MarkdownToDocOMConverterTest
{
   private MarkdownToDocOMConverter converter;

   @Before
   public void setUp()
   {
      converter = new MarkdownToDocOMConverter();
   }

   @Test
   public void testEmpty()
   {
      Document document = converter.toDocOM("");
      assertNotNull(document);
      assertEquals(0, document.getContent().size());
   }

   @Test
   public void testText()
   {
      Document document = converter.toDocOM("Hello World");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Hello World", text.getText());
   }

   @Test
   public void testTextWithNewLines()
   {
      Document document = converter.toDocOM("Hello\nWorld\n\nWhats going on?\n");
      assertNotNull(document);
      assertEquals(2, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Hello World", text.getText());
      
      paragraph = (Paragraph) document.getContent().get(1);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Whats going on?", text.getText());
   }
   
   @Test
   public void testSpecialText()
   {
      Document document = converter.toDocOM("!");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("!", text.getText());
   }

   @Test
   public void testSpecialTextAndNormalText()
   {
      Document document = converter.toDocOM("Hello World!");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Hello World!", text.getText());
   }

   @Test
   public void testListUnordered() throws Exception
   {
      Document document = converter.toDocOM("* item 1");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(ListType.UNORDERED, list.getType());
      assertEquals(1, list.getItems().size());

      ListItem item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      Text text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());
   }

   @Test
   public void testListOrdered() throws Exception
   {
      Document document = converter.toDocOM("1. item 1");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(ListType.ORDERED, list.getType());
      assertEquals(1, list.getItems().size());

      ListItem item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      Text text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());
   }

   @Test
   public void testNestedList() throws Exception
   {
      Document document = converter.toDocOM("- item 1\n    - item 1.1\n- item 2");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(2, list.getItems().size());

      ListItem item;
      item = list.getItems().get(0);
      assertEquals(2, item.getContent().size());

      Text text;
      text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());

      List nestedList = (List) item.getContent().get(1);
      assertEquals(1, nestedList.getItems().size());

      ListItem nestedItem = nestedList.getItems().get(0);
      assertEquals(1, nestedItem.getContent().size());

      text = (Text) nestedItem.getContent().get(0);
      assertEquals("item 1.1", text.getText());

      item = list.getItems().get(1);
      assertEquals(1, item.getContent().size());

      text = (Text) item.getContent().get(0);
      assertEquals("item 2", text.getText());
   }

   @Test
   public void testListItems() throws Exception
   {
      Document document = converter.toDocOM("* item 1\n* item 2");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(2, list.getItems().size());

      ListItem item;
      item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      Text text;
      text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());

      item = list.getItems().get(1);
      assertEquals(1, item.getContent().size());

      text = (Text) item.getContent().get(0);
      assertEquals("item 2", text.getText());
   }

   @Test
   public void testListParagraphList() throws Exception
   {
      Document document = converter.toDocOM("* item 1\n\nhallo\n\n* item 2");
      assertNotNull(document);
      assertEquals(3, document.getContent().size());

      List list;
      list = (List) document.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem item;
      item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      Text text;
      text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());

      Paragraph paragraph = (Paragraph) document.getContent().get(1);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("hallo", text.getText());

      list = (List) document.getContent().get(2);
      assertEquals(1, list.getItems().size());

      item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      text = (Text) item.getContent().get(0);
      assertEquals("item 2", text.getText());
   }

   @Test
   public void testListItemsWithParagraph() throws Exception
   {
      Document document = converter.toDocOM("* item 1\n\n* item 2");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list;
      list = (List) document.getContent().get(0);
      assertEquals(2, list.getItems().size());

      ListItem item;
      item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      Paragraph paragraph;
      paragraph = (Paragraph) item.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text;
      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("item 1", text.getText());

      item = list.getItems().get(1);
      assertEquals(1, item.getContent().size());

      paragraph = (Paragraph) item.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("item 2", text.getText());

   }

   @Test
   public void testListItemsWithParagraphs() throws Exception
   {
      Document document = converter.toDocOM("*   item 1\n\n    hallo\n\n*   item 2");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list;
      list = (List) document.getContent().get(0);
      assertEquals(2, list.getItems().size());

      ListItem item;
      item = list.getItems().get(0);
      assertEquals(2, item.getContent().size());

      Paragraph paragraph;
      paragraph = (Paragraph) item.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text;
      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("item 1", text.getText());

      paragraph = (Paragraph) item.getContent().get(1);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("hallo", text.getText());

      item = list.getItems().get(1);
      assertEquals(1, item.getContent().size());

      paragraph = (Paragraph) item.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("item 2", text.getText());
   }

   @Test
   public void testChapter() throws Exception
   {
      Document document = converter.toDocOM("# chapter 1");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);

      Header header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 1", text.getText());

      assertEquals(0, chapter.getContent().size());
   }

   @Test
   public void testChapterWithParagraph() throws Exception
   {
      Document document = converter.toDocOM("# chapter 1\n\nHallo");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);

      Header header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text;
      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 1", text.getText());

      assertEquals(1, chapter.getContent().size());

      Paragraph paragraph = (Paragraph) chapter.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Hallo", text.getText());
   }

   @Test
   public void testChapterWithList() throws Exception
   {
      Document document = converter.toDocOM("# chapter 1\n\n* item");
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);

      Header header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text;
      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 1", text.getText());

      assertEquals(1, chapter.getContent().size());

      List list = (List) chapter.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());

      text = (Text) item.getContent().get(0);
      assertEquals("item", text.getText());
   }

   @Test
   public void testChapterNested() throws Exception
   {
      Document document = converter.toDocOM("# chapter 1\n\n##chapter 1.1\n\n# chapter 2");
      assertNotNull(document);
      assertEquals(2, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);
      assertEquals(1, chapter.getContent().size());

      Header header;
      header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text;
      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 1", text.getText());

      assertEquals(1, chapter.getContent().size());

      Chapter nestedChapter = (Chapter) chapter.getContent().get(0);
      assertEquals(0, nestedChapter.getContent().size());

      Header nestedHeader = nestedChapter.getHeader();
      assertEquals(1, nestedHeader.getLiterals().size());

      text = (Text) nestedHeader.getLiterals().get(0);
      assertEquals("chapter 1.1", text.getText());

      chapter = (Chapter) document.getContent().get(1);
      assertEquals(0, chapter.getContent().size());

      header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 2", text.getText());
   }

   @Test
   public void testChapterNestedWithContent() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("# chapter 1\n");
      md.append("\n");
      md.append("text 1\n");
      md.append("\n");
      md.append("## chapter 1.1\n");
      md.append("\n");
      md.append("text 1.1\n");
      md.append("\n");
      md.append("# chapter 2\n");
      md.append("text 2\n");
      md.append("\n");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(2, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);
      assertEquals(2, chapter.getContent().size());

      Header header;
      header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text;
      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 1", text.getText());

      Paragraph paragraph;
      paragraph = (Paragraph) chapter.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("text 1", text.getText());

      Chapter nestedChapter = (Chapter) chapter.getContent().get(1);
      assertEquals(1, nestedChapter.getContent().size());

      Header nestedHeader = nestedChapter.getHeader();
      assertEquals(1, nestedHeader.getLiterals().size());

      text = (Text) nestedHeader.getLiterals().get(0);
      assertEquals("chapter 1.1", text.getText());

      paragraph = (Paragraph) nestedChapter.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("text 1.1", text.getText());

      chapter = (Chapter) document.getContent().get(1);
      assertEquals(1, chapter.getContent().size());

      header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      text = (Text) header.getLiterals().get(0);
      assertEquals("chapter 2", text.getText());

      paragraph = (Paragraph) chapter.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("text 2", text.getText());
   }

   @Test
   public void testQuote() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> test");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(1, quote.getContent().size());

      Paragraph paragraph = (Paragraph) quote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("test", text.getText());
   }

   @Test
   public void testQuoteMultiline() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> test\n");
      md.append("test");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(1, quote.getContent().size());

      Paragraph paragraph = (Paragraph) quote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("test test", text.getText());
   }

   @Test
   public void testQuoteMultiparagraph() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> test\n");
      md.append("> test\n");
      md.append("> \n");
      md.append("> foo\n");
      md.append("> bar");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(2, quote.getContent().size());

      Paragraph paragraph;
      Text text;

      paragraph = (Paragraph) quote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("test test", text.getText());

      paragraph = (Paragraph) quote.getContent().get(1);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("foo bar", text.getText());
   }

   @Test
   public void testQuoteMultiparagraphLazyStyle() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> test\n");
      md.append("test\n");
      md.append("\n");
      md.append("> foo\n");
      md.append("bar");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(2, quote.getContent().size());

      Paragraph paragraph;
      Text text;

      paragraph = (Paragraph) quote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("test test", text.getText());

      paragraph = (Paragraph) quote.getContent().get(1);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("foo bar", text.getText());
   }

   @Test
   public void testQuotedChapter() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> # test");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(1, quote.getContent().size());

      Chapter chapter = (Chapter) quote.getContent().get(0);
      assertEquals(0, chapter.getContent().size());

      Header header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text = (Text) header.getLiterals().get(0);
      assertEquals("test", text.getText());
   }

   @Test
   public void testQuotedQuote() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> foo\n");
      md.append(">\n");
      md.append("> >bar\n");
      md.append(">\n");
      md.append("> acme");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(3, quote.getContent().size());

      Paragraph paragraph;
      Text text;

      paragraph = (Paragraph) quote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("foo", text.getText());

      Quote nestedQuote = (Quote) quote.getContent().get(1);
      assertEquals(1, nestedQuote.getContent().size());

      paragraph = (Paragraph) nestedQuote.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("bar", text.getText());

      paragraph = (Paragraph) quote.getContent().get(2);
      assertEquals(1, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("acme", text.getText());
   }

   @Test
   public void testQuotedList() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("> * item 1\n");
      md.append("> * item 2\n");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Quote quote = (Quote) document.getContent().get(0);
      assertEquals(1, quote.getContent().size());

      ListItem item;
      Text text;

      List list = (List) quote.getContent().get(0);
      assertEquals(2, list.getItems().size());

      item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());
      text = (Text) item.getContent().get(0);
      assertEquals("item 1", text.getText());

      item = list.getItems().get(1);
      assertEquals(1, item.getContent().size());
      text = (Text) item.getContent().get(0);
      assertEquals("item 2", text.getText());
   }

   @Test
   public void testCodeBlock() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("    code");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Code code = (Code) document.getContent().get(0);
      assertEquals("code\n", code.getText());
   }
   
   @Test
   public void testFencedCodeBlockEmpty() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("```\n");
      md.append("```\n");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Code code = (Code) document.getContent().get(0);
      assertNull(code.getLanguage());
      assertEquals("", code.getText());
   }
   
   @Test
   public void testFencedCodeBlock() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("```java\n");
      md.append("code");
      md.append("```\n");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Code code = (Code) document.getContent().get(0);
      assertEquals("java", code.getLanguage());
      assertEquals("code", code.getText());
   }

   @Test
   public void testCodeBlockMultiline() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("    code\n");
      md.append("    {}");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Code code = (Code) document.getContent().get(0);
      assertEquals("code\n{}\n", code.getText());
   }

   @Test
   public void testCodeBlockInList() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("* Snipped below:\n\n");
      md.append("        code\n");
      md.append("        {}");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem listItem = list.getItems().get(0);
      assertEquals(2, listItem.getContent().size());

      Paragraph paragraph = (Paragraph) listItem.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Snipped below:", text.getText());

      Code code = (Code) listItem.getContent().get(1);
      assertEquals("code\n{}\n", code.getText());
   }

   @Test
   public void testHorizontalLine() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("---");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());
      assertTrue(document.getContent().get(0) instanceof HorizontalLine);
   }

   @Test
   public void testHorizontalLineInList() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("* foo:\n\n");
      md.append("    ---");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem listItem = list.getItems().get(0);
      assertEquals(2, listItem.getContent().size());

      Paragraph paragraph = (Paragraph) listItem.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Text text = (Text) paragraph.getLiterals().get(0);
      assertEquals("foo:", text.getText());

      assertTrue(listItem.getContent().get(1) instanceof HorizontalLine);
   }

   @Test
   public void testLink() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("[Über mich](/about/)");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Link link = (Link) paragraph.getLiterals().get(0);
      assertEquals("/about/", link.getUrl());
      assertNull(link.getTitle());
      assertEquals(1, link.getLiterals().size());

      Text text = (Text) link.getLiterals().get(0);
      assertEquals("Über mich", text.getText());
   }

   @Test
   public void testLinkLazyStyle() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("<http://www.sourcepit.org>");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Link link = (Link) paragraph.getLiterals().get(0);
      assertEquals("http://www.sourcepit.org", link.getUrl());
      assertNull(link.getTitle());
      assertEquals(0, link.getLiterals().size());
   }

   @Test
   public void testLinkWithTitle() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("[Über mich](/about/ 'Der Linktitel')");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Link link = (Link) paragraph.getLiterals().get(0);
      assertEquals("/about/", link.getUrl());
      assertEquals("Der Linktitel", link.getTitle());
      assertEquals(1, link.getLiterals().size());

      Text text = (Text) link.getLiterals().get(0);
      assertEquals("Über mich", text.getText());
   }

   @Test
   public void testLinkBetweenText() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("Hier [gehts](/about/) weiter.");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Text text;

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(3, paragraph.getLiterals().size());

      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Hier ", text.getText());

      Link link = (Link) paragraph.getLiterals().get(1);
      assertEquals("/about/", link.getUrl());
      assertNull(link.getTitle());
      assertEquals(1, link.getLiterals().size());

      text = (Text) link.getLiterals().get(0);
      assertEquals("gehts", text.getText());

      text = (Text) paragraph.getLiterals().get(2);
      assertEquals(" weiter.", text.getText());
   }

   @Test
   public void testLinkInList() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("* [foo](/bar/)");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());
      Text text;

      Link link = (Link) item.getContent().get(0);
      assertEquals("/bar/", link.getUrl());
      assertNull(link.getTitle());
      assertEquals(1, link.getLiterals().size());

      text = (Text) link.getLiterals().get(0);
      assertEquals("foo", text.getText());
   }

   @Test
   public void testReference() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("[ein Beispiel][id]");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Reference reference = (Reference) paragraph.getLiterals().get(0);
      assertEquals("id", reference.getId());
      assertEquals(1, reference.getLiterals().size());

      Text text = (Text) reference.getLiterals().get(0);
      assertEquals("ein Beispiel", text.getText());
   }

   @Test
   public void testReferenceInList() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("* [foo][bar]");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      List list = (List) document.getContent().get(0);
      assertEquals(1, list.getItems().size());

      ListItem item = list.getItems().get(0);
      assertEquals(1, item.getContent().size());
      Text text;

      Reference reference = (Reference) item.getContent().get(0);
      assertEquals("bar", reference.getId());
      assertEquals(1, reference.getLiterals().size());

      text = (Text) reference.getLiterals().get(0);
      assertEquals("foo", text.getText());
   }

   @Test
   public void testReferenceInHeader() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("# [foo][bar]");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Chapter chapter = (Chapter) document.getContent().get(0);
      assertEquals(0, chapter.getContent().size());

      Header header = chapter.getHeader();
      assertEquals(1, header.getLiterals().size());

      Text text;

      Reference reference = (Reference) header.getLiterals().get(0);
      assertEquals("bar", reference.getId());
      assertEquals(1, reference.getLiterals().size());

      text = (Text) reference.getLiterals().get(0);
      assertEquals("foo", text.getText());
   }

   @Test
   public void testDeclaration() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("[foo]: http://example.com/  (Optionaler Titel)");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Declaration declaration = (Declaration) document.getContent().get(0);
      assertEquals("foo", declaration.getId());
      assertEquals("http://example.com/", declaration.getUrl());
      assertEquals("Optionaler Titel", declaration.getTitle());
   }

   @Test
   public void testMail() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("<bernd.vogt@sourcepit.org>");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Link link = (Link) paragraph.getLiterals().get(0);
      assertEquals("mailto:bernd.vogt@sourcepit.org", link.getUrl());
      assertNull(link.getTitle());
      assertEquals(0, link.getLiterals().size());
   }

   @Test
   public void testItalic() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("*italic*");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Emphasis emphasis = (Emphasis) paragraph.getLiterals().get(0);
      assertEquals(EmphasisType.ITALIC, emphasis.getType());
      assertEquals(1, emphasis.getLiterals().size());

      Text text = (Text) emphasis.getLiterals().get(0);
      assertEquals("italic", text.getText());
   }

   @Test
   public void testBold() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("**bold**");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Emphasis emphasis = (Emphasis) paragraph.getLiterals().get(0);
      assertEquals(EmphasisType.BOLD, emphasis.getType());
      assertEquals(1, emphasis.getLiterals().size());

      Text text = (Text) emphasis.getLiterals().get(0);
      assertEquals("bold", text.getText());
   }

   @Test
   public void testBoldAndItalic() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("***bold and italic***");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Emphasis emphasis = (Emphasis) paragraph.getLiterals().get(0);
      assertEquals(EmphasisType.BOLD, emphasis.getType());
      assertEquals(1, emphasis.getLiterals().size());

      emphasis = (Emphasis) emphasis.getLiterals().get(0);
      assertEquals(EmphasisType.ITALIC, emphasis.getType());
      assertEquals(1, emphasis.getLiterals().size());

      Text text = (Text) emphasis.getLiterals().get(0);
      assertEquals("bold and italic", text.getText());
   }
   
   @Test
   public void testStrikeThrough() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("~~Mistaken text.~~");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      Emphasis emphasis = (Emphasis) paragraph.getLiterals().get(0);
      assertEquals(EmphasisType.STRIKETHROUGH, emphasis.getType());
      assertEquals(1, emphasis.getLiterals().size());

      Text text = (Text) emphasis.getLiterals().get(0);
      assertEquals("Mistaken text.", text.getText());
   }

   @Test
   public void testCode() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("`code`");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(1, paragraph.getLiterals().size());

      CodeLiteral code = (CodeLiteral) paragraph.getLiterals().get(0);
      assertEquals("code", code.getText());
   }

   @Test
   public void testNewLine() throws Exception
   {
      StringBuilder md = new StringBuilder();
      md.append("Take a  \n");
      md.append("break.");

      Document document = converter.toDocOM(md.toString());
      assertNotNull(document);
      assertEquals(1, document.getContent().size());

      Paragraph paragraph = (Paragraph) document.getContent().get(0);
      assertEquals(3, paragraph.getLiterals().size());

      Text text;
      text = (Text) paragraph.getLiterals().get(0);
      assertEquals("Take a", text.getText());

      assertTrue(paragraph.getLiterals().get(1) instanceof NewLine);

      text = (Text) paragraph.getLiterals().get(2);
      assertEquals("break.", text.getText());
   }
}
