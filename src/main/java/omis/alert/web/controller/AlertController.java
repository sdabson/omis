package omis.alert.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.alert.domain.OffenderAlert;
import omis.alert.domain.component.AlertResolution;
import omis.alert.service.OffenderAlertService;
import omis.alert.web.form.AlertForm;
import omis.alert.web.validator.AlertFormValidator;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for offender alert web requests.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (Dec 11, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/alert")
@PreAuthorize("hasRole('USER')")
public class AlertController   {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "alert/edit";
	
	private static final String LIST_VIEW_NAME = "alert/list";
	
	private static final String ALERTS_ACTION_MENU_VIEW_NAME
		= "alert/includes/alertsActionMenu";
	
	private static final String ALERT_ACTION_MENU_VIEW_NAME
		= "alert/includes/alertActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/alert/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String ALERT_FORM_MODEL_KEY = "alertForm";
	
	private static final String ALERTS_MODEL_KEY = "alerts";

	private static final String ALERT_MODEL_KEY = "alert";
	
	/* Message key. */
	
	private static final String ALERT_EXISTS_MESSAGE_KEY = "alert.exists";
	
	/* Report names. */
	
	private static final String ALERT_LISTING_REPORT_NAME 
		= "/Safety/Alerts/Alert_Listing";

	private static final String ALERT_DETAILS_REPORT_NAME 
		= "/Safety/Alerts/Alert_Details";

	
	/* Report parameter names. */
	
	private static final String ALERT_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String ALERT_DETAILS_ID_REPORT_PARAM_NAME 
		= "ALERT_ID";
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME = "omis.alert.msgs.form";
	
	/* Services. */	

	@Autowired
	private OffenderAlertService offenderAlertService;

	/* Property editor factories. */

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory offenderAlertPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("alertFormValidator")
	private AlertFormValidator alertFormValidator;

	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a controller for alerts. */
	public AlertController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Shows alerts for offender.
	 * 
	 * @param offender offender
	 * @return model and view to a list of offender alerts
	 */
	@RequestContentMapping(nameKey = "alertListingScreenName",
			descriptionKey = "alertListingScreenDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping("/list.html")
	@PreAuthorize("hasRole('ALERT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<OffenderAlert> offenderAlerts = this.offenderAlertService
				.findByOffender(offender);
		mav.addObject(ALERTS_MODEL_KEY, offenderAlerts);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}

	/**
	 * Shows a screen that allows a new alert to be created.
	 * 
	 * @param offender offender for whom to create alert
	 * @return model and view to screen that allows new alert to be created
	 */
	@RequestContentMapping(nameKey = "alertCreateScreenName",
			descriptionKey = "alertCreateScreenDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ALERT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return this.prepareEditMav(new AlertForm(), offender);
	}


	/**
	 * Shows a screen that allows an existing alert to be edited.
	 * 
	 * @param offenderAlert offender alert to edit
	 * @return model and view to screen that allows existing alert to be
	 * edited
	 */
	@RequestContentMapping(nameKey = "alertEditScreenName",
			descriptionKey = "alertEditScreenDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('ALERT_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "alert", required = true)
				final OffenderAlert offenderAlert) {
		AlertForm alertForm = new AlertForm();
		alertForm.setDescription(offenderAlert.getDescription());
		alertForm.setExpireDate(offenderAlert.getExpireDate());
		if (offenderAlert.getResolution() != null) {
			alertForm.setResolveByPerson(
					offenderAlert.getResolution().getResolvedBy());
			alertForm.setResolveDate(offenderAlert
					.getResolution().getDate());
			alertForm.setResolveDescription(offenderAlert
					.getResolution().getDescription());
		}
		ModelAndView mav = this.prepareEditMav(alertForm,
				offenderAlert.getOffender());
		mav.addObject(ALERT_MODEL_KEY, offenderAlert);
		return mav;
	}
	
	/**
	 * Saves a new offender alert.
	 * 
	 * @param offender offender
	 * @param alertForm alert form
	 * @param result binding result
	 * @return redirect to list URL
	 * @throws DuplicateEntityFoundException if the alert already exists
	 */
	@RequestContentMapping(nameKey = "alertCreateSubmitName",
			descriptionKey = "alertCreateSubmitDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ALERT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final AlertForm alertForm,
			final BindingResult result) throws DuplicateEntityFoundException  {
		this.alertFormValidator.validate(alertForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(alertForm, offender, result);
		}
		OffenderAlert offenderAlert = this.offenderAlertService
				.save(offender, alertForm.getExpireDate(),
						alertForm.getDescription(), 
					new AlertResolution(
							alertForm.getResolveDescription(),
							alertForm.getResolveDate(),
							alertForm.getResolveByPerson()));
		return new ModelAndView(String.format(LIST_REDIRECT,
				offenderAlert.getOffender().getId()));
	}

	/**
	 * Saves changes to an existing offender alert.
	 * 
	 * @param offenderAlert alert to update
	 * @param alertForm alert form
	 * @param result binding result
	 * @return redirect to list URL
	 * @throws DuplicateEntityFoundException if the alert already exists
	 */
	@RequestContentMapping(nameKey = "alertEditSubmitName",
			descriptionKey = "alertEditSubmitDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ALERT_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "alert", required = true)
				final OffenderAlert offenderAlert,
			final AlertForm alertForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.alertFormValidator.validate(alertForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(alertForm,
					offenderAlert.getOffender(), result);
			mav.addObject(ALERT_MODEL_KEY, offenderAlert);
			return mav;
		}
		this.offenderAlertService
				.update(offenderAlert, alertForm.getExpireDate(),
						alertForm.getDescription(), 
					new AlertResolution(
							alertForm.getResolveDescription(),
							alertForm.getResolveDate(),
							alertForm.getResolveByPerson()));
		return new ModelAndView(String.format(LIST_REDIRECT,
				offenderAlert.getOffender().getId()));
	}
	
	/**
	 * Removes an offender alert.
	 * 
	 * @param offenderAlert alert to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "alertRemoveName",
			descriptionKey = "alertRemoveDescription",
			messageBundle = "omis.alert.msgs.alert",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('ALERT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "alert", required = true)
				final OffenderAlert offenderAlert) {
		Offender offender = offenderAlert.getOffender();
		this.offenderAlertService.remove(offenderAlert);
		return new ModelAndView(String.format(LIST_REDIRECT,
				offender.getId()));
	}
	
	/* Action menus. */
	
	/**
	 * Returns a view for an alert action menu pertaining to the specified
	 * offender.
	 * 
	 * @param offender person
	 * @return model and view for alert action menu
	 */
	@RequestMapping(value = "/alertActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView alertActionMenu(@RequestParam(value = "offender", 
			required = true) final Person offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(ALERT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for an alerts action menu pertaining to the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @param alert alert
	 * @return model and view for alerts action menu
	 */
	@RequestMapping(value = "/alertsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView alertsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Person offender,
			@RequestParam(value = "alert", required = false)
				final OffenderAlert alert) {
		ModelMap map = new ModelMap();
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		if (alert != null) {
			map.addAttribute(ALERT_MODEL_KEY, alert);
		}
		return new ModelAndView(ALERTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offenders alerts.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/alertListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ALERT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportAlertListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(ALERT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				ALERT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified offenders alerts.
	 * 
	 * @param offenderAlert offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/alertDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ALERT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportAlertDetails(@RequestParam(
			value = "alert", required = true)
			final OffenderAlert offenderAlert,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(ALERT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(offenderAlert.getId()));
		byte[] doc = this.reportRunner.runReport(
				ALERT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param duplicateEntityFoundException duplicate entity found exception
	 * @return model and view to handle duplicate entity found exception
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(ALERT_EXISTS_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, duplicateEntityFoundException);
	}
	
	/* Helper methods. */
	
	// Prepares model and view for redisplay
	private ModelAndView prepareRedisplayMav(
			final AlertForm alertForm, final Offender offender,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(alertForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + ALERT_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	// Model and view prepared to edit alerts
	private ModelAndView prepareEditMav(
			final AlertForm alertForm, final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(ALERT_FORM_MODEL_KEY, alertForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Initializes and configures the property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(OffenderAlert.class, 
				this.offenderAlertPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, 
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
	}
}