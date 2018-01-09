package omis.relationship.domain;

import omis.audit.domain.Creatable;
import omis.person.domain.Person;

/**
 * Defines a relationship between two people.
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Feb 21, 2013)
 * @since OMIS 3.0
 */
public interface Relationship extends Creatable {

	/**
	 * Sets the ID.
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the first person in the relationship.
	 * @param firstPerson first person in relationship
	 */
	void setFirstPerson(Person firstPerson);
	
	/**
	 * Returns the first person in the relationship.
	 * @return first person in relationship
	 */
	Person getFirstPerson();
	
	/**
	 * Sets the second person in the relationship.
	 * @param secondPerson second person in relationship
	 */
	void setSecondPerson(Person secondPerson);
	
	/**
	 * Returns the second person in the relationship.
	 * @return second person in relationship
	 */
	Person getSecondPerson();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}