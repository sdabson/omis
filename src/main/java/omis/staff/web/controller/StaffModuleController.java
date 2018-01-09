package omis.staff.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for staff module.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize(
		"(hasRole('STAFF_MODULE') and hasRole('USER')) or hasRole('ADMIN')")
@RequestMapping("/staff")
public class StaffModuleController {

	private static final String INDEX_VIEW_NAME = "staff/index";

	/** Instantiates a controller for staff module. */
	public StaffModuleController() {
		// Default instantiation
	}
	
	/**
	 * Returns index screen.
	 * 
	 * @return index screen
	 */
	@RequestMapping("/index.html")
	public ModelAndView index() {
		return new ModelAndView(INDEX_VIEW_NAME);
	}
}