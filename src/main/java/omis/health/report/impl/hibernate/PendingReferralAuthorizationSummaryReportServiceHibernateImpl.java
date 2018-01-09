package omis.health.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.report.PendingReferralAuthorizationSummary;
import omis.health.report.PendingReferralAuthorizationSummaryReportService;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for summaries of pending referral
 * authorizations. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 3, 2014)
 * @since OMIS 3.0
 */
public class PendingReferralAuthorizationSummaryReportServiceHibernateImpl
		implements PendingReferralAuthorizationSummaryReportService {

	/* Query names. */
	
	private static final String
	FIND_PENDING_EXTERNAL_REFERRAL_AUTHORIZATIONS_BY_FACILITY_QUERY_NAME
		= "findPendingExternalReferralAuthorizationsByFacility";

	private static final String
	FIND_PENDING_EXTERNAL_REFERRAL_AUTHORIZATIONS_BY_OFFENDER_QUERY_NAME
		= "findPendingExternalReferralAuthorizationsByOffender";
	
	/* Parameter names. */
	
	private static final String FACILITY_PARAM_NAME = "facility";

	private static final String OFFENDER_PARAM_NAME = "offender";

	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of report service for summaries
	 * of pending referral authorizations
	 * 
	 * @param sessionFactory session factory
	 */
	public PendingReferralAuthorizationSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Implementation of methods. */
	
	/** {@inheritDoc} */
	@Override
	public List<PendingReferralAuthorizationSummary> findByFacility(
			final Facility facility) {
		List<PendingReferralAuthorizationSummary> referrals
			= new ArrayList<PendingReferralAuthorizationSummary>();
		@SuppressWarnings("unchecked")
		List<PendingReferralAuthorizationSummary> externalReferrals
		= this.sessionFactory.getCurrentSession().getNamedQuery(
		FIND_PENDING_EXTERNAL_REFERRAL_AUTHORIZATIONS_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		referrals.addAll(externalReferrals);
		Collections.sort(referrals);
		return referrals;
	}

	@Override
	public List<PendingReferralAuthorizationSummary> findByOffender(
			final Offender offender) {
		List<PendingReferralAuthorizationSummary> referrals
		= new ArrayList<PendingReferralAuthorizationSummary>();
		@SuppressWarnings("unchecked")
		List<PendingReferralAuthorizationSummary> externalReferrals
			= this.sessionFactory.getCurrentSession().getNamedQuery(
		FIND_PENDING_EXTERNAL_REFERRAL_AUTHORIZATIONS_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender).list();
		referrals.addAll(externalReferrals);
		Collections.sort(referrals);
		return referrals;
	}
}