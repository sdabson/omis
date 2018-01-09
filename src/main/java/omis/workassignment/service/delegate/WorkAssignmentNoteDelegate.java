package omis.workassignment.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workassignment.dao.WorkAssignmentNoteDao;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentNote;

/**
 * Delegate for work assignment note.
 *
 * @author Yidong Li
 * @version 0.0.2 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentNoteDelegate {
	/* Resources. */
	private final WorkAssignmentNoteDao workAssignmentNoteDao;
	private final InstanceFactory<WorkAssignmentNote> workAssignmentNoteInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for managing work assignment notes.
	 * 
	 * @param workAssignmentNoteInstanceFactory instance factory for work 
	 * assignment note
	 * @param auditComponentRetriever audit component retriever
	 * @param workAssignmentNoteDao data access object for work assignment note
	 */
	public WorkAssignmentNoteDelegate(
		final WorkAssignmentNoteDao workAssignmentNoteDao,
		final InstanceFactory<WorkAssignmentNote> workAssignmentNoteInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		this.workAssignmentNoteDao = workAssignmentNoteDao;
		this.workAssignmentNoteInstanceFactory = workAssignmentNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/* Methods. */
	/**
	 * Adds work assignment note.
	 * 
	 * @param workAssignment work assignment
	 * @param date date
	 * @param value value
	 * @throws DuplicateEntityFoundException
	 * @return work assignment note
	 */
	public WorkAssignmentNote addNote(final WorkAssignment workAssignment, 
		final Date date, final String value) 
		throws DuplicateEntityFoundException {
		if (this.workAssignmentNoteDao.findExistingWorkAssignmentNote(
			workAssignment,	date, value)!=null) {
				throw new DuplicateEntityFoundException("Work assignment note "
				+ "already exist");
		}
		WorkAssignmentNote workAssignmentNote 
			= this.workAssignmentNoteInstanceFactory.createInstance();
		workAssignmentNote.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		workAssignmentNote.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		workAssignmentNote.setDate(date);
		workAssignmentNote.setValue(value);
		workAssignmentNote.setAssignment(workAssignment);
		return this.workAssignmentNoteDao.makePersistent(workAssignmentNote);
	}	
	
	/**
	 * Updates an existing work assignment note
	 * 
	 * @param workAssignmentNote work assignment note
	 * @param date date
	 * @param notes notes
	 * @return a work assignment note
	 * @throws DuplicateEntityFoundException if work assignment note exists
	 */
	public WorkAssignmentNote updateNote(final WorkAssignmentNote note, 
		final Date date, final String value) throws DuplicateEntityFoundException{
		if (this.workAssignmentNoteDao.findExcludedExistingWorkAssignmentNote(
			note, note.getAssignment(), date, value)!=null) {
			throw new DuplicateEntityFoundException("Work assignment note "
			+ "already exist");
		}
		
		note.setDate(date);
		note.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		note.setValue(value);
		return this.workAssignmentNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes work assignment note.
	 * 
	 * @param workAssignmentNote work assignment note
	 */
	public void removeNote(final WorkAssignmentNote note) {
		this.workAssignmentNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns a list of work assignment notes.
	 * @param workAssignment work assignment
	 * @return a list of work assignment notes.
	 */
	public List<WorkAssignmentNote> findNotes(final WorkAssignment workAssignment) {
		return this.workAssignmentNoteDao.findNotes(workAssignment);
	}
}