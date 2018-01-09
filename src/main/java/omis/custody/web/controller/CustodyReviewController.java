package omis.custody.web.controller;

import java.util.Date;

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
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.custody.service.CustodyReviewService;
import omis.custody.validator.CustodyOverrideFormValidator;
import omis.custody.validator.CustodyReviewFormValidator;
import omis.custody.web.form.CustodyOverrideForm;
import omis.custody.web.form.CustodyReviewForm;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for custody review.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 08 2013)
 * @since OMIS 3.0
 */

@Controller
@RequestMapping("/custody")
@PreAuthorize("hasRole('USER')")
public class CustodyReviewController {

	/* Redirect URLs. */
	
	private static final String UP_DIRECTORY_LIST_REDIRECT = 
			"redirect:../list.html?offender=%d";
	
	private static final String LIST_REDIRECT = "redirect:list.html?offender=%d";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "custody/edit";
	
	private static final String OVERRIDE_CREATE_VIEW_NAME = 
			"custody/override/create";
	
	private static final String CUSTODY_REVIEW_ACTION_MENU_VIEW_NAME
			= "custody/includes/custodyReviewActionMenu";
	
	/* Model Keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String CUSTODY_REVIEW_MODEL_KEY = "custodyReview";
	
	private static final String CUSTODY_REVIEW_FORM_MODEL_KEY = 
			"custodyReviewForm";
	
	private static final String LEVELS_MODEL_KEY = "levels";
	
	private static final String CUSTODY_OVERRIDE_FORM_MODEL_KEY = 
			"custodyOverrideForm";
	
	private static final String CHANGE_REASONS_MODEL_KEY = "changeReasons";
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.custody.msgs.form";
	
	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_EXCEPTION_MESSAGE_KEY
		= "custodyReview.exists";
	
	@Autowired
	@Qualifier("custodyReviewService")
	private CustodyReviewService custodyReviewService;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("custodyReviewPropertyEditorFactory")
	private PropertyEditorFactory custodyReviewPropertyEditorFactory;

	@Autowired
	@Qualifier("custodyChangeReasonPropertyEditorFactory")
	private PropertyEditorFactory custodyChangeReasonPropertyEditorFactory;

	@Autowired
	@Qualifier("custodyLevelPropertyEditorFactory")
	private PropertyEditorFactory custodyLevelPropertyEditorFactory;

	@Autowired
	@Qualifier("custodyOverridePropertyEditorFactory")
	private PropertyEditorFactory custodyOverridePropertyEditorFactory;

	@Autowired
	@Qualifier("custodyReviewFormValidator")
	private CustodyReviewFormValidator custodyReviewFormValidator;
	
	@Autowired
	@Qualifier("custodyOverrideFormValidator")
	private CustodyOverrideFormValidator custodyOverrideFormValidator;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**Instantiates a default instance of custody review controller. */ 
	public CustodyReviewController() {
		//empty constructor
	}

