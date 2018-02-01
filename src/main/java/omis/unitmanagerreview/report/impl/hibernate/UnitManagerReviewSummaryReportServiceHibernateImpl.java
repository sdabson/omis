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
package omis.unitmanagerreview.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.unitmanagerreview.report.UnitManagerReviewSummary;
import omis.unitmanagerreview.report.UnitManagerReviewSummaryReportService;

/**
 * Hibernate implementation of the unit manager review summary report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewSummaryReportServiceHibernateImpl 
		implements UnitManagerReviewSummaryReportService {

	/* Queries. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME = 
			"findUnitManagerReviewSummariesByOffender";
	
	/* Parameters.*/ 
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	/* Members. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public UnitManagerReviewSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UnitManagerReviewSummary> 
			findUnitManagerReviewSummariesByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<UnitManagerReviewSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return summaries;
	}
}