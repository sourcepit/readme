import static org.sourcepit.readme.maven.MavenUtils.*;
import groovy.transform.EqualsAndHashCode;

import java.lang.annotation.Documented;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.maven.execution.MavenSession;
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

void addOverview(DocumentBuilder doc, MavenSession session)
{
   def project = session.currentProject;

   doc.startChapter(project.name)
   def desc = project.description;
   if (desc)
   {
      doc.paragraph(desc);
   }
}


void addPlugins(DocumentBuilder doc, HTMLToDocOM html, MavenSession session)
{
   def projects = session.projects

   def plugins = new LinkedHashMap<MavenProject, PluginDescriptor>()
   projects.each
   { project ->
      if (isMavenPlugin( project))
      {
         plugins[project] = readPluginDescriptor(project)
      }
   }

   doc.startChapter("Maven Plugins")

   plugins.each
   { project, plugin ->
      doc.startChapter(plugin.name)
      addPluginBody(doc, html, project, plugin);
      doc.endChapter()
   }

   doc.endChapter()
}

void addPluginBody(DocumentBuilder doc, HTMLToDocOM html, MavenProject project, PluginDescriptor plugin)
{
   def desc = plugin.description;
   if (desc)
   {
      if (!project.parent || !desc.equals(project.parent.description))
      {
         doc.paragraph(desc);
      }
   }

   doc.startChapter("Usage")
   doc.code("""\
<project>
    <build>
        <!-- Define the plugin version in your POM or parent POM. -->
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>${plugin.groupId}</groupId>
                    <artifactId>${plugin.artifactId}</artifactId>
                    <version>${plugin.version}</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <!-- Use the plugin goals in your POM or parent POM. -->
        <plugins>
            <plugin>
                <groupId>${plugin.groupId}</groupId>
                <artifactId>${plugin.artifactId}</artifactId>
                <!-- Add an execution element for each goal you want to execute. -->
                <executions />
            </plugin>
        </plugins>
    </build>
</project>""").language = "xml"
   doc.endChapter()

   doc.startChapter("Goals")
   addGoals(doc, html, plugin)
   doc.endChapter()
}

void addLibraries(DocumentBuilder doc, MavenSession session)
{
   List<MavenProject> projects = session.projects

   def libs = new ArrayList<MavenProject>()
   projects.each
   { project ->
      if (!isMavenPlugin( project) && !isPomProject( project))
      {
         libs << project
      }
   }

   doc.startChapter("Libraries")

   libs.each
   { lib ->
      doc.startChapter(lib.name)
      addLibraryBody(doc, lib)
      doc.endChapter()
   }

   doc.endChapter()
}

void addLibraryBody(DocumentBuilder doc, MavenProject project)
{
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
    <!-- Define the dependency version in your POM or parent POM. -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>${project.groupId}</groupId>
                <artifactId>${project.artifactId}</artifactId>
                <version>${project.version}</version>${appendType}
            </dependency>
        </dependencies>
    </dependencyManagement>
    <!-- Use the dependency in your POM or parent POM. -->
    <dependencies>
        <dependency>
            <groupId>${project.groupId}</groupId>
            <artifactId>${project.artifactId}</artifactId>
        </dependency>
    </dependencies>
</project>""").language = "xml"
}

void addGoals(DocumentBuilder doc, HTMLToDocOM html, PluginDescriptor plugin)
{
   doc.startUnorderedList()
   plugin.mojos.each
   { mojo ->
      addGoal(doc, html, mojo)
   }
   doc.endList()
}

void addGoal(DocumentBuilder doc, HTMLToDocOM html, MojoDescriptor mojo)
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
      def result = html.convert(descr, Literal.class, LiteralGroup.class);
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
         usage <<= html.toMarkdown(param.description)
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

def session = (MavenSession) mavenSession
def DocumentBuilder doc = documentBuilder
def HTMLToDocOM html = htmlToDocOM

doc.startDocument()

if (session.projects == 1)
{
   def project = session.currentProject

   if (isMavenPlugin(project))
   {
      def plugin = readPluginDescriptor(project)
      doc.startChapter(plugin.name)
      addPluginBody(doc, html, project, plugin)
   }
   else
   {
      doc.startChapter(project.name)
      addLibraryBody(doc, project);
   }

   addLicenses(doc, session)
   doc.endChapter();
}
else
{
   addOverview(doc, session)
   addPlugins(doc, html, session)
   addLibraries(doc, session);
   addLicenses(doc, session)
   doc.endChapter();
}
return doc.endDocument()
