package omis.adaaccommodation.report;

import java.util.List;

import omis.adaaccommodation.domain.Accommodation;

/**
 * Accommodation issuance report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationIssuanceReportService {

	/**
	 * List summary of accommodation issuance by accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return accommdation issuance summary
	 */
	List<AccommodationIssuanceSummary> findByAccommodation(
			Accommodation accommodation);
}