package omis.presentenceinvestigation.web.form;

import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.report.VictimAssociationSummary;

/**
 * Victim Section Summary Docket Association Item
 *
 * @author Trevor Isles
 * @version 0.1.0 (Sep 26, 2017)
 * @since OMIS 3.0
 */
public class VictimSectionSummaryDocketAssociationItem {

	private Person person;
	
	private VictimAssociation victimAssociation;
	
	private VictimDocketAssociation victimDocketAssociation;
	
	private VictimAssociationSummary victimAssociationSummary;
	
	private VictimDocketDocumentAssociationItemOperation itemOperation;
	
	/**
	 *  Default constructor for Victim Section Summary Docket Association Item
	 */
	public VictimSectionSummaryDocketAssociationItem() {
		// Default instantiation
	}
	
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return this.person;
	}
	
	/**
	 * Sets the person of the Victim Section Summary Docket Association Item
	 * @param person the person to set
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}
	
	/**
	 * @return the victimAssociation
	 */
	public VictimAssociation getVictimAssociation() {
		return this.victimAssociation;
	}

	/**
	 * @param victimAssociation the victimAssociation to set
	 */
	public void setVictimAssociation(final VictimAssociation victimAssociation) {
		this.victimAssociation = victimAssociation;
	}

	/**
	 * @return the victimDocketAssociation
	 */
	public VictimDocketAssociation getVictimDocketAssociation() {
		return victimDocketAssociation;
	}
	
	/**
	 * @param victimDocketAssociation the victimDocketAssociation to set
	 */
	public void setVictimDocketAssociation(
			final VictimDocketAssociation victimDocketAssociation) {
		this.victimDocketAssociation = victimDocketAssociation;
	}

	/**
	 * @return the victimAssociationSummary
	 */
	public VictimAssociationSummary getVictimAssociationSummary() {
		return this.victimAssociationSummary;
	}

	/**
	 * @param victimAssociationSummary the victimAssociationSummary to set
	 */
	public void setVictimAssociationSummary(
			final VictimAssociationSummary victimAssociationSummary) {
		this.victimAssociationSummary = victimAssociationSummary;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - VictimDocketDocumentAssociationItemOperation
	 */
	public VictimDocketDocumentAssociationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - VictimDocketDocumentAssociationItemOperation
	 */
	public void setItemOperation(
			final VictimDocketDocumentAssociationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
