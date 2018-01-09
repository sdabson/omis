package omis.workassignment.report.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.workassignment.report.WorkAssignmentReportService;
import omis.workassignment.report.WorkAssignmentSummary;

/**
 * Hibernate implementation of work assignment report service.
 *
 * @author Yidong Li
 * @version 0.0.1 (Sept. 1, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentReportServiceHibernateImpl
		implements WorkAssignmentReportService {

	/* Query names. */
	private static final String FIND_WORK_ASSIGNMENT_BY_OFFENDER_QUERY_NAME 
		= "findWorkAssignmentByOffender";

	/* Parameter names. */
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	/* Session factories. */
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of work assignment service.
	 * 
	 * @param sessionFactory session factory
	 */
	public WorkAssignmentReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentSummary> findByOffender(Offender offender) {
		List<WorkAssignmentSummary> summaries
		= new ArrayList<WorkAssignmentSummary>();
		@SuppressWarnings("unchecked")
		List<WorkAssignmentSummary> workAssignments = this.sessionFactory.getCurrentSession()
			.getNamedQuery(FIND_WORK_ASSIGNMENT_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		summaries.addAll(workAssignments);
		return summaries;
	}
}