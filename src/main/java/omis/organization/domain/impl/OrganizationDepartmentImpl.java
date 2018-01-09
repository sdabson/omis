package omis.organization.domain.impl;

import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;

/**
 * Organization department implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public class OrganizationDepartmentImpl implements OrganizationDepartment{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Organization organization;
	
	private String name;
	
	private Boolean active;
	
	/**
	 * Instantiates a default instance of organization department.
	 */
	public OrganizationDepartmentImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Organization getOrganization() {
		return this.organization;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrganization(final Organization organization) {
		this.organization = organization;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getActive() {
		return this.active;
	}

	/** {@inheritDoc} */
	@Override
	public void setActive(final Boolean active) {
		this.active = active;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof OrganizationDepartment)) {
			return false;
		}
		
		OrganizationDepartment that = (OrganizationDepartment) o;
		
		if (this.getOrganization() == null) {
			throw new IllegalStateException("Organization required.");
		}
		if (!this.getOrganization().equals(that.getOrganization())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOrganization() == null) {
			throw new IllegalStateException("Organization required.");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOrganization().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}