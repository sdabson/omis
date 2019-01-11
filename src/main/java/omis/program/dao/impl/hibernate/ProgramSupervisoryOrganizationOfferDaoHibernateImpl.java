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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.program.dao.ProgramSupervisoryOrganizationOfferDao;
import omis.program.domain.Program;
import omis.program.domain.ProgramSupervisoryOrganizationOffer;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Hibernate implementation of data access object offering program by
 * supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramSupervisoryOrganizationOfferDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProgramSupervisoryOrganizationOffer>
		implements ProgramSupervisoryOrganizationOfferDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME
		= "findProgramSupervisoryOrganizationOffer";
	
	/* Parameter names. */
	
	private static final String PROGRAM_PARAM_NAME = "program";
	
	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object offering
	 * program by supervisory organization.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProgramSupervisoryOrganizationOfferDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ProgramSupervisoryOrganizationOffer find(final Program program,
			final SupervisoryOrganization supervisoryOrganization) {
		ProgramSupervisoryOrganizationOffer offer
			= (ProgramSupervisoryOrganizationOffer)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setParameter(PROGRAM_PARAM_NAME, program)
					.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
							supervisoryOrganization)
					.uniqueResult();
		return offer;
	}
}