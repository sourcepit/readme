/**
 */
package org.sourcepit.docom.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Literal;
import org.sourcepit.docom.LiteralGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sourcepit.docom.impl.LiteralGroupImpl#getLiterals <em>Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LiteralGroupImpl extends MinimalEObjectImpl.Container implements LiteralGroup
{
   /**
    * The cached value of the '{@link #getLiterals() <em>Literals</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLiterals()
    * @generated
    * @ordered
    */
   protected EList<Literal> literals;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LiteralGroupImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return DocOMPackage.Literals.LITERAL_GROUP;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<Literal> getLiterals()
   {
      if (literals == null)
      {
         literals = new EObjectContainmentEList<Literal>(Literal.class, this, DocOMPackage.LITERAL_GROUP__LITERALS);
      }
      return literals;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case DocOMPackage.LITERAL_GROUP__LITERALS:
            return ((InternalEList<?>)getLiterals()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case DocOMPackage.LITERAL_GROUP__LITERALS:
            return getLiterals();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case DocOMPackage.LITERAL_GROUP__LITERALS:
            getLiterals().clear();
            getLiterals().addAll((Collection<? extends Literal>)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.LITERAL_GROUP__LITERALS:
            getLiterals().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.LITERAL_GROUP__LITERALS:
            return literals != null && !literals.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //LiteralGroupImpl
