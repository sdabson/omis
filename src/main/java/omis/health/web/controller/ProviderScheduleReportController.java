package omis.health.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.facility.domain.Facility;

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
 * Controller to report provider schedules.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 10, 2014)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
@RequestMapping("/health/provider/schedule")
public class ProviderScheduleReportController {
	
	/* View names. */
	
	private final static String PROVIDER_SCHEDULE_VIEW_NAME
		= "health/provider/includes/schedule";

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	/* Constructor. */
	
	/** Instantiates a default controller to report provider schedules. */
	public ProviderScheduleReportController() {
		// Default instantiation
	}
	
	/* AJAX methods. */
	
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('HEALTH_PROVIDER_SCHEDULE_VIEW')")
	@RequestMapping("/view.html")
	public ModelAndView schedule(
			@RequestParam(value = "facility", required = true)
				final Facility facility) {
		ModelAndView mav = new ModelAndView(PROVIDER_SCHEDULE_VIEW_NAME);
		return mav;
	}
	
	/* Init binder. */
	
	/** Binds property editors. */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
	}
}