package omis.stg.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.report.SecurityThreatGroupActivityInvolvementSummary;
import omis.stg.report.SecurityThreatGroupActivityReportService;
import omis.stg.report.SecurityThreatGroupActivitySummary;

/**
 * Hibernate implementation of report service for security threat group
 * activities.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Dec 6, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityReportServiceHibernateImpl
		implements SecurityThreatGroupActivityReportService {
	
	/* Queries. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME 
		= "summarizeSecurityThreatGroupActivitiesByOffender";
	
	private static final String SUMMARIZE_INVOLVEMENT_QUERY_NAME 
		= "summarizeSecurityThreatGroupActivityInvolvementByActivity";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String ACTIVITY_PARAM_NAME = "activity";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for security
	 * threat group activities.
	 * 
	 * @param sessionFactory session factory
	 */
	public SecurityThreatGroupActivityReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivitySummary> summarizeByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivitySummary> oSummary 
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return oSummary;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityInvolvementSummary> summarizeInvolvement(
			final SecurityThreatGroupActivity activity) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivityInvolvementSummary> iSummary 
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_INVOLVEMENT_QUERY_NAME)
				.setParameter(ACTIVITY_PARAM_NAME, activity)
				.list();
		return iSummary;
	}
	
}
