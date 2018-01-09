package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * EducationSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationSectionSummaryService {
	
	/**
	 * Creates an EducationSectionSummary with specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created EducationSectionSummary
	 * @throws DuplicateEntityFoundException - when an EducationSectionSummary
	 * already exists for specified PresentenceInvestigationRequest
	 */
	EducationSectionSummary createEducationSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String text) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an EducationSectionSummary with specified parameters
	 * @param educationSectionSummary - EducationSectionSummary to update
	 * @param text - String
	 * @return Updated EducationSectionSummary
	 * @throws DuplicateEntityFoundException - when an EducationSectionSummary
	 * already exists for specified PresentenceInvestigationRequest
	 */
	EducationSectionSummary updateEducationSectionSummary(
			EducationSectionSummary educationSectionSummary,
			String text) throws DuplicateEntityFoundException;
	
	/**
	 * Removes an EducationSectionSummary
	 * @param educationSectionSummary - EducationSectionSummary to remove
	 */
	void removeEducationSectionSummary(EducationSectionSummary
			educationSectionSummary);
	
	/**
	 * Creates an EducationSectionSummaryNote with specified parameters
	 * @param description - String
	 * @param date - Date
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return Newly created EducationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummaryNote
	 * already exists with given date and description for specified
	 * EducationSectionSummary
	 */
	EducationSectionSummaryNote createEducationSectionSummaryNote(
			String description, Date date,
			EducationSectionSummary educationSectionSummary)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an EducationSectionSummaryNote with specified parameters
	 * @param educationSectionSummaryNote - EducationSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated EducationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummaryNote
	 * already exists with given date and description for its EducationSectionSummary
	 */
	EducationSectionSummaryNote updateEducationSectionSummaryNote(
			EducationSectionSummaryNote educationSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes specified EducationSectionSummaryNote
	 * @param educationSectionSummaryNote - EducationSectionSummaryNote to 
	 * remove
	 */
	void removeEducationSectionSummaryNote(
			EducationSectionSummaryNote educationSectionSummaryNote);
	
	/**
	 * Returns an EducationSectionSummary found by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return EducationSectionSummary
	 */
	EducationSectionSummary
		findEducationSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a list of EducationSectionSummaryNotes by EducationSectionSummary
	 * @param educationSectionSummary - EducationSectionSummary
	 * @return List of EducationSectionSummaryNotes
	 */
	List<EducationSectionSummaryNote> findNotesByEducationSectionSummary(
			EducationSectionSummary educationSectionSummary);
}
