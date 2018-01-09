package omis.religion.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.religion.domain.Religion;
import omis.user.domain.UserAccount;

/**
 * Form for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private Religion religion;
	
	private Date startDate;
	
	private Date endDate;
	
	private String comment;
	
	private UserAccount verificationUserAccount;
	
	private String verificationUserAccountLabel;
	
	private Date verificationDate;
	
	private VerificationMethod verificationMethod;
	
	private Boolean verificationResult;
	
	private List<ReligiousAccommodationAuthorizationItem>
		accommodationAuthorizationItems
			= new ArrayList<ReligiousAccommodationAuthorizationItem>();
	
	/** Instantiates a form for religious preferences. */
	public ReligiousPreferenceForm() {
		// Default instantiation
	}

	/**
	 * Returns the religion.
	 * 
	 * @return religion
	 */
	public Religion getReligion() {
		return this.religion;
	}

	/**
	 * Sets the religion.
	 * 
	 * @param religion religion
	 */
	public void setReligion(final Religion religion) {
		this.religion = religion;
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
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}
	
	/**
	 * Returns the religious accommodation authorization items.
	 * 
	 * @return religious accommodation authorization items
	 */
	public List<ReligiousAccommodationAuthorizationItem>
			getAccommodationAuthorizationItems() {
		return this.accommodationAuthorizationItems;
	}

	/**
	 * Sets the religious accommodation authorization items.
	 * 
	 * @param accommodationAuthorizationItems religious accommodation
	 * authorization items
	 */
	public void setAccommodationAuthorizationItems(
			final List<ReligiousAccommodationAuthorizationItem>
				accommodationAuthorizationItems) {
		this.accommodationAuthorizationItems = accommodationAuthorizationItems;
	}

	/**
	 * Returns the verification user account.
	 * 
	 * @return verification user account
	 */
	public UserAccount getVerificationUserAccount() {
		return this.verificationUserAccount;
	}

	/**
	 * Sets the verification user account.
	 * 
	 * @param verificationUserAccount verification user account
	 */
	public void setVerificationUserAccount(
			final UserAccount verificationUserAccount) {
		this.verificationUserAccount = verificationUserAccount;
	}

	/**
	 * Returns the label for the verification user account.
	 * 
	 * @return label for verification user account
	 */
	public String getVerificationUserAccountLabel() {
		return this.verificationUserAccountLabel;
	}

	/**
	 * Sets the label for the verification user account.
	 * 
	 * @param verificationUserAccountLabel label for verification user account
	 */
	public void setVerificationUserAccountLabel(
			final String verificationUserAccountLabel) {
		this.verificationUserAccountLabel = verificationUserAccountLabel;
	}

	/**
	 * Returns the verification date.
	 * 
	 * @return verification date
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
	 * Returns the verification method.
	 * 
	 * @return verification methods.
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
	 * Returns the verification result.
	 * 
	 * @return verification result
	 */
	public Boolean getVerificationResult() {
		return this.verificationResult;
	}

	/**
	 * Sets the verification result.
	 * 
	 * @param verificationResult verification result
	 */
	public void setVerificationResult(final Boolean verificationResult) {
		this.verificationResult = verificationResult;
	}
}