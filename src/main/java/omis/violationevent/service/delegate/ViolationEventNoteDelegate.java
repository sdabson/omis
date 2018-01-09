package omis.violationevent.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.violationevent.dao.ViolationEventNoteDao;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventNote;

/**
 * ViolationEventNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */

public class ViolationEventNoteDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Violation Event Note already exists with specified date and"
			+ " description for given Violation Event";
	
	private final ViolationEventNoteDao violationEventNoteDao;
	
	private final InstanceFactory<ViolationEventNote> 
		violationEventNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ViolationEventNoteDelegate
	 * @param violationEventNoteDao
	 * @param violationEventNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ViolationEventNoteDelegate(
			final ViolationEventNoteDao violationEventNoteDao,
			final InstanceFactory<ViolationEventNote> 
				violationEventNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.violationEventNoteDao = violationEventNoteDao;
		this.violationEventNoteInstanceFactory =
				violationEventNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a ViolationEventNote with specified properties
	 * @param date - Date
	 * @param description - String
	 * @param violationEvent - ViolationEvent
	 * @return Newly created ViolationEventNote
	 * @throws DuplicateEntityFoundException - When a ViolationEventNote already
	 * exists with specified date and description for given ViolationEvent
	 */
	public ViolationEventNote create(final Date date, final String description,
			final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException{
		if(this.violationEventNoteDao
				.find(date, description, violationEvent) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ViolationEventNote violationEventNote = 
				this.violationEventNoteInstanceFactory.createInstance();
		
		violationEventNote.setDate(date);
		violationEventNote.setDescription(description);
		violationEventNote.setViolationEvent(violationEvent);
		violationEventNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		violationEventNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.violationEventNoteDao.makePersistent(violationEventNote);
	}
	
	/**
	 * Updates a ViolationEventNote with specified properties
	 * @param violationEventNote - ViolationEventNote to update
	 * @param date - Date
	 * @param description - String 
	 * @return Updated ViolationEventNote
	 * @throws DuplicateEntityFoundExcepton - when a ViolationEventNote already
	 * exists with given date and description for its ViolationEvent
	 */
	public ViolationEventNote update(
			final ViolationEventNote violationEventNote, final Date date,
			final String description)
					throws DuplicateEntityFoundException{
		if(this.violationEventNoteDao.findExcluding(violationEventNote, date,
				description, violationEventNote.getViolationEvent()) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		violationEventNote.setDate(date);
		violationEventNote.setDescription(description);
		violationEventNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.violationEventNoteDao.makePersistent(violationEventNote);
	}
	
	/**
	 * Removes a ViolationEventNote
	 * @param violationEventNote - ViolationEventNote to remove
	 */
	public void remove(final ViolationEventNote violationEventNote){
		this.violationEventNoteDao.makeTransient(violationEventNote);
	}
	
	
	/**
	 * Finds and returns a list of all ViolationEventNotes by ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of violationEventNotes by specified ViolationEvent
	 */
	public List<ViolationEventNote> findByViolationEvent(
			final ViolationEvent violationEvent){
		return violationEventNoteDao.findByViolationEvent(violationEvent);
	}
	
}
