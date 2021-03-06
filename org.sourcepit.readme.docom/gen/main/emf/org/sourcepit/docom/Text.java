/**
 */

package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.Text#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getText()
 * @model
 * @generated
 */
public interface Text extends Literal
{
   /**
    * Returns the value of the '<em><b>Text</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Text</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Text</em>' attribute.
    * @see #setText(String)
    * @see org.sourcepit.docom.DocOMPackage#getText_Text()
    * @model required="true"
    * @generated
    */
   String getText();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Text#getText <em>Text</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Text</em>' attribute.
    * @see #getText()
    * @generated
    */
   void setText(String value);

} // Text
