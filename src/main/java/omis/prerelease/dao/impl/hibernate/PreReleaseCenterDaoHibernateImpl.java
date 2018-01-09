package omis.prerelease.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.prerelease.dao.PreReleaseCenterDao;
import omis.prerelease.domain.PreReleaseCenter;

/**
 * Hibernate implementation of data access object for prerelease center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public class PreReleaseCenterDaoHibernateImpl 
		extends GenericHibernateDaoImpl<PreReleaseCenter>
		implements PreReleaseCenterDao {

	/* Queries. */
	private final static String FIND_PRERELEASE_CENTER_QUERY_NAME 
		= "findPreReleaseCenter";
	
	private final static String FIND_PRERELEASE_CENTER_EXCLUDING_QUERY_NAME 
			= "findPreReleaseCenterExcluding";
	
	/* Parameters. */
	private final static String LOCATION_PARAM_NAME = "location";
	
	private final static String NAME_PARAM_NAME = "name";
	
	private final static String TELEPHONE_NUMBER_PARAM_NAME = "telephoneNumber";
	
	private final static String EXCLUDED_PRERELEASE_CENTER_PARAM_NAME 
		= "excludedPreReleaseCenter";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * prerelease center.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PreReleaseCenterDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public PreReleaseCenter find(Location location, String name, 
			Long telephoneNumber) {
		PreReleaseCenter preReleaseCenter
			= (PreReleaseCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_PRERELEASE_CENTER_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.uniqueResult();
		return preReleaseCenter;
	}

	/** {@inheritDoc} */
	@Override
	public PreReleaseCenter findExcluding(Location location, String name, 
			Long telephoneNumber, PreReleaseCenter excludedPreReleaseCenter) {
		PreReleaseCenter preReleaseCenter
			= (PreReleaseCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_PRERELEASE_CENTER_EXCLUDING_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.setParameter(EXCLUDED_PRERELEASE_CENTER_PARAM_NAME, 
					excludedPreReleaseCenter)
			.uniqueResult();
		return preReleaseCenter;
	}
}
