/*
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_CLASSES;
import groovy.lang.Binding;
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
import org.sourcepit.docom.Document;
import org.sourcepit.readme.core.DocOMToMarkdownConverter;
import org.sourcepit.readme.core.DocumentBuilder;
import org.sourcepit.readme.core.HTMLToDocOM;

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
      binding.setVariable("documentBuilder", new DocumentBuilder());
      binding.setVariable("htmlToDocOM", new HTMLToDocOM());
      

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
