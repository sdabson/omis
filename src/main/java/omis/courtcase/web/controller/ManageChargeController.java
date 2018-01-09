package omis.courtcase.web.controller;

import java.util.Date;

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
import omis.contact.web.controller.delegate.ContactSummaryModelDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.exception.ChargeExistsException;
import omis.courtcase.exception.CourtCaseNoteExistsException;
import omis.courtcase.service.ChargeService;
import omis.courtcase.web.form.ChargeForm;
import omis.courtcase.web.validator.ChargeFormValidator;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offense.domain.Offense;
import omis.person.domain.Person;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing charges.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 11, 2017)
 * @since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/courtCase")
@PreAuthorize("hasRole('USER')")
public class ManageChargeController {

	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL 
		= "/courtCase/listCharges.html";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "courtCase/editCharge";
	
	private static final String CHARGE_ACTION_MENU_VIEW_NAME 
		= "courtCase/includes/chargeActionMenu";
	
	private static final String CHARGES_ACTION_MENU_VIEW_NAME 
		= "courtCase/includes/chargesActionMenu";
	
	/* Model keys. */
	
	private static final String CHARGE_MODEL_KEY = "charge";

	private static final String CHARGE_FORM_MODEL_KEY = "chargeForm";

	private static final String DEFENDANT_MODEL_KEY = "defendant";

	private static final String OFFENSES_MODEL_KEY = "offenses";

	/* Message keys. */
	
	private static final String CHARGE_EXISTS_MESSAGE_KEY = "charge.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = "omis.courtcase.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("chargeService")
	private ChargeService chargeService;
	
	/* Report service. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offensePropertyEditorFactory")
	private PropertyEditorFactory offensePropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("chargePropertyEditorFactory")
	private PropertyEditorFactory chargePropertyEditorFactory;

	/* Validators. */
	
	@Autowired
	@Qualifier("chargeFormValidator")
	private ChargeFormValidator chargeFormValidator;
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("contactSummaryModelDelegate")
	private ContactSummaryModelDelegate contactSummaryModelDelegate;
	
	@Autowired
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a default court case controller. */
	public ManageChargeController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */

	/**
	 * Displays the form for editing an existing charge.
	 * 
	 * @param charge court case to edit
	 * @return model and view to form for editing a charge
	 */
	@RequestMapping(value = "/editCharge.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('CHARGE_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "charge", required = true)
				final Charge charge) {
		ChargeForm chargeForm = new ChargeForm();
		chargeForm.setOffense(charge.getOffense());
		chargeForm.setCounts(charge.getCounts());
		chargeForm.setDate(charge.getDate());
		chargeForm.setFileDate(charge.getFileDate());
		Person person = charge.getCourtCase().getDocket().getPerson();
		ModelAndView mav = prepareMav(chargeForm, person);
		mav.addObject(CHARGE_MODEL_KEY, charge);
		return mav;
	}

	/**
	 * Updates an existing court case.
	 * 
	 * @param charge charge to update
	 * @param chargeForm charge form
	 * @param result binding result
	 * @return model and view
	 * @throws ChargeExistsException if charge exists
	 */
	@RequestMapping(value = "/editCharge.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('CHARGE_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "charge", required = true)
				final Charge charge,
			final ChargeForm chargeForm,
			final BindingResult result) 
					throws CourtCaseNoteExistsException, ChargeExistsException {
		this.chargeFormValidator.validate(chargeForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(chargeForm, 
					charge.getCourtCase().getDocket().getPerson(), result);
		} 
		this.chargeService.update(charge, chargeForm.getOffense(), 
				chargeForm.getDate(), chargeForm.getFileDate(), 
				chargeForm.getCounts());
		return new ModelAndView(
				"redirect:" + LIST_REDIRECT_URL
				+ "?defendant=" + 
						charge.getCourtCase().getDocket().getPerson().getId());
	}

	/**
	 * Removes a charge.
	 * 
	 * @param charge court case to remove
	 * @param redirectUrl optional URL to which to redirect after removal;
	 * if not specified, redirect to listing screen
	 * @param passDefendant whether to pass the defendant to the URL
	 * @return redirect to optional URL or listing screen
	 */
	@RequestMapping(value = "/removeCharge.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('CHARGE_REMOVE')")
	public ModelAndView remove(@RequestParam(value = "charge", required = true)
				final Charge charge) {
		Person defendant = charge.getCourtCase().getDocket().getPerson();
		this.chargeService.remove(charge);
		return new ModelAndView("redirect:" + LIST_REDIRECT_URL
				+ "?defendant=" + defendant.getId());
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for screen to create/edit charge.
	 * 
	 * @param defendant defendant
	 * @param courtCase court case
	 * @return action menu for screen to create/edit court case
	 */
	@RequestMapping(
			value = "/chargeActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showChargeActionMenu(
			@RequestParam(value = "defendant", required = true)
				final Person defendant) {
		ModelAndView mav = new ModelAndView(CHARGE_ACTION_MENU_VIEW_NAME);
		mav.addObject(DEFENDANT_MODEL_KEY, defendant);
		return mav;
	}
	
	/**
	 * Shows action menu for charges.
	 * 
	 * @param defendant defendant
	 * @param charge charge
	 * @return action menu for offenses
	 */
	@RequestMapping(
			value = "/chargesActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showChargesActionMenu(
			@RequestParam(value = "defendant", required = false)
				final Person defendant,
			@RequestParam(value = "charge", required = false)
				final Charge charge) {
		if (defendant == null && charge == null) {
			throw new RuntimeException("A defendant or charge is required.");
		}
		ModelAndView mav = new ModelAndView(CHARGES_ACTION_MENU_VIEW_NAME);
		if (defendant != null) {
			mav.addObject(DEFENDANT_MODEL_KEY, defendant);
		}
		if (charge != null) {
			mav.addObject(CHARGE_MODEL_KEY, charge);
		}
		return mav;
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles charge exists exceptions.
	 * 
	 * @param exception charge exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ChargeExistsException.class)
	public ModelAndView handleChargeExistsException(
			final ChargeExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CHARGE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares the model and view
	private ModelAndView prepareMav(final ChargeForm chargeForm, 
			final Person person) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CHARGE_FORM_MODEL_KEY, chargeForm);
		mav.addObject(OFFENSES_MODEL_KEY, this.chargeService.findOffenses());
		mav.addObject(DEFENDANT_MODEL_KEY, person);
		if (this.offenderReportService.summarizeIfOffender(person) != null) {
			// TODO - Add service method that returns offender from person - SA
			this.offenderSummaryModelDelegate.add(mav.getModel(), 
					(Offender) person);
		} else {
			this.contactSummaryModelDelegate.add(mav.getModel(), person);
		}
		return mav;
	}
	
	// Prepares the model and view for redisplay after an error
	private ModelAndView prepareRedisplayMav(final ChargeForm chargeForm, 
			final Person person, final BindingResult result) {
		ModelAndView mav = this.prepareMav(chargeForm, person);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + CHARGE_MODEL_KEY, 
				result);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Charge.class,
				this.chargePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offense.class,
				this.offensePropertyEditorFactory.createPropertyEditor());
	}
}
