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
package omis.chronologicalnote.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.chronologicalnote.domain.ChronologicalNote;
import omis.chronologicalnote.domain.ChronologicalNoteCategory;
import omis.chronologicalnote.report.ChronologicalNoteReportService;
import omis.chronologicalnote.report.ChronologicalNoteSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for chronological notes.
 *
 * @author Yidong Li
 * @version 0.0.1 (Jan 30, 2018)
 * @since OMIS 3.0
 */
public class ChronologicalNoteReportServiceHibernateImpl
		implements ChronologicalNoteReportService {
	/* Query names. */
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findChronologicalNoteSummaryByOffender";
	private static final String
		FIND_BY_OFFENDER_AND_CATEGORIES_QUERY_NAME
		= "findChronologicalNoteSummaryByOffenderAndCategories";
	private static final String FIND_CATEGORIES_QUERY_NAME = "findCategories";
	private static final String FIND_CATEGORY_NAME_QUERY_NAME
		= "findCategoryNames";
	
	/* Parameter names. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String CATEGORIES_PARAM_NAME = "categories";
	private static final String NOTE_PARAM_NAME = "note";
	
	/* Resources. */
	private final SessionFactory sessionFactory;

	/* Constructors. */
	/**
	 * Instantiates an implementation of report service.
	 * 
	 * @param sessionFactory session factory
	 */
	public ChronologicalNoteReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ChronologicalNoteSummary> findByOffender(
		final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ChronologicalNoteSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ChronologicalNoteSummary> findByOffenderAndCategories(
		final Offender offender, 
		final List<ChronologicalNoteCategory> categories) {
		@SuppressWarnings("unchecked")
		List<ChronologicalNoteSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_BY_OFFENDER_AND_CATEGORIES_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(CATEGORIES_PARAM_NAME, categories)
			.list();
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ChronologicalNoteCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<ChronologicalNoteCategory> categories = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_CATEGORIES_QUERY_NAME)
			.list();
		return categories;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<String> findCategoryNamesByNote(final ChronologicalNote note) {
		@SuppressWarnings("unchecked")
		List<String> categoryNames = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_CATEGORY_NAME_QUERY_NAME)
			.setParameter(NOTE_PARAM_NAME, note)
			.list();
		return categoryNames;
	}
}