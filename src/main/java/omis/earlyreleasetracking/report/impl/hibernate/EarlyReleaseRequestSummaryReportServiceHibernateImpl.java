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
package omis.earlyreleasetracking.report.impl.hibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.earlyreleasetracking.report.EarlyReleaseRequestSummary;
import omis.earlyreleasetracking.report.EarlyReleaseRequestSummaryReportService;
import omis.offender.domain.Offender;

/**
 * Early Release Request Summary Report Service Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestSummaryReportServiceHibernateImpl
		implements EarlyReleaseRequestSummaryReportService {
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME =
			"findEarlyReleaseRequestSummariesByOffender";
	
	private static final String FIND_BY_DATES_AND_STATUS_QUERY_NAME =
			"findEarlyReleaseRequestSummariesByDatesWithStatus";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String REQUEST_START_DATE_PARAM_NAME =
			"requestStartDate";

	private static final String REQUEST_END_DATE_PARAM_NAME =
			"requestEndDate";
	
	private static final String REQUEST_STATUS_PARAM_NAME = "requestStatus";

	private static final String ELGIIBILITY_START_DATE_PARAM_NAME =
			"eligibilityStartDate";

	private static final String ELIGIBILITY_END_DATE_PARAM_NAME =
			"eligibilityEndDate";

	private static final String REQUEST_DATE_PARAM_NAME =
			"requestDate";

	private static final String ELIGIBILITY_DATE_PARAM_NAME =
			"eligibilityDate";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String NO_RESPONSE_PARAM_NAME = "noResponse";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor for EarlyReleaseRequestSummaryReportServiceHibernateImpl.
	 * 
	 * @param sessionFactory Session Factory
	 */
	public EarlyReleaseRequestSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestSummary> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestSummary> findByDatesWithStatus(
			final Date requestStartDate, final Date requestEndDate,
			final Date requestDate, final Date eligibilityStartDate,
			final Date eligibilityEndDate, final Date eligibilityDate,
			final EarlyReleaseStatusCategory earlyReleaseStatusCategory) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DATES_AND_STATUS_QUERY_NAME)
				.setTimestamp(REQUEST_START_DATE_PARAM_NAME, requestStartDate)
				.setTimestamp(REQUEST_END_DATE_PARAM_NAME, requestEndDate)
				.setParameter(REQUEST_STATUS_PARAM_NAME,
						earlyReleaseStatusCategory)
				.setBoolean(NO_RESPONSE_PARAM_NAME, false)
				.setTimestamp(ELGIIBILITY_START_DATE_PARAM_NAME,
						eligibilityStartDate)
				.setTimestamp(ELIGIBILITY_END_DATE_PARAM_NAME,
						eligibilityEndDate)
				.setTimestamp(ELIGIBILITY_DATE_PARAM_NAME, eligibilityDate)
				.setTimestamp(REQUEST_DATE_PARAM_NAME, requestDate)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, new Date())
				.list();
		
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestSummary> findByDatesWithStatus(
			final Date requestStartDate, final Date requestEndDate,
			final Date requestDate, final Date eligibilityStartDate,
			final Date eligibilityEndDate, final Date eligibilityDate) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DATES_AND_STATUS_QUERY_NAME)
				.setTimestamp(REQUEST_START_DATE_PARAM_NAME, requestStartDate)
				.setTimestamp(REQUEST_END_DATE_PARAM_NAME, requestEndDate)
				.setParameter(REQUEST_STATUS_PARAM_NAME, null)
				.setBoolean(NO_RESPONSE_PARAM_NAME, true)
				.setTimestamp(ELGIIBILITY_START_DATE_PARAM_NAME,
						eligibilityStartDate)
				.setTimestamp(ELIGIBILITY_END_DATE_PARAM_NAME,
						eligibilityEndDate)
				.setTimestamp(ELIGIBILITY_DATE_PARAM_NAME, eligibilityDate)
				.setTimestamp(REQUEST_DATE_PARAM_NAME, requestDate)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, new Date())
				.list();
		
		return summaries;
	}
}
