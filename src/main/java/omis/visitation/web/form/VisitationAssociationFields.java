package omis.visitation.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.visitation.domain.VisitationAssociationCategory;

/**
 * Visitation association fields.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (May 12, 2016)
 * @since OMIS 3.0
 */
public class VisitationAssociationFields implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean money;
	
	private Boolean nonContact;
	
	private Boolean courtOrder;
	
	private Boolean specialVisit;
	
	private Boolean approved;
	
	private Date decisionDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private List<GuardianshipItem> guardianshipItems;
	
	private String guardianship;
	
	private VisitationAssociationCategory category;
	
	private String notes;
	
	/**
	 * Instantiates a default instance of visitation association fields.
	 */
	public VisitationAssociationFields() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of visitation association fields with the
	 * specified properties.
	 * 
	 * @param money money
	 * @param nonContact non contact
	 * @param courtOrder court order
	 * @param specialVisit special visit
	 * @param approved approved
	 * @param decisionDate decision date
	 */
	public VisitationAssociationFields(final Boolean money,
			final Boolean nonContact, final Boolean courtOrder,
			final Boolean specialVisit, final Boolean approved,
			final Date decisionDate, final Date startDate, final Date endDate,
			final List<GuardianshipItem> guardianshipItems,
			final Boolean categoryRequired,
			final VisitationAssociationCategory category) {
		this.money = money;
		this.nonContact = nonContact;
		this.courtOrder = courtOrder;
		this.specialVisit = specialVisit;
		this.approved = approved;
		this.decisionDate = decisionDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.guardianshipItems = guardianshipItems;
		this.category = category;
	}

	/**
	 * Returns whether money applies.
	 * 
	 * @return money
	 */
	public Boolean getMoney() {
		return this.money;
	}

	/**
	 * Sets whether money applies.
	 * 
	 * @param money money
	 */
	public void setMoney(final Boolean money) {
		this.money = money;
	}

	/**
	 * Returns whether non contact applies.
	 * 
	 * @return non contact
	 */
	public Boolean getNonContact() {
		return this.nonContact;
	}

	/**
	 * Sets whether non contact applies.
	 * 
	 * @param nonContact non contact
	 */
	public void setNonContact(final Boolean nonContact) {
		this.nonContact = nonContact;
	}

	/**
	 * Returns whether court order applies.
	 * 
	 * @return court order
	 */
	public Boolean getCourtOrder() {
		return this.courtOrder;
	}

	/**
	 * Sets whether court order applies.
	 * 
	 * @param courtOrder court order
	 */
	public void setCourtOrder(final Boolean courtOrder) {
		this.courtOrder = courtOrder;
	}

	/**
	 * Returns whether special visit applies.
	 * 
	 * @return special visit
	 */
	public Boolean getSpecialVisit() {
		return this.specialVisit;
	}

	/**
	 * Sets whether special visit applies.
	 * 
	 * @param specialVisit special visit
	 */
	public void setSpecialVisit(final Boolean specialVisit) {
		this.specialVisit = specialVisit;
	}

	/**
	 * Returns whether approved applies.
	 * 
	 * @return approved
	 */
	public Boolean getApproved() {
		return this.approved;
	}

	/**
	 * Sets whether approved applies.
	 * 
	 * @param approved approved
	 */
	public void setApproved(final Boolean approved) {
		this.approved = approved;
	}

	/**
	 * Returns the decision date.
	 * 
	 * @return decision date
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}

	/**
	 * Sets the decision date.
	 * 
	 * @param decisionDate decision date
	 */
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns the guardianship items.
	 * 
	 * @return list of guardianship items
	 */
	public List<GuardianshipItem> getGuardianshipItems() {
		return this.guardianshipItems;
	}

	/**
	 * Sets the guardianship items.
	 * 
	 * @param guardianshipItems guardianship items
	 */
	public void setGuardianshipItems(
			final List<GuardianshipItem> guardianshipItems) {
		this.guardianshipItems = guardianshipItems;
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
	 * Returns the visitation association notes.
	 * 
	 * @return visitation association notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the visitation association notes.
	 * 
	 * @param category visitation association notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
}