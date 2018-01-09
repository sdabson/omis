package omis.grievance.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceNoteDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceNote;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 8, 2015)
 * @since OMIS 3.0
 */
public class GrievanceNoteDelegate {

	/* Resources. */
	
	private final GrievanceNoteDao grievanceNoteDao;
	
	private final InstanceFactory<GrievanceNote> grievanceNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Delegate for grievance notes.
	 * 
	 * @param grievanceNoteDao data access object for grievance notes
	 * @param grievanceNoteInstanceFactory instance factory for grievance
	 * notes
	 * @param auditComponentRetriever audit component retriever
	 */
	public GrievanceNoteDelegate(
			final GrievanceNoteDao grievanceNoteDao,
			final InstanceFactory<GrievanceNote> grievanceNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.grievanceNoteDao = grievanceNoteDao;
		this.grievanceNoteInstanceFactory = grievanceNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Grievance note management methods. */
	
	/**
	 * Creates note for grievance.
	 * 
	 * @param grievance grievance for which to create note
	 * @param date date
	 * @param value value
	 * @return new note for grievance
	 * @throws DuplicateEntityFoundException if grievance note exists
	 */
	public GrievanceNote create(final Grievance grievance, final Date date,
			final String value) throws DuplicateEntityFoundException {
		if (this.grievanceNoteDao.find(grievance, date, value) != null) {
			throw new DuplicateEntityFoundException("Grievance note exists");
		}
		GrievanceNote note = this.grievanceNoteInstanceFactory.createInstance();
		note.setGrievance(grievance);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.grievanceNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates note for grievance.
	 * 
	 * @param note note to update
	 * @param date date
	 * @param value value
	 * @return updated note for grievance
	 * @throws DuplicateEntityFoundException if grievance note exists
	 */
	public GrievanceNote update(final GrievanceNote note, final Date date,
			final String value) throws DuplicateEntityFoundException {
		if (this.grievanceNoteDao.findExcluding(
				note.getGrievance(), date, value, note) != null) {
			throw new DuplicateEntityFoundException("Grievance note exists");
		}
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.grievanceNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes note.
	 * 
	 * @param note note
	 */
	public void remove(final GrievanceNote note) {
		this.grievanceNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns notes for grievance.
	 * 
	 * @param grievance grievance for which to find notes
	 * @return notes for grievance
	 */
	public List<GrievanceNote> findByGrievance(final Grievance grievance) {
		return this.grievanceNoteDao.findByGrievance(grievance);
	}
}