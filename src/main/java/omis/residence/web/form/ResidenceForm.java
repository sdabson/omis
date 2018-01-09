/**
 * 
 */
package omis.residence.web.form;

import java.util.Date;

import omis.address.domain.ZipCode;
import omis.audit.domain.VerificationMethod;
import omis.location.domain.Location;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.residence.domain.ResidenceStatus;
import omis.residence.domain.ResidenceTerm;
import omis.residence.report.ResidenceType;
import omis.user.domain.UserAccount;

/**
 * Residence form.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 2, 2015)
 * @since OMIS 3.0
 */
public class ResidenceForm {
	
	private ResidenceStatusOption statusOption;
	
	private ResidenceStatus status;
	
	private ResidenceType residenceType;
	
	private Date startDate;
	
	private Date endDate;
	
	private Location location;
	
	private String value;
	
	private State state;
	
	private City city;
	
	private ZipCode zipCode;
	
	private String residenceComment;
	
	private Boolean confirmed;
	
	private Boolean createNewLocation;
	
	private String locationName;
	
	private UserAccount verifiedByUserAccount;
	
	private Date verificationDate;
	
	private Boolean verified;
	
	private VerificationMethod verificationMethod;
	
	private ResidenceTerm existingPrimaryResidence;
	
	private ExistingResidenceOperation existingResidenceOperation;
	
	private Date existingHistoricalEndDate;
	
	/**
	 * Instantiates a default instance of residence form.
	 */
	public ResidenceForm() {
		//Default constructor.
	}
	
	/**
	 * Returns the residence status option.
	 * 
	 * @return the statusOption
	 */
	public ResidenceStatusOption getStatusOption() {
		return this.statusOption;
	}

	/**
	 * Sets the residence status options.
	 * 
	 * @param statusOption the status option to set
	 */
	public void setStatusOption(final ResidenceStatusOption statusOption) {
		this.statusOption = statusOption;
	}

	/**
	 *
	 *
	 * @return the status
	 */
	public ResidenceStatus getStatus() {
		return this.status;
	}

	/**
	 *
	 *
	 * @param status status
	 */
	public void setStatus(final ResidenceStatus status) {
		this.status = status;
	}

	/**
	 * Returns the residence type.
	 * 
	 * @return the residenceType
	 */
	public ResidenceType getResidenceType() {
		return this.residenceType;
	}

	/**
	 * Sets the residence type.
	 * 
	 * @param residenceType the residence type to set
	 */
	public void setResidenceType(final ResidenceType residenceType) {
		this.residenceType = residenceType;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return the startDate
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
	 * @return the endDate
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
	 * Returns the location.
	 * 
	 * @return location 
	 */
	public Location getLocation() {
		return this.location;
	}	
	
	/**
	 * Sets the location.
	 * 
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}
		
	/**
	 * Returns the value for the address.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value for the address.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Returns the state.
	 * 
	 * @return the state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Returns the city.
	 * 
	 * @return the city
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city city
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Returns the zip code.
	 * 
	 * @return the zipCode
	 */
	public ZipCode getZipCode() {
		return this.zipCode;
	}

	/**
	 * Sets the zip code.
	 * 
	 * @param zipCode zip code 
	 */
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Returns the residence comments.
	 * 
	 * @return the residenceComment
	 */
	public String getResidenceComment() {
		return this.residenceComment;
	}

	/**
	 * Sets the residence comments.
	 * 
	 * @param residenceComment residence comment
	 */
	public void setResidenceComment(final String residenceComment) {
		this.residenceComment = residenceComment;
	}

	/**
	 * Returns the confirmed value.
	 * 
	 * @return confirmed
	 */
	public Boolean getConfirmed() {
		return this.confirmed;
	}
	
	/**
	 * Sets the confirmed value.
	 * 
	 * @param confirmed confirmed
	 */
	public void setConfirmed(final Boolean confirmed) {
		this.confirmed = confirmed;
	}
			
	/**
	 *
	 *
	 * @return the createNewLocation
	 */
	public Boolean getCreateNewLocation() {
		return this.createNewLocation;
	}

	/**
	 *
	 *
	 * @param createNewLocation createNewLocation
	 */
	public void setCreateNewLocation(final Boolean createNewLocation) {
		this.createNewLocation = createNewLocation;
	}

	/**
	 *
	 *
	 * @return the locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 *
	 *
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * Returns the verified by user account.
	 * 
	 * @return the verified by user account
	 */
	public UserAccount getVerifiedByUserAccount() {
		return this.verifiedByUserAccount;
	}

	/**
	 * Sets the verified by user account.
	 * 
	 * @param verifiedByUserAccount verified by user account
	 */
	public void setVerifiedByUserAccount(
			final UserAccount verifiedByUserAccount) {
		this.verifiedByUserAccount = verifiedByUserAccount;
	}

	/**
	 * Return the verification date.
	 * 
	 * @return the verificationDate
	 */
	public Date getVerificationDate() {
		return this.verificationDate;
	}

	/**
	 * Sets the verification date.
	 * 
	 * @param verificationDate verification date
	 */
	public void setVerificationDate(final Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * Returns the verified result.
	 * 
	 * @return the verified
	 */
	public Boolean getVerified() {
		return this.verified;
	}

	/**
	 * Sets the verified result.
	 * 
	 * @param verified the verified to set
	 */
	public void setVerified(final Boolean verified) {
		this.verified = verified;
	}

	/**
	 * Returns the verification method.
	 * 
	 * @return the verificationMethod
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
	 * Returns the existing primary residence term.
	 * 
	 * @return existing primary residence term
	 */
	public ResidenceTerm getExistingPrimaryResidence() {
		return existingPrimaryResidence;
	}

	/**
	 * Sets the existing primary residence term.
	 * 
	 * @param existingPrimaryResidence existing primary residence term
	 */
	public void setExistingPrimaryResidence(
			final ResidenceTerm existingPrimaryResidence) {
		this.existingPrimaryResidence = existingPrimaryResidence;
	}

	/**
	 * Returns the existing residence operation.
	 * 
	 * @return existing residence operation
	 */
	public ExistingResidenceOperation getExistingResidenceOperation() {
		return existingResidenceOperation;
	}

	/**
	 * Sets the existing residence operation.
	 * 
	 * @param existingResidenceOperation existing residence operation
	 */
	public void setExistingResidenceOperation(
			final ExistingResidenceOperation existingResidenceOperation) {
		this.existingResidenceOperation = existingResidenceOperation;
	}

	/**
	 * Returns the existing historical end date.
	 * 
	 * @return existing historical end date
	 */
	public Date getExistingHistoricalEndDate() {
		return existingHistoricalEndDate;
	}

	/**
	 * Sets the existing historical end date.
	 * 
	 * @param existingHistoricalEndDate existing historical end date
	 */
	public void setExistingHistoricalEndDate(
			final Date existingHistoricalEndDate) {
		this.existingHistoricalEndDate = existingHistoricalEndDate;
	}
}