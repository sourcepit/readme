/**
 */

package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.Code#getText <em>Text</em>}</li>
 * <li>{@link org.sourcepit.docom.Code#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getCode()
 * @model
 * @generated
 */
public interface Code extends Structurable, Listable
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
    * @see org.sourcepit.docom.DocOMPackage#getCode_Text()
    * @model required="true"
    * @generated
    */
   String getText();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Code#getText <em>Text</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Text</em>' attribute.
    * @see #getText()
    * @generated
    */
   void setText(String value);

   /**
    * Returns the value of the '<em><b>Language</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Language</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Language</em>' attribute.
    * @see #setLanguage(String)
    * @see org.sourcepit.docom.DocOMPackage#getCode_Language()
    * @model
    * @generated
    */
   String getLanguage();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Code#getLanguage <em>Language</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Language</em>' attribute.
    * @see #getLanguage()
    * @generated
    */
   void setLanguage(String value);

} // Code
