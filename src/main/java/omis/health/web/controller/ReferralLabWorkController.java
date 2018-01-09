package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.ProviderAssignment;
import omis.health.web.controller.delegate.LabWorkControllerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Referral lab work controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/labWork")
@PreAuthorize("hasRole('ADMIN')" 
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ReferralLabWorkController {
	
	/* Model keys. */
	
	private static final String DEFAULT_ORDER_DATE_MODEL_KEY 
		= "defaultOrderDate";
	
	private static final String DEFAULT_ORDERED_BY_MODEL_KEY
		= "defaultOrderedBy";
	
	private static final String DEFAULT_SAMPLE_DATE_MODEL_KEY
		= "defaultSampleDate";
	
	private static final String DEFAULT_SAMPLE_LAB_MODEL_KEY
		= "defaultSampleLab";
	
	private static final String DEFAULT_NOTHING_PER_ORAL_MODEL_KEY
		= "defaultNothingPerOral";
	
	private static final String DEFAULT_NO_LEAKY_MODEL_KEY
		= "defaultNoLeaky";
	
	private static final String DEFAULT_NO_MEDS_MODEL_KEY = "defaultNoMeds";

	/* Property Editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("labWorkControllerDelegate")
	private LabWorkControllerDelegate labWorkControllerDelegate;
	
	/* Constructor. */

	/**
	 * Instantiates a default instance of referral lab work controller.
	 */
	public ReferralLabWorkController() {
		//Default constructor.
	}
	
	/**
	 * Add lab work appointment item.
	 * 
	 * @param index current lab work index
	 * @param facility facility
	 * @param defaultOrderDate default order date selection
	 * @param defaultOrderedBy default ordered by selection 
	 * @param defaultSampleDate default sample date selection
	 * @param defaultSampleLab default sample lab selection
	 * @param defaultNothingPerOral default nothing per oral
	 * @param defaultNoLeaky default no leaky
	 * @param defaultNoMeds default no meds
	 * @return model and view for lab work appointment item row
	 */
	@RequestContentMapping(nameKey = "addLabWorkerScreenName",
			descriptionKey = "addLabWorkScreenDescription",
			messageBundle = "omis.heath.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/addLabWorkItem.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('REFERRAL_LAB_WORK_SCHEDULE_VIEW')")
	public ModelAndView addLabWork(
			@RequestParam(value = "labWorkIndex", required = true)
			final int index, @RequestParam(value = "facility",
			required = true)final Facility facility,
			@RequestParam(value = "defaultOrderDate", 
			required = false) final Date defaultOrderDate, 
			@RequestParam(value = "defaultOrderedBy", required = false) 
			final ProviderAssignment defaultOrderedBy, 
			@RequestParam(value = "defaultSampleDate", required = false) 
			final Date defaultSampleDate, 
			@RequestParam(value = "defaultSampleLab", required = false) 
			final Lab defaultSampleLab,
			@RequestParam(value = "defaultNothingPerOral", required = false)
			final Boolean defaultNothingPerOral,
			@RequestParam(value = "defaultNoLeaky", required = false)
			final Boolean defaultNoLeaky,
			@RequestParam(value = "defaultNoMeds", required = false)
			final Boolean defaultNoMeds) {
		final ModelMap map = new ModelMap();
		map.addAttribute(this.labWorkControllerDelegate
				.getLabWorkIndexModelKey(), index);
		this.labWorkControllerDelegate.prepareLabWorkItemRow(map);
		if (defaultOrderDate != null) {
			map.addAttribute(DEFAULT_ORDER_DATE_MODEL_KEY, defaultOrderDate);
			map.addAttribute(this.labWorkControllerDelegate
					.getProvidersModelKey(), this.labWorkControllerDelegate
					.findProviders(facility, defaultOrderDate));
			if (defaultOrderedBy != null) {
				map.addAttribute(DEFAULT_ORDERED_BY_MODEL_KEY, 
						defaultOrderedBy);
			}
		}
		map.addAttribute(DEFAULT_SAMPLE_DATE_MODEL_KEY, defaultSampleDate);
		map.addAttribute(DEFAULT_SAMPLE_LAB_MODEL_KEY, defaultSampleLab);
		map.addAttribute(DEFAULT_NOTHING_PER_ORAL_MODEL_KEY, 
				defaultNothingPerOral);
		map.addAttribute(DEFAULT_NO_LEAKY_MODEL_KEY, defaultNoLeaky);
		map.addAttribute(DEFAULT_NO_MEDS_MODEL_KEY, defaultNoMeds);
		return new ModelAndView(this.labWorkControllerDelegate
				.getLabWorkItemViewName(), map);
	}
	
	/**
	 * Returns the provider assignments and view needed to add providers for
	 * selection when creating a new lab work within a referral.
	 * 
	 * @param facility facility
	 * @param orderDate order date
	 * @return model and view
	 */
	@RequestMapping(value = "/addOrderedByOptions.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('REFERRAL_LAB_WORK_SCHEDULE_VIEW')")
	public ModelAndView addOrderedByOptions(@RequestParam(value = "facility",
			required = true)final Facility facility, 
			@RequestParam(value = "date", required = true) 
			final Date orderDate) {
		ModelMap map = new ModelMap();
		List<ProviderAssignment> providers = this.labWorkControllerDelegate
				.findProviders(facility, orderDate);
		map.addAttribute(this.labWorkControllerDelegate
				.getProvidersModelKey(), providers);
		return new ModelAndView(this.labWorkControllerDelegate
				.getOrderedByOptionsViewName(), map);
	}
	
	/**
	 * Returns the provider assignments and view needed to add providers for
	 * selection when creating a new lab work within a referral.
	 * 
	 * @param facility facility
	 * @param orderDate order date
	 * @return model and view
	 */
	@RequestMapping(value = "/addDefaultOrderedByOptions.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('REFERRAL_LAB_WORK_SCHEDULE_VIEW')")
	public ModelAndView addDefaultOrderedByOptions(@RequestParam(value 
			= "facility", required = true)final Facility facility, 
			@RequestParam(value = "date", required = true) 
			final Date orderDate) {
		ModelMap map = new ModelMap();
		List<ProviderAssignment> providers = this.labWorkControllerDelegate
				.findProviders(facility, orderDate);
		map.addAttribute(this.labWorkControllerDelegate
				.getProvidersModelKey(), providers);
		return new ModelAndView(this.labWorkControllerDelegate
				.getOrderedByOptionsViewName(), map);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
	}
}