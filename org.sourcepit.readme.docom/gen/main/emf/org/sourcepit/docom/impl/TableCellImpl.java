/**
 */

package org.sourcepit.docom.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.TableCell;
import org.sourcepit.docom.TableRow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Cell</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.docom.impl.TableCellImpl#getRow <em>Row</em>}</li>
 * <li>{@link org.sourcepit.docom.impl.TableCellImpl#getColumnSpan <em>Column Span</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableCellImpl extends LiteralGroupImpl implements TableCell
{
   /**
    * The default value of the '{@link #getColumnSpan() <em>Column Span</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getColumnSpan()
    * @generated
    * @ordered
    */
   protected static final int COLUMN_SPAN_EDEFAULT = 1;
   /**
    * The cached value of the '{@link #getColumnSpan() <em>Column Span</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getColumnSpan()
    * @generated
    * @ordered
    */
   protected int columnSpan = COLUMN_SPAN_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected TableCellImpl()
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
      return DocOMPackage.Literals.TABLE_CELL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public TableRow getRow()
   {
      if (eContainerFeatureID() != DocOMPackage.TABLE_CELL__ROW)
         return null;
      return (TableRow) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetRow(TableRow newRow, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newRow, DocOMPackage.TABLE_CELL__ROW, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setRow(TableRow newRow)
   {
      if (newRow != eInternalContainer() || (eContainerFeatureID() != DocOMPackage.TABLE_CELL__ROW && newRow != null))
      {
         if (EcoreUtil.isAncestor(this, newRow))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newRow != null)
            msgs = ((InternalEObject) newRow).eInverseAdd(this, DocOMPackage.TABLE_ROW__CELLS, TableRow.class, msgs);
         msgs = basicSetRow(newRow, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.TABLE_CELL__ROW, newRow, newRow));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getColumnSpan()
   {
      return columnSpan;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setColumnSpan(int newColumnSpan)
   {
      int oldColumnSpan = columnSpan;
      columnSpan = newColumnSpan;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.TABLE_CELL__COLUMN_SPAN, oldColumnSpan,
            columnSpan));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case DocOMPackage.TABLE_CELL__ROW :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetRow((TableRow) otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
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
         case DocOMPackage.TABLE_CELL__ROW :
            return basicSetRow(null, msgs);
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
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case DocOMPackage.TABLE_CELL__ROW :
            return eInternalContainer().eInverseRemove(this, DocOMPackage.TABLE_ROW__CELLS, TableRow.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case DocOMPackage.TABLE_CELL__ROW :
            return getRow();
         case DocOMPackage.TABLE_CELL__COLUMN_SPAN :
            return getColumnSpan();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case DocOMPackage.TABLE_CELL__ROW :
            setRow((TableRow) newValue);
            return;
         case DocOMPackage.TABLE_CELL__COLUMN_SPAN :
            setColumnSpan((Integer) newValue);
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
         case DocOMPackage.TABLE_CELL__ROW :
            setRow((TableRow) null);
            return;
         case DocOMPackage.TABLE_CELL__COLUMN_SPAN :
            setColumnSpan(COLUMN_SPAN_EDEFAULT);
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
         case DocOMPackage.TABLE_CELL__ROW :
            return getRow() != null;
         case DocOMPackage.TABLE_CELL__COLUMN_SPAN :
            return columnSpan != COLUMN_SPAN_EDEFAULT;
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
      result.append(" (columnSpan: ");
      result.append(columnSpan);
      result.append(')');
      return result.toString();
   }

} // TableCellImpl
