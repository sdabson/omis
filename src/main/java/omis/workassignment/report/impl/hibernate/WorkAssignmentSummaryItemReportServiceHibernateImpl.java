package omis.workassignment.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.workassignment.report.WorkAssignmentSummary;
import omis.workassignment.report.WorkAssignmentSummaryItemReportService;

/**
 * Work Assignment Summary Item Report Service Hibernate Implementation.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 25, 2017)
 *@since OMIS 3.0
 *
 */
public class WorkAssignmentSummaryItemReportServiceHibernateImpl
		implements WorkAssignmentSummaryItemReportService {
	
	private static final String FIND_WORK_ASSIGNMENT_SUMMARY_QUERY_NAME =
			"findWorkAssignmentByOffender";
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor for WorkAssignmentSummaryItemReportServiceHibernateImpl.
	 * 
	 * @param sessionFactory - Session Factory
	 */
	public WorkAssignmentSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**{@inheritDoc} */
	@Override
	public WorkAssignmentSummary findWorkAssignmentSummaryByOffender(
			final Offender offender) {
		WorkAssignmentSummary summary = (WorkAssignmentSummary)
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_SUMMARY_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.setMaxResults(1)
				.uniqueResult();
				
		return summary;
	}

}
