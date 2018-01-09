package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OtherPertinentInformationSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public interface OtherPertinentInformationSectionSummaryService {
	
	/**
	 * Creates an OtherPertinentInformationSectionSummary with the specified
	 * properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param description - String
	 * @return Newly created OtherPertinentInformationSectionSummary
	 * @throws DuplicateEntityFoundException - When an
	 * OtherPertinentInformationSectionSummary already exists for the
	 * specified PresentenceInvestigiationRequest
	 */
	public OtherPertinentInformationSectionSummary
		createOtherPertinentInformationSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String description)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an OtherPertinentInformationSectionSummary with the specified
	 * properties
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary to update
	 * @param description - String
	 * @return Updated OtherPertinentInformationSectionSummary
	 * @throws DuplicateEntityFoundException - When another
	 * OtherPertinentInformationSectionSummary already exists for the
	 * OtherPertinentInformationSectionSummary's PresentenceInvestigiationRequest
	 */
	public OtherPertinentInformationSectionSummary
		updateOtherPertinentInformationSectionSummary(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			String description)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an OtherPertinentInformationSectionSummary
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary to remove
	 */
	public void removeOtherPertinentInformationSectionSummary(
		OtherPertinentInformationSectionSummary
			otherPertinentInformationSectionSummary);
	
	/**
	 * Creates an OtherPertinentInformationSectionSummaryNote with the
	 * specified properties
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created OtherPertinentInformationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When an
	 * OtherPertinentInformationSectionSummaryNote already exists with given 
	 * Date and Description for the specified
	 * OtherPertinentInformationSectionSummary
	 */
	public OtherPertinentInformationSectionSummaryNote
		createOtherPertinentInformationSectionSummaryNote(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an OtherPertinentInformationSectionSummaryNote with the
	 * specified properties
	 * @param otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated OtherPertinentInformationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When another
	 * OtherPertinentInformationSectionSummaryNote already exists with given 
	 * Date and Description for the specified
	 * OtherPertinentInformationSectionSummaryNote's
	 * OtherPertinentInformationSectionSummary
	 */
	public OtherPertinentInformationSectionSummaryNote
		updateOtherPertinentInformationSectionSummaryNote(
			OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an OtherPertinentInformationSectionSummaryNote
	 * @param otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote to remove
	 */
	public void removeOtherPertinentInformationSectionSummaryNote(
		OtherPertinentInformationSectionSummaryNote
			otherPertinentInformationSectionSummaryNote);
	
	/**
	 * Finds an OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public OtherPertinentInformationSectionSummary
		findByPresentenceInvestigationRequest(PresentenceInvestigationRequest
				presentenceInvestigationRequest);
	
	/**
	 * Returns a list of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @return List of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 */
	public List<OtherPertinentInformationSectionSummaryNote>
		findSectionSummaryNotesByOtherPertinentInformationSectionSummary(
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary);
}
