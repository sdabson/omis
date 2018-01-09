package omis.user.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.user.dao.UserGroupMembershipDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserGroupMembership;

/**
 * Hibernate implementation of data access object for user group memberships.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class UserGroupMembershipDaoHibernateImpl
		extends GenericHibernateDaoImpl<UserGroupMembership>
		implements UserGroupMembershipDao {

	/* Query names. */
	
	private static final String DELETE_BY_USER_GROUP_QUERY_NAME
			= "deleteUserGroupMembershipsByUserGroup";
	
	private static final String DELETE_BY_USER_ACCOUNT_QUERY_NAME
			= "deleteUserGroupMembershipsByUserAccount";
	
	/* Parameter names. */
	
	private static final String USER_GROUP_PARAM_NAME = "userGroup";
	
	private static final String USER_ACCOUNT_PARAM_NAME = "userAccount";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for user
	 * group memberships.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UserGroupMembershipDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
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
	public int deleteByUserAccount(final UserAccount userAccount) {
		return this.getSessionFactory().getCurrentSession()
				.getNamedQuery(DELETE_BY_USER_ACCOUNT_QUERY_NAME)
				.setParameter(USER_ACCOUNT_PARAM_NAME, userAccount)
				.executeUpdate();
	}
}