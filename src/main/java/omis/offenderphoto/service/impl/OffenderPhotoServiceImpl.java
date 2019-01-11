package omis.offenderphoto.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.BusinessException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.offenderphoto.dao.OffenderPhotoAssociationDao;
import omis.offenderphoto.dao.OffenderPhotoAssociationNoteDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;
import omis.offenderphoto.service.OffenderPhotoService;
import omis.offenderphoto.service.delegate.OffenderPhotoAssociationNoteDelegate;

/**
 * Implementation of service for offender photos.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Feb 23, 2014)
 * @since OMIS 3.0
 */
public class OffenderPhotoServiceImpl
		implements OffenderPhotoService {

	/* Data access objects. */
	
	private final OffenderPhotoAssociationDao offenderPhotoAssociationDao;
	private final PhotoDao photoDao;
	private final OffenderPhotoAssociationNoteDao
	offenderPhotoAssociationNoteDao;
	
	/* Service Delegates. */
	
	private final OffenderPhotoAssociationNoteDelegate
	offenderPhotoAssociationNoteDelegate;

	/* Instance factories. */
	
	private final InstanceFactory<OffenderPhotoAssociation>
	offenderPhotoAssociationInstanceFactory;
	private final InstanceFactory<Photo> photoInstanceFactory;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;

	/* Constructor. */
	/**
	 * Instantiates an implementation of service for offender photo association.
	 * 
	 * @param offenderPhotoAssociationDao data access object for offender photo
	 * associations
	 * @param offenderPhotoAssociationInstanceFactory instance factory for
	 * offender photo associations
	 * @param photoDao data access object for photos
	 * @param photoInstanceFactory instance factory for photos
	 * @param auditComponentRetriever retriever of audit components
	 */
	public OffenderPhotoServiceImpl(
			final OffenderPhotoAssociationDao offenderPhotoAssociationDao,
			final InstanceFactory<OffenderPhotoAssociation>
				offenderPhotoAssociationInstanceFactory,
			final PhotoDao photoDao,
			final OffenderPhotoAssociationNoteDao
				offenderPhotoAssociationNoteDao,
			final OffenderPhotoAssociationNoteDelegate
			offenderPhotoAssociationNoteDelegate,
			final InstanceFactory<Photo> photoInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderPhotoAssociationDao = offenderPhotoAssociationDao;
		this.offenderPhotoAssociationInstanceFactory
			= offenderPhotoAssociationInstanceFactory;
		this.photoDao = photoDao;
		this.offenderPhotoAssociationNoteDao = offenderPhotoAssociationNoteDao;
		this.photoInstanceFactory = photoInstanceFactory;
		this.offenderPhotoAssociationNoteDelegate
			= offenderPhotoAssociationNoteDelegate;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation findProfilePhotoAssociation(
			final Offender offender) {
		return this.offenderPhotoAssociationDao
				.findProfilePhotoAssociation(offender);
	}

	/** {@inheritDoc} */
	@Override
	public Photo findProfilePhoto(final Offender offender) {
		OffenderPhotoAssociation association = this.offenderPhotoAssociationDao
				.findProfilePhotoAssociation(offender);
		if (association != null) {
			return association.getPhoto();
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderPhotoAssociation> findByOffender(
			final Offender offender) {
		return this.offenderPhotoAssociationDao.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void setOffenderProfilePhoto(
			final OffenderPhotoAssociation association) {
		OffenderPhotoAssociation current =
				this.offenderPhotoAssociationDao.findProfilePhotoAssociation(
						(Offender) association.getPerson());
		if (current != null) {
			current.setProfile(false);
		}
		association.setProfile(true);
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final OffenderPhotoAssociation association)
			throws BusinessException {
		this.offenderPhotoAssociationNoteDelegate
		.removeByAssociation(association);
		Photo photo = association.getPhoto();
		this.offenderPhotoAssociationDao.makeTransient(association);
		this.photoDao.makeTransient(photo);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation associateNoneProfilePhotoFile(
			final Offender offender, final String filename,
			final Date photoDate) {
		return this.createOffenderPhotoAssociation(
				offender, filename, photoDate, false);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation associateProfilePhotoFile(
			final Offender offender, final String filename,
			final Date photoDate) {
		OffenderPhotoAssociation current =
				this.offenderPhotoAssociationDao.findProfilePhotoAssociation(
						offender);
		if (current != null) {
			current.setProfile(false);
			this.offenderPhotoAssociationDao.makePersistent(current);
		}
		return this.createOffenderPhotoAssociation(
				offender, filename, photoDate, true);
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociation updateAssociation(
			final OffenderPhotoAssociation offenderPhotoAssociation,
			final Date photoDate) {
		offenderPhotoAssociation.getPhoto().setDate(photoDate);
		this.photoDao.makePersistent(
				offenderPhotoAssociation.getPhoto());
		return offenderPhotoAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderPhotoAssociationNote> findAssociationNotes(
			final OffenderPhotoAssociation association) {
		return this.offenderPhotoAssociationNoteDao
				.findByAssociation(association);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociationNote addAssociationNote(
			final OffenderPhotoAssociation association, final Date date,
			String value) throws DuplicateEntityFoundException {
		return this.offenderPhotoAssociationNoteDelegate.create(value, date,
				association);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderPhotoAssociationNote updateAssociationNote(
			final OffenderPhotoAssociationNote associationNote, final Date date,
			final String value) throws DuplicateEntityFoundException {
		return this.offenderPhotoAssociationNoteDelegate.update(associationNote,
				value, date, associationNote.getAssociation());
	}

	/** {@inheritDoc} */
	@Override
	public void removeAssociationNote(
			final OffenderPhotoAssociationNote associationNote) {
		this.offenderPhotoAssociationNoteDelegate.remove(associationNote);
	}
	
	/* Helper methods. */
	
	// Creates offender photo association
	private OffenderPhotoAssociation createOffenderPhotoAssociation(
			final Offender offender, final String filename,
			final Date photoDate, final Boolean profile) {
		Photo photo = photoInstanceFactory.createInstance();
		photo.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setDate(photoDate);
		photo.setFilename(filename);
		photo = this.photoDao.makePersistent(photo);
		OffenderPhotoAssociation offenderPhotoAssociation
			= this.offenderPhotoAssociationInstanceFactory.createInstance();
		offenderPhotoAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		offenderPhotoAssociation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		offenderPhotoAssociation.setPhoto(photo);
		offenderPhotoAssociation.setPerson(offender);
		offenderPhotoAssociation.setProfile(profile);
		return this.offenderPhotoAssociationDao.makePersistent(
				offenderPhotoAssociation);
	}
}