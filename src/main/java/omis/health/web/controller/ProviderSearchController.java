package omis.health.web.controller;

import omis.health.web.form.ProviderSearchForm;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cib168
 *
 */
@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('APP_DEV')")
@RequestMapping("/health/provider/")
public class ProviderSearchController {

	public ProviderSearchController() {
		// Default instantiation
	}
	
	@RequestMapping(value = "/providerSearch.html", method = RequestMethod.GET)
	public ModelAndView searchprovider() {
		ProviderSearchForm providerSearchForm = new ProviderSearchForm();
		
		return this.prepareMav(providerSearchForm);
	}

	private ModelAndView prepareMav(
			final ProviderSearchForm providerSearchForm){
		ModelAndView mav = new ModelAndView("health/provider/editSearch");
		mav.addObject("providerSearchForm", providerSearchForm);
		return mav;
	}
	
	@RequestMapping(value = "/providerSearch.html", method = RequestMethod.POST)
	public ModelAndView search() {
	
		return null;
	}
	
	@RequestMapping("/searchCriteriaActionMenu.html")
	public ModelAndView showSearchCriteriaActionMenu() {
		ModelAndView mav = new ModelAndView(
				"includes/searchCriteriaActionMenu");
	
		return mav;
	}
	
	@RequestMapping("/searchResultsActionMenu.html")
	public ModelAndView showSearchResultsActionMenu() {
		ModelAndView mav = new ModelAndView("includes/searchResultsActionMenu");
		
		return mav;	
	}	

}