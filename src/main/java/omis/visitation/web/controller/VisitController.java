package omis.visitation.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.locationterm.domain.LocationTerm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitMethod;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.component.VisitFlags;
import omis.visitation.service.VisitService;
import omis.visitation.validator.VisitFormValidator;
import omis.visitation.validator.VisitorCheckInFormValidator;
import omis.visitation.web.form.VisitForm;
import omis.visitation.web.form.VisitorCheckInForm;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Visit controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 6, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/visitation/visit")
@PreAuthorize("hasRole('USER')")
public class VisitController {

	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL_FORMAT = 
			"redirect:../list.html?offender=%d";
	
	private static final String DATE_FORMAT = "%1$tm/%1$td/%1$tY";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "visitation/visit/edit";
	
	private static final String VISIT_ACTION_MENU_VIEW_NAME 
		= "visitation/visit/includes/visitActionMenu";
	
	private static final String VISITS_ACTION_MENU_VIEW_NAME
		= "visitation/visit/includes/visitsActionMenu";
	
	private static final String VISITATION_ASSOCIATION_OPTIONS_VIEW_NAME
		= "visitation/visit/includes/visitationAssociationOptions";
	
	private static final String VISIT_ROW_ACTION_MENU_VIEW_NAME
		= "visitation/visit/includes/visitRowActionMenu";
	
	/* Services. */
	
	@Autowired
	@Qualifier("visitService")
	private VisitService visitService;
	
	/* Model Keys. */
	
	private static final String VISIT_MODEL_KEY = "visit";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String VISITOR_CHECK_IN_FORM_MODEL_KEY
		= "visitorCheckInForm";
	
	private static final String VISIT_FORM_MODEL_KEY = "visitForm";
	
	private static final String VISITATION_ASSOCIATIONS_MODEL_KEY
		= "visitationAssociations";
	
	private static final String VISITATION_ASSOCIATION_MODEL_KEY
		= "visitationAssociation";
	
	private static final String VISIT_METHODS_MODEL_KEY = "visitMethods";
	
	private static final String VISITOR_MODEL_KEY = "visitor";
	
	private static final String CURRENTLY_VISITING_MODEL_KEY
		= "currentlyVisiting";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("visitPropertyEditorFactory")
	private PropertyEditorFactory visitPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("visitationAssociationPropertyEditorFactory")
	private PropertyEditorFactory visitationAssociationPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("visitFormValidator")
	private VisitFormValidator visitFormValidator;
	
	@Autowired
	@Qualifier("visitorCheckInFormValidator")
	private VisitorCheckInFormValidator visitorCheckInFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
/* Report names. */
	
	private static final String VISIT_DETAILS_REPORT_NAME 
		= "/Relationships/Visitation/Visitation_Details_Visitor_Log";

	/* Report parameter names. */
	
	private static final String VISIT_DETAILS_ID_REPORT_PARAM_NAME 
		= "VISIT_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.visitation.msgs.form";
	
	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_EXCEPTION_MESSAGE_KEY
		= "duplicateVisitFound";
	private static final String DATE_CONFLICT_MESSAGE_KEY
		= "conflictingVisitDates";
	
	/**
	 * Instantiates a default instance of visit controller.
	 */
	public VisitController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Prepares a model and view for creating a visit for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @return model and view for creating a visit
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(value = "offender",
			required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		VisitForm form = new VisitForm();
		form.setVisitMethod(VisitMethod.PHYSICALLY_PRESENT);
		map.addAttribute(VISIT_FORM_MODEL_KEY, form);
		return this.prepareEditMav(map, form, offender);
	}
	
