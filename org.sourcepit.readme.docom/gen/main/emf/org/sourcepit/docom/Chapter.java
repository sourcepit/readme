/**
 */

package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.Chapter#getHeader <em>Header</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getChapter()
 * @model
 * @generated
 */
public interface Chapter extends Structurable, Structured
{

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
    * @see #setHeader(Header)
    * @see org.sourcepit.docom.DocOMPackage#getChapter_Header()
    * @model containment="true" required="true"
    * @generated
    */
   Header getHeader();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Chapter#getHeader <em>Header</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Header</em>' containment reference.
    * @see #getHeader()
    * @generated
    */
   void setHeader(Header value);
} // Chapter
