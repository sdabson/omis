package omis.sentence.report;

import java.io.Serializable;
import java.util.Date;

import omis.sentence.domain.SentenceConnectionClassification;
import omis.sentence.domain.TermRequirement;

/**
 * Sentence summary.
 * @author Josh Divine
 * @version 0.1.0 (May 1, 2017)
 * @since OMIS 3.0
 */
public class SentenceSummary implements Serializable {

	private static final long serialVersionUID = 1;
	
	private final Long id;
	
	private final Long defendantId;
	
	private final String defendantLastName;
	
	private final String defendantFirstName;
	
	private final String defendantMiddleName;
	
	private final String defendantSuffix;
	
	private final Boolean defendantOffender;
	
	private final Integer defendantOffenderNumber;
	
	private final Long courtCaseId;
	
	private final String courtName;
	
	private final String docket;
	
	private final String legalDispositionCategoryName;
	
	private final Date pronouncementDate;
	
	private final Long judgeId;
	
	private final String judgeLastName;
	
	private final String judgeFirstName;
	
	private final String judgeMiddleName;
	
	private final String judgeSuffix;
	
	private final Long convictionId;
	
	private final Date convictionDate;
	
	private final String convictionOffenseName;
	
	private final String convictionOffenseUrl;
	
	private final Integer convictionCounts;
	
	private final Long categoryId;
	
	private final String categoryName;
	
	private final TermRequirement prisonRequirement;
	
	private final TermRequirement probationRequirement;
	
	private final TermRequirement deferredRequirement;
	
	private final Date commencementDate;
	
	private final SentenceConnectionClassification connectionClassification;
	
	private final Long connectionSentenceId;
	
	private final Integer prisonTermYears;
	
	private final Integer prisonTermMonths;
	
	private final Integer prisonTermDays;
	
	private final Integer probationTermYears;
	
	private final Integer probationTermMonths;
	
	private final Integer probationTermDays;
	
	private final Integer deferredTermYears;
	
	private final Integer deferredTermMonths;
	
	private final Integer deferredTermDays;
	
	private final Integer jailTimeCredit;
	
	private final Integer streetTimeCredit;
	
	private final Boolean active;
	
	private final Integer changeOrder;
	
	/**
	 * Instantiates an instance of sentence summary with all properties.
	 * 
	 * @param id sentence ID
	 * @param defendantId defendant ID
	 * @param defendantLastName defendant last name
	 * @param defendantFirstName defendant first name
	 * @param defendantMiddleName defendant middle name
	 * @param defendantSuffix defendant suffix
	 * @param defendantOffender defendant is an offender
	 * @param defendantOffenderNumber defendant offender number
	 * @param courtCaseId court case ID
	 * @param courtName court name
	 * @param docket docket
	 * @param legalDispositionCategoryName legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param judgeId judge ID
	 * @param judgeLastName judge last name
	 * @param judgeFirstName judge first name
	 * @param judgeMiddleName judge middle name
	 * @param judgeSuffix judge suffix
	 * @param convictionId conviction ID
	 * @param convictionDate conviction date
	 * @param convictionOffenseName conviction offense name
	 * @param convictionOffenseUrl conviction offense url
	 * @param convictionCounts conviction counts
	 * @param categoryId category id
	 * @param categoryName category name
	 * @param prisonRequirement prison requirement
	 * @param probationRequirement probation requirement
	 * @param deferredRequirement deferred requirement
	 * @param commencementDate commencement date
	 * @param connectionClassification sentence connection classification
	 * @param connectionSentenceId sentence connection ID
	 * @param prisonTermYears prison term years
	 * @param prisonTermMonths prison term months
	 * @param prisonTermDays prison term days
	 * @param probationTermYears probation term years
	 * @param probationTermMonths probation term months
	 * @param probationTermDays probation term days
	 * @param deferredTermYears deferred term years
	 * @param deferredTermMonths deferred term months
	 * @param deferredTermDays deferred term days
	 * @param jailTimeCredit jail time credit
	 * @param streetTimeCredit street time credit
	 * @param active active
	 * @param changeOrder change order
	 */
	public SentenceSummary(final Long id, final Long defendantId,
			final String defendantLastName, final String defendantFirstName,
			final String defendantMiddleName, final String defendantSuffix,
			final Boolean defendantOffender, 
			final Integer defendantOffenderNumber, final Long courtCaseId, 
			final String courtName, final String docket, 
			final String legalDispositionCategoryName,
			final Date pronouncementDate, final Long judgeId, 
			final String judgeLastName, final String judgeFirstName, 
			final String judgeMiddleName, final String judgeSuffix, 
			final Long convictionId, final Date convictionDate, 
			final String convictionOffenseName, 
			final String convictionOffenseUrl, final Integer convictionCounts, 
			final Long categoryId, final String categoryName, 
			final TermRequirement prisonRequirement, 
			final TermRequirement probationRequirement, 			
			final TermRequirement deferredRequirement, 
			final Date commencementDate,
			final SentenceConnectionClassification connectionClassification,
			final Long connectionSentenceId, final Integer prisonTermYears,
			final Integer prisonTermMonths, final Integer prisonTermDays,
			final Integer probationTermYears, final Integer probationTermMonths, 
			final Integer probationTermDays, final Integer deferredTermYears,
			final Integer deferredTermMonths, final Integer deferredTermDays,
			final Integer jailTimeCredit, final Integer streetTimeCredit, 
			final Boolean active, final Integer changeOrder) {
		this.id = id;
		this.defendantId = defendantId;
		this.defendantLastName = defendantLastName;
		this.defendantFirstName = defendantFirstName;
		this.defendantMiddleName = defendantMiddleName;
		this.defendantSuffix = defendantSuffix;
		this.defendantOffender = defendantOffender;
		this.defendantOffenderNumber = defendantOffenderNumber;
		this.courtCaseId = courtCaseId;
		this.courtName = courtName;
		this.docket = docket;
		this.legalDispositionCategoryName = legalDispositionCategoryName;
		this.pronouncementDate = pronouncementDate;
		this.judgeId = judgeId;
		this.judgeLastName = judgeLastName;
		this.judgeFirstName = judgeFirstName;
		this.judgeMiddleName = judgeMiddleName;
		this.judgeSuffix = judgeSuffix;
		this.convictionId = convictionId;
		this.convictionDate = convictionDate;
		this.convictionOffenseName = convictionOffenseName;
		this.convictionOffenseUrl = convictionOffenseUrl;
		this.convictionCounts = convictionCounts;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.prisonRequirement = prisonRequirement;
		this.probationRequirement = probationRequirement;
		this.deferredRequirement = deferredRequirement;
		this.commencementDate = commencementDate;
		this.connectionClassification = connectionClassification;
		this.connectionSentenceId = connectionSentenceId;
		this.prisonTermYears = prisonTermYears;
		this.prisonTermMonths = prisonTermMonths;
		this.prisonTermDays = prisonTermDays;
		this.probationTermYears = probationTermYears;
		this.probationTermMonths = probationTermMonths;
		this.probationTermDays = probationTermDays;
		this.deferredTermYears = deferredTermYears;
		this.deferredTermMonths = deferredTermMonths;
		this.deferredTermDays = deferredTermDays;
		this.jailTimeCredit = jailTimeCredit;
		this.streetTimeCredit = streetTimeCredit;
		this.active = active;
		this.changeOrder = changeOrder;
	}

