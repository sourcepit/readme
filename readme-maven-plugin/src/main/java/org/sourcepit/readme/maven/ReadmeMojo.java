/*
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_CLASSES;
import static org.sourcepit.common.maven.core.MavenProjectUtils.getOutputDir;
import static org.sourcepit.common.utils.io.IO.buffIn;
import static org.sourcepit.common.utils.io.IO.fileIn;
import static org.sourcepit.common.utils.io.IO.read;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.util.ReaderFactory;
import org.sourcepit.common.maven.core.MavenProjectUtils;
import org.sourcepit.common.utils.io.IO;
import org.sourcepit.common.utils.io.Read;

@Mojo(name = "generate", requiresProject = true, defaultPhase = PROCESS_CLASSES, aggregator = true)
public class ReadmeMojo extends AbstractMojo
{
   private final LegacySupport buildContext;

   @Parameter(required = true, property = "project.build.sourceEncoding")
   private String encoding;

   @Parameter(required = true, property = "project.build.directory")
   private File outputDirectory;

   @Inject
   public ReadmeMojo(LegacySupport buildContext)
   {
      this.buildContext = buildContext;
   }

   public void execute() throws MojoExecutionException, MojoFailureException
   {
      final MavenSession session = buildContext.getSession();

      final List<MavenProject> projects = session.getProjects();

      final boolean isMultiProjectBuild = projects.size() > 1;

      for (MavenProject project : projects)
      {
         if (isPluginProject(project))
         {
            PluginDescriptor pluginDescriptor;
            try
            {
               pluginDescriptor = readPluginDescriptor(project);
            }
            catch (IOException e)
            {
               throw new MojoExecutionException("Faild to read plugin descriptor for project " + project.getName(), e);
            }
            System.out.println(pluginDescriptor);
         }
      }

      PluginDescriptor pluginDescriptor = null;
   }

   private static boolean isPluginProject(MavenProject project)
   {
      return "maven-plugin".equals(project.getPackaging());
   }

   private static PluginDescriptor readPluginDescriptor(MavenProject project) throws IOException
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
