package omis.prisonterm.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.user.domain.UserAccount;

/**
 * Used to capture prison term information.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Dec 20, 2018)
 * @since OMIS 3.0
 */

public class PrisonTermForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date actionDate; 
	
	private Integer preSentenceCredits; 
	
	private Date sentenceDate; 
	
	private Integer sentenceTermYears; 
	
	private Integer sentenceTermDays; 
	
	private Date paroleEligibilityDate;
	
	private Date projectedDischargeDate; 
	
	private Date maximumDischargeDate; 
	
	private PrisonTermStatus status; 
	
	private Boolean sentenceToFollow; 
	
	private String comments;
	
	private PrisonTerm prisonTerm;
	
	private UserAccount verificationUser;
	
	private String verificationUserInput;
	
	private Date verificationDate;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems =
			new ArrayList<DocumentTagItem>();
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	private Boolean removeSentenceCalculation;
	
	private byte[] replaceData;
	
	/**
	 * Instantiates a default prison term form. */
	public PrisonTermForm() {
		// Default instantiation
	}

	/**
	 * @return the actionDate
	 */
	public Date getActionDate() {
		return actionDate;
	}

	/**
	 * @param actionDate the actionDate to set
	 */
	public void setActionDate(final Date actionDate) {
		this.actionDate = actionDate;
	}

	/**
	 * @return the preSentenceCredits
	 */
	public Integer getPreSentenceCredits() {
		return preSentenceCredits;
	}

	/**
	 * @param preSentenceCredits the preSentenceCredits to set
	 */
	public void setPreSentenceCredits(final Integer preSentenceCredits) {
		this.preSentenceCredits = preSentenceCredits;
	}

	/**
	 * @return the sentenceDate
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * @param sentenceDate the sentenceDate to set
	 */
	public void setSentenceDate(final Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	/**
	 * @return the sentenceTermYears
	 */
	public Integer getSentenceTermYears() {
		return sentenceTermYears;
	}

	/**
	 * @param sentenceTermYears the sentenceTermYears to set
	 */
	public void setSentenceTermYears(final Integer sentenceTermYears) {
		this.sentenceTermYears = sentenceTermYears;
	}

	/**
	 * @return the sentenceTermDays
	 */
	public Integer getSentenceTermDays() {
		return sentenceTermDays;
	}

	/**
	 * @param sentenceTermDays the sentenceTermDays to set
	 */
	public void setSentenceTermDays(final Integer sentenceTermDays) {
		this.sentenceTermDays = sentenceTermDays;
	}

	/**
	 * @return the paroleEligibilityDate
	 */
	public Date getParoleEligibilityDate() {
		return paroleEligibilityDate;
	}

	/**
	 * @param paroleEligibilityDate the paroleEligibilityDate to set
	 */
	public void setParoleEligibilityDate(final Date paroleEligibilityDate) {
		this.paroleEligibilityDate = paroleEligibilityDate;
	}

	/**
	 * @return the projectedDischargeDate
	 */
	public Date getProjectedDischargeDate() {
		return projectedDischargeDate;
	}

	/**
	 * @param projectedDischargeDate the projectedDischargeDate to set
	 */
	public void setProjectedDischargeDate(final Date projectedDischargeDate) {
		this.projectedDischargeDate = projectedDischargeDate;
	}

	/**
	 * @return the maximumDischargeDate
	 */
	public Date getMaximumDischargeDate() {
		return maximumDischargeDate;
	}

	/**
	 * @param maximumDischargeDate the maximumDischargeDate to set
	 */
	public void setMaximumDischargeDate(final Date maximumDischargeDate) {
		this.maximumDischargeDate = maximumDischargeDate;
	}

	/**
	 * @return the status
	 */
	public PrisonTermStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final PrisonTermStatus status) {
		this.status = status;
	}

	/**
	 * @return the sentenceToFollow
	 */
	public Boolean getSentenceToFollow() {
		return sentenceToFollow;
	}

	/**
	 * @param sentenceToFollow the sentenceToFollow to set
	 */
	public void setSentenceToFollow(final Boolean sentenceToFollow) {
		this.sentenceToFollow = sentenceToFollow;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * @return the prisonTerm
	 */
	public PrisonTerm getPrisonTerm() {
		return prisonTerm;
	}

	/**
	 * @param prisonTerm the prisonTerm to set
	 */
	public void setPrisonTerm(final PrisonTerm prisonTerm) {
		this.prisonTerm = prisonTerm;
	}

	/**
	 * @return the verificationUser
	 */
	public UserAccount getVerificationUser() {
		return verificationUser;
	}

	/**
	 * @param verificationUser the verificationUser to set
	 */
	public void setVerificationUser(final UserAccount verificationUser) {
		this.verificationUser = verificationUser;
	}

	/**
	 * @return the verificationDate
	 */
	public Date getVerificationDate() {
		return verificationDate;
	}

	/**
	 * @param verificationDate the verificationDate to set
	 */
	public void setVerificationDate(final Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * @return the verificationUserInput
	 */
	public String getVerificationUserInput() {
		return verificationUserInput;
	}

	/**
	 * @param verificationUserInput the verificationUserInput to set
	 */
	public void setVerificationUserInput(
			final String verificationUserInput) {
		this.verificationUserInput = verificationUserInput;
	}

	/**
	 * Returns the document.
	 *
	 * @return document
	 */
	public Document getDocument() {
		return this.document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document - document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the title.
	 *
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title - title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the documentTagItems.
	 *
	 * @return documentTagItems
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems;
	}

	/**
	 * Sets the documentTagItems.
	 *
	 * @param documentTagItems - documentTagItems
	 */
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date - date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the fileExtension.
	 *
	 * @return fileExtension
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Sets the fileExtension.
	 *
	 * @param fileExtension - fileExtension
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	/**
	 * Returns the removeSentenceCalculation.
	 *
	 * @return removeSentenceCalculation
	 */
	public Boolean getRemoveSentenceCalculation() {
		return this.removeSentenceCalculation;
	}

	/**
	 * Sets the removeSentenceCalculation.
	 *
	 * @param removeSentenceCalculation - removeSentenceCalculation
	 */
	public void setRemoveSentenceCalculation(
			final Boolean removeSentenceCalculation) {
		this.removeSentenceCalculation = removeSentenceCalculation;
	}

	/**
	 * Returns the data.
	 *
	 * @return data
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data - data
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * Returns the replaceData.
	 *
	 * @return replaceData
	 */
	public byte[] getReplaceData() {
		return this.replaceData;
	}

	/**
	 * Sets the replaceData.
	 *
	 * @param replaceData - replaceData
	 */
	public void setReplaceData(final byte[] replaceData) {
		this.replaceData = replaceData;
	}
	
	
}
