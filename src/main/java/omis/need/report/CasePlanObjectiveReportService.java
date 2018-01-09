package omis.need.report;

import java.util.List;

import omis.need.domain.NeedDomain;
import omis.offender.domain.Offender;

/**
 * Case plan objective report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 10, 2015)
 * @since OMIS 3.0
 */
public interface CasePlanObjectiveReportService {

	/**
	 * Returns a list of case plan objective summaries for the specified 
	 * offender and criminogenic domain.
	 * 
	 * @param offender offender
	 * @param criminogenicDomain criminogenic domain
	 * @return list of case plan objective summaries
	 */
	List<CasePlanObjectiveSummary> findCasePlanObjectiveSummariesByDomain(
			Offender offender, NeedDomain criminogenicDomain);
	
	/**
	 * Returns a list of case plan objectives for the specified offender.
	 *
	 * @param offender offender
	 * @return list of case plan objective summaries
	 */
	List<CasePlanObjectiveSummary> findCasePlanObjectiveSummariesByOffender(
			Offender offender);
}