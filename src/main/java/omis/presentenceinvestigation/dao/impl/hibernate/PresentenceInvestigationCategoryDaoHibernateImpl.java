package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationCategoryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;

/**
 * PresentenceInvestigationCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<PresentenceInvestigationCategory>
		implements PresentenceInvestigationCategoryDao {
	
	private static final String
		FIND_PRESENTENCE_INVESTIGATION_CATEGORY_QUERY_NAME =
			"findPresentenceInvestigationCategory";
	
	private static final String
		FIND_PRESENTENCE_INVESTIGATION_CATEGORY_EXCLUDING_QUERY_NAME =
			"findPresentenceInvestigationCategoryExcluding";
	
	private static final String
		FIND_PRESENTENCE_INVESTIGATION_CATEGORIES_QUERY_NAME =
			"findPresentenceInvestigationCategories";
	
	private static final String NAME_MODEL_KEY = "name";
	
	private static final String PRESENTENCE_INVESTIGATION_CATEGORY_MODEL_KEY =
			"presentenceInvestigationCategory";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationCategory find(final String name) {
		PresentenceInvestigationCategory presentenceInvestigationCategory =
				(PresentenceInvestigationCategory) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_CATEGORY_QUERY_NAME)
				.setParameter(NAME_MODEL_KEY, name)
				.uniqueResult();
		
		return presentenceInvestigationCategory;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationCategory findExcluding(final String name,
			final PresentenceInvestigationCategory
				presentenceInvestigationCategoryExcluded) {
		PresentenceInvestigationCategory presentenceInvestigationCategory =
				(PresentenceInvestigationCategory) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
					FIND_PRESENTENCE_INVESTIGATION_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_MODEL_KEY, name)
				.setParameter(PRESENTENCE_INVESTIGATION_CATEGORY_MODEL_KEY,
						presentenceInvestigationCategoryExcluded)
				.uniqueResult();
		
		return presentenceInvestigationCategory;
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationCategory> categories =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_CATEGORIES_QUERY_NAME)
				.list();
		
		return categories;
	}

}
