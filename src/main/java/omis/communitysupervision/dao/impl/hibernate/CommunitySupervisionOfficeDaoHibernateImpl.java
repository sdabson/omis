package omis.communitysupervision.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.communitysupervision.dao.CommunitySupervisionOfficeDao;
import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;

/**
 * Hibernate implementation of data access object for community supervision 
 * office.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public class CommunitySupervisionOfficeDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CommunitySupervisionOffice>
		implements CommunitySupervisionOfficeDao {

	/* Queries. */
	private final static String FIND_COMMUNITY_SUPERVISION_OFFICE_QUERY_NAME 
		= "findCommunitySupervisionOffice";
	
	private final static String 
		FIND_COMMUNITY_SUPERVISION_OFFICE_EXCLUDING_QUERY_NAME 
			= "findCommunitySupervisionOfficeExcluding";
	
	/* Parameters. */
	private final static String LOCATION_PARAM_NAME = "location";
	
	private final static String NAME_PARAM_NAME = "name";
	
	private final static String TELEPHONE_NUMBER_PARAM_NAME = "telephoneNumber";
	
	private final static String EXCLUDED_COMMUNITY_SUPERVISION_OFFICE_PARAM_NAME 
		= "excludedCommunitySupervisionOffice";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * community supervision office.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CommunitySupervisionOfficeDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CommunitySupervisionOffice find(Location location, String name, 
			Long telephoneNumber) {
		CommunitySupervisionOffice communitySupervisionOffice
			= (CommunitySupervisionOffice) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_COMMUNITY_SUPERVISION_OFFICE_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.uniqueResult();
		return communitySupervisionOffice;
	}

	/** {@inheritDoc} */
	@Override
	public CommunitySupervisionOffice findExcluding(Location location, 
			String name, Long telephoneNumber,
			CommunitySupervisionOffice excludedCommunitySupervisionOffice) {
		CommunitySupervisionOffice communitySupervisionOffice
			= (CommunitySupervisionOffice) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_COMMUNITY_SUPERVISION_OFFICE_EXCLUDING_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.setParameter(EXCLUDED_COMMUNITY_SUPERVISION_OFFICE_PARAM_NAME, 
					excludedCommunitySupervisionOffice)
			.uniqueResult();
		return communitySupervisionOffice;
	}
}
