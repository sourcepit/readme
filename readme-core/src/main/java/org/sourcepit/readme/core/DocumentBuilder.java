/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Code;
import org.sourcepit.docom.CodeLiteral;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.Link;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.Listable;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;

public class DocumentBuilder
{
   private final Stack<EObject> stack = new Stack<EObject>();

   private final DocOMFactory eFactory = DocOMFactory.eINSTANCE;

   public Document startDocument()
   {
      checkState(stack.isEmpty());
      final Document doc = eFactory.createDocument();
      stack.push(doc);
      return doc;
   }

   public Document endDocument()
   {
      checkState(stack.size() == 1);

      return (Document) stack.pop();
   }

   public Chapter startChapter(String header)
   {
      checkState(stack.peek() instanceof Structured);

      final Chapter chapter = eFactory.createChapter();
      stack.push(chapter);

      chapter.setHeader(eFactory.createHeader());
      stack.push(chapter.getHeader());
      text(header);
      stack.pop();

      return chapter;
   }

   public Chapter endChapter()
   {
      final Chapter chapter = (Chapter) stack.pop();
      ((Structured) stack.peek()).getContent().add(chapter);
      return chapter;
   }

   public List startUnorderedList()
   {
      return startList(ListType.UNORDERED);
   }

   public List startOrderedList()
   {
      return startList(ListType.ORDERED);
   }

   public List startList(ListType type)
   {
      checkArgument(type != null);

      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);

      final List list = eFactory.createList();
      list.setType(type);
      stack.push(list);

      return list;
   }

   public ListItem startListItem()
   {
      checkState(stack.peek() instanceof List);
      final ListItem li = eFactory.createListItem();
      stack.push(li);
      return li;
   }

   public ListItem endListItem()
   {
      final ListItem li = (ListItem) stack.pop();
      ((List) stack.peek()).getItems().add(li);
      return li;
   }

   public Paragraph startParagraph()
   {
      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);
      final Paragraph paragraph = eFactory.createParagraph();
      stack.push(paragraph);
      return paragraph;
   }

   public Paragraph endParagraph()
   {
      final Paragraph paragraph = (Paragraph) stack.pop();

      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);

      if (parent instanceof Structured)
      {
         ((Structured) parent).getContent().add(paragraph);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(paragraph);
      }

      return paragraph;
   }

   public List endList()
   {
      final List list = (List) stack.pop();

      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);

      if (parent instanceof Structured)
      {
         ((Structured) parent).getContent().add(list);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(list);
      }

      return list;
   }

   public Paragraph paragraph(String text)
   {
      startParagraph();
      text(text);
      return endParagraph();
   }

   public Text text(String text)
   {
      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      final Text t = eFactory.createText();
      t.setText(text);

      if (parent instanceof LiteralGroup)
      {
         ((LiteralGroup) parent).getLiterals().add(t);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(t);
      }

      return t;
   }

   public void mk(String markdown)
   {
      final Document document = new MarkdownToDocOMConverter().toDocOM(markdown);

      final java.util.List<Structurable> content = new ArrayList<Structurable>(document.getContent());

      if (content.size() == 1)
      {
         final Structurable structurable = content.get(0);

         final EObject parent = stack.peek();
         if (parent instanceof Structured)
         {
            ((Structured) parent).getContent().add(structurable);
            return;
         }

         if (structurable instanceof Paragraph)
         {
            final Paragraph p = (Paragraph) structurable;

            final EList<Literal> literals = p.getLiterals();

            checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

            if (parent instanceof LiteralGroup)
            {
               ((LiteralGroup) parent).getLiterals().addAll(literals);
            }
            else if (parent instanceof ListItem)
            {
               ((ListItem) parent).getContent().addAll(literals);
            }

            return;
         }
      }
      
      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);

      for (Structurable structurable : content)
      {
         if (parent instanceof Structured)
         {
            ((Structured) parent).getContent().add(structurable);
         }
         else if (parent instanceof ListItem)
         {
            ((ListItem) parent).getContent().add((Listable) structurable);
         }
      }
   }

   public Link link(String text, String url)
   {
      return link(text, url, null);
   }

   public Link startLink(String url)
   {
      return startLink(url, null);
   }

   public Link startLink(String url, String title)
   {
      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      final Link link = eFactory.createLink();
      link.setUrl(url);
      link.setTitle(title);

      stack.push(link);

      return link;
   }

   public Link endLink()
   {
      Link link = (Link) stack.pop();

      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      if (parent instanceof LiteralGroup)
      {
         ((LiteralGroup) parent).getLiterals().add(link);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(link);
      }

      return link;
   }

   public Link link(String text, String url, String title)
   {
      startLink(url, title);
      text(text);
      return endLink();
   }

   public CodeLiteral codeLiteral(String code)
   {
      final CodeLiteral c = eFactory.createCodeLiteral();
      c.setText(code);

      final LiteralGroup parent = (LiteralGroup) stack.peek();
      parent.getLiterals().add(c);

      return c;
   }

   public NewLine newLine()
   {
      final NewLine newLine = eFactory.createNewLine();

      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      if (parent instanceof LiteralGroup)
      {
         ((LiteralGroup) parent).getLiterals().add(newLine);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(newLine);
      }

      return newLine;
   }

   public Code code(String code)
   {
      final Code c = eFactory.createCode();
      c.setText(code);

      final EObject parent = stack.peek();
      checkState(parent instanceof Structured || parent instanceof ListItem);

      if (parent instanceof Structured)
      {
         ((Structured) parent).getContent().add(c);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(c);
      }

      return c;
   }

   public Emphasis startEmphasis(EmphasisType type)
   {
      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      Emphasis em = eFactory.createEmphasis();
      em.setType(type);

      stack.push(em);

      return em;
   }

   public Emphasis endEmphasis()
   {
      Emphasis em = (Emphasis) stack.pop();

      final EObject parent = stack.peek();
      checkState(parent instanceof LiteralGroup || parent instanceof ListItem);

      if (parent instanceof LiteralGroup)
      {
         ((LiteralGroup) parent).getLiterals().add(em);
      }
      else if (parent instanceof ListItem)
      {
         ((ListItem) parent).getContent().add(em);
      }

      return em;
   }
}