	/**
	 * Returns the sentence ID.
	 * 
	 * @return sentence ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the defendant ID.
	 * 
	 * @return defendant ID
	 */
	public Long getDefendantId() {
		return defendantId;
	}

	/**
	 * Returns the defendant last name.
	 * 
	 * @return defendant last name
	 */
	public String getDefendantLastName() {
		return defendantLastName;
	}

	/**
	 * Returns the defendant first name.
	 * 
	 * @return defendant first name
	 */
	public String getDefendantFirstName() {
		return defendantFirstName;
	}

	/**
	 * Returns the defendant middle name.
	 * 
	 * @return defendant middle name
	 */
	public String getDefendantMiddleName() {
		return defendantMiddleName;
	}

	/**
	 * Returns the defendant suffix.
	 * 
	 * @return defendant suffix
	 */
	public String getDefendantSuffix() {
		return defendantSuffix;
	}

	/**
	 * Returns whether the defendant is an existing offender.
	 * 
	 * @return defendant existing offender
	 */
	public Boolean getDefendantOffender() {
		return defendantOffender;
	}

	/**
	 * Returns the defendant offender number.
	 * 
	 * @return defendant offender number
	 */
	public Integer getDefendantOffenderNumber() {
		return defendantOffenderNumber;
	}

	/**
	 * Returns the court case ID.
	 * 
	 * @return court case ID
	 */
	public Long getCourtCaseId() {
		return courtCaseId;
	}

	/**
	 * Returns the name of the court.
	 * 
	 * @return court name
	 */
	public String getCourtName() {
		return courtName;
	}

	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	public String getDocket() {
		return docket;
	}

	/**
	 * Returns the legal disposition category name.
	 * 
	 * @return legal disposition category name
	 */
	public String getLegalDispositionCategoryName() {
		return legalDispositionCategoryName;
	}

	
	/**
	 * Returns the pronouncement date.
	 * 
	 * @return pronouncement date
	 */
	public Date getPronouncementDate() {
		return pronouncementDate;
	}

	/**
	 * Returns the judge ID.
	 * 
	 * @return judge ID
	 */
	public Long getJudgeId() {
		return judgeId;
	}

