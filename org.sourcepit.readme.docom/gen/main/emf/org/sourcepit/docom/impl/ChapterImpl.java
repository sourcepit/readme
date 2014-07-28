/**
 */

package org.sourcepit.docom.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.docom.Chapter;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Header;
import org.sourcepit.docom.Structurable;
import org.sourcepit.docom.Structured;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Chapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.docom.impl.ChapterImpl#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.docom.impl.ChapterImpl#getHeader <em>Header</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChapterImpl extends MinimalEObjectImpl.Container implements Chapter
{
   /**
    * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getContent()
    * @generated
    * @ordered
    */
   protected EList<Structurable> content;

   /**
    * The cached value of the '{@link #getHeader() <em>Header</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getHeader()
    * @generated
    * @ordered
    */
   protected Header header;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ChapterImpl()
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
   protected EClass eStaticClass()
   {
      return DocOMPackage.Literals.CHAPTER;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Structurable> getContent()
   {
      if (content == null)
      {
         content = new EObjectContainmentEList<Structurable>(Structurable.class, this, DocOMPackage.CHAPTER__CONTENT);
      }
      return content;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Header getHeader()
   {
      return header;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetHeader(Header newHeader, NotificationChain msgs)
   {
      Header oldHeader = header;
      header = newHeader;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocOMPackage.CHAPTER__HEADER,
            oldHeader, newHeader);
         if (msgs == null)
            msgs = notification;
         else
            msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setHeader(Header newHeader)
   {
      if (newHeader != header)
      {
         NotificationChain msgs = null;
         if (header != null)
            msgs = ((InternalEObject) header).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - DocOMPackage.CHAPTER__HEADER, null, msgs);
         if (newHeader != null)
            msgs = ((InternalEObject) newHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - DocOMPackage.CHAPTER__HEADER, null, msgs);
         msgs = basicSetHeader(newHeader, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.CHAPTER__HEADER, newHeader, newHeader));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case DocOMPackage.CHAPTER__CONTENT :
            return ((InternalEList<?>) getContent()).basicRemove(otherEnd, msgs);
         case DocOMPackage.CHAPTER__HEADER :
            return basicSetHeader(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case DocOMPackage.CHAPTER__CONTENT :
            return getContent();
         case DocOMPackage.CHAPTER__HEADER :
            return getHeader();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case DocOMPackage.CHAPTER__CONTENT :
            getContent().clear();
            getContent().addAll((Collection<? extends Structurable>) newValue);
            return;
         case DocOMPackage.CHAPTER__HEADER :
            setHeader((Header) newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.CHAPTER__CONTENT :
            getContent().clear();
            return;
         case DocOMPackage.CHAPTER__HEADER :
            setHeader((Header) null);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.CHAPTER__CONTENT :
            return content != null && !content.isEmpty();
         case DocOMPackage.CHAPTER__HEADER :
            return header != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == Structured.class)
      {
         switch (derivedFeatureID)
         {
            case DocOMPackage.CHAPTER__CONTENT :
               return DocOMPackage.STRUCTURED__CONTENT;
            default :
               return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == Structured.class)
      {
         switch (baseFeatureID)
         {
            case DocOMPackage.STRUCTURED__CONTENT :
               return DocOMPackage.CHAPTER__CONTENT;
            default :
               return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
   }

} // ChapterImpl
