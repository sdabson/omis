package omis.condition.report;

import java.util.Date;
import java.util.List;

/** Agreement Summary.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0 */
public class AgreementSummary {
	
	private final Long agreementId;
	private final Date startDate;
	private final Date endDate;
	private List<ConditionSummary> conditionSummaries;

	/**
	 * Instantiates summary of the Agreement
	 * @param agreementId  the agreement Id which is relevant to the summary.
	 * We don't use an actual Agreement object because we only need to pass
	 * the id.
	 * @param startDate the start Date of the Agreement Summary
	 * @param endDate
	 */
	public AgreementSummary(final Long agreementId,
			final Date startDate, final Date endDate) {
		this.agreementId = agreementId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Returns the agreementId
	 * @return agreementId - Long
	 */
	public Long getAgreementId() {
		return agreementId;
	}

	/**
	 * Returns the startDate
	 * @return startDate - Date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the endDate
	 * @return endDate - Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Returns the conditionSummaries
	 * @return conditionSummaries - List<ConditionSummary>
	 */
	public List<ConditionSummary> getConditionSummaries() {
		return conditionSummaries;
	}

	/**
	 * Sets the conditionSummaries
	 * @param conditionSummaries - List<ConditionSummary>
	 */
	public void setConditionSummaries(
			final List<ConditionSummary> conditionSummaries) {
		this.conditionSummaries = conditionSummaries;
	}
	
	
}
