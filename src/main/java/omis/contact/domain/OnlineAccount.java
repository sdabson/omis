package omis.contact.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.contact.domain.OnlineAccountHost;

/**
 * Employer
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 1, 2015)
 * @since: OMIS 3.0
 */
public interface OnlineAccount extends Updatable, Creatable{
	/**
	 * Returns the online account id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets the online account id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the online account name.
	 * 
	 * @param name online account name
	 */
	void setName(String name);
	
	/**
	 * Gets the online account name.
	 * 
	 * @returns online account name
	 */
	String getName();
	
	/**
	 * Sets the "active".
	 * 
	 * @param "active" whether this online account is active or not 
	 */
	void setActive(Boolean active);
	
	/**
	 * Gets the "active".
	 * 
	 * @returns whether this online account is active or not
	 */
	Boolean getActive();
	
	/**
	 * Sets whether primary applies.
	 * 
	 * @param primary primary
	 */
	void setPrimary(Boolean primary);
	
	/**
	 * Returns whether primary applies.
	 * 
	 * @return primary
	 */
	Boolean getPrimary();
	
	/**
	 * Sets the online account host.
	 * 
	 * @param onlineAccountHost online account host 
	 */
	void setHost(OnlineAccountHost host);
	
	/**
	 * Gets the online account host.
	 * 
	 * @returns online account host
	 */
	OnlineAccountHost getHost();
	
	/**
	 * Sets the Contact.
	 * 
	 * @param Contact contact 
	 */
	void setContact(Contact contact);
	
	/**
	 * Gets the contact.
	 * 
	 * @returns contact
	 */
	Contact getContact();
	
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