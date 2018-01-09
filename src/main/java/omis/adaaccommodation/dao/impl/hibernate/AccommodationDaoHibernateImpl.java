package omis.adaaccommodation.dao.impl.hibernate;

import omis.adaaccommodation.dao.AccommodationDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.Disability;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the accommodation data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AccommodationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Accommodation>
	implements AccommodationDao {

	/* Queries. */
	private static final String FIND_ACCOMMODATION_QUERY_NAME
		= "findAccommodation";
	private static final String FIND_ACCOMMODATION_EXCLUDING_QUERY_NAME
		= "findAccommodationExcluding";
	
	/* Parameters. */
	private static final String DISABILITY_PARAM_NAME = "disability";
	private static final String ACCOMMODATION_DESCRIPTION_PARAM_NAME 
		= "accommodationDescription";
	private static final String ACCOMMODATION_CATEGORY_PARAM_NAME 
		= "accommodationCategory";
	private static final String ACCOMMODATION_PARAM_NAME
		= "accommodation";

	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * accommodation.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AccommodationDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
	super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation find(final Disability disability,
			final String accommodationDescription,
			final AccommodationCategory accommodationCategory) {
		Accommodation accommodation = 
				(Accommodation) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_QUERY_NAME)
				.setParameter(DISABILITY_PARAM_NAME, disability)
				.setParameter(
						ACCOMMODATION_DESCRIPTION_PARAM_NAME, 
						accommodationDescription)
				.setParameter(
						ACCOMMODATION_CATEGORY_PARAM_NAME, 
						accommodationCategory)
				.uniqueResult();
		return accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation findExcluding(final Disability disability,
			final String accommodationDescription,
			final AccommodationCategory accommodationCategory, 
			final Accommodation accommodation) {
		Accommodation thisAccommodation = 
				(Accommodation) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_EXCLUDING_QUERY_NAME)
				.setParameter(DISABILITY_PARAM_NAME, disability)
				.setParameter(
						ACCOMMODATION_DESCRIPTION_PARAM_NAME, 
						accommodationDescription)
				.setParameter(
						ACCOMMODATION_CATEGORY_PARAM_NAME, 
						accommodationCategory)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.uniqueResult();
		return thisAccommodation;
	}
}