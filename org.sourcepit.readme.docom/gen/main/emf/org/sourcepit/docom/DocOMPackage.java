/**
 */

package org.sourcepit.docom;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.docom.DocOMFactory
 * @model kind="package"
 * @generated
 */
public interface DocOMPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNAME = "docom";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_URI = "http://www.sourcepit.org/docom";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_PREFIX = "docom";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   DocOMPackage eINSTANCE = org.sourcepit.docom.impl.DocOMPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.LiteralGroupImpl <em>Literal Group</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.LiteralGroupImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getLiteralGroup()
    * @generated
    */
   int LITERAL_GROUP = 7;

   /**
    * The feature id for the '<em><b>Literals</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LITERAL_GROUP__LITERALS = 0;

   /**
    * The number of structural features of the '<em>Literal Group</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LITERAL_GROUP_FEATURE_COUNT = 1;

   /**
    * The number of operations of the '<em>Literal Group</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LITERAL_GROUP_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.ParagraphImpl <em>Paragraph</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.ParagraphImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getParagraph()
    * @generated
    */
   int PARAGRAPH = 0;

   /**
    * The feature id for the '<em><b>Literals</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int PARAGRAPH__LITERALS = LITERAL_GROUP__LITERALS;

   /**
    * The number of structural features of the '<em>Paragraph</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int PARAGRAPH_FEATURE_COUNT = LITERAL_GROUP_FEATURE_COUNT + 0;

   /**
    * The number of operations of the '<em>Paragraph</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int PARAGRAPH_OPERATION_COUNT = LITERAL_GROUP_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.Listable <em>Listable</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.Listable
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListable()
    * @generated
    */
   int LISTABLE = 8;

   /**
    * The number of structural features of the '<em>Listable</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LISTABLE_FEATURE_COUNT = 0;

   /**
    * The number of operations of the '<em>Listable</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LISTABLE_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.Literal <em>Literal</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.Literal
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getLiteral()
    * @generated
    */
   int LITERAL = 4;

   /**
    * The number of structural features of the '<em>Literal</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LITERAL_FEATURE_COUNT = LISTABLE_FEATURE_COUNT + 0;

   /**
    * The number of operations of the '<em>Literal</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LITERAL_OPERATION_COUNT = LISTABLE_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.EmphasisImpl <em>Emphasis</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.EmphasisImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getEmphasis()
    * @generated
    */
   int EMPHASIS = 1;

   /**
    * The feature id for the '<em><b>Literals</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EMPHASIS__LITERALS = LITERAL_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EMPHASIS__TYPE = LITERAL_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Emphasis</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EMPHASIS_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>Emphasis</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EMPHASIS_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.Structurable <em>Structurable</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.Structurable
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getStructurable()
    * @generated
    */
   int STRUCTURABLE = 9;

   /**
    * The number of structural features of the '<em>Structurable</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int STRUCTURABLE_FEATURE_COUNT = 0;

   /**
    * The number of operations of the '<em>Structurable</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int STRUCTURABLE_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.ListImpl <em>List</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.ListImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getList()
    * @generated
    */
   int LIST = 2;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST__TYPE = STRUCTURABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Items</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST__ITEMS = STRUCTURABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>List</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST_FEATURE_COUNT = STRUCTURABLE_FEATURE_COUNT + 2;

   /**
    * The number of operations of the '<em>List</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST_OPERATION_COUNT = STRUCTURABLE_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.TextImpl <em>Text</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.TextImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getText()
    * @generated
    */
   int TEXT = 3;

   /**
    * The feature id for the '<em><b>Text</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int TEXT__TEXT = LITERAL_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Text</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int TEXT_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Text</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int TEXT_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.StructuredImpl <em>Structured</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.StructuredImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getStructured()
    * @generated
    */
   int STRUCTURED = 10;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int STRUCTURED__CONTENT = 0;

   /**
    * The number of structural features of the '<em>Structured</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int STRUCTURED_FEATURE_COUNT = 1;

   /**
    * The number of operations of the '<em>Structured</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int STRUCTURED_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.DocumentImpl <em>Document</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.DocumentImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getDocument()
    * @generated
    */
   int DOCUMENT = 5;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int DOCUMENT__CONTENT = STRUCTURED__CONTENT;

   /**
    * The number of structural features of the '<em>Document</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int DOCUMENT_FEATURE_COUNT = STRUCTURED_FEATURE_COUNT + 0;

   /**
    * The number of operations of the '<em>Document</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int DOCUMENT_OPERATION_COUNT = STRUCTURED_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.ChapterImpl <em>Chapter</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.ChapterImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getChapter()
    * @generated
    */
   int CHAPTER = 6;

   /**
    * The feature id for the '<em><b>Literals</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int CHAPTER__LITERALS = LITERAL_GROUP__LITERALS;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int CHAPTER__CONTENT = LITERAL_GROUP_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Chapter</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int CHAPTER_FEATURE_COUNT = LITERAL_GROUP_FEATURE_COUNT + 1;

   /**
    * The number of operations of the '<em>Chapter</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int CHAPTER_OPERATION_COUNT = LITERAL_GROUP_OPERATION_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.impl.ListItemImpl <em>List Item</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.impl.ListItemImpl
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListItem()
    * @generated
    */
   int LIST_ITEM = 11;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST_ITEM__CONTENT = 0;

   /**
    * The number of structural features of the '<em>List Item</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST_ITEM_FEATURE_COUNT = 1;

   /**
    * The number of operations of the '<em>List Item</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LIST_ITEM_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.EmphasisType <em>Emphasis Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.EmphasisType
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getEmphasisType()
    * @generated
    */
   int EMPHASIS_TYPE = 12;

   /**
    * The meta object id for the '{@link org.sourcepit.docom.ListType <em>List Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.docom.ListType
    * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListType()
    * @generated
    */
   int LIST_TYPE = 13;


   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Paragraph <em>Paragraph</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Paragraph</em>'.
    * @see org.sourcepit.docom.Paragraph
    * @generated
    */
   EClass getParagraph();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Emphasis <em>Emphasis</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Emphasis</em>'.
    * @see org.sourcepit.docom.Emphasis
    * @generated
    */
   EClass getEmphasis();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.docom.Emphasis#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.sourcepit.docom.Emphasis#getType()
    * @see #getEmphasis()
    * @generated
    */
   EAttribute getEmphasis_Type();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.List <em>List</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>List</em>'.
    * @see org.sourcepit.docom.List
    * @generated
    */
   EClass getList();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.docom.List#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.sourcepit.docom.List#getType()
    * @see #getList()
    * @generated
    */
   EAttribute getList_Type();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.docom.List#getItems
    * <em>Items</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Items</em>'.
    * @see org.sourcepit.docom.List#getItems()
    * @see #getList()
    * @generated
    */
   EReference getList_Items();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Text <em>Text</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Text</em>'.
    * @see org.sourcepit.docom.Text
    * @generated
    */
   EClass getText();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.docom.Text#getText <em>Text</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Text</em>'.
    * @see org.sourcepit.docom.Text#getText()
    * @see #getText()
    * @generated
    */
   EAttribute getText_Text();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Literal <em>Literal</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Literal</em>'.
    * @see org.sourcepit.docom.Literal
    * @generated
    */
   EClass getLiteral();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Document <em>Document</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Document</em>'.
    * @see org.sourcepit.docom.Document
    * @generated
    */
   EClass getDocument();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Chapter <em>Chapter</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Chapter</em>'.
    * @see org.sourcepit.docom.Chapter
    * @generated
    */
   EClass getChapter();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.LiteralGroup <em>Literal Group</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Literal Group</em>'.
    * @see org.sourcepit.docom.LiteralGroup
    * @generated
    */
   EClass getLiteralGroup();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.docom.LiteralGroup#getLiterals
    * <em>Literals</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Literals</em>'.
    * @see org.sourcepit.docom.LiteralGroup#getLiterals()
    * @see #getLiteralGroup()
    * @generated
    */
   EReference getLiteralGroup_Literals();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Listable <em>Listable</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Listable</em>'.
    * @see org.sourcepit.docom.Listable
    * @generated
    */
   EClass getListable();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Structurable <em>Structurable</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Structurable</em>'.
    * @see org.sourcepit.docom.Structurable
    * @generated
    */
   EClass getStructurable();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.Structured <em>Structured</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Structured</em>'.
    * @see org.sourcepit.docom.Structured
    * @generated
    */
   EClass getStructured();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.docom.Structured#getContent
    * <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Content</em>'.
    * @see org.sourcepit.docom.Structured#getContent()
    * @see #getStructured()
    * @generated
    */
   EReference getStructured_Content();

   /**
    * Returns the meta object for class '{@link org.sourcepit.docom.ListItem <em>List Item</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>List Item</em>'.
    * @see org.sourcepit.docom.ListItem
    * @generated
    */
   EClass getListItem();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.docom.ListItem#getContent
    * <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Content</em>'.
    * @see org.sourcepit.docom.ListItem#getContent()
    * @see #getListItem()
    * @generated
    */
   EReference getListItem_Content();

   /**
    * Returns the meta object for enum '{@link org.sourcepit.docom.EmphasisType <em>Emphasis Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for enum '<em>Emphasis Type</em>'.
    * @see org.sourcepit.docom.EmphasisType
    * @generated
    */
   EEnum getEmphasisType();

   /**
    * Returns the meta object for enum '{@link org.sourcepit.docom.ListType <em>List Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for enum '<em>List Type</em>'.
    * @see org.sourcepit.docom.ListType
    * @generated
    */
   EEnum getListType();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the factory that creates the instances of the model.
    * @generated
    */
   DocOMFactory getDocOMFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    * <li>each class,</li>
    * <li>each feature of each class,</li>
    * <li>each operation of each class,</li>
    * <li>each enum,</li>
    * <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.ParagraphImpl <em>Paragraph</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.ParagraphImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getParagraph()
       * @generated
       */
      EClass PARAGRAPH = eINSTANCE.getParagraph();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.EmphasisImpl <em>Emphasis</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.EmphasisImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getEmphasis()
       * @generated
       */
      EClass EMPHASIS = eINSTANCE.getEmphasis();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute EMPHASIS__TYPE = eINSTANCE.getEmphasis_Type();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.ListImpl <em>List</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.ListImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getList()
       * @generated
       */
      EClass LIST = eINSTANCE.getList();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute LIST__TYPE = eINSTANCE.getList_Type();

      /**
       * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference LIST__ITEMS = eINSTANCE.getList_Items();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.TextImpl <em>Text</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.TextImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getText()
       * @generated
       */
      EClass TEXT = eINSTANCE.getText();

      /**
       * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute TEXT__TEXT = eINSTANCE.getText_Text();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.Literal <em>Literal</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.Literal
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getLiteral()
       * @generated
       */
      EClass LITERAL = eINSTANCE.getLiteral();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.DocumentImpl <em>Document</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.DocumentImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getDocument()
       * @generated
       */
      EClass DOCUMENT = eINSTANCE.getDocument();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.ChapterImpl <em>Chapter</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.ChapterImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getChapter()
       * @generated
       */
      EClass CHAPTER = eINSTANCE.getChapter();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.LiteralGroupImpl <em>Literal Group</em>}'
       * class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.LiteralGroupImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getLiteralGroup()
       * @generated
       */
      EClass LITERAL_GROUP = eINSTANCE.getLiteralGroup();

      /**
       * The meta object literal for the '<em><b>Literals</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference LITERAL_GROUP__LITERALS = eINSTANCE.getLiteralGroup_Literals();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.Listable <em>Listable</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.Listable
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListable()
       * @generated
       */
      EClass LISTABLE = eINSTANCE.getListable();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.Structurable <em>Structurable</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.Structurable
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getStructurable()
       * @generated
       */
      EClass STRUCTURABLE = eINSTANCE.getStructurable();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.StructuredImpl <em>Structured</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.StructuredImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getStructured()
       * @generated
       */
      EClass STRUCTURED = eINSTANCE.getStructured();

      /**
       * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference STRUCTURED__CONTENT = eINSTANCE.getStructured_Content();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.impl.ListItemImpl <em>List Item</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.impl.ListItemImpl
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListItem()
       * @generated
       */
      EClass LIST_ITEM = eINSTANCE.getListItem();

      /**
       * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference LIST_ITEM__CONTENT = eINSTANCE.getListItem_Content();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.EmphasisType <em>Emphasis Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.EmphasisType
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getEmphasisType()
       * @generated
       */
      EEnum EMPHASIS_TYPE = eINSTANCE.getEmphasisType();

      /**
       * The meta object literal for the '{@link org.sourcepit.docom.ListType <em>List Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.docom.ListType
       * @see org.sourcepit.docom.impl.DocOMPackageImpl#getListType()
       * @generated
       */
      EEnum LIST_TYPE = eINSTANCE.getListType();

   }

} // DocOMPackage