	/**
	 * Creates a visit based on the specified visitation association.
	 * 
	 * @param visitationAssociation visitation association
	 * @param form badge number form
	 * @param result binding result
	 * @return model and view to redirect to list screen
	 * @throws DateConflictException thrown when a visit containing the
	 * specified visitation association overlaps with current date and time
	 * @throws DuplicateEntityFoundException thrown when a duplicate visit
	 * is found
	 */
	@RequestMapping(value="/checkIn.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VISIT_EDIT') or hasRole('ADMIN')")
	public ModelAndView checkIn(
			@RequestParam(value = "visitationAssociation", required = true)
			final VisitationAssociation visitationAssociation,
			@RequestParam(value = "date", required = false) final Date date,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "effectiveDate", required = false)
				final Date endDate,
			final VisitorCheckInForm form, final BindingResult result,
			final RedirectAttributes redirectAttributes)
		throws DuplicateEntityFoundException, DateConflictException {
		Offender offender = (Offender) visitationAssociation.getRelationship()
				.getFirstPerson();
		this.visitorCheckInFormValidator.validate(form, result);
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(
					VISITOR_CHECK_IN_FORM_MODEL_KEY, form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ VISITOR_CHECK_IN_FORM_MODEL_KEY, result);
			redirectAttributes.addFlashAttribute(VISITATION_ASSOCIATION_MODEL_KEY,
					visitationAssociation);
			return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
					offender.getId()));
		}
		//lookup current location term
		LocationTerm locationTerm = this.visitService
				.findLocationTermByOffenderOnDate(offender, new Date());
		
		if (locationTerm != null) {
			//When visitation methods are able to be entered, change the value
			//in the visit method to the form's visit method 
			this.visitService.log(visitationAssociation, new Date(), new Date(), 
				null, form.getBadgeNumber(), 
				new VisitFlags(false, false, VisitMethod.PHYSICALLY_PRESENT),
				form.getNotes(), locationTerm.getLocation());
		} else {
			throw new UnsupportedOperationException("Location required");
		}
		return this.prepareListMav(offender, date, startDate, endDate);
	}
	
	/*
	 * Prepares model and view for list screen.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param startDate start date
	 * @param endDate end date
	 */
	private ModelAndView prepareListMav(final Offender offender,
			final Date date, final Date startDate, final Date endDate) {
		return prepareListRedirect(offender.getId(), date, startDate, endDate);
	}
	
	/**
	 * Ends the specified visit.
	 * 
	 * @param visit visit
	 * @return redirect to visitation association listing screen
	 * @throws DateConflictException thrown when a visit with a conflicting
	 * start and end date exists
	 * @throws DuplicateEntityFoundException thrown when a duplicate visit is
	 * found
	 */
	@RequestMapping(value="/checkOut.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_EDIT') or hasRole('ADMIN')")
	public ModelAndView checkOut(
			@RequestParam(value = "visit", required = true)
				final Visit visit,
			@RequestParam(value = "date", required = false)final Date date,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "endDate", required = false)
				final Date endDate)
		throws DateConflictException, DuplicateEntityFoundException {
		this.visitService.endVisit(visit, new Date());
		return this.prepareListRedirect(visit.getVisitationAssociation()
				.getRelationship().getFirstPerson().getId(),
				date, startDate, endDate); 
	}
	
	/**
	 * Creates a visit for the specified visitation association, with
	 * information from the specified visit form.
	 * 
	 * @param visitationAssociation visitation association
	 * @param form visit form
	 * @param result binding result
	 * @return model and view for visitation list
	 * @throws DateConflictException thrown when a visit containing the
	 * specified visitation association overlaps with current date and time
	 * @throws DuplicateEntityFoundException thrown when a duplicate visit
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VISIT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
		@RequestParam(value = "offender", required = true)
			final Offender offender,
		final VisitForm form,final BindingResult result)
		throws DuplicateEntityFoundException, DateConflictException {
		this.visitFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(VISIT_FORM_MODEL_KEY, form);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ VISIT_FORM_MODEL_KEY, result);
			return this.prepareEditMav(map, form, offender);
		}
		this.visitService.findLocationTermByOffenderOnDate(
				offender, form.getDate());
		//TODO: change visit method to form's when ready.
		this.visitService.log(form.getVisitationAssociation(), form.getDate(),
				form.getStartTime(), form.getEndTime(), form.getBadgeNumber(),
				new VisitFlags(form.getDeniedByStaff(),
						form.getRefusedByOffender(), 
						VisitMethod.PHYSICALLY_PRESENT),
				form.getNotes(), this.visitService
				.findLocationTermByOffenderOnDate(offender, new Date())
				.getLocation());
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				offender.getId()));
	}
	
	/**
	 * Prepares a model and view for editing the specified visit.
	 * 
	 * @param visit visit
	 * @return model and view for editing a visit
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "visit", required = true)
			final Visit visit) {
		ModelMap map = new ModelMap();
		VisitForm form = new VisitForm();
		this.prepareEditForm(form, visit);
		map.addAttribute(VISIT_MODEL_KEY, visit);
		map.addAttribute(VISIT_FORM_MODEL_KEY, form);
		return this.prepareEditMav(map, form,
				(Offender) visit.getVisitationAssociation().getRelationship()
				.getFirstPerson());
	}
	
	/**
	 * Updates the specified visit with information from the specified
	 * visit form.
	 * 
	 * @param visit visit
	 * @param form visit form
	 * @param result binding result
	 * @return model and view for visitation list screen
	 * @throws DateConflictException thrown when the specified visit overlaps
	 * another 
	 * @throws DuplicateEntityFoundException thrown when the specified
	 * visit another existing visit 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VISIT_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(value = "visit", required = true)
			final Visit visit, final VisitForm form,
			final BindingResult result)
		throws DuplicateEntityFoundException, DateConflictException {
		this.visitFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(VISIT_FORM_MODEL_KEY, form);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ VISIT_FORM_MODEL_KEY, result);
			return this.prepareEditMav(map, form,
					(Offender) visit.getVisitationAssociation()
					.getRelationship().getFirstPerson());
		}
		this.visitService.findLocationTermByOffenderOnDate(
				(Offender) visit.getVisitationAssociation().getRelationship()
				.getFirstPerson(), form.getDate());
		//TODO: change visit method to form's when ready.
		this.visitService.update(visit, form.getDate(), form.getStartTime(),
				form.getEndTime(), form.getBadgeNumber(),
				new VisitFlags(form.getDeniedByStaff(),
						form.getRefusedByOffender(),
						VisitMethod.PHYSICALLY_PRESENT),
				form.getNotes(), this.visitService
				.findLocationTermByOffenderOnDate((Offender) visit
						.getVisitationAssociation().getRelationship()
						.getFirstPerson(), new Date())
				.getLocation());
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				visit.getVisitationAssociation().getRelationship()
				.getFirstPerson().getId()));
	}
	
	/**
	 * Removes the specified visit and redirects to the visitation listing
	 * screen on either the specified date, or within the specified start and/or
	 * end date.
	 * 
	 * @param visit visit to remove
	 * @param date date
	 * @param startDate start date
	 * @param endDate end date
	 * @return redirect to visitation list screen
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(value = "visit", required = true)
				final Visit visit,
			@RequestParam(value = "date", required = false) final Date date,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "endDate", required = false)
				final Date endDate) {
		this.visitService.remove(visit);
		return this.prepareListRedirect(visit.getVisitationAssociation()
				.getRelationship().getFirstPerson().getId(), date,
				startDate, endDate);
	}
	
	/**
	 * Displays the visits action menu.
	 * 
	 * @param offender offender
	 * @return model and view for visits action menu
	 */
	@RequestMapping(value = "visitsActionMenu.html", method = RequestMethod.GET)
	public ModelAndView displayVisitsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(VISITS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the visit action menu.
	 * 
	 * @param offender offender
	 * @return model and view for visit action menu
	 */
	@RequestMapping(value = "visitActionMenu.html", method = RequestMethod.GET)
	public ModelAndView displayVisitActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(VISIT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view to display the available selections of visitors
	 * for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param visitationAssociation visitation association
	 * @return model and view for special visit or approved visitation
	 * associations
	 */
	@RequestMapping(value = "visitationAssociationSelect.html",
			method = RequestMethod.GET)
	public ModelAndView displayVisitationAssociationOptions(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "date", required = true)
				final Date date,
			@RequestParam(value = "visitationAssociation", required = false)
				final VisitationAssociation visitationAssociation) {
		ModelMap map = new ModelMap();
		List<VisitationAssociation> associations 
			= new ArrayList<VisitationAssociation>();
		associations.addAll(this.visitService
						.findSpecialVisitAssociationsByOffender(offender,
								date));
		associations.addAll(this.visitService
						.findApprovedVisitationAssociaitonsByOffender(offender,
								date));
		map.addAttribute(VISITATION_ASSOCIATIONS_MODEL_KEY, associations);
		map.addAttribute(VISITOR_MODEL_KEY, visitationAssociation);
		return new ModelAndView(VISITATION_ASSOCIATION_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Displays the visit listing screen row action menu.
	 * 
	 * @param visit visit
	 * @param currentlyVisiting currently visiting
	 * @return model and view for visit row action menu
	 */
	@RequestMapping(value = "visitRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayVisitRowActionMenu(
			@RequestParam(value="visit", required = true)
				final Visit visit,
			@RequestParam(value="currentlyVisiting", required = true)
				final Boolean currentlyVisiting) {
		ModelMap map = new ModelMap();
		map.addAttribute(VISIT_MODEL_KEY, visit);
		map.addAttribute(CURRENTLY_VISITING_MODEL_KEY, currentlyVisiting);
		return new ModelAndView(VISIT_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Exception Handlers */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DUPLICATE_ENTITY_FOUND_EXCEPTION_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles date conflict found exception.
	 * 
	 * @param exception date conflict found
	 * @return
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
			final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DATE_CONFLICT_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	/* Reports. */
	
	/**
	 * Returns the report for the specified visit.
	 * 
	 * @param visit visit
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/visitDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportVisitDetails(@RequestParam(
			value = "visit", required = true)
			final Visit visit,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(VISIT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(visit.getId()));
		byte[] doc = this.reportRunner.runReport(
				VISIT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified visit form with values from the specified visit.
	 * 
	 * @param form visit form
	 * @param visit visit
	 * @return populated visit form
	 */
	private VisitForm prepareEditForm(final VisitForm form, final Visit visit) {
		form.setBadgeNumber(visit.getBadgeNumber());
		form.setDate(visit.getDateRange().getStartDate());
		form.setStartTime(visit.getDateRange().getStartDate());
		form.setEndTime(visit.getDateRange().getEndDate());
		VisitFlags flags = visit.getFlags();
		if (flags != null) {
			form.setDeniedByStaff(flags.getDeniedByStaff());
			form.setRefusedByOffender(flags.getRefusedByOffender());
			form.setVisitMethod(flags.getMethod());
		}
		form.setVisitationAssociation(visit.getVisitationAssociation());
		return form;
	}
	
	/*
	 * Prepares the model and view for editing a visit.
	 * 
	 * @param map model map
	 * @param form visit form
	 * @param offender offender
	 * @return populated model and view
	 */
	private ModelAndView prepareEditMav(final ModelMap map, 
			final VisitForm form, final Offender offender) {
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
			this.offenderSummaryModelDelegate.add(map, offender);
		}
		map.addAttribute(VISIT_METHODS_MODEL_KEY, VisitMethod.values());
		Date date = new Date();
		List<VisitationAssociation> associations 
			= new ArrayList<VisitationAssociation>();
		associations.addAll(this.visitService
						.findSpecialVisitAssociationsByOffender(offender,
								date));
		associations.addAll(this.visitService
						.findApprovedVisitationAssociaitonsByOffender(offender,
								date));
		map.addAttribute(VISITATION_ASSOCIATIONS_MODEL_KEY, associations);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * prepares a redirect to visit/list.html with optional parameters of date,
	 * start date, and end date.
	 *  
	 * @param id id
	 * @param date date
	 * @param startDate start date
	 * @param endDate end date
	 * @return model and view for list redirect
	 */
	private ModelAndView prepareListRedirect(final Long id, final Date date,
			final Date startDate, final Date endDate) {
		String listRedirect = String.format(LIST_REDIRECT_URL_FORMAT, id);
		if (date != null) {
			listRedirect = listRedirect + "&&date"
					+ String.format(DATE_FORMAT, date);
		} else if (startDate != null || endDate != null) {
			if (startDate != null) {
				listRedirect = listRedirect + "&&startDate"
						+ String.format(DATE_FORMAT, startDate);
			}
			if (endDate != null) {
				listRedirect = listRedirect + "&&endDate" 
						+ String.format(DATE_FORMAT, endDate);
			}
		}
		return new ModelAndView(listRedirect);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Person.class, 
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				VisitationAssociation.class,
				this.visitationAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Date.class,
				"startTime", 
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				Date.class,
				"endTime", 
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				Visit.class,
				this.visitPropertyEditorFactory
				.createPropertyEditor());
	}
}