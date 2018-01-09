package omis.security.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.security.service.AccessAttemptLogger;
import omis.user.domain.UserAccount;
import omis.user.exception.IdenticalPasswordException;
import omis.user.exception.PasswordUsedException;
import omis.user.service.ChangePasswordService;
import omis.user.web.form.PasswordChangeForm;

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
 * Controller to change expired password.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/security")
@PreAuthorize("hasRole('USER')")
public class ChangeExpiredPasswordController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "security/changeExpiredPassword";
	
	/* Model keys. */
	
	private static final String PASSWORD_CHANGE_FORM_MODEL_KEY
		= "passwordChangeForm";

	private static final String REDIRECT_TO_LOGIN = "redirect:/login.html";
	
	/* Property editor factories. */

	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("changePasswordService")
	private ChangePasswordService changePasswordService;
	
	@Autowired
	@Qualifier("accessAttemptLogger")
	private AccessAttemptLogger accessAttemptLogger;
		
	/* Constructor. */
	
	/** Instantiates a controller to change expired password. */
	public ChangeExpiredPasswordController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays form to change expired password.
	 * 
	 * @param userAccount user account
	 * @return form to change expired password
	 */
	@PreAuthorize("hasRole('PASSWORD_CHANGE') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/changeExpiredPassword.html", method = RequestMethod.GET)
	public ModelAndView editExpiredPassword(
			@RequestParam(value = "userAccount", required = true)
				final UserAccount userAccount) {
		if (!userAccount.getEnabled()) {
			return new ModelAndView(REDIRECT_TO_LOGIN);
		}
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(PASSWORD_CHANGE_FORM_MODEL_KEY, new PasswordChangeForm());
		return mav;
	}
	
	/**
	 * Changes expired password.
	 * 
	 * @param userAccount user account
	 * @param passwordChangeForm form containing updated information
	 * @return redirect to login
	 * @throws PasswordUsedException if the password has already been used
	 * @throws IdenticalPasswordException if the new password is that same
	 * as the current one
	 */
	@PreAuthorize("hasRole('PASSWORD_CHANGE') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/changeExpiredPassword.html", method = RequestMethod.POST)
	public ModelAndView updateExpiredPassword(
			@RequestParam(value = "userAccount", required = true)
				final UserAccount userAccount,
			final PasswordChangeForm passwordChangeForm,
			final BindingResult result)
					throws PasswordUsedException,
						IdenticalPasswordException {
		if (!userAccount.getEnabled()) {
			return new ModelAndView(REDIRECT_TO_LOGIN);
		}
		throw new UnsupportedOperationException("Password change not allowed");
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
	}
}