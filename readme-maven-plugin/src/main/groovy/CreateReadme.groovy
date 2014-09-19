import static org.sourcepit.readme.maven.MavenUtils.*;
import static org.sourcepit.readme.maven.TOCUtils.*;
import groovy.transform.EqualsAndHashCode;

import java.lang.annotation.Documented;

import javax.print.attribute.standard.MediaSize.ISO;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.maven.execution.MavenSession;
import org.sourcepit.readme.maven.DocumentCreator;
import org.sourcepit.readme.maven.GoalInvocation;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory
import org.sourcepit.docom.Document
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.readme.core.DocumentBuilder;
import org.sourcepit.readme.core.HTMLToDocOM;
import org.sourcepit.readme.core.TOCCreator;

class CreateReadme implements DocumentCreator
{
   private static final HTMLToDocOM HTML = new HTMLToDocOM()

   @Override
   public Document createDocument(MavenSession session, boolean aggregate)
   {
      def doc = new DocumentBuilder()
      doc.startDocument()

      if (session.projects == 1 || !aggregate)
      {
         def project = session.currentProject
         addProject(doc, project, false)
         addLicenses(doc, project)
         doc.endChapter();
      }
      else
      {
         def buildParent = getBuildParent(session)
         addProject(doc, buildParent, false)

         doc.paragraph("[TOC,3]")

         def projects = session.projects.findAll{!isPomProject(it)}.sort{ it.name }

         doc.startChapter("Sub Projects")
         projects.each
         { project ->
            addProject(doc, project, true)
         }
         doc.endChapter();

         def hasIssueManagement = buildParent.issueManagement && buildParent.issueManagement.url
         def scmIsGitHub = buildParent.scm && buildParent.scm.url && buildParent.scm.url.contains('://github.com/')

         if (hasIssueManagement || scmIsGitHub)
         {
            doc.startChapter("How to Contribute")
            if (hasIssueManagement)
            {
               doc.mk("The simplest way of contributing is probably to report issues. You can do so using the [Issue Tracker](${buildParent.issueManagement.url}).")
            }
            if (scmIsGitHub)
            {
               doc.mk("""\
If you want to contribute your code or just want to share it with others, you [can create a fork of the official repository](${buildParent.scm.url}/fork) at any time, for which you will have full access so that your local changesets can be pushed to it.
               
Once your code is ready and accepted (see code style section below), it is then easy for the project owners to pull your changesets into the official repository - all you have to do is to [create a pull request](https://help.github.com/articles/creating-a-pull-request).
               
For general information see [Contributing to Open Source on GitHub](https://guides.github.com/activities/contributing-to-open-source).""")
            }
            doc.endChapter();
         }

         addLicenses(doc, session)

         doc.endChapter();
      }

      def document = doc.endDocument();
      injectTOCs(document, 3)
      return document;
   }

   void addProject(DocumentBuilder doc, MavenProject project, boolean closeChapter)
   {
      if (isMavenPlugin(project))
      {
         addPlugin(doc, project, readPluginDescriptor(project), closeChapter)
      }
      else if (isPomProject(project))
      {
         addParent(doc, project, closeChapter)
      }
      else
      {
         addLibrary(doc, project, closeChapter)
      }
   }

   void addParent(DocumentBuilder doc, MavenProject project, boolean closeChapter)
   {
      doc.startChapter(project.name)
      def desc = project.description;
      if (desc)
      {
         doc.paragraph(desc);
      }

      if (closeChapter)
      {
         doc.endChapter()
      }
   }

   void addPlugin(DocumentBuilder doc, MavenProject project, PluginDescriptor plugin, boolean closeChapter)
   {
      doc.startChapter(project.name)
      def desc = project.description;
      if (desc)
      {
         if (!project.parent || !desc.equals(project.parent.description))
         {
            doc.paragraph(desc);
         }
      }

      addPluginGoals(doc, plugin)
      addPluginManagement(doc, plugin)

      if (closeChapter)
      {
         doc.endChapter()
      }
   }

   void addPluginGoals(DocumentBuilder doc, PluginDescriptor plugin)
   {
      doc.startChapter("Plugin Goals and Usage")
      doc.mk("""\
Here you can find the documentation and usage examples for all goals provided by this plugin. A goal represents a specific task that can be executed either during the build lifecycle of your project or by command line. See also [A Build Phase is Made Up of Plugin Goals](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals).

Available goals:
""")

      doc.paragraph("[TOC,1]")

      addGoals(doc, plugin)
      doc.endChapter()
   }

