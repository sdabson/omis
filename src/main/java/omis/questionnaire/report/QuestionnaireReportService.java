package omis.questionnaire.report;

import java.util.List;

import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireReportService {
	
	/**
	 * Returns a list of QuestionnaireTypeSummaries by 
	 * specified QuestionnaireCategory
	 * @param questionnaireCategory
	 * @return List&lt;QuestionnaireTypeSummary&gt; - list of 
	 * QuestionnaireTypeSummaries by  specified QuestionnaireCategory
	 */
	List<QuestionnaireTypeSummary>
		findQuestionnaireTypeSummariesByQuestionnaireCategory(
				QuestionnaireCategory questionnaireCategory);
	
	/**
	 * Returns a list of all QuestionnaireTypeSummaries
	 * @return List of all QuestionnaireTypeSummaries
	 */
	List<QuestionnaireTypeSummary> findAllQuestionnaireTypeSummaries();
	
	/**
	 * Returns a list of QuestionnaireSectionSummaries by specified
	 * QuestionnaireType
	 * @param questionnaireType
	 * @return List&lt;QuestionnaireSectionSummary&gt; - list of
	 * QuestionnaireSectionSummaries by specified QuestionnaireType
	 */
	List<QuestionnaireSectionSummary>
		findQuestionnaireSectionSummariesByQuestionnaireType(
				QuestionnaireType questionnaireType);
	
	/**
	 * Returns a QuestionnaireTypeSummary of specified QuestionnaireType
	 * @param questionnaireType
	 * @return QuestionnaireTypeSummary
	 */
	QuestionnaireTypeSummary summarize(QuestionnaireType questionnaireType);
	
	/**
	 * Returns a QuestionSummary of specified AllowedQuestion
	 * @param allowedQuestion
	 * @return QuestionSummary
	 */
	QuestionSummary summarize(AllowedQuestion allowedQuestion);
	
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
	
	/**
	 * Returns a count of administeredQuestionnaires by questionnaireType
	 * @param questionnaireType
	 * @return Integer - count of administeredQuestionnaires by 
	 * questionnaireType
	 */
	Integer findAdministeredQuestionnaireCountByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
}
