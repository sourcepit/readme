/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.sourcepit.readme.maven.MavenUtils.getDirectParentFromReactor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

import com.google.common.base.Objects;

public class ReactorProjectsComparator implements Comparator<MavenProject>
{
   private final MavenSession session;

   public ReactorProjectsComparator(MavenSession session)
   {
      this.session = session;
   }

   @Override
   public int compare(MavenProject p1, MavenProject p2)
   {
      return compare(session, p1, p2);
   }

   private static int compare(MavenSession session, MavenProject p1, MavenProject p2)
   {
      final MavenProject parent1 = getDirectParentFromReactor(session, p1);
      final MavenProject parent2 = getDirectParentFromReactor(session, p2);
      if (Objects.equal(parent1, parent2))
      {
         // sort by module
         return compare(parent1, p1, p2);
      }
      else
      {
         List<MavenProject> parentPath1 = getParentsFromReactor(session, p1);
         List<MavenProject> parentPath2 = getParentsFromReactor(session, p2);
         return compare(parentPath1, parentPath2);
      }
   }

   private static int compare(List<MavenProject> parentPath1, List<MavenProject> parentPath2)
   {
      return 0;
   }

   private static int compare(MavenProject parent, MavenProject module1, MavenProject module2)
   {
      final List<MavenProject> modules = parent.getCollectedProjects();

      final int idx1 = modules.indexOf(module1);
      if (idx1 < 0)
      {
         throw new IllegalStateException();
      }

      final int idx2 = modules.indexOf(module2);
      if (idx2 < 0)
      {
         throw new IllegalStateException();
      }

      return idx1 - idx2;
   }

   private static List<MavenProject> getParentsFromReactor(MavenSession session, MavenProject project)
   {
      final List<MavenProject> parents = new ArrayList<MavenProject>();
      collectParentsFromReactor(parents, session, project);
      return parents;
   }

   private static void collectParentsFromReactor(List<MavenProject> parents, MavenSession session, MavenProject project)
   {
      final MavenProject parent = getDirectParentFromReactor(session, project);
      if (parent != null)
      {
         getDirectParentFromReactor(session, parent);
         parents.add(parent);
      }
   }

}
