/**
 */

package org.sourcepit.docom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.TableRow#getCells <em>Cells</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getTableRow()
 * @model
 * @generated
 */
public interface TableRow extends EObject
{
   /**
    * Returns the value of the '<em><b>Cells</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.docom.TableCell}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.docom.TableCell#getRow <em>Row</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Cells</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Cells</em>' containment reference list.
    * @see org.sourcepit.docom.DocOMPackage#getTableRow_Cells()
    * @see org.sourcepit.docom.TableCell#getRow
    * @model opposite="row" containment="true" required="true"
    * @generated
    */
   EList<TableCell> getCells();

} // TableRow
