/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven.its;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.sourcepit.common.maven.testing.ExternalMavenTest;
import org.sourcepit.common.testing.Environment;

public class ReadmeIT extends ExternalMavenTest
{

   @Override
   protected Environment newEnvironment()
   {
      return Environment.get("it-env.properties");
   }

   @Override
   protected boolean isDebug()
   {
      return false;
   }

   @Test
   public void testName() throws Exception
   {
      final File projectDir = getResource("test-project");
      build(projectDir, "-e", "-B", "clean", "process-classes", "org.sourcepit:readme-maven-plugin:"
         + getEnvironment().getProperty("project.version") + ":generate");
   }

}
