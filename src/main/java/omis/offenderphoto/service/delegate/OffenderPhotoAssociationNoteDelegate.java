package omis.offenderphoto.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offenderphoto.dao.OffenderPhotoAssociationNoteDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Offender photo association note delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationNoteDelegate {

	/* Exception messages. */
	private static final String DUPLICATE_NOTE_FOUND_EXCEPTION_MESSAGE
		= "Duplicate offender photo association note found";
	
	/* Instance factories. */
	private InstanceFactory<OffenderPhotoAssociationNote>
	offenderPhotoAssociationNoteInstanceFactory;
	
	/* Data access objects. */
	private OffenderPhotoAssociationNoteDao offenderPhotoAssociationNoteDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	/**
	 * Instantiates an offender photo association note delegate with the
	 * specified instance factory, data access object, and audit component
	 * retriever.
	 * 
	 * @param offenderPhotoAssociationNoteInstanceFactory offender photo
	 * association note instance factory
	 * @param offenderPhotoAssociationNoteDao offender photo association note
	 * data access object
	 * @param auditComponentRetriever audit component retriever
	 */
	public OffenderPhotoAssociationNoteDelegate(
			final InstanceFactory<OffenderPhotoAssociationNote>
			offenderPhotoAssociationNoteInstanceFactory,
			final OffenderPhotoAssociationNoteDao 
			offenderPhotoAssociationNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderPhotoAssociationNoteDao = offenderPhotoAssociationNoteDao;
		this.offenderPhotoAssociationNoteInstanceFactory 
			= offenderPhotoAssociationNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Transaction methods. */
	/**
	 * Creates an offender photo association note with the specified values
	 * for the specified offender photo association.
	 * 
	 * @param value value
	 * @param date date
	 * @param association offender photo association
	 * @return created offender photo association note
	 * @throws DuplicateEntityFoundException thrown when a duplicate offender
	 * photo association note is found
	 */
	public OffenderPhotoAssociationNote create(final String value,
			final Date date, final OffenderPhotoAssociation association)
		throws DuplicateEntityFoundException {
		if (this.offenderPhotoAssociationNoteDao
				.find(value, date, association) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_NOTE_FOUND_EXCEPTION_MESSAGE);
		}
		OffenderPhotoAssociationNote note 
			= this.offenderPhotoAssociationNoteInstanceFactory.createInstance();
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateNote(value, date, association, note);
		return this.offenderPhotoAssociationNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates the specified offender photo association note with the
	 * specified values.
	 * 
	 * @param note offender photo association note
	 * @param value value
	 * @param date date
	 * @param association offender photo association
	 * @return updated offender photo association note
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderPhotoAssociationNote update(
			final OffenderPhotoAssociationNote note,
			final String value, final Date date,
			final OffenderPhotoAssociation association)
					throws DuplicateEntityFoundException {
		if (this.offenderPhotoAssociationNoteDao
				.findExcluding(value, date, association, note) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_NOTE_FOUND_EXCEPTION_MESSAGE);
		}
		this.populateNote(value, date, association, note);
		return this.offenderPhotoAssociationNoteDao.makePersistent(note);
	}
	
	/**
	 * Removes the specified offender photo association note.
	 * 
	 * @param note offender photo association note
	 */
	public void remove(final OffenderPhotoAssociationNote note) {
		this.offenderPhotoAssociationNoteDao.makeTransient(note);
	}
	
	/**
	 * Removes all offender photo association notes for the specified
	 * association.
	 * 
	 * @param association association
	 */
	public void removeByAssociation(final OffenderPhotoAssociation association) {
		this.offenderPhotoAssociationNoteDao.removeByAssociation(association);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified offender photo association with the specified
	 * values.
	 * 
	 * @param value value
	 * @param date date
	 * @param association offender photo association
	 * @param note offender photo association note
	 * @return populated offender photo association note
	 */
	private OffenderPhotoAssociationNote populateNote(final String value,
			final Date date, final OffenderPhotoAssociation association,
			final OffenderPhotoAssociationNote note) {
		note.setValue(value);
		note.setDate(date);
		note.setAssociation(association);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return note;
	}
}