package omis.sentence.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.sentence.dao.LegalDispositionCategoryDao;
import omis.sentence.domain.LegalDispositionCategory;

/**
 * Hibernate implementation of data access object for legal disposition 
 * categories.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Apr 3, 2017)
 * @since OMIS 3.0
 */
public class LegalDispositionCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<LegalDispositionCategory>
		implements LegalDispositionCategoryDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findLegalDispositionCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findLegalDispositionCategoryExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CATEGORY_PARAM_NAME 
		= "excludedCategory";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * conviction categories with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LegalDispositionCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public LegalDispositionCategory find(String name) {
		LegalDispositionCategory category 
			= (LegalDispositionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public LegalDispositionCategory findExcluding(String name, 
			LegalDispositionCategory excludedCategory) {
		LegalDispositionCategory category 
			= (LegalDispositionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CATEGORY_PARAM_NAME, excludedCategory)
				.uniqueResult();
	return category;
	}

}
