package omis.citation.web.controller;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.citation.report.MisdemeanorCitationReportService;
import omis.citation.report.MisdemeanorCitationSummary;
import omis.citation.service.MisdemeanorCitationService;
import omis.citation.web.form.MisdemeanorCitationForm;
import omis.citation.web.validator.MisdemeanorCitationFormValidator;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.Month;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Misdemeanor citation controller.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 12, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/citation")
@PreAuthorize("hasRole('USER')")
public class MisdemeanorCitationController {
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String CITATION_MODEL_KEY = "citation";
	
	private static final String CITATIONS_MODEL_KEY = "citations";

	private static final String CITATION_FORM_MODEL_KEY = "citationForm";
	
	private static final String OFFENSES_MODEL_KEY = "offenses";
	
	private static final String STATES_MODEL_KEY = "states";
	
	private static final String CITIES_MODEL_KEY = "cities";
	
	private static final String DISPOSITIONS_MODEL_KEY = "dispositions";
	
	private static final String MONTHS_MODEL_KEY = "months";

	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "citation/edit";

	private static final String LIST_VIEW_NAME = "citation/list";
	
	private static final String CITATION_ACTION_MENU_VIEW_NAME 
		= "citation/includes/citationActionMenu";
	
	private static final String CITATIONS_ACTION_MENU_VIEW_NAME
		= "citation/includes/citationsActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT_VIEW_NAME
		= "redirect:/citation/list.html?offender=%d";
	
	/* Message keys. */
	
	private static final String CITATION_EXISTS_MESSAGE_KEY
		= "citation.exists";
	
	/* Message bundles. */
	private static final String ERROR_BUNDLE_NAME
	= "omis.citation.msgs.form";
	
	/* Services. */	
	
	@Autowired
	@Qualifier("misdemeanorCitationService")
	private MisdemeanorCitationService misdemeanorCitationService;
	
	@Autowired
	@Qualifier("misdemeanorCitationReportService")
	private MisdemeanorCitationReportService misdemeanorCitationReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("misdemeanorCitationPropertyEditorFactory")
	private PropertyEditorFactory misdemeanorCitationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("misdemeanorOffensePropertyEditorFactory")
	private PropertyEditorFactory misdemeanorOffensePropertyEditorFactory;
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("misdemeanorCitationFormValidator")
	private MisdemeanorCitationFormValidator misdemeanorCitationFormValidator;
	
	/* Report names. */
	
	private static final String MISDEMEANOR_CITATION_LISTING_REPORT_NAME 
		= "/Legal/MisdemeanorCitation/Misdemeanor_Citations_Listing";

	private static final String MISDEMEANOR_CITATION_DETAILS_REPORT_NAME 
		= "/Legal/MisdemeanorCitation/Misdemeanor_Citation_Details";

	/* Report parameter names. */
	
	private static final String MISDEMEANOR_CITATION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String MISDEMEANOR_CITATION_DETAILS_ID_REPORT_PARAM_NAME 
		= "MISDEMEANOR_CITATION_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Constructor. */
	
	/** Instantiates a default controller for citations. */
	public MisdemeanorCitationController() {
		// Default instantiation
	}
	
	/* Screens. */
	
	/**
	 * Displays a list of citations for an offender.
	 * 
	 * @param offender offender whose citations to list
	 * @return model and view to a list of citations
	 */
	
