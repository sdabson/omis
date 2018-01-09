package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AllowedQuestionDao;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AllowedQuestionDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedQuestionDaoHibernateImpl extends GenericHibernateDaoImpl<AllowedQuestion>
		implements AllowedQuestionDao {
	
	/* Query Names */
	
	private static final String FIND_ALLOWED_QUESTION_QUERY_NAME =
			"findAllowedQuestion";
	
	private static final String FIND_ALLOWED_QUESTION_EXCLUDING_QUERY_NAME =
			"findAllowedQuestionExcluding";
	
	private static final String 
		FIND_ALLOWED_QUESTIONS_BY_QUESTIONNAIRE_SECTION_QUERY_NAME = 
			"findByQuestionnaireSection";
	
	private static final String
		FIND_ALL_ALLOWED_QUESTIONS_BY_QUESTIONNAIRE_SECTION_QUERY_NAME = 
			"findByQuestionnaireSectionIncludingInvalid";
	
	/* Parameter Names */
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME = 
			"questionnaireSection";
	
	private static final String QUESTION_PARAM_NAME = "question";
	
	private static final String QUESTION_NUMBER_PARAM_NAME = "questionNumber";
	
	private static final String ALLOWED_QUESTION_PARAM_NAME = "allowedQuestion";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AllowedQuestionDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AllowedQuestion find(final QuestionnaireSection questionnaireSection,
			final Question question, final String questionNumber) {
		AllowedQuestion allowedQuestion = 
				(AllowedQuestion) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_QUESTION_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(QUESTION_NUMBER_PARAM_NAME, questionNumber)
				.uniqueResult();
		
		return allowedQuestion;
	}

	/**{@inheritDoc} */
	@Override
	public AllowedQuestion findExcluding(
			final QuestionnaireSection questionnaireSection, 
			final Question question,
			final String questionNumber, 
			final AllowedQuestion excludedAllowedQuestion) {
		AllowedQuestion allowedQuestion = 
				(AllowedQuestion) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_QUESTION_EXCLUDING_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(QUESTION_NUMBER_PARAM_NAME, questionNumber)
				.setParameter(ALLOWED_QUESTION_PARAM_NAME, 
						excludedAllowedQuestion)
				.uniqueResult();
		
		return allowedQuestion;
	}

	/**{@inheritDoc} */
	@Override
	public List<AllowedQuestion> findAllByQuestionnaireSection(
			final QuestionnaireSection questionnaireSection) {
		@SuppressWarnings("unchecked")
		List<AllowedQuestion> allowedQuestions = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_ALLOWED_QUESTIONS_BY_QUESTIONNAIRE_SECTION_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.list();
		
		return allowedQuestions;
	}

	/**{@inheritDoc} */
	@Override
	public List<AllowedQuestion> findAllByQuestionnaireSectionIncludingInvalid(
			QuestionnaireSection questionnaireSection) {
		@SuppressWarnings("unchecked")
		List<AllowedQuestion> allowedQuestions = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_ALL_ALLOWED_QUESTIONS_BY_QUESTIONNAIRE_SECTION_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.list();
		
		return allowedQuestions;
	}

}
