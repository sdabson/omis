package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.EducationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;

/**
 * EducationSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Education Section Summary Note already exists with provided "
			+ "description and date for specified Education Section Summary";
	
	private final EducationSectionSummaryNoteDao educationSectionSummaryNoteDao;
	
	private final InstanceFactory<EducationSectionSummaryNote> 
		educationSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EducationSectionSummaryNoteDelegate
	 * @param educationSectionSummaryNoteDao
	 * @param educationSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public EducationSectionSummaryNoteDelegate(
			final EducationSectionSummaryNoteDao educationSectionSummaryNoteDao,
			final InstanceFactory<EducationSectionSummaryNote> 
				educationSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.educationSectionSummaryNoteDao = educationSectionSummaryNoteDao;
		this.educationSectionSummaryNoteInstanceFactory =
				educationSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an EducationSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return Newly created EducationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummaryNote
	 * already exists with given date and description for specified
	 * EducationSectionSummary
	 */
	public EducationSectionSummaryNote create(final String description,
			final Date date, final EducationSectionSummary educationSectionSummary)
			throws DuplicateEntityFoundException{
		if(this.educationSectionSummaryNoteDao.find(description, date,
				educationSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationSectionSummaryNote educationSectionSummaryNote = 
				this.educationSectionSummaryNoteInstanceFactory.createInstance();
		
		educationSectionSummaryNote.setDate(date);
		educationSectionSummaryNote.setDescription(description);
		educationSectionSummaryNote.setEducationSectionSummary(
				educationSectionSummary);
		educationSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationSectionSummaryNoteDao.makePersistent(
				educationSectionSummaryNote);
	}
	
	/**
	 * Updates an EducationSectionSummaryNote with specified parameters
	 * @param educationSectionSummaryNote - EducationSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated EducationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummaryNote
	 * already exists with given date and description for its EducationSectionSummary
	 */
	public EducationSectionSummaryNote update(
			final EducationSectionSummaryNote educationSectionSummaryNote,
			final String description, final Date date)
			throws DuplicateEntityFoundException{
		if(this.educationSectionSummaryNoteDao.findExcluding(description, date,
				educationSectionSummaryNote.getEducationSectionSummary(),
				educationSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationSectionSummaryNote.setDate(date);
		educationSectionSummaryNote.setDescription(description);
		educationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationSectionSummaryNoteDao.makePersistent(
				educationSectionSummaryNote);
	}
	
	/**
	 * Removes specified EducationSectionSummaryNote
	 * @param educationSectionSummaryNote - EducationSectionSummaryNote to 
	 * remove
	 */
	public void remove(
			final EducationSectionSummaryNote educationSectionSummaryNote){
		this.educationSectionSummaryNoteDao.makeTransient(
				educationSectionSummaryNote);
	}
	
	/**
	 * Returns a list of EducationSectionSummaryNotes by EducationSectionSummary
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return List of EducationSectionSummaryNotes
	 */
	public List<EducationSectionSummaryNote> findByEducationSectionSummary(
			final EducationSectionSummary educationSectionSummary){
		return this.educationSectionSummaryNoteDao
				.findByEducationSectionSummary(educationSectionSummary);
	}
	
	
}
