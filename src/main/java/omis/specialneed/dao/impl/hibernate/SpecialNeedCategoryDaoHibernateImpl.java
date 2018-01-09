/**
 * 
 */
package omis.specialneed.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedCategoryDao;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * special need category.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.3 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedCategory> 
	implements SpecialNeedCategoryDao {

	/* Query names. */
	
	/*private static final String FIND_ALL_VALID_QUERY_NAME 
	= "findAllValidSpecialNeedCategories";*/
	
	private static final String 
		FIND_ALL_CATEGORIES_BY_SPECIAL_NEED_CLASSIFICATION 
			= "findAllCategoriesBySpecialNeedClassification";
	
	private static final String FIND_QUERY_NAME = "findSpecialNeedCategory";
	
	private static final String FIND_EXCLDING_QUERY_NAME 
		= "findSpecialNeedCategoryExcluding";
	
	/* Parameters. */
	private static final String SPECIAL_NEED_CLASSIFICATION = "classification";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CATEGORY_PARAM_NAME 
		= "excludedCategory";
	
	/* Constructor. */
	
	/**
	 * Instantiates a data access object for special need category with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
/*	@Override
	public List<SpecialNeedCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<SpecialNeedCategory> categories = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_VALID_QUERY_NAME)
				.list();
		return categories;
	}*/

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedCategory> findCategories(
			final SpecialNeedClassification classification) {
		@SuppressWarnings("unchecked")
		List<SpecialNeedCategory> categories = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ALL_CATEGORIES_BY_SPECIAL_NEED_CLASSIFICATION)
				.setParameter(SPECIAL_NEED_CLASSIFICATION, classification)
				.list();
		return categories;				
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedCategory find(final String name, 
			final SpecialNeedClassification classification) {
		SpecialNeedCategory specialNeedCategory = (SpecialNeedCategory) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SPECIAL_NEED_CLASSIFICATION, classification)
				.uniqueResult();
		return specialNeedCategory;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedCategory findExcluding(final String name, 
			final SpecialNeedClassification classification,
			final SpecialNeedCategory excludedCategory) {
		SpecialNeedCategory specialNeedCategory = (SpecialNeedCategory) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SPECIAL_NEED_CLASSIFICATION, classification)
				.setParameter(EXCLUDED_CATEGORY_PARAM_NAME, excludedCategory)
				.uniqueResult();
		return specialNeedCategory;
	}
}