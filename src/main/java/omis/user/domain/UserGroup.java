package omis.user.domain;

import java.io.Serializable;

/**
 * User group allows roles to be assigned to users.
 * @author Stephen Abson
 * @version 0.1.3 (Jan 8, 2013)
 * @since OMIS 3.0
 */
public interface UserGroup extends Serializable {

	/**
	 * Returns the ID of the user group.
	 * @return user group ID
	 */
	Long getId();

	/**
	 * Sets the ID of the user group.
	 * @param id user group ID
	 */
	void setId(Long id);

	/**
	 * Returns the name of the user group.
	 * @return user group name
	 */
	String getName();

	/**
	 * Sets the name of the user group.
	 * @param name user group name
	 */
	void setName(String name);

	/**
	 * Returns the description of the user group.
	 * @return user group description
	 */
	String getDescription();

	/**
	 * Sets the description of the user group.
	 * @param description user group description
	 */
	void setDescription(String description);

	/**
	 * Sets the order in which the group is sorted.
	 * @param sortOrder order in which group is sorted
	 */
	void setSortOrder(Short sortOrder);	
	
	/**
	 * Returns the order in which the group is sorted.
	 * @return order in which group is sorted
	 */
	Short getSortOrder();
	
	/**
	 * Sets whether the group is valid.
	 * @param valid whether role is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the group is valid.
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
	 * Returns a string containing the ID, name and description of the user
	 * group.
	 * @return string containing user group ID, name and description 
	 */
	@Override
	String toString();
}