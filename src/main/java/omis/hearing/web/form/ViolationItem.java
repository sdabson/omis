package omis.hearing.web.form;

import java.util.Date;

import omis.hearing.domain.DispositionCategory;
import omis.hearing.domain.Infraction;
import omis.hearing.report.ViolationSummary;
import omis.person.domain.Person;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * ViolationItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationItem {
	
	private ViolationSummary summary;
	
	private ConditionViolation conditionViolation;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private Infraction infraction;
	
	private Date date;
	
	private String decision;
	
	private String reason;
	
	private String sanction;
	
	private Person authority;
	
	private Date appealDate;
	
	private DispositionCategory disposition;
	
	/**
	 * 
	 */
	public ViolationItem() {
	}

	/**
	 * @param summary
	 * @param conditionViolation
	 */
	public ViolationItem(final ViolationSummary summary,
			final ConditionViolation conditionViolation) {
		this.summary = summary;
		this.conditionViolation = conditionViolation;
		this.disciplinaryCodeViolation = null;
		this.infraction = null;
	}

	/**
	 * @param summary
	 * @param disciplinaryCodeViolation
	 */
	public ViolationItem(
			final ViolationSummary summary,
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.summary = summary;
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
		this.conditionViolation = null;
		this.infraction = null;
	}
	
	/**
	 * @param summary
	 * @param conditionViolation
	 */
	public ViolationItem(final ViolationSummary summary,
			final ConditionViolation conditionViolation,
			final Infraction infraction) {
		this.summary = summary;
		this.conditionViolation = conditionViolation;
		this.disciplinaryCodeViolation = null;
		this.infraction = infraction;
	}

	/**
	 * @param summary
	 * @param disciplinaryCodeViolation
	 */
	public ViolationItem(
			final ViolationSummary summary,
			final DisciplinaryCodeViolation disciplinaryCodeViolation,
			final Infraction infraction) {
		this.summary = summary;
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
		this.conditionViolation = null;
		this.infraction = infraction;
	}
	
	/**
	 * Returns the infraction
	 * @return infraction - Infraction
	 */
	public Infraction getInfraction() {
		return infraction;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the decision
	 * @return decision - String
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * Sets the decision
	 * @param decision - String
	 */
	public void setDecision(final String decision) {
		this.decision = decision;
	}

	/**
	 * Returns the reason
	 * @return reason - String
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Sets the reason
	 * @param reason - String
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * Returns the sanction
	 * @return sanction - String
	 */
	public String getSanction() {
		return sanction;
	}

	/**
	 * Sets the sanction
	 * @param sanction - String
	 */
	public void setSanction(final String sanction) {
		this.sanction = sanction;
	}

	/**
	 * Returns the disposition
	 * @return disposition - DispositionCategory
	 */
	public DispositionCategory getDisposition() {
		return disposition;
	}

	/**
	 * Sets the disposition
	 * @param disposition - DispositionCategory
	 */
	public void setDisposition(final DispositionCategory disposition) {
		this.disposition = disposition;
	}

	/**
	 * Returns the summary
	 * @return summary - ViolationSummary
	 */
	public ViolationSummary getSummary() {
		return summary;
	}

	/**
	 * Returns the conditionViolation
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation() {
		return conditionViolation;
	}

	/**
	 * Returns the disciplinaryCodeViolation
	 * @return disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public DisciplinaryCodeViolation getDisciplinaryCodeViolation() {
		return disciplinaryCodeViolation;
	}

	/**
	 * Sets the summary
	 * @param summary - ViolationSummary
	 */
	public void setSummary(
			final ViolationSummary summary) {
		this.summary = summary;
	}

	/**
	 * Sets the conditionViolation
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
	}

	/**
	 * Sets the disciplinaryCodeViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public void setDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
	}

	/**
	 * Sets the infraction
	 * @param infraction - Infraction
	 */
	public void setInfraction(final Infraction infraction) {
		this.infraction = infraction;
	}

	/**
	 * Returns the authority
	 * @return authority - Person
	 */
	public Person getAuthority() {
		return authority;
	}

	/**
	 * Sets the authority
	 * @param authority - Person
	 */
	public void setAuthority(final Person authority) {
		this.authority = authority;
	}

	/**
	 * Returns the appeal date
	 * @return appealDate - Date of appeal
	 */
	public Date getAppealDate() {
		return appealDate;
	}

	/**
	 * Sets the appeal date
	 * @param appealDate - Date of appeal
	 */
	public void setAppealDate(Date appealDate) {
		this.appealDate = appealDate;
	}
}
