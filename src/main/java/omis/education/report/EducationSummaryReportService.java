package omis.education.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * EducationSummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationSummaryReportService {
	
	/**
	 * Finds and returns a list of education summaries by offender
	 * @param offender - offender
	 * @return list of education summaries
	 */
	List<EducationSummary> findByOffender(Offender offender);
	
	/**
	 * Finds and returns a list of education document summaries by offender
	 * @param offender - Offender
	 * @return list of education document summaries
	 */
	List<EducationDocumentSummary> findEducationDocumentSummariesByOffender(
			Offender offender);
	
}
