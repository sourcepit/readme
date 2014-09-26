/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.sourcepit.common.maven.core.MavenProjectUtils.getOutputDir;
import static org.sourcepit.common.utils.props.PropertiesSources.chain;
import static org.sourcepit.common.utils.props.PropertiesSources.toPropertiesSource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.apache.maven.plugin.descriptor.Parameter;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.util.ReaderFactory;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;

import com.google.common.base.Objects;

public final class MavenUtils
{

   private MavenUtils()
   {
      super();
   }

   public static boolean isSelected(MavenProject project, PropertiesSource options)
   {
      final String projectFilter = options.get("doc.projectFilter");
      if (!isNullOrEmpty(projectFilter))
      {
         final String key = project.getGroupId() + ":" + project.getArtifactId();
         return PathMatcher.parse(projectFilter, ":", ",").isMatch(key);
      }
      return true;
   }

   public static boolean isGenerateContent(MavenProject project, PropertiesSource options)
   {
      final String projectFilter = options.get("doc.projectContentFilter");
      if (!isNullOrEmpty(projectFilter))
      {
         final String key = project.getGroupId() + ":" + project.getArtifactId();
         return PathMatcher.parse(projectFilter, ":", ",").isMatch(key);
      }
      return true;
   }

   public static boolean isSelected(MojoDescriptor goal, PropertiesSource options)
   {
      final String goalFilter = options.get("doc.goalFilter");
      return isNullOrEmpty(goalFilter) ? true : PathMatcher.parse(goalFilter, ":", ",").isMatch(goal.getFullGoalName());
   }

   public static boolean isGenerateContent(MojoDescriptor goal, PropertiesSource options)
   {
      final String goalFilter = options.get("doc.goalContentFilter");
      return isNullOrEmpty(goalFilter) ? true : PathMatcher.parse(goalFilter, ":", ",").isMatch(goal.getFullGoalName());
   }

   public static PropertiesSource getBuildProperties(MavenProject project)
   {
      return chain(toPropertiesSource(project.getProperties()), toPropertiesSource(System.getProperties()));
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
         if (getDirectParentFromReactor(session, project) == null && isBuildParent(session, project))
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

   public static MavenProject getDirectParentFromReactor(MavenSession session, MavenProject project)
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

   private static boolean isParamatersPreparedForCLI(Collection<Parameter> paramaters)
   {
      for (Parameter parameter : paramaters)
      {
         if (isNullOrEmpty(parameter.getExpression()))
         {
            return false;
         }
      }
      return true;
   }


   public static boolean isRequiresProject(PluginDescriptor plugin)
   {
      for (MojoDescriptor mojo : plugin.getMojos())
      {
         if (mojo.isProjectRequired())
         {
            return true;
         }
      }
      return false;
   }

   public static boolean hasCommandLineGoals(PluginDescriptor plugin)
   {
      for (MojoDescriptor goal : plugin.getMojos())
      {
         final GoalInvocation invokation = getGoalInvocation(goal);
         if (invokation == GoalInvocation.CLI)
         {
            return true;
         }
      }
      return false;
   }

   public static boolean hasBuildLifecycleGoals(PluginDescriptor plugin)
   {
      for (MojoDescriptor goal : plugin.getMojos())
      {
         final GoalInvocation invokation = getGoalInvocation(goal);
         if (invokation == GoalInvocation.BUILD)
         {
            return true;
         }
      }
      return false;
   }

   public static GoalInvocation getGoalInvocation(MojoDescriptor goal)
   {
      if (goal.isDirectInvocationOnly())
      {
         return GoalInvocation.CLI;
      }
      else
      {
         final boolean cliParams = goal.getParameters() == null ? true : isParamatersPreparedForCLI(goal
            .getParameters());
         final boolean aggregator = goal.isAggregator();
         final boolean requiresProject = goal.isProjectRequired();
         final boolean phaseBound = goal.getPhase() != null;
         return getGoalInvocation(cliParams, aggregator, requiresProject, phaseBound);
      }
   }

   static GoalInvocation getGoalInvocation(final boolean cliParams, final boolean aggregator, boolean requiresProject,
      final boolean phaseBound)
   {
      if (aggregator || (!aggregator && !phaseBound))
      {
         return GoalInvocation.CLI;
      }

      if (!aggregator && phaseBound)
      {
         return GoalInvocation.BUILD;
      }

      throw new IllegalStateException();
   }
}
