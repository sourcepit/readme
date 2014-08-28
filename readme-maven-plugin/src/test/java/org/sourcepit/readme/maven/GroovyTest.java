/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.junit.Assert.*;

import java.io.File;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import org.junit.Test;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;

public class GroovyTest
{
   @Test
   public void testEmbeddedEvaluation() throws Exception
   {
      Binding binding = new Binding();
      binding.setVariable("foo", new Integer(2));
      GroovyShell shell = new GroovyShell(binding);

      Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");

      assertEquals(Integer.valueOf(20), value);
      assertEquals(Integer.valueOf(123), binding.getVariable("x"));
   }

   @Test
   public void testExecuteEngine() throws Exception
   {
      final ClassLoader classLoader = getClass().getClassLoader();

      GroovyScriptEngine gse = new GroovyScriptEngine(new ClasspathResourceConnector(classLoader));

      Binding binding = new Binding();
      binding.setVariable("modelFactory", DocOMFactory.eINSTANCE);

      Document run = (Document) gse.run("CreateReadme.groovy", binding);
      System.out.println(run);
   }
}
