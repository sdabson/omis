package omis.task.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.task.report.TaskReportService;
import omis.task.report.TaskSummary;

/**
 * Hibernate implementation of report service for tasks.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskReportServiceHibernateImpl
		implements TaskReportService {
	
	/* Queries. */
	
	private static final String SUMMARIZE_BY_SOURCE_ACCOUNT_USERNAME_QUERY_NAME
		= "summarizeTasksBySourceAccountUsername";
	
	/* Parameters. */
	
	private static final String USERNAME_PARAM_NAME = "username";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for tasks.
	 * 
	 * @param sessionFactory session factory
	 */
	public TaskReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<TaskSummary> summarizeBySourceAccountUsername(final String username) {
		@SuppressWarnings("unchecked")
		List<TaskSummary> taskSummaries
			= this.sessionFactory.getCurrentSession().getNamedQuery(
					SUMMARIZE_BY_SOURCE_ACCOUNT_USERNAME_QUERY_NAME)
				.setParameter(USERNAME_PARAM_NAME, username)
				.list();
		return taskSummaries;
	}
}