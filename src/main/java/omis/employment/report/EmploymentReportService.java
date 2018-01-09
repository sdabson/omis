package omis.employment.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for employment history.
 * 
 * @author Yidong Li
 * @version 0.1.0 (March 12, 2014)
 * @since OMIS 3.0
 */
public interface EmploymentReportService {

	/**
	 * Returns the list of offender employment history terms.
	 * 
	 * @param offender offender
	 * @return list of offender employment history term
	 */

	List<EmploymentSummary> findByOffender(Offender offender); 

}