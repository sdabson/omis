package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;

/**
 * PsychologicalSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryNoteDelegate {
	
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Psychological Section Summary Note already exists with given"
			+ " Description and Date for specified Psychological Section Summary";
	
	private final PsychologicalSectionSummaryNoteDao
			psychologicalSectionSummaryNoteDao;
	
	private final InstanceFactory<PsychologicalSectionSummaryNote> 
			psychologicalSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PsychologicalSectionSummaryNoteDelegate
	 * @param psychologicalSectionSummaryNoteDao
	 * @param psychologicalSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PsychologicalSectionSummaryNoteDelegate(
			final PsychologicalSectionSummaryNoteDao
				psychologicalSectionSummaryNoteDao,
			final InstanceFactory<PsychologicalSectionSummaryNote> 
				psychologicalSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.psychologicalSectionSummaryNoteDao =
				psychologicalSectionSummaryNoteDao;
		this.psychologicalSectionSummaryNoteInstanceFactory =
				psychologicalSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PsychologicalSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return Newly Created PsychologicalSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when a 
	 * PsychologicalSectionSummaryNote already exists with given description,
	 * date, and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryNote create(
			final String description,
			final Date date,
			final PsychologicalSectionSummary psychologicalSectionSummary)
					throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryNoteDao.find(description, date,
				psychologicalSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PsychologicalSectionSummaryNote psychologicalSectionSummaryNote = 
				this.psychologicalSectionSummaryNoteInstanceFactory
						.createInstance();
		
		psychologicalSectionSummaryNote.setDescription(description);
		psychologicalSectionSummaryNote.setDate(date);
		psychologicalSectionSummaryNote.setPsychologicalSectionSummary(
				psychologicalSectionSummary);
		psychologicalSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		psychologicalSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryNoteDao
				.makePersistent(psychologicalSectionSummaryNote);
	}
	
	/**
	 * Updates a PsychologicalSectionSummaryNote with specified parameters
	 * @param psychologicalSectionSummaryNote - PsychologicalSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated PsychologicalSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when another
	 * PsychologicalSectionSummaryNote already exists with given description,
	 * date, and PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummaryNote update(
			final PsychologicalSectionSummaryNote psychologicalSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryNoteDao.findExcluding(description,
				date, psychologicalSectionSummaryNote
						.getPsychologicalSectionSummary(),
				psychologicalSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		psychologicalSectionSummaryNote.setDescription(description);
		psychologicalSectionSummaryNote.setDate(date);
		psychologicalSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryNoteDao
				.makePersistent(psychologicalSectionSummaryNote);
	}
	
	/**
	 * Removes specified PsychologicalSectionSummaryNote
	 * @param psychologicalSectionSummaryNote - PsychologicalSectionSummaryNote 
	 * to remove
	 */
	public void remove(
			final PsychologicalSectionSummaryNote psychologicalSectionSummaryNote){
		this.psychologicalSectionSummaryNoteDao
			.makeTransient(psychologicalSectionSummaryNote);
	}
	
	/**
	 * Returns a list of PsychologicalSectionSummaryNotes found by specified
	 * PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary
	 * @return List of PsychologicalSectionSummaryNotes
	 */
	public List<PsychologicalSectionSummaryNote> 
			findByPsychologicalSectionSummary(
					final PsychologicalSectionSummary psychologicalSectionSummary){
		return this.psychologicalSectionSummaryNoteDao
				.findByPsychologicalSectionSummary(psychologicalSectionSummary);
	}
	
	
	
	
}
