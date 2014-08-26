/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;
import java.io.Writer;

public class WordWrapWriter extends Writer
{
   private final Writer out;

   private final char[] lineBuffer;

   private int nextIdx = 0;

   private final char[] nl = new char[] { '\n' };

   protected WordWrapWriter(Writer out, int lineLength)
   {
      super(out);

      this.out = out;

      lineBuffer = new char[lineLength - nl.length];
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      for (int i = off; i < len; i++)
      {
         write(cbuf[i]);
      }
   }

   @Override
   public void write(int c) throws IOException
   {
      if (nextIdx == lineBuffer.length) // buffer is full
      {
         flushLineBuffer(c);
      }
      lineBuffer[nextIdx++] = (char) c;
   }

   private int off = 0;

   private static int lastWhitespace(char[] chars, int off, int len)
   {
      for (int i = len - 1; i >= off; i--)
      {
         char c = chars[i];
         if (Character.isWhitespace(c))
         {
            return i;
         }
      }
      return -1;
   }

   private static int firstNonWhitespace(char[] chars, int off, int len)
   {
      for (int i = off; i < len; i++)
      {
         char c = chars[i];
         if (!Character.isWhitespace(c))
         {
            return i;
         }
      }
      return -1;
   }

   private void flushLineBuffer(int nextChar) throws IOException
   {
      if (off == nextIdx)
      {
         // flush() was called with full buffer
         out.write(nl);
         off = 0;
         nextIdx = 0;
      }
      else
      {
         int startIdx = off == 0 ? firstNonWhitespace(lineBuffer, off, nextIdx) : off;
         if (startIdx < 0)
         {
            startIdx = off;
         }

         int stopIdx = Character.isWhitespace(nextChar) ? nextIdx : lastWhitespace(lineBuffer, off, nextIdx);
         if (stopIdx < 0 || stopIdx < startIdx)
         {
            stopIdx = nextIdx;
         }

         out.write(lineBuffer, startIdx, stopIdx - startIdx);
         out.write(nl);

         if (nextIdx > stopIdx + 1)
         {
            System.arraycopy(lineBuffer, stopIdx + 1, lineBuffer, 0, nextIdx - stopIdx - 1);
            off = 0;
            nextIdx = nextIdx - stopIdx - 1;
         }
         else
         {
            off = 0;
            nextIdx = 0;
         }
      }
   }

   @Override
   public void flush() throws IOException
   {
      if (off < nextIdx)
      {
         int startIdx = off == 0 ? firstNonWhitespace(lineBuffer, off, nextIdx) : off;
         if (startIdx < 0)
         {
            startIdx = off;
         }
         out.write(lineBuffer, startIdx, nextIdx - startIdx);
         off = nextIdx;
      }
      out.flush();
   }

   @Override
   public void close() throws IOException
   {
      flush();
      out.close();
   }

}
