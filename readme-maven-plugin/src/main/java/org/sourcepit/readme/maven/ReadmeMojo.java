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
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

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
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.readme.core.DocOMToMarkdownConverter;

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
      Binding binding = new Binding();
      binding.setVariable("mavenSession", buildContext.getSession());
      binding.setVariable("modelFactory", DocOMFactory.eINSTANCE);

      GroovyScriptEngine gse = new GroovyScriptEngine(new ClasspathResourceConnector(getClass().getClassLoader()));
      try
      {
         Document document = (Document) gse.run("CreateReadme.groovy", binding);

         DocOMToMarkdownConverter c = new DocOMToMarkdownConverter();
         System.out.println(c.toMarkdown(document));
      }
      catch (ResourceException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (ScriptException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
