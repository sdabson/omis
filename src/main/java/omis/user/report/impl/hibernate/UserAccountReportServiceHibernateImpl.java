package omis.user.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.user.report.UserAccountReportService;
import omis.user.report.UserAccountSearchResult;

/**
 * Implementation of report service for user accounts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 28, 2014)
 * @since OMIS 3.0
 */
public class UserAccountReportServiceHibernateImpl
		implements UserAccountReportService {

	private static final String SEARCH_USER_ACCOUNT_BY_QUERY_QUERY_NAME
		= "searchUserAccountsByQuery";

	private static final String QUERY_PARAM_NAME = "query";
	
	private SessionFactory sessionFactory;

	/**
	 * Instantiates an implementation of report service for user accounts.
	 * 
	 * @param sessionFactory session factory
	 */
	public UserAccountReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UserAccountSearchResult> searchForUserAccount(
			final String query) {
		@SuppressWarnings("unchecked")
		List<UserAccountSearchResult> results = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SEARCH_USER_ACCOUNT_BY_QUERY_QUERY_NAME)
				.setParameter(QUERY_PARAM_NAME, query.toUpperCase()).list();
		return results;
	}
}