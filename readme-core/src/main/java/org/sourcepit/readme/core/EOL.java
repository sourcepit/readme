/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;
public enum EOL
{
   LF('\n'), CRLF('\r', '\n'), CR('\r');

   private static EOL system;

   private final char[] chars;

   private final String string;

   private EOL(char... eol)
   {
      this.chars = eol;
      this.string = String.valueOf(chars, 0, chars.length);
   }

   public static EOL system()
   {
      if (system == null)
      {
         system = parse(System.getProperty("line.separator"));
      }
      return system;
   }

   public static EOL parse(final String string)
   {
      for (EOL eol : values())
      {
         if (eol.string.equals(string))
         {
            return eol;
         }
      }
      throw new UnsupportedOperationException("Unsupported EOL style: " + string);
   }

   public String asString()
   {
      return string;
   }

   public char[] asChars()
   {
      return chars;
   }
}
