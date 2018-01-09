package omis.employment.web.controller;

import java.io.IOException;
import java.util.List;

import omis.search.report.service.EmployerSearchService;
import omis.search.report.EmployerSearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** Search for employer operations.
 * @author Yidong Li
 * @version 0.1.0 (Feb 26, 2015)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/employerSearch")
@PreAuthorize("hasRole('EMPLOYER_LIST') or hasRole('ADMIN')")
public class EmployerSearchController {

	@Autowired
	private EmployerSearchService employerSearchService;

	/** returns employer given name search criteria.
	 * @param name employer name.
	 * @return employer.
	 * @throws IOException  */
	@RequestMapping("/searchByNonSpecified.json")
	@PreAuthorize("hasRole('EMPLOYER_LIST') or hasRole('ADMIN')")
	@ResponseBody
	public List<EmployerSearchResult> searchByName(
		  @RequestParam(value = "searchCriteria", required = true) 
		 	final String searchCriteria) throws IOException {
			List<EmployerSearchResult> employerSearchResults 
				= this.employerSearchService
				.findEmployerByUnspecified(searchCriteria);
			return employerSearchResults;
	}
}
