/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.List;

public class TOCCreatorTest
{
   @Test
   public void testCreateTOC()
   {
      DocumentBuilder db = new DocumentBuilder();

      db.startDocument();

      db.startChapter("c1");

      db.startChapter("c1.1");
      db.endChapter();

      db.startChapter("c1.2");
      db.endChapter();

      db.endChapter();

      db.startChapter("c2");
      db.endChapter();

      Document doc = db.endDocument();

      List toc = TOCCreator.forGitHub().createTOC(doc, -1);
      assertNotNull(toc);

      Document tocDoc = DocOMFactory.eINSTANCE.createDocument();
      tocDoc.getContent().add(toc);

      String actual = new DocOMToMarkdownConverter().toMarkdown(tocDoc, EOL.LF);

      String expected = "*   [c1](#c1)\n    *   [c1.1](#c11)\n    *   [c1.2](#c12)\n*   [c2](#c2)\n\n";

      assertEquals(expected, actual);
   }

   @Test
   public void testMaxDepth()
   {
      DocumentBuilder db = new DocumentBuilder();

      db.startDocument();

      db.startChapter("c1");
      db.startChapter("c1.1");
      db.startChapter("c1.1.1");
      db.startChapter("c1.1.1.1");
      db.endChapter();
      db.endChapter();
      db.endChapter();
      db.endChapter();

      Document doc = db.endDocument();

      List toc = TOCCreator.forGitHub().createTOC(doc, 3);
      assertNotNull(toc);

      Document tocDoc = DocOMFactory.eINSTANCE.createDocument();
      tocDoc.getContent().add(toc);

      String actual = new DocOMToMarkdownConverter().toMarkdown(tocDoc, EOL.LF);

      String expected = "*   [c1](#c1)\n    *   [c1.1](#c11)\n        *   [c1.1.1](#c111)\n\n";

      assertEquals(expected, actual);
   }
}
