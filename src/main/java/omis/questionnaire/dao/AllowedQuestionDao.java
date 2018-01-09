package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AllowedQuestionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AllowedQuestionDao extends GenericDao<AllowedQuestion> {
	
	/**
	 * Returns allowed question with specified properties
	 * @param questionnaireSection
	 * @param question
	 * @param questionNumber - String
	 * @return AllowedQuestion with specified properties
	 */
	AllowedQuestion find(QuestionnaireSection questionnaireSection, 
			Question question, String questionNumber);
	
	/**
	 * Returns allowed question with specified properties excluding 
	 * specified allowed question
	 * @param questionnaireSection
	 * @param question
	 * @param questionNumber - String
	 * @param excludedAllowedQuestion - allowedQuestion to exclude
	 * @return AllowedQuestion with specified properties excluding 
	 * specified allowed question
	 */
	AllowedQuestion findExcluding(QuestionnaireSection questionnaireSection, 
			Question question, String questionNumber, 
			AllowedQuestion excludedAllowedQuestion);
	
	/**
	 * Returns a list of AllowedQuestions by specified questionnaireSection
	 * @param questionnaireSection
	 * @return list of AllowedQuestions by specified questionnaireSection
	 */
	List<AllowedQuestion> findAllByQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of AllowedQuestions including invalid AllowedQuestions
	 * by specified questionnaireSection
	 * @param questionnaireSection
	 * @return list of AllowedQuestions  including invalid AllowedQuestions 
	 * by specified questionnaireSection
	 */
	List<AllowedQuestion> findAllByQuestionnaireSectionIncludingInvalid(
			QuestionnaireSection questionnaireSection);
	
}
