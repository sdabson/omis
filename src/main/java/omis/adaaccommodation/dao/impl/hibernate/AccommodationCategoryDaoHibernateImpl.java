package omis.adaaccommodation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.adaaccommodation.dao.AccommodationCategoryDao;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the accommodation category data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AccommodationCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AccommodationCategory> 
	implements AccommodationCategoryDao {

	/* Queries. */
	private static final String FIND_ACCOMMODATION_CATEGORIES_QUERY_NAME
		= "findAllAccommodationCategories";

	/**
	 * Instantiates a hibernate implementation of the data acces object for
	 * accommodation category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AccommodationCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationCategory> findAllAccommodationCategories() {
		@SuppressWarnings("unchecked")
		List<AccommodationCategory> categories = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ACCOMMODATION_CATEGORIES_QUERY_NAME)
				.list();
		return categories;
	}
}