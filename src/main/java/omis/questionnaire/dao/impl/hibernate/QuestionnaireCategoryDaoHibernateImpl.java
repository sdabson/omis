package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.QuestionnaireCategoryDao;
import omis.questionnaire.domain.QuestionnaireCategory;

/**
 * QuestionnaireCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<QuestionnaireCategory>
		implements QuestionnaireCategoryDao {
	
	/* Query Names */
	
	private static final String FIND_QUESTIONNAIRE_CATEGORY_QUERY_NAME =
			"findQuestionnaireCategory";
	
	private static final String 
		FIND_QUESTIONNAIRE_CATEGORY_EXCLUDING_QUERY_NAME =
			"findQuestionnaireCategoryExcluding";
	
	private static final String FIND_ALL_QUESTIONNAIRE_CATEGORIES_QUERY_NAME =
			"findAllQuestionnaireCategories";
	
	/* Parameter Names */
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String QUESTIONNAIRE_CATEGORY_PARAM_NAME =
			"questionnaireCategory";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected QuestionnaireCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireCategory find(final String description) {
		QuestionnaireCategory questionnaireCategory = (QuestionnaireCategory)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_CATEGORY_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		
		return questionnaireCategory;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireCategory findExcluding(final String description,
			final QuestionnaireCategory excludedQuestionnaireCategory) {
		QuestionnaireCategory questionnaireCategory = (QuestionnaireCategory)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(QUESTIONNAIRE_CATEGORY_PARAM_NAME, 
						excludedQuestionnaireCategory)
				.uniqueResult();
		
		return questionnaireCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireCategory> findAll(){
		@SuppressWarnings("unchecked")
		List<QuestionnaireCategory> categories = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUESTIONNAIRE_CATEGORIES_QUERY_NAME)
				.list();
		
		return categories;
	}
	
}
