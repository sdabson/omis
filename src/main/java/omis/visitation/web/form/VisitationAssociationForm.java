package omis.visitation.web.form;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.web.form.PersonFields;
import omis.visitation.domain.VisitationAssociationCategory;

/**
 * Visitation Association Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 19, 2013)
 * @since OMIS 3.0
 */
public class VisitationAssociationForm {
	
	private Offender associatedOffender;
	
	private Person person;
	
	private PersonFields personFields;
	
	private Date decisionDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private Boolean money;
	
	private Boolean nonContact;
	
	private Boolean courtOrder;
	
	private Boolean specialVisit;
	
	private Boolean approved;
	
	private VisitationAssociationCategory category;
	
	private String notes;
	
	private String guardianship;
	
	private Boolean newVisitor;
	
	/** Instantiates a default instance of visitation association form. */
	public VisitationAssociationForm() {
		//Default constructor.
	}

	/**
	 * Returns the associated offender.
	 * 
	 * @return associated offender
	 */
	public Offender getAssociatedOffender() {
		return this.associatedOffender;
	}

	/**
	 * Sets the associated offender.
	 * 
	 * @param associatedOffender associated offender
	 */
	public void setAssociatedOffender(final Offender associatedOffender) {
		this.associatedOffender = associatedOffender;
	}

	/**
	 * Return the visitor of the visitation association form.
	 * @return the visitor
	 */
	public Person getPerson() {
		return this.person;
	}

	/**
	 * Set the person.
	 * 
	 * @param person the person to set
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the person fields.
	 * 
	 * @return person fields
	 */
	public PersonFields getPersonFields() {
		return this.personFields;
	}

	/**
	 * Sets the person fields.
	 * 
	 * @param personFields person fields
	 */
	public void setPersonFields(final PersonFields personFields) {
		this.personFields = personFields;
	}

	/**
	 * Return the start date of the visitation association form.
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Set the start date of the visitation association form.
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Return the end date of the visitation association form.
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Set the end date of the visitation association form.
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the value of the money option for the visitation 
	 * association form.
	 * @return money value
	 */
	public Boolean getMoney() {
		return this.money;
	}

	/**
	 * Sets the value of the money option for the visitation association
	 * form.
	 * @param money money value
	 */
	public void setMoney(final Boolean money) {
		this.money = money;
	}

	/**
	 * Return the value of the non contact option for the visitation
	 * association form.
	 * @return non contact value
	 */
	public Boolean getNonContact() {
		return this.nonContact;
	}

	/**
	 * Sets the value of the non contact option for the visitation
	 * association form.
	 * @param nonContact non contact value
	 */
	public void setNonContact(final Boolean nonContact) {
		this.nonContact = nonContact;
	}

	/**
	 * Returns the value of the court order option for the visitation
	 * association form.
	 * @return court order value
	 */
	public Boolean getCourtOrder() {
		return this.courtOrder;
	}

	/**
	 * Sets the value of the court order option for the visitation
	 * association form.
	 * @param courtOrder court order value
	 */
	public void setCourtOrder(final Boolean courtOrder) {
		this.courtOrder = courtOrder;
	}

	/**
	 * Returns the value of the special visit option for the visitation
	 * association form. 
	 * @return special visit value
	 */
	public Boolean getSpecialVisit() {
		return this.specialVisit;
	}

	/**
	 * Sets the value of the special visit option for the visitation
	 * association form. 
	 * @param specialVisit special visit value
	 */
	public void setSpecialVisit(final Boolean specialVisit) {
		this.specialVisit = specialVisit;
	}

	/**
	 * Returns the decision date of the visitation association form.
	 * @return the decisionDate
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}

	/**
	 * Sets the decision date of the visitation association form.
	 * @param decisionDate the decisionDate to set
	 */
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * Returns the approved value of the visitation association form.
	 * @return the approved
	 */
	public Boolean getApproved() {
		return this.approved;
	}

	/**
	 * Sets the approved value of the visitation association form.
	 * @param approved the approved to set
	 */
	public void setApproved(final Boolean approved) {
		this.approved = approved;
	}

	/**
	 * Returns the visitation association category.
	 * 
	 * @return visitation association category
	 */
	public VisitationAssociationCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the visitation association category.
	 * 
	 * @param category visitation association category
	 */
	public void setCategory(final VisitationAssociationCategory category) {
		this.category = category;
	}

	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Returns guardianship.
	 * 
	 * @return guardianship
	 */
	public String getGuardianship() {
		return this.guardianship;
	}
	
	/**
	 * Sets guardianship.
	 * 
	 * @param guardianship guardianship
	 */
	public void setGuardianship(final String guardianship) {
		this.guardianship = guardianship;
	}

	/**
	 * Returns whether new visitor applies.
	 * 
	 * @return new visitor
	 */
	public Boolean getNewVisitor() {
		return this.newVisitor;
	}

	/**
	 * Sets whether new visitor applies.
	 * 
	 * @param newVisitor new visitor
	 */
	public void setNewVisitor(final Boolean newVisitor) {
		this.newVisitor = newVisitor;
	}
}