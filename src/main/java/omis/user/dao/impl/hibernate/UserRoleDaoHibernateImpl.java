package omis.user.dao.impl.hibernate;

import java.util.Collection;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.dao.UserRoleDao;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for user roles.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public class UserRoleDaoHibernateImpl
		extends GenericHibernateDaoImpl<UserRole>
		implements UserRoleDao {

	/* Queries. */
	
	private static final String FIND_BY_USER_GROUPS_QUERY_NAME
		= "findUserRolesByUserGroups";
	
	private static final String FIND_MAX_SORT_ORDER_QUERY_NAME
		= "findMaxUserRoleSortOrder";
	
	private static final String FIND_ALL_QUERY_NAME = "findAllUserRoles";
	
	private static final String FIND_BY_GROUP_QUERY_NAME
		= "findUserRolesByUserGroup";
	
	/* Parameters. */
	
	private static final String USER_GROUPS_PARAM_NAME = "userGroups";

	private static final String USER_GROUP_PARAM_NAME = "userGroup";
	
	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for user
	 * roles with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserRoleDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementation. */
	
	/** {@inheritDoc} */
	@Override
	public List<UserRole> findByUserGroups(
			final Collection<UserGroup> userGroups) {
		@SuppressWarnings("unchecked")
		List<UserRole> roles = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_USER_GROUPS_QUERY_NAME)
				.setParameterList(USER_GROUPS_PARAM_NAME, userGroups).list();
		return roles;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UserRole> findAll() {
		@SuppressWarnings("unchecked")
		List<UserRole> roles = this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return roles;
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
	public List<UserRole> findByGroups(final UserGroup group) {
		@SuppressWarnings("unchecked")
		List<UserRole> roles = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_GROUP_QUERY_NAME)
				.setParameter(USER_GROUP_PARAM_NAME, group)
				.list();
		return roles;
	}
}