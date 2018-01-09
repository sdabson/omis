package omis.separationneed.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Separation need report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 26, 2016)
 * @since OMIS 3.0
 */
public interface SeparationNeedReportService {

	/**
	 * Summarizes separation need for the specified offender.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return separation need summaries
	 */
	List<SeparationNeedSummary> summarizeByOffenderOnDate(
			Offender offender, Date date);
}