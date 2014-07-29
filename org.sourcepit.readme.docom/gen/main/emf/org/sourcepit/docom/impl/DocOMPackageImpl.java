/**
 */

package org.sourcepit.docom.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
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
import org.sourcepit.docom.Listable;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.NewLine;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Quote;
import org.sourcepit.docom.Reference;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Table;
import org.sourcepit.docom.TableCell;
import org.sourcepit.docom.TableRow;
import org.sourcepit.docom.Text;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class DocOMPackageImpl extends EPackageImpl implements DocOMPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass paragraphEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass emphasisEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass listEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass textEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass literalEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass documentEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass chapterEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass literalGroupEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass listableEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass structurableEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass structuredEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass listItemEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass headerEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass quoteEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass codeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass horizontalLineEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass linkEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass referenceEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass declarationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass codeLiteralEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass newLineEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass tableEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass tableRowEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass tableCellEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EEnum emphasisTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EEnum listTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EEnum alignmentEEnum = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
    * EPackage.Registry} by the package
    * package URI value.
    * <p>
    * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
    * performs initialization of the package, or returns the registered package, if one already exists. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.sourcepit.docom.DocOMPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private DocOMPackageImpl()
   {
      super(eNS_URI, DocOMFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    * 
    * <p>
    * This method is used to initialize {@link DocOMPackage#eINSTANCE} when that field is accessed. Clients should not
    * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static DocOMPackage init()
   {
      if (isInited)
         return (DocOMPackage) EPackage.Registry.INSTANCE.getEPackage(DocOMPackage.eNS_URI);

      // Obtain or create and register package
      DocOMPackageImpl theDocOMPackage = (DocOMPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DocOMPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new DocOMPackageImpl());

      isInited = true;

      // Create package meta-data objects
      theDocOMPackage.createPackageContents();

      // Initialize created meta-data
      theDocOMPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theDocOMPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(DocOMPackage.eNS_URI, theDocOMPackage);
      return theDocOMPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getParagraph()
   {
      return paragraphEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getEmphasis()
   {
      return emphasisEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getEmphasis_Type()
   {
      return (EAttribute) emphasisEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getList()
   {
      return listEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getList_Type()
   {
      return (EAttribute) listEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getList_Items()
   {
      return (EReference) listEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getText()
   {
      return textEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getText_Text()
   {
      return (EAttribute) textEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getLiteral()
   {
      return literalEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getDocument()
   {
      return documentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getChapter()
   {
      return chapterEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getChapter_Header()
   {
      return (EReference) chapterEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getLiteralGroup()
   {
      return literalGroupEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getLiteralGroup_Literals()
   {
      return (EReference) literalGroupEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getListable()
   {
      return listableEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getStructurable()
   {
      return structurableEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getStructured()
   {
      return structuredEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getStructured_Content()
   {
      return (EReference) structuredEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getListItem()
   {
      return listItemEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getListItem_Content()
   {
      return (EReference) listItemEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getHeader()
   {
      return headerEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getQuote()
   {
      return quoteEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getCode()
   {
      return codeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getCode_Text()
   {
      return (EAttribute) codeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getCode_Language()
   {
      return (EAttribute) codeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getHorizontalLine()
   {
      return horizontalLineEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getLink()
   {
      return linkEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getLink_Url()
   {
      return (EAttribute) linkEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getLink_Title()
   {
      return (EAttribute) linkEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getReference()
   {
      return referenceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getReference_Id()
   {
      return (EAttribute) referenceEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getDeclaration()
   {
      return declarationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDeclaration_Id()
   {
      return (EAttribute) declarationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDeclaration_Url()
   {
      return (EAttribute) declarationEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDeclaration_Title()
   {
      return (EAttribute) declarationEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getCodeLiteral()
   {
      return codeLiteralEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getCodeLiteral_Text()
   {
      return (EAttribute) codeLiteralEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getNewLine()
   {
      return newLineEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getTable()
   {
      return tableEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getTable_ColumnDefinitions()
   {
      return (EAttribute) tableEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getTable_Header()
   {
      return (EReference) tableEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getTable_Body()
   {
      return (EReference) tableEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getTableRow()
   {
      return tableRowEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getTableRow_Cells()
   {
      return (EReference) tableRowEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getTableCell()
   {
      return tableCellEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getTableCell_Row()
   {
      return (EReference) tableCellEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getTableCell_ColumnSpan()
   {
      return (EAttribute) tableCellEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EEnum getEmphasisType()
   {
      return emphasisTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EEnum getListType()
   {
      return listTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EEnum getAlignment()
   {
      return alignmentEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DocOMFactory getDocOMFactory()
   {
      return (DocOMFactory) getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package. This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated)
         return;
      isCreated = true;

      // Create classes and their features
      paragraphEClass = createEClass(PARAGRAPH);

      emphasisEClass = createEClass(EMPHASIS);
      createEAttribute(emphasisEClass, EMPHASIS__TYPE);

      listEClass = createEClass(LIST);
      createEAttribute(listEClass, LIST__TYPE);
      createEReference(listEClass, LIST__ITEMS);

      textEClass = createEClass(TEXT);
      createEAttribute(textEClass, TEXT__TEXT);

      literalEClass = createEClass(LITERAL);

      documentEClass = createEClass(DOCUMENT);

      chapterEClass = createEClass(CHAPTER);
      createEReference(chapterEClass, CHAPTER__HEADER);

      literalGroupEClass = createEClass(LITERAL_GROUP);
      createEReference(literalGroupEClass, LITERAL_GROUP__LITERALS);

      listableEClass = createEClass(LISTABLE);

      structurableEClass = createEClass(STRUCTURABLE);

      structuredEClass = createEClass(STRUCTURED);
      createEReference(structuredEClass, STRUCTURED__CONTENT);

      listItemEClass = createEClass(LIST_ITEM);
      createEReference(listItemEClass, LIST_ITEM__CONTENT);

      headerEClass = createEClass(HEADER);

      quoteEClass = createEClass(QUOTE);

      codeEClass = createEClass(CODE);
      createEAttribute(codeEClass, CODE__TEXT);
      createEAttribute(codeEClass, CODE__LANGUAGE);

      horizontalLineEClass = createEClass(HORIZONTAL_LINE);

      linkEClass = createEClass(LINK);
      createEAttribute(linkEClass, LINK__URL);
      createEAttribute(linkEClass, LINK__TITLE);

      referenceEClass = createEClass(REFERENCE);
      createEAttribute(referenceEClass, REFERENCE__ID);

      declarationEClass = createEClass(DECLARATION);
      createEAttribute(declarationEClass, DECLARATION__ID);
      createEAttribute(declarationEClass, DECLARATION__URL);
      createEAttribute(declarationEClass, DECLARATION__TITLE);

      codeLiteralEClass = createEClass(CODE_LITERAL);
      createEAttribute(codeLiteralEClass, CODE_LITERAL__TEXT);

      newLineEClass = createEClass(NEW_LINE);

      tableEClass = createEClass(TABLE);
      createEAttribute(tableEClass, TABLE__COLUMN_DEFINITIONS);
      createEReference(tableEClass, TABLE__HEADER);
      createEReference(tableEClass, TABLE__BODY);

      tableRowEClass = createEClass(TABLE_ROW);
      createEReference(tableRowEClass, TABLE_ROW__CELLS);

      tableCellEClass = createEClass(TABLE_CELL);
      createEReference(tableCellEClass, TABLE_CELL__ROW);
      createEAttribute(tableCellEClass, TABLE_CELL__COLUMN_SPAN);

      // Create enums
      emphasisTypeEEnum = createEEnum(EMPHASIS_TYPE);
      listTypeEEnum = createEEnum(LIST_TYPE);
      alignmentEEnum = createEEnum(ALIGNMENT);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model. This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized)
         return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      paragraphEClass.getESuperTypes().add(this.getLiteralGroup());
      paragraphEClass.getESuperTypes().add(this.getListable());
      paragraphEClass.getESuperTypes().add(this.getStructurable());
      emphasisEClass.getESuperTypes().add(this.getLiteral());
      emphasisEClass.getESuperTypes().add(this.getLiteralGroup());
      listEClass.getESuperTypes().add(this.getStructurable());
      listEClass.getESuperTypes().add(this.getListable());
      textEClass.getESuperTypes().add(this.getLiteral());
      literalEClass.getESuperTypes().add(this.getListable());
      documentEClass.getESuperTypes().add(this.getStructured());
      chapterEClass.getESuperTypes().add(this.getStructurable());
      chapterEClass.getESuperTypes().add(this.getStructured());
      headerEClass.getESuperTypes().add(this.getLiteralGroup());
      quoteEClass.getESuperTypes().add(this.getStructured());
      quoteEClass.getESuperTypes().add(this.getStructurable());
      codeEClass.getESuperTypes().add(this.getStructurable());
      codeEClass.getESuperTypes().add(this.getListable());
      horizontalLineEClass.getESuperTypes().add(this.getStructurable());
      horizontalLineEClass.getESuperTypes().add(this.getListable());
      linkEClass.getESuperTypes().add(this.getLiteralGroup());
      linkEClass.getESuperTypes().add(this.getLiteral());
      referenceEClass.getESuperTypes().add(this.getLiteralGroup());
      referenceEClass.getESuperTypes().add(this.getLiteral());
      declarationEClass.getESuperTypes().add(this.getStructurable());
      declarationEClass.getESuperTypes().add(this.getListable());
      codeLiteralEClass.getESuperTypes().add(this.getLiteral());
      codeLiteralEClass.getESuperTypes().add(this.getListable());
      newLineEClass.getESuperTypes().add(this.getStructurable());
      newLineEClass.getESuperTypes().add(this.getLiteral());
      tableEClass.getESuperTypes().add(this.getStructurable());
      tableCellEClass.getESuperTypes().add(this.getLiteralGroup());

      // Initialize classes, features, and operations; add parameters
      initEClass(paragraphEClass, Paragraph.class, "Paragraph", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(emphasisEClass, Emphasis.class, "Emphasis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getEmphasis_Type(), this.getEmphasisType(), "type", null, 1, 1, Emphasis.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getList_Type(), this.getListType(), "type", null, 1, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE,
         IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getList_Items(), this.getListItem(), null, "items", null, 0, -1, List.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getText_Text(), ecorePackage.getEString(), "text", null, 1, 1, Text.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(literalEClass, Literal.class, "Literal", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(chapterEClass, Chapter.class, "Chapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getChapter_Header(), this.getHeader(), null, "header", null, 1, 1, Chapter.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(literalGroupEClass, LiteralGroup.class, "LiteralGroup", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getLiteralGroup_Literals(), this.getLiteral(), null, "literals", null, 0, -1, LiteralGroup.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(listableEClass, Listable.class, "Listable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(structurableEClass, Structurable.class, "Structurable", IS_ABSTRACT, IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(structuredEClass, Structured.class, "Structured", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getStructured_Content(), this.getStructurable(), null, "content", null, 0, -1, Structured.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(listItemEClass, ListItem.class, "ListItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getListItem_Content(), this.getListable(), null, "content", null, 1, -1, ListItem.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(headerEClass, Header.class, "Header", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(quoteEClass, Quote.class, "Quote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(codeEClass, Code.class, "Code", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getCode_Text(), ecorePackage.getEString(), "text", null, 1, 1, Code.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getCode_Language(), ecorePackage.getEString(), "language", null, 0, 1, Code.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(horizontalLineEClass, HorizontalLine.class, "HorizontalLine", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getLink_Url(), ecorePackage.getEString(), "url", null, 1, 1, Link.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLink_Title(), ecorePackage.getEString(), "title", null, 0, 1, Link.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getReference_Id(), ecorePackage.getEString(), "id", null, 1, 1, Reference.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(declarationEClass, Declaration.class, "Declaration", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getDeclaration_Id(), ecorePackage.getEString(), "id", null, 1, 1, Declaration.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getDeclaration_Url(), ecorePackage.getEString(), "url", null, 1, 1, Declaration.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getDeclaration_Title(), ecorePackage.getEString(), "title", null, 0, 1, Declaration.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(codeLiteralEClass, CodeLiteral.class, "CodeLiteral", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getCodeLiteral_Text(), ecorePackage.getEString(), "text", null, 1, 1, CodeLiteral.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(newLineEClass, NewLine.class, "NewLine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getTable_ColumnDefinitions(), this.getAlignment(), "columnDefinitions", null, 1, -1, Table.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTable_Header(), this.getTableRow(), null, "header", null, 1, 1, Table.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);
      initEReference(getTable_Body(), this.getTableRow(), null, "body", null, 1, -1, Table.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(tableRowEClass, TableRow.class, "TableRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getTableRow_Cells(), this.getTableCell(), this.getTableCell_Row(), "cells", null, 1, -1,
         TableRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(tableCellEClass, TableCell.class, "TableCell", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getTableCell_Row(), this.getTableRow(), this.getTableRow_Cells(), "row", null, 1, 1,
         TableCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTableCell_ColumnSpan(), ecorePackage.getEInt(), "columnSpan", "1", 0, 1, TableCell.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Initialize enums and add enum literals
      initEEnum(emphasisTypeEEnum, EmphasisType.class, "EmphasisType");
      addEEnumLiteral(emphasisTypeEEnum, EmphasisType.ITALIC);
      addEEnumLiteral(emphasisTypeEEnum, EmphasisType.BOLD);
      addEEnumLiteral(emphasisTypeEEnum, EmphasisType.CODE);
      addEEnumLiteral(emphasisTypeEEnum, EmphasisType.STRIKETHROUGH);

      initEEnum(listTypeEEnum, ListType.class, "ListType");
      addEEnumLiteral(listTypeEEnum, ListType.UNORDERED);
      addEEnumLiteral(listTypeEEnum, ListType.ORDERED);

      initEEnum(alignmentEEnum, Alignment.class, "Alignment");
      addEEnumLiteral(alignmentEEnum, Alignment.LEFT);
      addEEnumLiteral(alignmentEEnum, Alignment.CENTER);
      addEEnumLiteral(alignmentEEnum, Alignment.RIGHT);

      // Create resource
      createResource(eNS_URI);
   }

} // DocOMPackageImpl
