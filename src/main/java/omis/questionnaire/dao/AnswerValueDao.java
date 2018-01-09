package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AnswerValue;

/**
 * AnswerValueDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AnswerValueDao extends GenericDao<AnswerValue> {
	
	/**
	 * Returns an Answer Value by specified description
	 * @param description - String
	 * @return AnswerValue
	 */
	AnswerValue find(String description);
	
	/**
	 * Returns an Answer value with specified description excluding specified
	 * AnswerValue
	 * @param description - String
	 * @param excludedAnswerValue - AnswerValue to Exclude
	 * @return AnswerValue
	 */
	AnswerValue findExcluding(String description, 
			AnswerValue excludedAnswerValue);
	
	/**
	 * Returns a list of answerValues by description
	 * @param description - String
	 * @return List of answerValues by specified description
	 */
	List<AnswerValue> findByDescription(String description);
}
