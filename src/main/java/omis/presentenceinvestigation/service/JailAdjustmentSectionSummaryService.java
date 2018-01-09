package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * JailAdjustmentSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public interface JailAdjustmentSectionSummaryService {
	
	/**
	 * Creates a JailAdjustmentSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly Created JailAdjustmentSectionSummary
	 * @throws DuplicateEntityFoundException - when a JailAdjustmentSectionSummary
	 * already exists with specified PresentenceInvestigationRequest
	 */
	public JailAdjustmentSectionSummary createJailAdjustmentSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates a JailAdjustmentSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return Newly created JailAdjustmentSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when JailAdjustmentSectionSummaryNote
	 * already exists for given JailAdjustmentSectionSummary with given 
	 * Description and Date
	 */
	public JailAdjustmentSectionSummaryNote
			createJailAdjustmentSectionSummaryNote(
				String description, Date date, JailAdjustmentSectionSummary
						jailAdjustmentSectionSummary)
								throws DuplicateEntityFoundException;
	
	/**
	 * Updates a JailAdjustmentSectionSummaryNote with specified parameters
	 * @param jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated JailAdjustmentSectionSummaryNote
	 * @throws DuplicateEntityFoundException - when JailAdjustmentSectionSummaryNote
	 * already exists for given JailAdjustmentSectionSummary with given 
	 * Description and Date
	 */
	public JailAdjustmentSectionSummaryNote updateJailAdjustmentSectionSummaryNote(
			JailAdjustmentSectionSummaryNote jailAdjustmentSectionSummaryNote,
			String description, Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Removes a JailAdjustmentSectionSummaryNote
	 * @param jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 * to remove
	 */
	public void removeJailAdjustmentSectionSummaryNote(
			JailAdjustmentSectionSummaryNote jailAdjustmentSectionSummaryNote);
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummary by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return JailAdjustmentSectionSummary
	 */
	public JailAdjustmentSectionSummary
		findJailAdjustmentSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Finds and returns a list of all JailAdjustmentSectionSummaryNotes
	 * by specified JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary
	 * @return List of all JailAdjustmentSectionSummaryNotes by specified
	 * JailAdjustmentSectionSummary
	 */
	public List<JailAdjustmentSectionSummaryNote>
		findJailAdjustmentSectionSummaryNotesByJailAdjustmentSectionSummary(
				JailAdjustmentSectionSummary jailAdjustmentSectionSummary);
	
}
