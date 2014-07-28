/**
 */

package org.sourcepit.docom.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sourcepit.docom.DocOMPackage;
import org.sourcepit.docom.Link;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.docom.impl.LinkImpl#getUrl <em>Url</em>}</li>
 * <li>{@link org.sourcepit.docom.impl.LinkImpl#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends LiteralGroupImpl implements Link
{
   /**
    * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getUrl()
    * @generated
    * @ordered
    */
   protected static final String URL_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getUrl()
    * @generated
    * @ordered
    */
   protected String url = URL_EDEFAULT;

   /**
    * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTitle()
    * @generated
    * @ordered
    */
   protected static final String TITLE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTitle()
    * @generated
    * @ordered
    */
   protected String title = TITLE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected LinkImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return DocOMPackage.Literals.LINK;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getUrl()
   {
      return url;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setUrl(String newUrl)
   {
      String oldUrl = url;
      url = newUrl;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.LINK__URL, oldUrl, url));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTitle(String newTitle)
   {
      String oldTitle = title;
      title = newTitle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, DocOMPackage.LINK__TITLE, oldTitle, title));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case DocOMPackage.LINK__URL :
            return getUrl();
         case DocOMPackage.LINK__TITLE :
            return getTitle();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case DocOMPackage.LINK__URL :
            setUrl((String) newValue);
            return;
         case DocOMPackage.LINK__TITLE :
            setTitle((String) newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.LINK__URL :
            setUrl(URL_EDEFAULT);
            return;
         case DocOMPackage.LINK__TITLE :
            setTitle(TITLE_EDEFAULT);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case DocOMPackage.LINK__URL :
            return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
         case DocOMPackage.LINK__TITLE :
            return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (url: ");
      result.append(url);
      result.append(", title: ");
      result.append(title);
      result.append(')');
      return result.toString();
   }

} // LinkImpl
