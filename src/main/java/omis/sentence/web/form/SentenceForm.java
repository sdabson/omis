package omis.sentence.web.form;

import java.util.Date;

import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;

/**
 * Form for sentences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public class SentenceForm {
	
	private Integer prisonTermYears;
	
	private Integer prisonTermMonths;
	
	private Integer prisonTermDays;
	
	private Date prisonDischargeDate;
	
	private Integer probationTermYears;
	
	private Integer probationTermMonths;
	
	private Integer probationTermDays;
	
	private Date probationDischargeDate;
	
	private Boolean concurrent;
	
	private Integer deferredTermYears;
	
	private Integer deferredTermMonths;
	
	private Integer deferredTermDays;
	
	private Integer jailTimeCredit;
	
	private Integer streetTimeCredit;
	
	private Date turnSelfInDate;
	
	private Date effectiveDate;
	
	private Date pronouncementDate;
	
	private SentenceCategory category;
	
	private SentenceLengthClassification lengthClassification;
	
	private LegalDispositionCategory legalDispositionCategory;
	
	/** Instantiates a default form for sentences. */
	public SentenceForm() {
		// Default instantiation
	}

	/**
	 * Returns the prison term years.
	 * 
	 * @return prison term years
	 */
	public Integer getPrisonTermYears() {
		return this.prisonTermYears;
	}

	/**
	 * Sets the prison term years.
	 * 
	 * @param prisonTermYears prison term years
	 */
	public void setPrisonTermYears(final Integer prisonTermYears) {
		this.prisonTermYears = prisonTermYears;
	}
	
	/**
	 * Returns the prison term months.
	 * 
	 * @return prison term months
	 */
	public Integer getPrisonTermMonths() {
		return this.prisonTermMonths;
	}

	/**
	 * Sets the prison term months.
	 * 
	 * @param prisonTermMonths prison term months
	 */
	public void setPrisonTermMonths(final Integer prisonTermMonths) {
		this.prisonTermMonths = prisonTermMonths;
	}

	/**
	 * Returns the prison term days.
	 * 
	 * @return prison term days
	 */
	public Integer getPrisonTermDays() {
		return this.prisonTermDays;
	}

	/**
	 * Sets the prison term days.
	 * 
	 * @param prisonTermDays prison term days
	 */
	public void setPrisonTermDays(final Integer prisonTermDays) {
		this.prisonTermDays = prisonTermDays;
	}

	/**
	 * Returns prison discharge date.
	 * 
	 * @return prison discharge date
	 */
	public Date getPrisonDischargeDate() {
		return this.prisonDischargeDate;
	}

	/**
	 * Sets the prison discharge date.
	 * 
	 * @param prisonDischargeDate prison discharge date
	 */
	public void setPrisonDischargeDate(final Date prisonDischargeDate) {
		this.prisonDischargeDate = prisonDischargeDate;
	}

	/**
	 * Returns probation term years.
	 * 
	 * @return probation term years
	 */
	public Integer getProbationTermYears() {
		return this.probationTermYears;
	}

	/**
	 * Sets probation term years.
	 * 
	 * @param probationTermYears probation years
	 */
	public void setProbationTermYears(final Integer probationTermYears) {
		this.probationTermYears = probationTermYears;
	}

	/**
	 * Returns probation term months.
	 * 
	 * @return probation term months
	 */
	public Integer getProbationTermMonths() {
		return this.probationTermMonths;
	}

	/**
	 * Sets probation term months.
	 * 
	 * @param probationTermMonths probation months
	 */
	public void setProbationTermMonths(final Integer probationTermMonths) {
		this.probationTermMonths = probationTermMonths;
	}

	/**
	 * Returns probation term days.
	 * 
	 * @return probation term days
	 */
	public Integer getProbationTermDays() {
		return this.probationTermDays;
	}

	/**
	 * Sets probation term days.
	 * 
	 * @param probationTermDays probation term days
	 */
	public void setProbationTermDays(final Integer probationTermDays) {
		this.probationTermDays = probationTermDays;
	}

	/**
	 * Returns probation discharge date.
	 * 
	 * @return probation discharge date
	 */
	public Date getProbationDischargeDate() {
		return this.probationDischargeDate;
	}

	/**
	 * Sets the probation discharge date.
	 * 
	 * @param probationDischargeDate probation discharge date
	 */
	public void setProbationDischargeDate(
			final Date probationDischargeDate) {
		this.probationDischargeDate = probationDischargeDate;
	}

	/**
	 * Returns whether the sentence is to be served concurrently.
	 * 
	 * @return whether sentence is to be served concurrently
	 */
	public Boolean getConcurrent() {
		return this.concurrent;
	}

	/**
	 * Sets whether the sentence is to be served concurrently.
	 * 
	 * @param concurrent whether sentence is to be served concurrently
	 */
	public void setConcurrent(final Boolean concurrent) {
		this.concurrent = concurrent;
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
	 * Sets the deferred term years.
	 * 
	 * @param deferredTermYears deferred term years
	 */
	public void setDeferredTermYears(Integer deferredTermYears) {
		this.deferredTermYears = deferredTermYears;
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
	 * Sets the deferred term months.
	 * 
	 * @param deferredTermMonths deferred term months
	 */
	public void setDeferredTermMonths(Integer deferredTermMonths) {
		this.deferredTermMonths = deferredTermMonths;
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
	 * Sets the deferred term days.
	 * 
	 * @param deferredTermDays deferred term days
	 */
	public void setDeferredTermDays(Integer deferredTermDays) {
		this.deferredTermDays = deferredTermDays;
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
	 * Sets the jail time credit.
	 * 
	 * @param jailTimeCredit jail time credit
	 */
	public void setJailTimeCredit(Integer jailTimeCredit) {
		this.jailTimeCredit = jailTimeCredit;
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
	 * Sets the street time credit.
	 * 
	 * @param streetTimeCredit street time credit
	 */
	public void setStreetTimeCredit(Integer streetTimeCredit) {
		this.streetTimeCredit = streetTimeCredit;
	}

	/**
	 * Returns the turn self in date.
	 * 
	 * @return turn self in date
	 */
	public Date getTurnSelfInDate() {
		return turnSelfInDate;
	}

	/**
	 * Sets the turn self in date.
	 * 
	 * @param turnSelfInDate turn self in date
	 */
	public void setTurnSelfInDate(Date turnSelfInDate) {
		this.turnSelfInDate = turnSelfInDate;
	}

	/**
	 * Returns the sentence effective date.
	 * 
	 * @return sentence effective date
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the sentence effective date.
	 * 
	 * @param effectiveDate sentence effective date
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the sentence pronouncement date.
	 * 
	 * @return sentence pronouncement date
	 */
	public Date getPronouncementDate() {
		return pronouncementDate;
	}

	/**
	 * Sets the sentence pronouncement date.
	 * 
	 * @param pronouncementDate sentence pronouncement date
	 */
	public void setPronouncementDate(Date pronouncementDate) {
		this.pronouncementDate = pronouncementDate;
	}

	/**
	 * Returns the sentence category.
	 * 
	 * @return sentence category
	 */
	public SentenceCategory getCategory() {
		return category;
	}

	/**
	 * Sets the sentence category.
	 * 
	 * @param category sentence category
	 */
	public void setCategory(SentenceCategory category) {
		this.category = category;
	}

	/**
	 * Returns the sentence length classification.
	 * 
	 * @return sentence length classification
	 */
	public SentenceLengthClassification getLengthClassification() {
		return lengthClassification;
	}

	/**
	 * Sets the sentence length classification.
	 * 
	 * @param lengthClassification sentence length classification
	 */
	public void setLengthClassification(
			SentenceLengthClassification lengthClassification) {
		this.lengthClassification = lengthClassification;
	}

	/**
	 * Returns the legal disposition category.
	 * 
	 * @return legal disposition category
	 */
	public LegalDispositionCategory getLegalDispositionCategory() {
		return legalDispositionCategory;
	}

	/**
	 * Sets the legal disposition category.
	 * @param legalDispositionCategory legal disposition category
	 */
	public void setLegalDispositionCategory(
			LegalDispositionCategory legalDispositionCategory) {
		this.legalDispositionCategory = legalDispositionCategory;
	}

}