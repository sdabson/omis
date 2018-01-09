package omis.user.web.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import omis.user.domain.UserGroup;

/**
 * Form for managing user roles.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 8, 2013)
 * @since OMIS 3.0
 */
public class UserRoleForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String description;
	
	private Short sortOrder;
	
	private Boolean valid;
	
	private Set<UserGroup> groups = new HashSet<UserGroup>();

	/** Instantiates a default user role form. */
	public UserRoleForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the name of the user role.
	 * 
	 * @return user role name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the user role.
	 * 
	 * @param name user role name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the user role.
	 * 
	 * @return user role description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the user role.
	 * 
	 * @param description user role description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Returns whether the role is valid.
	 * 
	 * @return whether role is valid
	 */
	public Boolean getValid() {
		return this.valid;
	}

	/**
	 * Sets whether the role is valid.
	 * 
	 * @param valid whether role is valid
	 */
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * Returns the groups with which the user role is associated.
	 * 
	 * @return groups with which user role is associated
	 */
	public Set<UserGroup> getGroups() {
		return this.groups;
	}

	/**
	 * Sets the groups with which the user role is associated.
	 * 
	 * @param groups groups with which user role is associated
	 */
	public void setGroups(final Set<UserGroup> groups) {
		this.groups = groups;
	}
	
	/**
	 * Replace the groups with which the user role is associated.
	 * 
	 * @param groups groups with which user role is associated
	 */
	public void replaceGroups(final Collection<UserGroup> groups) {
		this.groups.clear();
		this.groups.addAll(groups);
	}
	
	/**
	 * Clears the groups with which the user role is associated.
	 */
	public void clearGroups() {
		this.groups.clear();
	}
}