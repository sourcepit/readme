/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;
import java.io.Writer;

public class MaxLineLengthWriter extends Writer
{
   protected final Writer target;

   private final int maxLineLegth;

   private int curLen = 0;

   public MaxLineLengthWriter(Writer target, int maxLineLegth)
   {
      this.target = target;
      this.maxLineLegth = maxLineLegth;
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      for (int i = off; i < len; i++)
      {
         char c = cbuf[i];

         write(c);
      }
   }

   @Override
   public void write(int c) throws IOException
   {
      curLen++;

      if ('\n' == c || curLen >= maxLineLegth)
      {
         curLen = 0;
         nl();
         if ('\n' != c)
         {
            target.write(c);
            curLen++;
         }
      }
      else
      {
         target.write(c);
      }
   }

   protected void nl() throws IOException
   {
      target.write('\n');
   }

   @Override
   public void flush() throws IOException
   {
      target.flush();
   }

   @Override
   public void close() throws IOException
   {
      target.close();
   }
}
