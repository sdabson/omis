package omis.health.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.facility.domain.Facility;
import omis.health.report.ExternalReferralRequestReportService;
import omis.health.report.ExternalReferralRequestSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for external referral requests. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 1, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralRequestReportServiceHibernateImpl
		implements ExternalReferralRequestReportService {

	/* Queries. */
	
	private static final String FIND_BY_FACILITY_QUERY_NAME
		= "findExternalReferralRequestSummariesByFacility";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findExternalReferralRequestSummariesByOffender";
	
	/* Parameter names. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Hibernate implementation of report service for external referral
	 * requests.
	 * 
	 * @param sessionFactory session factory
	 */
	public ExternalReferralRequestReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralRequestSummary> findByFacility(
			final Facility facility) {
		@SuppressWarnings("unchecked")
		List<ExternalReferralRequestSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralRequestSummary> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ExternalReferralRequestSummary> summaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return summaries;
	}
}