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
package omis.response.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.response.domain.Grid;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;
import omis.response.report.ResponseSummary;
import omis.response.report.ResponseSummaryReportService;

/**
 * Hibernate implementation of response summary report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 5, 2019)
 * @since OMIS 3.0
 */
public class ResponseSummaryReportServiceHibernateImpl 
		implements ResponseSummaryReportService {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME = 
			"findResponseSummaries";
	
	private static final String FIND_BY_QUERY_NAME = 
			"findResponseSummariesBy";
	
	/* Parameters.*/ 
	
	private static final String DESCRIPTION_PARAMETER_NAME = "description";
	
	private static final String GRID_PARAMETER_NAME = "grid";
	
	private static final String CATEGORY_PARAMETER_NAME = "category";
	
	private static final String LEVEL_PARAMETER_NAME = "level";
	
	/* Members. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public ResponseSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ResponseSummary> findResponseSummaries() {
		@SuppressWarnings("unchecked")
		List<ResponseSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ResponseSummary> findResponseSummaries(final String description,
			final Grid grid, final ResponseCategory category, 
			final ResponseLevel level) {
		@SuppressWarnings("unchecked")
		List<ResponseSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAMETER_NAME, description)
				.setParameter(GRID_PARAMETER_NAME, grid)
				.setParameter(CATEGORY_PARAMETER_NAME, category)
				.setParameter(LEVEL_PARAMETER_NAME, level)
				.list();
		return summaries;
	}
}