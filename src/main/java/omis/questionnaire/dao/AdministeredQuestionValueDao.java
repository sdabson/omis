package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AdministeredQuestionValue;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionValueDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionValueDao 
	extends GenericDao<AdministeredQuestionValue> {
	
	/**
	 * Returns AdministeredQuestionValue with specified properties
	 * @param question
	 * @param answerValue
	 * @param answerValueText - String
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @return AdministeredQuestionValue
	 */
	AdministeredQuestionValue find(Question question, AnswerValue answerValue,
			String answerValueText, 
			AdministeredQuestionnaire administeredQuestionnaire, 
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns AdministeredQuestionValue with specified properties excluding
	 * specified AdministeredQuestionValue
	 * @param question
	 * @param answerValue
	 * @param answerValueText - String
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @param excludedAdministeredQuestionValue
	 * @return AdministeredQuestionValue to exclude
	 */
	AdministeredQuestionValue findExcluding(Question question, 
			AnswerValue answerValue, String answerValueText, 
			AdministeredQuestionnaire administeredQuestionnaire, 
			QuestionnaireSection questionnaireSection, 
			AdministeredQuestionValue excludedAdministeredQuestionValue);
	
	
	/**
	 * Returns an AdministeredQuestionValue by specified question
	 * @param question
	 * @param administeredQuestionnaire
	 * @return AdministeredQuestionValue
	 */
	AdministeredQuestionValue findByQuestionAndAdministeredQuestionnaire(
			Question question, 
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns a list of AdministeredQuestionValues by specified question
	 * @param question
	 * @param administeredQuestionnaire
	 * @return List of AdministeredQuestionValue
	 */
	List<AdministeredQuestionValue> 
		findAllByQuestionAndAdministeredQuestionnaire(Question question, 
				AdministeredQuestionnaire administeredQuestionnaire);
	
}
