/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;
import static org.sourcepit.readme.core.EOL.CR;
import static org.sourcepit.readme.core.EOL.CRLF;
import static org.sourcepit.readme.core.EOL.LF;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class WordWrapppingWriterTest
{

   @Test
   public void test() throws IOException
   {
      StringWriter out = new StringWriter();

      Writer w = new WordWrapppingWriter(out, 4, LF);

      w.write("hal");

      w.flush();

      w.write("l");

      w.flush();
      w.flush();
      w.flush();

      w.write("o");

      w.flush();

      w.write(" du");
      w.write(" ei");
      w.write("ei ei");

      w.close();

      assertEquals("hallo\ndu\neiei\nei", out.toString());
   }

   @Test
   public void test2() throws IOException
   {
      StringWriter out = new StringWriter();

      Writer w = new WordWrapppingWriter(out, 8, LF);

      w.write("hal");

      w.flush();

      w.write("l");

      w.flush();
      w.flush();
      w.flush();

      w.write("o");

      w.flush();

      w.write(" du");
      w.write(" ei");
      w.write("ei ei");

      w.close();

      assertEquals("hallo\ndu eiei\nei", out.toString());
   }
   
   @Test
   public void test3() throws IOException
   {
      StringWriter sw = new StringWriter();

      WordWrapppingWriter lb = new WordWrapppingWriter(sw, 3, EOL.LF);

      for (char c : "ha\nll\no".toCharArray())
      {
         lb.append(c);
      }

      lb.close();

      assertEquals("ha\nll\no", sw.toString());
   }

   @Test
   public void test4() throws IOException
   {
      StringWriter sw = new StringWriter();
      WordWrapppingWriter lb = new WordWrapppingWriter(sw, 3, EOL.LF);
      for (char c : "hallo".toCharArray())
      {
         lb.append(c);
      }
      lb.close();
      assertEquals("hallo", sw.toString());
   }

   @Test
   public void testBufferSize() throws IOException
   {
      StringWriter sw = new StringWriter();
      WordWrapppingWriter lb = new WordWrapppingWriter(sw, 512, 6, EOL.LF);
      for (char c : "hallo du ei".toCharArray())
      {
         lb.append(c);
      }
      lb.close();
      assertEquals("hallo\ndu ei", sw.toString());

      sw = new StringWriter();
      lb = new WordWrapppingWriter(sw, 2, 6, EOL.LF);
      for (char c : "hallo du ei".toCharArray())
      {
         lb.append(c);
      }
      lb.close();
      assertEquals("hallo\ndu ei", sw.toString());
   }

   @Test
   public void testLF() throws IOException
   {
      StringWriter out = new StringWriter();

      Writer w = new WordWrapppingWriter(out, 4, LF);
      w.write("ha\nllo");
      w.close();
      assertEquals("ha\nllo", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, LF);
      w.write("h\na\nl\nl\no");
      w.close();
      assertEquals("h\na\nl\nl\no", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, LF);
      w.write("h\r\na\r\nl\r\nl\r\no");
      w.close();
      assertEquals("h\na\nl\nl\no", out.toString());
   }
   
   @Test
   public void testCRLF() throws IOException
   {
      StringWriter out = new StringWriter();

      Writer w = new WordWrapppingWriter(out, 5, CRLF);
      w.write("ha\nllo");
      w.close();
      assertEquals("ha\r\nllo", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CRLF);
      w.write("h\na\nl\nl\no");
      w.close();
      assertEquals("h\r\na\r\nl\r\nl\r\no", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CRLF);
      w.write("h\r\na\r\nl\r\nl\r\no");
      w.close();
      assertEquals("h\r\na\r\nl\r\nl\r\no", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CRLF);
      w.write("h\r\na\r\nl\r\nl\r\no");
      w.close();
      assertEquals("h\r\na\r\nl\r\nl\r\no", out.toString());
   }
   
   @Test
   public void testCR() throws IOException
   {
      StringWriter out = new StringWriter();

      Writer w = new WordWrapppingWriter(out, 4, CR);
      w.write("ha\nllo");
      w.close();
      assertEquals("ha\rllo", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CR);
      w.write("h\na\nl\nl\no");
      w.close();
      assertEquals("h\ra\rl\rl\ro", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CR);
      w.write("h\r\na\r\nl\r\nl\r\no");
      w.close();
      assertEquals("h\ra\rl\rl\ro", out.toString());

      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CR);
      w.write("h\r\na\r\nl\r\nl\r\no");
      w.close();
      assertEquals("h\ra\rl\rl\ro", out.toString());
      
      out = new StringWriter();
      w = new WordWrapppingWriter(out, 4, CR);
      w.write("h\ra\rl\rl\ro");
      w.close();
      assertEquals("h\ra\rl\rl\ro", out.toString());
   }
   
}
