package omis.user.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for user administration related operations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 27, 2013)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user/admin")
public class UserAdminController {

	private static final String USER_ADMIN_INDEX_VIEW_NAME = "user/admin/index";
	
	/** Instantiates a default controller for user administration. */
	public UserAdminController() {
		// Default instantiation
	}
	
	/**
	 * Shows the user administration index page.
	 * 
	 * @return model and view to user administration page
	 */
	@PreAuthorize("hasRole('USER_ADMIN_INDEX_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		return new ModelAndView(USER_ADMIN_INDEX_VIEW_NAME);
	}
}