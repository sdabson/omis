package omis.user.web.controller;

import java.util.List;

import omis.user.domain.UserAccount;
import omis.user.report.UserAccountReportService;
import omis.user.report.UserAccountSearchResult;
import omis.user.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for user account related web requests.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 16, 2013)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user/userAccount")
public class UserAccountController {
	
	/* View names. */
	
	private static final String ENTER_USERNAME = "user/enterUsername";
	
	private static final String SEARCH_RESULTS_VIEW_NAME
		= "user/userAccount/json/searchResults";
	
	/* Redirects. */
	
	private static final String WITH_USER_ACCOUNT_REDIRECT
		= "redirect:%s?account=%d";

	/* Model keys. */
	
	private static final String REDIRECT_URL_MODEL_KEY = "redirectUrl";
	
	private static final String USER_ACCOUNT_SEARCH_RESULTS_MODEL_KEY
		= "userAccountSearchResults";

	private static final String NOT_FOUND_USERNAME_MODEL_KEY
		= "notFoundUsername";
	
	/* Services. */
	
	@Autowired
	private UserAccountService userAccountService;

	/* Report services. */
	
	@Autowired
	private UserAccountReportService userAccountReportService;
	
	/* Constructor. */
	
	/** Instantiates a default controller for user accounts. */
	public UserAccountController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Displays a form allowing a user account username to be entered if no
	 * username is specified. Otherwise redirect to the specified URL with
	 * the ID of the account with the specified username passed as a parameter
	 * named {@code account}.
	 * 
	 * @param redirectUrl URL to which to redirect
	 * @param username user account username
	 * @return model and view to enter user account username
	 */
	@PreAuthorize("hasRole('USER_ACCOUNT_SEARCH') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/enterUserAccount.html", method = RequestMethod.GET)
	public ModelAndView showSelectUserAccountPrompt(
			@RequestParam(value = "redirectUrl", required = true)
				final String redirectUrl,
			@RequestParam(value = "username", required = false)
				final String username) {
		if (username == null || username.length() == 0) {
			ModelAndView mav = new ModelAndView(ENTER_USERNAME);
			mav.addObject(REDIRECT_URL_MODEL_KEY, redirectUrl);
			return mav;
		} else {
			UserAccount account = this.userAccountService
					.findByUsername(username);
			if (account != null) {
				return new ModelAndView(String.format(
					WITH_USER_ACCOUNT_REDIRECT, redirectUrl, account.getId()));
			} else {
				ModelAndView mav = new ModelAndView(ENTER_USERNAME);
				mav.addObject(REDIRECT_URL_MODEL_KEY, redirectUrl);
				mav.addObject(NOT_FOUND_USERNAME_MODEL_KEY, username);
				return mav;
			}
		}
	}
	
	/**
	 * Searches user accounts using query, returns results as JSON.
	 * 
	 * @return results as JSON
	 */
	@PreAuthorize("hasRole('USER_ACCOUNT_SEARCH') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.json", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "term", required = true)
				final String query) {
		List<UserAccountSearchResult> userAccountSearchResults
			= this.userAccountReportService.searchForUserAccount(query);
		ModelAndView mav = new ModelAndView(SEARCH_RESULTS_VIEW_NAME);
		mav.addObject(USER_ACCOUNT_SEARCH_RESULTS_MODEL_KEY,
				userAccountSearchResults);
		return mav;
	}
}