package omis.identificationnumber.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.identificationnumber.service.IdentificationNumberService;
import omis.identificationnumber.web.form.IdentificationNumberForm;
import omis.identificationnumber.web.validator.IdentificationNumberFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to manage identification numbers.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/identificationNumber")
public class ManageIdentificationNumberController {

	/* View names */
	
	private static final String VIEW_NAME = "identificationNumber/edit";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "identificationNumber/includes/identificationNumberActionMenu";
	
	/* Redirects */
	
	private static final String LIST_REDIRECT
		= "redirect:/identificationNumber/list.html?offender=%d";
	
	/* Model keys */
	
	private static final String IDENTIFICATION_NUMBER_FORM_MODEL_KEY
		= "identificationNumberForm";

	private static final String IDENTIFICATION_NUMBER_MODEL_KEY
		= "identificationNumber";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String ISSUERS_MODEL_KEY = "issuers";

	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	/* Error message bundles */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.identificationnumber.msgs.form";
	
	/* Message keys */
	
	private static final String IDENTIFICATION_NUMBER_EXISTS_MESSAGE_KEY
		= "identificationNumber.exists";
	
	private static final String IDENTIFICATION_NUMBER_DATE_CONFLICT_MESSAGE_KEY =
			"identificationNumber.dateConflict";
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("identificationNumberIssuerPropertyEditorFactory")
	private PropertyEditorFactory
	identificationNumberIssuerPropertyEditorFactory;
	
	@Autowired
	@Qualifier("identificationNumberCategoryPropertyEditorFactory")
	private PropertyEditorFactory
	identificationNumberCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("identificationNumberPropertyEditorFactory")
	private PropertyEditorFactory identificationNumberPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Services */
	
	@Autowired
	@Qualifier("identificationNumberService")
	private IdentificationNumberService identificationNumberService;
	
	/* Validators */
	
	@Autowired
	@Qualifier("identificationNumberFormValidator")
	private IdentificationNumberFormValidator identificationNumberFormValidator;
	
	/* Constructors */

	/** Instantiates controller to manage identification numbers. */
	public ManageIdentificationNumberController() {
		// Default instantiation
	}
	
	/* URL invokable methods */
	
	/**
	 * Shows screen to create identification number.
	 * 
	 * @param offender offender
	 * @return screen to create identification number
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		IdentificationNumberForm identificationNumberForm
			= new IdentificationNumberForm();
		return this.prepareMav(identificationNumberForm, offender);
	}
	
	/**
	 * Shows screen to edit identification number.
	 * 
	 * @param identificationNumber identification number to edit
	 * @return screen to edit identification number
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "identificationNumber", required = true)
				final IdentificationNumber identificationNumber) {
		IdentificationNumberForm identificationNumberForm
			= new IdentificationNumberForm();
		identificationNumberForm.setIssuer(identificationNumber.getIssuer());
		identificationNumberForm.setCategory(
				identificationNumber.getCategory());
		identificationNumberForm.setValue(identificationNumber.getValue());
		identificationNumberForm.setIssueDate(
				identificationNumber.getIssueDate());
		identificationNumberForm.setExpireDate(
				identificationNumber.getExpireDate());
		ModelAndView mav = this.prepareMav(identificationNumberForm,
				identificationNumber.getOffender());
		this.addIdentificationNumber(mav.getModelMap(), identificationNumber);
		return mav;
	}
	
	/**
	 * Removes identification number.
	 * 
	 * @param identificationNumber identification number to remove
	 * @return redirect to listing screen
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "identificationNumber", required = true)
				final IdentificationNumber identificationNumber) {
		Offender offender = identificationNumber.getOffender();
		this.identificationNumberService.remove(identificationNumber);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Creates identification number.
	 * 
	 * @param offender offender
	 * @param identificationNumberForm identification number form
	 * @param result binding result
	 * @return redirect to listing screen
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException 
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final IdentificationNumberForm identificationNumberForm,
			final BindingResult result)
				throws DuplicateEntityFoundException, DateConflictException {
		this.identificationNumberFormValidator.validate(
				identificationNumberForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(identificationNumberForm,
					result, offender);
			return mav;
		}
		IdentificationNumber identificationNumber
				= this.identificationNumberService.create(
						offender,
						identificationNumberForm.getIssuer(),
						identificationNumberForm.getCategory(),
						identificationNumberForm.getValue(),
						identificationNumberForm.getIssueDate(),
						identificationNumberForm.getExpireDate());
		return new ModelAndView(String.format(LIST_REDIRECT,
				identificationNumber.getOffender().getId()));
	}
	
	/**
	 * Updates identification number.
	 * 
	 * @param identificationNumber identification number to update
	 * @param identificationNumberForm identification number form
	 * @param result binding result
	 * @return redirect to listing screen
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException 
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "identificationNumber", required = true)
				final IdentificationNumber identificationNumber,
			final IdentificationNumberForm identificationNumberForm,
			final BindingResult result)
				throws DuplicateEntityFoundException, DateConflictException {
		this.identificationNumberFormValidator.validate(
				identificationNumberForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(identificationNumberForm,
					result, identificationNumber.getOffender());
			this.addIdentificationNumber(mav.getModelMap(),
					identificationNumber);
			return mav;
		}
		this.identificationNumberService.update(identificationNumber,
				identificationNumberForm.getIssuer(),
				identificationNumberForm.getCategory(),
				identificationNumberForm.getValue(),
				identificationNumberForm.getIssueDate(),
				identificationNumberForm.getExpireDate());
		return new ModelAndView(String.format(LIST_REDIRECT,
				identificationNumber.getOffender().getId()));
	}
	
	/* Action menus */
	
