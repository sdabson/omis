package omis.placement.web.controller;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
import omis.exception.DuplicateEntityFoundException;
import omis.facility.exception.FacilityNotAllowedException;
import omis.location.domain.Location;
import omis.location.exception.LocationNotAllowedException;
import omis.location.exception.LocationRequiredException;
import omis.locationterm.exception.LocationTermConflictException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.placement.web.controller.delegate.PlacementControllerDelegate;
import omis.placement.web.form.CorrectionalStatusChangeForm;
import omis.placement.web.validator.CorrectionalStatusChangeFormValidator;
import omis.program.domain.Program;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.EndedPlacementTermException;
import omis.supervision.exception.IllegalCorrectionalStatusChangeException;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.OffenderUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeReasonNotAllowedException;
import omis.supervision.exception.PlacementTermConflictException;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to change correctional status. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/placement")
@PreAuthorize("hasRole('USER')")
public class CorrectionalStatusChangeController {

	/* Model keys. */
	
	private static final String FORM_MODEL_KEY = "correctionalStatusChangeForm";
	
	private static final String ACTION_MODEL_KEY = "action";
	
	private static final String FROM_CORRECTIONAL_STATUS_MODEL_KEY
		= "fromCorrectionalStatus";

	private static final String TO_CORRECTIONAL_STATUS_MODEL_KEY
		= "toCorrectionalStatus";
	
	private static final String SUPERVISORY_ORGANIZATIONS_MODEL_KEY
		= "supervisoryOrganizations";
	
	private static final String CHANGE_REASONS_MODEL_KEY = "changeReasons";
	
	private static final String LOCATIONS_MODEL_KEY = "locations";
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String DEFAULT_LOCATION_MODEL_KEY = "defaultLocation";
	
	private static final String PROGRAMS_MODEL_KEY = "programs";
	
	/* View names. */
	
	private static final String VIEW_NAME
		= "placement/correctionalStatus/change/edit";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "placement/correctionalStatus/change/includes/actionMenu";
	
	private static final String LOCATION_SELECT_VIEW_NAME
		= "placement/includes/locationSelect";

	private static final String ALLOWED_PROGRAM_VIEW_NAME
		= "program/includes/programSelect";

	/* Message bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.placement.msgs.form";

	/* Message keys. */
	
	private static final String ILLEGAL_CORRECTIONAL_STATUS_CHANGE_MESSAGE_KEY
		= "correctionalStatusChange.illegal";

	private static final String OFFENDER_NOT_UNDER_SUPERVISION_MESSAGE_KEY
		= "correctionalStatusChange.offenderNotUnderSupervision";

	private static final String OFFENDER_UNDER_SUPERVISION_MESSAGE_KEY
		= "correctionalStatusChange.offenderUnderSupervision";

	private static final String PLACEMENT_TERM_CONFLICT_MESSAGE_KEY
		= "correctionalStatusChange.placementTermConflict";

	private static final String PLACEMENT_TERM_ENDED_ON_DATE_MESSAGE_KEY
		= "placementTerm.endedOnEffectiveDate";

	private static final String LOCATION_REQUIRED_MESSAGE_KEY
		= "correctionalStatusChange.locationRequired";

	private static final String LOCATION_NOT_ALLOWED_MESSAGE_KEY
		= "correctionalStatusChange.locationNotAllowed";

	private static final String LOCATION_TERM_CONFLICT_MESSAGE_KEY
		= "correctionalStatusChange.locationTermConflict";
	
	/* Validators. */
	
