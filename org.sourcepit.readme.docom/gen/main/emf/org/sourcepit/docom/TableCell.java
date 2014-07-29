/**
 */

package org.sourcepit.docom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Cell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.docom.TableCell#getRow <em>Row</em>}</li>
 * <li>{@link org.sourcepit.docom.TableCell#getColumnSpan <em>Column Span</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.docom.DocOMPackage#getTableCell()
 * @model
 * @generated
 */
public interface TableCell extends LiteralGroup
{
   /**
    * Returns the value of the '<em><b>Row</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.docom.TableRow#getCells <em>Cells</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Row</em>' container reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Row</em>' container reference.
    * @see #setRow(TableRow)
    * @see org.sourcepit.docom.DocOMPackage#getTableCell_Row()
    * @see org.sourcepit.docom.TableRow#getCells
    * @model opposite="cells" required="true" transient="false"
    * @generated
    */
   TableRow getRow();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.TableCell#getRow <em>Row</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Row</em>' container reference.
    * @see #getRow()
    * @generated
    */
   void setRow(TableRow value);

   /**
    * Returns the value of the '<em><b>Column Span</b></em>' attribute.
    * The default value is <code>"1"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Column Span</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Column Span</em>' attribute.
    * @see #setColumnSpan(int)
    * @see org.sourcepit.docom.DocOMPackage#getTableCell_ColumnSpan()
    * @model default="1"
    * @generated
    */
   int getColumnSpan();

   /**
    * Sets the value of the '{@link org.sourcepit.docom.TableCell#getColumnSpan <em>Column Span</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Column Span</em>' attribute.
    * @see #getColumnSpan()
    * @generated
    */
   void setColumnSpan(int value);

} // TableCell
