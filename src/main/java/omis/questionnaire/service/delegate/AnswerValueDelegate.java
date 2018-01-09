package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.AnswerValueDao;
import omis.questionnaire.domain.AnswerValue;

/**
 * AnswerValueDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 20, 2016)
 *@since OMIS 3.0
 *
 */
public class AnswerValueDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Answer Value Already Exists With Given Description";
	
	private final AnswerValueDao answerValueDao;
	
	private final InstanceFactory<AnswerValue> 
		answerValueInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AnswerValueDelegate
	 * @param answerValueDao
	 * @param answerValueInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AnswerValueDelegate(
			final AnswerValueDao answerValueDao,
			final InstanceFactory<AnswerValue> 
				answerValueInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.answerValueDao = answerValueDao;
		this.answerValueInstanceFactory = answerValueInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates an AnswerValue
	 * @param description - String
	 * @param value - String
	 * @return AnswerValue - newly created AnswerValue
	 * @throws DuplicateEntityFoundException - When AnswerValue already exists
	 * with given Description
	 */
	public AnswerValue create(final String description, final String value)
			throws DuplicateEntityFoundException{
		if(this.answerValueDao.find(description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AnswerValue answerValue = 
				this.answerValueInstanceFactory.createInstance();
		
		answerValue.setDescription(description);
		answerValue.setValue(value);
		answerValue.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		answerValue.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.answerValueDao.makePersistent(answerValue);
	}
	
	/**
	 * Updates an AnswerValue
	 * @param answerValue - AnswerValue to update
	 * @param description - String
	 * @param value - String
	 * @return AnswerValue - updated AnswerValue
	 * @throws DuplicateEntityFoundException - When AnswerValue already exists
	 * with given Description
	 */
	public AnswerValue update(final AnswerValue answerValue, 
			final String description, final String value)
			throws DuplicateEntityFoundException{
		if(this.answerValueDao.findExcluding(description, answerValue) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		answerValue.setDescription(description);
		answerValue.setValue(value);
		answerValue.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.answerValueDao.makePersistent(answerValue);
	}
	
	/**
	 * Removes an AnswerValue
	 * @param answerValue - answeValue to remove
	 */
	public void remove(final AnswerValue answerValue){
		this.answerValueDao.makeTransient(answerValue);
	}
	
	/**
	 * Returns a list of answerValues by description
	 * @param description - String
	 * @return List of answerValues by specified description
	 */
	public List<AnswerValue> findAnswerValuesByDescription(
			final String description){
		return this.answerValueDao.findByDescription(description);
	}
	
	/**
	 * Returns the "true" answerValue for true/false questions (as "Yes")
	 * @return AnswerValue - the "false" answerValue for true/false questions
	 * (as "Yes")
	 */
	public AnswerValue findTrue(){
		return this.answerValueDao.find("Yes");
	}
	
	/**
	 * Returns the "false" answerValue for true/false questions (as "No")
	 * @return AnswerValue - the "false" answerValue for true/false questions
	 * (as "No")
	 */
	public AnswerValue findFalse(){
		return this.answerValueDao.find("No");
	}
	
	
}
