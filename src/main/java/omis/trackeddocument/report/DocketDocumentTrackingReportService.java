package omis.trackeddocument.report;

import java.util.List;
import omis.person.domain.Person;

/**
 * Report service for docket document tracking.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public interface DocketDocumentTrackingReportService {

	/**
	 * Returns the list of docket document receival summary.
	 * 
	 * @param defendant defendant
	 * @return list of docketDocumentReceivalSummary
	 */
	List<DocketDocumentReceivalSummary> summarizedByDefendant(
		Person defendant);
}
