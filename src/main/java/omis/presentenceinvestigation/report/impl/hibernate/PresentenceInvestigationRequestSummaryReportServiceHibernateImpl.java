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
package omis.presentenceinvestigation.report.impl.hibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummary;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummaryReportService;
import omis.user.domain.UserAccount;

/**
 * Hibernate implementation of presentence investigation request summary report 
 * service. 
 * 
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.6 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public class 
	PresentenceInvestigationRequestSummaryReportServiceHibernateImpl 
	implements PresentenceInvestigationRequestSummaryReportService {
	
	/* Query names. */
	
	private static final String FIND_BY_USER_QUERY_NAME = 
			"findSubmittedPresentenceInvestigationRequestSummariesByUser";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findPresentenceInvestigationRequestSummariesByOffender";

	private static final String FIND_BY_REQUEST_QUERY_NAME 
		= "findPresentenceInvestigationRequestSummary";

	private static final String FIND_UNSUBMITTED_BY_USER_QUERY_NAME = 
			"findUnsubmittedPresentenceInvestigationRequestSummariesByUser";
	
	private static final String FIND_DOCKET_VALUES_BY_REQUEST_QUERY_NAME = 
			"findDocketValuesByPresentenceInvestigationRequest";

	private static final String FIND_UNSUBMITTED_BY_NAME_QUERY_NAME = 
			"findUnsubmittedPresentenceInvestigationRequestSummariesByName";

	private static final String FIND_SUBMITTED_BY_NAME_QUERY_NAME = 
			"findSubmittedPresentenceInvestigationRequestSummariesByName";
	
	/* Parameter names. */
	
	private static final String USER_PARAM_NAME = "assignedUser";

	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String REQUEST_PARAM_NAME = 
			"presentenceInvestigationRequest";
	
	private static final String REQUEST_ID_PARAM_NAME = 
			"presentenceInvestigationRequestId";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	
	private static final String FIRST_NAME_PARAM_NAME = "firstName";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor. 
	 * @param sessionFactory - session factory. */
	public PresentenceInvestigationRequestSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<PresentenceInvestigationRequestSummary> 
		findSubmittedPresentenceInvestigationRequestSummariesByUser(
			final UserAccount user, final Date startDate, final Date endDate) {
		List<PresentenceInvestigationRequestSummary> summaries = this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_BY_USER_QUERY_NAME)
				.setParameter(USER_PARAM_NAME, user)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setReadOnly(true)
				.list();
		for (PresentenceInvestigationRequestSummary summary : summaries) {
			summary.getDocketValues().addAll(this.findDocketValuesByRequest(
					summary.getPresentenceInvestigationRequestId()));
		}
		return summaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestSummary> 
		findPresentenceInvestigationRequestSummariesByOffender(
			final Person offender) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationRequestSummary> summaries = this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setReadOnly(true)
				.list();
		for (PresentenceInvestigationRequestSummary summary : summaries) {
			summary.getDocketValues().addAll(this.findDocketValuesByRequest(
					summary.getPresentenceInvestigationRequestId()));
		}
		return summaries;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestSummary summarize(
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest) {
		PresentenceInvestigationRequestSummary summary = 
				(PresentenceInvestigationRequestSummary) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_REQUEST_QUERY_NAME)
				.setParameter(REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.setReadOnly(true)
				.uniqueResult();
		summary.getDocketValues().addAll(this.findDocketValuesByRequest(
				summary.getPresentenceInvestigationRequestId()));
		return summary;
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestSummary> 
			findUnsubmittedPresentenceInvestigationRequestSummariesByUser(
					final UserAccount user, final Date startDate,
					final Date endDate) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationRequestSummary> summaries = this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_UNSUBMITTED_BY_USER_QUERY_NAME)
				.setParameter(USER_PARAM_NAME, user)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setReadOnly(true)
				.list();
		for (PresentenceInvestigationRequestSummary summary : summaries) {
			summary.getDocketValues().addAll(this.findDocketValuesByRequest(
					summary.getPresentenceInvestigationRequestId()));
		}
		return summaries;
	}
	
	// Returns a list of docket values for the specified presentence 
	// investigation request id
	private List<String> findDocketValuesByRequest(final Long requestId) {
		@SuppressWarnings("unchecked")
		List<String> docketValues = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_DOCKET_VALUES_BY_REQUEST_QUERY_NAME)
				.setParameter(REQUEST_ID_PARAM_NAME, requestId)
				.setReadOnly(true)
				.list();
		return docketValues;
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestSummary>
		findUnsubmittedPresentenceInvestigationRequestSummariesByName(
			final String firstName, final String lastName,
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationRequestSummary> summaries = this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_UNSUBMITTED_BY_NAME_QUERY_NAME)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setReadOnly(true)
				.list();
		for (PresentenceInvestigationRequestSummary summary : summaries) {
			summary.getDocketValues().addAll(this.findDocketValuesByRequest(
					summary.getPresentenceInvestigationRequestId()));
		}
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestSummary>
		findSubmittedPresentenceInvestigationRequestSummariesByName(
			final String firstName, final String lastName,
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationRequestSummary> summaries = this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_SUBMITTED_BY_NAME_QUERY_NAME)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setReadOnly(true)
				.list();
		for (PresentenceInvestigationRequestSummary summary : summaries) {
			summary.getDocketValues().addAll(this.findDocketValuesByRequest(
					summary.getPresentenceInvestigationRequestId()));
		}
		return summaries;
	}
}