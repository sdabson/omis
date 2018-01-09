package omis.health.service;

import java.util.Date;

import omis.report.ReportFormat;

/** Service for health related reports.
 * @author Ryan Johns
 * @version 0.1.0 (May 23, 2014)
 * @since OMIS 3.0 */
public interface HealthReportService {
	/** runs call out list report.
	 * @param startDate start date.
	 * @param endDate end date.
	 * @param reportFormat report format. */
	byte[] runCallOutList(Date startDate, Date endDate,
			ReportFormat reportFormat);
}
