package omis.residence.web.controller;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.residence.domain.ResidenceTerm;
import omis.residence.report.ResidenceSearchSummary;
import omis.residence.report.ResidenceSearchSummaryReportService;
import omis.residence.web.form.ResidenceSearchForm;
import omis.residence.web.validator.ResidenceSearchFormValidator;

/**
 * Residence search controller.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 25, 2016)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@RequestMapping("/residence")
public class ResidenceSearchController {

	public ResidenceSearchController() {
		// Default instantiation
	}

	/* View names. */
	
	private static final String VIEW_NAME = "residence/editSearch";
	
	private static final String SEARCH_RESULTS_ACTION_MENU_VIEW_NAME
		= "residence/includes/searchResultsActionMenu";
	
	private static final String SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME
		= "residence/includes/searchCriteriaActionMenu";
	
	private static final String CITY_OPTIONS_VIEW_NAME
		= "residence/includes/searchCityOptions";
	
	/* Model keys. */
	
	private static final String RESIDENCE_SEARCH_SUMMARIES_MODEL_KEY
		= "searchSummaries";
	
	private static final String CITIES_MODEL_KEY = "cities";
	
	private static final String CITY_MODEL_KEY = "city";
	
	private static final String STATES_MODEL_KEY = "states";
	
	private static final String STATE_MODEL_KEY = "state";
	
	private static final String RESIDENCE_SEARCH_FORM_MODEL_KEY 
		= "residenceSearchForm";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("residenceSearchSummaryReportService")
	private ResidenceSearchSummaryReportService 
		residenceSearchSummaryReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("residenceSearchFormValidator")
	private ResidenceSearchFormValidator residenceSearchFormValidator;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("residenceTermPropertyEditorFactory")
	private PropertyEditorFactory residenceTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Residence search form controller.
	 * 
	 * @return model and view of residence search form
	 */
	@RequestMapping(value = "/residenceSearch.html", method = RequestMethod.GET)
	public ModelAndView searchResidence() {
		ResidenceSearchForm residenceSearchForm = new ResidenceSearchForm();
		return this.prepareMav(residenceSearchForm, 
				residenceSearchForm.getState());
	}
	
	/**
	 * Residence search form search results list.
	 * 
	 * @param effectiveDate effective date
	 * @param residenceSearchForm residence search form
	 * @param result result
	 * @return model and view of residence search results
	 */
	@RequestMapping(value = "/residenceSearch.html", 
			method = RequestMethod.POST)
	public ModelAndView search(
			final ResidenceSearchForm residenceSearchForm, 
			final BindingResult result) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		this.residenceSearchFormValidator.validate(residenceSearchForm, result);
		if (result.hasErrors()) {
			mav = this.prepareRedisplayMav(residenceSearchForm, result);
			return mav;
		}
		
		Date effectiveDate;
		if (residenceSearchForm.getEffectiveDate() != null) {
			effectiveDate = residenceSearchForm.getEffectiveDate();
		}  else {
			effectiveDate = new Date();
		}
		List<ResidenceSearchSummary> searchSummaries = 
				new ArrayList<ResidenceSearchSummary>();
		searchSummaries = this.residenceSearchSummaryReportService
				.findResidenceSearchSummary(residenceSearchForm.getValue(),
				residenceSearchForm.getState(), 
				residenceSearchForm.getCity(), effectiveDate);
		mav.addObject(RESIDENCE_SEARCH_SUMMARIES_MODEL_KEY, searchSummaries);
		mav.addObject(CITIES_MODEL_KEY, 
				this.residenceSearchSummaryReportService
				.findCitiesByState(residenceSearchForm.getState()));
		mav.addObject(STATES_MODEL_KEY, 
				this.residenceSearchSummaryReportService.findAllStates());
		mav.addObject(RESIDENCE_SEARCH_FORM_MODEL_KEY, residenceSearchForm);
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
	@RequestMapping(value = "/showSearchCityOptions.html",
			method = RequestMethod.GET)
	public ModelAndView showSearchCityOptions(
			@RequestParam(value = "state", required = true)
				final State state) {
		ModelMap map = new ModelMap();
		map.addAttribute(CITIES_MODEL_KEY, 
				this.residenceSearchSummaryReportService
				.findCitiesByState(state));
	
		return new ModelAndView(CITY_OPTIONS_VIEW_NAME, map);
	}
	
	/* Helper methods. */
	
	//Returns model and view of redisplayed residence search
	private ModelAndView prepareRedisplayMav(
			final ResidenceSearchForm residenceSearchForm, 
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(residenceSearchForm, 
				residenceSearchForm.getState());
		mav.addObject(STATE_MODEL_KEY, residenceSearchForm.getState());
		mav.addObject(CITY_MODEL_KEY, residenceSearchForm.getCity());
		mav.addObject(BindingResult.MODEL_KEY_PREFIX 
				+ RESIDENCE_SEARCH_FORM_MODEL_KEY, result);
		
		return mav;
	}

	//Returns model and view to search for residence
	private ModelAndView prepareMav(
			final ResidenceSearchForm residenceSearchForm, 
			final State state) {		
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(RESIDENCE_SEARCH_FORM_MODEL_KEY, residenceSearchForm);
		if (state != null) {
			mav.addObject(CITIES_MODEL_KEY, 
					this.residenceSearchSummaryReportService
					.findCitiesByState(residenceSearchForm.getState()));
		}
		mav.addObject(STATES_MODEL_KEY, 
				this.residenceSearchSummaryReportService.findAllStates());
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
		binder.registerCustomEditor(ResidenceTerm.class, 
				this.residenceTermPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}