/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.sourcepit.common.maven.core.MavenProjectUtils.getOutputDir;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.util.ReaderFactory;

public final class MavenUtils
{

   private MavenUtils()
   {
      super();
   }

   public static boolean isMavenPlugin(MavenProject project)
   {
      return "maven-plugin".equals(project.getPackaging());
   }

   public static PluginDescriptor readPluginDescriptor(MavenProject project) throws IOException
   {
      final File pluginFile = new File(getOutputDir(project), "META-INF/maven/plugin.xml");
      Reader reader = null;
      try
      {
         reader = ReaderFactory.newXmlReader(new BufferedInputStream(new FileInputStream(pluginFile)));
         return new PluginDescriptorBuilder().build(reader, pluginFile.getAbsolutePath());
      }
      catch (PlexusConfigurationException e)
      {
         throw new IOException(e);
      }
      finally
      {
         IOUtils.closeQuietly(reader);
      }
   }
}
