/**
 */

package org.sourcepit.docom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.LiteralGroup#getLiterals <em>Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getLiteralGroup()
 * @model abstract="true"
 * @generated
 */
public interface LiteralGroup extends EObject
{
   /**
    * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.docom.Literal}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Literals</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Literals</em>' containment reference list.
    * @see org.sourcepit.docom.DocOMPackage#getLiteralGroup_Literals()
    * @model containment="true"
    * @generated
    */
   EList<Literal> getLiterals();

} // LiteralGroup