	@RequestContentMapping(nameKey = "misdemeanorCitationListingScreenName", 
			descriptionKey = "misdemeanorCitationListingScreenDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<MisdemeanorCitationSummary> citations = 
			this.misdemeanorCitationReportService.summarizeByOffender(offender);
		mav.addObject(CITATIONS_MODEL_KEY, citations);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays a form allowing a new citation to be created.
	 * 
	 * @param offender offender from whom to create a new citation
	 * @return model and view to screen that allows new citation to be created
	 */
	@RequestContentMapping(nameKey = "misdemeanorCitationCreateScreenName",
			descriptionKey = "misdemeanorCitationCreateScreenDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", 
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		MisdemeanorCitationForm citationForm = new MisdemeanorCitationForm();
		citationForm.setState(this.misdemeanorCitationService.findHomeState());
		return this.prepareEditMav(citationForm, offender);
	}
	
	/**
	 * Displays a form allowing an existing citation to be edited.
	 * 
	 * @param citation citation to allow to be edited
	 * @return model and view to screen that allows existing citation to be
	 * edited
	 */
	@RequestContentMapping(nameKey = "citationEditScreenName",
			descriptionKey = "citationEditScreenDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html",
		method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "citation", required = true)
				final MisdemeanorCitation citation) {
		MisdemeanorCitationForm citationForm = new MisdemeanorCitationForm();
		citationForm.setOffense(citation.getOffense());
		citationForm.setState(citation.getState());
		citationForm.setCity(citation.getCity());
		citationForm.setCounts(citation.getCounts());
		citationForm.setDisposition(citation.getDisposition());
		
	// This should be good. If day is not null then neither will month so we
	// avoid a null pointer exception.
		if (citation.getDay() != null) {
		citationForm.setDate(
			new DateManipulator(citation.getYear(), 
					citation.getMonth().getCalendarMonth(),
					citation.getDay()).getDate());		
		citationForm.setPartialDate(false);
		} else {
			citationForm.setMonth(citation.getMonth());
			citationForm.setYear(citation.getYear());
			citationForm.setPartialDate(true);
		}
		ModelAndView mav = this.prepareEditMav(
				citationForm, citation.getOffender());
		mav.addObject(CITATION_MODEL_KEY, citation);
		return mav;
	}
	
