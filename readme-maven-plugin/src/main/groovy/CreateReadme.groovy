import static org.sourcepit.readme.maven.DocOMUtils.*;
import static org.sourcepit.readme.maven.MavenUtils.*;

import java.lang.annotation.Documented;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory
import org.sourcepit.docom.Document
import org.sourcepit.docom.Paragraph;
import org.sourcepit.readme.core.DocumentBuilder;

void addOverview(DocumentBuilder doc, MavenProject project)
{
   doc.startChapter(project.name)
   def desc = project.description;
   if (desc)
   {
      doc.paragraph(desc);
   }
   doc.endChapter();
}

void addGoals(DocumentBuilder doc, MavenSession session)
{
   doc.startChapter("Goals")
   doc.startUnorderedList()

   session.getProjects().each
   { project ->
      if (isMavenPlugin( project))
      {
         def decr = readPluginDescriptor(project)
         decr.mojos.each
         { mojo ->

            doc.startListItem()
            doc.paragraph(mojo.goal)
            doc.paragraph(mojo.description)
            doc.endListItem()
         }
      }
   }

   doc.endList()
   doc.endChapter()
}

def session = (MavenSession) mavenSession
def DocumentBuilder doc = documentBuilder

doc.startDocument()
addOverview(doc, session.getCurrentProject())
addGoals(doc, session);
return doc.endDocument()
