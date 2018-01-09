package omis.caseload.web.controller;

import java.util.Date;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.caseload.report.CaseloadReportService;
import omis.caseload.service.CaseloadService;
import omis.caseload.web.form.CaseloadForm;
import omis.caseload.web.validator.CaseloadFormValidator;
import omis.caseload.web.validator.CaseloadOffenderContactFormValidator;
import omis.caseload.web.validator.ReassignOfficerCaseloadFormValidator;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for case load.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 3, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/caseload")
@PreAuthorize("hasRole('USER')")
public class CaseloadController {

	/* Redirect view names, */
	private static final String CASELOAD_LIST_REDIRECT
					= "redirect:/caseload/list.html";
	/* View names. */
	private static final String CASELOAD_ACTION_MENU_VIEW_NAME 
					= "caseload/includes/caseloadActionMenu";
	
	private static final String CASELOAD_VEIW_NAME = "caseload/edit";
	/* Model keys. */
	private static final String CASELOAD_FORM_MODEL_KEY = "editForm";
	
	private static final String CASEWORK_CATEGORIES_MODEL_KEY 
					= "caseworkCategories";
	/* Message Keys. */
	private static final String EXISTS_MESSAGE_KEY
					= "caseload.exists";
	
	private static final String DATE_CONFLICT_MESSAGE_KEY
					= "caseload.assignment.dates.conflict";
	
	private static final String USER_ACCOUNT_MODEL_KEY
					= "userAccount";
	
	/* Message bundles. */
	private static final String ERROR_BUNDLE_NAME
					= "omis.caseload.msgs.form";

	/* Services. */
	
	@Autowired
	@Qualifier("caseloadService")
	private CaseloadService caseloadService;
	
	@Autowired
	@Qualifier("caseloadReportService")
	private CaseloadReportService caseloadReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("caseloadFormValidator")
	private CaseloadFormValidator caseloadFormValidator;
	
	@Autowired
	@Qualifier("caseloadOffenderContactFormValidator")
	private CaseloadOffenderContactFormValidator 
					caseloadOffenderContactFormValidator;
		
	@Autowired
	@Qualifier("reassignOfficerCaseloadFormValidator")
	private ReassignOfficerCaseloadFormValidator 
					reassignOfficerCaseloadFormValidator;
	
	/* Helpers.	 */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("caseloadPropertyEditorFactory")
	private PropertyEditorFactory caseloadPropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
		
	@Autowired	
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Constructors. */
	
	/** Instantiates an implementation of CaseloadController. */
	public CaseloadController() {
		// Default constructor.
	}
	
	/* Screens. */
	
	/** Produces form to create caseload.
	 * @param caseWorker - case worker.
	 * @return caseload create model and view. */
	@RequestContentMapping(nameKey = "caseloadCreateScreenName",
					descriptionKey = "caseloadCreateScreenDescription",
					messageBundle = "omis.caseload.msgs.caseload",
					screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CASELOAD_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
					@RequestParam(value = "caseWorker", required = false)
					final UserAccount caseWorker) {
		final ModelMap modelMap = this.prepareFormModel(new CaseloadForm(), 
						new ModelMap(), caseWorker);
		return new ModelAndView(CASELOAD_VEIW_NAME, modelMap);
	}
	
