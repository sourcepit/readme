/**
 */

package org.sourcepit.docom;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Emphasis Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.docom.DocOMPackage#getEmphasisType()
 * @model
 * @generated
 */
public enum EmphasisType implements Enumerator
{
   /**
    * The '<em><b>ITALIC</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #ITALIC_VALUE
    * @generated
    * @ordered
    */
   ITALIC(0, "ITALIC", "ITALIC"),

   /**
    * The '<em><b>BOLD</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #BOLD_VALUE
    * @generated
    * @ordered
    */
   BOLD(1, "BOLD", "BOLD"),

   /**
    * The '<em><b>STRIKETHROUGH</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #STRIKETHROUGH_VALUE
    * @generated
    * @ordered
    */
   STRIKETHROUGH(3, "STRIKETHROUGH", "STRIKETHROUGH");

   /**
    * The '<em><b>ITALIC</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>ITALIC</b></em>' literal object isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #ITALIC
    * @model
    * @generated
    * @ordered
    */
   public static final int ITALIC_VALUE = 0;

   /**
    * The '<em><b>BOLD</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>BOLD</b></em>' literal object isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #BOLD
    * @model
    * @generated
    * @ordered
    */
   public static final int BOLD_VALUE = 1;

   /**
    * The '<em><b>STRIKETHROUGH</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>STRIKETHROUGH</b></em>' literal object isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #STRIKETHROUGH
    * @model
    * @generated
    * @ordered
    */
   public static final int STRIKETHROUGH_VALUE = 3;

   /**
    * An array of all the '<em><b>Emphasis Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private static final EmphasisType[] VALUES_ARRAY = new EmphasisType[] { ITALIC, BOLD, STRIKETHROUGH, };

   /**
    * A public read-only list of all the '<em><b>Emphasis Type</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static final List<EmphasisType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Emphasis Type</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmphasisType get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         EmphasisType result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Emphasis Type</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmphasisType getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         EmphasisType result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Emphasis Type</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmphasisType get(int value)
   {
      switch (value)
      {
         case ITALIC_VALUE :
            return ITALIC;
         case BOLD_VALUE :
            return BOLD;
         case STRIKETHROUGH_VALUE :
            return STRIKETHROUGH;
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final int value;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final String name;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final String literal;

   /**
    * Only this class can construct instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EmphasisType(int value, String name, String literal)
   {
      this.value = value;
      this.name = name;
      this.literal = literal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getValue()
   {
      return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getLiteral()
   {
      return literal;
   }

   /**
    * Returns the literal value of the enumerator, which is its string representation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString()
   {
      return literal;
   }

} // EmphasisType
