package omis.stg.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.user.domain.UserAccount;

/**
 * Form for security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date startDate;
	
	private Date endDate;
	
	private SecurityThreatGroup group;
	
	private SecurityThreatGroupActivityLevel activityLevel;
	
	private SecurityThreatGroupChapter chapter;
	
	private State state;
	
	private City city;
	
	private String moniker;
	
	private SecurityThreatGroupRank rank;
	
	private String comment;
	
	private UserAccount verificationUserAccount;
	
	private String verificationUserAccountLabel;
	
	private Date verificationDate;
	
	private Boolean verificationResult;
	
	private VerificationMethod verificationMethod;
	
	private List<SecurityThreatGroupAffiliationNoteItem> affiliationNoteItems;
	
	private Boolean createNewChapter;
	
	private String chapterName;

	private Boolean createNewRank;
	
	private String rankName;
	
	/** Instantiates a form for security threat group affiliations. */
	public SecurityThreatGroupAffiliationForm() {
		// Default instantiation
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
	 * Returns the group.
	 * 
	 * @return group
	 */
	public SecurityThreatGroup getGroup() {
		return this.group;
	}

	/**
	 * Sets the group.
	 * 
	 * @param group group
	 */
	public void setGroup(final SecurityThreatGroup group) {
		this.group = group;
	}

	/**
	 * Returns the activity level.
	 * 
	 * @return activity level
	 */
	public SecurityThreatGroupActivityLevel getActivityLevel() {
		return this.activityLevel;
	}

	/**
	 * Sets the activity level.
	 * 
	 * @param activityLevel activity level
	 */
	public void setActivityLevel(
			final SecurityThreatGroupActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
	}

	/**
	 * Returns the chapter.
	 * 
	 * @return chapter
	 */
	public SecurityThreatGroupChapter getChapter() {
		return this.chapter;
	}

	/**
	 * Sets the chapter.
	 * 
	 * @param chapter chapter
	 */
	public void setChapter(final SecurityThreatGroupChapter chapter) {
		this.chapter = chapter;
	}

	/**
	 * Returns the State.
	 * 
	 * @return State
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets the State.
	 * 
	 * @param state State
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Returns the city.
	 * 
	 * @return city
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
	 * Returns the moniker.
	 * 
	 * @return moniker
	 */
	public String getMoniker() {
		return this.moniker;
	}

	/**
	 * Sets the moniker.
	 * 
	 * @param moniker moniker
	 */
	public void setMoniker(final String moniker) {
		this.moniker = moniker;
	}

	/**
	 * Returns the rank.
	 * 
	 * @return rank
	 */
	public SecurityThreatGroupRank getRank() {
		return this.rank;
	}

	/**
	 * Sets the rank.
	 * 
	 * @param rank rank
	 */
	public void setRank(final SecurityThreatGroupRank rank) {
		this.rank = rank;
	}

	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return this.comment;
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
	 * Returns verification user account.
	 * 
	 * @return verification user account
	 */
	public UserAccount getVerificationUserAccount() {
		return this.verificationUserAccount;
	}

	/**
	 * Sets verification user account.
	 * 
	 * @param verificationUserAccount verification user account
	 */
	public void setVerificationUserAccount(
			final UserAccount verificationUserAccount) {
		this.verificationUserAccount = verificationUserAccount;
	}

	/**
	 * Sets the label for the verification user account.
	 * 
	 * @return label for verification user account
	 */
	public String getVerificationUserAccountLabel() {
		return this.verificationUserAccountLabel;
	}

	/**
	 * Returns the label for the verification user account.
	 * 
	 * @param verificationUserAccountLabel label for verification user account
	 */
	public void setVerificationUserAccountLabel(
			final String verificationUserAccountLabel) {
		this.verificationUserAccountLabel = verificationUserAccountLabel;
	}

	/**
	 * Returns verification date.
	 * 
	 * @return verification date
	 */
	public Date getVerificationDate() {
		return this.verificationDate;
	}

	/**
	 * Sets verification date.
	 * 
	 * @param verificationDate verification date
	 */
	public void setVerificationDate(final Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * Returns verification result.
	 * 
	 * @return verification result
	 */
	public Boolean getVerificationResult() {
		return this.verificationResult;
	}

	/**
	 * Sets verification result.
	 * 
	 * @param verificationResult verification result
	 */
	public void setVerificationResult(final Boolean verificationResult) {
		this.verificationResult = verificationResult;
	}

	/**
	 * Returns verification method.
	 * 
	 * @return verification method
	 */
	public VerificationMethod getVerificationMethod() {
		return this.verificationMethod;
	}

	/**
	 * Sets verification method.
	 * 
	 * @param verificationMethod verification method
	 */
	public void setVerificationMethod(
			final VerificationMethod verificationMethod) {
		this.verificationMethod = verificationMethod;
	}
	
	/**
	 * Returns the affiliation note items.
	 * 
	 * @return list of security threat group affiliation note items
	 */
	public List<SecurityThreatGroupAffiliationNoteItem> 
			getAffiliationNoteItems() {
		return this.affiliationNoteItems;
	}

	/**
	 * Sets the affiliation note items.
	 * 
	 * @param affiliationNoteItems security threat group affiliation note items
	 */
	public void setAffiliationNoteItems(
			final List<SecurityThreatGroupAffiliationNoteItem> 
				affiliationNoteItems) {
		this.affiliationNoteItems = affiliationNoteItems;
	}

	/**
	 * Returns whether or not to create a new chapter
	 * @return boolean
	 */
	public Boolean getCreateNewChapter() {
		return createNewChapter;
	}

	/**
	 * Sets whether or not to create a new chapter
	 * @param createNewChapter create new chapter
	 */
	public void setCreateNewChapter(Boolean createNewChapter) {
		this.createNewChapter = createNewChapter;
	}

	/**
	 * Returns the new chapter name
	 * @return new chapter name
	 */
	public String getChapterName() {
		return chapterName;
	}

	/**
	 * Sets the new chapter name
	 * @param chapterName chapter name
	 */
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	/**
	 * Returns whether or not to create a new rank
	 * @return boolean
	 */
	public Boolean getCreateNewRank() {
		return createNewRank;
	}

	/**
	 * Sets whether or not to create a new rank
	 * @param createNewRank create new rank
	 */
	public void setCreateNewRank(Boolean createNewRank) {
		this.createNewRank = createNewRank;
	}

	/**
	 * Returns the new rank name
	 * @return rank name
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * Sets the new rank name
	 * @param rankName rank name
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
}