package omis.health.web.controller;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.health.domain.ProviderAssignmentCategory;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for reporting provider assignments.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/provider/assignment/")
@PreAuthorize("hasRole('ADMIN')" 
		+" or hasRole('HEALTH_ADMIN')"
		+" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ProviderAssignmentReportController {

	/* View names. */

	private static final String LIST_VIEW_NAME = "health/provider/list";

	/* Constructors. */

	/** Instantiates a controller for provider assignments. */
	public ProviderAssignmentReportController() {
		// Default instantiation
	}

	/* Services. */

	/* Property Editor Factories. */



	/* URL invokable methods. */

	/**
	 * Displays a list of provider assignments at a facility on a given date.
	 *
	 * <p>Optionally, the provider assignments can be limited by category.
	 *
	 * @param facility facility
	 * @param date date
	 * @param category category
	 * @return screen displaying list of provider assignments
	 */
	@RequestMapping("list.html")
	public ModelAndView list(
			@RequestParam(value = "facility", required = true)
			final Facility facility,
			@RequestParam(value = "date", required = true)
			final Date date,
			@RequestParam(value = "providerAssignmentCategory", required = true)
			final ProviderAssignmentCategory category) {
		return new ModelAndView(LIST_VIEW_NAME);
	}
}