package omis.locationterm.web.controller;

import java.util.Date;

import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for location term profile.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 13, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/locationTerm")
@PreAuthorize("hasRole('USER')")
public class LocationTermProfileController {

	/* View names. */
	
	private static final String PROFILE_VIEW_NAME = "locationTerm/profile";
	
	/* Model keys. */
	
	/* Report Service. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	/* Helpers. */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/** Instantiates a controller for location term profile. */
	public LocationTermProfileController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows module profile screen.
	 * 
	 * @param offender offender
	 * @return module profile screen
	 */
	@PreAuthorize("hasRole('LOCATION_TERM_PROFILE_VIEW') or hasRole('ADMIN')")
	@RequestMapping("/profile.html")
	public ModelAndView profile(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		Date currentDate = new Date();
		ModelAndView mav = new ModelAndView(PROFILE_VIEW_NAME);
		addOffenderSummary(mav, offender, currentDate);
		return mav;
	}
	
	// Adds offender summary to a model and view
	private void addOffenderSummary(
			final ModelAndView mav, final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	}
	
	/* Init binder. */

	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}