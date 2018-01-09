package omis.education.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.EducationDocumentCategoryDao;
import omis.education.domain.EducationDocumentCategory;

/**
 * EducationalDocumentCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationDocumentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<EducationDocumentCategory>
		implements EducationDocumentCategoryDao {
	
	private static final String FIND_ALL_EDUCATION_DOCUMENT_CATEGORIES =
			"findEducationDocumentCategories";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationDocumentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}


	/**{@inheritDoc} */
	@Override
	public List<EducationDocumentCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<EducationDocumentCategory> categories = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_EDUCATION_DOCUMENT_CATEGORIES)
				.list();
		
		return categories;
	}

}
