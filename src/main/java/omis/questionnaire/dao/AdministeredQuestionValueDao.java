package omis.questionnaire.dao;

import java.util.List;
import omis.dao.GenericDao;
import omis.questionnaire.domain.AdministeredQuestionValue;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * Administered Question Value Data access object.
 * 
 *@author Annie Wahl 
 *@version 0.1.2 (Mar 12, 2019)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionValueDao 
	extends GenericDao<AdministeredQuestionValue> {
	
	/**
	 * Returns AdministeredQuestionValue with specified properties.
	 * @param question - Question
	 * @param answerValue - Answer Value
	 * @param answerValueText - String answer value text
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @param questionnaireSection - Questionnaire Section
	 * @return AdministeredQuestionValue with specified properties.
	 */
	AdministeredQuestionValue find(Question question, AnswerValue answerValue,
			String answerValueText,
			AdministeredQuestionnaire administeredQuestionnaire, 
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns AdministeredQuestionValue with specified properties excluding
	 * specified AdministeredQuestionValue.
	 * @param question - Question
	 * @param answerValue - Answer Value
	 * @param answerValueText - String answer value text
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @param questionnaireSection - Questionnaire Section
	 * @param excludedAdministeredQuestionValue - Administered Question Value
	 * to exclude
	 * @return AdministeredQuestionValue with specified properties excluding
	 * specified AdministeredQuestionValue.
	 */
	AdministeredQuestionValue findExcluding(Question question, 
			AnswerValue answerValue, String answerValueText, 
			AdministeredQuestionnaire administeredQuestionnaire, 
			QuestionnaireSection questionnaireSection, 
			AdministeredQuestionValue excludedAdministeredQuestionValue);
	
	
	/**
	 * Returns an AdministeredQuestionValue by specified question
	 * and Administered Questionnaire Section Status.
	 * @param question - Question
	 * @param administeredQuestionnaireSectionStatus - Administered
	 * Questionnaire Section Status
	 * @return AdministeredQuestionValue by specified question
	 * and Administered Questionnaire Section Status.
	 */
	AdministeredQuestionValue
		findByQuestionAndAdministeredQuestionnaireSectionStatus(
			Question question, 
			AdministeredQuestionnaireSectionStatus
				administeredQuestionnaireSectionStatus);
	
	/**
	 * Returns Administered Question Value with specified properties
	 * and that does not contain an Answer Value.
	 * @param question - Question
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @param questionnaireSection - Questionnaire Section
	 * @return AdministeredQuestionValue - Administered Question Value with
	 * specified properties and that does not contain an Answer Value. 
	 */
	AdministeredQuestionValue findByNoAnswerValue(Question question,
			AdministeredQuestionnaire administeredQuestionnaire,
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of AdministeredQuestionValues by specified question
	 * and Administered Questionnaire Section Status.
	 * @param question - Question
	 * @param administeredQuestionnaireSectionStatus - Administered
	 * Questionnaire Section Status
	 * @return List of AdministeredQuestionValues by specified question
	 * and Administered Questionnaire Section Status.
	 */
	List<AdministeredQuestionValue> 
		findAllByQuestionAndAdministeredQuestionnaireSectionStatus(
				Question question, 
				AdministeredQuestionnaireSectionStatus
					administeredQuestionnaireSectionStatus);
	
}
