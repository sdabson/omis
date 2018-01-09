package omis.custody.report.impl.hibernate;

import java.util.List;

import omis.custody.report.CustodyReportService;
import omis.custody.report.CustodySummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate Implementation of
 * Report service for custody.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 27 2013)
 * @since OMIS 3.0
 */
public class CustodyReportServiceHibernateImpl
		implements CustodyReportService {
	
	private static final String OFFENDER_PARAM = "offender";
	
	private static final String FIND_CUSTODY_SUMMARIES_QUERY_NAME
	 	= "summarizeCustodyByOffender";
	
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of custody report service with the specified
	 * session factory.
	 * @param sessionFactory session factory
	 */
	public CustodyReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public final List<CustodySummary> findReviewSummaries(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CustodySummary> custodySummaries = (List<CustodySummary>) 
				this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_SUMMARIES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM, offender)
				.list();
		
		return custodySummaries;
	}

}