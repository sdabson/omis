package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.SectionTypeDao;
import omis.questionnaire.domain.SectionType;

/**
 * SectionTypeDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class SectionTypeDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SectionType> implements SectionTypeDao {
	
	/* Query Names */
	
	private static final String FIND_SECTION_TYPE_QUERY_NAME =
			"findSectionType";
	
	private static final String FIND_SECTION_TYPE_EXCLUDING_QUERY_NAME =
			"findSectionTypeExcluding";
	
	private static final String FIND_ALL_SECTION_TYPES_QUERY_NAME =
			"findAllSectionTypes";
	
	/* Parameter Names */
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String SECTION_TYPE_PARAM_NAME = "sectionType";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected SectionTypeDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public SectionType find(final String description) {
		SectionType sectionType = (SectionType) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SECTION_TYPE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		
		return sectionType;
	}

	/**{@inheritDoc} */
	@Override
	public SectionType findExcluding(final String description, 
			final SectionType excludedSectionType) {
		SectionType sectionType = (SectionType) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SECTION_TYPE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(SECTION_TYPE_PARAM_NAME, excludedSectionType)
				.uniqueResult();
		
		return sectionType;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<SectionType> findAll(){
		@SuppressWarnings("unchecked")
		List<SectionType> types = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_SECTION_TYPES_QUERY_NAME)
				.list();
		
		return types;
	}

}
