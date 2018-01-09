package omis.adaaccommodation.service.impl;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.dao.AccommodationCategoryDao;
import omis.adaaccommodation.dao.AccommodationDao;
import omis.adaaccommodation.dao.AccommodationNoteDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.AccommodationNote;
import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.service.AccommodationService;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Accommodation service implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public class AccommodationServiceImpl implements AccommodationService {

	/* Data access objects. */
	private final AccommodationDao accommodationDao;
	private final AccommodationNoteDao accommodationNoteDao;
	private final AccommodationCategoryDao accommodationCategoryDao;
	
	/* Component retrievers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Instance factories. */
	private final InstanceFactory<AccommodationNote> 
		accommodationNoteInstanceFactory;
	private final InstanceFactory<Accommodation> accommodationInstanceFactory;
	

	public AccommodationServiceImpl(
		final AccommodationDao accommodationDao,
		final AccommodationNoteDao accommodationNoteDao,
		final AccommodationCategoryDao accommodationCategoryDao,
		final AuditComponentRetriever auditComponentRetriever,
		final InstanceFactory<AccommodationNote> 
			accommodationNoteInstanceFactory,
		final InstanceFactory<Accommodation> accommodationInstanceFactory) {
		
		this.accommodationDao = accommodationDao;
		this.accommodationNoteDao = accommodationNoteDao;
		this.accommodationCategoryDao = accommodationCategoryDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.accommodationNoteInstanceFactory = 
				accommodationNoteInstanceFactory;
		this.accommodationInstanceFactory = accommodationInstanceFactory;
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation create(final Disability disability,
			final String accommodationDescription,
			final AccommodationCategory accommodationCategory)
			throws DuplicateEntityFoundException {
		Accommodation accommodation = 
				this.accommodationInstanceFactory.createInstance();
		if(this.accommodationDao.find(disability, accommodationDescription, 
				accommodationCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate accommodation found");
		}
		accommodation.setDisability(disability);
		accommodation.setDescription(accommodationDescription);
		accommodation.setAccommodationCategory(accommodationCategory);
		accommodation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		accommodation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		
		return this.accommodationDao.makePersistent(accommodation);
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation update(final Accommodation accommodation,
			final String accommodationDescription,
			final AccommodationCategory accommodationCategory)
			throws DuplicateEntityFoundException {
		if(this.accommodationDao.findExcluding(accommodation.getDisability(), 
				accommodationDescription, accommodationCategory, 
				accommodation) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate accommodation found");
		}
		accommodation.setAccommodationCategory(accommodationCategory);
		accommodation.setDescription(accommodationDescription);
		accommodation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		
		return this.accommodationDao.makePersistent(accommodation);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Accommodation accommodation) {
		this.accommodationDao.makeTransient(accommodation);
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationNote addAccommodationNote(
			final Accommodation accommodation, final Date date, 
			final String text) {	
		AccommodationNote accommodationNote = 
				this.accommodationNoteInstanceFactory.createInstance();
		accommodationNote.setAccommodation(accommodation);
		accommodationNote.setDate(date);
		accommodationNote.setText(text);
		accommodationNote.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		accommodationNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.accommodationNoteDao.makePersistent(accommodationNote);
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationNote updateAccommmodationNote(
			final AccommodationNote accommodationNote, final Date date, 
			final String text) {
		accommodationNote.setDate(date);
		accommodationNote.setText(text);
		accommodationNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.accommodationNoteDao.makePersistent(accommodationNote);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAccommodationNote(
			final AccommodationNote accommodationNote) {
		this.accommodationNoteDao.makeTransient(accommodationNote);
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationCategory> findAllAccommodationCategories() {
		return this.accommodationCategoryDao.findAllAccommodationCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationNote> findAccommodationNotesByAccommodation(
			final Accommodation accommodation) {
		return this.accommodationNoteDao.findByAccommodation(accommodation);
	}
	
}