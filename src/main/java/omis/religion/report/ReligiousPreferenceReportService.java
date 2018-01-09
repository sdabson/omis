package omis.religion.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Service for reporting religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousPreferenceReportService {

	/**
	 * Returns summaries of religious preferences by offender.
	 * 
	 * @param offender offender
	 * @return summaries of religious preferences by offender
	 */
	List<ReligiousPreferenceSummary> findSummariesByOffender(Offender offender);
	
	/**
	 * Returns accommodation names.
	 * 
	 * @return accommodation names
	 */
	List<String> findAccommodationNames();
}