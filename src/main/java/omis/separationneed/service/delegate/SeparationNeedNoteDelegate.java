package omis.separationneed.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.separationneed.dao.SeparationNeedNoteDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;

/**
 * Delegate for separation need notes.
 *
 * @author Josh Divine
 * @version 0.0.1 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public class SeparationNeedNoteDelegate {

	private final SeparationNeedNoteDao separationNeedNoteDao;
	
	private final InstanceFactory<SeparationNeedNote>
		separationNeedNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a delegate for separation need notes.
	 * 
	 * @param separationNeedNoteDao separation need note data access object
	 * @param separationNeedNoteInstanceFactory separation need note instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SeparationNeedNoteDelegate(
			final SeparationNeedNoteDao separationNeedNoteDao,
			final InstanceFactory<SeparationNeedNote>
				separationNeedNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.separationNeedNoteDao = separationNeedNoteDao;
		this.separationNeedNoteInstanceFactory = 
				separationNeedNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a separation need note.
	 * 
	 * @param separationNeed separation need
	 * @param date date
	 * @param value note value
	 * @return created separation need note
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need note is found
	 */
	public SeparationNeedNote create(final SeparationNeed separationNeed,
			final Date date, final String value)
		throws DuplicateEntityFoundException {
		if (this.separationNeedNoteDao.find(value, date, separationNeed)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation need note found");
		}
		SeparationNeedNote note = this.separationNeedNoteInstanceFactory
				.createInstance();
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		note.setSeparationNeed(separationNeed);
		this.populateSeparationNeedNote(note, date, value);
		return this.separationNeedNoteDao.makePersistent(note);
	}

	/**
	 * Updates the specified separation need note.
	 * 
	 * @param note separation need note
	 * @param date date
	 * @param value note value
	 * @return updated separation need note
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need note is found
	 */
	public SeparationNeedNote update(final SeparationNeedNote note,
			final Date date, final String value)
		throws DuplicateEntityFoundException {
		if (this.separationNeedNoteDao.findExcluding(value, date, 
				note.getSeparationNeed(), note) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate separation needs note found");
		}
		this.populateSeparationNeedNote(note, date, value);
		return this.separationNeedNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes the specified separation need note.
	 * 
	 * @param note separation need note
	 */
	public void remove(final SeparationNeedNote note) {
		this.separationNeedNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns a list of separation need notes for the specified separation
	 * need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need notes
	 */
	public List<SeparationNeedNote> findBySeparationNeed(
			SeparationNeed separationNeed) {
		return this.separationNeedNoteDao.findBySeparationNeed(separationNeed);
	}

	// Populates a separation need note
	private SeparationNeedNote populateSeparationNeedNote(
			final SeparationNeedNote note, final Date date, 
			final String value) {
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return note;
	}
}
