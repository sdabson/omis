package omis.location.search.report;

import java.util.List;

/** Report service for location searches.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0 */
public interface LocationSearchService {
	/** Gets location by address fields.
	 * @param streetNumber - street number.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix. 
	 * @return location search results. */
	List<LocationSearchResult> findByAddressFields(
			String streetNumber, String streetName, String streetSuffix);
	
	/** Gets location by address fields.
	 * @param streetNumber - street number.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix.
	 * @param cityName - city name.
	 * @param stateName - state name.
	 * @param zipCode - zip code.
	 * @return location search results. */
	List<LocationSearchResult> findByAddressFields(
			String streetNumber, String streetName, String streetSuffix,
			String cityName, String stateName, String zipCode);
	
	/** Gets location by unspecified. This will determine which type of search 
	 * to perform and return results if found. If a determination on which 
	 * search to be done, 0 results will be returned.
	 * @param unspecified - unspecified search criteria.
	 * @return location search results. */
	List<LocationSearchResult> findByUnspecified(String unspecified);
	
	/** Gets location by organization name.
	 * @param organizationName - organization name.
	 * @return location search results. */
	List<LocationSearchResult> findByOrganizationName(String organizationName);
	
}
