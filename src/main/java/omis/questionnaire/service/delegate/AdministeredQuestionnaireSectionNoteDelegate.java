package omis.questionnaire.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.AdministeredQuestionnaireSectionNoteDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionNote;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Administered Questionnaire Section Note Already Exists";
	
	private final AdministeredQuestionnaireSectionNoteDao 
		administeredQuestionnaireSectionNoteDao;
	
	private final InstanceFactory<AdministeredQuestionnaireSectionNote>
		administeredQuestionnaireSectionNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaireSectionNoteDao
	 * @param administeredQuestionnaireSectionNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AdministeredQuestionnaireSectionNoteDelegate(
			final AdministeredQuestionnaireSectionNoteDao 
				administeredQuestionnaireSectionNoteDao,
			final InstanceFactory<AdministeredQuestionnaireSectionNote> 
				administeredQuestionnaireSectionNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.administeredQuestionnaireSectionNoteDao = administeredQuestionnaireSectionNoteDao;
		this.administeredQuestionnaireSectionNoteInstanceFactory = administeredQuestionnaireSectionNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @param comments - String
	 * @return AdministeredQuestionnaireSectionNote
	 * @throws DuplicateEntityFoundException - when 
	 * AdministeredQuestionnaireSectionNote already exists with given properties
	 */
	public AdministeredQuestionnaireSectionNote create(
			final AdministeredQuestionnaire administeredQuestionnaire,
			final QuestionnaireSection questionnaireSection, 
			final String comments) throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireSectionNoteDao.find(
			questionnaireSection, comments, administeredQuestionnaire) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AdministeredQuestionnaireSectionNote 
			administeredQuestionnaireSectionNote = 
			this.administeredQuestionnaireSectionNoteInstanceFactory
			.createInstance();
		
		administeredQuestionnaireSectionNote.setAdministeredQuestionnaire(administeredQuestionnaire);
		administeredQuestionnaireSectionNote.setComments(comments);
		administeredQuestionnaireSectionNote.setQuestionnaireSection(questionnaireSection);
		administeredQuestionnaireSectionNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		administeredQuestionnaireSectionNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireSectionNoteDao
				.makePersistent(administeredQuestionnaireSectionNote);
	}
	
	/**
	 * Updates an existing AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaireSectionNote -
	 * AdministeredQuestionnaireSectionNote to update
	 * @param questionnaireSection
	 * @param comments - String
	 * @return AdministeredQuestionnaireSectionNote
	 * @throws DuplicateEntityFoundException - when
	 * AdministeredQuestionnaireSectionNote already exists with given properties
	 * for its administeredQuestionnaire
	 * 
	 */
	public AdministeredQuestionnaireSectionNote update(
			final AdministeredQuestionnaireSectionNote 
				administeredQuestionnaireSectionNote,
			final QuestionnaireSection questionnaireSection, 
			final String comments) throws DuplicateEntityFoundException{
		if(this.administeredQuestionnaireSectionNoteDao.findExcluding(
			questionnaireSection, comments, 
			administeredQuestionnaireSectionNote.getAdministeredQuestionnaire(), 
			administeredQuestionnaireSectionNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		
		administeredQuestionnaireSectionNote.setComments(comments);
		administeredQuestionnaireSectionNote.setQuestionnaireSection(questionnaireSection);
		administeredQuestionnaireSectionNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.administeredQuestionnaireSectionNoteDao
				.makePersistent(administeredQuestionnaireSectionNote);
	}
	
	/**
	 * Removes an AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaireSectionNote
	 */
	public void remove(final AdministeredQuestionnaireSectionNote 
			administeredQuestionnaireSectionNote){
		this.administeredQuestionnaireSectionNoteDao
			.makeTransient(administeredQuestionnaireSectionNote);
	}
	
	/**
	 * Returns an AdministeredQuestionnaireSectionNote found by 
	 * AdministeredQuestionnaire and QuestionnaireSection
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @return AdministeredQuestionnaireSectionNote
	 */
	public AdministeredQuestionnaireSectionNote findByQuestionnaireAndSection(
			final AdministeredQuestionnaire administeredQuestionnaire,
			final QuestionnaireSection questionnaireSection){
		return this.administeredQuestionnaireSectionNoteDao
				.findByQuestionnaireAndSection(administeredQuestionnaire, 
						questionnaireSection);
	}
}
