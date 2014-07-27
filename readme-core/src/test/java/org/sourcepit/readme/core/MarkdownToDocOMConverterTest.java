/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Test;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structurable;
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
}
