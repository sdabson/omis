package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.AllowedQuestionDao;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AllowedQuestionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 20, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedQuestionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Allowed Question Already Exists For Given Questionnaire Section "
			+ "With Given Question and Question Number";
	
	private final AllowedQuestionDao allowedQuestionDao;
	
	private final InstanceFactory<AllowedQuestion> 
		allowedQuestionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AllowedQuestionDelegate
	 * @param allowedQuestionDao
	 * @param allowedQuestionInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AllowedQuestionDelegate(
			final AllowedQuestionDao allowedQuestionDao,
			final InstanceFactory<AllowedQuestion> 
				allowedQuestionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.allowedQuestionDao = allowedQuestionDao;
		this.allowedQuestionInstanceFactory = allowedQuestionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates an AllowedQuestion
	 * @param questionnaireSection
	 * @param question
	 * @param valid - Boolean 
	 * @param sortOrder - Short
	 * @param questionNumber - String
	 * @param questionConditionality
	 * @param answerCardinality
	 * @param questionHelp - String
	 * @return AllowedQuestion - newly created AllowedQuestion
	 * @throws DuplicateEntityFoundException - when AllowedQuestion already 
	 * exists with given question and questionNumber
	 */
	public AllowedQuestion create(
			final QuestionnaireSection questionnaireSection, 
			final Question question, final Boolean valid, final Short sortOrder,
			final String questionNumber, 
			final QuestionConditionality questionConditionality, 
			final AnswerCardinality answerCardinality, final String questionHelp)
			throws DuplicateEntityFoundException{
		if(this.allowedQuestionDao.find(questionnaireSection, question, 
				questionNumber) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AllowedQuestion allowedQuestion = 
				this.allowedQuestionInstanceFactory.createInstance();
		
		allowedQuestion.setQuestionnaireSection(questionnaireSection);
		allowedQuestion.setQuestion(question);
		allowedQuestion.setValid(valid);
		allowedQuestion.setSortOrder(sortOrder);
		allowedQuestion.setQuestionNumber(questionNumber);
		allowedQuestion.setQuestionConditionality(questionConditionality);
		allowedQuestion.setAnswerCardinality(answerCardinality);
		allowedQuestion.setQuestionHelp(questionHelp);
		allowedQuestion.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		allowedQuestion.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.allowedQuestionDao.makePersistent(allowedQuestion);
	}
	
	/**
	 * Updates an AllowedQuestion
	 * @param allowedQuestion - Allowed Question to update
	 * @param questionnaireSection
	 * @param question
	 * @param valid - Boolean 
	 * @param sortOrder - Short
	 * @param questionNumber - String
	 * @param questionConditionality
	 * @param answerCardinality
	 * @param questionHelp - String
	 * @return AllowedQuestion - updated AllowedQuestion
	 * @throws DuplicateEntityFoundException - when AllowedQuestion already 
	 * exists with given question and questionNumber
	 */
	public AllowedQuestion update(final AllowedQuestion allowedQuestion,
			final QuestionnaireSection questionnaireSection, 
			final Question question, final Boolean valid, 
			final Short sortOrder, final String questionNumber, 
			final QuestionConditionality questionConditionality, 
			final AnswerCardinality answerCardinality, final String questionHelp)
			throws DuplicateEntityFoundException{
		if(this.allowedQuestionDao.findExcluding(questionnaireSection, question, 
				questionNumber, allowedQuestion) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		allowedQuestion.setQuestionnaireSection(questionnaireSection);
		allowedQuestion.setQuestion(question);
		allowedQuestion.setValid(valid);
		allowedQuestion.setSortOrder(sortOrder);
		allowedQuestion.setQuestionNumber(questionNumber);
		allowedQuestion.setQuestionConditionality(questionConditionality);
		allowedQuestion.setAnswerCardinality(answerCardinality);
		allowedQuestion.setQuestionHelp(questionHelp);
		allowedQuestion.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.allowedQuestionDao.makePersistent(allowedQuestion);
	}
	
	/**
	 * Removes an AllowedQuestion
	 * @param allowedQuestion - AllowedQuestion to remove
	 */
	public void remove(final AllowedQuestion allowedQuestion){
		this.allowedQuestionDao.makeTransient(allowedQuestion);
	}
	
	
	
	/**
	 * Returns a list of AllowedQuestions by specified questionnaireSection
	 * @param questionnaireSection
	 * @return list of AllowedQuestions by specified questionnaireSection
	 */
	public List<AllowedQuestion> findAllByQuestionnaireSection(
			QuestionnaireSection questionnaireSection){
		return this.allowedQuestionDao
				.findAllByQuestionnaireSection(questionnaireSection);
	}
	
	/**
	 * Returns a list of AllowedQuestions including invalid AllowedQuestions
	 * by specified questionnaireSection
	 * @param questionnaireSection
	 * @return list of AllowedQuestions  including invalid AllowedQuestions 
	 * by specified questionnaireSection
	 */
	public List<AllowedQuestion> findAllByQuestionnaireSectionIncludingInvalid(
			QuestionnaireSection questionnaireSection){
		return this.allowedQuestionDao
				.findAllByQuestionnaireSectionIncludingInvalid(
						questionnaireSection);
	}

}
