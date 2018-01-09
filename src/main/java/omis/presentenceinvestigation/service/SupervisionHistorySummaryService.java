package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history summary service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public interface SupervisionHistorySummaryService {
	
	/**
	 * Create a new supervision history section summary.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return new supervision history section summary
	 * @throws DuplicateEntityFoundException
	 */
	SupervisionHistorySectionSummary createSupervisionHistorySectionSummary(
			String text,
			PresentenceInvestigationRequest presentenceInvestigationRequest)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates this supervision history section summary.
	 *
	 *
	 * @param priorSupervisionSectionSummary prior supervision section summary
	 * @param text text
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return updated supervision history section summary
	 * @throws DuplicateEntityFoundException
	 */
	SupervisionHistorySectionSummary updateSupervisionHistorySection(
			SupervisionHistorySectionSummary priorSupervisionSectionSummary,
			String text, PresentenceInvestigationRequest 
			presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Remove this supervision history section summary.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 */
	void removeSupervisionHistorySectionSummary(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary);
	
	/**
	 * Creates a new supervision history note.
	 *
	 *
	 * @param description description
	 * @param date date
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @return new supervision history note
	 * @throws DuplicateEntityFoundException
	 */
	SupervisionHistoryNote createSupervisionHistoryNote(String description,
			Date date, SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates this supervision history note.
	 *
	 *
	 * @param supervisionHistoryNote supervision history note
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @param description description
	 * @param date date
	 * @return updated supervision history note
	 * @throws DuplicateEntityFoundException
	 */
	SupervisionHistoryNote updateSupervisionHistoryNote(
			SupervisionHistoryNote supervisionHistoryNote, 
			SupervisionHistorySectionSummary supervisionHistorySectionSummary,
			String description, Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Removes a supervision history note.
	 *
	 *
	 * @param supervisionHistoryNote supervision history note
	 */
	void removeSupervisionHistoryNote(
			SupervisionHistoryNote supervisionHistoryNote);
	
	/**
	 * Find supervision history section summary by PSI.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return supervision history section summary
	 */
	SupervisionHistorySectionSummary 
	findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Find List of supervision history notes by supervision history 
	 * section summary.
	 *
	 *
	 * @param supervisionHistorysectionSummary supervision history 
	 * section summary
	 * @return list of supervision history notes
	 */
	List<SupervisionHistoryNote> 
		findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
			SupervisionHistorySectionSummary supervisionHistorysectionSummary);	
}