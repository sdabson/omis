/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.MedicalFacilityDao;
import omis.health.domain.MedicalFacility;
import omis.organization.domain.Organization;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for medical facilities.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class MedicalFacilityDaoHibernateImpl
		extends GenericHibernateDaoImpl<MedicalFacility>
		implements MedicalFacilityDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findMedicalFacilities";
	
	private static final String FIND_HOSPITALS_QUERY_NAME
		= "findMedicalFacilitiesWhereHospitals";

	private static final String FIND_HOSPITALS_BY_ORGANIZATION_QUERY_NAME
		= "findMedicalFacilitiesByOrganizationWhereHospitals";
	
	private static final String FIND_FACILITY_BY_NAME_QUERY_NAME
		= "findMedicalFacilityByName";
	/* Parameter names. */
	
	private static final String ORGANIZATION_PARAM_NAME = "organization";
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * medical facilities.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MedicalFacilityDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findAll() {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findHospitals() {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_HOSPITALS_QUERY_NAME)
			.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findHospitalsByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_HOSPITALS_BY_ORGANIZATION_QUERY_NAME)
			.setParameter(ORGANIZATION_PARAM_NAME, organization)
			.list();
		return facilities;
	}
	
	/** {@inheritDoc} */
	@Override
	public MedicalFacility findMedicalFacilityByName(final String name)
	{	MedicalFacility facility = (MedicalFacility) this.getSessionFactory()
		.getCurrentSession().getNamedQuery(FIND_FACILITY_BY_NAME_QUERY_NAME)
		.setParameter(NAME_PARAM_NAME, name)
		.setReadOnly(true)
		.uniqueResult();
		return facility;
	}
}