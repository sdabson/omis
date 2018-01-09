package omis.questionnaire.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.questionnaire.dao.AdministeredQuestionnaireDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * AdministeredQuestionnaireDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Administered Questionnaire Already Exists For Given Answerer";
	
	private final AdministeredQuestionnaireDao administeredQuestionnaireDao;
	
	private final InstanceFactory<AdministeredQuestionnaire> 
		administeredQuestionnaireInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AdministeredQuestionnaireDelegate
	 * @param administeredQuestionnaireDao
	 * @param administeredQuestionnaireInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AdministeredQuestionnaireDelegate(
			final AdministeredQuestionnaireDao administeredQuestionnaireDao,
			final InstanceFactory<AdministeredQuestionnaire> 
				administeredQuestionnaireInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.administeredQuestionnaireDao = administeredQuestionnaireDao;
		this.administeredQuestionnaireInstanceFactory = 
				administeredQuestionnaireInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new AdministeredQuestionnaire
	 * @param answerer - Person
	 * @param draft - Boolean
	 * @param comments - String
	 * @param assessor - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @return newly created AdministeredQuestionnaire
	 * @throws DuplicateEntityFoundException - when an administered
	 * questionnaire already exists for given answerer
	 */
	public AdministeredQuestionnaire create(final Person answerer, 
			final Boolean draft, final String comments, final Person assessor,
			final Date date, final QuestionnaireType questionnaireType)
				throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireDao.find(answerer, date, 
				questionnaireType) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AdministeredQuestionnaire administeredQuestionnaire =
				this.administeredQuestionnaireInstanceFactory.createInstance();
		
		administeredQuestionnaire.setAnswerer(answerer);
		administeredQuestionnaire.setAssessor(assessor);
		administeredQuestionnaire.setComments(comments);
		administeredQuestionnaire.setDate(date);
		administeredQuestionnaire.setDraft(draft);
		administeredQuestionnaire.setQuestionnaireType(questionnaireType);
		administeredQuestionnaire.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		administeredQuestionnaire.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireDao
				.makePersistent(administeredQuestionnaire);
	}
	
	/**
	 * Updates an administered questionnaire
	 * @param administeredQuestionnaire - administered questionnaire to update
	 * @param draft - Boolean
	 * @param comments - String
	 * @param assessor - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @return updated AdministeredQuestionnaire
	 * @throws DuplicateEntityFoundException - when a separate administered 
	 * questionnaire already exists for that answerer
	 */
	public AdministeredQuestionnaire update(
			final AdministeredQuestionnaire administeredQuestionnaire,
			final Boolean draft, final String comments, final Person assessor,
			final Date date, final QuestionnaireType questionnaireType)
				throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireDao.findExcluding(
				administeredQuestionnaire.getAnswerer(), date, 
				questionnaireType, administeredQuestionnaire) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		administeredQuestionnaire.setAssessor(assessor);
		administeredQuestionnaire.setComments(comments);
		administeredQuestionnaire.setDate(date);
		administeredQuestionnaire.setDraft(draft);
		administeredQuestionnaire.setQuestionnaireType(questionnaireType);
		administeredQuestionnaire.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireDao
				.makePersistent(administeredQuestionnaire);
	}
	
	/**
	 * Removes an administered questionnaire
	 * @param administeredQuestionnaire - administered questionnaire to remove
	 */
	public void remove(
			final AdministeredQuestionnaire administeredQuestionnaire){
		this.administeredQuestionnaireDao
			.makeTransient(administeredQuestionnaire);
	}
	
	
}
