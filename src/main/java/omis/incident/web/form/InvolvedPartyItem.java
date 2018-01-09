package omis.incident.web.form;

import java.io.Serializable;

import omis.audit.domain.VerificationMethod;
import omis.incident.domain.InvolvedParty;
import omis.incident.domain.InvolvedPartyCategory;
import omis.person.domain.Person;

/**
 * Involved party item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 7, 2015)
 * @since OMIS 3.0
 */
public class InvolvedPartyItem implements Serializable {

	private static final long serialVersionUID = 1L;

	Person person;
	
	Integer offenderNumber;
	
	String staffTitle;
	
	VerificationMethod verificationMethod;
	
	Boolean verified;
	
	InvolvedParty involvedParty;
	
	InvolvedPartyCategory category;
	
	InvolvedPartyItemOperation operation;
	
	String narrative;
	
	String name;
	
	/**
	 * Instantiates a default instance of involved party item.
	 */
	public InvolvedPartyItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of involved party item with the specified
	 * involved party and verification signature;
	 * 
	 * @param person person
	 * @param offenderNumber offender number
	 * @param staffTitle staff title
	 * @param verificationMethod verification method
	 * @param verified verified
	 * @param involvedParty involved party
	 * @param category involved party category
	 * @param operation involved party item operation
	 * @param narrative narrative
	 * @param name name
	 */
	public InvolvedPartyItem(
			final Person person,
			final Integer offenderNumber,
			final String staffTitle,
			final VerificationMethod verificationMethod,
			final Boolean verified,
			final InvolvedParty involvedParty,
			final InvolvedPartyCategory category,
			final InvolvedPartyItemOperation operation,
			final String narrative, final String name) {
		this.person = person;
		this.offenderNumber = offenderNumber;
		this.staffTitle = staffTitle;
		this.verificationMethod = verificationMethod;
		this.verified = verified;
		this.involvedParty = involvedParty;
		this.category = category;
		this.operation = operation;
		this.narrative = narrative;
		this.name = name;
	}

	/**
	 * Returns the person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}

	/**
	 * Sets the person.
	 * 
	 * @param person person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}
	
	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Sets the offender number.
	 * 
	 * @param offenderNumber offender number
	 */
	public void setOffenderNumber(final Integer offenderNumber) {
		this.offenderNumber = offenderNumber;
	}
	
	/**
	 * Returns the staff title.
	 * 
	 * @return staff title
	 */
	public String getStaffTitle() {
		return this.staffTitle;
	}
	
	/**
	 * Sets the staff title.
	 * 
	 * @param staffTitle staff title
	 */
	public void setStaffTitle(final String staffTitle) {
		this.staffTitle = staffTitle;
	}

	/**
	 * Returns the verification method.
	 * 
	 * @return verification method
	 */
	public VerificationMethod getVerificationMethod() {
		return this.verificationMethod;
	}

	/**
	 * Sets the verification method.
	 * 
	 * @param verificationMethod verification method
	 */
	public void setVerificationMethod(
			final VerificationMethod verificationMethod) {
		this.verificationMethod = verificationMethod;
	}

	/**
	 * Returns whether verified applies.
	 * 
	 * @return verified
	 */
	public Boolean getVerified() {
		return this.verified;
	}

	/**
	 * Sets whether verified applies.
	 * 
	 * @param verified verified
	 */
	public void setVerified(final Boolean verified) {
		this.verified = verified;
	}

	/**
	 * Returns the involved party.
	 * 
	 * @return involved party
	 */
	public InvolvedParty getInvolvedParty() {
		return this.involvedParty;
	}

	/**
	 * Sets the involved party.
	 * 
	 * @param involvedParty involved party
	 */
	public void setInvolvedParty(final InvolvedParty involvedParty) {
		this.involvedParty = involvedParty;
	}

	/**
	 * Returns the involved party category.
	 * 
	 * @return involved party category
	 */
	public InvolvedPartyCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Set the involved party category.
	 * 
	 * @param category involved party category
	 */
	public void setCategory(final InvolvedPartyCategory category) {
		this.category = category;
	}
	
	/**
	 * Returns the operation.
	 * 
	 * @return operation
	 */
	public InvolvedPartyItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation involved party item operation
	 */
	public void setOperation(final InvolvedPartyItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the narrative.
	 * 
	 * @return narrative
	 */
	public String getNarrative() {
		return this.narrative;
	}

	/**
	 * Sets the narrative.
	 * 
	 * @param narrative narrative
	 */
	public void setNarrative(final String narrative) {
		this.narrative = narrative;
	}

	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets name.
	 * 
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}
}