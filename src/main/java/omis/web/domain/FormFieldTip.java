package omis.web.domain;

import java.io.Serializable;

/**
 * Form field tip.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public interface FormFieldTip
		extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets form.
	 * 
	 * @param form form
	 */
	void setForm(String form);
	
	/**
	 * Returns form.
	 * 
	 * @return form
	 */
	String getForm();
	
	/**
	 * Sets field name
	 * 
	 * @param fieldName field name
	 */
	void setFieldName(String fieldName);
	
	/**
	 * Returns field name.
	 * 
	 * @return field name
	 */
	String getFieldName();
	
	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	String getValue();
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param o reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code o} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object o);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}