package omis.religion.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.religion.dao.ReligiousAccommodationDao;
import omis.religion.domain.ReligiousAccommodation;

/**
 * Hibernate implementation of data access object for religious accommodations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public class ReligiousAccommodationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ReligiousAccommodation>
		implements ReligiousAccommodationDao {

	private static final String FIND_ALL_QUERY_NAME
		= "findReligiousAccommodations";
	
	private static final String FIND_QUERY_NAME
		= "findReligiousAccommodation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findReligiousAccommodationExcluding";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_ACCOMMODATION_PARAM_NAME 
		= "excludedAccommodation";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * religious accommodations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ReligiousAccommodationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReligiousAccommodation> findAll() {
		@SuppressWarnings("unchecked")
		List<ReligiousAccommodation> accommodations = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return accommodations;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousAccommodation find(final String name) {
		ReligiousAccommodation accommodation = (ReligiousAccommodation) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousAccommodation findExcluding(final String name, 
			final ReligiousAccommodation excludedAccommodation) {
		ReligiousAccommodation accommodation = (ReligiousAccommodation) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_ACCOMMODATION_PARAM_NAME, 
						excludedAccommodation)
				.uniqueResult();
		return accommodation;
	}
}