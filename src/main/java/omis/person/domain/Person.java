package omis.person.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Person.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 30, 2013)
 * @since OMIS 3.0
 */
public interface Person
		extends Creatable, Updatable {

	/**
	 * Sets the ID of the person.
	 * 
	 * @param id person ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the person.
	 * 
	 * @return person ID
	 */
	Long getId();
	
	/**
	 * Sets the name of the person.
	 * 
	 * @param name person name
	 */
	void setName(PersonName name);
	
	/**
	 * Returns the name of the person.
	 * 
	 * @return person name
	 */
	PersonName getName();
	
	/**
	 * Sets the identity of the person.
	 * 
	 * @param identity identity of person
	 */
	void setIdentity(PersonIdentity identity);
	
	/**
	 * Returns the identity of the person.
	 * 
	 * @return identity of person
	 */
	PersonIdentity getIdentity();
	
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