	/** Creates caseload and forwards to my caseload.
	 * @param caseWorker - case worker.
	 * @param caseloadForm - caseload form.
	 * @param bindingResult - binding result.
	 * @return redirect to my caseloads or redisplays form in the event of 
	 * validation errors.
	 * @throws DuplicateEntityFoundException - When caseload with given name 
	 * exists.
	 * @throws DateConflictException - When caseworker assignment conflicts 
	 * with existing assignment. */
	@RequestContentMapping(nameKey = "caseloadSaveScreenName",
					descriptionKey = "caseloadSaveScreenDescription",
					messageBundle = "omis.caseload.msgs.caseload",
					screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CASELOAD_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
					@RequestParam(value = "caseWorker", required = false)
					final UserAccount caseWorker,
					final CaseloadForm caseloadForm,
					final BindingResult bindingResult) 
							throws DuplicateEntityFoundException, 
							DateConflictException {
		final ModelAndView mav;
		this.caseloadFormValidator.validate(caseloadForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			mav = this.prepareValidationErrorsModelAndView(caseloadForm, 
					bindingResult);
		} else {
			final Caseload caseload = this.caseloadService.createCaseload(
							caseloadForm.getCaseloadName(), 
							caseloadForm.getCaseworkCategory());
			this.caseloadService.createCaseWorkerAssignment(caseload, 
								caseloadForm.getUserAccount().getUser(), 
								caseloadForm.getStartDate(), 
								caseloadForm.getEndDate());
			mav = this.prepareCaseloadsRedirect();
		}
		return mav;	
	}
	
	
	
	/** Action menu for caseload.
	 * @return action menu for caseload details. */
	@RequestContentMapping(nameKey = "caseloadActionMenuName",
					descriptionKey = "caseloadActionMenuDescription",
					messageBundle = "omis.caseload.msgs.caseload",
					screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/caseloadActionMenu.html", 
						method = RequestMethod.GET)
	public ModelAndView caseloadActionMenu() {
		final ModelAndView mav = new ModelAndView(
						CASELOAD_ACTION_MENU_VIEW_NAME);
		return mav;
	}
	
	/**
	 * Retrieves the current users account.
	 *
	 *
	 * @return current user
	 */
	private UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
						.getRequestAttributes()
						.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
							.getAuthentication().getName();
			userAccount = 
					this.caseloadService.findUserAccountByUsername(username);
			RequestContextHolder.getRequestAttributes()
							.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
									RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
	
	/* Prepares form model.
	 * @return model. */
	private ModelMap prepareFormModel(final CaseloadForm caseloadForm, 
					final ModelMap modelMap, final UserAccount userAccount) {
		if (userAccount != null) {
			caseloadForm.setUserAccount(userAccount);
		} else {
			caseloadForm.setUserAccount(this.retrieveUserAccount());
		}
		modelMap.put(CASELOAD_FORM_MODEL_KEY, caseloadForm);
		modelMap.put(CASEWORK_CATEGORIES_MODEL_KEY, CaseworkCategory.values());
		return modelMap;
	}
	
	/* Prepares validation errors mav. */
	private ModelAndView prepareValidationErrorsModelAndView(
					final CaseloadForm caseloadForm, 
					final BindingResult bindingResult) {
		final ModelMap modelMap = this.prepareFormModel(caseloadForm, 
						new ModelMap(), caseloadForm.getUserAccount());
		modelMap.put(BindingResult.MODEL_KEY_PREFIX + CASELOAD_FORM_MODEL_KEY, 
						bindingResult);
		return new ModelAndView(CASELOAD_VEIW_NAME, modelMap);
	}
	
	/* Prepares caseload list redirect. */
	private ModelAndView prepareCaseloadsRedirect() {
		return new ModelAndView(CASELOAD_LIST_REDIRECT);
	}
	
	
	
	/* Init binder. */
	
	/** Init binder.
	 * @param binder web data binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Caseload.class, 
						this.caseloadPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
						this.userAccountPropertyEditorFactory
							.createPropertyEditor());
		binder.registerCustomEditor(
						Date.class,
						this.customDateEditorFactory
							.createCustomDateOnlyEditor(true));
	}
	
		/**
	 * Returns the duplicate entity found exception.
	 * 
	 * @param exception exception
	 * @return business exception
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
					final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
					EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
					exception); 
	}
	
	/**
	 * Handles date conflict exceptions.
	 * 
	 * @param exception date conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
					final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DATE_CONFLICT_MESSAGE_KEY, 
				ERROR_BUNDLE_NAME, exception);
	}
}