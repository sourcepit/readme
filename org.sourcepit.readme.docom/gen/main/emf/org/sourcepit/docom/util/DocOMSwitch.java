/**
 */

package org.sourcepit.docom.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.Code;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.HorizontalLine;
import org.sourcepit.docom.List;
import org.sourcepit.docom.ListItem;
import org.sourcepit.docom.Listable;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;
import org.sourcepit.docom.Paragraph;
import org.sourcepit.docom.Quote;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;
import org.sourcepit.docom.Text;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each
 * class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.docom.DocOMPackage
 * @generated
 */
public class DocOMSwitch<T> extends Switch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected static DocOMPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DocOMSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = DocOMPackage.eINSTANCE;
      }
   }

   /**
    * Checks whether this is a switch for the given package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @parameter ePackage the package in question.
    * @return whether this is a switch for the given package.
    * @generated
    */
   @Override
   protected boolean isSwitchFor(EPackage ePackage)
   {
      return ePackage == modelPackage;
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   @Override
   protected T doSwitch(int classifierID, EObject theEObject)
   {
      switch (classifierID)
      {
         case DocOMPackage.PARAGRAPH :
         {
            Paragraph paragraph = (Paragraph) theEObject;
            T result = caseParagraph(paragraph);
            if (result == null)
               result = caseLiteralGroup(paragraph);
            if (result == null)
               result = caseListable(paragraph);
            if (result == null)
               result = caseStructurable(paragraph);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.EMPHASIS :
         {
            Emphasis emphasis = (Emphasis) theEObject;
            T result = caseEmphasis(emphasis);
            if (result == null)
               result = caseLiteral(emphasis);
            if (result == null)
               result = caseLiteralGroup(emphasis);
            if (result == null)
               result = caseListable(emphasis);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.LIST :
         {
            List list = (List) theEObject;
            T result = caseList(list);
            if (result == null)
               result = caseStructurable(list);
            if (result == null)
               result = caseListable(list);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.TEXT :
         {
            Text text = (Text) theEObject;
            T result = caseText(text);
            if (result == null)
               result = caseLiteral(text);
            if (result == null)
               result = caseListable(text);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.LITERAL :
         {
            Literal literal = (Literal) theEObject;
            T result = caseLiteral(literal);
            if (result == null)
               result = caseListable(literal);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.DOCUMENT :
         {
            Document document = (Document) theEObject;
            T result = caseDocument(document);
            if (result == null)
               result = caseStructured(document);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.CHAPTER :
         {
            Chapter chapter = (Chapter) theEObject;
            T result = caseChapter(chapter);
            if (result == null)
               result = caseStructurable(chapter);
            if (result == null)
               result = caseStructured(chapter);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.LITERAL_GROUP :
         {
            LiteralGroup literalGroup = (LiteralGroup) theEObject;
            T result = caseLiteralGroup(literalGroup);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.LISTABLE :
         {
            Listable listable = (Listable) theEObject;
            T result = caseListable(listable);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.STRUCTURABLE :
         {
            Structurable structurable = (Structurable) theEObject;
            T result = caseStructurable(structurable);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.STRUCTURED :
         {
            Structured structured = (Structured) theEObject;
            T result = caseStructured(structured);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.LIST_ITEM :
         {
            ListItem listItem = (ListItem) theEObject;
            T result = caseListItem(listItem);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.HEADER :
         {
            Header header = (Header) theEObject;
            T result = caseHeader(header);
            if (result == null)
               result = caseLiteralGroup(header);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.QUOTE :
         {
            Quote quote = (Quote) theEObject;
            T result = caseQuote(quote);
            if (result == null)
               result = caseStructured(quote);
            if (result == null)
               result = caseStructurable(quote);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.CODE :
         {
            Code code = (Code) theEObject;
            T result = caseCode(code);
            if (result == null)
               result = caseStructurable(code);
            if (result == null)
               result = caseListable(code);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case DocOMPackage.HORIZONTAL_LINE :
         {
            HorizontalLine horizontalLine = (HorizontalLine) theEObject;
            T result = caseHorizontalLine(horizontalLine);
            if (result == null)
               result = caseStructurable(horizontalLine);
            if (result == null)
               result = caseListable(horizontalLine);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         default :
            return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Paragraph</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Paragraph</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseParagraph(Paragraph object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Emphasis</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Emphasis</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseEmphasis(Emphasis object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>List</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>List</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseList(List object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Text</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseText(Text object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseLiteral(Literal object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Document</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Document</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseDocument(Document object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Chapter</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Chapter</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseChapter(Chapter object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Literal Group</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Literal Group</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseLiteralGroup(LiteralGroup object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Listable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Listable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseListable(Listable object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Structurable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Structurable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseStructurable(Structurable object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Structured</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Structured</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseStructured(Structured object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>List Item</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>List Item</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseListItem(ListItem object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Header</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Header</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseHeader(Header object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Quote</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Quote</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseQuote(Quote object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Code</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Code</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseCode(Code object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Horizontal Line</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Horizontal Line</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseHorizontalLine(HorizontalLine object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch, but this is the last case anyway.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
   @Override
   public T defaultCase(EObject object)
   {
      return null;
   }

} // DocOMSwitch
