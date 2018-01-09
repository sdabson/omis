package omis.courtcase.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.dao.CourtCaseNoteDao;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.CourtCaseNote;
import omis.courtcase.exception.CourtCaseNoteExistsException;
import omis.instance.factory.InstanceFactory;

public class CourtCaseNoteDelegate {
	
	private final CourtCaseNoteDao courtCaseNoteDao;
	
	/* Instance factories. */
	private final InstanceFactory<CourtCaseNote> courtCaseNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param courtCaseNoteDao court case dao
	 * @param courtCaseNoteInstanceFactory court case instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public CourtCaseNoteDelegate(final CourtCaseNoteDao courtCaseNoteDao,
			final InstanceFactory<CourtCaseNote> courtCaseNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.courtCaseNoteDao = courtCaseNoteDao;
		this.courtCaseNoteInstanceFactory = courtCaseNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Adds a note to the specified court case with the specified date and 
	 * value.
	 * 
	 * @param courtCase court case
	 * @param date date
	 * @param value value
	 * @return court case note
	 * @throws CourtCaseNoteExistsException occurs when duplicate note is found
	 */
	public CourtCaseNote addNote(final CourtCase courtCase, final Date date, 
			final String value) 
			throws CourtCaseNoteExistsException {
		if (this.courtCaseNoteDao.findCourtCaseNote(courtCase, date, value) 
				!= null) {
			throw new CourtCaseNoteExistsException("Duplicate court case note "
					+ "found.");
		}
		CourtCaseNote courtCaseNote = this.courtCaseNoteInstanceFactory
				.createInstance();
		courtCaseNote.setCourtCase(courtCase);
		courtCaseNote.setDate(date);
		courtCaseNote.setValue(value);
		courtCaseNote.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		courtCaseNote.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		return this.courtCaseNoteDao.makePersistent(courtCaseNote);
	}
	
	/**
	 * Updates the specified note with the date and value.
	 * 
	 * @param courtCaseNote court case note
	 * @param date date
	 * @param value value
	 * @return court case note
	 * @throws CourtCaseNoteExistsException occurs when duplicate note is found
	 */
	public CourtCaseNote updateNote(final CourtCaseNote courtCaseNote, 
			final Date date, final String value) 
					throws CourtCaseNoteExistsException {
		if (this.courtCaseNoteDao.findCourtCaseNoteExcluding(courtCaseNote
				.getCourtCase(), date, value, courtCaseNote) != null) {
			throw new CourtCaseNoteExistsException("Duplicate court case note "
					+ "found.");
		}
		courtCaseNote.setDate(date);
		courtCaseNote.setValue(value);
		courtCaseNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.courtCaseNoteDao.makePersistent(courtCaseNote);
	}
	
	/**
	 * Remove the specified note.
	 * 
	 * @param courtCaseNote court case note
	 */
	public void removeNote(CourtCaseNote courtCaseNote) {
		this.courtCaseNoteDao.makeTransient(courtCaseNote);
	}
	
	/**
	 * Find all notes for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return list of notes
	 */
	public List<CourtCaseNote> findNotes(CourtCase courtCase) {
		return this.courtCaseNoteDao.findByCourtCase(courtCase);
	}
	
}
