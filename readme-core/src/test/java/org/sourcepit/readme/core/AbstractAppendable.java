/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.io.IOException;

public abstract class AbstractAppendable implements Appendable
{

   @Override
   public Appendable append(CharSequence csq) throws IOException
   {
      for (int i = 0; i < csq.length(); i++)
      {
         append(csq.charAt(i));
      }
      return this;
   }

   @Override
   public Appendable append(CharSequence csq, int start, int end) throws IOException
   {
      for (int i = start; i < end - start; i++)
      {
         append(csq.charAt(i));
      }
      return this;
   }

}
