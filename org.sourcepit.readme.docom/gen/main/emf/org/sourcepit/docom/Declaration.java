/**
 */

package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.Declaration#getId <em>Id</em>}</li>
 * <li>{@link org.sourcepit.docom.Declaration#getUrl <em>Url</em>}</li>
 * <li>{@link org.sourcepit.docom.Declaration#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getDeclaration()
 * @model
 * @generated
 */
public interface Declaration extends Structurable, Listable
{
   /**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see org.sourcepit.docom.DocOMPackage#getDeclaration_Id()
    * @model required="true"
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Declaration#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
   void setId(String value);

   /**
    * Returns the value of the '<em><b>Url</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Url</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Url</em>' attribute.
    * @see #setUrl(String)
    * @see org.sourcepit.docom.DocOMPackage#getDeclaration_Url()
    * @model required="true"
    * @generated
    */
   String getUrl();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Declaration#getUrl <em>Url</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Url</em>' attribute.
    * @see #getUrl()
    * @generated
    */
   void setUrl(String value);

   /**
    * Returns the value of the '<em><b>Title</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Title</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Title</em>' attribute.
    * @see #setTitle(String)
    * @see org.sourcepit.docom.DocOMPackage#getDeclaration_Title()
    * @model
    * @generated
    */
   String getTitle();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.Declaration#getTitle <em>Title</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Title</em>' attribute.
    * @see #getTitle()
    * @generated
    */
   void setTitle(String value);

} // Declaration