	/**
	 * Returns the judge last name.
	 * 
	 * @return judge last name
	 */
	public String getJudgeLastName() {
		return judgeLastName;
	}

	/**
	 * Returns the judge first name.
	 * 
	 * @return judge first name
	 */
	public String getJudgeFirstName() {
		return judgeFirstName;
	}

	/**
	 * Returns the judge middle name.
	 * 
	 * @return judge middle name
	 */
	public String getJudgeMiddleName() {
		return judgeMiddleName;
	}

	/**
	 * Returns the judge suffix.
	 * 
	 * @return judge suffix
	 */
	public String getJudgeSuffix() {
		return judgeSuffix;
	}

	/**
	 * Returns the conviction ID.
	 * 
	 * @return the conviction ID
	 */
	public Long getConvictionId() {
		return convictionId;
	}

	/**
	 * Returns the conviction date.
	 * 
	 * @return conviction date
	 */
	public Date getConvictionDate() {
		return convictionDate;
	}

	/**
	 * Returns the conviction offense name.
	 * 
	 * @return conviction offense name
	 */
	public String getConvictionOffenseName() {
		return convictionOffenseName;
	}

	/**
	 * Returns the conviction offense url.
	 * 
	 * @return conviction offense url
	 */
	public String getConvictionOffenseUrl() {
		return convictionOffenseUrl;
	}
	
	/**
	 * Returns the conviction counts.
	 * 
	 * @return conviction counts
	 */
	public Integer getConvictionCounts() {
		return convictionCounts;
	}

	/**
	 * Returns the category ID.
	 * 
	 * @return category ID
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns the category name.
	 * 
	 * @return category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Returns the term requirement for a prison term.
	 * 
	 * @return prison term requirement
	 */
	public TermRequirement getPrisonRequirement() {
		return prisonRequirement;
	}

	/**
	 * Returns the term requirement for a probation term.
	 * 
	 * @return probation term requirement
	 */
	public TermRequirement getProbationRequirement() {
		return probationRequirement;
	}

	/**
	 * Returns the term requirement for a deferred term.
	 * 
	 * @return deferred term requirement
	 */
	public TermRequirement getDeferredRequirement() {
		return deferredRequirement;
	}

	/**
	 * Returns the commencement date.
	 * 
	 * @return commencement date
	 */
	public Date getCommencementDate() {
		return commencementDate;
	}

	/**
	 * Returns the sentence connection classification.
	 * 
	 * @return sentence connection classification
	 */
	public SentenceConnectionClassification getConnectionClassification() {
		return connectionClassification;
	}

	/**
	 * Returns the sentence connection ID.
	 * 
	 * @return sentence connection ID
	 */
	public Long getConnectionSentenceId() {
		return connectionSentenceId;
	}

	/**
	 * Returns the number of prison term years.
	 * 
	 * @return prison term years
	 */
	public Integer getPrisonTermYears() {
		return prisonTermYears;
	}

	/**
	 * Returns the number of prison term months.
	 * 
	 * @return prison term months
	 */
	public Integer getPrisonTermMonths() {
		return prisonTermMonths;
	}

	/**
	 * Returns the number of prison term days.
	 * 
	 * @return prison term days
	 */
	public Integer getPrisonTermDays() {
		return prisonTermDays;
	}

	/**
	 * Returns the number of probation term years.
	 *  
	 * @return probation term years
	 */
	public Integer getProbationTermYears() {
		return probationTermYears;
	}

	/**
	 * Returns the number of probation term months.
	 * 
	 * @return probation term months
	 */
	public Integer getProbationTermMonths() {
		return probationTermMonths;
	}

	/**
	 * Returns the number of probation term days.
	 * 
	 * @return probation term days
	 */
	public Integer getProbationTermDays() {
		return probationTermDays;
	}

	/**
	 * Returns the deferred term years.
	 * 
	 * @return deferred term years
	 */
	public Integer getDeferredTermYears() {
		return deferredTermYears;
	}

	/**
	 * Returns the deferred term months.
	 * 
	 * @return deferred term months
	 */
	public Integer getDeferredTermMonths() {
		return deferredTermMonths;
	}

	/**
	 * Returns the deferred term days.
	 * 
	 * @return deferred term days
	 */
	public Integer getDeferredTermDays() {
		return deferredTermDays;
	}

	/**
	 * Returns the jail time credit.
	 * 
	 * @return jail time credit
	 */
	public Integer getJailTimeCredit() {
		return jailTimeCredit;
	}

	/**
	 * Returns the street time credit.
	 * 
	 * @return street time credit
	 */
	public Integer getStreetTimeCredit() {
		return streetTimeCredit;
	}
	
	/**
	 * Returns whether the sentence is active.
	 * 
	 * @return sentence active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Returns the change order.
	 * 
	 * @return change order
	 */
	public Integer getChangeOrder() {
		return changeOrder;
	}
}
