import static org.sourcepit.readme.maven.MavenUtils.*;
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
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.readme.core.DocumentBuilder;
import org.sourcepit.readme.core.HTMLToDocOM;

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

         def projects = session.projects
         projects.each
         { project ->
            if (!isPomProject(project))
            {
               addProject(doc, project, true)
            }
         }

         addLicenses(doc, session)
         doc.endChapter();
      }

      return doc.endDocument()
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

      doc.startChapter("Plugin Management")
      def hasCommandLineGoals = hasCommandLineGoals(plugin)
      def hasBuildLifecycleGoals = hasBuildLifecycleGoals(plugin)
      def requiresProject = isRequiresProject(plugin)


      if (requiresProject || hasBuildLifecycleGoals)
      {
         doc.startParagraph()
         doc.mk("It is a good practice to define plugin versions in the plugin management section of your or a parents `pom.xml`:")
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

      doc.startChapter("Plugin Goals and Usage")
      //      Goals available for this plugin:
      //      Brief examples on how to use the dependency goals.
      doc.mk("""\
The *goals* available for this plugin with brief examples on how to use it.

A plugin *goal* represents a specific task that could be executed during the build lifecycle and/or from command line. See also [A Build Phase is Made Up of Plugin Goals](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals).
""")

      addGoals(doc, plugin)
      doc.endChapter()

      if (closeChapter)
      {
         doc.endChapter()
      }
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
    <!-- Use the dependency in your POM or parent POM. -->
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
      doc.startUnorderedList()
      plugin.mojos.each
      { mojo ->
         addGoal(doc, mojo)
      }
      doc.endList()
   }

   void addGoal(DocumentBuilder doc, MojoDescriptor goal)
   {
      doc.startListItem()
      doc.startParagraph()
      doc.startEmphasis(EmphasisType.BOLD)
      doc.text(goal.goal)
      doc.endEmphasis()
      doc.endParagraph()

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
         def usage = "<execution>"

         def phase = goal.phase ;
         if (phase)
         {
            usage <<= """
          <!-- Optionally, you can bind this goal to a specific Maven phase, default is "${phase}". -->
          <!--phase>${phase}</phase-->"""
         } else
         {
            usage <<= """
          <!-- Bind his goal to a specific Maven phase. -->
          <phase />"""
         }

         usage <<= """
          <goals>
            <goal>${goal.goal}<goal>
          </goals>"""

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

               usage <<= "\n              -->"
               usage <<= "\n            <" + param.name +  " />"
            }
            usage <<= "\n          </configuration>"
         }
         usage <<= "\n        </execution>"

         doc.paragraph("To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it to your needs:")
         doc.code("""\
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>${plugin.groupId}</groupId>
        <artifactId>${plugin.artifactId}</artifactId>
        ${usage}
      </plugin>
    </plugins>
  </build>
</project>""").language = "xml"
      }

      if (describeDirectInvocation)
      {
         if (requiresProject)
         {
            doc.paragraph("To invoke this goal from command line, `cd` into your project folder an execute the following command:")
         }
         else
         {
            doc.paragraph("To invoke this goal from command line, execute the following command:")
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
      }

      doc.endListItem()
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
