package omis.physicalfeature.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.physicalfeature.dao.FeatureClassificationDao;
import omis.physicalfeature.dao.PhysicalFeatureAssociationDao;
import omis.physicalfeature.dao.PhysicalFeatureAssociationNoteDao;
import omis.physicalfeature.dao.PhysicalFeatureDao;
import omis.physicalfeature.dao.PhysicalFeaturePhotoAssociationDao;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;
import omis.physicalfeature.service.PhysicalFeatureAssociationService;

/**
 * Offender physical feature service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationServiceImpl 
	implements PhysicalFeatureAssociationService {

	/* Data Access Objects */
	
	private PhysicalFeatureAssociationDao physicalFeatureAssociationDao;
	
	private PhotoDao photoDao;
	
	private PhysicalFeaturePhotoAssociationDao
	physicalFeaturePhotoAssociationDao;
	
	private FeatureClassificationDao featureClassificationDao;
	
	private PhysicalFeatureDao physicalFeatureDao;
	
	private PhysicalFeatureAssociationNoteDao physicalFeatureAssociationNoteDao;
	
	/* Instance Factories */
	
	private final InstanceFactory<PhysicalFeatureAssociation>
	physicalFeatureAssociationInstanceFactory;
	
	private final InstanceFactory<Photo> photoInstanceFactory;
	
	private final InstanceFactory<PhysicalFeaturePhotoAssociation>
	physicalFeaturePhotoAssociationInstanceFactory;
	
	private final InstanceFactory<PhysicalFeatureAssociationNote>
	physicalFeatureAssociationNoteInstanceFactory;
	
	/* Audit Component Retriever */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a offender physical feature service implementation with 
	 * the specified data access object.
	 * 
	 * @param physicalFeatureAssociationDao physical feature association DAO
	 * @param physicalFeatureAssociationInstanceFactory physical feature
	 * association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public PhysicalFeatureAssociationServiceImpl(
			final PhysicalFeatureAssociationDao physicalFeatureAssociationDao,
			final PhotoDao photoDao,
			final PhysicalFeaturePhotoAssociationDao
			physicalFeaturePhotoAssociationDao,
			final FeatureClassificationDao featureClassificationDao,
			final PhysicalFeatureDao physicalFeatureDao,
			final PhysicalFeatureAssociationNoteDao
			physicalFeatureAssociationNoteDao,
			final InstanceFactory<PhysicalFeatureAssociation> 
			physicalFeatureAssociationInstanceFactory,
			final InstanceFactory<Photo> photoInstanceFactory,
			final InstanceFactory<PhysicalFeaturePhotoAssociation>
			physicalFeaturePhotoAssociationInstanceFactory,
			final InstanceFactory<PhysicalFeatureAssociationNote>
			physicalFeatureAssociationNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.physicalFeatureAssociationDao = physicalFeatureAssociationDao;
		this.photoDao = photoDao;
		this.physicalFeaturePhotoAssociationDao 
			= physicalFeaturePhotoAssociationDao;
		this.featureClassificationDao = featureClassificationDao;
		this.physicalFeatureDao = physicalFeatureDao;
		this.physicalFeatureAssociationNoteDao
			= physicalFeatureAssociationNoteDao;
		this.physicalFeatureAssociationInstanceFactory 
			= physicalFeatureAssociationInstanceFactory;
		this.photoInstanceFactory = photoInstanceFactory;
		this.physicalFeaturePhotoAssociationInstanceFactory 
			= physicalFeaturePhotoAssociationInstanceFactory;
		this.physicalFeatureAssociationNoteInstanceFactory
			= physicalFeatureAssociationNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public void remove(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		this.physicalFeatureAssociationDao.makeTransient(
				physicalFeatureAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeatureAssociation> 
	findPhysicalFeaturesAssociationsByOffender(final Offender offender) {
		return this.physicalFeatureAssociationDao
				.findPhysicalFeatureAssociationsByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociation create(final PhysicalFeature feature,
			final String description, final Date originationDate, 
			final Offender offender) throws DuplicateEntityFoundException {
		if (this.physicalFeatureAssociationDao.find(description, feature,
				offender) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		PhysicalFeatureAssociation physicalFeatureAssociation = this
				.physicalFeatureAssociationInstanceFactory.createInstance();
		physicalFeatureAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		populatePhysicalFeatureAssociation(physicalFeatureAssociation, feature,
				description, originationDate, offender);
		return this.physicalFeatureAssociationDao.makePersistent(
				physicalFeatureAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociation update(
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			final PhysicalFeature feature, final String description, 
			final Date originationDate, final Offender offender) 
		throws DuplicateEntityFoundException {
		if (this.physicalFeatureAssociationDao.findExcluding(description,
				feature, offender, physicalFeatureAssociation) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		populatePhysicalFeatureAssociation(physicalFeatureAssociation, feature,
				description, originationDate, offender);
		return this.physicalFeatureAssociationDao.makePersistent(
				physicalFeatureAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public Photo createPhoto(final Date date, final String fileName) {
		Photo photo = this.photoInstanceFactory.createInstance();
		photo.setDate(date);
		photo.setFilename(fileName);
		photo.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.photoDao.makePersistent(photo);
	}

	/** {@inheritDoc} */
	@Override
	public void removePhoto(final Photo photo) {
		this.photoDao.makeTransient(photo);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociationNote createNote(
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			final String note, final Date date) {
		PhysicalFeatureAssociationNote associationNote 
			= this.physicalFeatureAssociationNoteInstanceFactory
			.createInstance();
		associationNote.setPhysicalFeatureAssociation(
				physicalFeatureAssociation);
		associationNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		this.populateAssociationNote(associationNote, note, date);
		return this.physicalFeatureAssociationNoteDao.makePersistent(
				associationNote);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociationNote updateNote(
			final PhysicalFeatureAssociationNote associationNote,
			final String note, final Date date) {
		this.populateAssociationNote(associationNote, note, date);
		return this.physicalFeatureAssociationNoteDao.makePersistent(
				associationNote);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeNote(final PhysicalFeatureAssociationNote note) {
		this.physicalFeatureAssociationNoteDao.makeTransient(note);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation 
		createPhysicalFeaturePhotoAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			final Offender offender, final Photo photo) {
		PhysicalFeaturePhotoAssociation association =
				this.physicalFeaturePhotoAssociationInstanceFactory
				.createInstance();
		association.setPerson(offender);
		association.setPhysicalFeatureAssociation(physicalFeatureAssociation);
		association.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		this.populatePhysicalFeaturePhotoAssociation(association, photo);
		return this.physicalFeaturePhotoAssociationDao.makePersistent(
				association);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation
		updatePhysicalFeaturePhotoAssociation(
			final PhysicalFeaturePhotoAssociation photoAssociation,
			final Photo photo) {
		this.populatePhysicalFeaturePhotoAssociation(photoAssociation, photo);
		return this.physicalFeaturePhotoAssociationDao.makePersistent(
				photoAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public void removePhysicalFeaturePhotoAssociation(
			final PhysicalFeaturePhotoAssociation
			physicalFeaturePhotoAssociation) {
		this.physicalFeaturePhotoAssociationDao
			.makeTransient(physicalFeaturePhotoAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findPhysicalFeatureByClassification(
			final FeatureClassification featureClassification) {
		return this.physicalFeatureDao.findPhysicalFeatureByClassification(
				featureClassification);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findAllPhysicalFeatures() {
		return this.physicalFeatureDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation>
		findPhotoAssociationsByPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		return this.physicalFeaturePhotoAssociationDao
				.findByPhysicalFeatureAssociation(physicalFeatureAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation findPhysicalFeaturePhotoAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			final Photo photo) {
		return this.physicalFeaturePhotoAssociationDao
				.find(physicalFeatureAssociation, photo);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation>
		findPhotoAssociationsByFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		return this.physicalFeaturePhotoAssociationDao
				.findByPhysicalFeatureAssociation(physicalFeatureAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public List<FeatureClassification> findFeatureClassifications() {
		return this.featureClassificationDao.findValid();
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeatureAssociationNote>
		findNotesByPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		return this.physicalFeatureAssociationNoteDao
				.findNotesByPhysicalFeatureAssociation(
						physicalFeatureAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation updatePhotoAssociation(
			final PhysicalFeaturePhotoAssociation photoAssociation, 
			final Date date) {
		photoAssociation.getPhoto().setDate(date);
		return this.physicalFeaturePhotoAssociationDao.makePersistent(
				photoAssociation);
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer countOtherPhotosByOffender(Offender offender) {
		return this.physicalFeaturePhotoAssociationDao
				.findOrphanedPhotoAssications(offender).size();
	}

	/* Helper methods. */
	
	/*
	 * Populates a physical feature association with all of the values expected
	 * for a complete physical feature association except creation signature.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @param feature physical feature
	 * @param description description
	 * @param originationDate origination date
	 * @param offender offender
	 */
	private void populatePhysicalFeatureAssociation(
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			final PhysicalFeature feature, final String description, 
			final Date originationDate, final Offender offender) {
		physicalFeatureAssociation.setFeature(feature);
		physicalFeatureAssociation.setOriginationDate(originationDate);
		physicalFeatureAssociation.setDescription(description);
		physicalFeatureAssociation.setOffender(offender);
		physicalFeatureAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
	}
	
	/*
	 * Populates the specified physical feature photo association.
	 * 
	 * @param association physical feature photo association
	 * @param photo photo
	 * @return populated physical feature photo association
	 */
	private PhysicalFeaturePhotoAssociation 
		populatePhysicalFeaturePhotoAssociation(
			final PhysicalFeaturePhotoAssociation association, 
			final Photo photo) {
		association.setPhoto(photo);
		association.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return association;
	}
	
	/*
	 * Populates the specified physical feature association note.
	 * 
	 * @param associationNote physical feature association note
	 * @param note note
	 * @param date date
	 * @return populated physical feature association note
	 */
	private PhysicalFeatureAssociationNote populateAssociationNote(
			final PhysicalFeatureAssociationNote associationNote, 
			final String note, final Date date) {
		associationNote.setDate(date);
		associationNote.setNote(note);
		associationNote.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return associationNote;
	}
}