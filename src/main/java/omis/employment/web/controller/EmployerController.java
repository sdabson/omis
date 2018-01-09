package omis.employment.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.employment.domain.Employer;
import omis.employment.report.EmployerSearchSummary;
import omis.employment.report.EmployerSearchSummaryReportService;
import omis.employment.web.form.SearchEmployerForm;
import omis.employment.web.validator.EmployerSearchFormValidator;
import omis.region.domain.City;
import omis.region.domain.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Employer search controller.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 16, 2016)
 * @since OMIS 3.0 
 */
@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@RequestMapping("/employer")
public class EmployerController {
	
	public EmployerController() {
		// Default instantiation
	}
	
	/* View names. */
	
	private static final String VIEW_NAME = "employment/employer/editSearch";
	
	private static final String SEARCH_RESULTS_ACTION_MENU_VIEW_NAME
		= "employment/employer/includes/searchResultsActionMenu";
	
	private static final String SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME
		= "employment/employer/includes/searchCriteriaActionMenu";
	
	private static final String CITY_OPTIONS_VIEW_NAME 
		= "employment/employer/includes/cityOptions";
	
	/* Model keys. */
	private static final String EMPLOYER_SEARCH_SUMMARIES_MODEL_KEY 
		= "searchSummaries";
	
	private static final String CITIES_MODEL_KEY = "cities";
	
	private static final String CITY_MODEL_KEY = "city";
	
	private static final String STATES_MODEL_KEY = "states";
	
	private static final String STATE_MODEL_KEY = "state";
	
	private static final String EFFECTIVE_DATE_MODEL_KEY = "effectiveDate";
	
	private static final String EMPLOYER_SEARCH_FORM_MODEL_KEY 
		= "employerSearchForm";	
	
	/* Report services. */
	
	@Autowired
	@Qualifier("employerSummaryReportService")
	private EmployerSearchSummaryReportService employerSummaryReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("employerSearchFormValidator")
	private EmployerSearchFormValidator employerSearchFormValidator;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("employerPropertyEditorFactory")
	private PropertyEditorFactory employerPropertyEditorFactory;	

	/**
	 * Employer search form.
	 * 
	 * @return model and view of employer search form
	 */
	@RequestMapping(value = "/employerSearch.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EMPLOYER_LIST') or hasRole('ADMIN')")
	public ModelAndView searchEmployer() {
		SearchEmployerForm employerSearchForm = new SearchEmployerForm();
		return this.prepareMav(employerSearchForm, 
				employerSearchForm.getState());
	}

	/**
	 * Employer search form search results list.
	 * 
	 * @param effectiveDate effective date
	 * @param employerSearchForm employer search form
	 * @param result result
	 * @return model and view of employer search results
	 */
	@RequestMapping(value = "/employerSearch.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('EMPLOYER_LIST') or hasRole('ADMIN')")
	public ModelAndView search(
			@RequestParam(value = "effectiveDate", required = false) 
			final Date effectiveDate, 
			final SearchEmployerForm employerSearchForm,
			final BindingResult result) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		this.employerSearchFormValidator.validate(employerSearchForm, result);
		if (result.hasErrors()) {
			mav = this.prepareRedisplayMav(employerSearchForm, result);
			return mav;
		}
		Date queryDate = new Date();
		if (effectiveDate != null) {
			queryDate = effectiveDate;
		} 
		List<EmployerSearchSummary> searchSummaries = 
				new ArrayList<EmployerSearchSummary>();
		searchSummaries = this.employerSummaryReportService.search(
				employerSearchForm.getEmployerName(), 
				employerSearchForm.getCity(), employerSearchForm.getState(),
				queryDate);
		mav.addObject(EMPLOYER_SEARCH_SUMMARIES_MODEL_KEY, searchSummaries);
		mav.addObject(CITIES_MODEL_KEY, this.employerSummaryReportService
				.findCitiesByState(employerSearchForm.getState()));
		mav.addObject(STATES_MODEL_KEY, 
				this.employerSummaryReportService.findAllStates());
		mav.addObject(EFFECTIVE_DATE_MODEL_KEY, effectiveDate);
		mav.addObject(EMPLOYER_SEARCH_FORM_MODEL_KEY, employerSearchForm);
		return mav;
	}
	
	/**
	 * Search criteria action menu.
	 * 	
	 * @return model and view
	 */
	@RequestMapping("/searchCriteriaActionMenu.html")
	public ModelAndView showSearchCriteriaActionMenu() {
		ModelAndView mav = new ModelAndView(
				SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME);	
		return mav;
	}
	
	/**
	 * Search results action menu.
	 * 
	 * @return model and view
	 */
	@RequestMapping("/searchResultsActionMenu.html")
	public ModelAndView showSearchResultsActionMenu() {
		ModelAndView mav = new ModelAndView(
				SEARCH_RESULTS_ACTION_MENU_VIEW_NAME);		
		return mav;	
	}	
	
	/**
	 * Show city options by state.
	 * 
	 * @param state state
	 * @return model and view
	 */
	@RequestMapping(value = "/showCityOptions.html",
			method = RequestMethod.GET)
	public ModelAndView showCityOptions(
			@RequestParam(value = "state", required = true)
				final State state) {
		ModelMap map = new ModelMap();
		map.addAttribute(CITIES_MODEL_KEY, 
				this.employerSummaryReportService.findCitiesByState(state));
	
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/* Helper methods. */
	
	//Returns model and view to search for employer
	private ModelAndView prepareMav(
			final SearchEmployerForm employerSearchForm, final State state) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(EMPLOYER_SEARCH_FORM_MODEL_KEY, employerSearchForm);
		if (state != null) {
			mav.addObject(CITIES_MODEL_KEY, this.employerSummaryReportService
					.findCitiesByState(employerSearchForm.getState()));
		}
		mav.addObject(STATES_MODEL_KEY, this.employerSummaryReportService
				.findAllStates());
		return mav;
	}
	
	//Returns model and view of redisplayed employer search
	private ModelAndView prepareRedisplayMav(final SearchEmployerForm form,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(form, form.getState());
		mav.addObject(STATE_MODEL_KEY, form.getState());
		mav.addObject(CITY_MODEL_KEY, form.getCity());
		mav.addObject(BindingResult.MODEL_KEY_PREFIX 
				+ "employerSearchForm", result);
		return mav;
	}
	
	/* Init binder. */

	/**
	 * Init binder.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(City.class, 
				this.cityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Employer.class, 
				this.employerPropertyEditorFactory
				.createPropertyEditor());
	}	
}