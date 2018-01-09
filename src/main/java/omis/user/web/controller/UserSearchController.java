package omis.user.web.controller;

import java.util.List;

import omis.user.report.UserReportService;
import omis.user.report.UserSummary;
import omis.user.web.form.UserSearchForm;
import omis.user.web.form.UserSearchType;
import omis.user.web.validator.UserSearchFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to search for users.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 6, 2015)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserSearchController {

	/* View names. */
	
	private static final String VIEW_NAME = "user/search";
	
	/* Model keys. */
	
	private static final String USER_SEARCH_FORM_MODEL_KEY = "userSearchForm";

	private static final String USER_SUMMARIES_MODEL_KEY = "userSummaries";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("userReportService")
	private UserReportService userReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("userSearchFormValidator")
	private UserSearchFormValidator userSearchFormValidator;
	
	/* Constructors. */
	
	/** Instantiates controller to search for users. */
	public UserSearchController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to search for users.
	 * 
	 * @param searchUserForm form to search for users.
	 * @return screen to search for users
	 */
	@PreAuthorize("hasRole('USER_SEARCH') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView search() {
		UserSearchForm searchUserForm = new UserSearchForm();
		searchUserForm.setType(UserSearchType.NAME);
		return this.prepareMav(searchUserForm);
	}
	
	/**
	 * Searches for users.
	 * 
	 * @param userSearchForm form to search for users.
	 * @param result binding result
	 * @return screen showing search results
	 */
	@PreAuthorize("hasRole('USER_SEARCH') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.POST)
	public ModelAndView performSearch(
			final UserSearchForm userSearchForm,
			final BindingResult result) {
		this.userSearchFormValidator.validate(userSearchForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(userSearchForm, result);
		}
		List<UserSummary> userSummaries;
		if (UserSearchType.NAME.equals(userSearchForm.getType())) {
			userSummaries = this.userReportService
					.summarizeByName(userSearchForm.getLastName(),
							userSearchForm.getFirstName());
		} else if (UserSearchType.USERNAME.equals(userSearchForm.getType())) {
			userSummaries = this.userReportService
					.summarizeByUsername(userSearchForm.getUsername());
		} else {
			throw new UnsupportedOperationException(String.format(
					"Unknown search type %s", userSearchForm.getType()));
		}
		ModelAndView mav = this.prepareMav(userSearchForm);
		mav.addObject(USER_SUMMARIES_MODEL_KEY, userSummaries);
		return mav;
	}
	
	/* Helper methods. */
	
	// Returns model and view to search for users
	private ModelAndView prepareMav(
			final UserSearchForm userSearchForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(USER_SEARCH_FORM_MODEL_KEY, userSearchForm);
		return mav;
	}
	
	// Returns model and view to redisplay form to search for user
	private ModelAndView prepareRedisplayMav(
			final UserSearchForm userSearchForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(userSearchForm);
		mav.addObject(
				BindingResult.MODEL_KEY_PREFIX + USER_SEARCH_FORM_MODEL_KEY,
				result);
		return mav;
	}
}