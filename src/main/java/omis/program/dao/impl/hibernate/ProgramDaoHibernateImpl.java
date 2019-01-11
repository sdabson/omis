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
package omis.program.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.program.dao.ProgramDao;
import omis.program.domain.Program;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Hibernate implementation of data access object for programs.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 8, 2015)
 * @since OMIS 3.0
 */
public class ProgramDaoHibernateImpl
		extends GenericHibernateDaoImpl<Program> implements ProgramDao {
	
	/* Query names. */
	
	private static final String
	FIND_OFFERED_BY_SUPERVISORY_ORGANIZATION_QUERY_NAME
		= "findProgramsOfferedBySupervisoryOrganization";
	
	private static final String FIND_OFFERED_BY_LOCATION_QUERY_NAME
		= "findProgramsOfferedByLocation";
	
	private static final String FIND_QUERY_NAME = "findProgram";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findProgramExcluding";
	
	/* Parameter names. */
	
	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_PROGRAM_PARAM_NAME = "excludedProgram";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for
	 * programs.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProgramDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<Program> findOfferedBySupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		@SuppressWarnings("unchecked")
		List<Program> programs = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_OFFERED_BY_SUPERVISORY_ORGANIZATION_QUERY_NAME)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
						supervisoryOrganization)
				.list();
		return programs;
	}

	/** {@inheritDoc} */
	@Override
	public List<Program> findOfferedByLocation(final Location location) {
		@SuppressWarnings("unchecked")
		List<Program> programs = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_OFFERED_BY_LOCATION_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.list();
		return programs;
	}

	/** {@inheritDoc} */
	@Override
	public Program find(String name) {
		Program program = (Program) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return program;
	}

	/** {@inheritDoc} */
	@Override
	public Program findExcluding(String name, Program excludedProgram) {
		Program program = (Program) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_PROGRAM_PARAM_NAME, excludedProgram)
				.uniqueResult();
		return program;
	}
}