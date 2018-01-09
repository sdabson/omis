package omis.placement.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placement.service.ChangeProgramPlacementService;
import omis.placement.web.controller.delegate.PlacementControllerDelegate;
import omis.placement.web.form.ProgramPlacementChangeForm;
import omis.placement.web.validator.ProgramPlacementChangeFormValidator;
import omis.program.domain.Program;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to change program placement.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 2, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/placement")
@PreAuthorize("hasRole('USER')")
public class ProgramPlacementChangeController {

	/* View names. */
	
	private static final String VIEW_NAME = "placement/program/change/edit";
	
	private static final String ACTION_MENU_VIEW_NAME 
		= "placement/program/change/includes/actionMenu";
	
	/* Model key names. */
	
	private static final String PROGRAM_PLACEMENT_CHANGE_FORM_MODEL_KEY
		= "programPlacementChangeForm";

	private static final String PROGRAMS_MODEL_KEY = "programs";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Message keys. */
	
	private static final String PROGRAM_PLACEMENT_EXISTS_MESSAGE_KEY
		= "programPlacement.exists";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME = "omis.program.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("changeProgramPlacementService")
	private ChangeProgramPlacementService changeProgramPlacementService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("programPlacementChangeFormValidator")
	private ProgramPlacementChangeFormValidator
	programPlacementChangeFormValidator;
	
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

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("programPropertyEditorFactory")
	private PropertyEditorFactory programPropertyEditorFactory;
	
	/* Constructors. */
	
	/** Instantiates controller to change program placement. */
	public ProgramPlacementChangeController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to change program placement.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @return model and view to change program placement
	 */
	@RequestMapping(
			value = "/changeProgram.html", method = RequestMethod.GET)
	@PreAuthorize(
			"hasRole('PROGRAM_PLACEMENT_CHANGE_VIEW') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime) {
		Date effectiveDateTime;
		if (defaultEffectiveDate != null) {
			effectiveDateTime = DateManipulator.getDateAtTimeOfDay(
					defaultEffectiveDate,
					this.parseTimeText(defaultEffectiveTime));
		} else {
			effectiveDateTime = new Date();
		}
		ProgramPlacementChangeForm programPlacementChangeForm
				= new ProgramPlacementChangeForm();
		programPlacementChangeForm.setStartDate(effectiveDateTime);
		return this.prepareMav(offender, effectiveDateTime,
				programPlacementChangeForm);
	}
	
	/**
	 * Changes program placement.
	 * 
	 * @param offender offender
	 * @param defaultEffectiveDate effective date
	 * @param defaultEffectiveTime effective time
	 * @param programPlacementChangeForm form to change program placement
	 * @param result binding result
	 * @return redirect to placement home screen
	 * @throws DuplicateEntityFoundException if program placement exists
	 */
	@RequestMapping(
			value = "/changeProgram.html", method = RequestMethod.POST)
	@PreAuthorize(
			"hasRole('PROGRAM_PLACEMENT_CHANGE_EDIT') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "defaultEffectiveTime", required = false)
				final String defaultEffectiveTime,
			final ProgramPlacementChangeForm programPlacementChangeForm,
			final BindingResult result)
				throws DuplicateEntityFoundException {
		this.programPlacementChangeFormValidator.validate(
				programPlacementChangeForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(
					offender, defaultEffectiveDate, programPlacementChangeForm,
					result);
		}
		this.changeProgramPlacementService.change(offender,
				programPlacementChangeForm.getStartDate(),
				programPlacementChangeForm.getEndDate(),
				programPlacementChangeForm.getProgram());
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
	@RequestMapping(value = "/changeProgramActionMenu.html",
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
	 * Handles {@code DuplicateEntityFoundException}s.
	 * 
	 * @param exception {@code DuplicateEntityFoundException} to handle
	 * @return model and view to handle {@code DuplicateEntityFoundException}
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PROGRAM_PLACEMENT_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view to edit program placements
	private ModelAndView prepareMav(
			final Offender offender,
			final Date effectiveDate,
			final ProgramPlacementChangeForm programPlacementChangeForm) {
		List<Program> programs = this.changeProgramPlacementService
				.findAllowedProgramsForOffenderOnDate(
						offender, effectiveDate);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(PROGRAM_PLACEMENT_CHANGE_FORM_MODEL_KEY,
				programPlacementChangeForm);
		mav.addObject(PROGRAMS_MODEL_KEY, programs);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay screen to edit program placements
	private ModelAndView prepareRedisplayMav(
			final Offender offender,
			final Date effectiveDate,
			final ProgramPlacementChangeForm programPlacementChangeForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(
				offender, effectiveDate, programPlacementChangeForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ PROGRAM_PLACEMENT_CHANGE_FORM_MODEL_KEY, result);
		return mav;
	}
	
	// Parses time
	private Date parseTimeText(final String timeText) {
		if (timeText != null && !timeText.isEmpty()) {
			CustomDateEditor propertyEditor
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
	protected void registerPropertyEditors(
			final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Program.class,
				this.programPropertyEditorFactory
					.createPropertyEditor());
	}
}