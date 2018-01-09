package omis.organization.domain.impl;

import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDivision;

/**
 * Organization division implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 14, 2016)
 * @since OMIS 3.0
 */

final public class OrganizationDivisionImpl implements OrganizationDivision {

	private static final long serialVersionUID = 1L;
	
	private Organization organization;
	
	private Long id;
	
	private String name;
	
	private Boolean active;

	/**
	 * Instantiates a default instance of organization department.
	 */
	public OrganizationDivisionImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** {@inheritDoc} */
	@Override
	public Organization getOrganization() {
		return this.organization;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getActive() {
		return this.active;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof OrganizationDivision)) {
			return false;
		}
		
		OrganizationDivision that = (OrganizationDivision) o;
		
		if (this.getOrganization() == null) {
			throw new IllegalStateException("Organization required.");
		}
		if (!this.getOrganization().equals(that.getOrganization())) {
			return false;
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
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.organization.hashCode();
		hashCode += 29 * hashCode + this.name.hashCode();
		return hashCode;
	}
}