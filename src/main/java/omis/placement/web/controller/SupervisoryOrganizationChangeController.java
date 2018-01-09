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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placement.web.controller.delegate.PlacementControllerDelegate;
import omis.placement.web.form.SupervisoryOrganizationChangeForm;
import omis.placement.web.validator.SupervisoryOrganizationChangeFormValidator;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.OffenderSupervisedByOrganizationException;
import omis.supervision.service.ChangeSupervisoryOrganizationService;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to change supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/placement")
@PreAuthorize("hasRole('USER')")
public class SupervisoryOrganizationChangeController {

	/* View names. */
	
	private static final String VIEW_NAME
		= "placement/supervisoryOrganization/change/edit";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "placement/supervisoryOrganization/change/includes/actionMenu";
	
	/* Model keys. */
	
	private static final String FORM_MODEL_KEY
		= "supervisoryOrganizationChangeForm";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String ACTION_MODEL_KEY = "action";

	private static final String SUPERVISORY_ORGANIZATIONS_MODEL_KEY
		= "supervisoryOrganizations";
	
	private static final String CHANGE_REASONS_MODEL_KEY = "changeReasons";

	/* Message keys. */
	
	private static final String OFFENDER_NOT_UNDER_SUPERVISION_MESSAGE_KEY
		= "offender.notUnderSupervisionOnDate";

	private static final String OFFENDER_SUPERVISED_BY_ORGANIZATION_MESSAGE_KEY
		= "offender.supervisedByOrganizationOnDate";

	/* Error bundles. */
	
	private static final String ERROR_BUNDLE_NAME = "omis.placement.msgs.form";
	
	/* Validators. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationChangeFormValidator")
	private SupervisoryOrganizationChangeFormValidator
	supervisoryOrganizationChangeFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("placementTermChangeActionPropertyEditorFactory")
	private PropertyEditorFactory
	placementTermChangeActionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("correctionalStatusPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("placementTermChangeReasonPropertyEditorFactory")
	private PropertyEditorFactory
	placementTermChangeReasonPropertyEditorFactory;
	
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
	
	/* Services. */
	
	@Autowired
	@Qualifier("changeSupervisoryOrganizationService")
	private ChangeSupervisoryOrganizationService
	changeSupervisoryOrganizationService;
	
	/* Constructors. */

