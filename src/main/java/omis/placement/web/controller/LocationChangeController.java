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
import omis.location.domain.Location;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTermChangeAction;
import omis.locationterm.exception.EndedLocationTermException;
import omis.locationterm.exception.LocationReasonTermConflictException;
import omis.locationterm.exception.LocationTermConflictException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placement.service.ChangeLocationService;
import omis.placement.web.controller.delegate.PlacementControllerDelegate;
import omis.placement.web.form.LocationChangeForm;
import omis.placement.web.validator.LocationChangeFormValidator;
import omis.supervision.domain.CorrectionalStatus;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to change location.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/placement")
@PreAuthorize("hasRole('USER')")
public class LocationChangeController {
	
	/* View names. */
	
	private static final String EDIT_VIEW = "placement/location/change/edit";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "placement/location/change/includes/actionMenu";

	/* Model keys. */
	
	private static final String FORM_MODEL_KEY = "locationChangeForm";

	private static final String ACTION_MODEL_KEY = "action";

	private static final String REASONS_MODEL_KEY = "reasons";

	private static final String LOCATIONS_MODEL_KEY = "locations";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Message bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.placement.msgs.form";

	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MESSAGE_KEY
		= "locationChange.locationTermExists";

	private static final String LOCATION_TERM_CONFLICT_MESSAGE_KEY
		= "locationChange.locationTermConflict";

	private static final String LOCATION_TERM_ENDED_MESSAGE_KEY
		= "locationChange.locationTermEnded";

	private static final String LOCATION_REASON_TERM_CONFLICT_MESSAGE_KEY
		= "locationChange.locationReasonTermConflict";
	
	/* Validators. */
	
	@Autowired
	@Qualifier("locationChangeFormValidator")
	private LocationChangeFormValidator locationChangeFormValidator;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("placementControllerDelegate")
	private PlacementControllerDelegate placementControllerDelegate;
	
	/* Property editor factories. */

	@Autowired
	@Qualifier("locationTermChangeActionPropertyEditorFactory")
	private PropertyEditorFactory locationTermChangeActionPropertyEditorFactory;

	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("locationReasonPropertyEditorFactory")
	private PropertyEditorFactory locationReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("correctionalStatusPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("changeLocationService")
	private ChangeLocationService changeLocationService;
	
	/* Constructors. */
	
	/** Instantiates controller to change location. */
	public LocationChangeController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Shows screen to change location.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param correctionalStatus correctional status
	 * @param action location change action
	 * @return screen to change location 
	 */
	@PreAuthorize("hasRole('LOCATION_TERM_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/changeLocation.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			@RequestParam(value = "correctionalStatus", required = false)
				final CorrectionalStatus correctionalStatus,
			@RequestParam(value = "action", required = true)
				final LocationTermChangeAction action) {
		Date effectiveDateTime;
		if (defaultEffectiveDate != null) {
			effectiveDateTime = DateManipulator.getDateAtTimeOfDay(
					defaultEffectiveDate,
					this.parseTimeText(defaultEffectiveTime));
		} else {
			effectiveDateTime = new Date();
		}
		LocationChangeForm locationChangeForm = new LocationChangeForm();
		locationChangeForm.setEndDateAllowed(true);
		locationChangeForm.setEffectiveDate(effectiveDateTime);
		locationChangeForm.setEffectiveTime(effectiveDateTime);
		return this.prepareEditMav(locationChangeForm, offender, action,
				correctionalStatus);
	}
	
	/**
	 * Changes location.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param correctionalStatus correctional status
	 * @param action location change action
	 * @param locationChangeForm form to change location
	 * @param result binding result
	 * @return redirect to placement home
	 * @throws DuplicateEntityFoundException if location change has already
	 * been made
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist
	 * @throws EndedLocationTermException if location term on effective date
	 * is ended
	 * @throws LocationReasonTermConflictException if conflicting reason terms
	 * exist
	 */
	@PreAuthorize("hasRole('LOCATION_TERM_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/changeLocation.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			@RequestParam(value = "correctionalStatus", required = false)
				final CorrectionalStatus correctionalStatus,
			@RequestParam(value = "action", required = true)
				final LocationTermChangeAction action,
			final LocationChangeForm locationChangeForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						LocationTermConflictException,
						EndedLocationTermException,
						LocationReasonTermConflictException {
		this.locationChangeFormValidator.validate(locationChangeForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(
					locationChangeForm, result, offender, action,
					correctionalStatus);
		}
		this.changeLocationService.change(offender,
				this.calculateDateTime(
						locationChangeForm.getEffectiveDate(),
						locationChangeForm.getEffectiveTime()),
				this.calculateDateTime(
						locationChangeForm.getEndDate(),
						locationChangeForm.getEndTime()),
				locationChangeForm.getLocation(),
				locationChangeForm.getReason());
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
	@RequestMapping(value = "/changeLocation/actionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/**
	 * Handles {@code DuplicateEntityFoundException}.
	 * 
	 * @param duplicateEntityFoundException exception thrown
	 * @return screen to handle {@code DuplicateEntityFoundException}
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						DUPLICATE_ENTITY_FOUND_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, duplicateEntityFoundException);
	}
	
	/**
	 * Handles {@code LocationTermConflictException}.
	 * 
	 * @param locationTermConflictException exception thrown
	 * @return screen to handle {@code LocationTermConflictException}
	 */
	@ExceptionHandler(LocationTermConflictException.class)
	public ModelAndView handleLocationTermConflictException(
			final LocationTermConflictException locationTermConflictException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						LOCATION_TERM_CONFLICT_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, locationTermConflictException);
	}
	
	/**
	 * Handles {@code EndedLocationTermException}.
	 * 
	 * @param endedLocationTermException exception thrown
	 * @return screen to handle {@code EndedLocationTermException}
	 */
	@ExceptionHandler(EndedLocationTermException.class)
	public ModelAndView handleEndedLocationTermException(
			final EndedLocationTermException endedLocationTermException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						LOCATION_TERM_ENDED_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, endedLocationTermException);
	}
	
