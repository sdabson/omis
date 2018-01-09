package omis.physicalfeature.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Physical feature summary report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 6, 2016)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureSummaryReportService {

	/**
	 * Find summary of physical features by offender.
	 *
	 *
	 * @param offender offender
	 * @return list of physical features
	 */
	List<PhysicalFeatureSummary> findByOffender(Offender offender);
}