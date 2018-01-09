package omis.workrestriction.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * WorkRestrictionReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionReportService {
	
	/**
	 * Finds and returns a list of work restriction summaries by specified offender
	 * @param offender - specified offender
	 * @return list of work restriction summaries by specified offender
	 */
	List<WorkRestrictionSummary> summariesByOffender(Offender offender);

}
