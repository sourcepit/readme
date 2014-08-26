/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class EOLNormalizingWriterTest
{

   @Test
   public void testLF() throws IOException
   {
      EOL eol = EOL.LF;

      StringWriter actual = new StringWriter();

      Writer normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("hal\r\nlo");
      normalizer.close();

      assertEquals("hal\nlo", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\n\r\n\r\n\r\n");
      normalizer.close();
      assertEquals("\n\n\n\n", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\r\r\r");
      normalizer.close();
      assertEquals("\n\n\n\n", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\n\n\n\n");
      normalizer.close();
      assertEquals("\n\n\n\n", actual.toString());
   }

   @Test
   public void testCRLF() throws IOException
   {
      EOL eol = EOL.CRLF;

      StringWriter actual = new StringWriter();

      Writer normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("hal\r\nlo");
      normalizer.close();

      assertEquals("hal\r\nlo", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\n\r\n\r\n\r\n");
      normalizer.close();
      assertEquals("\r\n\r\n\r\n\r\n", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\r\r\r");
      normalizer.close();
      assertEquals("\r\n\r\n\r\n\r\n", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\n\n\n\n");
      normalizer.close();
      assertEquals("\r\n\r\n\r\n\r\n", actual.toString());
   }

   @Test
   public void testCR() throws IOException
   {
      EOL eol = EOL.CR;

      StringWriter actual = new StringWriter();

      Writer normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("hal\r\nlo");
      normalizer.close();

      assertEquals("hal\rlo", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\n\r\n\r\n\r\n");
      normalizer.close();
      assertEquals("\r\r\r\r", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\r\r\r\r");
      normalizer.close();
      assertEquals("\r\r\r\r", actual.toString());

      actual = new StringWriter();
      normalizer = new EOLNormalizingWriter(actual, eol);
      normalizer.write("\n\n\n\n");
      normalizer.close();
      assertEquals("\r\r\r\r", actual.toString());
   }
}
