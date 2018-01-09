package omis.contact.domain;

import omis.address.domain.Address;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.contact.domain.component.PoBox;
import omis.person.domain.Person;

/**
 * Contact
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 1, 2015)
 * @since: OMIS 3.0
 */
public interface Contact extends Updatable, Creatable{
	/**
	 * Returns the contact id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets the contact id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the mailing address.
	 * 
	 * @param mailing address
	 */
	void setMailingAddress(Address mailingAddress);
	
	/**
	 * Gets the mailing address.
	 * 
	 * @returns the mailing address
	 */
	Address getMailingAddress();
	
	/**
	 * Sets the person.
	 * 
	 * @param person person 
	 */
	void setPerson(Person person);
	
	/**
	 * Gets the person.
	 * 
	 * @returns person 
	 */
	Person getPerson();
	
	/**
	 * Sets the PO box.
	 * 
	 * @param poBox PO box 
	 */
	void setPoBox(PoBox poBox);
	
	/**
	 * Gets the PO box.
	 * 
	 * @returns PO box 
	 */
	PoBox getPoBox();

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