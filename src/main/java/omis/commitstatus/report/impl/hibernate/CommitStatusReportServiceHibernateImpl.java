package omis.commitstatus.report.impl.hibernate;

import java.util.List;
import java.util.ArrayList;

import omis.commitstatus.report.CommitStatusReportService;
import omis.commitstatus.report.CommitStatusTermSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of commit status report service.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 5, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusReportServiceHibernateImpl
		implements CommitStatusReportService {

	/* Queries */
	private static final String FIND_COMMIT_STATUS_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findCommitStatusSummaryByOffender";
	
	/* Parameters */
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
		
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of commit status history 
	 * report service
	 * @param sessionFactory session factory
	 */

	public CommitStatusReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
			this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */
	@Override 
	public List<CommitStatusTermSummary> summarizeByOffender(final Offender offender){
		List<CommitStatusTermSummary> summaries
			= new ArrayList<CommitStatusTermSummary>();
		@SuppressWarnings("unchecked")
		List<CommitStatusTermSummary> internalSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_COMMIT_STATUS_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		summaries.addAll(internalSummaries); 
		return summaries;
	}
}