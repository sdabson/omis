package omis.courtcasecondition.report;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionGroup;
import omis.condition.report.ConditionSummary;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.offender.domain.Offender;

/** Agreement Summary.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Oct 11, 2017)
 * @since OMIS 3.0 */
public interface CourtCaseAgreementReportService {

	/** Returns a CourtCaseAgreementSummary from an Agreement.
	 * @param agreement - CourtCaseAgreement.
	 * @return CourtCaseAgreementSummary object relevant to the Agreement. */
	CourtCaseAgreementSummary summarize(CourtCaseAgreement agreement);

	/** Finds court case agreements by offender on a date.
	 * @param offender - Offender
	 * @param effectiveDate - Date
	 * @return list of Court Case Agreements. */
	List<CourtCaseAgreementSummary> findByOffender(Offender offender,
			Date effectiveDate);
	
	/**
	 * Finds court case agreement summaries by specified Offender effective
	 * within the given date range
	 * @param offender - Offender
	 * @param startDate - start Date of the date range
	 * @param endDate - end Date of the date range
	 * @return list of court case agreement summaries by specified Offender
	 * effective within the given date range
	 */
	List<CourtCaseAgreementSummary> findByOffenderOnDates(Offender offender,
			Date startDate, Date endDate);
	
	/**
	 * Returns Condition Summaries of conditions for the specified Agreement
	 * with the given Condition Category
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
	 * Returns Condition Summaries of conditions for the specified Agreement with
	 * the specified Condition Group
	 * 
	 * @param agreement - Agreement
	 * @param conditionGroup - Condition Group
	 * @return List of Condition Summaries of conditions for the specified
	 * Agreement with the specified Condition Group
	 */
	List<ConditionSummary> findConditionSummariesByAgreementAndConditionGroup(
			Agreement agreement, ConditionGroup conditionGroup);
	
	
	
}
