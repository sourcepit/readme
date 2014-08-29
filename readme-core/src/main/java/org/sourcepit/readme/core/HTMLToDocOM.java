/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Literal;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;

public class HTMLToDocOM
{

   public Document convert(String html)
   {
      String remarked = toMarkdown(html);
      return new MarkdownToDocOMConverter().toDocOM(remarked);
   }

   public String toMarkdown(String html)
   {
      final Options github = Options.github();
      github.hardwraps = false;

      Remark remark = new Remark(github);
      return remark.convert(html);
   }

   @SuppressWarnings("unchecked")
   public <T extends EObject> T convert(String html, Class<T> expected)
   {
      return convert(convert(html), expected);
   }

   public <T extends EObject> T convert(String html, Class<? extends T>... expected)
   {
      return convert(convert(html), expected);
   }

   @SuppressWarnings("unchecked")
   public <T extends EObject> T convert(Document document, Class<? extends T>... expected)
   {
      EObject eObject = document;
      while (eObject.eContents().size() == 1)
      {
         if (eObject instanceof Literal)
         {
            break;
         }
         eObject = eObject.eContents().get(0);
      }

      while (!isExpected(eObject, expected))
      {
         if (eObject.eContainer() == null)
         {
            throw new IllegalStateException("Cannot find any expected type " + Arrays.toString(expected));
         }
         eObject = eObject.eContainer();
      }

      return (T) eObject;
   }

   private static boolean isExpected(EObject eObject, Class<?>[] expected)
   {
      for (Class<?> clazz : expected)
      {
         if (clazz.isAssignableFrom(eObject.getClass()))
         {
            return true;
         }
      }
      return false;
   }
}