   void addPluginManagement(DocumentBuilder doc, PluginDescriptor plugin)
   {
      doc.startChapter("Plugin Management")
      def hasCommandLineGoals = hasCommandLineGoals(plugin)
      def hasBuildLifecycleGoals = hasBuildLifecycleGoals(plugin)
      def requiresProject = isRequiresProject(plugin)

      if (requiresProject || hasBuildLifecycleGoals)
      {
         doc.startParagraph()
         doc.mk("It is a good practice to define plugin versions in the plugin management section of your or a parents `pom.xml`.")
         doc.endParagraph()
         doc.code("""\
<project>
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>${plugin.groupId}</groupId>
          <artifactId>${plugin.artifactId}</artifactId>
          <version>${plugin.version}</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>""").language = "xml"
      }

      if (hasCommandLineGoals)
      {
         doc.mk("""This plugin also provides goals that can be invoked from command line. For convenience you can use the shorter plugin prefix `$plugin.goalPrefix` for that instead of the full-blown plugin coordinates. To enable the prefix add this plugins group id to the list of plugin groups in your 'settings.xml':

```xml
<settings>
  <pluginGroups>
    <pluginGroup>${plugin.groupId}</pluginGroup>
  </pluginGroups>
</settings>
```

See also [Introduction to Plugin Prefix Resolution](http://maven.apache.org/guides/introduction/introduction-to-plugin-prefix-mapping.html).""")
      }
      doc.endChapter()
   }

   void addLibrary(DocumentBuilder doc, MavenProject project, boolean closeChapter)
   {
      doc.startChapter(project.name)
      def desc = project.description;
      if (desc)
      {
         if (!project.parent || !desc.equals(project.parent.description))
         {
            doc.paragraph(desc);
         }
      }
      def appendType =
      {
         -> if (!"jar".equals(project.artifact.type))
         {
            """
                    <type>${project.artifact.type}</type>"""
         } else
         {
            ""
         }
      }
      doc.code("""\
<project>
  <dependencies>
    <dependency>
      <groupId>${project.groupId}</groupId>
      <artifactId>${project.artifactId}</artifactId>
      <version>${project.version}</version>${appendType}
    </dependency>
  </dependencies>
</project>""").language = "xml"

      if (closeChapter)
      {
         doc.endChapter()
      }
   }

   void addGoals(DocumentBuilder doc, PluginDescriptor plugin)
   {
      def goals = plugin.mojos.collect().sort
      { it.goal }
      plugin.mojos.each
      { mojo ->
         addGoal(doc, mojo)
      }
   }

