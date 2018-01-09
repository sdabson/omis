package omis.condition.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.dao.AgreementDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementCategory;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;


/**
 * Agreement Delegate.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public class AgreementDelegate {

	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Agreement already"
			+ "exists with the specified date range, description, and category"
			+ "for this offender.";

	/* Instance factories. */
	
	private final InstanceFactory<Agreement> agreementFactory;
	
	/* DAOs. */
	
	private final AgreementDao agreementDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	
	/**
	 * Default Constructor for Agreement Delegate.
	 * @param agreementInstanceFactory - Agreement Instance Factory
	 * @param agreementDao - Agreement Dao
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public AgreementDelegate(
			final InstanceFactory<Agreement> agreementInstanceFactory,
			final AgreementDao agreementDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.agreementFactory = agreementInstanceFactory;
		this.agreementDao = agreementDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Agreement.
	 * @param offender - offender.
	 * @param startDate - startDate.
	 * @param endDate - endDate.
	 * @param description - String description
	 * @param category - AgreementCategory
	 * @return newly created Agreement. 
	 * @throws DuplicateEntityFoundException - when Agreement already exists. */
	public Agreement create(final Offender offender, final Date startDate,
			final Date endDate, final String description,
			final AgreementCategory category)
					throws DuplicateEntityFoundException {
		if (this.agreementDao.find(offender, startDate, endDate, description,
				category) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		Agreement agreement = this.agreementFactory.createInstance();
		
		this.populateAgreement(agreement, offender, startDate, endDate,
				description, category);
		agreement.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		agreement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.agreementDao.makePersistent(agreement);
	}
	
	/**
	 * Updates an existing Agreement.
	 * @param agreement - Agreement.
	 * @param startDate - startDate.
	 * @param endDate - endDate.
	 * @param description - String description
	 * @param category - AgreementCategory
	 * @return - updated Agreement
	 * @throws DuplicateEntityFoundException - when Agreement already exists. */
	public Agreement update(final Agreement agreement, final Date startDate,
			final Date endDate, final String description,
			final AgreementCategory category)
					throws DuplicateEntityFoundException {
		if (this.agreementDao.findExcluding(agreement, startDate, endDate,
				description, category) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		this.populateAgreement(agreement, startDate, endDate,
				description, category);
		agreement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.agreementDao.makePersistent(agreement);
	}

	// Populates agreement
	private void populateAgreement(final Agreement agreement,
			final Date startDate, final Date endDate, final String description,
			final AgreementCategory category) {
		DateRange dateRange = new DateRange(startDate, endDate);
		agreement.setDateRange(dateRange);
		agreement.setCategory(category);
		agreement.setDescription(description);
		agreement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
	
	//Overshadows above method with additional "offender" parameter
	private void populateAgreement(final Agreement agreement,
			final Offender offender, final Date startDate, final Date endDate, 
			final String description,
			final AgreementCategory category) {
		agreement.setOffender(offender);
		this.populateAgreement(agreement, startDate, endDate, description,
				category);
		
	}
	
	/**
	 * Removes a Agreement.
	 * @param agreement - Agreement
	 */
	public void remove(final Agreement agreement) {
		this.agreementDao.makeTransient(agreement);
	}
	
	/**
	 * Returns a list of Agreements found by specified Offender.
	 * @param offender - Offender
	 * @return List of Agreements found by specified Offender
	 */
	public List<Agreement> findByOffender(final Offender offender) {
		return this.agreementDao.findByOffender(offender);
	}
	
	/**
	 * Returns a list of Agreements found by specified Docket.
	 * @param docket - Docket
	 * @return List of Agreements found by specified Docket
	 */
	public List<Agreement> findByDocket(final Docket docket) {
		return this.agreementDao.findByDocket(docket);
	}
}
