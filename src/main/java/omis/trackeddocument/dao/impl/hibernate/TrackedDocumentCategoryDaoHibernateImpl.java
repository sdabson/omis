package omis.trackeddocument.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.trackeddocument.dao.TrackedDocumentCategoryDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for tracked document category.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public class TrackedDocumentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<TrackedDocumentCategory>
		implements TrackedDocumentCategoryDao {

	/* Query names. */
	
	private static final String FIND_CATEGORIES_QUERY_NAME
		= "findTrackedDocumentCategories";
	
	/* Parameters. */
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * tracked document category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TrackedDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<TrackedDocumentCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<TrackedDocumentCategory> categories = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_CATEGORIES_QUERY_NAME)
			.list();
		return categories;
	}
}