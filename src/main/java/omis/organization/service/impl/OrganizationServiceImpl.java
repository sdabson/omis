package omis.organization.service.impl;

import java.util.List;

import omis.organization.dao.OrganizationDao;
import omis.organization.domain.Organization;
import omis.organization.service.OrganizationService;

/**
 * Implementation of service for organizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2013)
 * @since OMIS 3.0
 */
public class OrganizationServiceImpl
		implements OrganizationService {

	private final OrganizationDao organizationDao;

	/**
	 * Instantiates an implementation of service for organizations.
	 * 
	 * @param organizationDao data access object for organizations
	 */
	public OrganizationServiceImpl(
			final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<Organization> findByParent(final Organization parent) {
		return this.organizationDao.findByParent(parent);
	}

	/** {@inheritDoc} */
	@Override
	public Organization findById(final Long id) {
		return this.organizationDao.findById(id, false);
	}
	
	/** {@inheritDoc} */
	@Override
	public Organization findByName(final String name) {
		return this.organizationDao.findByName(name);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Organization> findAll() {
		return this.organizationDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Organization save(final Organization organization) {
		return this.organizationDao.makePersistent(organization);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Organization organization) {
		this.organizationDao.makeTransient(organization);
	}

	/** {@inheritDoc} */
	@Override
	public List<Organization> findByPartialName(final String partialName) {
		return this.organizationDao.findByPartialName(partialName);
	}
}