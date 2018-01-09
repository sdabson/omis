package omis.physicalfeature.service.impl;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.physicalfeature.dao.PhysicalFeaturePhotoAssociationDao;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;
import omis.physicalfeature.service.PhysicalFeaturePhotoAssociationService;

/**
 * Physical feature photo service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeaturePhotoAssociationServiceImpl 
	implements PhysicalFeaturePhotoAssociationService {

	/* Data access objects */
	
	private PhysicalFeaturePhotoAssociationDao 
	physicalFeaturePhotoAssociationDao;
	
	private PhotoDao photoDao;
	
	/* Instance Factories */
	
	private final InstanceFactory<PhysicalFeaturePhotoAssociation>
	physicalFeaturePhotoAssociationInstanceFactory;
	
	/* Audit Component Retriever */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a physical feature photo service implementation with 
	 * the specified data access object.
	 * 
	 * @param physicalFeaturePhotoAssociationDao physical feature photo DAO
	 * @param photoDao photo DAO
	 * @param physicalFeaturePhotoAssociationInstanceFactory physical feature
	 * photo association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public PhysicalFeaturePhotoAssociationServiceImpl(
			final PhysicalFeaturePhotoAssociationDao 
			physicalFeaturePhotoAssociationDao, final PhotoDao photoDao,
			final InstanceFactory<PhysicalFeaturePhotoAssociation>
			physicalFeaturePhotoAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.physicalFeaturePhotoAssociationDao = 
				physicalFeaturePhotoAssociationDao;
		this.photoDao = photoDao;
		this.physicalFeaturePhotoAssociationInstanceFactory =
				physicalFeaturePhotoAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation findById(final Long id) {
		return this.physicalFeaturePhotoAssociationDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(
			final PhysicalFeaturePhotoAssociation physicalFeaturePhoto) {
		this.physicalFeaturePhotoAssociationDao
			.makeTransient(physicalFeaturePhoto);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation> findAll() {
		return this.physicalFeaturePhotoAssociationDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation> 
	findByPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation pFAssociation) {
		return this.physicalFeaturePhotoAssociationDao
				.findByPhysicalFeatureAssociation(pFAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation find(
			final PhysicalFeatureAssociation pFAssociation,
			final Photo photo) {
		return this.physicalFeaturePhotoAssociationDao
				.find(pFAssociation, photo);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAssociationByPhysicalFeatureAssociationExcluding(
			final PhysicalFeatureAssociation pFAssociation, 
			final PhysicalFeaturePhotoAssociation... 
			excludingPhotoAssociations) {
		this.physicalFeaturePhotoAssociationDao
		.removeAssociationByPhysicalFeatureAssociationExcluding(pFAssociation, 
				excludingPhotoAssociations);
	}

	/** {@inheritDoc} */
	@Override
	public void removePhysicalFeaturePhotoAssociationPhoto(final Photo photo) {
		this.photoDao.makeTransient(photo);
	}

	/** {@inheritDoc} */
	@Override
	public List<Photo> findPhysicalFeaturePhotoOrphans(final List<Photo> photos,
			final List<Photo> persistedPhotos) {
		return this.physicalFeaturePhotoAssociationDao
				.findPhysicalFeaturePhotoOrphans(photos, persistedPhotos);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation> findOrphanedPhotoAssociations(
			final Offender offender) {
		return this.physicalFeaturePhotoAssociationDao
				.findOrphanedPhotoAssications(offender);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation save(
			final PhysicalFeatureAssociation association, final Person person,
			final Photo photo)
		throws DuplicateEntityFoundException {
		photo.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		photo.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.photoDao.makePersistent(photo);
		if (this.physicalFeaturePhotoAssociationDao.find(association, 
				photo) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		PhysicalFeaturePhotoAssociation physicalFeaturePhotoAssociation = this
				.physicalFeaturePhotoAssociationInstanceFactory
				.createInstance();
		physicalFeaturePhotoAssociation.setCreationSignature(
				new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		populatePhotoAssociation(association, person, photo, 
				physicalFeaturePhotoAssociation);
		return this.physicalFeaturePhotoAssociationDao.makePersistent(
				physicalFeaturePhotoAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation update(
			final PhysicalFeatureAssociation association, final Person person,
			final Photo photo, final PhysicalFeaturePhotoAssociation 
			physicalFeaturePhotoAssociation)
		throws DuplicateEntityFoundException {
		if (photo.getCreationSignature() == null) {
			photo.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		}
		photo.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.photoDao.makePersistent(photo);
		if (this.physicalFeaturePhotoAssociationDao.findExcluding(association,
				photo, physicalFeaturePhotoAssociation) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		populatePhotoAssociation(association, person, photo, 
				physicalFeaturePhotoAssociation);
		return this.physicalFeaturePhotoAssociationDao.makePersistent(
				physicalFeaturePhotoAssociation);
	}
	
	/*
	 * Populates a physical feature photo association with all of the values
	 * expected for a complete entity except creation signature.
	 */
	private void populatePhotoAssociation(
			final PhysicalFeatureAssociation association, final Person person, 
			final Photo photo, final PhysicalFeaturePhotoAssociation
			physicalFeaturePhotoAssociation) {
		physicalFeaturePhotoAssociation.setPerson(person);
		physicalFeaturePhotoAssociation.setPhoto(photo);
		physicalFeaturePhotoAssociation.setPhysicalFeatureAssociation(
				association);
		physicalFeaturePhotoAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
	}
}