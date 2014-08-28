import static org.sourcepit.readme.maven.DocOMUtils.*;
import static org.sourcepit.readme.maven.MavenUtils.*;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMFactory
import org.sourcepit.docom.Document
import org.sourcepit.docom.Paragraph;

static addOverview(Document doc, MavenProject project)
{
   def chapter = addChapter(doc, project.name)
   def desc = project.description;
   if (desc)
   {
      def p = addParagraph(chapter);
      addText(p, project.description)
   }
}

static addGoals(Document doc, MavenSession session)
{
   def chapter = addChapter(doc, "Goals")
   def list = addList(chapter)

   session.getProjects().each
   { project ->
      if (isMavenPlugin( project))
      {
         def decr = readPluginDescriptor(project)
         decr.mojos.each
         { mojo ->

            def li = addListItem(list)

            def p = addParagraph(li)
            addText(p, mojo.goal)

            p = addParagraph(li)
            addText(p, mojo.description)
         }
      }
   }
}

def session = (MavenSession) mavenSession

def doc = createDocument()
addOverview(doc, session.getCurrentProject())
addGoals(doc, session);
return doc;
