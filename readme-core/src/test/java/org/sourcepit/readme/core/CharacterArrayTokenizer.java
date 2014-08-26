/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;


public final class CharacterArrayTokenizer
{
   public static interface TokenHandler<E extends Exception>
   {
      void literal(char[] chars, int off, int len) throws E;

      void whitespace(char[] chars, int off, int len) throws E;;

      void lf(char[] chars, int idx) throws E;;

      void cr(char[] chars, int idx) throws E;;
   }

   private CharacterArrayTokenizer()
   {
      super();
   }

   public static <E extends Exception> void tokenize(char[] chars, TokenHandler<E> tokens) throws E
   {
      tokenize(chars, chars.length, tokens);
   }

   public static <E extends Exception> void tokenize(char[] chars, int length, TokenHandler<E> tokens) throws E
   {
      int kind = -1;

      int off = 0;

      int i = 0;
      for (; i < length; i++)
      {
         char c = chars[i];

         if ('\n' == c || '\r' == c)
         {
            switch (kind)
            {
               case 0 :
                  tokens.whitespace(chars, off, i - off);
                  break;
               case 1 :
                  tokens.literal(chars, off, i - off);
                  break;
               case -1 :
                  break;
               default :
                  throw new IllegalStateException();
            }
            off = i;
            kind = -1;
            if ('\n' == c)
            {
               tokens.lf(chars, off);
            }
            else
            {
               tokens.cr(chars, off);
            }
            off++;
         }
         else if (Character.isWhitespace(c))
         {
            switch (kind)
            {
               case 0 :
                  break;
               case 1 :
                  tokens.literal(chars, off, i - off);
                  off = i;
               case -1 :
                  kind = 0;
                  break;
               default :
                  throw new IllegalStateException();
            }
         }
         else
         {
            switch (kind)
            {
               case 1 :
                  break;
               case 0 :
                  tokens.whitespace(chars, off, i - off);
                  off = i;
               case -1 :
                  kind = 1;
                  break;
               default :
                  throw new IllegalStateException();
            }
         }
      }

      switch (kind)
      {
         case 0 :
            tokens.whitespace(chars, off, i - off);
            break;
         case 1 :
            tokens.literal(chars, off, i - off);
            break;
         case -1 :
            kind = 1;
            break;
         default :
            throw new IllegalStateException();
      }
   }

}
