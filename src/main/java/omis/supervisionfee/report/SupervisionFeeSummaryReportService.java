package omis.supervisionfee.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.supervisionfee.domain.MonthlySupervisionFee;

/**
 * Summary service for supervision fees.
 * 
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jun 3, 2016)
 * @since OMIS 3.0
 */
public interface SupervisionFeeSummaryReportService {

	/**
	 * Finds summary of supervision fees by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate - effective date.
	 * @return supervision fee summary
	 */
	List<SupervisionFeeSummary> findByOffender(
					final Offender offender, final Date effectiveDate);
	
	/**
	 * Finds supervision fees by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate - effective date.
	 * @return supervision fees associated with this offender
	 */
	List<MonthlySupervisionFee> findSupervisionFeesBy(
					final Offender offender);
	
	/**
	 * Finds a summary of the supervision fee requirements by 
	 * monthly supervision fee.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee
	 * @param effectiveDate - effective date.
	 * @return supervision fee requirement summary
	 */
	List<SupervisionFeeRequirementSummary> 
					findSupervisionFeeRequirementsBySupervisionFee(
							final MonthlySupervisionFee monthlySupervisionFee,
							final Date effectiveDate);
}
