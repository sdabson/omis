package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AllowedAnswerDao;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerValue;

/**
 * AllowedAnswerDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedAnswerDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AllowedAnswer> implements AllowedAnswerDao {
	
	/* Query names */
	
	private static final String FIND_ALLOWED_ANSWER_QUERY_NAME =
			"findAllowedAnswer";
	
	private static final String FIND_ALLOWED_ANSWER_EXCLUDING_QUERY_NAME =
			"findAllowedAnswerExcluding";
	
	private static final String 
		FIND_ALLOWED_ANSWER_BY_ALLOWED_QUESTION_QUERY_NAME =
			"findAllowedAnswerByAllowedQuestion";
	
	/* Parameter Names */
	
	private static final String ALLOWED_QUESTION_PARAM_NAME = "allowedQuestion";
	
	private static final String ANSWER_VALUE_PARAM_NAME = "answerValue";
	
	private static final String ALLOWED_ANSWER_PARAM_NAME = "allowedAnswer";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AllowedAnswerDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AllowedAnswer find(final AllowedQuestion allowedQuestion, 
			final AnswerValue answerValue) {
		AllowedAnswer allowedAnswer = (AllowedAnswer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_ANSWER_QUERY_NAME)
				.setParameter(ALLOWED_QUESTION_PARAM_NAME, allowedQuestion)
				.setParameter(ANSWER_VALUE_PARAM_NAME, answerValue)
				.uniqueResult();
		
		return allowedAnswer;
	}

	/**{@inheritDoc} */
	@Override
	public AllowedAnswer findExcluding(final AllowedQuestion allowedQuestion, 
			final AnswerValue answerValue, 
			final AllowedAnswer excludedAllowedAnswer) {
		AllowedAnswer allowedAnswer = (AllowedAnswer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_ANSWER_EXCLUDING_QUERY_NAME)
				.setParameter(ALLOWED_QUESTION_PARAM_NAME, allowedQuestion)
				.setParameter(ANSWER_VALUE_PARAM_NAME, answerValue)
				.setParameter(ALLOWED_ANSWER_PARAM_NAME, excludedAllowedAnswer)
				.uniqueResult();
		
		return allowedAnswer;
	}

	/**{@inheritDoc} */
	@Override
	public List<AllowedAnswer> findAllByAllowedQuestion(
			final AllowedQuestion allowedQuestion) {
		@SuppressWarnings("unchecked")
		List<AllowedAnswer> allowedAnswers = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ALLOWED_ANSWER_BY_ALLOWED_QUESTION_QUERY_NAME)
				.setParameter(ALLOWED_QUESTION_PARAM_NAME, allowedQuestion)
				.list();
		
		return allowedAnswers;
	}

}
