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
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.docom.Alignment;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Table;
import org.sourcepit.docom.TableRow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.docom.impl.TableImpl#getColumnDefinitions <em>Column Definitions</em>}</li>
 * <li>{@link org.sourcepit.docom.impl.TableImpl#getHeader <em>Header</em>}</li>
 * <li>{@link org.sourcepit.docom.impl.TableImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableImpl extends MinimalEObjectImpl.Container implements Table
{
   /**
    * The cached value of the '{@link #getColumnDefinitions() <em>Column Definitions</em>}' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getColumnDefinitions()
    * @generated
    * @ordered
    */
   protected EList<Alignment> columnDefinitions;

   /**
    * The cached value of the '{@link #getHeader() <em>Header</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getHeader()
    * @generated
    * @ordered
    */
   protected TableRow header;
   /**
    * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getBody()
    * @generated
    * @ordered
    */
   protected EList<TableRow> body;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected TableImpl()
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
      return DocOMPackage.Literals.TABLE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Alignment> getColumnDefinitions()
   {
      if (columnDefinitions == null)
      {
         columnDefinitions = new EDataTypeEList<Alignment>(Alignment.class, this,
            DocOMPackage.TABLE__COLUMN_DEFINITIONS);
      }
      return columnDefinitions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public TableRow getHeader()
   {
      return header;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetHeader(TableRow newHeader, NotificationChain msgs)
   {
      TableRow oldHeader = header;
      header = newHeader;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocOMPackage.TABLE__HEADER,
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
   public void setHeader(TableRow newHeader)
   {
      if (newHeader != header)
      {
         NotificationChain msgs = null;
         if (header != null)
            msgs = ((InternalEObject) header).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DocOMPackage.TABLE__HEADER,
               null, msgs);
         if (newHeader != null)
            msgs = ((InternalEObject) newHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DocOMPackage.TABLE__HEADER,
               null, msgs);
         msgs = basicSetHeader(newHeader, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.TABLE__HEADER, newHeader, newHeader));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<TableRow> getBody()
   {
      if (body == null)
      {
         body = new EObjectContainmentEList<TableRow>(TableRow.class, this, DocOMPackage.TABLE__BODY);
      }
      return body;
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
         case DocOMPackage.TABLE__HEADER :
            return basicSetHeader(null, msgs);
         case DocOMPackage.TABLE__BODY :
            return ((InternalEList<?>) getBody()).basicRemove(otherEnd, msgs);
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
         case DocOMPackage.TABLE__COLUMN_DEFINITIONS :
            return getColumnDefinitions();
         case DocOMPackage.TABLE__HEADER :
            return getHeader();
         case DocOMPackage.TABLE__BODY :
            return getBody();
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
         case DocOMPackage.TABLE__COLUMN_DEFINITIONS :
            getColumnDefinitions().clear();
            getColumnDefinitions().addAll((Collection<? extends Alignment>) newValue);
            return;
         case DocOMPackage.TABLE__HEADER :
            setHeader((TableRow) newValue);
            return;
         case DocOMPackage.TABLE__BODY :
            getBody().clear();
            getBody().addAll((Collection<? extends TableRow>) newValue);
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
         case DocOMPackage.TABLE__COLUMN_DEFINITIONS :
            getColumnDefinitions().clear();
            return;
         case DocOMPackage.TABLE__HEADER :
            setHeader((TableRow) null);
            return;
         case DocOMPackage.TABLE__BODY :
            getBody().clear();
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
         case DocOMPackage.TABLE__COLUMN_DEFINITIONS :
            return columnDefinitions != null && !columnDefinitions.isEmpty();
         case DocOMPackage.TABLE__HEADER :
            return header != null;
         case DocOMPackage.TABLE__BODY :
            return body != null && !body.isEmpty();
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
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (columnDefinitions: ");
      result.append(columnDefinitions);
      result.append(')');
      return result.toString();
   }

} // TableImpl
