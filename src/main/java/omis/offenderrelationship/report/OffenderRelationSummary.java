package omis.offenderrelationship.report;

import java.io.Serializable;

/**
 * Summary report service for offender relation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (July 20, 2016)
 * @since OMIS 3.0
 */


import omis.family.domain.FamilyAssociation;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.victim.domain.VictimAssociation;
import omis.visitation.domain.VisitationAssociation;

/**
 * Offender relation summary.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.1 (Dec 6, 2018)
 * @since OMIS 3.0
 */
public class OffenderRelationSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String suffix;
	
	private final Boolean offender;
	
	private final Integer offenderNumber;
	
	private final String offenderLastName;
	 
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderOffenderNumber;
	
	private final Boolean victim;
	
	private final Boolean visitor;
	
	private final String visitationAssociationCategoryName;
	
	private final Boolean familyMember;
	
	private final String familyAssociationCategoryName;
	
	private final Long secondPersonId;
	
	private final Long familyAssociationId;
	
	private final Long victimAssociationId;
	
	private final Long visitationAssociationId;
	
	private final Long firstPersonId;
	
	/**
	 * Instantiates an offender relationship summary.
	 * 
	 * @param firstPerson first person
	 * @param victimAssociation victim association
	 * @param familyAssociation family association
	 * @param visitationAssociation visitation association
	 * @param secondPerson second person
	 * @param relationship relationship
	 * @param associate associate
	 */
	public OffenderRelationSummary(
		final Person firstPerson,
		final Person secondPerson,
		final FamilyAssociation familyAssociation,
		final VictimAssociation victimAssociation,
		final VisitationAssociation visitationAssociation,
		final Offender associate,
		final Relationship relationship) {
		this.secondPersonId = secondPerson.getId();
		this.firstPersonId = firstPerson.getId();
		this.id = relationship.getId();
		if (secondPerson.getName() != null) {
			this.lastName = secondPerson.getName().getLastName();
			this.firstName = secondPerson.getName().getFirstName();
			this.middleName = secondPerson.getName().getMiddleName();
			this.suffix = secondPerson.getName().getSuffix();
		} else {
			this.lastName = null;
			this.firstName = null;
			this.middleName = null;
			this.suffix = null;
		}
		
		this.offenderLastName = firstPerson.getName().getLastName();
		this.offenderFirstName = firstPerson.getName().getFirstName();
		this.offenderMiddleName = firstPerson.getName().getMiddleName();
		this.offenderSuffix = firstPerson.getName().getSuffix();
		this.offenderOffenderNumber
			= ((Offender) firstPerson).getOffenderNumber();
		
		if (associate != null) {
			this.offender = true;
			this.offenderNumber = ((Offender) secondPerson).getOffenderNumber();
		} else {
			this.offender = false;
			this.offenderNumber = null;
		}
		
		if (victimAssociation != null) {
			this.victim = true;
			this.victimAssociationId = victimAssociation.getId();
		} else {
			this.victim = false;
			this.victimAssociationId = null;
		}
		if (visitationAssociation != null) {
			this.visitor = true;
			this.visitationAssociationCategoryName 
				= visitationAssociation.getCategory().getName();
			this.visitationAssociationId = visitationAssociation.getId();
		} else {
			this.visitor = false;
			this.visitationAssociationCategoryName = null;
			this.visitationAssociationId = null;
		} 
		if (familyAssociation != null) {
			this.familyMember = true;
			this.familyAssociationCategoryName = familyAssociation
				.getCategory().getName(); 
			this.familyAssociationId = familyAssociation.getId();
		} else {
			this.familyMember = false;
			this.familyAssociationCategoryName = null; 
			this.familyAssociationId = null;
		}
	}
		

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}


	/**
	 * Returns whether relation is an offender.
	 * 
	 * @return whether relation is an offender
	 */
	public Boolean getOffender() {
		return this.offender;
	}

	// Consider renaming "offenderNumber" to "relationOffenderNumber" to make
	// it clearer to whom the number belongs - SA
	
	/**
	 * Returns offender number of relation.
	 * 
	 * @return offender number of relation
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	// Consider renaming "offenderOffenderNumber" to "offenderNumber" to
	// eliminate the recursion in the naming - SA
	
	/**
	 * Returns offender number of offender.
	 * 
	 * @return offender number of offender
	 */
	public Integer getOffenderOffenderNumber() {
		return this.offenderOffenderNumber;
	}
	
	/**
	 * Returns victim or not.
	 * 
	 * @return victim
	 */
	public Boolean getVictim() {
		return this.victim;
	}
	
	/**
	 * Returns visitor status.
	 * 
	 * @return visitor status
	 */
	public Boolean getVisitor() {
		return this.visitor;
	}
	
	/**
	 * Returns visitation association category name.
	 * 
	 * @return visitation association category name
	 */
	public String getVisitationAssociationCategoryName() {
		return this.visitationAssociationCategoryName;
	}
	
	/**
	 * Returns family member.
	 * 
	 * @return family member
	 */
	public Boolean getFamilyMember() {
		return this.familyMember;
	}
	
	/**
	 * Returns family association category name.
	 * 
	 * @return family association category name
	 */
	public String getFamilyAssociationCategoryName() {
		return this.familyAssociationCategoryName;
	}
	
	/**
	 * Returns second person id.
	 * 
	 * @return second person id
	 */
	public Long getSecondPersonId() {
		return this.secondPersonId;
	}
	
	/**
	 * Returns victim association id.
	 * 
	 * @return victim association id
	 */
	public Long getVictimAssociationId() {
		return this.victimAssociationId;
	}
	
	/**
	 * Returns family association id.
	 * 
	 * @return family association id
	 */
	public Long getFamilyAssociationId() {
		return this.familyAssociationId;
	}
	
	/**
	 * Returns visitation association id.
	 * 
	 * @return visitation association id
	 */
	public Long getVisitationAssociationId() {
		return this.visitationAssociationId;
	}
	
	/**
	 * Returns second person id.
	 * 
	 * @return second person id
	 */
	public Long getFirstPersonId() {
		return this.firstPersonId;
	}
}