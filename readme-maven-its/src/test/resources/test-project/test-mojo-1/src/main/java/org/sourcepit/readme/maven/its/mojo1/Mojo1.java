/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven.its.mojo1;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_CLASSES;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "goalName", requiresProject = true, defaultPhase = PROCESS_CLASSES, aggregator = true)
public class Mojo1 extends AbstractMojo
{
   @Override
   public void execute() throws MojoExecutionException, MojoFailureException
   { // noop
   }
}
