package omis.offender.web.controller;

import java.util.Date;

import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
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
 * Offender module controller.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 26, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offender")
@PreAuthorize("hasRole('USER')")
public class OffenderModuleController {

	/* Model keys. */
	
	private static final String OFFENDER_SUMMARY_MODEL_KEY = "offenderSummary";
	
	/* View names. */
	
	private static final String MODULE_VIEW_NAME = "offender/modules";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/** Instantiates a default controller for offender module. */
	public OffenderModuleController() {
		// Default instantiation
	}
	
	/**
	 * Shows offender module screen.
	 * @param offender - offender. 
	 * @return offender module screen
	 */
	@PreAuthorize("hasRole('OFFENDER_MODULES_VIEW') or hasRole('ADMIN')")
	@RequestMapping("/modules.html")
	public ModelAndView modules(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(MODULE_VIEW_NAME);
		this.addOffenderSummary(mav, offender);
		return mav;
	}
	
	// Adds offender summary to a model and view
	private void addOffenderSummary(
			final ModelAndView mav, final Offender offender) {
		OffenderSummary offenderSummary = this.offenderReportService
				.summarizeOffender(offender, new Date());
		mav.addObject(OFFENDER_SUMMARY_MODEL_KEY, offenderSummary);
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