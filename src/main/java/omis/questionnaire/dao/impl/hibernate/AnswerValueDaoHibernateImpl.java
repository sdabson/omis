package omis.questionnaire.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AnswerValueDao;
import omis.questionnaire.domain.AnswerValue;

/**
 * AnswerValueDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class AnswerValueDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AnswerValue> implements AnswerValueDao {
	
	/* Query Names */
	
	private static final String FIND_ANSWER_VALUE_QUERY_NAME =
			"findAnswerValue";
	
	private static final String FIND_ANSWER_VALUE_EXCLUDING_QUERY_NAME =
			"findAnswerValueExcluding";

	private static final String FIND_ANSWER_VALUES_BY_DESCRIPTION = 
			"findAnswerValuesByDescription";
	
	/* Parameter Names */
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String ANSWER_VALUE_PARAM_NAME = "answerValue";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AnswerValueDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	

	/**{@inheritDoc} */
	@Override
	public AnswerValue find(final String description) {
		AnswerValue answerValue = (AnswerValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ANSWER_VALUE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		
		return answerValue;
	}

	/**{@inheritDoc} */
	@Override
	public AnswerValue findExcluding(final String description, 
			final AnswerValue excludedAnswerValue) {
		AnswerValue answerValue = (AnswerValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ANSWER_VALUE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(ANSWER_VALUE_PARAM_NAME, excludedAnswerValue)
				.uniqueResult();
		
		return answerValue;
	}


	/**{@inheritDoc} */
	@Override
	public List<AnswerValue> findByDescription(final String description) {
		@SuppressWarnings("unchecked")
		List<AnswerValue> answerValues = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ANSWER_VALUES_BY_DESCRIPTION)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.list();
		
		return answerValues;
	}

}
