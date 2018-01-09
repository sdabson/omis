package omis.userpreference.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.domain.UserAccount;
import omis.userpreference.dao.UserPreferenceDao;
import omis.userpreference.domain.UserPreference;

/**
 * User preference data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public class UserPreferenceDaoHibernateImpl 
extends GenericHibernateDaoImpl<UserPreference>
implements UserPreferenceDao {

	/* Query names. */
	
	private static final String FIND_BY_USER_ACCOUNT_QUERY_NAME
		= "findUserPreferenceByUserAccount";
	
	/* Parameter names. */
	
	private static final String USER_ACCOUNT_PARAM_NAME = "userAccount";
	
	
	/**
	 * Instantiates an instance of user preference data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserPreferenceDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public UserPreference findByAccount(final UserAccount userAccount) {
		UserPreference preference = (UserPreference) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_USER_ACCOUNT_QUERY_NAME)
				.setParameter(USER_ACCOUNT_PARAM_NAME, userAccount)
				.uniqueResult();
		return preference;
	}
}