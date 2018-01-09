package omis.detainernotification.web.form;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.address.web.form.AddressFields;
import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.domain.DetainerJurisdictionCategory;
import omis.detainernotification.domain.DetainerType;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;
import omis.offender.domain.Offender;

/**
 * DetainerNotificationForm.java
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.1 (May 16, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerNotificationForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offender offender;
	private String alternateOffenderId;
	private String offenseDescription;
	private String courtCaseNumber;
	private DetainerType detainerType;
	private DetainerAgency detainerAgency;
	private DetainerJurisdictionCategory jurisdiction;
	private Date receiveDate;
	private Date issueDate;
	private String warrantNumber;
	private DetainerWarrantProcessingStatusFields 
		detainerWarrantProcessingStatusFields;
	private Boolean usingInterstateAgreementDetainer;
	private Boolean creatingDetainerAgency;
	private Boolean usingAddress;
	private Boolean processed;
	private String agencyName;
	private String telephoneNumber;
	private AddressFields addressFields;
	private InterstateAgreementInitiatedByCategory initiatedBy;
	private Date prosecutorReceivedDate;
	private List<InterstateDetainerActivityItem>
		interstateDetainerActivityItems;
	private List<DetainerNoteItem> noteItems 
		= new ArrayList<DetainerNoteItem>();

	/**
	 * Default constructor for Detainer Notification Form
	 */
	public DetainerNotificationForm(){
		// Default constructor
	}
	
	/**
	 * Returns the initiatedBy
	 * @return initiatedBy - InterstateAgreementInitiatedByCategory
	 */
	public InterstateAgreementInitiatedByCategory getInitiatedBy() {
		return initiatedBy;
	}

	/**
	 * Sets the initiatedBy
	 * @param initiatedBy - InterstateAgreementInitiatedByCategory
	 */
	public void setInitiatedBy(
			final InterstateAgreementInitiatedByCategory initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	/**
	 * Returns offender of detainer notification form
	 * @return the offender
	 */
	public Offender getOffender() {
		return offender;
	}

	/**
	 * Sets the offender of detainer notification form
	 * @param offender the offender to set
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns the alternate offender id of detainer notification form
	 * @return the alternateOffenderId
	 */
	public String getAlternateOffenderId() {
		return alternateOffenderId;
	}

	/**
	 * Sets the alternate offender id of detainer notification form
	 * @param alternateOffenderId the alternateOffenderId to set
	 */
	public void setAlternateOffenderId(final String alternateOffenderId) {
		this.alternateOffenderId = alternateOffenderId;
	}

	/**
	 * Returns the offense desctiption of detainer notification form
	 * @return the offenseDescription
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}

	/**
	 * Sets the offense description of detainer notification form
	 * @param offenseDescription the offenseDescription to set
	 */
	public void setOffenseDescription(final String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}

	/**
	 * Returns the court case number of detainer notification form
	 * @return the courtCaseNumber
	 */
	public String getCourtCaseNumber() {
		return courtCaseNumber;
	}

	/**
	 * Sets the court case number of detainer notification form
	 * @param courtCaseNumber the courtCaseNumber to set
	 */
	public void setCourtCaseNumber(final String courtCaseNumber) {
		this.courtCaseNumber = courtCaseNumber;
	}

	/**
	 * Returns detainer type of detainer notification form
	 * @return the detainerType
	 */
	public DetainerType getDetainerType() {
		return detainerType;
	}

	/**
	 * Sets detainer type of detainer notification form
	 * @param detainerType the detainerType to set
	 */
	public void setDetainerType(final DetainerType detainerType) {
		this.detainerType = detainerType;
	}

	/**
	 * Returns detainer agency of detainer notification form
	 * @return the detainerAgency
	 */
	public DetainerAgency getDetainerAgency() {
		return detainerAgency;
	}

	/**
	 * Sets detainer agency of detainer notification form
	 * @param detainerAgency the detainerAgency to set
	 */
	public void setDetainerAgency(final DetainerAgency detainerAgency) {
		this.detainerAgency = detainerAgency;
	}

	/**
	 * Returns detainer jurisdiction category of detainer notification form
	 * @return the jurisdiction
	 */
	public DetainerJurisdictionCategory getJurisdiction() {
		return this.jurisdiction;
	}

	/**
	 * Sets detainer jurisdiction category of detainer notification form
	 * @param jurisdiction the DetainerJurisdictionCategory to set
	 */
	public void setJurisdiction(final DetainerJurisdictionCategory jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * Returns the receive date.
	 * 
	 * @return receive date
	 */
	public Date getReceiveDate() {
		return this.receiveDate;
	}

	/**
	 * Sets the receive date.
	 * 
	 * @param receiveDate receive date
	 */
	public void setReceiveDate(final Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * Returns the issue date.
	 * 
	 * @return issue date
	 */
	public Date getIssueDate() {
		return this.issueDate;
	}

	/**
	 * Sets the issue date.
	 * 
	 * @param issueDate issue date
	 */
	public void setIssueDate(final Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Returns the warrant number.
	 * 
	 * @return warrant number
	 */
	public String getWarrantNumber() {
		return this.warrantNumber;
	}

	/**
	 * Sets the warrant number.
	 * 
	 * @param warrantNumber warrant number
	 */
	public void setWarrantNumber(final String warrantNumber) {
		this.warrantNumber = warrantNumber;
	}

	/**
	 * Returns detainer warrant processing status fields of detainer
	 * notification forms
	 * @return the detainerWarrantProcessingStatusFields
	 */
	public DetainerWarrantProcessingStatusFields
			getDetainerWarrantProcessingStatusFields() {
		return detainerWarrantProcessingStatusFields;
	}

	/**
	 * Sets detainer warrant processing fields of detainer notification form
	 * @param detainerWarrantProcessingStatusFields the detainerWarrantProcessingStatusFields to set
	 */
	public void setDetainerWarrantProcessingStatusFields(
			final DetainerWarrantProcessingStatusFields
			detainerWarrantProcessingStatusFields) {
		this.detainerWarrantProcessingStatusFields =
				detainerWarrantProcessingStatusFields;
	}

	/**
	 * Returns using interstate agreement detainer of detainer notification form
	 * @return the usingInterstateAgreementDetainer - boolean
	 */
	public Boolean getUsingInterstateAgreementDetainer() {
		return usingInterstateAgreementDetainer;
	}

	/**
	 * Sets using interstate agreement detainer of detainer notification form
	 * @param usingInterstateAgreementDetainer the usingInterstateAgreementDetainer to set - Boolean
	 */
	public void setUsingInterstateAgreementDetainer(
			final Boolean usingInterstateAgreementDetainer) {
		this.usingInterstateAgreementDetainer = usingInterstateAgreementDetainer;
	}
	
	/**
	 * Returns creating detainer agency of detainer notification form
	 * @return the creatingDetainerAgency - Boolean
	 */
	public Boolean getCreatingDetainerAgency() {
		return creatingDetainerAgency;
	}

	/**
	 * Sets creating detainer agency of detainer notification form
	 * @param creatingDetainerAgency the creatingDetainerAgency to set
	 */
	public void setCreatingDetainerAgency(final Boolean creatingDetainerAgency) {
		this.creatingDetainerAgency = creatingDetainerAgency;
	}

	/**
	 * Sets telephone number of detainer notification form
	 * @param telephoneNumber the telephoneNumber to set - String
	 */
	public void setTelephoneNumber(final String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * Returns using address of detainer notification form
	 * @return the usingAddress - Boolean
	 */
	public Boolean getUsingAddress() {
		return usingAddress;
	}

	/**
	 * Sets using address of detainer notification form
	 * @param usingAddress the usingAddress to set - Boolean
	 */
	public void setUsingAddress(final Boolean usingAddress) {
		this.usingAddress = usingAddress;
	}

	/**
	 * Returns whether processed applies.
	 * 
	 * @return processed
	 */
	public Boolean getProcessed() {
		return this.processed;
	}

	/**
	 * Sets whether processed applies.
	 * 
	 * @param processed processed
	 */
	public void setProcessed(final Boolean processed) {
		this.processed = processed;
	}

	/**
	 * Returns agency name of of detainer notification form
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return this.agencyName;
	}

	/**
	 * Sets agency name of detainer notification form
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(final String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 * Returns telephone number of of detainer notification form
	 * @return the telephoneNumber - String
	 */
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/**
	 * Returns the address fields of detainer notification form
	 * @return the addressFields
	 */
	public AddressFields getAddressFields() {
		return addressFields;
	}

	/**
	 * Sets the address fields of detainer notification form
	 * @param addressFields the addressFields to set
	 */
	public void setAddressFields(final AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	/**
	 * Returns the prosecutorReceivedDate
	 * @return prosecutorReceivedDate - Date
	 */
	public Date getProsecutorReceivedDate() {
		return prosecutorReceivedDate;
	}


	/**
	 * Sets the prosecutorReceivedDate
	 * @param prosecutorReceivedDate - Date
	 */
	public void setProsecutorReceivedDate(Date prosecutorReceivedDate) {
		this.prosecutorReceivedDate = prosecutorReceivedDate;
	}


	/**
	 * Returns the interstateDetainerActivityItems
	 * @return interstateDetainerActivityItems - List<InterstateDetainerActivityItem>
	 */
	public List<InterstateDetainerActivityItem> getInterstateDetainerActivityItems() {
		return interstateDetainerActivityItems;
	}


	/**
	 * Sets the interstateDetainerActivityItems
	 * @param interstateDetainerActivityItems - List<InterstateDetainerActivityItem>
	 */
	public void setInterstateDetainerActivityItems(
			final List<InterstateDetainerActivityItem>
				interstateDetainerActivityItems) {
		this.interstateDetainerActivityItems = interstateDetainerActivityItems;
	}

	/**
	 * Returns the note items.
	 * 
	 * @return detainer note items
	 */
	public List<DetainerNoteItem> getNoteItems() {
		return this.noteItems;
	}

	/**
	 * Sets the detainer note items.
	 * 
	 * @param noteItems detainer note items
	 */
	public void setNoteItems(final List<DetainerNoteItem> noteItems) {
		this.noteItems = noteItems;
	}
}
