package omis.employment.report;

import java.util.Date;
import java.util.List;

import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Employer summary report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 4, 2016)
 * @since OMIS 3.0
 */
public interface EmployerSearchSummaryReportService {
	
	/**
	 * List of cities by state.
	 * 
	 * @param state state
	 * @return list of cities
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * List of states.
	 * 
	 * @return list of states
	 */
	List<State> findAllStates();
	
	/**
	 * Employer search summary list.
	 * 
	 * @param employerLocationOrganizationName employer location 
	 * organization name
	 * @param city city
	 * @return summary of employer search
	 */
	List<EmployerSearchSummary> search(String employerLocationOrganizationName, 
			City city, State state, Date effectiveDate);
}