	@Autowired
	@Qualifier("correctionalStatusChangeFormValidator")
	private CorrectionalStatusChangeFormValidator
	correctionalStatusChangeFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("correctionalStatusPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("placementTermChangeActionPropertyEditorFactory")
	private PropertyEditorFactory
	placementTermChangeActionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("placementTermChangeReasonPropertyEditorFactory")
	private PropertyEditorFactory
	placementTermChangeReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("programPropertyEditorFactory")
	private PropertyEditorFactory programPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;

	/* Services. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("placementControllerDelegate")
	private PlacementControllerDelegate placementControllerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a controller to change correctional status. */
	public CorrectionalStatusChangeController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Displays form to change correctional status.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @param action placement term change action
	 * @return form to change correctional status
	 */
	@RequestMapping(value = "/changeCorrectionalStatus.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PLACEMENT_TERM_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			@RequestParam(value = "fromCorrectionalStatus", required = false)
				final CorrectionalStatus fromCorrectionalStatus,
			@RequestParam(value = "toCorrectionalStatus", required = false)
				final CorrectionalStatus toCorrectionalStatus,
			@RequestParam(value = "action", required = true)
				final PlacementTermChangeAction action) {
		Date effectiveDateTime;
		if (defaultEffectiveDate != null) {
			effectiveDateTime = DateManipulator.getDateAtTimeOfDay(
					defaultEffectiveDate,
					this.parseTimeText(defaultEffectiveTime));
		} else {
			effectiveDateTime = new Date();
		}
		CorrectionalStatusChangeForm correctionalStatusChangeForm
			= new CorrectionalStatusChangeForm();
		correctionalStatusChangeForm.setEffectiveDate(effectiveDateTime);
		correctionalStatusChangeForm.setEffectiveTime(effectiveDateTime);
		if (toCorrectionalStatus != null) {
			correctionalStatusChangeForm.setEndDateAllowed(true);
			correctionalStatusChangeForm
				.setSupervisoryOrganizationRequired(true);
			if (toCorrectionalStatus.getLocationRequired()) {
				correctionalStatusChangeForm.setLocationRequired(true);
				correctionalStatusChangeForm.setEndLocationAllowed(false);
			} else {
				correctionalStatusChangeForm.setLocationRequired(false);
				correctionalStatusChangeForm.setEndLocationAllowed(true);
				correctionalStatusChangeForm.setEndLocation(true);
			}
			correctionalStatusChangeForm.setProgramAllowed(true);
		} else {
			correctionalStatusChangeForm.setEndDateAllowed(false);
			correctionalStatusChangeForm
				.setSupervisoryOrganizationRequired(false);
			correctionalStatusChangeForm.setLocationRequired(false);
			correctionalStatusChangeForm.setProgramAllowed(false);
		}
		return this.prepareMav(correctionalStatusChangeForm, action,
				fromCorrectionalStatus, toCorrectionalStatus, offender);
	}
	
	/**
	 * Changes correctional status.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @param action placement term change action
	 * @param correctionalStatusChangeForm form to change correctional status
	 * @param result binding results
	 * @return redirect to supervision home
	 * @throws DuplicateEntityFoundException if the change has already been made
	 * @throws IllegalCorrectionalStatusChangeException if an attempt is made to
	 * make an illegal change in correctional status
	 * @throws OffenderNotUnderSupervisionException if and attempt to change or
	 * end correctional status is made and the offender is not under supervision
	 * on the effective date
	 * @throws OffenderUnderSupervisionException if an attempt is made to begin
	 * supervision for an offender already supervised on the effective date
	 * @throws PlacementTermConflictException if placement terms exists
	 * between effective date and end date
	 * @throws PlacementTermChangeReasonNotAllowedException if an attempt
	 * is made to use a change reason that is not allowed
	 * @throws EndedPlacementTermException if placement term on date is
	 * ended
	 * @throws LocationRequiredException if correctional status requires a
	 * location but a location is not provided
	 * @throws FacilityNotAllowedException if correctional status does not
	 * allow a facility but one is provided
	 * @throws LocationTermConflictException if an attempt is made to begin
	 * a location term at a facility and conflicting location terms exist
	 */
	@RequestMapping(value = "/changeCorrectionalStatus.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('PLACEMENT_TERM_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			@RequestParam(value = "fromCorrectionalStatus", required = false)
				final CorrectionalStatus fromCorrectionalStatus,
			@RequestParam(value = "toCorrectionalStatus", required = false)
				final CorrectionalStatus toCorrectionalStatus,
			@RequestParam(value = "action", required = true)
				final PlacementTermChangeAction action,
			final CorrectionalStatusChangeForm correctionalStatusChangeForm,
			final BindingResult result) throws DuplicateEntityFoundException,
				IllegalCorrectionalStatusChangeException,
				OffenderNotUnderSupervisionException,
				OffenderUnderSupervisionException,
				PlacementTermConflictException,
				PlacementTermChangeReasonNotAllowedException,
				EndedPlacementTermException,
				LocationRequiredException,
				LocationNotAllowedException,
				LocationTermConflictException {
		this.correctionalStatusChangeFormValidator.validate(
				correctionalStatusChangeForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(
					correctionalStatusChangeForm, result, action,
					fromCorrectionalStatus, toCorrectionalStatus, offender);
			return mav;
		}
		Date effectiveDate = this.calculateDateTime(
				correctionalStatusChangeForm.getEffectiveDate(),
				correctionalStatusChangeForm.getEffectiveTime()); 
		if (toCorrectionalStatus == null) {
			this.changeCorrectionalStatusService.endSupervision(offender,
					correctionalStatusChangeForm.getChangeReason(),
					effectiveDate);
		} else {
			Date endDate = this.calculateDateTime(
					correctionalStatusChangeForm.getEndDate(),
					correctionalStatusChangeForm.getEndTime());
			if (fromCorrectionalStatus == null) {
				this.changeCorrectionalStatusService.beginSupervision(offender,
					toCorrectionalStatus,
					correctionalStatusChangeForm.getSupervisoryOrganization(),
					correctionalStatusChangeForm.getChangeReason(),
					effectiveDate, endDate);
			} else {
				this.changeCorrectionalStatusService.change(offender,
					toCorrectionalStatus,
					correctionalStatusChangeForm.getSupervisoryOrganization(),
					correctionalStatusChangeForm.getChangeReason(),
					effectiveDate, endDate);
			}
			if (toCorrectionalStatus.getLocationRequired()) {
				this.changeCorrectionalStatusService.placeAtLocation(
						offender, correctionalStatusChangeForm.getLocation(),
						effectiveDate, endDate,
						correctionalStatusChangeForm.getLocationReason());
			}
		}
		if (toCorrectionalStatus == null
				|| (!toCorrectionalStatus.getLocationRequired()
					&& correctionalStatusChangeForm.getEndLocation() != null
					&& correctionalStatusChangeForm.getEndLocation())) {
			this.changeCorrectionalStatusService.endLocation(
					offender, effectiveDate);
		}
		if (defaultEffectiveDate != null) {
			return new ModelAndView(this.placementControllerDelegate
					.buildRedirectViewNameWithDate(offender, 
							DateManipulator.getDateAtTimeOfDay(
									defaultEffectiveDate,
									this.parseTimeText(defaultEffectiveTime))));
		} else {
			return new ModelAndView(this.placementControllerDelegate
					.buildRedirectViewName(offender));
		}
	}
	
	/* Action menus. */
	
	/**
	 * Returns action menu.
	 * 
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "/changeCorrectionalStatus/actionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Returns a select list of location options by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @param correctionalStatus correctional status
	 * @return select list of location options by supervisory organization
	 */
	@RequestMapping(value = "/locationSelect.html", method = RequestMethod.GET)
	public ModelAndView buildLocationSelect(
			@RequestParam(value = "supervisoryOrganization", required = true)
				final SupervisoryOrganization supervisoryOrganization, 
			@RequestParam(value = "correctionalStatus", required = true) 
				final CorrectionalStatus correctionalStatus) {
		List<Location> locations = this.changeCorrectionalStatusService
				.findLocationsForOrganization(supervisoryOrganization);
		Location location;
		if (locations.size() == 1) {
			location = locations.get(0);
		} else {
			location = null;
		}
		locations = this.changeCorrectionalStatusService
				.findLocationsForCorrectionalStatus(correctionalStatus);
		ModelAndView mav = new ModelAndView(LOCATION_SELECT_VIEW_NAME);
		mav.addObject(LOCATIONS_MODEL_KEY, locations);
		mav.addObject(DEFAULT_LOCATION_MODEL_KEY, location);
		
		return mav;
	}
	
	/**
	 * Returns select list of program options for facility or supervisory
	 * organization if no facility is specified.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param effectiveTime effective time
	 * @return select list of programs by offender and date
	 */
	@RequestMapping(value ="/programSelect.html", method = RequestMethod.GET)
	public ModelAndView buildProgramSelect(
			@RequestParam(value = "supervisoryOrganization", required = true)
				final SupervisoryOrganization supervisoryOrganization,
			@RequestParam(value = "location", required = false)
				final Location location,
			@RequestParam(value = "effectiveDate", required = true)
				final Date effectiveDate,
			@RequestParam(value = "effectiveTime", required = false)
				final Date effectiveTime) {
		List<Program> programs;
		if (location != null) {
			programs = this.changeCorrectionalStatusService
					.findProgramsOfferedAtLocation(location);
		} else {
			programs = this.changeCorrectionalStatusService
					.findProgramsOfferedBySupervisoryOrganization(
							supervisoryOrganization);
		}
		ModelAndView mav = new ModelAndView(ALLOWED_PROGRAM_VIEW_NAME);
		mav.addObject(PROGRAMS_MODEL_KEY, programs);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code IllegalCorrectionalStatusChangeException}.
	 * 
	 * @param illegalCorrectionalStatusChangeException exception thrown
	 * @return screen to handle {@code IllegalCorrectionalStatusChangeException}
	 */
	@ExceptionHandler(IllegalCorrectionalStatusChangeException.class)
	public ModelAndView handleIllegalCorrectionalStatusChangeException(
			final IllegalCorrectionalStatusChangeException
				illegalCorrectionalStatusChangeException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				ILLEGAL_CORRECTIONAL_STATUS_CHANGE_MESSAGE_KEY,
				ERROR_BUNDLE_NAME, illegalCorrectionalStatusChangeException);
	}
	
