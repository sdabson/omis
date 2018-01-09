package omis.substance.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for substance summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceSummaryService {

	/**
	 * Return a list of substance test summaries for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of substance summaries
	 */
	List<SubstanceTestSummary> summarizeSubstanceTestsByOffender(
			Offender offender);
}