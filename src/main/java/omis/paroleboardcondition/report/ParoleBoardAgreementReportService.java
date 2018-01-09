package omis.paroleboardcondition.report;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionGroup;
import omis.condition.report.ConditionSummary;
import omis.offender.domain.Offender;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;

/**
 * Parole Board Agreement Report Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementReportService {
	
	/** 
	 * Returns a ParoleBoardAgreementSummary from an Agreement.
	 * 
	 * @param agreement - ParoleBoardAgreement.
	 * @return ParoleBoardAgreementSummary object relevant to the Agreement. */
	ParoleBoardAgreementSummary summarize(ParoleBoardAgreement agreement);

	/** 
	 * Finds parole board agreements by offender on a date.
	 * 
	 * @param offender - Offender
	 * @param effectiveDate - Date
	 * @return list of Parole Board Agreements. */
	List<ParoleBoardAgreementSummary> findByOffender(Offender offender,
			Date effectiveDate);
	
	/**
	 * Finds parole board agreement summaries by specified Offender effective
	 * within the given date range.
	 * 
	 * @param offender - Offender
	 * @param startDate - start Date of the date range
	 * @param endDate - end Date of the date range
	 * @return list of parole board agreement summaries by specified Offender
	 * effective within the given date range
	 */
	List<ParoleBoardAgreementSummary> findByOffenderOnDates(Offender offender,
			Date startDate, Date endDate);
	
	/**
	 * Returns Condition Summaries of conditions for the specified Agreement
	 * with the given Condition Category.
	 * 
	 * @param agreement - Agreement
	 * @param conditionCategory - Condition Category
	 * @return List of Condition Summaries of conditions for the specified
	 * Agreement with the given Condition Category
	 */
	List<ConditionSummary>
		findConditionSummariesByAgreementAndConditionCategory(
				Agreement agreement, ConditionCategory conditionCategory);
	
	/**
	 * Returns Condition Summaries of conditions for the specified Agreement
	 * with the specified Condition Group.
	 * 
	 * @param agreement - Agreement
	 * @param conditionGroup - Condition Group
	 * @return List of Condition Summaries of conditions for the specified
	 * Agreement with the specified Condition Group
	 */
	List<ConditionSummary> findConditionSummariesByAgreementAndConditionGroup(
			Agreement agreement, ConditionGroup conditionGroup);
}
