package omis.courtcase.report;

import java.io.Serializable;
import java.util.Date;

import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.region.domain.State;

/**
 * Court case summary.
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Apr 7, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseSummary implements Serializable {
	
	private static final long serialVersionUID = 1;
	
	private final Long courtCaseId;
	
	private final Long defendantId;
	
	private final String defendantFirstName;
	
	private final String defendantLastName;
	
	private final String defendantMiddleName;
	
	private final String defendantSuffix;
	
	private final Boolean defendantOffender;
	
	private final Integer defendantOffenderNumber;
	
	private final State interState;
	
	private final String interStateNumber;
	
	private final String courtName;
	
	private final String docket;

	private final Date pronouncementDate;
	
	private final Date sentenceReviewDate;
	
	private final Boolean judge;
	
	private final Long judgeId;
	
	private final String judgeFirstName;
	
	private final String judgeLastName;
	
	private final String judgeMiddleName;
	
	private final String judgeSuffix;
	
	private final String defenseAttorneyName;
	
	private final String prosecutingAttorneyName;
	
	private final JurisdictionAuthority jurisdictionAuthority;
	
	private final OffenderDangerDesignator dangerDesignator;
	
	private final Boolean criminallyConvictedYouth;
		
	private final Boolean youthTransfer;
	
	private final Boolean dismissed;
	
	private final Boolean convictionOverturned;
	
	private final String comments;
	
	private final Long charges;
	
	private final Long convictions;

	/**
	 * Instantiates a court case summary with all properties.
	 * 
	 * @param courtCaseId ID of the court case
	 * @param defendantId ID of defendant
	 * @param defendantFirstName first name of defendant
	 * @param defendantLastName last name of defendant
	 * @param defendantMiddleName middle name of defendant
	 * @param defendantSuffix suffix of defendant
	 * @param defendantOffender whether defendant is an existing offender
	 * @param defendantOffenderNumber offender number of defendant
	 * @param interState inter state of defendant
	 * @param interStateNumber inter state number of defendant
	 * @param courtName name of the court
	 * @param docket docket number
	 * @param pronouncementDate pronouncement date
	 * @param sentenceReviewDate sentence review date
	 * @param judgeId ID of judge
	 * @param judgeFirstName first name of judge
	 * @param judgeLastName last name of judge
	 * @param judgeMiddleName middle name of judge
	 * @param judgeSuffix suffix of judge
	 * @param defenseAttorneyName name of defense attorney
	 * @param prosecutingAttorneyName name of prosecuting attorney
	 * @param jurisdictionAuthority jurisdiction authority
	 * @param dangerDesignator danger designator
	 * @param criminallyConvictedYouth whether criminally convicted youth
	 * @param youthTransfer whether youth transfer
	 * @param dismissed whether the court case was dismissed
	 * @param convictionOverturned whether the conviction was overturned
	 * @param comments comments
	 */
	public CourtCaseSummary(
			final Long courtCaseId, 
			final Long defendantId,
			final String defendantFirstName,
			final String defendantLastName,
			final String defendantMiddleName,
			final String defendantSuffix,
			final Boolean defendantOffender,
			final Integer defendantOffenderNumber,
			final State interState,
			final String interStateNumber,
			final String courtName,
			final String docket,
			final Date pronouncementDate,
			final Date sentenceReviewDate,
			final Boolean judge,
			final Long judgeId,
			final String judgeFirstName,
			final String judgeLastName,
			final String judgeMiddleName,
			final String judgeSuffix,
			final String defenseAttorneyName, 
			final String prosecutingAttorneyName,
			final JurisdictionAuthority jurisdictionAuthority,
			final OffenderDangerDesignator dangerDesignator,
			final Boolean criminallyConvictedYouth, 
			final Boolean youthTransfer, 
			final Boolean dismissed,
			final Boolean convictionOverturned,
			final String comments,
			final Long charges,
			final Long convictions) {
		this.courtCaseId = courtCaseId;
		this.defendantId = defendantId;
		this.defendantFirstName = defendantFirstName;
		this.defendantLastName = defendantLastName;
		this.defendantMiddleName = defendantMiddleName;
		this.defendantSuffix = defendantSuffix;
		this.defendantOffender = defendantOffender;
		this.defendantOffenderNumber = defendantOffenderNumber;
		this.interState = interState;
		this.interStateNumber = interStateNumber;
		this.courtName = courtName;
		this.docket = docket;
		this.pronouncementDate = pronouncementDate;
		this.sentenceReviewDate = sentenceReviewDate;
		this.judge = judge;
		this.judgeId = judgeId;
		this.judgeFirstName = judgeFirstName;
		this.judgeLastName = judgeLastName;
		this.judgeMiddleName = judgeMiddleName;
		this.judgeSuffix = judgeSuffix;
		this.defenseAttorneyName = defenseAttorneyName;
		this.prosecutingAttorneyName = prosecutingAttorneyName;
		this.jurisdictionAuthority = jurisdictionAuthority;
		this.dangerDesignator = dangerDesignator;
		this.criminallyConvictedYouth = criminallyConvictedYouth;
		this.youthTransfer = youthTransfer;
		this.dismissed = dismissed;
		this.convictionOverturned = convictionOverturned;
		this.comments = comments;
		this.charges = charges;
		this.convictions = convictions;
	}

	/**
	 * Returns the id of the court case.
	 * 
	 * @return court case ID
	 */
	public Long getCourtCaseId() {
		return this.courtCaseId;
	}
	
	/**
	 * Returns the id of the defendant.
	 * 
	 * @return defendant ID
	 */
	public Long getDefendantId() {
		return defendantId;
	}

	/**
	 * Returns the first name of the defendant.
	 * 
	 * @return defendant first name
	 */
	public String getDefendantFirstName() {
		return this.defendantFirstName;
	}

	/**
	 * Returns the last name of the defendant.
	 * 
	 * @return defendant last name
	 */
	public String getDefendantLastName() {
		return this.defendantLastName;
	}

	
	/**
	 * Returns the middle name of the defendant.
	 * 
	 * @return defendant middle name
	 */
	public String getDefendantMiddleName() {
		return defendantMiddleName;
	}

	/**
	 * Returns the suffix of the defendant.
	 * 
	 * @return defendant suffix
	 */
	public String getDefendantSuffix() {
		return defendantSuffix;
	}

	/**
	 * Returns whether the defendant is an offender.
	 * 
	 * @return defendant is an offender
	 */
	public Boolean getDefendantOffender() {
		return defendantOffender;
	}

	/**
	 * Returns the offender number of the defendant.
	 * 
	 * @return defendant offender number
	 */
	public Integer getDefendantOffenderNumber() {
		return defendantOffenderNumber;
	}

	/**
	 * Returns the inter state for the defendant.
	 * 
	 * @return inter state
	 */
	public State getInterState() {
		return interState;
	}

	/**
	 * Returns the inter state number for the defendant.
	 * 
	 * @return inter state number
	 */
	public String getInterStateNumber() {
		return interStateNumber;
	}

	/**
	 * Returns the name of the court.
	 * 
	 * @return court name.
	 */
	public String getCourtName() {
		return this.courtName;
	}
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	public String getDocket() {
		return this.docket;
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
	 * Returns the sentence review date.
	 * 
	 * @return sentence review date
	 */
	public Date getSentenceReviewDate() {
		return this.sentenceReviewDate;
	}

	/**
	 * Returns whether the judge exists.
	 * 
	 * @return judge
	 */
	public Boolean getJudge() {
		return this.judge;
	}

	/**
	 * Returns the id of the judge.
	 * 
	 * @return judge ID
	 */
	public Long getJudgeId() {
		return this.judgeId;
	}

	/**
	 * Returns the first name of the judge.
	 * 
	 * @return judge first name
	 */
	public String getJudgeFirstName() {
		return this.judgeFirstName;
	}

	/**
	 * Returns the last name of the judge.
	 * 
	 * @return judge last name
	 */
	public String getJudgeLastName() {
		return this.judgeLastName;
	}

	/**
	 * Returns the middle name of the judge.
	 * 
	 * @return judge middle name
	 */
	public String getJudgeMiddleName() {
		return this.judgeMiddleName;
	}

	/**
	 * Returns the suffix of the judge.
	 * 
	 * @return judge suffix
	 */
	public String getJudgeSuffix() {
		return this.judgeSuffix;
	}

	/**
	 * Returns the name of the defense attorney.
	 * 
	 * @return defense attorney name
	 */
	public String getDefenseAttorneyName() {
		return this.defenseAttorneyName;
	}

	/**
	 * Returns the name of the prosecuting attorney.
	 * 
	 * @return prosecuting attorney name
	 */
	public String getProsecutingAttorneyName() {
		return this.prosecutingAttorneyName;
	}

	/**
	 * Returns the jurisdiction authority of the defendant.
	 * 
	 * @return jurisdiction authority
	 */
	public JurisdictionAuthority getJurisdictionAuthority() {
		return jurisdictionAuthority;
	}

	/**
	 * Returns the danger designator of the defendant.
	 * 
	 * @return danger designator
	 */
	public OffenderDangerDesignator getDangerDesignator() {
		return dangerDesignator;
	}

	/**
	 * Returns whether court case specifies criminally convicted youth.
	 * 
	 * @return whether criminally convicted youth
	 */
	public Boolean getCriminallyConvictedYouth() {
		return this.criminallyConvictedYouth;
	}

	/**
	 * Returns whether court case is the result of a youth transfer.
	 * 
	 * @return whether youth transfer
	 */
	public Boolean getYouthTransfer() {
		return this.youthTransfer;
	}

	/**
	 * Returns whether the court case was dismissed.
	 * 
	 * @return dismissed
	 */
	public Boolean getDismissed() {
		return dismissed;
	}

	/**
	 * Returns whether the conviction was overturned.
	 * 
	 * @return conviction overturned
	 */
	public Boolean getConvictionOverturned() {
		return convictionOverturned;
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
	 * Returns the number of charges.
	 * 
	 * @return number of charges
	 */
	public Long getCharges() {
		return charges;
	}

	/**
	 * Returns the number of convictions.
	 * 
	 * @return number of convictions
	 */
	public Long getConvictions() {
		return convictions;
	}
}