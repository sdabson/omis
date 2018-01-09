package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.AllowedAnswerDao;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerValue;

/**
 * AllowedAnswerDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 20, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedAnswerDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Allowed Answer Already Exists For Given Question With Given"
			+ " Answer Value";
	
	private final AllowedAnswerDao allowedAnswerDao;
	
	private final InstanceFactory<AllowedAnswer> 
		allowedAnswerInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AllowedAnswerDelegate
	 * @param allowedAnswerDao
	 * @param allowedAnswerInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AllowedAnswerDelegate(
			final AllowedAnswerDao allowedAnswerDao,
			final InstanceFactory<AllowedAnswer> 
				allowedAnswerInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.allowedAnswerDao = allowedAnswerDao;
		this.allowedAnswerInstanceFactory = allowedAnswerInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an AllowedAnswer
	 * @param allowedQuestion
	 * @param sortOrder - Short
	 * @param answerValue
	 * @return AllowedAnswer - newly created AllowedAnswer
	 * @throws DuplicateEntityFoundException - When AllowedAnswer already exists
	 * with given answer value
	 */
	public AllowedAnswer create(final AllowedQuestion allowedQuestion, 
			final Short sortOrder, final AnswerValue answerValue)
			throws DuplicateEntityFoundException{
		if(this.allowedAnswerDao.find(allowedQuestion, answerValue) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AllowedAnswer allowedAnswer = 
				this.allowedAnswerInstanceFactory.createInstance();
		
		allowedAnswer.setAllowedQuestion(allowedQuestion);
		allowedAnswer.setSortOrder(sortOrder);
		allowedAnswer.setAnswerValue(answerValue);
		allowedAnswer.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		allowedAnswer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.allowedAnswerDao.makePersistent(allowedAnswer);
	}
	
	/**
	 * Updates an AllowedAnswer
	 * @param allowedAnswer - AllowedAnswer to update
	 * @param allowedQuestion
	 * @param sortOrder - Short
	 * @param answerValue
	 * @return AllowedAnswer - updated AllowedAnswer
	 * @throws DuplicateEntityFoundException - When AllowedAnswer already exists
	 * with given answer value
	 */
	public AllowedAnswer update(final AllowedAnswer allowedAnswer, 
			final AllowedQuestion allowedQuestion, final Short sortOrder,
			final AnswerValue answerValue)
			throws DuplicateEntityFoundException{
		if(this.allowedAnswerDao.findExcluding(allowedQuestion, answerValue, 
				allowedAnswer) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		allowedAnswer.setAllowedQuestion(allowedQuestion);
		allowedAnswer.setSortOrder(sortOrder);
		allowedAnswer.setAnswerValue(answerValue);
		allowedAnswer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.allowedAnswerDao.makePersistent(allowedAnswer);
	}
	
	/**
	 * Removes an AllowedAnswer
	 * @param allowedAnswer - AllowedAnswer to remove
	 */
	public void remove(final AllowedAnswer allowedAnswer){
		this.allowedAnswerDao.makeTransient(allowedAnswer);
	}
	
	/**
	 * Returns a list of all AllowedAnswers by specified AllowedQuestion
	 * @param allowedQuestion
	 * @return List of all AllowedAnswers by specified AllowedQuestion
	 */
	public List<AllowedAnswer> findAllByAllowedQuestion(
			final AllowedQuestion allowedQuestion){
		return this.allowedAnswerDao.findAllByAllowedQuestion(allowedQuestion);
	}
	
	
}
