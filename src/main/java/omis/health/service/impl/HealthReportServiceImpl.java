package omis.health.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import omis.health.service.HealthReportService;
import omis.report.ReportFormat;
import omis.report.ReportRunner;

/** Implementation of Health report service.
 * @author Ryan Johns
 * @version 0.1.0 (May 23, 2014)
 * @since OMIS 3.0 */
public class HealthReportServiceImpl implements HealthReportService {
	final private static String DATE_FORMAT = "yyyy-MM-dd";
	final private static String START_DATE_MAP_KEY = "start_date";
	final private static String END_DATE_MAP_KEY = "end_date";


	final private String callOutListReportPath;
	final private ReportRunner reportRunner;

	public HealthReportServiceImpl(final ReportRunner reportRunner,
			final String callOutListReportPath) {
		this.reportRunner = reportRunner;
		this.callOutListReportPath = callOutListReportPath;
	}

	@Override
	public byte[] runCallOutList(final Date startDate, final Date endDate,
			final ReportFormat reportFormat) {
		final Map<String, String> map = new HashMap<String, String>();
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		map.put(START_DATE_MAP_KEY, sdf.format(startDate));
		map.put(END_DATE_MAP_KEY, sdf.format(endDate));

		return this.reportRunner.runReport(this.callOutListReportPath, map,
				reportFormat);
	}

}
