package omis.detainernotification.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.DetainerNoteDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerNote;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Detainer note delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2017)
 * @since OMIS 3.0
 */
public class DetainerNoteDelegate {

	/* Data access objects */
	
	private DetainerNoteDao detainerNoteDao;
	
	/* Component retrievers */

	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Instance factories */
	
	private final InstanceFactory<DetainerNote> detainerNoteInstanceFactory;
	
	/**
	 * Instantiates an instance of detainer note delegate with the specified
	 * data access object, component retriever and instance factory.
	 * 
	 * @param detainerNoteDao detainer note data access object
	 * @param auditComponentRetriever audit component retriever
	 * @param detainerNoteInstanceFactory detainer note instance factory
	 */
	public DetainerNoteDelegate(final DetainerNoteDao detainerNoteDao,
			final AuditComponentRetriever auditComponentRetriever,
			final InstanceFactory<DetainerNote> detainerNoteInstanceFactory) {
		this.detainerNoteDao = detainerNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.detainerNoteInstanceFactory = detainerNoteInstanceFactory;
	}
	
	/**
	 * Creates a detainer note with the specified date and value for the 
	 * specified detainer.
	 * 
	 * @param detainer detainer
	 * @param value value
	 * @param date date
	 * @return newly created detainer note
	 * @throws DuplicateEntityFoundException thrown when a duplicate
	 * detainer note is found
	 */
	public DetainerNote create(final Detainer detainer, final String value,
			final Date date) throws DuplicateEntityFoundException {
		if (this.detainerNoteDao.find(detainer, value, date) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate detainer note found");
		}
		DetainerNote note = this.detainerNoteInstanceFactory.createInstance();
		note.setDetainer(detainer);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateNote(note, date, value);
		return detainerNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates the specified detainer note with the specified value and date.
	 * 
	 * @param value value
	 * @param date date
	 * @param note detainer note to update
	 * @return updated detainer note
	 * @throws DuplicateEntityFoundException thrown when a duplicate detainer
	 * note is found
	 */
	public DetainerNote update(final String value, final Date date,
			final DetainerNote note) throws DuplicateEntityFoundException {
		if (this.detainerNoteDao.findExcluding(note.getDetainer(),
				value, date, note) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate detainer note found");
		}
		this.populateNote(note, date, value);
		return detainerNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes the specified detainer note.
	 * 
	 * @param note detainer note to remove
	 */
	public void remove(final DetainerNote note) {
		this.detainerNoteDao.makeTransient(note);
	}
	
	/*
	 * Populates the specified detainer note with the specified date and
	 * value.
	 * 
	 * @param note detainer note to populate
	 * @param date date
	 * @param value value
	 * @return populated detainer note
	 */
	private DetainerNote populateNote(final DetainerNote note, final Date date,
			final String value) {
		note.setValue(value);
		note.setDate(date);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return note;
	}
}
