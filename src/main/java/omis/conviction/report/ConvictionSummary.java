package omis.conviction.report;

import java.io.Serializable;
import java.util.Date;

import omis.conviction.domain.OffenseSeverity;

/**
 * Conviction summary.
 * @author Josh Divine
 * @version 0.1.0 (May 1, 2017)
 * @since OMIS 3.0
 */
public class ConvictionSummary implements Serializable {

	private static final long serialVersionUID = 1;
	
	private final Long id;
	
	private final Long courtCaseId;
	
	private final Date date;
	
	private final String offenseName;
	
	private final Integer counts;
	
	private final OffenseSeverity severity;
	
	private final Boolean violentOffense;
	
	private final Boolean sexualOffense;
	
	private final Boolean paroleIneligible;
	
	private final Boolean supervisedReleaseIneligibile;
	

	/**
	 * Instantiates a conviction summary with all properties.
	 * 
	 * @param id conviction ID
	 * @param courtCaseId court case ID
	 * @param date date
	 * @param offenseName conviction offense name
	 * @param counts counts
	 * @param severity offense severity
	 * @param violentOffense violent offense
	 * @param sexualOffense sexual offense
	 * @param paroleIneligible parole ineligible
	 * @param supervisedReleaseIneligibile supervised release ineligible
	 */
	public ConvictionSummary(final Long id, final Long courtCaseId,
			final Date date, final String offenseName, final Integer counts,
			final OffenseSeverity severity, final Boolean violentOffense, 
			final Boolean sexualOffense, final Boolean paroleIneligible, 
			final Boolean supervisedReleaseIneligibile) {
		this.id = id;
		this.courtCaseId = courtCaseId;
		this.date = date;
		this.offenseName = offenseName;
		this.counts = counts;
		this.severity = severity;
		this.violentOffense = violentOffense;
		this.sexualOffense = sexualOffense;
		this.paroleIneligible = paroleIneligible;
		this.supervisedReleaseIneligibile = supervisedReleaseIneligibile;
	}


	/**
	 * Returns the conviction ID.
	 * 
	 * @return conviction ID
	 */
	public Long getId() {
		return id;
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
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the conviction offense name.
	 * 
	 * @return offense name
	 */
	public String getOffenseName() {
		return offenseName;
	}


	/**
	 * Returns the conviction counts.
	 * 
	 * @return counts
	 */
	public Integer getCounts() {
		return counts;
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
	 * Returns whether the offense was violent.
	 * 
	 * @return violent offense
	 */
	public Boolean getViolentOffense() {
		return violentOffense;
	}


	/**
	 * Returns whether the offense was sexual.
	 * 
	 * @return sexual iffense
	 */
	public Boolean getSexualOffense() {
		return sexualOffense;
	}


	/**
	 * Returns whether the conviction is parole ineligible.
	 * 
	 * @return parole ineligible
	 */
	public Boolean getParoleIneligible() {
		return paroleIneligible;
	}


	/**
	 * Returns whether the conviction is supervised release ineligible.
	 * 
	 * @return supervised release ineligibile
	 */
	public Boolean getSupervisedReleaseIneligibile() {
		return supervisedReleaseIneligibile;
	}
}
