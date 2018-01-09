package omis.sentence.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.sentence.dao.SentenceCategoryDao;
import omis.sentence.domain.SentenceCategory;

/**
 * Hibernate implementation of data access object for sentence categories.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 27, 2017)
 * @since OMIS 3.0
 */
public class SentenceCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<SentenceCategory>
		implements SentenceCategoryDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findSentenceCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findSentenceCategoryExcluding";
	
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
	public SentenceCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public SentenceCategory find(String name) {
		SentenceCategory category 
			= (SentenceCategory) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public SentenceCategory findExcluding(String name, 
			SentenceCategory excludedCategory) {
		SentenceCategory category 
		= (SentenceCategory) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(EXCLUDED_CATEGORY_PARAM_NAME, excludedCategory)
			.uniqueResult();
	return category;
	}

}
