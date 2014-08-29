import static org.sourcepit.readme.maven.MavenUtils.*;

import java.lang.annotation.Documented;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory
import org.sourcepit.docom.Document
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

   if (session.projects.size() == 1)
   {
      if (isMavenPlugin(project))
      {
         addPluginGAV(doc,readPluginDescriptor(project))
      }
      else
      {
         addProjectGAV(doc,readPluginDescriptor(project))
      }
   }

   doc.endChapter();
}

void addPlugins(DocumentBuilder doc, HTMLToDocOM html, MavenSession session)
{
   List<MavenProject> projects = session.projects

   List<PluginDescriptor> plugins = new ArrayList<PluginDescriptor>()
   projects.each
   { project ->
      if (isMavenPlugin( project))
      {
         plugins << readPluginDescriptor(project)
      }
   }

   if (projects.size() == 1)
   {
      doc.startChapter("Goals")
      addGoals(doc, html, session, plugins);
      doc.endChapter()
   } else
   {

      if (plugins.size() == 1)
      {
         doc.startChapter("Maven Plugin")
         def plugin = plugins.get(0);
         addPluginGAV(doc, plugin)
         addGoals(doc, html, session, plugin)
         doc.endChapter()
      }
      else
      {
         plugins.each
         {plugin ->
            doc.startChapter("Maven Plugins")
            doc.startChapter(plugin.name)
            addPluginGAV(doc, plugin)
            addGoals(doc, html, session, plugin)
            doc.endChapter()
            doc.endChapter()
         }
      }
   }
}

void addPluginGAV(DocumentBuilder doc, PluginDescriptor plugin)
{
   def gav = "<plugin>"
   gav <<= "\n    <groupId>" + plugin.groupId + "<groupId>"
   gav <<= "\n    <artifactId>" + plugin.artifactId + "<artifactId>"
   gav <<= "\n    <version>" + plugin.version + "<version>"
   gav <<= "\n</plugin>"

   def code = doc.code(gav.toString())
   code.language = "xml"
}

void addProjectGAV(DocumentBuilder doc, MavenProject project)
{
   def gav = "<dependency>"
   gav <<= "\n    <groupId>" + project.groupId + "<groupId>"
   gav <<= "\n    <artifactId>" + project.artifactId + "<artifactId>"
   gav <<= "\n    <version>" + project.version + "<version>"
   gav <<= "\n    <type>" + project.artifact.type + "<type>"
   gav <<= "\n</dependency>"

   def code = doc.code(gav.toString())
   code.language = "xml"
}

void addGoals(DocumentBuilder doc, HTMLToDocOM html, MavenSession session, PluginDescriptor plugin)
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
   doc.paragraph(mojo.goal)

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
      usage <<= "\n    <phase>"
      usage <<= phase
      usage <<= "</phase> (default)"
   }

   usage <<= "\n    <goals>\n        <goal>"
   usage <<= mojo.goal
   usage <<= "<goal>\n    <goal>"

   usage <<= "\n    <configuration>"
   mojo.parameters.each
   { param ->
      usage <<= "\n        <!-- "

      if (param.description)
      {
         usage <<= "\n            "
         usage <<= html.toMarkdown(param.description)
      }

      if (param.expression)
      {
         usage <<= "\n            *   expression: " + param.expression
      }

      if (param.defaultValue)
      {
         usage <<= "\n            *   defaultValue: " + param.defaultValue
      }

      if (param.required)
      {
         usage <<= "\n            *   required: " + param.required
      }

      if (param.deprecated)
      {
         usage <<= "\n            *   deprecated: " + param.deprecated
      }

      if (param.since)
      {
         usage <<= "\n            *   since: " + param.since
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

def session = (MavenSession) mavenSession
def DocumentBuilder doc = documentBuilder
def HTMLToDocOM html = htmlToDocOM

doc.startDocument()
addOverview(doc, session)
addPlugins(doc, html, session)
return doc.endDocument()
