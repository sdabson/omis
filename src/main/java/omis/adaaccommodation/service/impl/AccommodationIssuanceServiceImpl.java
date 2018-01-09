package omis.adaaccommodation.service.impl;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.dao.AccommodationIssuanceDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationIssuance;
import omis.adaaccommodation.service.AccommodationIssuanceService;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

public class AccommodationIssuanceServiceImpl 
	implements AccommodationIssuanceService {

	/* Data access object. */
	private final AccommodationIssuanceDao issuanceDao;
	
	/* Instance factories. */
	private final InstanceFactory<AccommodationIssuance> 
		issuanceInstanceFactory;
	
	/* Component retrievers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	public AccommodationIssuanceServiceImpl(
			final AccommodationIssuanceDao issuanceDao,
			final InstanceFactory<AccommodationIssuance> 
				issuanceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		
		this.issuanceDao = issuanceDao;
		this.issuanceInstanceFactory = issuanceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;		
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationIssuance create(Accommodation accommodation,
			Date timestamp, Person issuer, String text) 
					throws DuplicateEntityFoundException {
		AccommodationIssuance issuance = 
				this.issuanceInstanceFactory.createInstance();
		if(this.issuanceDao.find(accommodation, timestamp) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate accommodation issuance found.");
		}
		issuance.setAccommodation(accommodation);
		issuance.setDescription(text);
		issuance.setTimestamp(timestamp);
		issuance.setIssuer(issuer);
		issuance.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		issuance.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.issuanceDao.makePersistent(issuance);
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationIssuance update(
			AccommodationIssuance accommodationIssuance, Date timestamp,
			String text) throws DuplicateEntityFoundException {
		if(this.issuanceDao.findExcluding(accommodationIssuance, 
				accommodationIssuance.getAccommodation(), timestamp) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate accommodation issuance found.");
		}
		accommodationIssuance.setDescription(text);
		accommodationIssuance.setTimestamp(timestamp);		
		accommodationIssuance.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		
		return this.issuanceDao.makePersistent(accommodationIssuance);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(AccommodationIssuance accommodationIssuance) {
		this.issuanceDao.makeTransient(accommodationIssuance);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AccommodationIssuance> findByAccommodation(
			Accommodation accommodation) {
		return this.issuanceDao.findByAccommodation(accommodation);
	}
}