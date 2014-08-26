/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.sourcepit.readme.core.CharacterArrayTokenizer.tokenize;

import java.io.IOException;
import java.io.Writer;

import org.sourcepit.readme.core.CharacterArrayTokenizer.TokenHandler;

public class WordWrapppingWriter extends Writer
{
   private final EOLNormalizer normalizer;

   private final char[] eol;
   private final int maxLength;

   private final char[] buffer;

   private int nextChar = 0;

   private final Writer out;

   public WordWrapppingWriter(Writer out, int maxLength, EOL eol)
   {
      this(out, 512, maxLength, eol);
   }

   public WordWrapppingWriter(Writer out, int bufferSize, int maxLength, EOL eol)
   {
      super(out);

      buffer = new char[bufferSize];
      this.out = out;

      normalizer = new EOLNormalizer(new AbstractAppendable()
      {
         @Override
         public Appendable append(char c) throws IOException
         {
            if (nextChar == buffer.length)
            {
               flush(nextChar);
            }
            buffer[nextChar++] = c;
            return this;
         }
      }, EOL.LF);

      this.eol = eol.asChars();
      this.maxLength = maxLength - this.eol.length;
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      for (int i = off; i < len; i++)
      {
         normalizer.append(cbuf[i]);
      }
   }

   @Override
   public void flush() throws IOException
   {
      if (nextChar > 0)
      {
         flush(nextChar);
      }
      out.flush();
   }

   @Override
   public void close() throws IOException
   {
      flush();
      out.close();
   }

   private int lineLength = 0;

   private String ws = "";

   private void flush(int bufferLength) throws IOException
   {
      tokenize(buffer, bufferLength, new TokenHandler<IOException>()
      {
         @Override
         public void literal(char[] chars, int off, int len) throws IOException
         {
            final int requiredSpace = ws.length() + len;

            if (lineLength == 0)
            {
               out.write(ws);
               out.write(chars, off, len);
               lineLength = requiredSpace;
            }
            else if (lineLength + requiredSpace <= maxLength)
            {
               out.write(ws);
               out.write(chars, off, len);
               lineLength += requiredSpace;
            }
            else
            {
               if (ws.length() == 0)
               {
                  out.write(chars, off, len);
                  lineLength += requiredSpace;
               }
               else
               {
                  out.write(eol);
                  out.write(chars, off, len);
                  lineLength = len;
               }
            }

            ws = "";
         }

         @Override
         public void whitespace(char[] chars, int off, int len) throws IOException
         {
            ws = String.valueOf(chars, off, len);
         }

         @Override
         public void lf(char[] chars, int idx) throws IOException
         {
            lineLength = 0;
            ws = "";
            out.write(eol);
         }

         @Override
         public void cr(char[] chars, int idx)
         {
            throw new IllegalStateException();
         }
      });

      nextChar = 0;
   }
}