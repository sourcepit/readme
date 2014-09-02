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
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.execution.MavenSession;
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

   public static boolean isPomProject(MavenProject project)
   {
      return "pom".equals(project.getPackaging());
   }

   public static boolean isMavenPlugin(MavenProject project)
   {
      return "maven-plugin".equals(project.getPackaging());
   }

   public static MavenProject getBuildParent(MavenSession session)
   {
      for (MavenProject project : session.getProjects())
      {
         if (findParentInBuild(session, project) == null && isBuildParent(session, project))
         {
            return project;
         }
      }
      throw new IllegalStateException();
   }

   private static boolean isBuildParent(MavenSession session, MavenProject project)
   {
      for (MavenProject mavenProject : session.getProjects())
      {
         if (project.equals(mavenProject) || isSubProjectOf(project, mavenProject))
         {
            continue;
         }
         return false;
      }
      return session.getProjects().contains(project);
   }

   private static boolean isSubProjectOf(MavenProject parent, MavenProject project)
   {
      final List<MavenProject> modules = parent.getCollectedProjects();
      if (modules.contains(project))
      {
         return true;
      }

      for (MavenProject module : modules)
      {
         if (isSubProjectOf(module, project))
         {
            return true;
         }
      }

      return false;
   }

   public static MavenProject findParentInBuild(MavenSession session, MavenProject project)
   {
      MavenProject parent = project.getParent();
      if (parent != null && session.getProjects().contains(parent))
      {
         return parent;
      }
      return null;
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
