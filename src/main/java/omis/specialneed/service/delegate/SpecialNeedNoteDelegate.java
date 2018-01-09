package omis.specialneed.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedNoteDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedNote;

/**
 * Special need classification delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedNoteDelegate {

	/* Data access objects. */
	
	private final SpecialNeedNoteDao specialNeedNoteDao;
	

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedNote> 
			specialNeedNoteInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of special need delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedNoteDao special need note data access object
	 * @param specialNeedNoteInstanceFactory special need note instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SpecialNeedNoteDelegate(
			final SpecialNeedNoteDao specialNeedNoteDao,
			final InstanceFactory<SpecialNeedNote> 
				specialNeedNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.specialNeedNoteDao = specialNeedNoteDao;
		this.specialNeedNoteInstanceFactory = 
				specialNeedNoteInstanceFactory; 
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Return a list of special need notes for this special need.
	 *
	 *
	 * @param specialNeed special need
	 * @return list of special need notes
	 */
	public List<SpecialNeedNote> findNotes(final SpecialNeed specialNeed) {
		return this.specialNeedNoteDao.findBySpecialNeed(specialNeed);
	}	
	
	/**
	 * Returns a new special need note.
	 *
	 *
	 * @param specialNeed special need
	 * @param date date
	 * @param value value
	 * @return special need note
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeedNote createNote(final SpecialNeed specialNeed, 
			final Date date, final String value)
			throws DuplicateEntityFoundException {
		if (this.specialNeedNoteDao.find(value, date, specialNeed) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need note found");
		}
		SpecialNeedNote note = this.specialNeedNoteInstanceFactory
				.createInstance();
		note.setSpecialNeed(specialNeed);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		populateSpecialNeedNote(note, date, value);
		return this.specialNeedNoteDao.makePersistent(note);
	}
	
	/**
	 * Returns an updated special need note.
	 *
	 *
	 * @param note note
	 * @param date date
	 * @param value value
	 * @return special need note
	 * @throws DuplicateEntityFoundException
	 */
	public SpecialNeedNote updateNote(final SpecialNeedNote note, 
			final Date date, final SpecialNeed specialNeed, final String value)
			throws DuplicateEntityFoundException {
		if (this.specialNeedNoteDao.findExcluding(value, date, 
				specialNeed, note) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate special need note found");
		}
		populateSpecialNeedNote(note, date, value);
		return this.specialNeedNoteDao.makePersistent(note);
	}

	/**
	 * Removes a special need note.
	 *
	 *
	 * @param note note
	 */
	public void removeNote(final SpecialNeedNote note) {
		this.specialNeedNoteDao.makeTransient(note);
	}

	// Populates a special need note
	private void populateSpecialNeedNote(final SpecialNeedNote note, 
			final Date date, final String value) {
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
