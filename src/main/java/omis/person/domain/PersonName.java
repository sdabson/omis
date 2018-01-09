package omis.person.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Person name.
 * @author Stephen Abson
 * @version 0.1.0 (Nov 27, 2012)
 * @since OMIS 3.0
 */
public interface PersonName extends Creatable, Updatable {
	
	/**
	 * Sets the ID of the name.
	 * @param id name ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the name.
	 * @return name ID
	 */
	Long getId();
	
	/**
	 * Sets the person with whom the name is associated.
	 * @param person person with whom name is associated
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the person with whom the name is associated.
	 * @return person with whom name is associated
	 */
	Person getPerson();
	
	/**
	 * Sets the last name.
	 * @param lastName last name
	 */
	void setLastName(String lastName);
	
	/**
	 * Returns the last name.
	 * @return last name
	 */
	String getLastName();
	
	/**
	 * Sets the first name.
	 * @param firstName first name
	 */
	void setFirstName(String firstName);
	
	/**
	 * Returns the first name.
	 * @return first name
	 */
	String getFirstName();
	
	/**
	 * Sets the middle name.
	 * @param middleName middle name
	 */
	void setMiddleName(String middleName);
	
	/**
	 * Returns the middle name.
	 * @return middle name
	 */
	String getMiddleName();
	
	/**
	 * Sets the suffix.
	 * @param suffix suffix
	 */
	void setSuffix(String suffix);
	
	/**
	 * Returns the suffix.
	 * @return suffix
	 */
	String getSuffix();
	
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