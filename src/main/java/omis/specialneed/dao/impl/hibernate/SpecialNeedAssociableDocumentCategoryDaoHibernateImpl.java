/**
 * 
 */
package omis.specialneed.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedAssociableDocumentCategoryDao;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;

/**
 * Hibernate entity configurable implementation of data access object for
 * special need associable document category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedAssociableDocumentCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedAssociableDocumentCategory> 
	implements SpecialNeedAssociableDocumentCategoryDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = 
			"findSpecialNeedAssociableDocumentCategory";
	
	private static final String FIND_EXCLDING_QUERY_NAME = 
			"findSpecialNeedAssociableDocumentCategoryExcluding";
	
	/* Parameters. */

	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CATEGORY_PARAM_NAME = 
			"excludedCategory";
	
	/* Constructor. */
	
	/**
	 * Instantiates a data access object for special need associable document 
	 * category with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedAssociableDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocumentCategory find(final String name) {
		SpecialNeedAssociableDocumentCategory category = 
				(SpecialNeedAssociableDocumentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocumentCategory findExcluding(final String name, 
			final SpecialNeedAssociableDocumentCategory excludedCategory) {
		SpecialNeedAssociableDocumentCategory category = 
				(SpecialNeedAssociableDocumentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CATEGORY_PARAM_NAME, excludedCategory)
				.uniqueResult();
		return category;
	}
}