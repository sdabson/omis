package omis.questionnaire.report;

import java.util.List;

import omis.person.domain.Person;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * AdministeredQuestionnaireReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireReportService {
	
	/**
	 * Returns a list of administered questionnaire summaries by specified
	 * properties
	 * @param answerer - Person
	 * @return list of administered questionnaire summaries by specified
	 * properties
	 */
	List<AdministeredQuestionnaireSummary> findQuestionnairesByAnswerer(
			Person answerer);
	
	/**
	 * Returns a list of administered questionnaire summaries by specified
	 * properties
	 * @param answerer - Person
	 * @param questionnaireType
	 * @return list of administered questionnaire summaries by specified
	 * properties
	 */
	List<AdministeredQuestionnaireSummary> 
		findQuestionnairesByAnswererAndQuestionnaireType(
			Person answerer, QuestionnaireType questionnaireType);
	
	/**
	 * Returns an administered questionnaire summary
	 * @param administeredQuestionnaire to summarize
	 * @return administeredQuestionnaireSummary
	 */
	AdministeredQuestionnaireSummary summarize(
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns a question summary found by specified allowed question
	 * @param allowedQuestion - specified allowedQuestion
	 * @return questionSummary
	 */
	QuestionSummary summarize(AllowedQuestion allowedQuestion);
	
	/**
	 * Returns a list of Questionnaire Section Summaries by specified
	 * administered questionnaire
	 * @param administeredQuestionnaire
	 * @return list of Questionnaire Section Summaries by specified
	 * administered questionnaire
	 */
	List<AdministeredQuestionnaireSectionSummary> 
		findAdministeredQuestionnaireSectionSummariesByAdministeredQuestionnaire(
				AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns a list of question summaries by specified questionnaire section
	 * @param questionnaireSection
	 * @return list of question summaries by specified questionnaire section
	 */
	List<QuestionSummary> findQuestionSummariesByQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of answer value summaries by specified allowedQuestion
	 * @param allowedQuestion
	 * @return list of answer value summaries by specified question
	 */
	List<AnswerValueSummary> findAnswerValueSummariesByAllowedQuestion(
			AllowedQuestion allowedQuestion);
	
}
