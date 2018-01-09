package omis.presentenceinvestigation.service;

import java.util.Date;

import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/** Service for Biographic and Contact Section related operations.
 * @author Jonny Santy
 * @version 0.1.0 (Oct 27, 2016)
 * @since OMIS 3.0 */
public interface BiographicAndContactSectionService {
	
	/**
	 * Creates a Biographic and Contact Section
	 * @param presentenceInvestigationRequest -  the Presentence Investigation Request Tied to the bio
	 * @param name - the name of the offender
	 * @param dateOfReport the date of the PSI report
	 * @param alsoKnownAs the offender alias
	 * @param dateOfSentence date of sentence hearing
	 * @param address  address of offender
	 * @param phoneNumber phone of offender
	 * @param cellPhoneNumber cell of offender
	 * @return a newly created BiographicAndContactSection object
	 * @throws DuplicateEntityFoundException if the BiographicAndContactSection object already exists in the database
	 */
	 public BiographicAndContactSection create(PresentenceInvestigationRequest presentenceInvestigationRequest,
			 String name, Date dateOfReport, String alsoKnownAs, 
			 Date dateOfSentence, String address, String phoneNumber, String cellPhoneNumber)
	 throws DuplicateEntityFoundException;
	
	 /**
	  * Updates an existing Biographic and Contact Section
	  * @param biographicAndContactSection the Biographic and Contact Section to update
	  * @param presentenceInvestigationRequest -  the Presentence Investigation Request Tied to the bio
	  * @param name name  of offender
	  * @param dateOfReport report date of psi
	  * @param alsoKnownAs alias of offender 
	  * @param dateOfSentence date of offender sentence
	  * @param address address of offender
	  * @param phoneNumber phone of offender
	  * @param cellPhoneNumber cell of offender
	  * @return updated Biographic and Contact Section
	  * @throws DuplicateEntityFoundException
	  */
	public BiographicAndContactSection update(
			BiographicAndContactSection biographicAndContactSection,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String name,
			Date dateOfReport,
			String alsoKnownAs,
			Date dateOfSentence,
			String address,
			String phoneNumber,
			String cellPhoneNumber)
	throws DuplicateEntityFoundException;
	
	/** Removes a BiographicAndContactSection
	 * @param biographicAndContactSection - BiographicAndContactSection to remove. */
	public void remove(final BiographicAndContactSection 
			biographicAndContactSection);
}