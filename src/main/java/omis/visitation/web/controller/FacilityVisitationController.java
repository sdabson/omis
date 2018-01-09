package omis.visitation.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.media.domain.Photo;
import omis.media.io.PhotoRetriever;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.report.FacilityVisitationOffenderSummary;
import omis.visitation.report.FacilityVisitationReportService;
import omis.visitation.report.FacilityVisitationSummary;
import omis.visitation.report.VisitSummary;
import omis.visitation.report.VisitSummaryReportService;
import omis.visitation.report.VisitationAssociationSummaryReportService;
import omis.visitation.service.FacilityVisitationService;
import omis.visitation.service.VisitationAssociationService;
import omis.visitation.validator.FacilityVisitationLogFormValidator;
import omis.visitation.web.form.FacilityVisitationLogForm;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Facility visitation controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (February 21, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/visitation/facility")
@PreAuthorize("hasRole('USER')")
public class FacilityVisitationController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME = "visitation/facility/list";
	
	private static final String VISIT_LOG_ROW_ACTION_MENU_VIEW_NAME
		= "visitation/facility/includes/visitLogRowActionMenu";
	
	private static final String FACILITY_VISITATIONS_ACTION_MENU_VIEW_NAME
		= "visitation/facility/includes/facilityVisitationsActionMenu";
	
	private static final String OFFENDER_VISITATION_PANEL_ACTION_MENU_VIEW_NAME
		= "visitation/facility/includes/offenderVisitationPanelActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT_URL_FORMAT
		= "redirect:list.html?location=%d&&effectiveDate=%2$tm/%2$td/%2$tY";
	
	/* Model keys. */
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String FACILITY_VISITATION_OFFENDER_SUMMARIES
	= "facilityVisitationOffenderSummaries";

	private static final String FACILITY_VISITATION_LOG_FORM_MODEL_KEY
	= "facilityVisitationLogForm";
	
	private static final String FACILITY_VISITATION_SUMMARY
		= "facilityVisitationSummary";
	
	private static final String FACILITIES_MODEL_KEY = "facilities";
	
	private static final String VISIT_MODEL_KEY = "visit";
	
	private static final String CURRENTLY_VISITING_MODEL_KEY
		= "currentlyVisiting";
	
	private static final String DATE_MODEL_KEY = "date";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Services */
	
	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;
	
	@Autowired
	@Qualifier("facilityVisitationService")
	private FacilityVisitationService facilityVisitationService;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("visitationAssociationSummaryReportService")
	private VisitationAssociationSummaryReportService
	visitationAssociationSummaryReportService;
	
	@Autowired
	@Qualifier("visitSummaryReportService")
	private VisitSummaryReportService visitSummaryReportService;
	
	@Autowired
	@Qualifier("facilityVisitationReportService")
	private FacilityVisitationReportService facilityVisitationReportService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("visitPropertyEditorFactory")
	private PropertyEditorFactory visitPropertyEditorFactory;
	
	@Autowired
	@Qualifier("visitationAssociationPropertyEditorFactory")
	private PropertyEditorFactory visitationAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("photoPropertyEditorFactory")
	private PropertyEditorFactory photoPropertyEditorFactory;
	
	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validiators. */
	
	@Autowired
	@Qualifier("facilityVisitationLogFormValidator")
	private FacilityVisitationLogFormValidator
	facilityVisitationLogFormValidator;
	
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderPhotoRetriever")
	private PhotoRetriever offenderPhotoRetriever;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.visitation.msgs.form";
	
	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_EXCEPTION_MESSAGE_KEY
		= "duplicateVisitFound";
	private static final String DATE_CONFLICT_MESSAGE_KEY
		= "conflictingVisitDates";
	
	/* Constructors. */
	
	/** Instantiates a default instance of facility visitation controller. */
	public FacilityVisitationController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Show the facility visitation list screen with current active visits,
	 * and visitation associations involved.
	 * 
	 * @param location location
	 * @return model and view for facility visitation list screen
	 */
	@RequestMapping(value="/list.html", method=RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "location", required = false)
			final Location location,
			@RequestParam(value = "effectiveDate", required = false)
			final Date effectiveDate) {
		final Date date;
		if (effectiveDate != null) {
			date = effectiveDate;
		} else {
			date = new Date();
		}
		ModelMap map = new ModelMap();
		FacilityVisitationLogForm form = new FacilityVisitationLogForm();
		form.setDate(date);
		Facility facility = this.facilityVisitationService
				.findFacilityByLocation(location);
		form.setFacility(facility);
		map.addAttribute(FACILITY_VISITATION_LOG_FORM_MODEL_KEY, form);
		this.prepareListMav(map, facility, date);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Presents the facility visitation screen with the values of the
	 * specified visitor log form.
	 *  
	 * @param form visitor log form
	 * @param result binding result
	 * @return model and view for facility visitation
	 */
	@RequestMapping(value="/list.html", method=RequestMethod.POST)
	@PreAuthorize("hasRole('VISIT_LIST') or hasRole('ADMIN')")
	public ModelAndView facilityVisitLog(final FacilityVisitationLogForm form,
			final BindingResult result) {
		this.facilityVisitationLogFormValidator.validate(form, result);
		ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_VISITATION_LOG_FORM_MODEL_KEY, form);
		this.prepareListMav(map, form.getFacility(), form.getDate());
		if (result.hasErrors()) {
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ FACILITY_VISITATION_LOG_FORM_MODEL_KEY, result);
			return new ModelAndView(LIST_VIEW_NAME, map);
		}
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the photo data of the specified content type for the specified
	 * photo.
	 * 
	 * @param photo photo
	 * @param contentType content type
	 * @return response entity byte array
	 */
	@RequestMapping(value = "/displayPhoto.html")
	public ResponseEntity<byte[]> displayPhoto(
			@RequestParam(value = "photo", 
			required = true) final Photo photo, 
			@RequestParam(value = "contentType", required = true) 
			final String contentType) {
		byte[] photoData;
		if (photo != null) {
			photoData = this.offenderPhotoRetriever.retrieve(photo);
		} else {
			photoData = this.offenderPhotoRetriever.retrieve("NotSet.jpg");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", contentType);
		return new ResponseEntity<byte[]>(photoData, headers, HttpStatus.OK);
	}
	
	/**
	 * Displays a visit log row action menu with interactions for the specified
	 * visit.
	 * 
	 * @param visit visit
	 * @param currentlyVisiting currently visiting
	 * @return model and view for visit log row action menu
	 */
	@RequestMapping(value = "/visitLogRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayVisitLogRowActionMenu(
			@RequestParam(value = "visit", required = true)final Visit visit,
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "currentlyVisiting", required = true)
				final Boolean currentlyVisiting,
			@RequestParam(value = "date", required = false)final Date date) {
		ModelMap map = new ModelMap();
		map.addAttribute(VISIT_MODEL_KEY, visit);
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(CURRENTLY_VISITING_MODEL_KEY, currentlyVisiting);
		map.addAttribute(DATE_MODEL_KEY, date);
		return new ModelAndView(VISIT_LOG_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the facility visitations center action menu.
	 * 
	 * @param facility facility
	 * @param date effective date
	 * @return model and view for facilities action menu
	 */
	@RequestMapping(value = "/facilityVisitationsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayFacilityVisitationsActionMenu(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "date", required = true)
				final Date date) {
		ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(DATE_MODEL_KEY, date);
		return new ModelAndView(FACILITY_VISITATIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the offender visitation panel action menu for facility
	 * visitation.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @return model and view for offender visitation panel action menu
	 */
	@RequestMapping(value = "/displayOffenderVisitationPanelActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayOffenderVisitationPanelActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "date", required = true)
				final Date date) {
		ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(DATE_MODEL_KEY, date);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(OFFENDER_VISITATION_PANEL_ACTION_MENU_VIEW_NAME, map);
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
				final Visit visit, @RequestParam(value = "date",
				required = false) final Date date,
				@RequestParam(value = "facility", required = true)
				final Facility facility)
			throws DateConflictException, DuplicateEntityFoundException {
		final Date effectiveDate;
		if (date != null) {
			effectiveDate = date;
		} else {
			effectiveDate = new Date();
		}
		this.facilityVisitationService.endVisit(visit, new Date());
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				facility.getLocation().getId(), effectiveDate));
	}
	
	/**
	 * Removes the specified visit, and redirects to the specified facility's
	 * visitation screen.
	 * 
	 * @param visit visit
	 * @param facility facility
	 * @param date date
	 * @return model and view that redirects to facility visitation list
	 */
	@RequestMapping(value="/removeVisit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView removeVisit(
			@RequestParam(value = "visit", required = true) final Visit visit,
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "date", required = true) final Date date) {
		this.facilityVisitationService.removeVisit(visit);
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				facility.getLocation().getId(), date));
	}
	
	/**
	 * Ends all visits for the specified offender on the specified date, 
	 * then returns to the facility visitation screen for the specified facility
	 * on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @param facility facility
	 * @return model and view for facility visitation list screen
	 */
	@RequestMapping(value="/checkOutAllOffenderVisits.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_EDIT') or hasRole('ADMIN')")
	public ModelAndView checkOutAllOffenderVisits(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "date", required = true)
				final Date date,
			@RequestParam(value = "facility", required = true)
				final Facility facility) {
		this.facilityVisitationService.checkOutAllOffenderVisits(offender,
				date);
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				facility.getLocation().getId(), date));
	}
	
	/**
	 * Checks out all visits for the specified date at the specified facility.
	 * 
	 * @param date date
	 * @param facility facility
	 * @return redirect to facility visitation list screen
	 */
	@RequestMapping(value="/checkOutAllByFacility.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('VISIT_EDIT') or hasRole('ADMIN')")
	public ModelAndView checkOutAllByFacility(
			@RequestParam(value = "date", required = true)
				final Date date,
			@RequestParam(value = "facility", required = true)
				final Facility facility) {
		for (Offender offender : this.facilityVisitationService
				.findOffendersWithVisitsOnDate(facility, date)) {
			this.facilityVisitationService.checkOutAllOffenderVisits(offender,
					date);
		}
		return new ModelAndView(String.format(LIST_REDIRECT_URL_FORMAT,
				facility.getLocation().getId(), date));
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
	
	/* Helper methods. */
	
	/*
	 * Prepares model map for facility listing screen.
	 * 
	 * @param map model map
	 * @param facility facility
	 * @param date date
	 * @return model map
	 */
	private ModelMap prepareListMav(final ModelMap map,
			final Facility facility, final Date date) {
		if (facility != null) {
			//Find all visiting offenders, then create facility visitation
			//offender summary for each one with a list of visit summaries.
			List<Offender> visitingOffenders = new ArrayList<Offender>();
			
			visitingOffenders = this.facilityVisitationReportService
					.findVisitingOffendersByLocationOnDate(
							facility.getLocation(), date);
			
			final List<FacilityVisitationOffenderSummary> summaries
				= new ArrayList<FacilityVisitationOffenderSummary>();
			for(Offender offender : visitingOffenders) {
				List<VisitSummary> visitSummaries = 
						this.facilityVisitationReportService
						.findVisitSummariesByOffenderOnDate(offender, date);
				String unitName = this.facilityVisitationReportService
						.findOffenderHousingUnit(offender, date);
				Long photoId = this.facilityVisitationReportService
						.findMugShotIdByOffender(offender);
				summaries.add(new FacilityVisitationOffenderSummary(
						offender.getId(), offender.getName().getFirstName(),
						offender.getName().getLastName(),
						offender.getName().getMiddleName(),
						offender.getOffenderNumber(), unitName, photoId,
						visitSummaries));
			}
			map.addAttribute(FACILITY_VISITATION_OFFENDER_SUMMARIES,
					summaries);
			Integer visitCount = 0;
			Integer activeVisits = 0;
			for(FacilityVisitationOffenderSummary summary 
					: summaries) {
				for (VisitSummary visitSummary : summary.getVisitSummaries()) {
					visitCount++;
					if( visitSummary.getCurrentlyVisiting()) {
						activeVisits++;
					}
				}
			}
			map.addAttribute(FACILITY_VISITATION_SUMMARY,
					new FacilityVisitationSummary(facility.getId(), 
							summaries.size(), visitCount,
							activeVisits,
							summaries.size() + visitCount));
			map.addAttribute(FACILITY_MODEL_KEY, facility);
			map.addAttribute(DATE_MODEL_KEY, date);
		}
		map.addAttribute(FACILITIES_MODEL_KEY,
				this.facilityVisitationService.findFacilities());
		return map;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Visit.class,
				this.visitPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				VisitationAssociation.class,
				this.visitationAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Photo.class,
				this.photoPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Location.class,
				this.locationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}