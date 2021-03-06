/**
 */

package org.sourcepit.docom.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.docom.Alignment;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Code;
import org.sourcepit.docom.CodeLiteral;
import org.sourcepit.docom.Declaration;
import org.sourcepit.docom.DocOMFactory;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.EmphasisType;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.HorizontalLine;
import org.sourcepit.docom.Link;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.ListType;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Quote;
import org.sourcepit.docom.Reference;
import org.sourcepit.docom.Table;
import org.sourcepit.docom.TableCell;
import org.sourcepit.docom.TableRow;
import org.sourcepit.docom.Text;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class DocOMFactoryImpl extends EFactoryImpl implements DocOMFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static DocOMFactory init()
   {
      try
      {
         DocOMFactory theDocOMFactory = (DocOMFactory) EPackage.Registry.INSTANCE.getEFactory(DocOMPackage.eNS_URI);
         if (theDocOMFactory != null)
         {
            return theDocOMFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new DocOMFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DocOMFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case DocOMPackage.PARAGRAPH :
            return createParagraph();
         case DocOMPackage.EMPHASIS :
            return createEmphasis();
         case DocOMPackage.LIST :
            return createList();
         case DocOMPackage.TEXT :
            return createText();
         case DocOMPackage.DOCUMENT :
            return createDocument();
         case DocOMPackage.CHAPTER :
            return createChapter();
         case DocOMPackage.LIST_ITEM :
            return createListItem();
         case DocOMPackage.HEADER :
            return createHeader();
         case DocOMPackage.QUOTE :
            return createQuote();
         case DocOMPackage.CODE :
            return createCode();
         case DocOMPackage.HORIZONTAL_LINE :
            return createHorizontalLine();
         case DocOMPackage.LINK :
            return createLink();
         case DocOMPackage.REFERENCE :
            return createReference();
         case DocOMPackage.DECLARATION :
            return createDeclaration();
         case DocOMPackage.CODE_LITERAL :
            return createCodeLiteral();
         case DocOMPackage.NEW_LINE :
            return createNewLine();
         case DocOMPackage.TABLE :
            return createTable();
         case DocOMPackage.TABLE_ROW :
            return createTableRow();
         case DocOMPackage.TABLE_CELL :
            return createTableCell();
         default :
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object createFromString(EDataType eDataType, String initialValue)
   {
      switch (eDataType.getClassifierID())
      {
         case DocOMPackage.EMPHASIS_TYPE :
            return createEmphasisTypeFromString(eDataType, initialValue);
         case DocOMPackage.LIST_TYPE :
            return createListTypeFromString(eDataType, initialValue);
         case DocOMPackage.ALIGNMENT :
            return createAlignmentFromString(eDataType, initialValue);
         default :
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String convertToString(EDataType eDataType, Object instanceValue)
   {
      switch (eDataType.getClassifierID())
      {
         case DocOMPackage.EMPHASIS_TYPE :
            return convertEmphasisTypeToString(eDataType, instanceValue);
         case DocOMPackage.LIST_TYPE :
            return convertListTypeToString(eDataType, instanceValue);
         case DocOMPackage.ALIGNMENT :
            return convertAlignmentToString(eDataType, instanceValue);
         default :
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Paragraph createParagraph()
   {
      ParagraphImpl paragraph = new ParagraphImpl();
      return paragraph;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Emphasis createEmphasis()
   {
      EmphasisImpl emphasis = new EmphasisImpl();
      return emphasis;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public List createList()
   {
      ListImpl list = new ListImpl();
      return list;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Text createText()
   {
      TextImpl text = new TextImpl();
      return text;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Document createDocument()
   {
      DocumentImpl document = new DocumentImpl();
      return document;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Chapter createChapter()
   {
      ChapterImpl chapter = new ChapterImpl();
      return chapter;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ListItem createListItem()
   {
      ListItemImpl listItem = new ListItemImpl();
      return listItem;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Header createHeader()
   {
      HeaderImpl header = new HeaderImpl();
      return header;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Quote createQuote()
   {
      QuoteImpl quote = new QuoteImpl();
      return quote;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Code createCode()
   {
      CodeImpl code = new CodeImpl();
      return code;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public HorizontalLine createHorizontalLine()
   {
      HorizontalLineImpl horizontalLine = new HorizontalLineImpl();
      return horizontalLine;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Link createLink()
   {
      LinkImpl link = new LinkImpl();
      return link;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Reference createReference()
   {
      ReferenceImpl reference = new ReferenceImpl();
      return reference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Declaration createDeclaration()
   {
      DeclarationImpl declaration = new DeclarationImpl();
      return declaration;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public CodeLiteral createCodeLiteral()
   {
      CodeLiteralImpl codeLiteral = new CodeLiteralImpl();
      return codeLiteral;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NewLine createNewLine()
   {
      NewLineImpl newLine = new NewLineImpl();
      return newLine;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Table createTable()
   {
      TableImpl table = new TableImpl();
      return table;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public TableRow createTableRow()
   {
      TableRowImpl tableRow = new TableRowImpl();
      return tableRow;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public TableCell createTableCell()
   {
      TableCellImpl tableCell = new TableCellImpl();
      return tableCell;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EmphasisType createEmphasisTypeFromString(EDataType eDataType, String initialValue)
   {
      EmphasisType result = EmphasisType.get(initialValue);
      if (result == null)
         throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
            + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String convertEmphasisTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ListType createListTypeFromString(EDataType eDataType, String initialValue)
   {
      ListType result = ListType.get(initialValue);
      if (result == null)
         throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
            + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String convertListTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Alignment createAlignmentFromString(EDataType eDataType, String initialValue)
   {
      Alignment result = Alignment.get(initialValue);
      if (result == null)
         throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
            + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String convertAlignmentToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DocOMPackage getDocOMPackage()
   {
      return (DocOMPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static DocOMPackage getPackage()
   {
      return DocOMPackage.eINSTANCE;
   }

} // DocOMFactoryImpl
