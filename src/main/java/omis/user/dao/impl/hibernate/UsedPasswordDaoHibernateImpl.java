package omis.user.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.dao.UsedPasswordDao;
import omis.user.domain.UsedPassword;
import omis.user.domain.UserAccount;

/**
 * Hibernate implementation of data access object for used passwords.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 28, 2014)
 * @since OMIS 3.0
 */
public class UsedPasswordDaoHibernateImpl
		extends GenericHibernateDaoImpl<UsedPassword>
		implements UsedPasswordDao {

	private static final String FIND_QUERY_NAME = "findUsedPassword";
	
	private static final String USER_ACCOUNT_PARAM_NAME = "userAccount";
	
	private static final String VALUE_PARAM_NAME = "value";

	/**
	 * Instantiates a data access object for used passwords.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UsedPasswordDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public UsedPassword find(
			final UserAccount userAccount, final String password,
			final Date effectiveDate) {
		UsedPassword usedPassword = (UsedPassword) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(USER_ACCOUNT_PARAM_NAME, userAccount)
				.setParameter(VALUE_PARAM_NAME, password).uniqueResult();
		return usedPassword;
	}
}