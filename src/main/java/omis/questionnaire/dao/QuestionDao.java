package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;

/**
 * QuestionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionDao extends GenericDao<Question> {
	
	/**
	 * Returns a Question with specified properties
	 * @param questionCategory
	 * @param text - String
	 * @return Question
	 */
	Question find(QuestionCategory questionCategory, String text);
	
	/**
	 * Returns a Question with specified properties excluding specified question
	 * @param questionCategory
	 * @param text - String
	 * @param excludedQuestion - Question to exclude
	 * @return Question
	 */
	Question findExcluding(QuestionCategory questionCategory, String text,
			Question excludedQuestion);
	
	/**
	 * Returns a list of Questions by question text
	 * @param text - String
	 * @return List of questions by specified description
	 */
	List<Question> findByText(String text);
	
}