	/**
	 * Shows action menu.
	 * 
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "/identificationNumberActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Exception handlers */
	
	/**
	 * Handles {@code DuplicateEntityFoundException}.
	 * 
	 * @param exception exception to handle
	 * @return model and view to handle exception
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(IDENTIFICATION_NUMBER_EXISTS_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code DateConflictException}.
	 * 
	 * @param exception exception to handle
	 * @return model and view to handle exception
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
			final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						IDENTIFICATION_NUMBER_DATE_CONFLICT_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, exception);
	}

	/* Helper methods */
	
	// Prepares model and view for edit screen
	private ModelAndView prepareMav(
			final IdentificationNumberForm identificationNumberForm,
			final Offender offender) {
		List<IdentificationNumberIssuer> issuers
			= this.identificationNumberService.findIssuers();
		List<IdentificationNumberCategory> categories
			= this.identificationNumberService.findCategories();
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(IDENTIFICATION_NUMBER_FORM_MODEL_KEY,
				identificationNumberForm);
		mav.addObject(ISSUERS_MODEL_KEY, issuers);
		mav.addObject(CATEGORIES_MODEL_KEY, categories);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay form with validation errors
	private ModelAndView prepareRedisplay(
			final IdentificationNumberForm identificationNumberForm,
			final BindingResult result,
			final Offender offender) {
		ModelAndView mav = this.prepareMav(identificationNumberForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ IDENTIFICATION_NUMBER_FORM_MODEL_KEY,	result);
		return mav;
	}
	
	// Adds identification number to model
	private void addIdentificationNumber(
			final Map<String, Object> modelMap,
			final IdentificationNumber identificationNumber) {
		modelMap.put(IDENTIFICATION_NUMBER_MODEL_KEY, identificationNumber);
	}
	
	/* Init binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param webBinder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder webBinder) {
		webBinder.registerCustomEditor(IdentificationNumberIssuer.class,
				this.identificationNumberIssuerPropertyEditorFactory
					.createPropertyEditor());
		webBinder.registerCustomEditor(IdentificationNumberCategory.class,
				this.identificationNumberCategoryPropertyEditorFactory
					.createPropertyEditor());
		webBinder.registerCustomEditor(IdentificationNumber.class,
				this.identificationNumberPropertyEditorFactory
					.createPropertyEditor());
		webBinder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		webBinder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
	}
}