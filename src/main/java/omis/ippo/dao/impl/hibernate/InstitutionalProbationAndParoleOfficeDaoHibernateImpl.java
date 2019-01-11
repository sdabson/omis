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
package omis.ippo.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.ippo.dao.InstitutionalProbationAndParoleOfficeDao;
import omis.ippo.domain.InstitutionalProbationAndParoleOffice;
import omis.location.domain.Location;

/**
 * Hibernate implementation of data access object for institutional probation 
 * and parole office.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (September 25, 2018)
 * @since OMIS 3.0
 */

public class InstitutionalProbationAndParoleOfficeDaoHibernateImpl 
		extends GenericHibernateDaoImpl<InstitutionalProbationAndParoleOffice>
		implements InstitutionalProbationAndParoleOfficeDao {
	
	/* Queries. */
	private final static String 
		FIND_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_QUERY_NAME 
			= "findInstitutionalProbationAndParoleOffice";
	
	private final static String
		FIND_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_EXCLUDING_QUERY_NAME
			= "findInstitutionalProbationAndParoleOfficeExcluding";
	
	/* Parameters. */
	private final static String LOCATION_PARAM_NAME = "location";
	
	private final static String NAME_PARAM_NAME = "name";
	
	private final static String TELEPHONE_NUMBER_PARAM_NAME = "telephoneNumber";
	
	private final static String 
		EXCLUDED_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_PARAM_NAME 
			= "excludedInstitutionalProbationAndParoleOffice";
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * institutional probation and parole office.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InstitutionalProbationAndParoleOfficeDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public InstitutionalProbationAndParoleOffice find(Location location,
			String name, Long telephoneNumber) {
			InstitutionalProbationAndParoleOffice 
			institutionalProbationAndParoleOffice 
			= (InstitutionalProbationAndParoleOffice) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
				.uniqueResult();
		return institutionalProbationAndParoleOffice;
	}
	
	/** {@inheritDoc} */
	@Override
	public InstitutionalProbationAndParoleOffice findExcluding(Location location,
			String name, Long telephoneNumber, 
			InstitutionalProbationAndParoleOffice 
				excludedInstitutionalProbationAndParoleOffice) {
			InstitutionalProbationAndParoleOffice 
			institutionalProbationAndParoleOffice 
			= (InstitutionalProbationAndParoleOffice) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_EXCLUDING_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
				.setParameter(
			EXCLUDED_INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE_PARAM_NAME, 
					excludedInstitutionalProbationAndParoleOffice)
				.uniqueResult();
		return institutionalProbationAndParoleOffice;
	}

}
