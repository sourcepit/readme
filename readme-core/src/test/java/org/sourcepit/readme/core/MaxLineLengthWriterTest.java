/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class MaxLineLengthWriterTest
{
   @Test
   public void test() throws Exception
   {
      StringWriter result = new StringWriter();
      Writer writer = new MaxLineLengthWriter(result, 4);
      writer.write("hallo!");
      writer.close();
      assertEquals("hal\nlo!", result.toString());
      
      result = new StringWriter();
      writer = new MaxLineLengthWriter(result, 4);
      writer.write("hal\nlo!");
      writer.close();
      assertEquals("hal\nlo!", result.toString());
      
      result = new StringWriter();
      writer = new MaxLineLengthWriter(result, 4);
      writer.write("ha\nllo!");
      writer.close();
      assertEquals("ha\nllo\n!", result.toString());
      
      result = new StringWriter();
      writer = new MaxLineLengthWriter(result, 4);
      writer.write("\n\n\n\n");
      writer.close();
      assertEquals("\n\n\n\n", result.toString());
      
      result = new StringWriter();
      writer = new MaxLineLengthWriter(result, 4);
      writer.write("\n\n\n\n\n\n\n\n");
      writer.close();
      assertEquals("\n\n\n\n\n\n\n\n", result.toString());
   }

}
