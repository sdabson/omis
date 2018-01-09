package omis.report;

import java.util.Map;

/** Report runner.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0 */
public interface ReportRunner {
	/** Runs and retrieves report.
	 * @param report report name.
	 * @param parameters parameters required for report.
	 * @param format format to export report as.
	 * @return report. */
	byte[] runReport(String report, Map<String, String> parameters,
			ReportFormat format);
}
