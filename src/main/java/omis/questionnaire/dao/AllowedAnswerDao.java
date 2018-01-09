package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerValue;

/**
 * AllowedAnswerDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AllowedAnswerDao extends GenericDao<AllowedAnswer> {
	
	/**
	 * Returns AllowedAnswer with specified properties
	 * @param allowedQuestion
	 * @param answerValue
	 * @return AllowedAnswer
	 */
	AllowedAnswer find(AllowedQuestion allowedQuestion, AnswerValue answerValue);
	
	/**
	 * Returns AllowedAnswer with specified properties excluding specified
	 * AllowedAnser
	 * @param allowedQuestion
	 * @param answerValue
	 * @param excludedAllowedAnswer - AllowedAnswer to exclude
	 * @return AllowedAnswer
	 */
	AllowedAnswer findExcluding(AllowedQuestion allowedQuestion, 
			AnswerValue answerValue, AllowedAnswer excludedAllowedAnswer);
	
	/**
	 * Returns a list of all AllowedAnswers by specified AllowedQuestion
	 * @param allowedQuestion
	 * @return List of all AllowedAnswers by specified AllowedQuestion
	 */
	List<AllowedAnswer> findAllByAllowedQuestion(AllowedQuestion allowedQuestion);
}
