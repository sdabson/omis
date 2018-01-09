package omis.user.domain;

import java.io.Serializable;

/**
 * User role.
 * 
 * <p>User role assignments determine the features that a user can use and the
 * information that a user is permitted to view or modify.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 8, 2013)
 * @since OMIS 3.0
 */
public interface UserRole extends Serializable {

	/**
	 * Returns the ID of the user role.
	 * @return user role ID
	 */
	Long getId();

	/**
	 * Sets the ID of the user role.
	 * @param id user role ID
	 */
	void setId(Long id);

	/**
	 * Returns the name of the user role.
	 * @return user role name
	 */
	String getName();

	/**
	 * Sets the name of the user role.
	 * @param name user role name
	 */
	void setName(String name);

	/**
	 * Returns the description of the user role.
	 * @return user role description
	 */
	String getDescription();

	/**
	 * Sets the description of the user role.
	 * @param description user role description
	 */
	void setDescription(String description);
	
	/**
	 * Sets the order in which the role is sorted.
	 * @param sortOrder order in which role is sorted
	 */
	void setSortOrder(Short sortOrder);	
	
	/**
	 * Returns the order in which the role is sorted.
	 * @return order in which role is sorted
	 */
	Short getSortOrder();
	
	/**
	 * Sets whether the role is valid.
	 * @param valid whether role is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the role is valid.
	 * @return whether role is valid
	 */
	Boolean getValid();
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code o} are equal;
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
	
	/**
	 * Returns a string representation of the user role containing the ID,
	 * user role name and description.
	 * @return string containing user role ID, name and description
	 */
	@Override
	String toString();
}