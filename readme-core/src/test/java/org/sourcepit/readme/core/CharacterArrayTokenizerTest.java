/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CharacterArrayTokenizerTest
{

   private final class TestTokenHandler implements CharacterArrayTokenizer.TokenHandler<RuntimeException>
   {
      private final List<String> tokens = new ArrayList<String>();

      public List<String> getTokens()
      {
         return tokens;
      }

      @Override
      public void literal(char[] chars, int off, int len)
      {
         tokens.add("literal: " + String.valueOf(chars, off, len));
      }

      @Override
      public void whitespace(char[] chars, int off, int len)
      {
         tokens.add("whitespace: " + String.valueOf(chars, off, len));
      }

      @Override
      public void lf(char[] chars, int idx)
      {
         tokens.add("lf: " + chars[idx]);
      }

      @Override
      public void cr(char[] chars, int idx)
      {
         tokens.add("cr: " + chars[idx]);
      }
   }

   @Test
   public void testSingleLiteral()
   {
      char[] charArray = "hallo".toCharArray();

      TestTokenHandler tokenHandler = new TestTokenHandler();

      CharacterArrayTokenizer.tokenize(charArray, tokenHandler);

      List<String> tokens = tokenHandler.getTokens();
      assertEquals("[literal: hallo]", tokens.toString());
   }

   @Test
   public void testSingleWhitespace()
   {
      char[] charArray = "\t".toCharArray();

      TestTokenHandler tokenHandler = new TestTokenHandler();

      CharacterArrayTokenizer.tokenize(charArray, tokenHandler);

      List<String> tokens = tokenHandler.getTokens();
      assertEquals("[whitespace: \t]", tokens.toString());
   }

   @Test
   public void testSingleLF()
   {
      char[] charArray = "\n".toCharArray();

      TestTokenHandler tokenHandler = new TestTokenHandler();

      CharacterArrayTokenizer.tokenize(charArray, tokenHandler);

      List<String> tokens = tokenHandler.getTokens();
      assertEquals("[lf: \n]", tokens.toString());
   }

   @Test
   public void testSingleCR()
   {
      char[] charArray = "\r".toCharArray();

      TestTokenHandler tokenHandler = new TestTokenHandler();

      CharacterArrayTokenizer.tokenize(charArray, tokenHandler);

      List<String> tokens = tokenHandler.getTokens();
      assertEquals("[cr: \r]", tokens.toString());
   }

   @Test
   public void testMixed()
   {
      char[] charArray = "  Ha\r \n l \r\n \to ".toCharArray();

      TestTokenHandler tokenHandler = new TestTokenHandler();

      CharacterArrayTokenizer.tokenize(charArray, tokenHandler);

      List<String> tokens = tokenHandler.getTokens();
      assertEquals(
         "[whitespace:   , literal: Ha, cr: \r, whitespace:  , lf: \n, whitespace:  , literal: l, whitespace:  , cr: \r, lf: \n, whitespace:  \t, literal: o, whitespace:  ]",
         tokens.toString());
   }


}
