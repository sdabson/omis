package omis.religion.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.religion.dao.ReligiousAccommodationAuthorizationDao;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;

/**
 * Hibernate implementation of data access object for religious accommodation
 * authorizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public class ReligiousAccommodationAuthorizationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ReligiousAccommodationAuthorization>
		implements ReligiousAccommodationAuthorizationDao {

	private static final String FIND_QUERY_NAME
		= "findReligiousAccommodationAuthorization";
	
	private static final String REMOVE_BY_PREFERENCE_QUERY_NAME
		= "deleteReligiousAccommodationAuthorizationsByPreference";
	
	private static final String PREFERENCE_PARAM_NAME = "preference";
	
	private static final String ACCOMMODATION_PARAM_NAME = "accommodation";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * religious accommodation authorizations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ReligiousAccommodationAuthorizationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousAccommodationAuthorization find(
			final ReligiousPreference preference,
			final ReligiousAccommodation accommodation) {
		ReligiousAccommodationAuthorization authorization
			= (ReligiousAccommodationAuthorization)
			this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_QUERY_NAME)
			.setParameter(PREFERENCE_PARAM_NAME, preference)
			.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
			.uniqueResult();
		return authorization;
	}

	/** {@inheritDoc} */
	@Override
	public int removeByPreference(final ReligiousPreference preference) {
		int result = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(REMOVE_BY_PREFERENCE_QUERY_NAME)
				.setParameter(PREFERENCE_PARAM_NAME, preference)
			.executeUpdate();
		return result;
	}
}