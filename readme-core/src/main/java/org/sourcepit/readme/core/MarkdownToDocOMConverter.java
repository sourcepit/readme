/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import java.util.Stack;

import org.pegdown.PegDownProcessor;
import org.pegdown.ast.AbbreviationNode;
import org.pegdown.ast.AutoLinkNode;
import org.pegdown.ast.BlockQuoteNode;
import org.pegdown.ast.BulletListNode;
import org.pegdown.ast.CodeNode;
import org.pegdown.ast.DefinitionListNode;
import org.pegdown.ast.DefinitionNode;
import org.pegdown.ast.DefinitionTermNode;
import org.pegdown.ast.ExpImageNode;
import org.pegdown.ast.ExpLinkNode;
import org.pegdown.ast.HeaderNode;
import org.pegdown.ast.HtmlBlockNode;
import org.pegdown.ast.InlineHtmlNode;
import org.pegdown.ast.ListItemNode;
import org.pegdown.ast.MailLinkNode;
import org.pegdown.ast.Node;
import org.pegdown.ast.OrderedListNode;
import org.pegdown.ast.ParaNode;
import org.pegdown.ast.QuotedNode;
import org.pegdown.ast.RefImageNode;
import org.pegdown.ast.RefLinkNode;
import org.pegdown.ast.ReferenceNode;
import org.pegdown.ast.RootNode;
import org.pegdown.ast.SimpleNode;
import org.pegdown.ast.SpecialTextNode;
import org.pegdown.ast.StrikeNode;
import org.pegdown.ast.StrongEmphSuperNode;
import org.pegdown.ast.SuperNode;
import org.pegdown.ast.TableBodyNode;
import org.pegdown.ast.TableCaptionNode;
import org.pegdown.ast.TableCellNode;
import org.pegdown.ast.TableColumnNode;
import org.pegdown.ast.TableHeaderNode;
import org.pegdown.ast.TableNode;
import org.pegdown.ast.TableRowNode;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.VerbatimNode;
import org.pegdown.ast.Visitor;
import org.pegdown.ast.WikiLinkNode;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;

public class MarkdownToDocOMConverter
{
   public Document toDocOM(String markdown)
   {
      final RootNode md = new PegDownProcessor().parseMarkdown(markdown.toCharArray());
      final DocOMBuilder builder = new DocOMBuilder(DocOMFactory.eINSTANCE);
      md.accept(builder);
      return builder.getDocument();
   }

   private static class DocOMBuilder implements Visitor
   {
      private final DocOMFactory factory;

      private Document document;

      private final Stack<Structured> structure = new Stack<Structured>();

      private final Stack<Object> parents = new Stack<Object>();

      public DocOMBuilder(DocOMFactory factory)
      {
         this.factory = factory;
      }

      public Document getDocument()
      {
         return document;
      }

      private void visitChildren(Node node)
      {
         for (Node child : node.getChildren())
         {
            child.accept(this);
         }
      }

      @Override
      public void visit(RootNode node)
      {
         if (document == null)
         {
            document = factory.createDocument();
            structure.push(document);
            visitChildren(node);
            pop(structure, document);
         }
         else
         {
            visitChildren(node);
         }
      }


      private static <T> void pop(Stack<T> stack, T structured)
      {
         if (stack.pop() != structured)
         {
            throw new IllegalStateException();
         }
      }

      @Override
      public void visit(ParaNode node)
      {
         final Paragraph paragraph = factory.createParagraph();

         if (!parents.isEmpty())
         {
            final ListItem listItem = (ListItem) parents.peek();
            listItem.getContent().add(paragraph);
         }
         else
         {
            final Structured parent = structure.peek();
            parent.getContent().add(paragraph);
         }

         parents.push(paragraph);
         visitChildren(node);
         pop(parents, paragraph);
      }

      @Override
      public void visit(TextNode node)
      {
         final Text text = factory.createText();
         text.setText(node.getText());

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(text);
         }
         else
         {
            ((ListItem) parent).getContent().add(text);
         }

         if (!node.getChildren().isEmpty())
         {
            throw new IllegalStateException();
         }
      }

      @Override
      public void visit(AbbreviationNode node)
      {
         throw new UnsupportedOperationException();
      }

      @Override
      public void visit(AutoLinkNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(BlockQuoteNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(CodeNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(DefinitionListNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(DefinitionNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(DefinitionTermNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(ExpImageNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(ExpLinkNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(HeaderNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(HtmlBlockNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(InlineHtmlNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(ListItemNode node)
      {
         final ListItem listItem = factory.createListItem();

         ((List) parents.peek()).getItems().add(listItem);

         parents.push(listItem);
         visitChildren(node);
         pop(parents, listItem);
      }

      @Override
      public void visit(MailLinkNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(BulletListNode node)
      {
         final List list = factory.createList();
         list.setType(ListType.UNORDERED);
         processListNode(node, list);
      }

      @Override
      public void visit(OrderedListNode node)
      {
         final List list = factory.createList();
         list.setType(ListType.ORDERED);
         processListNode(node, list);
      }

      private void processListNode(Node node, final List list)
      {
         if (!parents.isEmpty())
         {
            final ListItem parent = (ListItem) parents.peek();
            parent.getContent().add(list);
         }
         else
         {
            final Structured parent = structure.peek();
            parent.getContent().add(list);
         }
      
         parents.push(list);
         visitChildren(node);
         pop(parents, list);
      }

      @Override
      public void visit(QuotedNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(ReferenceNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(RefImageNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(RefLinkNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(SimpleNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(SpecialTextNode node)
      {
         final Object sibling = getSibling();

         if (sibling instanceof Text)
         {
            final Text text = (Text) sibling;
            text.setText(text.getText() + node.getText());

            if (!node.getChildren().isEmpty())
            {
               throw new IllegalStateException();
            }

         }
         else
         {
            visit((TextNode) node);
         }
      }

      private Object getSibling()
      {
         final Object parent = parents.peek();

         final java.util.List<?> siblings;
         if (parent instanceof LiteralGroup)
         {
            siblings = ((LiteralGroup) parent).getLiterals();
         }
         else
         {
            siblings = ((ListItem) parent).getContent();
         }

         final int size = siblings.size();
         return size > 0 ? siblings.get(size - 1) : null;
      }

      @Override
      public void visit(StrikeNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(StrongEmphSuperNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableBodyNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableCaptionNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableCellNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableColumnNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableHeaderNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableRowNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(VerbatimNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(WikiLinkNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(SuperNode node)
      {
         visitChildren(node);
      }

      @Override
      public void visit(Node node)
      {
         throw new UnsupportedOperationException();

      }

   }
}
