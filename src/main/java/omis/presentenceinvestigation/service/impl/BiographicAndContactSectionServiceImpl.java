package omis.presentenceinvestigation.service.impl;

import java.util.Date;

import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.presentenceinvestigation.service.BiographicAndContactSectionService;
import omis.presentenceinvestigation.service.delegate.BiographicAndContactSectionDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
/** Implementation of biographic and contact section service.
 * @author Jonny Santy
 * @version 0.1.0 (Jun 28, 2016)
 * @since OMIS 3.0 */
public class BiographicAndContactSectionServiceImpl 
	implements BiographicAndContactSectionService {
	
	private final BiographicAndContactSectionDelegate biographicAndContactSectionDelegate;
//	private final CourtCaseDelegate courtCaseDelegate;

	/** Constructor.
	 * @param biographicAndContactSectionDelegate - biographic And Contact Section delegate. */
	public BiographicAndContactSectionServiceImpl(
			final BiographicAndContactSectionDelegate 
			biographicAndContactSectionDelegate) {
		this.biographicAndContactSectionDelegate 
			= biographicAndContactSectionDelegate;
	}
	

	/** {@inheritDoc} */
	@Override
	public BiographicAndContactSection create(PresentenceInvestigationRequest presentenceInvestigationRequest,
			String name, Date dateOfReport, String alsoKnownAs, Date dateOfSentence, String address, String phoneNumber,
			String cellPhoneNumber) throws DuplicateEntityFoundException {
		
		return this.biographicAndContactSectionDelegate.create(
				presentenceInvestigationRequest,
				name,
				dateOfReport,
				alsoKnownAs,
				dateOfSentence,
				address,
				phoneNumber,
				cellPhoneNumber
				);
	}


	/** {@inheritDoc} */
	@Override
	public BiographicAndContactSection update(BiographicAndContactSection biographicAndContactSection, 
			PresentenceInvestigationRequest presentenceInvestigationRequest, String name,
			Date dateOfReport, String alsoKnownAs, Date dateOfSentence, String address, String phoneNumber,
			String cellPhoneNumber) throws DuplicateEntityFoundException {

		return this.biographicAndContactSectionDelegate.update(biographicAndContactSection, presentenceInvestigationRequest, name, dateOfReport, alsoKnownAs, dateOfSentence, address, phoneNumber, cellPhoneNumber);
	}


	/** {@inheritDoc} */
	@Override
	public void remove(BiographicAndContactSection biographicAndContactSection) {

		this.biographicAndContactSectionDelegate.remove(biographicAndContactSection);
		
	}

}
