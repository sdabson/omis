package omis.user.web.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import omis.user.domain.UserAccount;
import omis.user.domain.UserRole;

/**
 * Form for managing user groups.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 8, 2013)
 * @since OMIS 3.0
 */
public class UserGroupForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String description;
	
	private Set<UserRole> roles = new HashSet<UserRole>();
	
	private Set<UserAccount> members = new HashSet<UserAccount>();

	private Short sortOrder;

	private Boolean valid;

	/** Instantiates a default user group form. */
	public UserGroupForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the name of the user group.
	 * 
	 * @return user group name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the user group.
	 * 
	 * @param name user group name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the user group.
	 * 
	 * @return user group description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the user group.
	 * 
	 * @param description user group description
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
	 * Returns the roles associated with the user group.
	 * 
	 * @return user group roles
	 */
	public Set<UserRole> getRoles() {
		return this.roles;
	}

	/**
	 * Sets the roles associated with the user group.
	 * 
	 * @param roles user group roles
	 */
	public void setRoles(final Set<UserRole> roles) {
		this.roles = roles;
	}
	
	/**
	 * Replaces the roles associated with the user group.
	 * 
	 * @param roles roles to exclusively associate with user group
	 */
	public void replaceRoles(final Collection<UserRole> roles) {
		this.roles.clear();
		this.roles.addAll(roles);
	}
	
	/**
	 * Clears the roles.
	 */
	public void clearRoles() {
		this.roles.clear();
	}

	/**
	 * Returns the members of the group.
	 * 
	 * @return members of group
	 */
	public Set<UserAccount> getMembers() {
		return this.members;
	}

	/**
	 * Sets the members of the group.
	 * 
	 * @param members members of group
	 */
	public void setMembers(final Set<UserAccount> members) {
		this.members = members;
	}
	
	/**
	 * Replaces the members of the groups.
	 * 
	 * @param members members to exclusively add to group
	 */
	public void replaceMembers(final Collection<UserAccount> members) {
		this.members.clear();
		this.members.addAll(members);
	}
	
	/**
	 * Clears the members.
	 */
	public void clearMembers() {
		this.members.clear();
	}
}