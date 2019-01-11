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
package omis.caseload.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.caseload.dao.InterstateCompactAssignmentDao;
import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for interstate compact 
 * assignment.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactAssignmentDaoHibernateImpl 
		extends GenericHibernateDaoImpl<InterstateCompactAssignment> 
		implements InterstateCompactAssignmentDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findInterstateCompactAssignment";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findInterstateCompactAssignmentExcluding";
	
	private static final String FIND_BY_OFFICER_CASE_ASSIGNMENT_QUERY_NAME = 
			"findInterstateCompactAssignmentByOfficerCaseAssignment";
	
	/* Parameters. */
	
	private static final String OFFICER_CASE_ASSIGNMENT_PARAM_NAME = 
			"officerCaseAssignment";
	
	private static final String 
			EXCLUDED_INTERSTATE_COMPACT_ASSIGNMENT_PARAM_NAME = 
					"excludedInterstateCompactAssignment";	
	
	/** 
	 * Instantiates a hibernate implementation of interstate compact assignment 
	 * data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InterstateCompactAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment find(
			final OfficerCaseAssignment officerCaseAssignment) {
		InterstateCompactAssignment interstateCompactAssignment = 
				(InterstateCompactAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						officerCaseAssignment)
				.uniqueResult();
		return interstateCompactAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment findExcluding(
			final OfficerCaseAssignment officerCaseAssignment,
			final InterstateCompactAssignment 
					excludedInterstateCompactAssignment) {
		InterstateCompactAssignment interstateCompactAssignment = 
				(InterstateCompactAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						officerCaseAssignment)
				.setParameter(EXCLUDED_INTERSTATE_COMPACT_ASSIGNMENT_PARAM_NAME, 
						excludedInterstateCompactAssignment)
				.uniqueResult();
		return interstateCompactAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment findByOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment) {
		InterstateCompactAssignment interstateCompactAssignment = 
				(InterstateCompactAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFICER_CASE_ASSIGNMENT_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						officerCaseAssignment)
				.uniqueResult();
		return interstateCompactAssignment;
	}
}