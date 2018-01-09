package omis.education.report;

import java.util.List;

import omis.offender.domain.Offender;

/** Reports service for education profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 30, 2016)
 * @since OMIS 3.0 */
public interface EducationProfileItemReportService {
	/** Finds high school education by offender.
	 * @param offender - offender.
	 * @return high school educated. */
	public List<EducationProfileSummary> findEducationSummaryByOffender(
			final Offender offender);
}
