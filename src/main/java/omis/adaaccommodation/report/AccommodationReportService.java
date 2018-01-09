package omis.adaaccommodation.report;

import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.offender.domain.Offender;

/**
 * Accommodation report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationReportService {	
	
	/**
	 * Summaries the accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return accommodation summary
	 */
	AccommodationSummary summarize(Accommodation accommodation);
	
	/**
	 * Finds summary of accommodations by offender.
	 * 
	 * @param offender offender
	 * @return accommodation summary list
	 */
	List<AccommodationSummary> findByOffenderAccommodation(Offender offender);

	/**
	 * Finds summary of issuances associated with accommodation.
	 * 
	 * @param accommodation accommodation
	 * @return issuances summary list
	 */
	Boolean hasIssuances(Accommodation accommodation);
}