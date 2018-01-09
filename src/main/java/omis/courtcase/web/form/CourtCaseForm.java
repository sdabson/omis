package omis.courtcase.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.court.domain.Court;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.person.domain.Person;
import omis.region.domain.State;

/**
 * Court case form.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public class CourtCaseForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean criminallyConvictedYouth;
	
	private Boolean youthTransfer;
	
	private Boolean convictionOverturned;
	
	private Boolean dismissed;
	
	private Court court;

	private Date pronouncementDate;
	
	private Date sentenceReviewDate;
	
	private String comments;
	
	private String docketValue;
	
	private Person judge;
	
	private List<ChargeItem> charges = new ArrayList<ChargeItem>();
	
	private String prosecutingAttorneyName;
	
	private String defenseAttorneyName;
	
	private List<CourtCaseNoteItem> noteItems 
		= new ArrayList<CourtCaseNoteItem>();
	
	private String interStateNumber;
	
	private State interState;
	
	private JurisdictionAuthority jurisdictionAuthority;
	
	private OffenderDangerDesignator dangerDesignator;
	
	private Boolean allowCourt;
	
	private Boolean allowDocket;
	
	/** Instantiates a default court case form. */
	public CourtCaseForm() {
		// Default instantiation
	}

	/**
	 * Returns whether this court case should be consider the defendant to be a
	 * criminally convicted youth.
	 * 
	 * @return whether criminally convicted youth applies
	 */
	public Boolean getCriminallyConvictedYouth() {
		return this.criminallyConvictedYouth;
	}

	/**
	 * Sets whether this court case should be consider the defendant to be a
	 * criminally convicted youth.
	 * 
	 * @param criminallyConvictedYouth criminally convicted youth
	 */
	public void setCriminallyConvictedYouth(
			final Boolean criminallyConvictedYouth) {
		this.criminallyConvictedYouth = criminallyConvictedYouth;
	}

	/**
	 * Returns whether the court case is the result of a youth transfer.
	 * 
	 * @return youth transfer
	 */
	public Boolean getYouthTransfer() {
		return this.youthTransfer;
	}

	/**
	 * Sets whether the court case is the result of a youth transfer.
	 * 
	 * @param youthTransfer youth transfer
	 */
	public void setYouthTransfer(final Boolean youthTransfer) {
		this.youthTransfer = youthTransfer;
	}
	
	/**
	 * Gets whether the court case conviction was overturned.
	 * 
	 * @return whether the court case conviction was overturned
	 */
	public Boolean getConvictionOverturned() {
		return convictionOverturned;
	}

	/**
	 * Sets whether the court case conviction was overturned.
	 * 
	 * @param convictionOverturned conviction overturned
	 */
	public void setConvictionOverturned(final Boolean convictionOverturned) {
		this.convictionOverturned = convictionOverturned;
	}

	/**
	 * Returns whether the court case was dismissed.
	 * 
	 * @return whether the court case was dismissed
	 */
	public Boolean getDismissed() {
		return dismissed;
	}

	/**
	 * Sets whether the court case was dismissed.
	 * 
	 * @param dismissed dismissed
	 */
	public void setDismissed(final Boolean dismissed) {
		this.dismissed = dismissed;
	}

	/**
	 * Returns the court.
	 * 
	 * @return court
	 */
	public Court getCourt() {
		return this.court;
	}

	/**
	 * Sets the court.
	 * 
	 * @param court court
	 */
	public void setCourt(final Court court) {
		this.court = court;
	}

	/**
	 * Returns the pronouncement date.
	 * 
	 * @return pronouncement date
	 */
	public Date getPronouncementDate() {
		return this.pronouncementDate;
	}

	/**
	 * Sets the pronouncement date.
	 * 
	 * @param pronouncementDate pronouncement date
	 */
	public void setPronouncementDate(final Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}

	/**
	 * Returns the sentence review date.
	 * 
	 * @return sentence review date
	 */
	public Date getSentenceReviewDate() {
		return sentenceReviewDate;
	}

	/**
	 * Sets the sentence review date.
	 * 
	 * @param sentenceReviewDate sentence review date
	 */
	public void setSentenceReviewDate(final Date sentenceReviewDate) {
		this.sentenceReviewDate = sentenceReviewDate;
	}

	/**
	 * Returns the comments.
	 * 
	 * @return comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments comments
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * Sets the docket.
	 * 
	 * @param docketValue docket
	 */
	public void setDocketValue(final String docketValue) {
		this.docketValue = docketValue;
	}

	/**
	 * Returns the judge.
	 * 
	 * @return judge
	 */
	public Person getJudge() {
		return this.judge;
	}

	/**
	 * Sets the judge.
	 * 
	 * @param judge judge
	 */
	public void setJudge(final Person judge) {
		this.judge = judge;
	}

	/** Returns charges.
	 * @return list of charges. */
	public List<ChargeItem> getCharges() {
		return this.charges;
	}
	
	/** Sets charges.
	 * @param charges - charges. */
	public void setCharges(final List<ChargeItem> charges) {
		this.charges = charges;
	}

	/**
	 * Returns the defense attorney name.
	 * 
	 * @return defense attorney name
	 */
	public String getDefenseAttorneyName() {
		return this.defenseAttorneyName;
	}
	
	/**
	 * Sets the defense attorney name.
	 * 
	 * @param defenseAttorneyName defense attorney name
	 */
	public void setDefenseAttorneyName(final String defenseAttorneyName) {
		this.defenseAttorneyName = defenseAttorneyName;
	}

	/**
	 * Returns the prosecuting attorney name.
	 * 
	 * @return prosecuting attorney name
	 */
	public String getProsecutingAttorneyName() {
		return this.prosecutingAttorneyName;
	}

	/**
	 * Sets the prosecuting attorney name.
	 * 
	 * @param prosecutingAttorneyName prosecuting attorney name
	 */
	public void setProsecutingAttorneyName(
			final String prosecutingAttorneyName) {
		this.prosecutingAttorneyName = prosecutingAttorneyName;
	}

	/**
	 * Returns the court case notes.
	 * 
	 * @return court case notes
	 */
	public List<CourtCaseNoteItem> getNoteItems() {
		return noteItems;
	}

	/**
	 * Sets the court case notes.
	 * 
	 * @param noteItems court case note items
	 */
	public void setNoteItems(final List<CourtCaseNoteItem> noteItems) {
		this.noteItems = noteItems;
	}
	
	/**
	 * Returns the inter state number.
	 * 
	 * @return inter state number
	 */
	public String getInterStateNumber() {
		return this.interStateNumber;
	}
	
	/**
	 * Sets the inter state number.
	 * 
	 * @param interStateNumber inter state number
	 */
	public void setInterStateNumber(final String interStateNumber) {
		this.interStateNumber = interStateNumber;
	}
	
	/**
	 * Returns the inter state.
	 * 
	 * @return inter state
	 */
	public State getInterState() {
		return this.interState;
	}
	
	/**
	 * Sets the inter state.
	 * 
	 * @param interState inter state
	 */
	public void setInterState(final State interState) {
		this.interState = interState;
	}
	
	/**
	 * Returns the jurisdiction authority.
	 * 
	 * @return jurisdiction authority
	 */
	public JurisdictionAuthority getJurisdictionAuthority() {
		return this.jurisdictionAuthority;
	}
	
	/**
	 * Sets the jurisdiction authority.
	 * 
	 * @param jurisdictionAuthority jurisdiction authority
	 */
	public void setJurisdictionAuthority(
			final JurisdictionAuthority jurisdictionAuthority) {
		this.jurisdictionAuthority = jurisdictionAuthority;
	}
	
	/**
	 * Returns the offender danger designator.
	 * 
	 * @return offender danger designator
	 */
	public OffenderDangerDesignator getDangerDesignator() {
		return this.dangerDesignator;
	}
	
	/**
	 * Sets the offender danger designator.
	 * 
	 * @param dangerDesignator offender danger designator
	 */
	public void setDangerDesignator(
			final OffenderDangerDesignator dangerDesignator) {
		this.dangerDesignator = dangerDesignator;
	}

	/**
	 * Returns whether court is allowed to be edited.
	 * 
	 * @return whether court is allowed to be edited
	 */
	public Boolean getAllowCourt() {
		return allowCourt;
	}

	/**
	 * Sets whether court is allowed to be edited.
	 * 
	 * @param allowCourt allow court
	 */
	public void setAllowCourt(final Boolean allowCourt) {
		this.allowCourt = allowCourt;
	}

	/**
	 * Returns whether docket is allowed to be edited.
	 * 
	 * @return whether docket is allowed to be edited
	 */
	public Boolean getAllowDocket() {
		return allowDocket;
	}

	/**
	 * Sets whether docket is allowed to be edited.
	 * 
	 * @param allowDocket allow docket
	 */
	public void setAllowDocket(final Boolean allowDocket) {
		this.allowDocket = allowDocket;
	}
}