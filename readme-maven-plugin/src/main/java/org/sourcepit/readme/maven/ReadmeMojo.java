/*
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_CLASSES;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;

import javax.inject.Inject;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.docom.Document;
import org.sourcepit.readme.core.DocOMToMarkdownConverter;

@Mojo(name = "generate", requiresProject = true, defaultPhase = PROCESS_CLASSES, aggregator = true)
public class ReadmeMojo extends AbstractMojo
{
   private final LegacySupport buildContext;

   @Parameter(required = true, defaultValue = "${project.build.sourceEncoding}")
   private String encoding;

   @Parameter(required = true, defaultValue = "${project.build.directory}")
   private File outputDirectory;

   @Parameter(required = false, defaultValue = "**")
   private String projectFilter;
   
   @Parameter(required = false, defaultValue = "**")
   private String projectContentFilter;

   @Parameter(required = false, defaultValue = "**")
   private String goalFilter;
   
   @Parameter(required = false, defaultValue = "**")
   private String goalContentFilter;

   @Inject
   public ReadmeMojo(LegacySupport buildContext)
   {
      this.buildContext = buildContext;
   }

   public void execute() throws MojoExecutionException, MojoFailureException
   {
      final PropertiesMap options = new LinkedPropertiesMap(2);
      options.put("doc.projectFilter", projectFilter);
      options.put("doc.projectContentFilter", projectContentFilter);
      options.put("doc.goalFilter", goalFilter);
      options.put("doc.goalContentFilter", goalContentFilter);

      GroovyScriptEngine gse = new GroovyScriptEngine(new ClasspathResourceConnector(getClass().getClassLoader()));
      try
      {
         @SuppressWarnings("unchecked")
         Class<DocumentCreator> clazz = gse.loadScriptByName("CreateReadme.groovy");

         DocumentCreator documentCreator = clazz.newInstance();

         Document document = documentCreator.createDocument(buildContext.getSession(), true, options);

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
      catch (InstantiationException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
