package omis.contact.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Telephone number
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 1, 2015)
 * @since: OMIS 3.0
 */
public interface TelephoneNumber extends Updatable, Creatable{
	/**
	 * Returns the telephone number id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets the telephone number id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the phone number.
	 * 
	 * @param "value" phone number 
	 */
	void setValue(Long value);
	
	/**
	 * Gets the telephone .
	 * 
	 * @returns phone number
	 */
	Long getValue();
	
	/**
	 * Sets the phone extension.
	 * 
	 * @param extension phone extension 
	 */
	void setExtension(Integer extension);
	
	/**
	 * Gets the phone extension.
	 * 
	 * @returns phone extension
	 */
	Integer getExtension();
	
	/**
	 * Sets the "primary".
	 * 
	 * @param primary if this number is primary phone number or not 
	 */
	void setPrimary(Boolean primary);
	
	/**
	 * Gets the "primary".
	 * 
	 * @returns "primary" whether this phone number is primary or not
	 */
	Boolean getPrimary();
	
	/**
	 * Sets the "active".
	 * 
	 * @param active if this number is active in use or not 
	 */
	void setActive(Boolean active);
	
	/**
	 * Gets the "active".
	 * 
	 * @returns "active" whether this phone number is active in use or not
	 */
	Boolean getActive();
	
	/**
	 * Sets the "contact".
	 * 
	 * @param contact contact 
	 */
	void setContact(Contact contact);
	
	/**
	 * Gets the "contact".
	 * 
	 * @returns contact contact
	 */
	Contact getContact();
	
	/**
	 * Sets the telephone number category.
	 * 
	 * @param category set the phone number to certain category 
	 */
	void setCategory(TelephoneNumberCategory 
			category);
	
	/**
	 * Gets the telephone number category.
	 * 
	 * @returns category the category this number belongs to
	 */
	TelephoneNumberCategory getCategory();
	
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