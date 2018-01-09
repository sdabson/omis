package omis.presentenceinvestigation.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.dao.BiographicAndContactSectionDao;
import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/** Service delegate for BiographicAndContactSection related operations.
 * @author Jonny Santy
 * @version 0.1.0 (Oct 27, 2016)
 * @since OMIS 3.0 */
public class BiographicAndContactSectionDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "BiographicAndContactSection exists for this offender, name, and psi report date.";
	
	private final BiographicAndContactSectionDao biographicAndContactSectionDao;
	
	private final InstanceFactory<BiographicAndContactSection> 
		biographicAndContactSectionFactory;
	
	private final AuditComponentRetriever auditComponenetRetriever;
	
	/**
	 * Constructor
	 * @param biographicAndContactSectionDao   DAO to interact with the BiographicAndContactSection table
	 * @param biographicAndContactSectionFactory  factory for building BiographicAndContactSection objects
	 * @param auditComponentRetriever Audit component for determining who changed what and when
	 */
	public BiographicAndContactSectionDelegate(
			final BiographicAndContactSectionDao 
				biographicAndContactSectionDao,
			final InstanceFactory<BiographicAndContactSection>
				biographicAndContactSectionFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.biographicAndContactSectionDao 
			= biographicAndContactSectionDao;
		this.biographicAndContactSectionFactory 
			= biographicAndContactSectionFactory;
		this.auditComponenetRetriever = auditComponentRetriever;
	}
	
	/** Creates a BiographicAndContactSection in the database.
	 * @param presentenceInvestigationRequest PSI attached to the contact info
	 * @param name name of offender
	 * @param dateOfReport date of the PSI Report
	 * @param alsoKnownAs Alias of the offender(the AKA field on the form)
	 * @param dateOfSentence date of sentence hearing for the offender
	 * @param address address of the offender
	 * @param phoneNumber phone number of the offender
	 * @param cellPhoneNumber cell phone number of the offender
	 * @return Biographic and Contact Section.
	 * @throws DuplicateEntityFoundException - entity with given PSI, name, and dateOfReport 
	 * exists */
	public BiographicAndContactSection create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String name,
			final Date dateOfReport, 
			final String alsoKnownAs,
			final Date dateOfSentence, 
			final String address,
			final String phoneNumber, 
			final String cellPhoneNumber) 
					throws DuplicateEntityFoundException {
		if (this.biographicAndContactSectionDao.find(presentenceInvestigationRequest,
				name, dateOfReport) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		BiographicAndContactSection  biographicAndContactSection 
			= this.biographicAndContactSectionFactory
				.createInstance();
		
		biographicAndContactSection.setAddress(address);
		biographicAndContactSection.setAlsoKnownAs(alsoKnownAs);
		biographicAndContactSection.setCellPhoneNumber(cellPhoneNumber);
		biographicAndContactSection.setDateOfReport(dateOfReport);
		biographicAndContactSection.setDateOfSentence(dateOfSentence);
		biographicAndContactSection.setName(name);
		biographicAndContactSection.setPhoneNumber(phoneNumber);
		biographicAndContactSection.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		biographicAndContactSection.setUpdateSignature(
				new UpdateSignature(
						this.auditComponenetRetriever.retrieveUserAccount(),
						this.auditComponenetRetriever.retrieveDate()));
		biographicAndContactSection.setCreationSignature(
				new CreationSignature(
						this.auditComponenetRetriever.retrieveUserAccount(), 
						this.auditComponenetRetriever.retrieveDate()));
		
		return this.biographicAndContactSectionDao.makePersistent(
				biographicAndContactSection);
	}
	
	/** Updates an existing BiographicAndContactSection in the database.
	 * @param biographicAndContactSection - the BiographicAndContactSection object to update.
	 * @param presentenceInvestigationRequest PSI attached to the contact info
	 * @param name name of offender
	 * @param dateOfReport date of the PSI Report
	 * @param alsoKnownAs Alias of the offender(the AKA field on the form)
	 * @param dateOfSentence date of sentence hearing for the offender
	 * @param address address of the offender
	 * @param phoneNumber phone number of the offender
	 * @param cellPhoneNumber cell phone number of the offender
	 * @return the updated BiographicAndContactSection object
	 * @throws DuplicateEntityFoundException - entity with given PSI, name, and dateOfReport 
	 * exists */
	public BiographicAndContactSection update(
			final BiographicAndContactSection biographicAndContactSection,
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String name,
			final Date dateOfReport, 
			final String alsoKnownAs,
			final Date dateOfSentence, 
			final String address,
			final String phoneNumber, 
			final String cellPhoneNumber)
	throws DuplicateEntityFoundException {
		
		if (this.biographicAndContactSectionDao.findExcluding(
				biographicAndContactSection,presentenceInvestigationRequest,
				name, dateOfReport) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}

		biographicAndContactSection.setAddress(address);
		biographicAndContactSection.setAlsoKnownAs(alsoKnownAs);
		biographicAndContactSection.setCellPhoneNumber(cellPhoneNumber);
		biographicAndContactSection.setDateOfReport(dateOfReport);
		biographicAndContactSection.setDateOfSentence(dateOfSentence);
		biographicAndContactSection.setName(name);
		biographicAndContactSection.setPhoneNumber(phoneNumber);
		biographicAndContactSection.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		biographicAndContactSection.setUpdateSignature(
				new UpdateSignature(
						this.auditComponenetRetriever.retrieveUserAccount(),
						this.auditComponenetRetriever.retrieveDate()));
		
		
		return this.biographicAndContactSectionDao.makePersistent(
				biographicAndContactSection);
	}
	
	/** This removes a BiographicAndContactSection from the database.
	 * @param biographicAndContactSection - BiographicAndContactSection to be removed from database. */
	public void remove(final BiographicAndContactSection 
			biographicAndContactSection) {
		this.biographicAndContactSectionDao.makeTransient(
				biographicAndContactSection);
	}
}