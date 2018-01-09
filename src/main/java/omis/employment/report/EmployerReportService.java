package omis.employment.report;

import java.util.List;

/**
 * Report service for employer.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 4, 2015)
 * @since OMIS 3.0
 */
public interface EmployerReportService {
	/**
	 * Returns a list of employer.
	 * 
	 * @param name name
	 * @return list of employer
	 */
	List<EmployerSummary> summarizeByName(String name); 
}