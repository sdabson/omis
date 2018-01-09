package omis.stg.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.stg.report.SecurityThreatGroupAffiliationReportService;
import omis.stg.report.SecurityThreatGroupAffiliationSummary;

/**
 * Hibernate implementation of report service for security threat group
 * affiliations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 16, 2015)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationReportServiceHibernateImpl
		implements SecurityThreatGroupAffiliationReportService {

	/* Queries. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME
		= "summarizeSecurityThreatGroupAffiliationsByOffender";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Resources. */

	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for security
	 * threat group.
	 * 
	 * @param sessionFactory session factory
	 */
	public SecurityThreatGroupAffiliationReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupAffiliationSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupAffiliationSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}
}