package omis.security.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.security.dao.AccessAttemptDao;
import omis.security.domain.AccessAttempt;

/**
 * Hibernate implementation of data access object for access attempts.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (July 25, 2013)
 * @since OMIS 3.0
 */
public class AccessAttemptDaoHibernateImpl
		extends GenericHibernateDaoImpl<AccessAttempt>
		implements AccessAttemptDao {

	/**
	 * Instantiates a data access object for access attempts with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entityName
	 */
	public AccessAttemptDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AccessAttempt> findByUsername(final String username) {
		@SuppressWarnings("unchecked")
		List<AccessAttempt> accessAttempts = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findAccessAttemptsByUsername")
				.setParameter("username", username)
				.list();
		return accessAttempts;
	}
}