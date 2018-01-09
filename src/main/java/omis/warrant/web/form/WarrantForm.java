package omis.warrant.web.form;

import java.util.Date;
import java.util.List;

import omis.jail.domain.Jail;
import omis.person.domain.Person;

/**
 * WarrantForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantForm {
	
	private Date date;
	
	private String addressee;
	
	private Person issuedBy;
	
	private String bondRecommendation;
	
	private Boolean bondRecommended;
	
	private Boolean arrested;
	
	private Date arrestDate;
	
	private Jail arrestJail;
	
	private Date contactBy;//TODO figure out exactly what this is supposed to be
	
	private List<WarrantNoteItem> warrantNoteItems;
	
	private List<WarrantCauseViolationItem> warrantCauseViolationItems;
	
	/**
	 * 
	 */
	public WarrantForm() {
	}
	
	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the addressee
	 * @return addressee - String
	 */
	public String getAddressee() {
		return addressee;
	}

	/**
	 * Sets the addressee
	 * @param addressee - String
	 */
	public void setAddressee(final String addressee) {
		this.addressee = addressee;
	}

	/**
	 * Returns the issuedBy
	 * @return issuedBy - Person
	 */
	public Person getIssuedBy() {
		return issuedBy;
	}

	/**
	 * Sets the issuedBy
	 * @param issuedBy - Person
	 */
	public void setIssuedBy(final Person issuedBy) {
		this.issuedBy = issuedBy;
	}

	/**
	 * Returns the bondRecommendation
	 * @return bondRecommendation - String
	 */
	public String getBondRecommendation() {
		return bondRecommendation;
	}

	/**
	 * Sets the bondRecommendation
	 * @param bondRecommendation - String
	 */
	public void setBondRecommendation(final String bondRecommendation) {
		this.bondRecommendation = bondRecommendation;
	}

	/**
	 * Returns the bondRecommended
	 * @return bondRecommended - Boolean
	 */
	public Boolean getBondRecommended() {
		return bondRecommended;
	}

	/**
	 * Sets the bondRecommended
	 * @param bondRecommended - Boolean
	 */
	public void setBondRecommended(final Boolean bondRecommended) {
		this.bondRecommended = bondRecommended;
	}

	/**
	 * Returns the arrested
	 * @return arrested - Boolean
	 */
	public Boolean getArrested() {
		return arrested;
	}

	/**
	 * Sets the arrested
	 * @param arrested - Boolean
	 */
	public void setArrested(final Boolean arrested) {
		this.arrested = arrested;
	}

	/**
	 * Returns the arrestDate
	 * @return arrestDate - Date
	 */
	public Date getArrestDate() {
		return arrestDate;
	}

	/**
	 * Sets the arrestDate
	 * @param arrestDate - Date
	 */
	public void setArrestDate(final Date arrestDate) {
		this.arrestDate = arrestDate;
	}

	/**
	 * Returns the arrestJail
	 * @return arrestJail - Jail
	 */
	public Jail getArrestJail() {
		return arrestJail;
	}

	/**
	 * Sets the arrestJail
	 * @param arrestJail - Jail
	 */
	public void setArrestJail(final Jail arrestJail) {
		this.arrestJail = arrestJail;
	}

	/**
	 * Returns the contactBy
	 * @return contactBy - Date
	 */
	public Date getContactBy() {
		return contactBy;
	}

	/**
	 * Sets the contactBy
	 * @param contactBy - Date
	 */
	public void setContactBy(final Date contactBy) {
		this.contactBy = contactBy;
	}

	/**
	 * Returns the warrantNoteItems
	 * @return warrantNoteItems - List<WarrantNoteItem>
	 */
	public List<WarrantNoteItem> getWarrantNoteItems() {
		return warrantNoteItems;
	}

	/**
	 * Sets the warrantNoteItems
	 * @param warrantNoteItems - List<WarrantNoteItem>
	 */
	public void setWarrantNoteItems(final List<WarrantNoteItem> warrantNoteItems) {
		this.warrantNoteItems = warrantNoteItems;
	}

	/**
	 * Returns the warrantCauseViolationItems
	 * @return warrantCauseViolationItems - List<WarrantCauseViolationItem>
	 */
	public List<WarrantCauseViolationItem> getWarrantCauseViolationItems() {
		return warrantCauseViolationItems;
	}

	/**
	 * Sets the warrantCauseViolationItems
	 * @param warrantCauseViolationItems - List<WarrantCauseViolationItem>
	 */
	public void setWarrantCauseViolationItems(
			final List<WarrantCauseViolationItem> warrantCauseViolationItems) {
		this.warrantCauseViolationItems = warrantCauseViolationItems;
	}
	
	
	
	
}