	/** Instantiates controller to change supervisory organization. */
	public SupervisoryOrganizationChangeController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to change supervisory organization.
	 * 
	 * @param action change action
	 * @param offender offender
	 * @param correctionalStatus correctional status
	 * @param fromSupervisoryOrganization from supervisory organization
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @return screen to change supervisory organization
	 */
	@RequestMapping(
			value = "/changeSupervisoryOrganization.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PLACEMENT_TERM_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "action", required = true)
				final PlacementTermChangeAction action,
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "correctionalStatus", required = true)
				final CorrectionalStatus correctionalStatus,
			@RequestParam(
					value = "fromSupervisoryOrganization", required = true)
				final SupervisoryOrganization fromSupervisoryOrganization,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime) {
		SupervisoryOrganizationChangeForm supervisoryOrganizationChangeForm
			= new SupervisoryOrganizationChangeForm();
		Date effectiveDate;
		if (defaultEffectiveDate != null) {
			effectiveDate = DateManipulator.getDateAtTimeOfDay(
				defaultEffectiveDate, this.parseTimeText(defaultEffectiveTime));
		} else {
			effectiveDate = new Date();
		}
		supervisoryOrganizationChangeForm.setEffectiveDate(effectiveDate);
		supervisoryOrganizationChangeForm.setEffectiveTime(effectiveDate);
		return this.prepareEditMav(action, offender,
				correctionalStatus, fromSupervisoryOrganization,
				supervisoryOrganizationChangeForm);
	}
	
	/**
	 * Changes supervisory organization.
	 * 
	 * @param action change action
	 * @param offender offender
	 * @param correctionalStatus correctional status
	 * @param fromSupervisoryOrganization from supervisory organization
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param supervisoryOrganizationChangeForm supervisory organization change
	 * form
	 * @param result binding result
	 * @return redirect to placement home
	 * @throws OffenderSupervisedByOrganizationException if offender is
	 * supervised by organization on date
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision on date  
	 */
	@RequestMapping(
			value = "/changeSupervisoryOrganization.html",
			method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "action", required = true)
				final PlacementTermChangeAction action,
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "correctionalStatus", required = true)
				final CorrectionalStatus correctionalStatus,
			@RequestParam(
					value = "fromSupervisoryOrganization", required = true)
				final SupervisoryOrganization fromSupervisoryOrganization,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			final SupervisoryOrganizationChangeForm
				supervisoryOrganizationChangeForm,
			final BindingResult result)
					throws OffenderNotUnderSupervisionException,
						OffenderSupervisedByOrganizationException {
		this.supervisoryOrganizationChangeFormValidator
			.validate(supervisoryOrganizationChangeForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(
					action, offender, correctionalStatus,
					fromSupervisoryOrganization,
					supervisoryOrganizationChangeForm);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,
					result);
			return mav;
		}
		this.changeSupervisoryOrganizationService
			.change(offender,
					supervisoryOrganizationChangeForm
						.getSupervisoryOrganization(),
					supervisoryOrganizationChangeForm
						.getChangeReason(),
					DateManipulator.getDateAtTimeOfDay(
							supervisoryOrganizationChangeForm
								.getEffectiveDate(),
							supervisoryOrganizationChangeForm
								.getEffectiveTime()));
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
	 * Shows action menu for screen to change supervisory organization.
	 * 
	 * @param offender offender
	 * @return action menu for screen to change supervisory organization
	 */
	@RequestMapping(value = "/changeSupervisoryOrganization/actionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code OffenderNotUnderSupervisionException}.
	 * 
	 * @param exception exception
	 * @return screen to handle {@code OffenderNotUnderSupervisionException}
	 */
	@ExceptionHandler(OffenderNotUnderSupervisionException.class)
	public ModelAndView handleOffenderNotUnderSupervisionException(
			final OffenderNotUnderSupervisionException exception) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						OFFENDER_NOT_UNDER_SUPERVISION_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code OffenderSupervisedByOrganizationException}.
	 * 
	 * @param exception exception
	 * @return screen to handle
	 * {@code OffenderSupervisedByOrganizationException}
	 */
	@ExceptionHandler(OffenderSupervisedByOrganizationException.class)
	public ModelAndView handleOffenderSupervisedByOrganizationException(
			final OffenderSupervisedByOrganizationException exception) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						OFFENDER_SUPERVISED_BY_ORGANIZATION_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareEditMav(
			final PlacementTermChangeAction action,
			final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final SupervisoryOrganizationChangeForm
				supervisoryOrganizationChangeForm) {
		List<SupervisoryOrganization> organizations
			= this.changeSupervisoryOrganizationService
				.findAllowedSupervisoryOrganizationsForChange(
						correctionalStatus, supervisoryOrganization);
		List<PlacementTermChangeReason> changeReasons
			= this.changeSupervisoryOrganizationService
				.findAllowedChangeReasons(correctionalStatus, correctionalStatus);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(FORM_MODEL_KEY, supervisoryOrganizationChangeForm);
		mav.addObject(ACTION_MODEL_KEY, action);
		mav.addObject(SUPERVISORY_ORGANIZATIONS_MODEL_KEY, organizations);
		mav.addObject(CHANGE_REASONS_MODEL_KEY, changeReasons);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Parses time - needed as time property editor does not work for request
	// params [SA, Jan 18, 2017]
	private Date parseTimeText(final String timeText) {
		if (timeText != null && !timeText.isEmpty()) {
			PropertyEditor propertyEditor = this.datePropertyEditorFactory
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
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerPropertyEditor(
			final WebDataBinder binder) {
		binder.registerCustomEditor(PlacementTermChangeAction.class,
				this.placementTermChangeActionPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "defaultEffectiveDate",
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "effectiveDate",
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "effectiveTime",
				this.datePropertyEditorFactory
					.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(SupervisoryOrganization.class,
				this.supervisoryOrganizationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(CorrectionalStatus.class,
				this.correctionalStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(PlacementTermChangeReason.class,
				this.placementTermChangeReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}