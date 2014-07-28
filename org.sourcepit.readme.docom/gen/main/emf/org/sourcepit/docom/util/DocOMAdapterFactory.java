/**
 */

package org.sourcepit.docom.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Document;
import org.sourcepit.docom.Emphasis;
import org.sourcepit.docom.Header;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.docom.DocOMPackage
 * @generated
 */
public class DocOMAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected static DocOMPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DocOMAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = DocOMPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object
    * of the model.
    * <!-- end-user-doc -->
    * 
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject) object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected DocOMSwitch<Adapter> modelSwitch = new DocOMSwitch<Adapter>()
   {
      @Override
      public Adapter caseParagraph(Paragraph object)
      {
         return createParagraphAdapter();
      }

      @Override
      public Adapter caseEmphasis(Emphasis object)
      {
         return createEmphasisAdapter();
      }

      @Override
      public Adapter caseList(List object)
      {
         return createListAdapter();
      }

      @Override
      public Adapter caseText(Text object)
      {
         return createTextAdapter();
      }

      @Override
      public Adapter caseLiteral(Literal object)
      {
         return createLiteralAdapter();
      }

      @Override
      public Adapter caseDocument(Document object)
      {
         return createDocumentAdapter();
      }

      @Override
      public Adapter caseChapter(Chapter object)
      {
         return createChapterAdapter();
      }

      @Override
      public Adapter caseLiteralGroup(LiteralGroup object)
      {
         return createLiteralGroupAdapter();
      }

      @Override
      public Adapter caseListable(Listable object)
      {
         return createListableAdapter();
      }

      @Override
      public Adapter caseStructurable(Structurable object)
      {
         return createStructurableAdapter();
      }

      @Override
      public Adapter caseStructured(Structured object)
      {
         return createStructuredAdapter();
      }

      @Override
      public Adapter caseListItem(ListItem object)
      {
         return createListItemAdapter();
      }

      @Override
      public Adapter caseHeader(Header object)
      {
         return createHeaderAdapter();
      }

      @Override
      public Adapter caseQuote(Quote object)
      {
         return createQuoteAdapter();
      }

      @Override
      public Adapter defaultCase(EObject object)
      {
         return createEObjectAdapter();
      }
   };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject) target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Paragraph <em>Paragraph</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Paragraph
    * @generated
    */
   public Adapter createParagraphAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Emphasis <em>Emphasis</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Emphasis
    * @generated
    */
   public Adapter createEmphasisAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.List <em>List</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.List
    * @generated
    */
   public Adapter createListAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Text <em>Text</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Text
    * @generated
    */
   public Adapter createTextAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Literal <em>Literal</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Literal
    * @generated
    */
   public Adapter createLiteralAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Document <em>Document</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Document
    * @generated
    */
   public Adapter createDocumentAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Chapter <em>Chapter</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Chapter
    * @generated
    */
   public Adapter createChapterAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.LiteralGroup <em>Literal Group</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.LiteralGroup
    * @generated
    */
   public Adapter createLiteralGroupAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Listable <em>Listable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Listable
    * @generated
    */
   public Adapter createListableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Structurable <em>Structurable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Structurable
    * @generated
    */
   public Adapter createStructurableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Structured <em>Structured</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Structured
    * @generated
    */
   public Adapter createStructuredAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.ListItem <em>List Item</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.ListItem
    * @generated
    */
   public Adapter createListItemAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Header <em>Header</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Header
    * @generated
    */
   public Adapter createHeaderAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.docom.Quote <em>Quote</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.docom.Quote
    * @generated
    */
   public Adapter createQuoteAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} // DocOMAdapterFactory
