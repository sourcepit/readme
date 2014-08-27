/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;
import java.io.Writer;

public class EOLNormalizingWriter extends Writer
{
   private final Writer out;

   private final EOLNormalizer normalizer;

   public EOLNormalizingWriter(Writer out, EOL eol)
   {
      super(out);
      this.out = out;
      normalizer = new EOLNormalizer(out, eol);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      for (int i = off; i < len; i++)
      {
         write(cbuf[i]);
      }
   }

   boolean skipNextLF = false;

   @Override
   public void write(int c) throws IOException
   {
      normalizer.append((char) c);
   }

   @Override
   public void flush() throws IOException
   {
      out.flush();
   }

   @Override
   public void close() throws IOException
   {
      out.close();
   }
}
