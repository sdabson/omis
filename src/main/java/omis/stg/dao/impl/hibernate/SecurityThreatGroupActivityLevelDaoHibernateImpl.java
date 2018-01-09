package omis.stg.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.stg.dao.SecurityThreatGroupActivityLevelDao;
import omis.stg.domain.SecurityThreatGroupActivityLevel;

/**
 * Hibernate implementation of data access object for security threat group
 * activity levels.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityLevelDaoHibernateImpl
		extends GenericHibernateDaoImpl<SecurityThreatGroupActivityLevel>
		implements SecurityThreatGroupActivityLevelDao {

	private static final String FIND_ALL_QUERY_NAME
		= "findSecurityThreatGroupActivityLevels";
	
	private static final String FIND_QUERY_NAME
		= "findSecurityThreatGroupActivityLevel";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSecurityThreatGroupActivityLevelExcluding";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDING_ACTIVITY_LEVEL_PARAM_NAME 
		= "excludedActivityLevel";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * security threat group activity levels.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupActivityLevelDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityLevel> findAll() {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivityLevel> activityLevels
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_ALL_QUERY_NAME).list();
		return activityLevels;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityLevel find(String name) {
		SecurityThreatGroupActivityLevel activityLevel = 
				(SecurityThreatGroupActivityLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return activityLevel;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityLevel findExcluding(String name,
			SecurityThreatGroupActivityLevel excludedActivityLevel) {
		SecurityThreatGroupActivityLevel activityLevel = 
				(SecurityThreatGroupActivityLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDING_ACTIVITY_LEVEL_PARAM_NAME, 
						excludedActivityLevel)
				.uniqueResult();
		return activityLevel;
	}
}