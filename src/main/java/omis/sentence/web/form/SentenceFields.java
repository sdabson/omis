package omis.sentence.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;

/**
 * Fields for sentences.
 *
 * <p>Note that fields do not handle consecutive/concurrent. This has to be
 * handled by the module using sentence fields.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SentenceFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SentenceCategory category;
	
	private SentenceLengthClassification lengthClassification;
	
	private LegalDispositionCategory legalDispositionCategory;
	
	private Integer prisonYears;
	
	private Integer prisonMonths;
	
	private Integer prisonDays;
	
	private Integer probationYears;
	
	private Integer probationMonths;
	
	private Integer probationDays;
	
	private Integer deferredYears;
	
	private Integer deferredMonths;
	
	private Integer deferredDays;
	
	private Integer prisonTotalDays;
	
	private Integer probationTotalDays;
	
	private Integer deferredTotalDays;
	
	private Date effectiveDate;
	
	private Date pronouncementDate;
	
	private Integer jailTimeCredit;
	
	private Integer streetTimeCredit;
	
	private Date turnSelfInDate;
	
	/** Instantiates sentence fields. */
	public SentenceFields() {
		// Default instantiation
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public SentenceCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	public void setCategory(final SentenceCategory category) {
		this.category = category;
	}
	
	/**
	 * Returns length classification.
	 * 
	 * @return length classification
	 */
	public SentenceLengthClassification getLengthClassification() {
		return this.lengthClassification;
	}
	
	/**
	 * Sets length classification.
	 * 
	 * @param lengthClassification length classification
	 */
	public void setLengthClassification(
			final SentenceLengthClassification lengthClassification) {
		this.lengthClassification = lengthClassification;
	}
	
	/**
	 * Returns legal disposition category.
	 * 
	 * @return legal disposition category
	 */
	public LegalDispositionCategory getLegalDispositionCategory() {
		return this.legalDispositionCategory;
	}
	
	/**
	 * Sets legal disposition category.
	 * 
	 * @param legalDispositionCategory legal disposition category
	 */
	public void setLegalDispositionCategory(
			final LegalDispositionCategory legalDispositionCategory) {
		this.legalDispositionCategory = legalDispositionCategory;
	}
	
	/**
	 * Returns prison years.
	 * 
	 * @return prison years
	 */
	public Integer getPrisonYears() {
		return this.prisonYears;
	}

	/**
	 * Sets prison years.
	 * 
	 * @param prisonYears prison years
	 */
	public void setPrisonYears(final Integer prisonYears) {
		this.prisonYears = prisonYears;
	}

	/**
	 * Returns prison months.
	 * 
	 * @return prison months
	 */
	public Integer getPrisonMonths() {
		return this.prisonMonths;
	}

	/**
	 * SEts prison months.
	 * 
	 * @param prisonMonths prison months
	 */
	public void setPrisonMonths(final Integer prisonMonths) {
		this.prisonMonths = prisonMonths;
	}

	/**
	 * Returns prison days. 
	 * 
	 * @return prison days
	 */
	public Integer getPrisonDays() {
		return this.prisonDays;
	}

	/**
	 * Sets prison days.
	 * 
	 * @param prisonDays prison days
	 */
	public void setPrisonDays(final Integer prisonDays) {
		this.prisonDays = prisonDays;
	}

	/**
	 * Returns probation years.
	 * 
	 * @return probation years
	 */
	public Integer getProbationYears() {
		return this.probationYears;
	}

	/**
	 * Sets probation years.
	 * 
	 * @param probationYears probation years
	 */
	public void setProbationYears(final Integer probationYears) {
		this.probationYears = probationYears;
	}

	/**
	 * Returns probation years.
	 * 
	 * @return probation years
	 */
	public Integer getProbationMonths() {
		return this.probationMonths;
	}

	/**
	 * Sets probation months.
	 * 
	 * @param probationMonths probation months
	 */
	public void setProbationMonths(final Integer probationMonths) {
		this.probationMonths = probationMonths;
	}

	/**
	 * Returns probation days.
	 * 
	 * @return probation days
	 */
	public Integer getProbationDays() {
		return this.probationDays;
	}

	/**
	 * Sets probation days.
	 * 
	 * @param probationDays probation days
	 */
	public void setProbationDays(final Integer probationDays) {
		this.probationDays = probationDays;
	}

	/**
	 * Returns deferred years.
	 * 
	 * @return deferred years
	 */
	public Integer getDeferredYears() {
		return this.deferredYears;
	}

	/**
	 * Sets deferred years.
	 * 
	 * @param deferredYears deferred years
	 */
	public void setDeferredYears(final Integer deferredYears) {
		this.deferredYears = deferredYears;
	}

	/**
	 * Returns deferred months.
	 * 
	 * @return deferred months
	 */
	public Integer getDeferredMonths() {
		return this.deferredMonths;
	}

	/**
	 * Sets deferred months.
	 * 
	 * @param deferredMonths deferred months
	 */
	public void setDeferredMonths(final Integer deferredMonths) {
		this.deferredMonths = deferredMonths;
	}

	/**
	 * Returns deferred days.
	 * 
	 * @return deferred days
	 */
	public Integer getDeferredDays() {
		return this.deferredDays;
	}

	/**
	 * Sets deferred days.
	 * 
	 * @param deferredDays deferred days
	 */
	public void setDeferredDays(final Integer deferredDays) {
		this.deferredDays = deferredDays;
	}

	/**
	 * Returns effective date.
	 * 
	 * @return effective date
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets effective date.
	 * 
	 * @param effectiveDate effective date
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	 * Sets pronouncement date.
	 * 
	 * @param pronouncementDate pronouncement date
	 */
	public void setPronouncementDate(final Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}

	/**
	 * Returns jail time credit.
	 * 
	 * @return jail time credit
	 */
	public Integer getJailTimeCredit() {
		return this.jailTimeCredit;
	}

	/**
	 * Sets jail time credit.
	 * 
	 * @param jailTimeCredit jail time credit
	 */
	public void setJailTimeCredit(final Integer jailTimeCredit) {
		this.jailTimeCredit = jailTimeCredit;
	}

	/**
	 * Returns street time credit.
	 * 
	 * @return street time credit
	 */
	public Integer getStreetTimeCredit() {
		return this.streetTimeCredit;
	}

	/**
	 * Sets street time credit.
	 * 
	 * @param streetTimeCredit street time credit
	 */
	public void setStreetTimeCredit(final Integer streetTimeCredit) {
		this.streetTimeCredit = streetTimeCredit;
	}

	/**
	 * Returns turn self in date.
	 * 
	 * @return turn self in date
	 */
	public Date getTurnSelfInDate() {
		return this.turnSelfInDate;
	}

	/**
	 * Sets turn self in date.
	 * 
	 * @param turnSelfInDate turn self in date
	 */
	public void setTurnSelfInDate(final Date turnSelfInDate) {
		this.turnSelfInDate = turnSelfInDate;
	}
	
	/**
	 * Sets total days of prison.
	 * 
	 * @param prisonTotalDays total days of prison
	 */
	public void setPrisonTotalDays(final Integer prisonTotalDays) {
		this.prisonTotalDays = prisonTotalDays;
	}
	
	/**
	 * Returns total days of prison.
	 * 
	 * @return total days of prison
	 */
	public Integer getPrisonTotalDays() {
		return this.prisonTotalDays;
	}
	
	/**
	 * Sets total days of probation.
	 * 
	 * @param probationTotalDays total days of probation
	 */
	public void setProbationTotalDays(final Integer probationTotalDays) {
		this.probationTotalDays = probationTotalDays;
	}
	
	/**
	 * Returns total days of probation.
	 * 
	 * @return total days of probation
	 */
	public Integer getProbationTotalDays() {
		return this.probationTotalDays;
	}
	
	/**
	 * Sets total days deferred.
	 * 
	 * @param deferredTotalDays total days deferred
	 */
	public void setDeferredTotalDays(final Integer deferredTotalDays) {
		this.deferredTotalDays = deferredTotalDays;
	}
	
	/**
	 * Returns total days deferred.
	 * 
	 * @return total days deferred
	 */
	public Integer getDeferredTotalDays() {
		return this.deferredTotalDays;
	}
}