	/**
	 * Displays the custody review form for viewing/editing a
	 * specified custody review.
	 * 
	 * @param custodyReview custody review
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyReviewEditScreenName",
			descriptionKey = "custodyReviewEditScreenDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_VIEW') or hasRole('ADMIN')")
	public ModelAndView editCustodyReview(@RequestParam(value = "custodyReview",
			required = true) final CustodyReview custodyReview) {
		ModelMap map = new ModelMap();
		CustodyReviewForm form = new CustodyReviewForm();
		prepareCustodyReviewForm(form, custodyReview);
		return prepareCustodyReviewEditMav(map, form, custodyReview);
	}
	
	/**
	 * Updates the specified custody override with an authorization signature.
	 * 
	 * @param id id
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyOverrideAuthorizeScreenName",
			descriptionKey = "custodyOverrideAuthorizeScreenDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/override/authorize.html", method = 
			RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_OVERRIDE_AUTHORIZE') or hasRole('ADMIN')")
	public ModelAndView authorizeCustodyOverride(@RequestParam(
			value = "custodyOverride", required = true) 
			final CustodyOverride custodyOverride) {
		CustodyOverride override = this.custodyReviewService
				.authorizeOverride(custodyOverride);
		return new ModelAndView(String.format(UP_DIRECTORY_LIST_REDIRECT, 
				override.getCustodyReview().getOffender().getId()));
	}

	/**
	 * Submits the custody review form and updates the specified custody
	 * review with values from the edit screen. 
	 * 
	 * @param form custody review form
	 * @param custodyReview custody review
	 * @param result result
	 * @return model and view
	 * @throws DuplicateEntityFoundException duplicate entity found exception 
	 */
	@RequestContentMapping(nameKey = "custodyReviewEditName",
			descriptionKey = "custodyReviewEditDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_EDIT') or hasRole('ADMIN')")
	public ModelAndView updateCustodyReview(final CustodyReviewForm form,
			@RequestParam(value = "custodyReview", required = true)
		final CustodyReview custodyReview, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.custodyReviewFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			return prepareCustodyReviewEditMav(map, form, custodyReview);
		}
		this.custodyReviewService.update(custodyReview, 
				custodyReview.getOffender(), form.getCustodyLevel(), 
				form.getChangeReason(), form.getActionDate(), 
				form.getNextReviewDate());
		return new ModelAndView(String.format(LIST_REDIRECT, 
				custodyReview.getOffender().getId()));
	}
	
	/**
	 * Displays the custody review form for the creation of a new custody
	 * review.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyReviewCreateScreenName",
			descriptionKey = "custodyReviewCreateScreenDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_CREATE') or hasRole('ADMIN')")
	public ModelAndView createCustodyReview(@RequestParam(value = "offender",
			required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		CustodyReviewForm form = new CustodyReviewForm();
		return prepareCreateCustodyReviewMav(map, form, offender);
	}
	
	/**
	 * Displays the custody override form (partial) for the creation of a new
	 * custody override.
	 * 
	 * @param custodyReview custody review
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyOverrideCreateScreenName",
			descriptionKey = "custodyOverrideCreateScreenDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/override/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_OVERRIDE_CREATE') or hasRole('ADMIN')")
	public ModelAndView createCustodyOverride(@RequestParam(value = 
			"custodyReview", required = true) 
			final CustodyReview custodyReview) {
		ModelMap map = new ModelMap();
		CustodyOverrideForm form = new CustodyOverrideForm();
		return prepareCreateCustodyOverrideMav(map, form, custodyReview);
	}
	
	/**
	 * Submits the creation of a new custody review for the specified offender
	 * with values from the custody review form on the custody review create 
	 * screen.
	 * 
	 * @param form custody review form
	 * @param offender offender
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException duplicate entity found exception 
	 */
	@RequestContentMapping(nameKey = "custodyReviewSaveName",
			descriptionKey = "custodyReviewSaveDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveCustodyReview(final CustodyReviewForm form,
			@RequestParam(value = "offender", required = true)
			final Offender offender, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.custodyReviewFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			return prepareCreateCustodyReviewMav(map, form, offender);
		}
		this.custodyReviewService.create(offender, form.getCustodyLevel(), 
				form.getChangeReason(), form.getActionDate(), 
				form.getNextReviewDate());
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Submits the creation of a new custody override for the specified custody
	 * review with values from the custody override form on the custody
	 * override create screen.
	 * 
	 * @param form custody override form
	 * @param custodyReview custody review
	 * @param result binding result
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyOverrideSaveName",
			descriptionKey = "custodyOverrideSaveDescription",
			messageBundle = "omis.custody.msgs.custodyOverride",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/override/create.html", method = 
			RequestMethod.POST)
	@PreAuthorize("hasRole('CUSTODY_OVERRIDE_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveCustodyOverride(final CustodyOverrideForm form,
			@RequestParam(value = "custodyReview", required = true) 
			final CustodyReview custodyReview, final BindingResult result) 
					throws DuplicateEntityFoundException {
		this.custodyOverrideFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			return prepareCreateCustodyOverrideMav(map, form, custodyReview);
		}
		CustodyOverride custodyOverride = this.custodyReviewService
				.overrideReview(custodyReview, form.getCustodyLevel());
		return new ModelAndView(String.format(UP_DIRECTORY_LIST_REDIRECT, 
				custodyOverride.getCustodyReview().getOffender().getId()));
	}
	
	/**
	 * Removes the specified custody review and returns to the custody review
	 * list screen.
	 * 
	 * @param custodyReview custody review
	 * @return redirect string
	 */
	@RequestContentMapping(nameKey = "custodyReviewRemoveName",
			descriptionKey = "custodyReviewRemoveDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_REMOVE') or hasRole('ADMIN')")
	public String removeCustodyReview(@RequestParam(value = "custodyReview", 
			required = true) final CustodyReview custodyReview) {
		CustodyOverride custodyOverride = this.custodyReviewService
				.findOverrideByReview(custodyReview);
		if (custodyOverride != null) {
			this.custodyReviewService.removeOverride(custodyOverride);
		}
		Long offenderId = custodyReview.getOffender().getId();
		this.custodyReviewService.remove(custodyReview);
		return String.format(LIST_REDIRECT, offenderId);
	}
	
	/**
	 * Removes the specified custody override and returns to the custody review
	 * list screen.
	 * 
	 * @param id id
	 * @return redirect string
	 */
	@RequestContentMapping(nameKey = "custodyOverrideRemoveName",
			descriptionKey = "custodyOverrideRemoveDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/override/remove.html", method = 
			RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_OVERRIDE_REMOVE') or hasRole('ADMIN')")
	public String removeCustodyOverride(@RequestParam(value = "custodyOverride",
			required = true) final CustodyOverride custodyOverride) {
		Long offenderId = custodyOverride.getCustodyReview()
				.getOffender().getId();
		this.custodyReviewService.removeOverride(custodyOverride);
		return String.format(UP_DIRECTORY_LIST_REDIRECT, offenderId);
	}
	
	/* Exception handlers. */
	
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
	
	/* Helper methods. */
	
	/*
	 * Prepares a model and view object with attributes necessary to create
	 * a new custody review.
	 */
	private ModelAndView prepareCreateCustodyReviewMav(final ModelMap map,
			final CustodyReviewForm form, final Offender offender) {
		prepareDropDownListCustodyReview(map);
		map.addAttribute(CUSTODY_REVIEW_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Prepare a model and view object with attributes needed to edit an
	 * existing custody review.
	 */
	private ModelAndView prepareCustodyReviewEditMav(final ModelMap map,
			final CustodyReviewForm form, final CustodyReview custodyReview) {
		prepareDropDownListCustodyReview(map);
		map.addAttribute(CUSTODY_REVIEW_MODEL_KEY, custodyReview);
		map.addAttribute(CUSTODY_REVIEW_FORM_MODEL_KEY, form);
		this.offenderSummaryModelDelegate.add(map, custodyReview.getOffender());
		map.addAttribute(OFFENDER_MODEL_KEY, custodyReview.getOffender());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}

	/*
	 * Prepares a model and view object with attributes necessary to create a
	 * new custody override.
	 */
	private ModelAndView prepareCreateCustodyOverrideMav(final ModelMap map,
			final CustodyOverrideForm form, final CustodyReview custodyReview) {
		map.addAttribute(LEVELS_MODEL_KEY, this.custodyReviewService
				.findCustodyLevels());
		map.addAttribute(CUSTODY_OVERRIDE_FORM_MODEL_KEY, form);
		map.addAttribute(CUSTODY_REVIEW_MODEL_KEY, custodyReview);
		this.offenderSummaryModelDelegate.add(map, custodyReview.getOffender());
		return new ModelAndView(OVERRIDE_CREATE_VIEW_NAME, map);
	}
	
	/*
	 * Finds all closed text options needed for the custody review form
	 * for the current Model Map.
	 */	
	private void prepareDropDownListCustodyReview(final ModelMap map) {
		map.addAttribute(CHANGE_REASONS_MODEL_KEY, this
				.custodyReviewService.findCustodyChangeReasons());
		map.addAttribute(LEVELS_MODEL_KEY, this.custodyReviewService
				.findCustodyLevels());
	}

	/*
	 * Prepares the custody review edit form based off of existing information 
	 * from the specified custody review.
	 */
	private void prepareCustodyReviewForm(final CustodyReviewForm form,
			final CustodyReview custodyReview) {
		form.setChangeReason(custodyReview.getChangeReason());
		form.setActionDate(custodyReview.getActionDate());
		form.setNextReviewDate(custodyReview.getNextReviewDate());
		form.setCustodyLevel(custodyReview.getCustodyLevel());
	}
	
	/**
	 * Returns a view of the custody review action menu.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestMapping(value = "/custodyReviewActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView custodyReviewActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CUSTODY_REVIEW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				CustodyReview.class,
				this.custodyReviewPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				CustodyChangeReason.class,
				this.custodyChangeReasonPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				CustodyLevel.class,
				this.custodyLevelPropertyEditorFactory
				.createPropertyEditor());

		binder.registerCustomEditor(
				CustodyOverride.class,
				this.custodyOverridePropertyEditorFactory
				.createPropertyEditor());

		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}