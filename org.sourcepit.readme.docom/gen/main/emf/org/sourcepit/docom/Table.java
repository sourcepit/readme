/**
 */

package org.sourcepit.docom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.Table#getColumnDefinitions <em>Column Definitions</em>}</li>
 * <li>{@link org.sourcepit.docom.Table#getHeader <em>Header</em>}</li>
 * <li>{@link org.sourcepit.docom.Table#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends Structurable
{
   /**
    * Returns the value of the '<em><b>Column Definitions</b></em>' attribute list.
    * The list contents are of type {@link org.sourcepit.docom.Alignment}.
    * The literals are from the enumeration {@link org.sourcepit.docom.Alignment}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Column Definitions</em>' attribute list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Column Definitions</em>' attribute list.
    * @see org.sourcepit.docom.Alignment
    * @see org.sourcepit.docom.DocOMPackage#getTable_ColumnDefinitions()
    * @model unique="false" required="true"
    * @generated
    */
   EList<Alignment> getColumnDefinitions();

   /**
    * Returns the value of the '<em><b>Header</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Header</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Header</em>' containment reference.
    * @see #setHeader(TableRow)
    * @see org.sourcepit.docom.DocOMPackage#getTable_Header()
    * @model containment="true" required="true"
    * @generated
    */
   TableRow getHeader();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Table#getHeader <em>Header</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Header</em>' containment reference.
    * @see #getHeader()
    * @generated
    */
   void setHeader(TableRow value);

   /**
    * Returns the value of the '<em><b>Body</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.docom.TableRow}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Body</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Body</em>' containment reference list.
    * @see org.sourcepit.docom.DocOMPackage#getTable_Body()
    * @model containment="true" required="true"
    * @generated
    */
   EList<TableRow> getBody();

} // Table
