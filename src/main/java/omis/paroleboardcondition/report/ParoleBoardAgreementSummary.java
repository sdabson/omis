package omis.paroleboardcondition.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.condition.report.ConditionSummary;

/**
 * Parole Board Agreement Summary.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Long paroleBoardAgreementId;
	
	private final String paroleBoardAgreementCategory;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private List<ConditionSummary> conditionSummaries;

	/**
	 * @param paroleBoardAgreementId - Long
	 * @param paroleBoardAgreementCategory - String
	 * @param startDate - Date
	 * @param endDate - Date
	 */
	public ParoleBoardAgreementSummary(final Long paroleBoardAgreementId,
			final String paroleBoardAgreementCategory, final Date startDate,
			final Date endDate) {
		this.paroleBoardAgreementId = paroleBoardAgreementId;
		this.paroleBoardAgreementCategory = paroleBoardAgreementCategory;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Returns the Parole Board Agreement Id.
	 * @return paroleBoardAgreementId - Long
	 */
	public Long getParoleBoardAgreementId() {
		return paroleBoardAgreementId;
	}

	/**
	 * Returns the Parole Board Agreement Category.
	 * @return paroleBoardAgreementCategory - String
	 */
	public String getParoleBoardAgreementCategory() {
		return paroleBoardAgreementCategory;
	}

	/**
	 * Returns the start Date.
	 * @return startDate - Date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the end Date.
	 * @return endDate - Date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Returns the Condition Summaries.
	 * @return conditionSummaries - List of Condition Summaries
	 */
	public List<ConditionSummary> getConditionSummaries() {
		return conditionSummaries;
	}

	/**
	 * Sets the Condition Summaries.
	 * @param conditionSummaries - List of Condition Summaries
	 */
	public void setConditionSummaries(
			final List<ConditionSummary> conditionSummaries) {
		this.conditionSummaries = conditionSummaries;
	}
}
