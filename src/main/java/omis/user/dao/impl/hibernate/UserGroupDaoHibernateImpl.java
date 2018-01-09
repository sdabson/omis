package omis.user.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.dao.UserGroupDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for user groups.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public class UserGroupDaoHibernateImpl
		extends GenericHibernateDaoImpl<UserGroup>
		implements UserGroupDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME = "findAllUserGroups";
	
	private static final String FIND_MAX_SORT_ORDER_QUERY_NAME
		= "findMaxUserGroupSortOrder";
	
	private static final String FIND_BY_USER_ACCOUNT_QUERY_NAME
		= "findUserGroupsByAccount";
	
	private static final String FIND_BY_ROLE_QUERY_NAME
		= "findUserGroupsByUserRole";
	
	/* Parameters. */
	
	private static final String USER_ACCOUNT_PARAM_NAME = "userAccount";

	private static final String USER_ROLE_PARAM_NAME = "userRole";

	/* Constructor. */
	
	/**
	 * Instantiates an implementation of data access object for user groups.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserGroupDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findAll() {
		@SuppressWarnings("unchecked")
		List<UserGroup> groups = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return groups;
	}

	/** {@inheritDoc} */
	@Override
	public short findMaxSortOrder() {
		return Short.valueOf(this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_MAX_SORT_ORDER_QUERY_NAME).uniqueResult()
					.toString());
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findByUserAccount(final UserAccount userAccount) {
		@SuppressWarnings("unchecked")
		List<UserGroup> groups = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_USER_ACCOUNT_QUERY_NAME)
				.setParameter(USER_ACCOUNT_PARAM_NAME, userAccount).list();
		return groups;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findByRole(final UserRole role) {
		@SuppressWarnings("unchecked")
		List<UserGroup> groups =  this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_ROLE_QUERY_NAME)
			.setParameter(USER_ROLE_PARAM_NAME, role)
			.list();
		return groups;
	}
}