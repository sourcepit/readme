/**
 */

package org.sourcepit.docom;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.docom.DocOMPackage
 * @generated
 */
public interface DocOMFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   DocOMFactory eINSTANCE = org.sourcepit.docom.impl.DocOMFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Paragraph</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Paragraph</em>'.
    * @generated
    */
   Paragraph createParagraph();

   /**
    * Returns a new object of class '<em>Emphasis</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Emphasis</em>'.
    * @generated
    */
   Emphasis createEmphasis();

   /**
    * Returns a new object of class '<em>List</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>List</em>'.
    * @generated
    */
   List createList();

   /**
    * Returns a new object of class '<em>Text</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Text</em>'.
    * @generated
    */
   Text createText();

   /**
    * Returns a new object of class '<em>Document</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Document</em>'.
    * @generated
    */
   Document createDocument();

   /**
    * Returns a new object of class '<em>Chapter</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Chapter</em>'.
    * @generated
    */
   Chapter createChapter();

   /**
    * Returns a new object of class '<em>List Item</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>List Item</em>'.
    * @generated
    */
   ListItem createListItem();

   /**
    * Returns a new object of class '<em>Header</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Header</em>'.
    * @generated
    */
   Header createHeader();

   /**
    * Returns a new object of class '<em>Quote</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Quote</em>'.
    * @generated
    */
   Quote createQuote();

   /**
    * Returns a new object of class '<em>Code</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Code</em>'.
    * @generated
    */
   Code createCode();

   /**
    * Returns a new object of class '<em>Horizontal Line</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Horizontal Line</em>'.
    * @generated
    */
   HorizontalLine createHorizontalLine();

   /**
    * Returns a new object of class '<em>Link</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Link</em>'.
    * @generated
    */
   Link createLink();

   /**
    * Returns a new object of class '<em>Reference</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Reference</em>'.
    * @generated
    */
   Reference createReference();

   /**
    * Returns a new object of class '<em>Declaration</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Declaration</em>'.
    * @generated
    */
   Declaration createDeclaration();

   /**
    * Returns a new object of class '<em>Code Literal</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Code Literal</em>'.
    * @generated
    */
   CodeLiteral createCodeLiteral();

   /**
    * Returns a new object of class '<em>New Line</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>New Line</em>'.
    * @generated
    */
   NewLine createNewLine();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the package supported by this factory.
    * @generated
    */
   DocOMPackage getDocOMPackage();

} // DocOMFactory