   void addGoal(DocumentBuilder doc, MojoDescriptor goal)
   {
      doc.startChapter(goal.fullGoalName)

      def descr = goal.description
      if (descr)
      {
         def p = doc.startParagraph()
         def result = HTML.convert(descr, Literal.class, LiteralGroup.class);
         if (result instanceof Literal)
         {
            p.literals.add(result);
         }
         else
         {
            p.literals.addAll(((LiteralGroup)result).literals);
         }
         doc.endParagraph()
      }

      def requiresProject = goal.projectRequired

      def invocation = getGoalInvocation(goal)
      def describeDirectInvocation = invocation == GoalInvocation.CLI
      def describePluginInvocation = invocation == GoalInvocation.BUILD

      def plugin = goal.pluginDescriptor

      if (describePluginInvocation)
      {
         def usage = ""

         if (goal.parameters && !goal.parameters.empty)
         {
            usage <<= "\n        <configuration>"

            goal.parameters.each
            { param ->
               usage <<= "\n          <!-- "

               if (param.description)
               {
                  usage <<= "\n            "
                  usage <<= HTML.toMarkdown(param.description)
                  usage <<= "\n            "
               }

               if (param.expression)
               {
                  usage <<= "\n            Property: " + param.expression
               }

               if (param.type)
               {
                  usage <<= "\n            Type: " + param.type
               }

               if (param.defaultValue)
               {
                  usage <<= "\n            Default Value: " + param.defaultValue
               }

               usage <<= "\n            Required: " + param.required

               if (param.deprecated)
               {
                  usage <<= "\n            Deprecated: " + param.deprecated
               }

               if (param.since)
               {
                  usage <<= "\n            Since: " + param.since
               }

               usage <<= "\n          -->"
               usage <<= "\n          <" + param.name +  " />"
            }
            usage <<= "\n        </configuration>"
         }

         doc.paragraph("To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it to your needs.")
         doc.code("""\
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>${plugin.groupId}</groupId>
        <artifactId>${plugin.artifactId}</artifactId>
        <goals>
          <goal>${goal.goal}<goal> <!-- bount to phase *${goal.phase}* -->
        </goals>${usage}
      </plugin>
    </plugins>
  </build>
</project>""").language = "xml"
      }

      if (describeDirectInvocation)
      {
         if (requiresProject)
         {
            doc.paragraph("To invoke this goal from command line, `cd` into your project folder an execute the following command.")
         }
         else
         {
            doc.paragraph("To invoke this goal from command line, execute the following command.")
         }

         def phase = goal.phase ;
         if (phase)
         {
            phase = phase + " "
         }
         else
         {
            phase = ""
         }


         doc.mk("""\
```
mvn ${phase}${plugin.groupId}:${plugin.artifactId}[:${plugin.version}]:${goal.goal} [<propertie(s)>]
```

If you enabled the usage of this plugins plugin prefix, you can also use the shorter version:

```
mvn ${phase}${plugin.goalPrefix}:${goal.goal} [<propertie(s)>]
```

""")
         if (requiresProject)
         {
            doc.paragraph("You can also pre-configure the properties for this goal for your project, so it is no more necessary to set the properties in your command. To achive that use the plugins management section in your `pom.xml`.")

            def usage = ""

            if (goal.parameters && !goal.parameters.empty)
            {
               usage <<= "\n          <configuration>"

               goal.parameters.each
               { param ->
                  usage <<= "\n            <!-- "

                  if (param.description)
                  {
                     usage <<= "\n              "
                     usage <<= HTML.toMarkdown(param.description)
                     usage <<= "\n              "
                  }

                  if (param.expression)
                  {
                     usage <<= "\n              Property: " + param.expression
                  }

                  if (param.type)
                  {
                     usage <<= "\n              Type: " + param.type
                  }

                  if (param.defaultValue)
                  {
                     usage <<= "\n              Default Value: " + param.defaultValue
                  }

                  usage <<= "\n              Required: " + param.required

                  if (param.deprecated)
                  {
                     usage <<= "\n              Deprecated: " + param.deprecated
                  }

                  if (param.since)
                  {
                     usage <<= "\n              Since: " + param.since
                  }

                  usage <<= "\n            -->"
                  usage <<= "\n            <" + param.name +  " />"
               }
               usage <<= "\n          </configuration>"
            }

            doc.code("""\
<project>
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>${plugin.groupId}</groupId>
          <artifactId>${plugin.artifactId}</artifactId>
          <version>${plugin.version}</version>${usage}
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>""").language = "xml"
         }
      }

      doc.endChapter()
   }

   @EqualsAndHashCode(includeFields=true)
   class Lic
   {
      String name, url;
   }

   void addLicenses(DocumentBuilder doc, MavenSession session)
   {
      def projects = session.getProjects();

      def lics = new HashSet<Lic>();

      projects.each
      { project ->
         project.licenses.each
         { license ->
            def lic = new Lic()
            lic.name = license.name
            lic.url = license.url

            lics << lic;
         }
      }

      addLicenses(doc, lics);
   }

   void addLicenses(DocumentBuilder doc, MavenProject project)
   {
      def lics = new HashSet<Lic>();
      project.licenses.each
      { license ->
         def lic = new Lic()
         lic.name = license.name
         lic.url = license.url

         lics << lic;
      }
      addLicenses(doc, lics);
   }

   void addLicenses(DocumentBuilder doc, Set<Lic> lics)
   {
      if (lics.size() == 0)
      {
         return
      }

      if (lics.size() == 1)
      {
         doc.startChapter("License")
         doc.startParagraph()
      }
      else
      {
         doc.startChapter("Licenses")
         doc.startUnorderedList()
      }

      lics.each
      { lic ->
         if (lics.size() > 1)
         {
            doc.startListItem()
         }

         doc.link(lic.name, lic.url)

         if (lics.size() > 1)
         {
            doc.endListItem()
         }
      }

      if (lics.size() == 1)
      {
         doc.endParagraph()
      }
      else
      {
         doc.endList()
      }

      doc.endChapter()
   }
}
