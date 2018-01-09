package omis.user.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.user.report.UserAccountProfileItemReportService;

/** Hibernate implementation of user account profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public class UserAccountProfileItemReportServiceHibernateImpl 
	implements UserAccountProfileItemReportService {
	private static final String 
		FIND_USER_ACCOUNT_COUNT_BY_USER_QUERY_NAME
		= "findUserAccountCountByUser";
	private static final String USER_PARAM_NAME = "user";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory session factory. */
	public UserAccountProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findUserAccountCountByUser(final Person user) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_USER_ACCOUNT_COUNT_BY_USER_QUERY_NAME);
		q.setEntity(USER_PARAM_NAME, user);
		return ((Long) q.uniqueResult()).intValue();
	}

}
