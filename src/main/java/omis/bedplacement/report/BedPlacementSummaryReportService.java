package omis.bedplacement.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for bed placement.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public interface BedPlacementSummaryReportService {
	/** Summarize bed placement by offender and date.
	 * @param offender - offender.
	 * @param date - date. 
	 * @return bed placement summary. */
	BedPlacementSummary findBedPlacementSummaryByOffenderAndDate(
			Offender offender,	Date date);

}
