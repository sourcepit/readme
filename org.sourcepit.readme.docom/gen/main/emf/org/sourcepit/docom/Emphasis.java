/**
 */
package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Emphasis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.docom.Emphasis#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getEmphasis()
 * @model
 * @generated
 */
public interface Emphasis extends Literal, LiteralGroup
{
   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * The literals are from the enumeration {@link org.sourcepit.docom.EmphasisType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see org.sourcepit.docom.EmphasisType
    * @see #setType(EmphasisType)
    * @see org.sourcepit.docom.DocOMPackage#getEmphasis_Type()
    * @model required="true"
    * @generated
    */
   EmphasisType getType();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Emphasis#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see org.sourcepit.docom.EmphasisType
    * @see #getType()
    * @generated
    */
   void setType(EmphasisType value);

} // Emphasis
