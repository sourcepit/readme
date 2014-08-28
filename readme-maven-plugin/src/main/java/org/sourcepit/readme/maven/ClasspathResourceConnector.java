/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import groovy.util.ResourceConnector;
import groovy.util.ResourceException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ClasspathResourceConnector implements ResourceConnector
{
   private final ClassLoader classLoader;

   ClasspathResourceConnector(ClassLoader classLoader)
   {
      this.classLoader = classLoader;
   }

   @Override
   public URLConnection getResourceConnection(String resourceName) throws ResourceException
   {
      final URL url = classLoader.getResource(resourceName);
      if (url == null)
      {
         throw new ResourceException("No resource for " + resourceName + " was found");
      }
      try
      {
         return url.openConnection();
      }
      catch (IOException e)
      {
         throw new ResourceException("Cannot open URL: " + url, e);
      }
   }
}