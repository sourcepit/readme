/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.maven;

import static org.junit.Assert.*;
import static org.sourcepit.readme.maven.MavenUtils.getGoalInvocation;

import org.junit.Test;

public class MavenUtilsTest
{

   @Test
   public void test()
   {
      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, false, false, false));

      assertEquals(GoalInvocation.BUILD, getGoalInvocation(false, false, false, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, false, true, false));

      assertEquals(GoalInvocation.BUILD, getGoalInvocation(false, false, true, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, true, false, false));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, true, false, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, true, true, false));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(false, true, true, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, false, false, false));

      assertEquals(GoalInvocation.BUILD, getGoalInvocation(true, false, false, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, false, true, false));

      assertEquals(GoalInvocation.BUILD, getGoalInvocation(true, false, true, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, true, false, false));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, true, false, true));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, true, true, false));

      assertEquals(GoalInvocation.CLI, getGoalInvocation(true, true, true, true));
   }

}
