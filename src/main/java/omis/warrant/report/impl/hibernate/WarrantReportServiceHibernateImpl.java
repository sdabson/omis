package omis.warrant.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.warrant.report.WarrantReportService;
import omis.warrant.report.WarrantSummary;

/**
 * WarrantReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReportServiceHibernateImpl implements WarrantReportService {
	
	private static final String
		FIND_UNCLEARED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findUnclearedWarrantSummariesByOffender";
	
	private static final String
		FIND_RELEASED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findReleasedWarrantSummariesByOffender";
	
	private static final String
		FIND_CANCELLED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findCancelledWarrantSummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public WarrantReportServiceHibernateImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**{@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<WarrantSummary> findByOffender(final Offender offender) {
		
		List<WarrantSummary> warrantSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
						FIND_UNCLEARED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		warrantSummaries.addAll(this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
						FIND_RELEASED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		
		warrantSummaries.addAll(this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
						FIND_CANCELLED_WARRANT_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		
		return warrantSummaries;
	}

}
