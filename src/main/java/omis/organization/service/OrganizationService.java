package omis.organization.service;

import java.util.List;

import omis.organization.domain.Organization;

/**
 * Service for organizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 4, 2013)
 * @since OMIS 3.0
 */
public interface OrganizationService {

	/**
	 * Returns the organization with the specified ID.
	 * 
	 * @param id ID of organization to return
	 * @return organization with specified ID
	 */
	Organization findById(Long id);
	
	/**
	 * Returns the organization with the specified name.
	 * 
	 * @param name name of organization to return
	 * @return organization with specified name; {@code null} if no organization
	 * was found with the specified name
	 */
	Organization findByName(String name);
	
	/**
	 * Returns the children of a parent organization ordered by name.
	 * 
	 * @param parent parent organization of children to return
	 * @return children of parent organization ordered by name
	 */
	List<Organization> findByParent(Organization parent);
	
	/**
	 * Returns organizations ordered by name.
	 * 
	 * @return organizations ordered by name
	 */
	List<Organization> findAll();
	
	/**
	 * Saves an organization.
	 * 
	 * @param organization organization to save
	 * @return saved organization
	 */
	Organization save(Organization organization);
	
	/**
	 * Removes an organization.
	 * 
	 * @param organization organization to remove
	 */
	void remove(Organization organization);
	
	/**
	 * Returns organizations with names matching the specified partial name
	 * ordered by name.
	 * 
	 * @param partialName partial name of organizations to return
	 * @return organizations with names matching partial name
	 */
	List<Organization> findByPartialName(String partialName);
}