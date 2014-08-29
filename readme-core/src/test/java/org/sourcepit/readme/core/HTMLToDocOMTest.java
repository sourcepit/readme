/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Text;

public class HTMLToDocOMTest
{
   private HTMLToDocOM converter = new HTMLToDocOM();

   @Test
   public void testJavaDoc()
   {
      String html = "Display help information on\r\ntest-mojo-1.<br/>Call <code>mvn test-mojo-1:help -Ddetail=true -Dgoal=&lt;goal-name&gt;</code> to display parameter details.";

      Document doc = converter.convert(html);

      String markdown = new DocOMToMarkdownConverter().toMarkdown(doc, 80, EOL.LF);

      assertEquals(
         "Display help information on test-mojo-1.  \nCall `mvn test-mojo-1:help -Ddetail=true -Dgoal=<goal-name>` to display\nparameter details.\n\n",
         markdown);
   }

   @Test
   public void testText() throws Exception
   {
      assertEquals("hallo", converter.convert("hallo", Text.class).getText());

      @SuppressWarnings("unchecked")
      Text text = (Text) converter.convert("hallo", Text.class, LiteralGroup.class);
      assertEquals("hallo", text.getText());
   }

   @Test
   public void testLiteralGroup() throws Exception
   {
      Emphasis em = (Emphasis) converter.convert("<b>foo</b>", LiteralGroup.class);
      assertEquals(EmphasisType.BOLD, em.getType());
      assertEquals(1, em.getLiterals().size());

      Text text = (Text) em.getLiterals().get(0);
      assertEquals("foo", text.getText());
   }

}