	/**
	 * Handles {@code OffenderNotUnderSupervisionException}
	 * 
	 * @param offenderNotUnderSupervisionException exception thrown
	 * @return screen to handle {@code OffenderNotUnderSupervisionException}
	 */
	@ExceptionHandler(OffenderNotUnderSupervisionException.class)
	public ModelAndView handleOffenderNotUnderSupervisionException(
			final OffenderNotUnderSupervisionException
				offenderNotUnderSupervisionException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				OFFENDER_NOT_UNDER_SUPERVISION_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				offenderNotUnderSupervisionException);
	}
	
	/**
	 * Handles {@code OffenderUnderSupervisionException}.
	 * 
	 * @param offenderUnderSupervisionException exception thrown
	 * @return screen to handle {@code OffenderUnderSupervisionException}
	 */
	@ExceptionHandler(OffenderUnderSupervisionException.class)
	public ModelAndView handleOffenderUnderSupervisionException(
			final OffenderUnderSupervisionException
				offenderUnderSupervisionException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				OFFENDER_UNDER_SUPERVISION_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				offenderUnderSupervisionException);
	}
	
	/**
	 * Handles {@code PlacementTermConflictException}.
	 * 
	 * @param placementTermConflictException exception thrown
	 * @return screen to handle {@code PlacementTermConflictException}
	 */
	@ExceptionHandler(PlacementTermConflictException.class)
	public ModelAndView handlePlacementTermConflictException(
			final PlacementTermConflictException
				placementTermConflictException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PLACEMENT_TERM_CONFLICT_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				placementTermConflictException);
	}
	
	/**
	 * Handles {@code EndedPlacementTermException}.
	 * 
	 * @param endedPlacementTermException exception thrown
	 * @return screen to handle {@code EndedPlacementTermException}
	 */
	@ExceptionHandler(EndedPlacementTermException.class)
	public ModelAndView handleEndedPlacementTermException(
			final EndedPlacementTermException endedPlacementTermException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PLACEMENT_TERM_ENDED_ON_DATE_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				endedPlacementTermException);
	}
	
	/**
	 * Handles {@code LocationRequiredException}.
	 * 
	 * @param locationRequiredException exception thrown
	 * @return screen to handle {@code LocationRequiredException}
	 */
	@ExceptionHandler(LocationRequiredException.class)
	public ModelAndView handleLocationRequiredException(
			final LocationRequiredException locationRequiredException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				LOCATION_REQUIRED_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				locationRequiredException);
	}
	
	/**
	 * Handles {@code LocationNotAllowedException}.
	 * 
	 * @param locationNotAllowedException exception thrown
	 * @return screen to handle {@code LocationNotAllowedException}
	 */
	@ExceptionHandler(LocationNotAllowedException.class)
	public ModelAndView handleLocationNotAllowedException(
			final LocationNotAllowedException locationNotAllowedException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				LOCATION_NOT_ALLOWED_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				locationNotAllowedException);
	}
	
	/**
	 * Handles {@code LocationTermConflictException}.
	 * 
	 * @param locationTermConflictException exception threw
	 * @return screen to handle {@code LocationTermConflictException}
	 */
	@ExceptionHandler(LocationTermConflictException.class)
	public ModelAndView handleLocationTermConflictException(
			final LocationTermConflictException locationTermConflictException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				LOCATION_TERM_CONFLICT_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				locationTermConflictException);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(
			final CorrectionalStatusChangeForm correctionalStatusChangeForm,
			final PlacementTermChangeAction action,
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus,
			final Offender offender) {
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.changeCorrectionalStatusService
				.findAllowedSupervisoryOrganizations(toCorrectionalStatus);
		List<PlacementTermChangeReason> changeReasons
			= this.changeCorrectionalStatusService.findAllowedChangeReasons(
					fromCorrectionalStatus, toCorrectionalStatus);
		List<Program> programs
			= this.changeCorrectionalStatusService
				.findAllowedProgramsForOffenderOnDate(offender,
						DateManipulator.getDateAtTimeOfDay(
							correctionalStatusChangeForm.getEffectiveDate(),
							correctionalStatusChangeForm.getEffectiveTime()));
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(FORM_MODEL_KEY, correctionalStatusChangeForm);
		mav.addObject(ACTION_MODEL_KEY, action);
		mav.addObject(FROM_CORRECTIONAL_STATUS_MODEL_KEY,
				fromCorrectionalStatus);
		mav.addObject(TO_CORRECTIONAL_STATUS_MODEL_KEY,
				toCorrectionalStatus);
		mav.addObject(SUPERVISORY_ORGANIZATIONS_MODEL_KEY,
				supervisoryOrganizations);
		mav.addObject(CHANGE_REASONS_MODEL_KEY,
				changeReasons);
		mav.addObject(PROGRAMS_MODEL_KEY, programs);
		if (correctionalStatusChangeForm.getSupervisoryOrganization() != null) {
			List<Location> locations = this.changeCorrectionalStatusService
					.findLocationsForOrganization(correctionalStatusChangeForm
							.getSupervisoryOrganization());
			Location location;
			if (locations.size() == 1) {
				location = locations.get(0);
			} else {
				location = null;
			}
			mav.addObject(DEFAULT_LOCATION_MODEL_KEY, location);
		} 
		if (toCorrectionalStatus != null) {
			List<Location> locations = this.changeCorrectionalStatusService
					.findLocationsForCorrectionalStatus(toCorrectionalStatus);
			mav.addObject(LOCATIONS_MODEL_KEY, locations);
		}
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay
	private ModelAndView prepareRedisplay(
			final CorrectionalStatusChangeForm correctionalStatusChangeForm,
			final BindingResult result,
			final PlacementTermChangeAction action,
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus,
			final Offender offender) {
		ModelAndView mav = this.prepareMav(
				correctionalStatusChangeForm, action, fromCorrectionalStatus,
				toCorrectionalStatus, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	// Adds time to date and returns date - if date is null return null (ignore
	// time) if time is null assume 0
	private Date calculateDateTime(final Date date, final Date time) {
		if (date != null) {
			return DateManipulator.getDateAtTimeOfDay(date, time);
		} else {
			return null;
		}
	}
	
	// Parses time
	private Date parseTimeText(final String timeText) {
		if (timeText != null && !timeText.isEmpty()) {
			PropertyEditor propertyEditor
				= this.datePropertyEditorFactory
					.createCustomTimeOnlyEditor(true);
			propertyEditor.setAsText(timeText);
			return (Date) propertyEditor.getValue();
		} else {
			return null;
		}
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editor factories.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(CorrectionalStatus.class,
				this.correctionalStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SupervisoryOrganization.class,
				this.supervisoryOrganizationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(PlacementTermChangeAction.class,
				this.placementTermChangeActionPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(PlacementTermChangeReason.class,
				this.placementTermChangeReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Location.class,
				this.locationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Program.class,
				this.programPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class, "effectiveDate",
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "endDate",
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "effectiveTime",
				this.datePropertyEditorFactory
					.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "endTime",
				this.datePropertyEditorFactory
					.createCustomTimeOnlyEditor(true));
	}
}