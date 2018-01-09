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
package omis.paroleeligibility.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleeligibility.dao.ParoleEligibilityNoteDao;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.domain.ParoleEligibilityNote;

/**
 * Hibernate implementation of data access object for parole eligibility note.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ParoleEligibilityNote> 
	implements ParoleEligibilityNoteDao {
	
	/* Query names */
	
	private static final String FIND_NOTES_BY_PAROLE_ELIGIBILITY_QUERY_NAME 
		= "findParoleEligibilityNotesByParoleEligibility";
	
	private static final String FIND_NOTE_QUERY_NAME 
		= "findParoleEligibilityNote";
	
	private static final String FIND_PAROLE_ELIGIBILITY_NOTE_EXCLUDING_QUERY_NAME 
		= "findParoleEligibilityNoteExcluding";
	
	/* Parameter names */
	
	private static final String PAROLE_ELIGIBILITY_PARAM_NAME 
		= "eligibility";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String NOTE_EXCLUDING_PARAM_NAME = "excludedNote";
	
	/* Constructor */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * parole eligibility note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleEligibilityNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public List<ParoleEligibilityNote> 
		findParoleEligibilityNotesByParoleEligibility(
			final ParoleEligibility paroleEligibility) {
		@SuppressWarnings("unchecked")
		List<ParoleEligibilityNote> notes 
		= this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_NOTES_BY_PAROLE_ELIGIBILITY_QUERY_NAME)
			.setParameter(PAROLE_ELIGIBILITY_PARAM_NAME, paroleEligibility)
			.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibilityNote findNote(ParoleEligibility paroleEligibility, 
			String description, Date date) {
		ParoleEligibilityNote note = (ParoleEligibilityNote) 
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_NOTE_QUERY_NAME)
			.setParameter(PAROLE_ELIGIBILITY_PARAM_NAME, paroleEligibility)
			.setParameter(DESCRIPTION_PARAM_NAME, description)
			.setParameter(DATE_PARAM_NAME, date)
			.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibilityNote findNoteExcluding(
			ParoleEligibilityNote excludedNote,
			ParoleEligibility paroleEligibility, 
			String description, Date date) {
		ParoleEligibilityNote note = (ParoleEligibilityNote)
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PAROLE_ELIGIBILITY_NOTE_EXCLUDING_QUERY_NAME)
			.setParameter(NOTE_EXCLUDING_PARAM_NAME, excludedNote)
			.setParameter(PAROLE_ELIGIBILITY_PARAM_NAME, paroleEligibility)
			.setParameter(DESCRIPTION_PARAM_NAME, description)
			.setParameter(DATE_PARAM_NAME, date)
			.uniqueResult();
		return note;
	}

}
