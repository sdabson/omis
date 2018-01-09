package omis.user.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.user.report.UserReportService;
import omis.user.report.UserSummary;

/**
 * Hibernate implementation of report service for users.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 7, 2015)
 * @since OMIS 3.0
 */
public class UserReportServiceHibernateImpl
		implements UserReportService {
	
	/* Query names. */
	
	private static final String SUMMARIZE_BY_NAME_QUERY_NAME
		= "summarizeUsersByName";
	
	private static final String SUMMARIZE_BY_USERNAME_QUERY_NAME
		= "summarizeUsersByUsername";
	
	/* Parameter names. */
	
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	
	private static final String FIRST_NAME_PARAM_NAME = "firstName";
	
	private static final String USERNAME_PARAM_NAME = "username";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Hibernate implementation of report service for users.
	 * 
	 * @param sessionFactory session factory
	 */
	public UserReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<UserSummary> summarizeByName(
			final String lastName, final String firstName) {
		@SuppressWarnings("unchecked")
		List<UserSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(SUMMARIZE_BY_NAME_QUERY_NAME)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserSummary> summarizeByUsername(final String username) {
		@SuppressWarnings("unchecked")
		List<UserSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SUMMARIZE_BY_USERNAME_QUERY_NAME)
				.setParameter(USERNAME_PARAM_NAME, username)
				.list();
		return summaries;
	}
}