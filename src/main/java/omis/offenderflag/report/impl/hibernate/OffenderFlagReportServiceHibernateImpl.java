package omis.offenderflag.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offenderflag.report.OffenderFlagReportService;
import omis.offenderflag.report.OffenderFlagSummary;

/**
 * OffenderFlagReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 15, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderFlagReportServiceHibernateImpl
	implements OffenderFlagReportService {
	
	private static final String
		FIND_OFFENDER_FLAG_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findOffenderFlagSummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public OffenderFlagReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<OffenderFlagSummary> findOffenderFlagSummariesByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderFlagSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
						FIND_OFFENDER_FLAG_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return summaries;
	}

}
