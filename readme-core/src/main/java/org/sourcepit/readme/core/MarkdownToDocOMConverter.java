/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.readme.core;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.pegdown.Extensions.FENCED_CODE_BLOCKS;
import static org.pegdown.Extensions.STRIKETHROUGH;
import static org.pegdown.Extensions.TABLES;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import org.pegdown.ast.TableColumnNode.Alignment;
import org.pegdown.ast.TableHeaderNode;
import org.pegdown.ast.TableNode;
import org.pegdown.ast.TableRowNode;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.VerbatimNode;
import org.pegdown.ast.Visitor;
import org.pegdown.ast.WikiLinkNode;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Code;
import org.sourcepit.docom.CodeLiteral;
import org.sourcepit.docom.Declaration;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.HorizontalLine;
import org.sourcepit.docom.Link;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Quote;
import org.sourcepit.docom.Reference;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Table;
import org.sourcepit.docom.TableCell;
import org.sourcepit.docom.TableRow;
import org.sourcepit.docom.Text;

import com.google.common.base.Strings;

public class MarkdownToDocOMConverter
{
   public Document toDocOM(String markdown)
   {
      // Github style
      // Extensions.HARDWRAPS; (?)
      // WIKILINKS;STRIKETHROUGH;FENCED_CODE_BLOCKS

      int options = 0;
      // options = options | Extensions.AUTOLINKS;
      // options = options | Extensions.WIKILINKS;
      options |= TABLES;
      // FENCED_CODE_BLOCKS is required for STRIKETHROUGH. See bug https://github.com/sirthias/pegdown/issues/131.
      options |= STRIKETHROUGH | FENCED_CODE_BLOCKS;

      final RootNode md = new PegDownProcessor(options).parseMarkdown(markdown.toCharArray());
      final DocOMBuilder builder = new DocOMBuilder(DocOMFactory.eINSTANCE);
      md.accept(builder);
      return builder.getDocument();
   }

   public boolean ext(int extension, int options)
   {
      return (options & extension) > 0;
   }

   private static class DocOMBuilder implements Visitor
   {
      private final DocOMFactory factory;

      private Document document;

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
         final int parentsSize = parents.size();

         for (Node child : node.getChildren())
         {
            child.accept(this);
         }

         if (parents.size() > parentsSize) // fix dangling chapter
         {
            final Chapter chapter = (Chapter) parents.peek();
            pop(parents, chapter);
         }
      }

      @Override
      public void visit(RootNode node)
      {
         if (document == null)
         {
            document = factory.createDocument();
            parents.push(document);
            visitChildren(node);
            pop(parents, document);
         }
         else
         {
            visitChildren(node);
         }
      }


      private static <T> void pop(Stack<T> stack, T object)
      {
         if (stack.pop() != object)
         {
            throw new IllegalStateException();
         }
      }

      @Override
      public void visit(ParaNode node)
      {
         final CodeNode codeNode = getFencedCodeNode(node);
         if (codeNode == null)
         {
            final Paragraph paragraph = factory.createParagraph();

            final Object parent = parents.peek();

            if (parent instanceof ListItem)
            {
               ((ListItem) parent).getContent().add(paragraph);
            }
            else
            {
               ((Structured) parent).getContent().add(paragraph);
            }

            parents.push(paragraph);
            visitChildren(node);
            pop(parents, paragraph);
         }
         else
         {
            visitFencedCodeBlock(codeNode);
         }
      }

      private CodeNode getFencedCodeNode(ParaNode node)
      {
         CodeNode codeNode = null;
         if (node.getChildren().size() == 1)
         {
            Node child = node.getChildren().get(0);
            if (child instanceof SuperNode)
            {
               if (child.getChildren().size() == 1)
               {
                  child = child.getChildren().get(0);
                  if (child instanceof CodeNode)
                  {
                     codeNode = (CodeNode) child;
                  }
               }
            }
         }
         if (codeNode != null && codeNode.getText().contains("\n"))
         {
            return codeNode;
         }
         return null;
      }

      private void visitFencedCodeBlock(CodeNode node)
      {
         String code = node.getText();

         int idx = code.indexOf('\n');

         final String language = code.substring(0, idx);

         code = code.substring(idx + 1, code.length());

         checkState(node.getChildren().isEmpty());

         final Code codeBlock = factory.createCode();
         codeBlock.setText(code);

         if (!isNullOrEmpty(language))
         {
            codeBlock.setLanguage(language);
         }

         final Object parent = parents.peek();
         if (parent instanceof Structured)
         {
            ((Structured) parent).getContent().add(codeBlock);
         }
         else
         {
            ((ListItem) parent).getContent().add(codeBlock);
         }
      }

