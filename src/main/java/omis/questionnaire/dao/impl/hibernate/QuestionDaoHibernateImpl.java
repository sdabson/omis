package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.QuestionDao;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;

/**
 * QuestionDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Question> implements QuestionDao {
	
	/* Query Names */
	
	private static final String FIND_QUESTION_QUERY_NAME = "findQuestion";
	
	private static final String FIND_QUESTION_EXCLUDING_QUERY_NAME =
			"findQuestionExcluding";

	private static final String FIND_QUESTIONS_BY_TEXT = 
			"findQuestionsByText";
	
	/* Parameter Names */
	
	private static final String QUESTION_CATEGORY_PARAM_NAME =
			"questionCategory";
	
	private static final String TEXT_PARAM_NAME = "text";
	
	private static final String QUESTION_PARAM_NAME = "question";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected QuestionDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public Question find(final QuestionCategory questionCategory, 
			final String text) {
		Question question = (Question) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTION_QUERY_NAME)
				.setParameter(QUESTION_CATEGORY_PARAM_NAME, questionCategory)
				.setParameter(TEXT_PARAM_NAME, text)
				.uniqueResult();
		
		return question;
	}

	/**{@inheritDoc} */
	@Override
	public Question findExcluding(final QuestionCategory questionCategory, 
			final String text, final Question excludedQuestion) {
		Question question = (Question) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTION_EXCLUDING_QUERY_NAME)
				.setParameter(QUESTION_CATEGORY_PARAM_NAME, questionCategory)
				.setParameter(TEXT_PARAM_NAME, text)
				.setParameter(QUESTION_PARAM_NAME, excludedQuestion)
				.uniqueResult();
		
		return question;
	}

	/**{@inheritDoc} */
	@Override
	public List<Question> findByText(final String text) {
		@SuppressWarnings("unchecked")
		List<Question> questions = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONS_BY_TEXT)
				.setParameter(TEXT_PARAM_NAME, text)
				.list();
		
		return questions;
	}

}
