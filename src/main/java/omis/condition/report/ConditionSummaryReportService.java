package omis.condition.report;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.offender.domain.Offender;

/**
 * Condition Summary Report Service.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0
 */
public interface ConditionSummaryReportService {
	
	/** Finds an AgreementSummary by agreement.
	 * @param agreement - Agreement.
	 * @return list of agreement summaries. */
	AgreementSummary summarize(Agreement agreement);

	/** Finds Condition Summaries by Offender.
	 * @param offender - offender.
	 * @return list of condition summaries. */
	List<AgreementSummary> findByOffender(Offender offender,
			Date effectiveDate);


	/** Finds Condition Summaries by Agreement.
	 * @param Agreement - Agreement.
	 * @param effectiveDate - effectiveDate to highlight as "active".
	 * @return list of condition summaries. */
	List<ConditionSummary> findByAgreement(Agreement agreement,
			Date effectiveDate);
}
