package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.QuestionnaireSectionDao;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireSectionDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<QuestionnaireSection>
		implements QuestionnaireSectionDao {
	
	/* Query Names */
	
	private static final String FIND_QUESTIONNAIRE_SECTION_QUERY_NAME =
			"findQuestionnaireSection";
	
	private static final String FIND_QUESTIONNAIRE_SECTION_EXCLUDING_QUERY_NAME 
		= "findQuestionnaireSectionExcluding";
	
	private static final String 
		FIND_QUESTIONNAIRE_SECTION_BY_QUESTIONNAIRE_TYPE_QUERY_NAME
			= "findQuestionnaireSectionByQuestionnaireType";
	
	/* Parameter Names */
	
	private static final String TITLE_PARAM_NAME = "title";
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME 
		= "questionnaireSection";
	
	private static final String QUESTIONNAIRE_TYPE_PARAM_NAME 
		= "questionnaireType";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected QuestionnaireSectionDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireSection find(final String title,
			final QuestionnaireType questionnaireType) {
		QuestionnaireSection questionnaireSection = 
				(QuestionnaireSection) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_SECTION_QUERY_NAME)
				.setParameter(TITLE_PARAM_NAME, title)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
				.uniqueResult();
		
		return questionnaireSection;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireSection findExcluding(final String title,
			final QuestionnaireType questionnaireType, 
			final QuestionnaireSection excludedQuestionnaireSection) {
		QuestionnaireSection questionnaireSection = 
				(QuestionnaireSection) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_SECTION_EXCLUDING_QUERY_NAME)
				.setParameter(TITLE_PARAM_NAME, title)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						excludedQuestionnaireSection)
				.uniqueResult();
		
		return questionnaireSection;
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireSection> findByQuestionnaireType(QuestionnaireType questionnaireType) {
		@SuppressWarnings("unchecked")
		List<QuestionnaireSection> questionnaireSections = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_SECTION_BY_QUESTIONNAIRE_TYPE_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
				.list();
		
		return questionnaireSections;
	}

}
