package omis.identificationnumber.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.dao.IdentificationNumberDao;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.identificationnumber.domain.Multitude;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for identification numbers.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class IdentificationNumberDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<IdentificationNumber>
	identificationNumberInstanceFactory;
	
	/* Data access objects. */
	
	private final IdentificationNumberDao identificationNumberDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Delegate for identification numbers.
	 * 
	 * @param identificationNumberInstanceFactory instance factory for
	 * identification numbers
	 * @param identificationNumberDao data access object for identification
	 * numbers
	 * @param auditComponentRetriever audit component retriever
	 */
	public IdentificationNumberDelegate(
			final InstanceFactory<IdentificationNumber>
				identificationNumberInstanceFactory,
			final IdentificationNumberDao identificationNumberDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.identificationNumberInstanceFactory
			= identificationNumberInstanceFactory;
		this.identificationNumberDao = identificationNumberDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates identification number.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return created identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException - if Identification Number Category is
	 * 'SINGLE' and date range conflicts with an existing identification number
	 * of the same category
	 */
	public IdentificationNumber create(
			final Offender offender,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final String value,
			final Date issueDate,
			final Date expireDate)
				throws DuplicateEntityFoundException, DateConflictException {
		if (this.identificationNumberDao.find(offender, issuer, category,
				value, issueDate, expireDate) != null) {
			throw new DuplicateEntityFoundException(
					"Identification number exists");
		}
		if(Multitude.SINGLE.equals(category.getMultitude())) {
			if(this.identificationNumberDao.findWithinDates(
					offender, issuer, category, issueDate, expireDate) != null) {
				throw new DateConflictException("Identification Number for the " +
						"given category exists within specified date range.");
			}
		}
		
		IdentificationNumber identificationNumber
			= this.identificationNumberInstanceFactory.createInstance();
		identificationNumber.setOffender(offender);
		identificationNumber.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		this.populate(identificationNumber, issuer, category, value, issueDate,
				expireDate);
		return this.identificationNumberDao.makePersistent(
				identificationNumber);
	}

	/**
	 * Updates identification number.
	 * 
	 * @param identificationNumber identification number to update
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return updated identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException - if Identification Number Category is
	 * 'SINGLE' and date range conflicts with an existing identification number
	 * of the same category
	 */
	public IdentificationNumber update(
			final IdentificationNumber identificationNumber,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final String value,
			final Date issueDate,
			final Date expireDate)
				throws DuplicateEntityFoundException, DateConflictException {
		if (this.identificationNumberDao.findExcluding(
				identificationNumber.getOffender(), issuer, category,
				value, issueDate, expireDate, identificationNumber) != null) {
			throw new DuplicateEntityFoundException(
					"Identification number exists");
		}
		if(Multitude.SINGLE.equals(category.getMultitude())) {
			if(this.identificationNumberDao.findWithinDatesExcluding(
					identificationNumber.getOffender(), issuer, category,
					issueDate, expireDate, identificationNumber) != null) {
				throw new DateConflictException("Identification Number for the " +
						"given category exists within specified date range.");
			}
		}
		this.populate(identificationNumber, issuer, category, value, issueDate,
				expireDate);
		return this.identificationNumberDao.makePersistent(
				identificationNumber);
	}
	
	/**
	 * Removes identification number.
	 * 
	 * @param identificationNumber identification number to remove
	 */
	public void remove(final IdentificationNumber identificationNumber) {
		this.identificationNumberDao.makeTransient(identificationNumber);
	}
	
	/* Helper methods. */
	
	// Populates identification number
	private void populate(
			final IdentificationNumber identificationNumber,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final String value,
			final Date issueDate,
			final Date expireDate) {
		if (issueDate != null && expireDate != null
				&& issueDate.after(expireDate)) {
			throw new IllegalArgumentException(
					"Issue date cannot be after expire date");
		}
		identificationNumber.setIssuer(issuer);
		identificationNumber.setCategory(category);
		identificationNumber.setValue(value);
		identificationNumber.setIssueDate(issueDate);
		identificationNumber.setExpireDate(expireDate);
		identificationNumber.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
	}

	/**
	 * Returns identification numbers by offender.
	 * 
	 * @param offender offender
	 * @return identification numbers by offender
	 */
	public List<IdentificationNumber> findByOffender(final Offender offender) {
		return this.identificationNumberDao.findByOffender(offender);
	}
}