	// Returns a model and view suitable for editing the specified citation.
	private ModelAndView prepareEditMav(
			final MisdemeanorCitationForm citationForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CITATION_FORM_MODEL_KEY, citationForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);		
		List<MisdemeanorOffense> offenses = this.misdemeanorCitationService
				.findOffenses();
		mav.addObject(OFFENSES_MODEL_KEY, offenses);		
		List<State> states = this.misdemeanorCitationService.findStates();
		mav.addObject(STATES_MODEL_KEY, states);		
		if (citationForm.getState() != null) {
			List<City> cities = this.misdemeanorCitationService
					.findCitiesByState(citationForm.getState());
			mav.addObject(CITIES_MODEL_KEY, cities);
		}
		mav.addObject(DISPOSITIONS_MODEL_KEY, MisdemeanorDisposition.values());
		mav.addObject(MONTHS_MODEL_KEY, Month.values());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Saves a new citation.
	 * 
	 * @param offender offender for whom to create the new citation
	 * @param citationForm form containing citation information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * citation is attempted  
	 */
	@RequestContentMapping(nameKey = "citationCreateSubmitName",
			descriptionKey = "citationCreateSubmitDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
			final Offender offender, final MisdemeanorCitationForm citationForm, 
			final BindingResult result) 
				throws DuplicateEntityFoundException {
		this.misdemeanorCitationFormValidator.validate(citationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(citationForm, offender);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ CITATION_FORM_MODEL_KEY, result);
			return mav;
		}
		MisdemeanorOffense newOffense;
		if (citationForm.getCreateNewOffense() != null
				&& citationForm.getCreateNewOffense()) {
			newOffense = this.misdemeanorCitationService.createOffense(
					citationForm.getOffenseName(),
					citationForm.getCreateNewOffense());
		} else {
			newOffense = citationForm.getOffense();
		}
		Integer day; 
		Month month;
		Integer year;
		if (citationForm.getPartialDate() == true) {
			day = null;
			month = citationForm.getMonth();
			year = citationForm.getYear();
		} else {
			DateManipulator dateMan = new DateManipulator(citationForm.getDate());
			day = dateMan.getDayOfMonth();
			month = Month.findByCalendarMonth(dateMan.getMonth());
			year = dateMan.getYear();
	}
		MisdemeanorCitation citation = this.misdemeanorCitationService.save(
				offender, citationForm.getState(), 
				citationForm.getCity(), day,
				month, year, citationForm.getCounts(), newOffense, 
				citationForm.getDisposition());
		return this.prepareListRedirect(citation.getOffender());
	}
	
	/**
	 * Updates an existing citation.
	 * 
	 * @param citation citation to update
	 * @param citationForm form containing citation information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * citation is attempted
	 */
	@RequestContentMapping(nameKey = "citationEditSubmitName",
			descriptionKey = "citationEditSubmitDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "citation", required = true)
				final MisdemeanorCitation citation,
				final MisdemeanorCitationForm citationForm,
				final BindingResult result) 
						throws DuplicateEntityFoundException {
		this.misdemeanorCitationFormValidator.validate(citationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(citationForm, 
					citation.getOffender());
			mav.addObject(
					BindingResult.MODEL_KEY_PREFIX + CITATION_FORM_MODEL_KEY, 
					result);
			mav.addObject(CITATION_MODEL_KEY, citation);
			return mav;
		}
		MisdemeanorOffense newOffense;
		if (citationForm.getCreateNewOffense() != null
				&& citationForm.getCreateNewOffense()) {
			newOffense = this.misdemeanorCitationService.createOffense(
					citationForm.getOffenseName(),
					citationForm.getCreateNewOffense());
		} else {
			newOffense = citationForm.getOffense();
		}
		Integer day;
		Month month;
		Integer year;
		if (citationForm.getPartialDate() == true) {
			day = null;
			month = citationForm.getMonth();
			year = citationForm.getYear();
		} else {
			DateManipulator dateMan = new DateManipulator(citationForm.getDate());
			day = dateMan.getDayOfMonth();
			month = Month.findByCalendarMonth(dateMan.getMonth());
			year = dateMan.getYear();
	}
		MisdemeanorCitation updatedCitation = this.misdemeanorCitationService
				.update(citation, citationForm.getState(), 
						citationForm.getCity(), day,
						month, year, citationForm.getCounts(),
						newOffense, citationForm.getDisposition());
		return this.prepareListRedirect(updatedCitation.getOffender());
	}
					
	// Prepares citation screen redirect
	private ModelAndView prepareListRedirect(
			final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
				offender.getId()));
	}
	
	/**
	 * Removes a citation.
	 * 
	 * @param citation citation to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "citationRemove",
			descriptionKey = "citationRemoveDescription",
			messageBundle = "omis.citation.msgs.citation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "citation", required = true)
				final MisdemeanorCitation citation)	{
		Offender offender = citation.getOffender();
		this.misdemeanorCitationService.remove(citation);
		return this.prepareListRedirect(offender);
	}
	
	/**
	 * Returns the content for the citations action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the citations action menu
	 */
	@RequestMapping(value = "citationsActionMenu.html")
	public ModelAndView citationsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "citation", required = false)
				final MisdemeanorCitation citation) {
		ModelMap map = new ModelMap();
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		if (citation != null) {
			map.addAttribute(CITATION_MODEL_KEY, citation);
		}
		return new ModelAndView(CITATIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the content for the citation action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the citation action menu
	 */
	@RequestMapping(value = "citationActionMenu.html")
	public ModelAndView citationActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CITATION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Exception handler methods. */
	
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
				CITATION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders misdemeanor citations.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/misdemeanorCitationsListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportMisdemeanorCitationsListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(MISDEMEANOR_CITATION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				MISDEMEANOR_CITATION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified misdemeanor citation.
	 * 
	 * @param citation misdemeanor citation
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/misdemeanorCitationsDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportMisdemeanorCitationsDetails(@RequestParam(
			value = "citation", required = true)
			final MisdemeanorCitation citation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(MISDEMEANOR_CITATION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(citation.getId()));
		byte[] doc = this.reportRunner.runReport(
				MISDEMEANOR_CITATION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init binder. */
	
	/**
	 * Init binder.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(MisdemeanorCitation.class,
				this.misdemeanorCitationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(MisdemeanorOffense.class,
				this.misdemeanorOffensePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(State.class, 
				this.statePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(City.class, 
				this.cityPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}