package omis.search.web.controller;

import java.io.IOException;
import java.util.List;

import omis.search.report.OffenderSearchResult;
import omis.search.report.PersonSearchResult;
import omis.search.report.UserSearchResult;
import omis.search.report.service.OffenderPersonSearchReportService;
import omis.search.report.service.PersonSearchReportService;
import omis.search.report.service.UserPersonSearchReportService;
import omis.user.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** Search for person operations.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2013)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/personSearch")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class PersonSearchController {

	@Autowired
	private PersonSearchReportService personSearchReportService;

	@Autowired
	private OffenderPersonSearchReportService offenderSearchReportService;

	@Autowired
	private UserPersonSearchReportService userSearchReportService;
	
	@Autowired
	private UserAccountService userAccountService;

	/** returns list of person given name search criteria.
	 * @param name1 search criteria.
	 * @param name2 search criteria.
	 * @return collection of person search results.
	 * @throws IOException  */
	@RequestMapping("/searchByName.json")
	public ModelAndView searchByName(
		 @RequestParam(value = "name1", required = true) final String name1,
		 @RequestParam(value = "name2", required = true) final String name2)
        throws IOException {
		final ModelMap map = new ModelMap();

		map.put("people", this.personSearchReportService
				.findPersonNamesByNameSearch(name1, name2));

		return new ModelAndView("search/personSearch/list", map);
	}

	/** returns list of person given unspecified search criteria.
	 * @param searchCriteria search criteria.
	 * @return collection of person search results.
	 * @throws IOException */
	@RequestMapping("/searchByNonSpecified.json")
	@ResponseBody
	public List<PersonSearchResult> searchByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
				final String searchCriteria) throws IOException {
		return this.personSearchReportService.findPersonNamesByUnspecified(searchCriteria);
	}

	/** returns list of offender.
	 * @param searchCriteria search criteria.
	 * @return list of offenders, with related actions.*/
	@RequestMapping("/loadOffender.html")
	public ModelAndView loadOffenderList(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) throws IOException {
		final ModelMap map = new ModelMap();

		map.put("offenders", this.offenderSearchReportService.
				findPersonNamesByUnspecified(searchCriteria));
		return new ModelAndView("search/offenderSearch/includes/listUl", map);
	}

	/** returns list of offenders given unspecified search criteria.
	 * @param searchCriteria search criteria.
	 * @return collection of person search results.
	 * @throws IOException */
	@RequestMapping("/searchOffenderByNonSpecified.json")
	@ResponseBody
	public List<OffenderSearchResult>  searchOffenderByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) throws IOException {

		return this.offenderSearchReportService
				.findPersonNamesByUnspecified(searchCriteria);
	}


	/** returns list of offenders given unspecified search criteria.
	 * @param searchCriteria search criteria.
	 * @return collection of person search results.
	 * @throws IOException
	 * @Deprecated */
	@RequestMapping("/searchOffenderByNonSpecified.html")
	@ResponseBody
	public ModelAndView  searchOffenderByNonSpecifiedhtml(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) throws IOException {
		final ModelMap map = new ModelMap();

		map.put("offenders", this.offenderSearchReportService
					.findPersonNamesByUnspecified(searchCriteria));

		return new ModelAndView("search/offenderSearch/list", map);

	}

	/** returns list of users given unspecified search criteria.
	 * @param searchCriteria sear criteria.
	 * @return collection of person search results. */
	@RequestMapping("/searchUsersByNonSpecified.json")
	@ResponseBody
	public List<UserSearchResult> searchUsersByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) {
		return this.userSearchReportService.findUserPersonNamesByUnspecified(
				searchCriteria);

	}

	/** returns list of users given unspecified search criteria.
	 * @param searchCriteria sear criteria.
	 * @return collection of person search results. */
	@RequestMapping("/searchUsersByNonSpecified.html")
	public ModelAndView searchUsersByNonSpecifiedHtml(
			@RequestParam(value = "searchCriteria", required = true)
			final String searchCriteria) {
		final ModelMap map = new ModelMap();

		map.put("users", this.userSearchReportService
				.findUserPersonNamesByUnspecified(searchCriteria));

		return new ModelAndView("search/userSearch/list", map);
	}


	/*** Returns JSON provided a person.
	 * @param person person.
	 * @return PersonSearchResult caseLoadSearchResult.
	 * @TODO get rid of. */
	@RequestMapping("/person.json")
	@ResponseBody
	public PersonSearchResult findPersonSearchResultByPersonId(
			@RequestParam(value = "person", required = true)
			final Long person) {
		return this.personSearchReportService.findById(person);
	}

	/*** Returns JSON provided a offender.
	 * @param offender offender.
	 * @return offenderSearchResult.
	 * @TODO get rid of.*/
	@RequestMapping("/offender.json")
	@ResponseBody
	public OffenderSearchResult findOffenderSearchResultByPersonId(
			@RequestParam(value = "offender", required = true)
			final Long offender) {
		return this.offenderSearchReportService.findById(offender);
	}

	/*** Returns JSON provided a user.
	 * @param user user.
	 * @return UserSearchResult.
	 * @TODO get rid of.*/
	@RequestMapping("/user.json")
	@ResponseBody
	public UserSearchResult findUserSearchResultByPersonId(
			@RequestParam(value = "user", required = true)
			final Long user) {
		return this.userSearchReportService.findById(user);
	}
	
	/** Returns JSON for current user.
	 * @return userSearchResult user search result. */
	@RequestMapping("/currentUserAssignment.json")
	@ResponseBody
	public UserSearchResult findCurrentUser() {
		System.out.println(SecurityContextHolder.getContext()
		.getAuthentication().getName());
		return this.userSearchReportService.findById(this.userAccountService
				.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName()).getId());
	}
}
