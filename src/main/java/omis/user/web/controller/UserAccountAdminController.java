package omis.user.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.security.domain.AccessAttempt;
import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;
import omis.user.web.form.UserAccountForm;
import omis.user.web.validator.UserAccountFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for user account administration.
 * 
 * @author Stephen Abson
 * @version 0.2.0 (March 8, 2016)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user/admin/userAccount")
public class UserAccountAdminController {
	
	/* Model keys. */
	
	private static final String USER_ACCOUNT_FORM_MODEL_KEY
		= "userAccountForm";
	
	private static final String ACCOUNTS_MODEL_KEY
		= "accounts";
	
	private static final String ACCESS_ATTEMPTS_MODEL_KEY
		= "accessAttempts";
	
	private static final String USER_MODEL_KEY = "user";
	
	private static final String USER_ACCOUNT_MODEL_KEY = "userAccount";
	
	private static final String SUFFIXES_MODEL_KEY = "suffixes";
	
	/* View names. */
	
	private static final String USER_ACCOUNT_LIST_VIEW_NAME
		= "user/admin/userAccount/list";
	
	private static final String EDIT_USER_ACCOUNT_VIEW_NAME
		= "user/admin/userAccount/edit";
	
	private static final String USER_ACCOUNTS_ACTION_MENU_VIEW_NAME
		= "user/admin/userAccount/includes/userAccountsActionMenu";

	private static final String USER_ACCOUNT_ACTION_MENU_VIEW_NAME
		= "user/admin/userAccount/includes/userAccountActionMenu";
	
	/* Redirects. */
	
	private static final String ENTER_USER_ACCOUNT_WITH_REDIRECT_URL_REDIRECT
		= "redirect:/user/userAccount/enterUserAccount.html?redirectUrl="
				+ "/user/admin/userAccount/edit.html";
	
	private static final String USER_ACCOUNT_LIST_BY_USER_REDIRECT
		= "redirect:list.html?user=%d";
		
	/* Property editor factories. */
	
	@Autowired
	private PropertyEditorFactory userAccountPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Services. */
	
	@Autowired
	private UserAccountService userAccountService;
	
	/* Validators. */
	
	@Autowired
	private UserAccountFormValidator userAccountFormValidator;
	
	/* Constructor. */

