package omis.questionnaire.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * QuestionService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionService {
	
	
	/**
	 * Creates an AllowedQuestion
	 * @param questionnaireSection
	 * @param question
	 * @param questionConditionality
	 * @param answerCardinality
	 * @param questionNumber - String
	 * @param sortOrder - Short
	 * @param valid - Boolean
	 * @param questionHelp - String
	 * @return Newly Created AllowedQuestion
	 * @throws DuplicateEntityFoundException - When AllowedQuestion already 
	 * exists with given QuestionnaireSection, Question, and QuestionNumber
	 */
	public AllowedQuestion createAllowedQuestion(
			QuestionnaireSection questionnaireSection, Question question,
			QuestionConditionality questionConditionality,
			AnswerCardinality answerCardinality, String questionNumber,
			Short sortOrder, Boolean valid, String questionHelp)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an AllowedQuestion
	 * @param allowedQuestion - AllowedQuestion to update
	 * @param questionnaireSection
	 * @param question
	 * @param questionConditionality
	 * @param answerCardinality
	 * @param questionNumber - String
	 * @param sortOrder - Short
	 * @param valid - Boolean
	 * @param questionHelp - String
	 * @return Updated AllowedQuestion
	 * @throws DuplicateEntityFoundException - When AllowedQuestion already 
	 * exists with given QuestionnaireSection, Question, and QuestionNumber
	 */
	public AllowedQuestion updateAllowedQuestion(
			AllowedQuestion allowedQuestion,
			QuestionnaireSection questionnaireSection, Question question,
			QuestionConditionality questionConditionality,
			AnswerCardinality answerCardinality, String questionNumber,
			Short sortOrder, Boolean valid, String questionHelp)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates a Question
	 * @param questionCategory
	 * @param text - String
	 * @param statik - Boolean
	 * @param valid - Boolean
	 * @return Question - Newly Created Question
	 * @throws DuplicateEntityFoundException - When Question already exists
	 * with given Text
	 */
	public Question createQuestion(QuestionCategory questionCategory,
			String text, Boolean statik, Boolean valid)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a Question
	 * @param question - Question to remove
	 * @param questionCategory
	 * @param text - String
	 * @param statik - Boolean
	 * @param valid - Boolean
	 * @return Question - Updated Question
	 * @throws DuplicateEntityFoundException - When Question already exists
	 * with given Text
	 */
	public Question updateQuestion(Question question,
			QuestionCategory questionCategory, String text, Boolean statik,
			Boolean valid) throws DuplicateEntityFoundException;
	
	/**
	 * Creates an AnswerValue
	 * @param description - String
	 * @param value - String
	 * @return AnswerValue - newly created AnswerValue
	 * @throws DuplicateEntityFoundException - When AnswerValue already exists
	 * with given Description
	 */
	public AnswerValue createAnswerValue(String description, String value)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates an AnswerValue
	 * @param answerValue - AnswerValue to update
	 * @param description - String
	 * @param value - String
	 * @return AnswerValue - updated AnswerValue
	 * @throws DuplicateEntityFoundException - When AnswerValue already exists
	 * with given Description
	 */
	public AnswerValue updateAnswerValue(AnswerValue answerValue, 
			String description, String value)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates an AllowedAnswer
	 * @param allowedQuestion
	 * @param answerValue
	 * @param sortOrder - Short
	 * @return AllowedAnswer - newly created AllowedAnswer
	 * @throws DuplicateEntityFoundException - When AllowedAnswer already exists
	 * with given answer value
	 */
	public AllowedAnswer createAllowedAnswer(AllowedQuestion allowedQuestion,
			AnswerValue answerValue, Short sortOrder)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an AllowedAnswer
	 * @param allowedAnswer - AllowedAnswer to update
	 * @param allowedQuestion
	 * @param answerValue
	 * @param sortOrder - Short
	 * @return AllowedAnswer - updated AllowedAnswer
	 * @throws DuplicateEntityFoundException - When AllowedAnswer already exists
	 * with given answer value
	 */
	public AllowedAnswer updateAllowedAnswer(AllowedAnswer allowedAnswer,
			AllowedQuestion allowedQuestion, AnswerValue answerValue,
			Short sortOrder) throws DuplicateEntityFoundException;
	
	/**
	 * Removes a question
	 * @param question - Question to remove
	 */
	public void removeQuestion(Question question);
	
	/**
	 * Removes an allowedQuestion
	 * @param allowedQuestion - allowedQuestion to remove
	 */
	public void removeAllowedQuestion(AllowedQuestion allowedQuestion);
	
	/**
	 * Removes an AnswerValue
	 * @param answerValue - answeValue to remove
	 */
	public void removeAnswerValue(AnswerValue answerValue);
	
	/**
	 * Removes an AllowedAnswer
	 * @param allowedAnswer - AllowedAnswer to remove
	 */
	public void removeAllowedAnswer(AllowedAnswer allowedAnswer);
	
	/**
	 * Returns a list of AllowedQuestions by specified questionnaireSection
	 * @param questionnaireSection
	 * @return list of AllowedQuestions by specified questionnaireSection
	 */
	public List<AllowedQuestion> findAllowedQuestionsByQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of AllowedQuestions by specified questionnaireSection
	 * including invalid AllowedQuestions
	 * @param questionnaireSection
	 * @return list of AllowedQuestions by specified questionnaireSection 
	 * including invalid AllowedQuestions
	 */
	public List<AllowedQuestion> findAllAllowedQuestionsByQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of all AllowedAnswers by specified AllowedQuestion
	 * @param allowedQuestion
	 * @return List of all AllowedAnswers by specified AllowedQuestion
	 */
	public List<AllowedAnswer> findAllowedAnswersByAllowedQuestion(
			AllowedQuestion allowedQuestion);

	/**
	 * Returns a list of Questions by question text
	 * @param text - String
	 * @return List of questions by specified text
	 */
	public List<Question> findQuestionsByText(String text);
	
	/**
	 * Returns a list of answerValues by description
	 * @param description - String
	 * @return List of answerValues by specified description
	 */
	public List<AnswerValue> findAnswerValuesByDescription(String description);
	
	/**
	 * Returns an allowedQuestion with true/false options set as allowedAnswers
	 * @param allowedQuestion
	 * @return allowedQuestion with true/false options set as allowedAnswers
	 */
	public AllowedQuestion setTrueFalseAllowedAnswersToAllowedQuestion(
			AllowedQuestion allowedQuestion)
					throws DuplicateEntityFoundException;
	
}
