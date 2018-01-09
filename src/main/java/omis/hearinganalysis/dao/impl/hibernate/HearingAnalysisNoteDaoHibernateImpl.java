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
package omis.hearinganalysis.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearinganalysis.dao.HearingAnalysisNoteDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisNote;

/**
 * Hibernate implementation of the hearing analysis note data access object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public class HearingAnalysisNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<HearingAnalysisNote> 
	implements HearingAnalysisNoteDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findHearingAnalysisNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findHearingAnalysisNoteExcluding";
	
	private static final String FIND_BY_HEARING_ANALYSIS_QUERY_NAME = 
			"findHearingAnalysisNoteByHearingAnalysis";
	
	/* Parameters. */
	
	private static final String HEARING_ANALYSIS_PARAM_NAME = "hearingAnalysis";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EXCLUDED_NOTE_PARAM_NAME = "excludedNote";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  hearing analysis note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HearingAnalysisNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisNote find(final HearingAnalysis hearingAnalysis, 
			final Date date, final String description) {
		HearingAnalysisNote hearingAnalysisNote = (HearingAnalysisNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		return hearingAnalysisNote;
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisNote findExcluding(
			final HearingAnalysis hearingAnalysis, final Date date, 
			final String description, final HearingAnalysisNote excludedNote) {
		HearingAnalysisNote hearingAnalysisNote = (HearingAnalysisNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(EXCLUDED_NOTE_PARAM_NAME, excludedNote)
				.uniqueResult();
		return hearingAnalysisNote;
	}

	/** {@inheritDoc} */
	@Override
	public List<HearingAnalysisNote> findByHearingAnalysis(
			final HearingAnalysis hearingAnalysis) {
		@SuppressWarnings("unchecked")
		List<HearingAnalysisNote> notes = (List<HearingAnalysisNote>) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_HEARING_ANALYSIS_QUERY_NAME)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.list();
		return notes;
	}

}
