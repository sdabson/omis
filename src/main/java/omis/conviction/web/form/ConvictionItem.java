package omis.conviction.web.form;

import java.util.Date;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.courtcase.domain.CourtCase;
import omis.offense.domain.Offense;

/**
 * Conviction item on a sentence form.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (Jan 30, 2017)
 * @since OMIS 3.0
 */
public class ConvictionItem {

	private ConvictionOperation operation;
	
	private Conviction conviction;
	
	private Offense offense;
	
	private Date date;
	
	private Integer counts;
	
	private CourtCase courtCase;
	
	private OffenseSeverity severity;
	
	private Boolean violentOffense;
	
	private Boolean sexualOffense;
	
	private Boolean paroleIneligible;
	
	private Boolean supervisedReleaseIneligible;
	
	/** Instantiates a default conviction item on a sentence form. */
	public ConvictionItem() {
		// Default instantiation
	}

	/**
	 * Returns the charge operation.
	 * 
	 * @return charge operation
	 */
	public ConvictionOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the charge operation.
	 * 
	 * @param operation charge operation
	 */
	public void setOperation(final ConvictionOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the conviction.
	 * 
	 * @return conviction
	 */
	public Conviction getConviction() {
		return conviction;
	}

	/**
	 * Sets the conviction.
	 * 
	 * @param conviction conviction
	 */
	public void setConviction(Conviction conviction) {
		this.conviction = conviction;
	}

	/**
	 * Returns the offense.
	 * 
	 * @return offense
	 */
	public Offense getOffense() {
		return this.offense;
	}

	/**
	 * Sets the offense.
	 * 
	 * @param offense offense
	 */
	public void setOffense(final Offense offense) {
		this.offense = offense;
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
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the counts.
	 * 
	 * @return counts
	 */
	public Integer getCounts() {
		return this.counts;
	}

	/**
	 * Sets the counts.
	 * 
	 * @param counts counts
	 */
	public void setCounts(final Integer counts) {
		this.counts = counts;
	}

	/**
	 * Returns the court case.
	 * 
	 * @return court case
	 */
	public CourtCase getCourtCase() {
		return courtCase;
	}

	/**
	 * Sets the court case.
	 * 
	 * @param courtCase court case
	 */
	public void setCourtCase(CourtCase courtCase) {
		this.courtCase = courtCase;
	}

	/**
	 * Returns the offense severity.
	 * 
	 * @return offense severity
	 */
	public OffenseSeverity getSeverity() {
		return severity;
	}

	/**
	 * Sets the offense severity.
	 * 
	 * @param severity offense severity
	 */
	public void setSeverity(OffenseSeverity severity) {
		this.severity = severity;
	}

	/**
	 * Returns whether the conviction was a violent offense.
	 * 
	 * @return whether the conviction was a violent offense
	 */
	public Boolean getViolentOffense() {
		return violentOffense;
	}

	/**
	 * Sets whether the conviction was a violent offense.
	 * 
	 * @param violentOffense violent offense
	 */
	public void setViolentOffense(Boolean violentOffense) {
		this.violentOffense = violentOffense;
	}

	/**
	 * Returns whether the conviction was a sexual offense.
	 * @return whether the conviction was a sexual offense
	 */
	public Boolean getSexualOffense() {
		return sexualOffense;
	}

	/**
	 * Sets whether the conviction was a sexual offense.
	 * 
	 * @param sexualOffense sexual offense
	 */
	public void setSexualOffense(Boolean sexualOffense) {
		this.sexualOffense = sexualOffense;
	}

	/**
	 * Returns whether the conviction is parole ineligible.
	 * 
	 * @return whether the conviction is parole ineligible
	 */
	public Boolean getParoleIneligible() {
		return paroleIneligible;
	}

	/**
	 * Sets whether the conviction is parole ineligible.
	 * 
	 * @param paroleIneligible parole ineligible
	 */
	public void setParoleIneligible(Boolean paroleIneligible) {
		this.paroleIneligible = paroleIneligible;
	}

	/**
	 * Return whether the conviction is supervised release ineligible.
	 * 
	 * @return whether the conviction is supervised release ineligible
	 */
	public Boolean getSupervisedReleaseIneligible() {
		return supervisedReleaseIneligible;
	}

	/**
	 * Sets whether the conviction is supervised release ineligible.
	 * 
	 * @param supervisedReleaseIneligible supervised release ineligible
	 */
	public void setSupervisedReleaseIneligible(
			Boolean supervisedReleaseIneligible) {
		this.supervisedReleaseIneligible = supervisedReleaseIneligible;
	}

}