	/** Instantiates a default controller for user account administration. */
	public UserAccountAdminController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Displays a list of accounts for the specified user or all users.
	 * 
	 * @param user optional user to which to limit displayed list
	 * @return model and view to list accounts for all or current user
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER_ACCOUNT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "user", required = false)
				final Person user) {
		ModelAndView mav = new ModelAndView(USER_ACCOUNT_LIST_VIEW_NAME);
		List<UserAccount> accounts;
		if (user != null) {
			accounts = this.userAccountService.findByUser(user);
		} else {
			accounts = this.userAccountService.findAll();
		}
		mav.addObject(ACCOUNTS_MODEL_KEY, accounts);
		mav.addObject(USER_MODEL_KEY, user);
		return mav;
	}
	
	/**
	 * Displays a form allowing the creation of a new user account.
	 * 
	 * @param user person for whom to create user account
	 * @return model and view to form allowing editing of new user account
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER_ACCOUNT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "user", required = false)
				final Person user) {
		UserAccountForm userAccountForm = new UserAccountForm();
		userAccountForm.setAllowUser(true);
		ModelAndView mav = this.prepareEditMav(userAccountForm, user);
		if (user == null) {
			mav.addObject(SUFFIXES_MODEL_KEY, this.userAccountService
					.findSuffixes());
		}
		return mav;
	}
	
	/**
	 * Displays a form allowing the editing of an existing user account.
	 * 
	 * @param account user account to edit
	 * @return model and view to form allowing editing of existing user account
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER_ACCOUNT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "account", required = false)
				final UserAccount account) {
		if (account == null) {
			return new ModelAndView(
					ENTER_USER_ACCOUNT_WITH_REDIRECT_URL_REDIRECT);
		}
		UserAccountForm userAccountForm = new UserAccountForm();
		userAccountForm.setAllowUser(false);
		userAccountForm.setUsername(account.getUsername());
		//userAccountForm.setEnabled(account.getEnabled());
		ModelAndView mav = this.prepareEditMav(userAccountForm,
				account.getUser());
		List<AccessAttempt> accessAttempts = this.userAccountService
				.findByUserAccount(account);
		mav.addObject(ACCESS_ATTEMPTS_MODEL_KEY, accessAttempts);
		mav.addObject(USER_ACCOUNT_MODEL_KEY, account);
		return mav;
	}

	/**
	 * Saves a new user account.
	 * 
	 * @param user person for whom to create the account
	 * @param userAccountForm form from which the create user account
	 * @param result binding result
	 * @return model and view to redirect to listing screen
	 * @throws DuplicateEntityFoundException if account exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER_ACCOUNT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "user", required = false)
				final Person user,
			final UserAccountForm userAccountForm,
			final BindingResult result)
					throws DuplicateEntityFoundException {
		this.userAccountFormValidator.validate(userAccountForm, result);
		if (result.hasErrors()) {
			return this.prepareRedirect(userAccountForm, result, user);
		}
		Person userToUse;
		if (user == null) {
			userToUse = this.userAccountService.createPerson(
							userAccountForm.getLastName(),
							userAccountForm.getFirstName(),
							userAccountForm.getMiddleName(),
							userAccountForm.getSuffix());
		} else {
			userToUse = user;
		}
		Date passwordExpirationDate;
		String password;
		if (userAccountForm.getAllowPassword() != null
				&& userAccountForm.getAllowPassword()) {
			
			// TODO: Implement - SA
			throw new UnsupportedOperationException("Not yet implemented");
		} else {
			passwordExpirationDate = null;
			password = null;	
		}
		this.userAccountService.create(userToUse,
				userAccountForm.getUsername(), password, passwordExpirationDate,
				userAccountForm.getEnabled());
		if (userAccountForm.getAllowGroups() != null
				&& userAccountForm.getAllowGroups()
				&& userAccountForm.getGroups() != null) {
			
			// TODO: Implement - SA
			throw new UnsupportedOperationException("Not yet implemented");
		}
		return new ModelAndView(String.format(
				USER_ACCOUNT_LIST_BY_USER_REDIRECT, userToUse.getId()));
	}
	
	/**
	 * Updates an existing user account.
	 * 
	 * @param account user account to update
	 * @param userAccountForm form from which the update user account
	 * @param result binding result
	 * @return model and view to redirect to listing screen
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER_ACCOUNT_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "account", required = true)
				final UserAccount account,
			final UserAccountForm userAccountForm,
			final BindingResult result) {
		this.userAccountFormValidator.validate(userAccountForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedirect(
					userAccountForm, result, account.getUser());
			List<AccessAttempt> accessAttempts = this.userAccountService
					.findByUserAccount(account);
			mav.addObject(ACCESS_ATTEMPTS_MODEL_KEY, accessAttempts);
			mav.addObject(USER_ACCOUNT_MODEL_KEY, account);
			return mav;
		}
		String password;
		Date passwordExpirationDate;
		if (userAccountForm.getAllowPassword() != null
				&& userAccountForm.getAllowPassword()) {
			
			// TODO: Implement - SA
			throw new UnsupportedOperationException("Not yet implemented");
		} else {
			password = null;
			passwordExpirationDate = null;
		}
		this.userAccountService.update(account, password,
				passwordExpirationDate, userAccountForm.getEnabled());
		if(userAccountForm.getAllowGroups() != null
				&& userAccountForm.getAllowGroups()) {
			
			// TODO: Implement - SA
			throw new UnsupportedOperationException("Not yet implemented");
		}
		return new ModelAndView(String.format(
				USER_ACCOUNT_LIST_BY_USER_REDIRECT,
				account.getUser().getId()));
	}
	
	/**
	 * Removes a user account.
	 * 
	 * @param account user account to remove
	 * @return model and view to redirect to listing screen
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER_ACCOUNT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "account", required = true)
				final UserAccount account) {
		long userId = account.getUser().getId();
		this.userAccountService.remove(account);
		return new ModelAndView(String.format(
				USER_ACCOUNT_LIST_BY_USER_REDIRECT, userId));
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for user accounts.
	 * 
	 * @param user user
	 * @return action menu for user accounts
	 */
	@RequestMapping(value = "/userAccountsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showUserAccountsActionMenu(
			@RequestParam(value = "user", required = false)
				final Person user) {
		ModelAndView mav = new ModelAndView(
				USER_ACCOUNTS_ACTION_MENU_VIEW_NAME);
		mav.addObject(USER_MODEL_KEY, user);
		return mav;
	}
	
	/**
	 * Shows action menu for user account.
	 * 
	 * @param user user
	 * @return action menu for user account
	 */
	@RequestMapping(value = "/userAccountActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showUserAccountActionMenu(
			@RequestParam(value = "user", required = true)
				final Person user) {
		ModelAndView mav = new ModelAndView(
				USER_ACCOUNT_ACTION_MENU_VIEW_NAME);
		mav.addObject(USER_MODEL_KEY, user);
		return mav;
	}
	
	/* Helper methods. */
	
	// Prepares model and view to edit user accounts
	private ModelAndView prepareEditMav(
			final UserAccountForm userAccountForm,
			final Person user) {
		ModelAndView mav = new ModelAndView(EDIT_USER_ACCOUNT_VIEW_NAME);
		mav.addObject(USER_ACCOUNT_FORM_MODEL_KEY, userAccountForm);
		mav.addObject(USER_MODEL_KEY, user);
		return mav;
	}

	// Prepares redirect with binding results
	private ModelAndView prepareRedirect(
			final UserAccountForm userAccountForm,
			final BindingResult result,
			final Person user) {
		ModelAndView mav = this.prepareEditMav(userAccountForm, user);
		mav.addObject(
				BindingResult.MODEL_KEY_PREFIX + USER_ACCOUNT_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/* Controller configuration. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserAccount.class, 
			this.userAccountPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Person.class, 
			this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
	}
}