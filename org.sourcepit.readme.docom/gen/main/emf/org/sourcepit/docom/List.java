/**
 */
package org.sourcepit.docom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.docom.List#getType <em>Type</em>}</li>
 *   <li>{@link org.sourcepit.docom.List#getListItems <em>List Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getList()
 * @model
 * @generated
 */
public interface List extends Structurable
{
   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * The literals are from the enumeration {@link org.sourcepit.docom.ListType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see org.sourcepit.docom.ListType
    * @see #setType(ListType)
    * @see org.sourcepit.docom.DocOMPackage#getList_Type()
    * @model required="true"
    * @generated
    */
   ListType getType();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.List#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see org.sourcepit.docom.ListType
    * @see #getType()
    * @generated
    */
   void setType(ListType value);

   /**
    * Returns the value of the '<em><b>List Items</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.docom.Listable}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>List Items</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>List Items</em>' containment reference list.
    * @see org.sourcepit.docom.DocOMPackage#getList_ListItems()
    * @model containment="true"
    * @generated
    */
   EList<Listable> getListItems();

} // List
