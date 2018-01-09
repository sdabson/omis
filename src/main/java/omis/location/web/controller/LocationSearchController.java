package omis.location.web.controller;

import java.util.List;

import omis.location.search.report.LocationSearchResult;
import omis.location.search.report.LocationSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** Controller for location related searches.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/location/search")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class LocationSearchController {
	@Autowired
	private LocationSearchService locationSearchService;
	
	/** Returns list of location search results given unspecified search
	 * criteria.
	 * @param searchCriteria - search criteria.
	 * @return collection of location search results. */
	@RequestMapping("/searchByUnspecified.json")
	@ResponseBody
	public List<LocationSearchResult> searchByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) {
		return this.locationSearchService.findByUnspecified(searchCriteria);
	}

}
