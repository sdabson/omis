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

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.OfficerCaseAssignmentNoteDao;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for officer case assignment 
 * note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentNoteDaoHibernateImpl 
		extends GenericHibernateDaoImpl<OfficerCaseAssignmentNote> 
		implements OfficerCaseAssignmentNoteDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findOfficerCaseAssignmentNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findOfficerCaseAssignmentNoteExcluding";
	
	private static final String FIND_BY_OFFICER_CASE_ASSIGNMENT_QUERY_NAME = 
			"findOfficerCaseAssignmentNotesByOfficerCaseAssignment";
	
	/* Parameters. */
	
	private static final String OFFICER_CASE_ASSIGNMENT_PARAM_NAME = 
			"officerCaseAssignment";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAMETER_NAME = "date";
	
	private static final String EXCLUDED_OFFICER_CASE_ASSIGNMENT_NOTE_PARAM_NAME 
			= "excludedOfficerCaseAssignmentNote";	
	
	/** 
	 * Instantiates a hibernate implementation of officer case assignment note 
	 * data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OfficerCaseAssignmentNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignmentNote find(
			final OfficerCaseAssignment officerCaseAssignment, 
			final String description, final Date date) {
		OfficerCaseAssignmentNote officerCaseAssignmentNote = 
				(OfficerCaseAssignmentNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						officerCaseAssignment)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAMETER_NAME, date)
				.uniqueResult();
		return officerCaseAssignmentNote;
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignmentNote findExcluding(
			final OfficerCaseAssignment officerCaseAssignment, 
			final String description, final Date date, 
			final OfficerCaseAssignmentNote excludedNote) {
		OfficerCaseAssignmentNote officerCaseAssignmentNote = 
				(OfficerCaseAssignmentNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME,
						officerCaseAssignment)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAMETER_NAME, date)
				.setParameter(EXCLUDED_OFFICER_CASE_ASSIGNMENT_NOTE_PARAM_NAME, 
						excludedNote)
				.uniqueResult();
		return officerCaseAssignmentNote;
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignmentNote> findByOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment) {
		@SuppressWarnings("unchecked")
		List<OfficerCaseAssignmentNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFICER_CASE_ASSIGNMENT_QUERY_NAME)
				.setParameter(OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						officerCaseAssignment)
				.list();
		return notes;
	}
}