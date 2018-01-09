package omis.conviction.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.contact.web.controller.delegate.ContactSummaryModelDelegate;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.ConvictionService;
import omis.conviction.validator.ConvictionFormValidator;
import omis.conviction.web.form.ConvictionForm;
import omis.conviction.web.form.ConvictionItem;
import omis.conviction.web.form.ConvictionOperation;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offense.domain.Offense;
import omis.person.domain.Person;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;
import omis.web.form.FormOperation;

/**
 * Conviction controller.
 * 
 * <p>When editing convictions, charges without convictions are assumed to be
 * dropped. This is in contrast to when creating convictions for the first time
 * for a court case, in which case the charges are assumed to have no
 * operation associated with them. (Viz., the absence of conviction for a charge
 * is interpreted according to whether the convictions have previously been
 * edited. In no situation should no charges have convictions after
 * creation/editing.)
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.5 (Aug 11, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/conviction")
@PreAuthorize(
	"(hasRole('USER') and hasRole('CONVICTION_MODULE')) or hasRole('ADMIN')")
public class ConvictionController {
	
	/* Redirect URLs. */
	
	private static final String SAVE_AND_SENTENCE_REDIRECT_URL
		= "/sentence/createOrEdit.html";
	
	private static final String LIST_REDIRECT_URL = "/courtCase/list.html";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "conviction/edit";
	
	private static final String CONVICTION_ACTION_MENU_VIEW_NAME 
		= "conviction/includes/convictionsActionMenu";
	
	private static final String CONVICTION_ROW_VIEW_NAME 
		= "conviction/includes/editTableRow";
	
	private static final String OFFENSE_VIEW_NAME = "offense/json/offense";
	
	/* Model keys. */
	
	private static final String CONVICTION_FORM_MODEL_KEY = "convictionForm";

	private static final String COURT_CASE_MODEL_KEY = "courtCase";

	private static final String OFFENSES_MODEL_KEY = "offenses";

	private static final String SEVERITIES_MODEL_KEY = "severities";
	
	private static final String CONVICTION_INDEX_MODEL_KEY 
		= "convictionIndex";

	private static final String CURRENT_CONVICTION_INDEX_MODEL_KEY 
		= "currentConvictionIndex";
	
	private static final String CONVICTION_ITEM_OPERATION_MODEL_KEY 
		= "convictionOperation";
	
	private static final String OFFENSE_MODEL_KEY = "offense";
	
	/* Message keys */
	
	private static final String CONVICTION_EXISTS_MESSAGE_KEY
		= "conviction.exists";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = "omis.conviction.msgs.form";

	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("contactSummaryModelDelegate")
	private ContactSummaryModelDelegate contactSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("convictionService")
	private ConvictionService convictionService;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("courtCasePropertyEditorFactory")
	private PropertyEditorFactory courtCasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("offensePropertyEditorFactory")
	private PropertyEditorFactory offensePropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("convictionPropertyEditorFactory")
	private PropertyEditorFactory convictionPropertyEditorFactory;
	
	/* Validators. */
	@Autowired
	@Qualifier("convictionFormValidator")
	private ConvictionFormValidator convictionFormValidator;
	
	/**
	 * Displays a form allowing the creation of the convictions of a court case.
	 * 
	 * @param courtCase court case to convict
	 * @return form allowing creation of convictions of court case
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CONVICTION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase) {
		if (this.convictionService.hasConvictions(courtCase)) {
			// Edit screen should be used instead
			throw new IllegalArgumentException(
					"Court case has existing convictions");
		}
		List<Charge> charges = this.convictionService
				.findChargesByCourtCase(courtCase);
		ConvictionForm convictionForm = new ConvictionForm();
		for (Charge charge : charges) {
			ConvictionItem convictionItem
				= new ConvictionItem();
			convictionItem.setCourtCase(courtCase);
			convictionForm.getConvictionItems().add(convictionItem);
			convictionItem.setOperation(ConvictionOperation.CREATE);
			convictionItem.setCounts(charge.getCounts());
			convictionItem.setDate(charge.getFileDate());
			convictionItem.setOffense(charge.getOffense());
		}
		return prepareMav(convictionForm, courtCase);
	}
	
	/**
	 * Displays a form allowing the update of the convictions of a court case.
	 * 
	 * @param courtCase court case to convict
	 * @return form allowing update of convictions of court case
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CONVICTION_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase) {
		if (!this.convictionService.hasConvictions(courtCase)) {
			// Create screen should be used instead
			throw new IllegalArgumentException(
					"Court case has no existing convictions");
		}
		List<Conviction> convictions = this.convictionService
				.findConvictionsByCourtCase(courtCase);
		ConvictionForm convictionForm = new ConvictionForm();
		for (Conviction conviction : convictions) {
			ConvictionItem convictionItem
				= new ConvictionItem();
			convictionItem.setConviction(conviction);
			convictionItem.setCourtCase(conviction.getCourtCase());
			convictionForm.getConvictionItems().add(convictionItem);
			convictionItem.setCounts(conviction.getCounts());
			convictionItem.setDate(conviction.getDate());
			convictionItem.setOffense(conviction.getOffense());
			convictionItem.setSeverity(conviction.getSeverity());
			convictionItem.setParoleIneligible(conviction.getFlags()
					.getParoleIneligible());
			convictionItem.setSexualOffense(conviction.getFlags()
					.getSexualOffense());
			convictionItem.setSupervisedReleaseIneligible(conviction.getFlags()
					.getSupervisedReleaseIneligible());
			convictionItem.setViolentOffense(conviction.getFlags()
					.getViolentOffense());
			convictionItem.setOperation(ConvictionOperation.EDIT);
			
		}
		return prepareMav(convictionForm, courtCase);
	}
	
	// Prepares a model and view using the specified conviction form
	private ModelAndView prepareMav(final ConvictionForm convictionForm,
			final CourtCase courtCase) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CONVICTION_FORM_MODEL_KEY, convictionForm);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		List<Offense> offenses = this.convictionService.findOffenses();
		mav.addObject(OFFENSES_MODEL_KEY, offenses);
		addOffenderSummary(mav, courtCase.getDocket().getPerson());
		mav.addObject(SEVERITIES_MODEL_KEY, OffenseSeverity.values());
		Integer convictionsCount;
		if (convictionForm.getConvictionItems() != null) {
			convictionsCount = convictionForm.getConvictionItems().size();
		} else {
			convictionsCount = 0;
		}
		mav.addObject(CURRENT_CONVICTION_INDEX_MODEL_KEY, convictionsCount);
		return mav;
	}
	
	// Adds offender summary to a model and view
	private void addOffenderSummary(
			final ModelAndView mav, final Person person) {
		if (this.offenderReportService.summarizeIfOffender(person) != null) {
			// TODO - Add service method that returns offender from person - SA
			this.offenderSummaryModelDelegate.add(mav.getModelMap(), 
					(Offender) person);
		} else {
			this.contactSummaryModelDelegate.add(mav.getModelMap(), person);
		}
	}
	
	/**
	 * Saves convictions of a court case.
	 * 
	 * @param courtCase court case for which to update convictions.
	 * @param operation form operation
	 * @param convictionForm form from which to populate convictions
	 * @param result binding result
	 * @return redirect to listing or sentence screen depending on operation
	 * @throws ConvictionExistsException 
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CONVICTION_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase,
			@RequestParam(value = "operation", required = true)
				final FormOperation operation,
			final ConvictionForm convictionForm,
			final BindingResult result) 
					throws ConvictionExistsException {
		this.convictionFormValidator.validate(convictionForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(convictionForm, courtCase, result);
		}
		if (this.convictionService.hasConvictions(courtCase)) {
			// Edit screen should be used instead
			throw new IllegalArgumentException(
					"Court case has existing convictions");
		}
		int convicted = 0;
		for (ConvictionItem convictionItem
				: convictionForm.getConvictionItems()) {
			this.convictionService.convict(
					courtCase,
					convictionItem.getOffense(),
					convictionItem.getSeverity(),
					convictionItem.getDate(),
					convictionItem.getCounts(),
					new ConvictionFlags(resolveCheckBoxValue(
							convictionItem.getViolentOffense()), 
							resolveCheckBoxValue(
									convictionItem.getSexualOffense()), 
							resolveCheckBoxValue(
									convictionItem.getParoleIneligible()), 
							resolveCheckBoxValue(
									convictionItem
									.getSupervisedReleaseIneligible())));
			convicted++;
		}
		
		// Must be at least 1 conviction
		if (convicted < 1) {
			throw new IllegalArgumentException(
					"Cannot convict without a conviction");
		}
		return prepareRedirectMav(courtCase, operation);
	}
	
	
	/**
	 * Updates convictions of a court case.
	 * 
	 * @param courtCase court case for which to update convictions.
	 * @param operation form operation
	 * @param convictionForm form from which to populate convictions
	 * @param result binding result
	 * @return redirect to listing or sentence screen depending on operation
	 * @throws ConvictionExistsException 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CONVICTION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase,
			@RequestParam(value = "operation", required = true)
				final FormOperation operation,
			final ConvictionForm convictionForm,
			final BindingResult result) 
					throws ConvictionExistsException {
		this.convictionFormValidator.validate(convictionForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(convictionForm, courtCase, result);
		}
		int convicted = 0;
		for (ConvictionItem convictionItem
				: convictionForm.getConvictionItems()) {
			if (ConvictionOperation.CREATE.equals(
					convictionItem.getOperation())) {
				this.convictionService.convict(
						courtCase,
						convictionItem.getOffense(),
						convictionItem.getSeverity(),
						convictionItem.getDate(),
						convictionItem.getCounts(),
						new ConvictionFlags(resolveCheckBoxValue(
								convictionItem.getViolentOffense()), 
								resolveCheckBoxValue(
										convictionItem.getSexualOffense()), 
								resolveCheckBoxValue(
										convictionItem.getParoleIneligible()), 
								resolveCheckBoxValue(
										convictionItem
										.getSupervisedReleaseIneligible())));
				convicted++;
			} else if (ConvictionOperation.EDIT.equals(
					convictionItem.getOperation())) {
				this.convictionService.update(convictionItem.getConviction(), 
						convictionItem.getOffense(), 
						convictionItem.getSeverity(), 
						convictionItem.getDate(), convictionItem.getCounts(), 
						new ConvictionFlags(resolveCheckBoxValue(
								convictionItem.getViolentOffense()), 
								resolveCheckBoxValue(
										convictionItem.getSexualOffense()), 
								resolveCheckBoxValue(
										convictionItem.getParoleIneligible()), 
								resolveCheckBoxValue(
										convictionItem
										.getSupervisedReleaseIneligible())));
				convicted++;
			} else if (ConvictionOperation.REMOVE.equals(
					convictionItem.getOperation())) {
				this.convictionService.remove(convictionItem.getConviction());
			}
		}
		
		// Must be at least 1 conviction
		if (convicted < 1) {
			throw new IllegalArgumentException(
					"Cannot convict without a conviction");
		}
		return prepareRedirectMav(courtCase, operation);
	}
	
	/* Helper methods. */
	
	// Prepares MaV to redisplay validation errors
	private ModelAndView prepareRedisplayMav(
			final ConvictionForm convictionForm,
			final CourtCase courtCase,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareMav(convictionForm, courtCase);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ CONVICTION_FORM_MODEL_KEY, bindingResult);
		return mav;
	}
	
	// Prepares redirect MaV based on operation
	private ModelAndView prepareRedirectMav(
			final CourtCase courtCase, final FormOperation operation) {
		if (FormOperation.SAVE.equals(operation)) {
			return new ModelAndView(
					"redirect:" + LIST_REDIRECT_URL + "?defendant=" 
						+ courtCase.getDocket().getPerson().getId());
		} else if (FormOperation.SAVE_AND_CONTINUE.equals(operation)) {
			return new ModelAndView(
					"redirect:" + SAVE_AND_SENTENCE_REDIRECT_URL
						+ "?courtCase=" + courtCase.getId());
		} else {
			throw new IllegalArgumentException(
					"Unsupported form operation: " + operation);
		}
	}
	
	// Returns true if value is true; false otherwise
	private Boolean resolveCheckBoxValue(final Boolean value) {
		if (value != null && value) {
			return true;
		} else {
			return false;
		}
	}
		
	/* AJAX invokable URLs. */
	
	/** 
	 * Conviction action menu.
	 * 
	 * @param convictionIndex conviction index.
	 * @return action menu. 
	 */
	@RequestMapping(value = "convictionsActionMenu.html", 
		method = RequestMethod.GET)
	public ModelAndView courtCaseNotesActionMenu(
			@RequestParam("convictionIndex") final Long convictionIndex) {
		final ModelMap modelMap = new ModelMap();
		modelMap.put(CONVICTION_INDEX_MODEL_KEY, convictionIndex);
		return new ModelAndView(CONVICTION_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/**
	 * Displays table row of conviction inputs.
	 * 
	 * @param convictionIndex index of conviction for which to display row of 
	 * inputs
	 * @return table row of conviction inputs
	 */
	@RequestMapping(value = "/addConviction.html", method = RequestMethod.GET)
	public ModelAndView addConviction(
			@RequestParam(value = "convictionIndex", required = true)
				final int convictionIndex) {
		ModelAndView mav = new ModelAndView(CONVICTION_ROW_VIEW_NAME);
		mav.addObject(CONVICTION_INDEX_MODEL_KEY, convictionIndex);
		mav.addObject(OFFENSES_MODEL_KEY, 
				this.convictionService.findOffenses());
		mav.addObject(SEVERITIES_MODEL_KEY, OffenseSeverity.values());
		mav.addObject(CONVICTION_ITEM_OPERATION_MODEL_KEY, 
				ConvictionOperation.CREATE);
		return mav;
	}

	/**
	 * Returns URL of offense.
	 * 
	 * @param offense offense
 	 * @return URL of offense
	 */
	@RequestMapping(value = "/findOffense.json", method = RequestMethod.GET)
	public ModelAndView findOffenseUrl(
			@RequestParam(value = "offense", required = true)
				final Offense offense) {
		ModelAndView mav = new ModelAndView(OFFENSE_VIEW_NAME);
		mav.addObject(OFFENSE_MODEL_KEY, offense);
		return mav;
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles conviction exists exceptions.
	 * 
	 * @param exception conviction exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ConvictionExistsException.class)
	public ModelAndView handleConvictionExistsException(
			final ConvictionExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CONVICTION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Init binder. */
	
	/**
	 * Initializes and binds property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(CourtCase.class,
				this.courtCasePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offense.class,
				this.offensePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Conviction.class, 
				this.convictionPropertyEditorFactory.createPropertyEditor());
	}
}