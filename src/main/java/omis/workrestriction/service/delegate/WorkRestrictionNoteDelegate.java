package omis.workrestriction.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workrestriction.dao.WorkRestrictionNoteDao;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * WorkRestrictionNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Work Restriction Note Already Exists with Given Parameters";
	
	private final WorkRestrictionNoteDao workRestrictionNoteDao;
	
	private final InstanceFactory<WorkRestrictionNote> 
		workRestrictionNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Default Constructor for WorkRestrictionNoteDelegate
	 * @param workRestrictionNoteDao
	 * @param workRestrictionNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public WorkRestrictionNoteDelegate(
			WorkRestrictionNoteDao workRestrictionNoteDao,
			InstanceFactory<WorkRestrictionNote> 
				workRestrictionNoteInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.workRestrictionNoteDao = workRestrictionNoteDao;
		this.workRestrictionNoteInstanceFactory = 
				workRestrictionNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates and returns a workRestrictionNote with given parameters
	 * @param workRestriction
	 * @param value - String
	 * @param date
	 * @return workRestrictionNote with given parameters
	 * @throws DuplicateEntityFoundException - when a workRestrictionNote 
	 * already exists with given parameters
	 */
	public WorkRestrictionNote create(final WorkRestriction workRestriction,
			final String value, final Date date) 
					throws DuplicateEntityFoundException{
		if(this.workRestrictionNoteDao.find(workRestriction, date, value) 
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WorkRestrictionNote workRestrictionNote = 
				this.workRestrictionNoteInstanceFactory.createInstance();
		
		workRestrictionNote.setWorkRestriction(workRestriction);
		workRestrictionNote.setDate(date);
		workRestrictionNote.setValue(value);
		workRestrictionNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		workRestrictionNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.workRestrictionNoteDao.makePersistent(workRestrictionNote);
	}
	
	/**
	 * Updates and returns a workRestrictionNote with given parameters
	 * @param workRestrictionNote
	 * @param value - String
	 * @param date
	 * @return updated workRestrictionNote with given parameters
	 * @throws DuplicateEntityFoundException - when a workRestrictionNote 
	 * already exists with given parameters
	 */
	public WorkRestrictionNote update(
			final WorkRestrictionNote workRestrictionNote,
			final String value, final Date date)
					throws DuplicateEntityFoundException{
		if(this.workRestrictionNoteDao.findExcluding(workRestrictionNote, 
				workRestrictionNote.getWorkRestriction(), date, value) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		workRestrictionNote.setValue(value);
		workRestrictionNote.setDate(date);
		workRestrictionNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.workRestrictionNoteDao.makePersistent(workRestrictionNote);
	}
	
	/**
	 * Removes the specified workRestrictionNote
	 * @param workRestrictionNote - work restriction note to remove
	 */
	public void remove(WorkRestrictionNote workRestrictionNote){
		this.workRestrictionNoteDao.makeTransient(workRestrictionNote);
	}
	
	/**
	 * Finds and returns a list of all workRestrictionNotes by specified 
	 * workRestriction
	 * @param workRestriction
	 * @return list of all workRestrictionNotes by specified workRestriction
	 */
	public List<WorkRestrictionNote> findByWorkRestriction(
			final WorkRestriction workRestriction){
		return this.workRestrictionNoteDao
				.findAllByWorkRestriction(workRestriction);
	}
	
}
