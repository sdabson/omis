package omis.victim.report;

import java.util.List;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocumentAssociation;

/**
 * Report service for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 8, 2015)
 * @since OMIS 3.0
 */
public interface VictimAssociationReportService {

	/**
	 * Returns summary of victims by offender.
	 * 
	 * @param offender offender
	 * @return summary of victims by offender
	 */
	List<VictimAssociationSummary> findSummariesByOffender(Offender offender);
	
	/**
	 * Return summary of victim association.
	 * 
	 * @param victimAssociation - VictimAssociation
	 * @return summary of victim association
	 */
	VictimAssociationSummary summarizeVictimAssociation(
			VictimAssociation victimAssociation);
	
	/**
	 * Returns summary of victim associations by victim.
	 * 
	 * @param victim victim
	 * @return summary of victim associations by victim
	 */
	List<VictimAssociationSummary> findSummariesByVictim(Person victim);
	
	/**
	 * Returns victim document associations for the specified victim and docket.
	 * 
	 * @param docket docket
	 * @param victim victim
	 * @return list of victim document associations
	 */
	public List<VictimDocumentAssociationSummary> 
		findDocumentAssociationSummariesByVictim(Person victim);

	/**
	 * Returns whether association has notes.
	 * 
	 * @param victimAssociation association
	 * @return whether association has notes
	 */
	boolean hasNotes(VictimAssociation victimAssociation);
	
	
	/**
	 * Returns whether association is an offender
	 * @param person
	 * @return
	 */
	boolean isOffender(Person person);
}