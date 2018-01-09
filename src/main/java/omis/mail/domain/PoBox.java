package omis.mail.domain;

import java.io.Serializable;

import omis.address.domain.ZipCode;

/**
 * PO box.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 28, 2014)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public interface PoBox extends Serializable {
	
	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	String getValue();
	
	/**
	 * Sets the ZIP code.
	 * 
	 * @param zipCode ZIP code
	 */
	void setZipCode(ZipCode zipCode);
	
	/**
	 * Returns the ZIP code.
	 * 
	 * @return ZIP code
	 */
	ZipCode getZipCode();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}