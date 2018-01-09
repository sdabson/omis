package omis.stg.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.domain.State;
import omis.stg.dao.SecurityThreatGroupDao;
import omis.stg.domain.SecurityThreatGroup;

/**
 * Hibernate implementation of data access object for security threat groups.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupDaoHibernateImpl
		extends GenericHibernateDaoImpl<SecurityThreatGroup>
		implements SecurityThreatGroupDao {

	private static final String FIND_ALL_QUERY_NAME
		= "findSecurityThreatGroups";
	
	private static final String FIND_QUERY_NAME
		= "findSecurityThreatGroup";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSecurityThreatGroupExcluding";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String STATE_PARAM_NAME = "state";
	
	private static final String EXCLUDED_GROUP_PARAM_NAME 
		= "excludedSecurityThreatGroup";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * security threat groups.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroup> findAll() {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroup> groups
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_ALL_QUERY_NAME).list();
		return groups;
	}

	@Override
	public SecurityThreatGroup find(String name, State state) {
		SecurityThreatGroup group = (SecurityThreatGroup) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(STATE_PARAM_NAME, state)
				.uniqueResult();
		return group;
	}

	@Override
	public SecurityThreatGroup findExcluding(String name, State state, 
			SecurityThreatGroup securityThreatGroup) {
		SecurityThreatGroup group = (SecurityThreatGroup) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(STATE_PARAM_NAME, state)
				.setParameter(EXCLUDED_GROUP_PARAM_NAME, securityThreatGroup)
				.uniqueResult();
		return group;
	}
}