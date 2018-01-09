package omis.family.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.family.domain.FamilyAssociationCategory;

/**
 * Family association fields.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 29, 2016)
 * @since OMIS 3.0
 */
public class FamilyAssociationFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	
	private Date endDate;
	
	private FamilyAssociationCategory category;
	
	private Date marriageDate;
	
	private Date divorceDate;
	
	private Boolean emergencyContact;
	
	private Boolean dependent;
	
	private Boolean cohabitant;
	
	/**
	 * Instantiates a default instance of family association fields.
	 */
	public FamilyAssociationFields() {
		//Default constructor.
	}

	/**
	 * Family association fields.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @param category category
	 * @param marriageDate marriage date
	 * @param divorceDate divorce date
	 * @param emergencyContact emergency contact
	 * @param dependent dependent
	 * @param cohabitant cohabitant
	 */
	public FamilyAssociationFields(final Date startDate, final Date endDate, 
			final FamilyAssociationCategory category, final Date marriageDate,
			final Date divorceDate, final Boolean emergencyContact, 
			final Boolean dependent, final Boolean cohabitant) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.marriageDate = marriageDate;
		this.divorceDate = divorceDate;
		this.emergencyContact = emergencyContact;
		this.dependent = dependent;
		this.cohabitant = cohabitant;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return the start date
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
	 * @return the end date
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
	 * Returns the category.
	 * 
	 * @return the category
	 */
	public FamilyAssociationCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	public void setCategory(final FamilyAssociationCategory category) {
		this.category = category;
	}

	/**
	 * Returns the marriage date.
	 * 
	 * @return the marriage date
	 */
	public Date getMarriageDate() {
		return this.marriageDate;
	}

	/**
	 * Sets the marriage date.
	 * 
	 * @param marriageDate marriage date
	 */
	public void setMarriageDate(final Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	/**
	 * Returns the divorce date.
	 * 
	 * @return the divorce date
	 */
	public Date getDivorceDate() {
		return this.divorceDate;
	}

	/**
	 * Sets the divorce date.
	 * 
	 * @param divorceDate divorce date
	 */
	public void setDivorceDate(final Date divorceDate) {
		this.divorceDate = divorceDate;
	}

	/**
	 * Returns the emergency contact.
	 * 
	 * @return the emergency contact
	 */
	public Boolean getEmergencyContact() {
		return this.emergencyContact;
	}

	/**
	 * Sets the emergency contact.
	 * 
	 * @param emergencyContact emergency contact
	 */
	public void setEmergencyContact(final Boolean emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * Returns the dependent.
	 * 
	 * @return the dependent
	 */
	public Boolean getDependent() {
		return this.dependent;
	}

	/**
	 * Sets the dependent.
	 * 
	 * @param dependent dependent
	 */
	public void setDependent(final Boolean dependent) {
		this.dependent = dependent;
	}

	/**
	 * Returns the cohabitant.
	 * 
	 * @return the cohabitant
	 */
	public Boolean getCohabitant() {
		return this.cohabitant;
	}

	/**
	 * Sets the cohabitant.
	 * 
	 * @param cohabitant cohabitant
	 */
	public void setCohabitant(final Boolean cohabitant) {
		this.cohabitant = cohabitant;
	}
}