      @SuppressWarnings({ "unchecked", "rawtypes" })
      @Override
      public void visit(TextNode node)
      {
         final Object parent = parents.peek();

         java.util.List<EObject> siblings;
         if (parent instanceof LiteralGroup)
         {
            siblings = (java.util.List) ((LiteralGroup) parent).getLiterals();
         }
         else
         {
            siblings = (java.util.List) ((ListItem) parent).getContent();
         }

         final EObject previous = siblings.isEmpty() ? null : siblings.get(siblings.size() - 1);
         if (previous instanceof Text)
         {
            Text text = (Text) previous;
            text.setText(text.getText() + node.getText());
         }
         else
         {
            final Text text = factory.createText();
            text.setText(node.getText());
            siblings.add(text);
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
         checkState(node.getChildren().isEmpty());

         final Link link = factory.createLink();
         link.setUrl(node.getText());

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(link);
         }
         else
         {
            ((ListItem) parent).getContent().add(link);
         }
      }

      @Override
      public void visit(BlockQuoteNode node)
      {
         final Structured parent = (Structured) parents.peek();

         final Quote quote = factory.createQuote();
         parent.getContent().add(quote);

         parents.push(quote);
         visitChildren(node);
         pop(parents, quote);
      }

      @Override
      public void visit(CodeNode node)
      {
         checkState(node.getChildren().isEmpty());

         final CodeLiteral code = factory.createCodeLiteral();
         code.setText(node.getText());

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(code);
         }
         else
         {
            ((ListItem) parent).getContent().add(code);
         }
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
         final Link link = factory.createLink();
         link.setUrl(Strings.isNullOrEmpty(node.url) ? null : node.url);
         link.setTitle(Strings.isNullOrEmpty(node.title) ? null : node.title);

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(link);
         }
         else
         {
            ((ListItem) parent).getContent().add(link);
         }

         parents.push(link);
         visitChildren(node);
         pop(parents, link);
      }

      private int level = 0;

      @Override
      public void visit(HeaderNode node)
      {
         Structured parent = (Structured) parents.peek();
         while (level >= node.getLevel())
         {
            pop(parents, parent);
            level--;

            parent = (Structured) parents.peek();
         }

         final Header header = factory.createHeader();

         final Chapter chapter = factory.createChapter();
         chapter.setHeader(header);

         parent.getContent().add(chapter);

         parents.push(chapter); // dangling chapters will are poped in visitChildren
         level++;

         parents.push(header);
         visitChildren(node);
         pop(parents, header);
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
         checkState(node.getChildren().isEmpty());

         final Link link = factory.createLink();
         link.setUrl("mailto:" + node.getText());

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(link);
         }
         else
         {
            ((ListItem) parent).getContent().add(link);
         }
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
         final Object parent = parents.peek();
         if (parent instanceof ListItem)
         {
            ((ListItem) parent).getContent().add(list);
         }
         else
         {
            ((Structured) parent).getContent().add(list);
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
         final Declaration declaration = factory.createDeclaration();

         final TextNode textNode = (TextNode) node.getChildren().get(0).getChildren().get(0);
         final String id = textNode.getText();
         if (!isNullOrEmpty(id))
         {
            declaration.setId(id);
         }

         final String url = node.getUrl();
         if (!isNullOrEmpty(url))
         {
            declaration.setUrl(url);
         }

         final String title = node.getTitle();
         if (!isNullOrEmpty(title))
         {
            declaration.setTitle(title);
         }

         final Object parent = parents.peek();
         if (parent instanceof ListItem)
         {
            ((ListItem) parent).getContent().add(declaration);
         }
         else
         {
            ((Structured) parent).getContent().add(declaration);
         }
      }

      @Override
      public void visit(RefImageNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(RefLinkNode node)
      {
         final Reference reference = factory.createReference();

         final TextNode textNode = (TextNode) node.referenceKey.getChildren().get(0);
         reference.setId(textNode.getText());

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(reference);
         }
         else
         {
            ((ListItem) parent).getContent().add(reference);
         }

         parents.push(reference);
         visitChildren(node);
         pop(parents, reference);

      }

      @Override
      public void visit(SimpleNode node)
      {
         switch (node.getType())
         {
            case HRule :
               visitHorizontalLine(node);
               break;
            case Linebreak :
               visitNewLine(node);
               break;
            default :
               throw new UnsupportedOperationException();
         }
      }

      private void visitHorizontalLine(SimpleNode node)
      {
         checkState(node.getChildren().isEmpty());
         final HorizontalLine hl = factory.createHorizontalLine();
         final Object parent = parents.peek();
         if (parent instanceof ListItem)
         {
            ((ListItem) parent).getContent().add(hl);
         }
         else
         {
            ((Structured) parent).getContent().add(hl);
         }
      }

      private void visitNewLine(SimpleNode node)
      {
         checkState(node.getChildren().isEmpty());
         final NewLine nl = factory.createNewLine();
         final Object parent = parents.peek();
         ((LiteralGroup) parent).getLiterals().add(nl);
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
         processEmphasis(node, EmphasisType.STRIKETHROUGH);
      }

      @Override
      public void visit(StrongEmphSuperNode node)
      {
         final EmphasisType type;
         if (node.isStrong())
         {
            type = EmphasisType.BOLD;
         }
         else
         {
            type = EmphasisType.ITALIC;
         }
         processEmphasis(node, type);
      }

      private void processEmphasis(Node node, final EmphasisType type)
      {
         final Emphasis em = factory.createEmphasis();
         em.setType(type);

         final Object parent = parents.peek();
         if (parent instanceof LiteralGroup)
         {
            ((LiteralGroup) parent).getLiterals().add(em);
         }
         else
         {
            ((ListItem) parent).getContent().add(em);
         }

         parents.add(em);
         visitChildren(node);
         pop(parents, em);
      }

      @Override
      public void visit(TableCaptionNode node)
      {
         throw new UnsupportedOperationException();

      }

      @Override
      public void visit(TableNode node)
      {
         final Table table = factory.createTable();

         final Structured parent = (Structured) parents.peek();
         parent.getContent().add(table);

         parents.add(table);

         for (TableColumnNode columnNode : node.getColumns())
         {
            columnNode.accept(this);
         }

         visitChildren(node);


         pop(parents, table);
      }

      @Override
      public void visit(TableHeaderNode node)
      {
         visitChildren(node);
      }

      @Override
      public void visit(TableColumnNode node)
      {
         checkState(node.getChildren().isEmpty());

         final Alignment alignment = node.getAlignment();

         final org.sourcepit.docom.Alignment align;
         switch (alignment)
         {
            case None :
            case Left :
               align = org.sourcepit.docom.Alignment.LEFT;
               break;
            case Center :
               align = org.sourcepit.docom.Alignment.CENTER;
               break;
            case Right :
               align = org.sourcepit.docom.Alignment.RIGHT;
               break;
            default :
               throw new IllegalStateException();
         }

         final Table table = (Table) parents.peek();
         table.getColumnDefinitions().add(align);
      }

      @Override
      public void visit(TableBodyNode node)
      {
         final Table table = (Table) parents.peek();

         final EList<TableRow> body = table.getBody();
         parents.add(body);
         visitChildren(node);
         pop(parents, body);
      }

      @Override
      @SuppressWarnings({ "rawtypes", "unchecked" })
      public void visit(TableRowNode node)
      {
         final TableRow row = factory.createTableRow();

         final Object parent = parents.peek();
         if (parent instanceof java.util.List)
         {
            ((java.util.List) parent).add(row);
         }
         else
         {
            ((Table) parent).setHeader(row);
         }

         parents.add(row);
         visitChildren(node);
         pop(parents, row);
      }

      @Override
      public void visit(TableCellNode node)
      {
         final TableCell cell = factory.createTableCell();
         cell.setColumnSpan(node.getColSpan());

         final TableRow parent = (TableRow) parents.peek();
         parent.getCells().add(cell);

         parents.add(cell);
         visitChildren(node);
         pop(parents, cell);
      }

      @Override
      public void visit(VerbatimNode node)
      {
         checkState(node.getChildren().isEmpty());

         final char[] text = node.getText().toCharArray();

         int length = text.length;
         for (int i = text.length - 1; i >= 0; i--)
         {
            char c = text[i];
            if (c == '\n' || c == '\r')
            {
               length--;
            }
            else
            {
               break;
            }
         }

         final Code code = factory.createCode();
         code.setText(String.valueOf(text, 0, length));

         final String lang = node.getType();
         if (!Strings.isNullOrEmpty(lang))
         {
            code.setLanguage(lang);
         }

         final Object parent = parents.peek();
         if (parent instanceof Structured)
         {
            ((Structured) parent).getContent().add(code);
         }
         else
         {
            ((ListItem) parent).getContent().add(code);
         }
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
