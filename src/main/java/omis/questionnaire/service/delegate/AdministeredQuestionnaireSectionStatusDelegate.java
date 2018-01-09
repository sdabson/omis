package omis.questionnaire.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.AdministeredQuestionnaireSectionStatusDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionStatusDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 16, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionStatusDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Administered Questionnaire Section Status Already Exists";
	
	private final AdministeredQuestionnaireSectionStatusDao
		administeredQuestionnaireSectionStatusDao;
	
	private final InstanceFactory<AdministeredQuestionnaireSectionStatus>
		administeredQuestionnaireSectionStatusInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AdministeredQuestionnaireSectionStatusDelegate
	 * @param administeredQuestionnaireSectionStatusDao
	 * @param administeredQuestionnaireSectionStatusInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AdministeredQuestionnaireSectionStatusDelegate(
			final AdministeredQuestionnaireSectionStatusDao 
				administeredQuestionnaireSectionStatusDao,
			final InstanceFactory<AdministeredQuestionnaireSectionStatus> 
				administeredQuestionnaireSectionStatusInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.administeredQuestionnaireSectionStatusDao = 
				administeredQuestionnaireSectionStatusDao;
		this.administeredQuestionnaireSectionStatusInstanceFactory = 
				administeredQuestionnaireSectionStatusInstanceFactory;
		this.auditComponentRetriever = 
				auditComponentRetriever;
	}
	
	/**
	 * Creates a new AdministeredQuestionnaireSectionStatus
	 * @param questionnaireSection
	 * @param administeredQuestionnaire
	 * @param draft - Boolean
	 * @param date
	 * @return created AdministeredQuestionnaireSectionStatus
	 * @throws DuplicateEntityFoundException - when an
	 * AdministeredQuestionnaireSectionStatus already exists with given properties
	 */
	public AdministeredQuestionnaireSectionStatus create(
			final QuestionnaireSection questionnaireSection,
			final AdministeredQuestionnaire administeredQuestionnaire,
			final Boolean draft, final Date date) 
					throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireSectionStatusDao.find(
				questionnaireSection, administeredQuestionnaire, draft) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AdministeredQuestionnaireSectionStatus 
			administeredQuestionnaireSectionStatus =
			this.administeredQuestionnaireSectionStatusInstanceFactory
			.createInstance();
		
		administeredQuestionnaireSectionStatus
			.setAdministeredQuestionnaire(administeredQuestionnaire);
		administeredQuestionnaireSectionStatus
			.setQuestionnaireSection(questionnaireSection);
		administeredQuestionnaireSectionStatus.setDate(date);
		administeredQuestionnaireSectionStatus.setDraft(draft);
		administeredQuestionnaireSectionStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		administeredQuestionnaireSectionStatus.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireSectionStatusDao
				.makePersistent(administeredQuestionnaireSectionStatus);
	}
	
	/**
	 * Updates an existing AdministeredQuestionnaireSectionStatus
	 * @param administeredQuestionnaireSectionStatus - 
	 * AdministeredQuestionnaireSectionStatus  to update
	 * @param draft - Boolean
	 * @param date 
	 * @return Updated AdministeredQuestionnaireSectionStatus
	 * @throws DuplicateEntityFoundException - when an
	 * AdministeredQuestionnaireSectionStatus already exists with given
	 * properties
	 */
	public AdministeredQuestionnaireSectionStatus update(
			final AdministeredQuestionnaireSectionStatus 
				administeredQuestionnaireSectionStatus,
			final Boolean draft, final Date date) 
					throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireSectionStatusDao.findExcluding(
				administeredQuestionnaireSectionStatus
					.getQuestionnaireSection(),
				administeredQuestionnaireSectionStatus
					.getAdministeredQuestionnaire(), 
				draft, administeredQuestionnaireSectionStatus) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		administeredQuestionnaireSectionStatus.setDate(date);
		administeredQuestionnaireSectionStatus.setDraft(draft);
		administeredQuestionnaireSectionStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireSectionStatusDao
				.makePersistent(administeredQuestionnaireSectionStatus);
	}
	
	/**
	 * Removes an AdministeredQuestionnaireSectionStatus
	 * @param administeredQuestionnaireSectionStatus to remove
	 */
	public void remove(final AdministeredQuestionnaireSectionStatus 
			administeredQuestionnaireSectionStatus){
		this.administeredQuestionnaireSectionStatusDao
			.makeTransient(administeredQuestionnaireSectionStatus);
	}
	
	/**
	 * Returns a list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 * @param administeredQuestionnaire
	 * @return list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 */
	public List<AdministeredQuestionnaireSectionStatus> 
		findByAdministeredQuestionnaire(
				final AdministeredQuestionnaire administeredQuestionnaire){
		return this.administeredQuestionnaireSectionStatusDao
				.findByAdministeredQuestionnaire(administeredQuestionnaire);
	}
}
