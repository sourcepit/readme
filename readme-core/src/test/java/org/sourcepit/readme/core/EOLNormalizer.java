/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;

public class EOLNormalizer extends AbstractAppendable implements Appendable
{
   private final Appendable out;

   private final char[] eol;

   public EOLNormalizer(Appendable out, EOL eol)
   {
      this.out = out;
      this.eol = eol.asChars();
   }

   private boolean skipNextLF = false;

   @Override
   public EOLNormalizer append(CharSequence csq) throws IOException
   {
      return (EOLNormalizer) super.append(csq);
   }

   @Override
   public EOLNormalizer append(CharSequence csq, int start, int end) throws IOException
   {
      return (EOLNormalizer) super.append(csq, start, end);
   }

   @Override
   public EOLNormalizer append(char c) throws IOException
   {
      if (skipNextLF && c == '\n')
      {
         skipNextLF = false;
      }
      else
      {
         skipNextLF = c == '\r';
         if (c == '\n' || c == '\r')
         {
            for (int i = 0; i < eol.length; i++)
            {
               out.append(eol[i]);
            }
         }
         else
         {
            out.append(c);
         }
      }
      return this;
   }
}
