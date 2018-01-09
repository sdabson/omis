package omis.health.web.controller;

import java.util.Date;

import omis.health.report.UnitReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;

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
 * Controller for units.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/unit")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class UnitController {
	
	/* Views. */
	
	private static final String UNIT_INFORMATION_VIEW_NAME
		= "health/unit/js/unitInformation";
	
	/* Model keys. */
	
	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Constructors. */
	
	/** Instantiates a controller for units. */
	public UnitController() {
		// Default instantiation
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Returns a JSON object containing unit information for an offender.
	 * 
	 * @param offender offender
	 * @return JSON object containing unit information for offender
	 */
	@RequestMapping("/findByOffender.json")
	public ModelAndView findByOffender(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		Date currentDate = new Date();
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, currentDate);
		ModelAndView mav = new ModelAndView(UNIT_INFORMATION_VIEW_NAME);
		mav.addObject(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Init Binder.
	 *
	 * @param binder binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}