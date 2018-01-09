package omis.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.config.util.PropertyHolder;
import omis.datatype.DateRange;
import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;
import omis.userpreference.service.UserPreferenceService;

/**
 * Controller for application index page.
 *
 * @author Stephen Abson
 * @version 0.1.1 (Feb 14, 2013)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
public class IndexController {

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	@Qualifier("userPreferenceService")
	private UserPreferenceService userPreferenceService;
	
	@Autowired
	@Qualifier("hostingEnvPropertyHolder")
	private PropertyHolder hostingEnvPropertyHolder;
	
	@Autowired
	@Qualifier("helpLinkPropertyHolder")
	private PropertyHolder helpLinkPropertyHolder;

	/** Instantiates a default controller for application index page. */
	public IndexController() {
		// Default instantiation
	}

	/**
	 * Shows index page.
	 *
	 * @param multiScreenMode whether to use multiple screen modes (default)
	 * @return model and view to show index page
	 */
	@RequestMapping(value = "/index.html", method=RequestMethod.GET)
	public ModelAndView index(
			@RequestParam(value = "multiScreenMode", required = false)
				final Boolean multiScreenMode) {
		if (multiScreenMode == null || multiScreenMode == true) {
			return this.prepareIndexMav("multi");
		} else {
			return this.prepareIndexMav("single");
		}
	}

	/**
	 * Shows home page.
	 *
	 * @return home page
	 */
	@RequestMapping("/home.html")
	public ModelAndView home() {
		final ModelMap map = new ModelMap();
		Date weekStartDate = DateRange.findWeeklyRange(
				new Date()).getStartDate();
		map.put("weekStartDate", weekStartDate);
		return new ModelAndView("home", map);
	}

	/**
	 * Shows about page.
	 *
	 * @return about page
	 */
	@RequestMapping("/about.html")
	public ModelAndView about() {
		final Runtime runtime = Runtime.getRuntime();
		final ModelAndView mav = new ModelAndView("about");
		mav.addObject("systemProperties", System.getProperties());
		mav.addObject("freeMemory", runtime.freeMemory());
		mav.addObject("totalMemory", runtime.totalMemory());
		mav.addObject("maxMemory", runtime.maxMemory());
		mav.addObject("availableProcessors", runtime.availableProcessors());
		return mav;
	}

	/**
	 * Shows ascension page.
	 *
	 * @return model and view to show ascension page
	 */
	@RequestMapping("/ascension.html")
	@PreAuthorize("hasRole('APP_DEV')")
	public ModelAndView ascension() {
		return this.prepareIndexMav("ascension");
	}

	// Prepares an index model and view for the specified view name
	private ModelAndView prepareIndexMav(final String viewName) {
		final UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		final ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("userAccount", userAccount);
		mav.addObject("hostingEnvPropertyHolder", hostingEnvPropertyHolder);
		mav.addObject("helpLinkPropertyHolder", helpLinkPropertyHolder);
		return mav;
	}

	/**
	 * Shows menu content.
	 *
	 * @return model and view to show menu content
	 */
	@RequestMapping("/application/launchMenuItems.html")
	public ModelAndView launchMenuContent() {
		final ModelAndView mav
			= new ModelAndView("/application/includes/launchMenuItems");
		return mav;
	}
	
	/** Shows index action menu items.
	 * @return model and view to show action menu items. */
	@RequestMapping("/multi/actionMenu.html")
	public ModelAndView multiActionMenu() {
		final UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		final ModelMap map = new ModelMap();
		map.put("userAccount", userAccount);
		map.addAttribute("userPreference",
				this.userPreferenceService.findByUserAccount());
		final ModelAndView mav = new ModelAndView("/web/multiActionMenu", map);
		return mav;
	}
	
	@RequestMapping("/searchActionMenu.html")
	public ModelAndView showSearchActionMenu() {
		ModelAndView mav = new ModelAndView("includes/searchActionMenu");
		//mav.addObject("search", search);
		return mav;		
	}
	
	@RequestMapping("/caseLoadActionMenu.html")
	public ModelAndView showCaseLoadActionMenu() {
		ModelAndView mav = new ModelAndView("includes/caseLoadActionMenu");
		//mav.addObject("search", search);
		return mav;		
	}
	
	@RequestMapping("/workCentersActionMenu.html")
	public ModelAndView showWorkCenterActionMenu() {
		ModelAndView mav = new ModelAndView("includes/workCentersActionMenu");
		//mav.addObject("search", search);
		return mav;		
	}
	
}