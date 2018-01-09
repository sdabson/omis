package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;

/** Report service for health providers.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2014)
 * @since OMIS 3.0 */
public interface ProviderReportService {
	/** list health providers summary.
	 * @param facility facility.
	 * @param startDate start date.
	 * @param endDate end date.
	 * @return provider summary. */
	List<ProviderSummary> findHealthProviders(Facility facility,
			Date startDate, Date endDate);

	/** list health provider summaries.
	 * @param facility facility.
	 * @param date date.
	 * @return provider summaries. */
	List<ProviderSummary> findHealthProviders(Facility facility,
			Date date);

	/** list health providers on date with category.
	 * @param */
}
