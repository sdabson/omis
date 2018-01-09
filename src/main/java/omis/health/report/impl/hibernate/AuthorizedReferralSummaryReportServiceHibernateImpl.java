package omis.health.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.facility.domain.Facility;
import omis.health.report.AuthorizedReferralSummary;
import omis.health.report.AuthorizedReferralSummaryReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for authorized referral summaries.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public class AuthorizedReferralSummaryReportServiceHibernateImpl
		implements AuthorizedReferralSummaryReportService {

	/* Query names. */
	
	private static final String FIND_UNSCHEDULED_BY_FACILITY_QUERY_NAME
		= "findUnscheduledAuthorizedExternalReferralSummariesByFacility";
	
	private static final String FIND_UNSCHEDULED_BY_OFFENDER_QUERY_NAME
	= "findUnscheduledAuthorizedExternalReferralSummariesByOffender";
	
	/* Parameters. */
	
	private static final String FACILITY_PARAM_NAME = "facility";

	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Hibernate implementation of report service for authorized referral
	 * summaries.
	 * 
	 * @param sessionFactory session factory
	 */
	public AuthorizedReferralSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<AuthorizedReferralSummary> findUnscheduledByFacility(
			final Facility facility) {
		@SuppressWarnings("unchecked")
		List<AuthorizedReferralSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_UNSCHEDULED_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<AuthorizedReferralSummary> findUnscheduledByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<AuthorizedReferralSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_UNSCHEDULED_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}
}