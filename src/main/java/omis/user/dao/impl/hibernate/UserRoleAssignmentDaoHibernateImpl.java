package omis.user.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.dao.UserRoleAssignmentDao;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;

/**
 * Hibernate implementation of data access object for assignments of user roles.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class UserRoleAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<UserRoleAssignment>
		implements UserRoleAssignmentDao {

	/* Query names. */
	
	private static final String DELETE_BY_USER_ROLE_QUERY_NAME
			= "deleteUserRoleAssignmentsByUserRole";
	
	private static final String DELETE_BY_USER_GROUP_QUERY_NAME
			= "deleteUserRoleAssignmentsByUserGroup";

	private static final String FIND_QUERY_NAME = "findUserRoleAssignment";
	
	/* Parameter names. */
	
	private static final String USER_ROLE_PARAM_NAME = "userRole";

	private static final String USER_GROUP_PARAM_NAME = "userGroup";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object for
	 * assignments of user roles.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserRoleAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public int deleteByUserRole(final UserRole userRole) {
		return this.getSessionFactory().getCurrentSession()
				.getNamedQuery(DELETE_BY_USER_ROLE_QUERY_NAME)
				.setParameter(USER_ROLE_PARAM_NAME, userRole)
				.executeUpdate();
	}

	/** {@inheritDoc} */
	@Override
	public int deleteByUserGroup(final UserGroup userGroup) {
		return this.getSessionFactory().getCurrentSession()
				.getNamedQuery(DELETE_BY_USER_GROUP_QUERY_NAME)
				.setParameter(USER_GROUP_PARAM_NAME, userGroup)
				.executeUpdate();
	}
	
	/** {@inheritDoc} */
	@Override
	public UserRoleAssignment find(
			final UserGroup userGroup, final UserRole userRole) {
		UserRoleAssignment assignment
			= (UserRoleAssignment) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(USER_GROUP_PARAM_NAME, userGroup)
				.setParameter(USER_ROLE_PARAM_NAME, userRole)
				.uniqueResult();
		return assignment;
	}
}