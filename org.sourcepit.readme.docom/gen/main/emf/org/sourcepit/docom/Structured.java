/**
 */
package org.sourcepit.docom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structured</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.docom.Structured#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getStructured()
 * @model abstract="true"
 * @generated
 */
public interface Structured extends EObject
{
   /**
    * Returns the value of the '<em><b>Content</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.docom.Structurable}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Content</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Content</em>' containment reference list.
    * @see org.sourcepit.docom.DocOMPackage#getStructured_Content()
    * @model containment="true"
    * @generated
    */
   EList<Structurable> getContent();

} // Structured
