package omis.residence.report;

import java.util.Date;
import java.util.List;

import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Summary service for residence searches. 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 25, 2016)
 * @since OMIS 3.0
 */

public interface ResidenceSearchSummaryReportService {
	
	/**
	 * Residence search summary.
	 * 
	 * @param number number
	 * @param streetName street name
	 * @param state state
	 * @param city city
	 * @param effectiveDate effective date
	 * @return summary of residences searched
	 */
	List<ResidenceSearchSummary> findResidenceSearchSummary(String value, State state, City city, Date effectiveDate);
	
	/**
	 * Find list of cities based on state.
	 * 
	 * @param state state
	 * @return cities
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Find a list of all states.
	 * 
	 * @return states
	 */
	List<State> findAllStates();
}