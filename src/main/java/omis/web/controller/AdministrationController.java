package omis.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for administrative web operations.
 * @author Stephen Abson
 * @version 0.1.0 (Mar 8, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('USER')")
public class AdministrationController {

	/** Instantiates a default controller for a administration. */
	public AdministrationController() {
		// Default instantiation
	}
	
	/**
	 * Shows the administrator index page.
	 * @return model and view to show administrator index page
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/index.html")
	public ModelAndView showAdminIndex() {
		return new ModelAndView("adminIndex");
	}
}