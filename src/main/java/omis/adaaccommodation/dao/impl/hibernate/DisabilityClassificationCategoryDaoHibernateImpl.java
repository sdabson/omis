package omis.adaaccommodation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.adaaccommodation.dao.DisabilityClassificationCategoryDao;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the disability classification category data 
 * access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class DisabilityClassificationCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<DisabilityClassificationCategory>
	implements 	DisabilityClassificationCategoryDao {

	/* Queries. */
	private static final String FIND_DISABILITY_CLASSIFICATION_CATEGORIES
		= "findAllDisabilityClassificationCategories";

	/**
	 * Instantiates a hibernate implementation of the data access object for 
	 * disability classification category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DisabilityClassificationCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DisabilityClassificationCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<DisabilityClassificationCategory> categories = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_DISABILITY_CLASSIFICATION_CATEGORIES)
				.list();
		return categories;
	}
}