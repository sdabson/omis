package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocketAssociation;

/**
 * VictimSectionSummaryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public interface VictimSectionSummaryService {
	
	/**
	 * Creates a VictimSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created VictimSectionSummary
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummary
	 * already exists with the specified PresentenceInvestigationRequest
	 */
	public VictimSectionSummary createVictimSectionSummary(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a VictimSectionSummary with the specified properties
	 * @param victimSectionSummary - VictimSectionSummary to update
	 * @param text - String
	 * @return Updated VictimSectionSummary
	 * @throws DuplicateEntityFoundException - When another VictimSectionSummary
	 * already exists with given VictimSectionSummary's
	 * PresentenceInvestigationRequest
	 */
	public VictimSectionSummary updateVictimSectionSummary(
			VictimSectionSummary victimSectionSummary,
			String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary to remove
	 */
	public void removeVictimSectionSummary(
			VictimSectionSummary victimSectionSummary);
	
	/**
	 * Creates a VictimSectionSummaryNote with the specified properties
	 * @param victimSectionSummary - VictimSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created VictimSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummaryNote
	 * already exists with the given Date and Description for the specified
	 * VictimSectionSummary
	 */
	public VictimSectionSummaryNote createVictimSectionSummaryNote(
			VictimSectionSummary victimSectionSummary,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a VictimSectionSummaryNote with the specified properties
	 * @param victimSectionSummaryNote - VictimSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated VictimSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When another
	 * VictimSectionSummaryNote already exists with the specified Date and
	 * Description for the specified VictimSectionSummaryNote's 
	 * VictimSectionSummary
	 */
	public VictimSectionSummaryNote updateVictimSectionSummaryNote(
			VictimSectionSummaryNote victimSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a VictimSectionSummaryNote
	 * @param victimSectionSummaryNote - VictimSectionSummaryNote to remove
	 */
	public void removeVictimSectionSummaryNote(
			VictimSectionSummaryNote victimSectionSummaryNote);
	
	/**
	 * Returns a List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary
	 * @return List of VictimSectionSummaryNotes found by specified
	 * VictimSectionSummary
	 */
	public List<VictimSectionSummaryNote>
		findVictimSectionSummaryNotesByVictimSectionSummary(
			VictimSectionSummary victimSectionSummary);
	
	/**
	 * Returns a VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public VictimSectionSummary
		findVictimSectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * 
	 * @param victimAssociation
	 * @param docket
	 * @return
	 */
	public VictimDocketAssociation createVictimDocketAssociation(
			VictimAssociation victimAssociation, Docket docket);
	
	/**
	 * 
	 * @param victimDocketAssociation
	 */
	public void removeVictimDocketAssociation(
			VictimDocketAssociation victimDocketAssociation);
	
	/**
	 * Returns a list of victims associated by offender.
	 * @param offender
	 * @return List of victim associations by offender
	 */
	public List<VictimAssociation> findVictimsByOffender(Offender offender);
	
	/**
	 * 
	 * @param docket
	 * @return List of victim associations by docket
	 */
	public List<VictimDocketAssociation> findVictimAssociationByDocket(
			Docket docket);
	
}
