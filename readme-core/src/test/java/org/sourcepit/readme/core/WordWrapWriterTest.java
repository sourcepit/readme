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

public class WordWrapWriterTest
{

   @Test
   public void test() throws IOException
   {
      StringWriter out = new StringWriter();
      
      Writer w = new WordWrapWriter(out, 4);
      
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
      
      w.flush();
      
      assertEquals("hal\nlo\ndu\neie\ni\nei", out.toString());
   }
   
   @Test
   public void test2() throws IOException
   {
      StringWriter out = new StringWriter();
      
      Writer w = new WordWrapWriter(out, 8);
      
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
      
      w.flush();
      
      assertEquals("hallo\ndu eiei\nei", out.toString());
   }

}
