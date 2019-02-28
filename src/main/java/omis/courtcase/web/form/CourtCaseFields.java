package omis.courtcase.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.person.domain.Person;
import omis.region.domain.State;

/**
 * Fields for court case.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String interStateNumber;
	
	private State interState;
	
	private Date pronouncementDate;
	
	private Date sentenceReviewDate;
	
	private JurisdictionAuthority jurisdicationAuthority;
	
	private Person judge;
	
	private String judgeQuery;
	
	private String prosecutingAttorneyName;
	
	private String defenseAttorneyName;
	
	private OffenderDangerDesignator dangerDesignator;
	
	private Boolean criminallyConvictedYouth;
	
	private Boolean youthTransfer;
	
	private Boolean convictionOverturned;
	
	private String comments;
	
	/** Instantiates fields for court case. */
	public CourtCaseFields() {
		// Default instantiation
	}
	
	/** Instantiates fields from court case. */
	public CourtCaseFields(final CourtCase courtCase) {
		this.interStateNumber = courtCase.getInterStateNumber();
		this.interState = courtCase.getInterState();
		this.pronouncementDate = courtCase.getPronouncementDate();
		this.sentenceReviewDate = courtCase.getSentenceReviewDate();
		this.jurisdicationAuthority = courtCase.getJurisdictionAuthority();
		if (courtCase.getPersonnel() != null) {
			this.judge = courtCase.getPersonnel().getJudge();
			this.prosecutingAttorneyName
				= courtCase.getPersonnel().getProsecutingAttorneyName();
			this.defenseAttorneyName
				= courtCase.getPersonnel().getDefenseAttorneyName();
		}
		this.dangerDesignator = courtCase.getDangerDesignator();
		if (courtCase.getFlags() != null) {
			this.criminallyConvictedYouth
				= courtCase.getFlags().getCriminallyConvictedYouth();
			this.youthTransfer
				= courtCase.getFlags().getYouthTransfer();
			this.convictionOverturned
				= courtCase.getFlags().getConvictionOverturned();
		}
		this.comments = courtCase.getComments();
	}
		
	/**
	 * Sets inter State number.
	 * 
	 * @param interStateNumber inter State number
	 */
	public void setInterStateNumber(final String interStateNumber) {
		this.interStateNumber = interStateNumber;
	}
	
	/**
	 * Returns inter State number.
	 * 
	 * @return inter State number
	 */
	public String getInterStateNumber() {
		return this.interStateNumber;
	}
	
	/**
	 * Sets inter State.
	 * 
	 * @param interState inter State
	 */
	public void setInterState(final State interState) {
		this.interState = interState;
	}
	
	/**
	 * Returns inter State.
	 * 
	 * @return inter State
	 */
	public State getInterState() {
		return this.interState;
	}
	
	/**
	 * Sets pronouncement date.
	 * 
	 * @param pronouncementDate pronouncement date
	 */
	public void setPronouncementDate(final Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}
	
	/**
	 * Returns pronouncement date.
	 * 
	 * @return pronouncement date
	 */
	public Date getPronouncementDate() {
		return this.pronouncementDate;
	}
	
	/**
	 * Sets sentence review date.
	 * 
	 * @param sentenceReviewDate sentence review date
	 */
	public void setSentenceReviewDate(final Date sentenceReviewDate) {
		this.sentenceReviewDate = sentenceReviewDate;
	}
	
	/**
	 * Returns sentence review date.
	 * 
	 * @return sentence review date
	 */
	public Date getSentenceReviewDate() {
		return this.sentenceReviewDate;
	}
	
	/**
	 * Sets jurisdiction authority.
	 * 
	 * @param jurisdictionAuthority jurisdiction authority
	 */
	public void setJurisdictionAuthority(
			final JurisdictionAuthority jurisdictionAuthority) {
		this.jurisdicationAuthority = jurisdictionAuthority;
	}
	
	/**
	 * Returns jurisdiction authority.
	 * 
	 * @return jurisdiction authority
	 */
	public JurisdictionAuthority getJurisdictionAuthority() {
		return this.jurisdicationAuthority;
	}
	
	/**
	 * Sets judge.
	 * 
	 * @param judge judge
	 */
	public void setJudge(final Person judge) {
		this.judge = judge;
	}
	
	/**
	 * Returns judge.
	 * 
	 * @return judge
	 */
	public Person getJudge() {
		return this.judge;
	}
	
	/**
	 * Sets judge query.
	 * 
	 * @param judgeQuery judge query
	 */
	public void setJudgeQuery(final String judgeQuery) {
		this.judgeQuery = judgeQuery;
	}
	
	/**
	 * Returns judge query.
	 * 
	 * @return judge query
	 */
	public String getJudgeQuery() {
		return this.judgeQuery;
	}
	
	/**
	 * Sets prosecuting attorney name.
	 * 
	 * @param prosecutingAttorney prosecuting attorney name
	 */
	public void setProsecutingAttorneyName(
			final String prosecutingAttorneyName) {
		this.prosecutingAttorneyName = prosecutingAttorneyName;
	}
	
	/**
	 * Returns prosecuting attorney name.
	 * 
	 * @return prosecuting attorney name
	 */
	public String getProsecutingAttorneyName() {
		return this.prosecutingAttorneyName;
	}
	
	/**
	 * Sets defense attorney name.
	 * 
	 * @param defenseAttorney defense attorney name
	 */
	public void setDefenseAttorneyName(final String defenseAttorneyName) {
		this.defenseAttorneyName = defenseAttorneyName;
	}
	
	/**
	 * Returns defense attorney name.
	 * 
	 * @return defense attorney name
	 */
	public String getDefenseAttorneyName() {
		return this.defenseAttorneyName;
	}
	
	/**
	 * Sets danger designator.
	 * 
	 * @param dangerDesignator danger designator
	 */
	public void setDangerDesignator(
			final OffenderDangerDesignator dangerDesignator) {
		this.dangerDesignator = dangerDesignator;
	}
	
	/**
	 * Returns danger designator.
	 * 
	 * @return danger designator
	 */
	public OffenderDangerDesignator getDangerDesignator() {
		return this.dangerDesignator;
	}
	
	/**
	 * Sets whether criminally convicted youth.
	 * 
	 * @param criminallyConvictedYouth whether criminally convicted youth
	 */
	public void setCriminallyConvictedYouth(
			final Boolean criminallyConvictedYouth) {
		this.criminallyConvictedYouth = criminallyConvictedYouth;
	}
	
	/**
	 * Returns whether criminally convicted youth.
	 * 
	 * @return whether criminally convicted youth
	 */
	public Boolean getCriminallyConvictedYouth() {
		return this.criminallyConvictedYouth;
	}
	
	/**
	 * Set whether a youth transfer.
	 * 
	 * @param youthTransfer whether a youth transfer
	 */
	public void setYouthTransfer(final Boolean youthTransfer) {
		this.youthTransfer = youthTransfer;
	}
	
	/**
	 * Returns whether a youth transfer.
	 * 
	 * @return whether a youth transfer
	 */
	public Boolean getYouthTransfer() {
		return this.youthTransfer;
	}
	
	/**
	 * Sets whether conviction is overturned.
	 * 
	 * @param convictionOverturned whether conviction is overturned
	 */
	public void setConvictionOverturned(final Boolean convictionOverturned) {
		this.convictionOverturned = convictionOverturned;
	}
	
	/**
	 * Returns whether conviction is overturned.
	 * 
	 * @return whether conviction is overturned
	 */
	public Boolean getConvictionOverturned() {
		return this.convictionOverturned;
	}
	
	/**
	 * Sets comments.
	 * 
	 * @param comments comments
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	/**
	 * Returns comments.
	 * 
	 * @return comments
	 */
	public String getComments() {
		return this.comments;
	}
}