	/**
	 * Handles {@code LocationReasonTermConflictException}.
	 * 
	 * @param locationReasonTermConflictException exception thrown
	 * @return screen to handle {@code LocationReasonTermConflictException}
	 */
	@ExceptionHandler(LocationReasonTermConflictException.class)
	public ModelAndView handleLocationReasonTermConflictException(
			final LocationReasonTermConflictException
				locationReasonTermConflictException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(LOCATION_REASON_TERM_CONFLICT_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, locationReasonTermConflictException);
	}
	
	/* Helper methods. */
	
	// Prepares model and view to change location
	private ModelAndView prepareEditMav(
			final LocationChangeForm locationChangeForm,
			final Offender offender, final LocationTermChangeAction action,
			final CorrectionalStatus correctionalStatus) {
		List<LocationReason> reasons = this.changeLocationService
				.findReasonsForChangeAction(action);
		List<Location> locations = this.changeLocationService
				.findLocationsAllowedForActionWithCorrectionalStatus(
						action, correctionalStatus);
		ModelAndView mav = new ModelAndView(EDIT_VIEW);
		mav.addObject(FORM_MODEL_KEY, locationChangeForm);
		mav.addObject(ACTION_MODEL_KEY, action);
		mav.addObject(REASONS_MODEL_KEY, reasons);
		mav.addObject(LOCATIONS_MODEL_KEY, locations);
		this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay form to change location
	private ModelAndView prepareRedisplayMav(
			final LocationChangeForm locationChangeForm,
			final BindingResult result, final Offender offender,
			final LocationTermChangeAction action,
			final CorrectionalStatus correctionalStatus) {
		ModelAndView mav
			= this.prepareEditMav(locationChangeForm, offender, action,
					correctionalStatus);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY, result); 
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
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(CorrectionalStatus.class,
				this.correctionalStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(LocationTermChangeAction.class,
				this.locationTermChangeActionPropertyEditorFactory
					.createPropertyEditor());
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
		binder.registerCustomEditor(Location.class,
				this.locationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(LocationReason.class,
				this.locationReasonPropertyEditorFactory
					.createPropertyEditor());
	}
}