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
package omis.placement.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.datatype.DateRange;
import omis.locationterm.report.LocationTermSummary;
import omis.locationterm.report.delegate.LocationTermSummaryDelegate;
import omis.offender.domain.Offender;
import omis.placement.report.AllowedCorrectionalStatusChangeSummary;
import omis.placement.report.LocationTermChangeActionSummary;
import omis.placement.report.OffenderPlacementSummary;
import omis.placement.report.PlacementReportService;
import omis.placement.report.PlacementSummary;
import omis.placement.report.PlacementTermChangeActionSummary;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.report.CorrectionalStatusTermSummary;
import omis.supervision.report.SupervisoryOrganizationTermSummary;

/**
 * Hibernate implementation of report service for placement. 
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.3 (Feb 22, 2019)
 * @since OMIS 3.0
 */
public class PlacementReportServiceHibernateImpl
				implements PlacementReportService {
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Helpers. */
	
	private final LocationTermSummaryDelegate locationTermSummaryDelegate;
		
	/* Query names. */
	
	private static final String
	FIND_CORRECTIONAL_STATUS_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME
		= "findCorrectionalStatusTermSummariesByOffender";
	
	private static final String
	FIND_SUPERVISORY_ORGANIZATION_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME
		= "findSupervisoryOrganizationTermSummariesByOffender";
	
	private static final String
	FIND_CORRECTIONAL_STATUS_TERM_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "findCorrectionalStatusTermForOffenderOnDate";
	
	private static final String FIND_PLACEMENT_SUMMARY_QUERY_NAME
		= "findPlacementSummary";

	private static final String
	FIND_CHANGE_SUMMARIES_ALLOWED_FROM_CORRECTIONAL_STATUS_QUERY_NAME
		= "findAllowedChangeSummariesForCorrectionalStatus";
	
	private static final String
	COUNT_PLACEMENTS_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "countPlacementTermsByOffenderOnDate";
	
	private static final String
	FIND_PLACEMENT_TERM_CHANGE_ACTIONS_BY_SUPERVISORY_ORGANIZATION_CHANGE_QUERY_NAME
		= "findPlacementTermChangeActionForSupervisoryOrganizationChange";
	
	private static final String
	FIND_LOCATION_TERM_CHANGE_ACTION_SUMMARIES_BY_CORRECTIONAL_STATUS_QUERY_NAME
		= "findLocationTermChangeActionSummaryForCorrectionalStatus";
	
	private static final String
	SUMMARIZE_OFFENDER_PLACEMENT_QUERY_NAME = "summarizeOffenderPlacement";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String DATE_PARAM_NAME = "date";

	private static final String CORRECTIONAL_STATUS_PARAM_NAME
		= "correctionalStatus";

	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of report service for placement.
	 * 
	 * @param sessionFactory session factory
	 * @param locationTermSummaryDelegate delegate for location term summaries
	 */
	public PlacementReportServiceHibernateImpl(
			final LocationTermSummaryDelegate locationTermSummaryDelegate,
			final SessionFactory sessionFactory) {
		this.locationTermSummaryDelegate = locationTermSummaryDelegate;
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public PlacementSummary findPlacementSummary(final Offender offender,
			final Date effectiveDate) {
		PlacementSummary summary = (PlacementSummary)
			this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_PLACEMENT_SUMMARY_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.setReadOnly(true)
				.uniqueResult();
		return summary;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CorrectionalStatusTermSummary>
	findCorrectionalStatusTermSummaries(
			final Offender offender, final Date startDate, final Date endDate,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<CorrectionalStatusTermSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CORRECTIONAL_STATUS_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.setReadOnly(true)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganizationTermSummary>
	findSupervisoryOrganizationTermSummaries(
			final Offender offender, final Date startDate, final Date endDate,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganizationTermSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
		FIND_SUPERVISORY_ORGANIZATION_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setTimestamp(START_DATE_PARAM_NAME, startDate)
			.setTimestamp(END_DATE_PARAM_NAME, endDate)
			.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
			.setReadOnly(true)
			.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<LocationTermSummary>
	findLocationTermSummariesDuringSupervisoryOrganizationTerm(
			final SupervisoryOrganizationTerm supervisoryOrganizationTerm,
			final Date effectiveDate) {
		return this.locationTermSummaryDelegate
				.findForOffenderBetweenDates(
						supervisoryOrganizationTerm.getOffender(),
						DateRange.getStartDate(
								supervisoryOrganizationTerm.getDateRange()),
						DateRange.getEndDate(
								supervisoryOrganizationTerm.getDateRange()),
						effectiveDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<AllowedCorrectionalStatusChangeSummary>
	findAllowedCorrectionalStatusChangeSummaries(
			final Offender offender, final Date effectiveDate) {
		CorrectionalStatus correctionalStatus
			= this.findCorrectionalStatusForOffenderOnDate(
					offender, effectiveDate);
		return this.findAllowedChangeSummariesFromCorrectionalStatus(
				correctionalStatus);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AllowedCorrectionalStatusChangeSummary>
	findAllowedChangeSummariesFromCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		@SuppressWarnings("unchecked")
		List<AllowedCorrectionalStatusChangeSummary> summaries
				= this.sessionFactory.getCurrentSession().getNamedQuery(
			FIND_CHANGE_SUMMARIES_ALLOWED_FROM_CORRECTIONAL_STATUS_QUERY_NAME)
					.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
							correctionalStatus)
					.setReadOnly(true)
					.list();
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LocationTermChangeActionSummary>
	findLocationTermChangeActionSummariesForCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		@SuppressWarnings("unchecked")
		List<LocationTermChangeActionSummary> summaries
				= this.sessionFactory.getCurrentSession().getNamedQuery(
FIND_LOCATION_TERM_CHANGE_ACTION_SUMMARIES_BY_CORRECTIONAL_STATUS_QUERY_NAME)                                                                       
					.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
							correctionalStatus)
					.setReadOnly(true)
					.list();
		return summaries;
	}
	
	/* Helper methods */
	
	// Returns correctional status for offender on date
	private CorrectionalStatus findCorrectionalStatusForOffenderOnDate(
			final Offender offender, final Date date) {
		CorrectionalStatusTerm term = (CorrectionalStatusTerm)
					this.sessionFactory
				.getCurrentSession().getNamedQuery(
				FIND_CORRECTIONAL_STATUS_TERM_FOR_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setReadOnly(true)
				.uniqueResult();
		if (term != null) {
			return term.getCorrectionalStatus();
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasActivePlacementTerm(
			final Offender offender, final Date effectiveDate) {
		return (long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(COUNT_PLACEMENTS_BY_OFFENDER_ON_DATE_QUERY_NAME)                        
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.setReadOnly(true)
				.uniqueResult() > 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderPlacementSummary findOffenderPlacementSummaryByOffender(
					final Offender offender, final Date effectiveDate) {
		OffenderPlacementSummary offenderPlacementSummary
			= (OffenderPlacementSummary) this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SUMMARIZE_OFFENDER_PLACEMENT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.uniqueResult();
		return offenderPlacementSummary;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeActionSummary>
	findPlacementTermChangeActionSummariesForSupervisoryOrganizationChange(
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization) {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeActionSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(
			FIND_PLACEMENT_TERM_CHANGE_ACTIONS_BY_SUPERVISORY_ORGANIZATION_CHANGE_QUERY_NAME)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
						correctionalStatus)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
						supervisoryOrganization)
				.setReadOnly(true)
				.list();
		return summaries;
	}
}