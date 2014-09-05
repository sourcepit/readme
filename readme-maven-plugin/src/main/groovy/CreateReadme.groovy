import static org.sourcepit.readme.maven.MavenUtils.*;
import groovy.transform.EqualsAndHashCode;

import java.lang.annotation.Documented;

import javax.print.attribute.standard.MediaSize.ISO;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.maven.execution.MavenSession;import org.sourcepit.readme.maven.DocumentCreator;
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

      def goalInvokation = getGoalInvocation(plugin)
      def requiresProject = isRequiresProject(plugin)
      

      if (requiresProject || goalInvokation == GoalInvocation.BUILD_ONLY || goalInvokation == GoalInvocation.DIRECT_AND_BUILD )
      {
         doc.startParagraph()
         doc.mk("Best practice is to define the version of the plugin that you want to use in either your `pom.xml` or a parent `pom.xml`")
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

      if (goalInvokation == GoalInvocation.DIRECT_ONLY || goalInvokation == GoalInvocation.DIRECT_AND_BUILD )
      {
         doc.mk("""This plugin also provides goals that can be invoked via command line. For convenience you can use the shorter plugin prefix `$plugin.goalPrefix` in your commands by adding this plugins group id to the list of plugin groups in your 'settings.xml'.

```
<settings>
    <pluginGroups>
        <pluginGroup>${plugin.groupId}</pluginGroup>
    </pluginGroups>
</settings>
```

See also [Introduction to Plugin Prefix Resolution](http://maven.apache.org/guides/introduction/introduction-to-plugin-prefix-mapping.html)""")
      }

      doc.endChapter()

      doc.startChapter("Direct Invocation")

      doc.mk("""\
To directly invoke a goal of this plugin, use the follwoing commands. For the list of possible goals, see the list of goals below.

```
mvn ${plugin.groupId}:${plugin.artifactId}:${plugin.version}:<goal> [<propertie(s)>]

mvn ${plugin.goalPrefix}:<goal> [<propertie(s)>]
```

The second command shows the invocation via this plugins prefix: `$plugin.goalPrefix`. For more details see [Introduction to Plugin Prefix Resolution](http://maven.apache.org/guides/introduction/introduction-to-plugin-prefix-mapping.html).

""")

      doc.endChapter()
      doc.startChapter("Invocation via Maven Build")
      doc.code("""\
<project>
    <build>
        <!-- Use the plugin in your POM or parent POM. -->
        <plugins>
            <plugin>
                <groupId>${plugin.groupId}</groupId>
                <artifactId>${plugin.artifactId}</artifactId>
                <version>${plugin.version}</version>
                <!-- Add an execution element for each goal you want to execute. -->
                <executions />
            </plugin>
        </plugins>
    </build>
</project>""").language = "xml"
      doc.endChapter()

      doc.startChapter("Goals")
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

   void addGoal(DocumentBuilder doc, MojoDescriptor mojo)
   {
      doc.startListItem()
      doc.startParagraph()
      doc.startEmphasis(EmphasisType.BOLD)
      doc.text(mojo.goal)
      doc.endEmphasis()
      doc.endParagraph()

      def descr = mojo.description
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

      def usage = "<execution>"

      def phase = mojo.phase ;
      if (phase)
      {
         usage <<= """
    <!-- Bind the goal to a specific Maven phase, default is "${phase}". -->
    <phase />"""
      } else
      {
         usage <<= """
    <!-- Bind the goal to a specific Maven phase. -->
    <phase />"""
      }

      usage <<= """
    <goals>
        <goal>${mojo.goal}<goal>
    </goals>"""

      usage <<= "\n    <configuration>"

      mojo.parameters.each
      { param ->
         usage <<= "\n        <!-- "

         if (param.description)
         {
            usage <<= "\n            "
            usage <<= HTML.toMarkdown(param.description)
            usage <<= "\n            "
         }

         if (param.expression)
         {
            usage <<= "\n            Expression: " + param.expression
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

         usage <<= "\n            -->"
         usage <<= "\n        <" + param.name +  " />"
      }
      usage <<= "\n    </configuration>"
      usage <<= "\n</execution>"

      def code = doc.code(usage.toString())
      code.language = "xml"

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
