package omis.user.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import omis.user.exception.IdenticalPasswordException;
import omis.user.exception.PasswordUsedException;
import omis.user.web.form.PasswordChangeForm;

/**
 * Allows user accounts to be managed and for the passwords for logged in
 * users to be changed.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 8, 2013)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserManagerController {
	
	/* View names. */
	
	private static final String CHANGE_PASSWORD_VIEW_NAME
		= "user/changePassword";
	
	/* Model keys. */
	
	private static final String PASSWORD_CHANGE_FORM_MODEL_KEY
		= "passwordChangeForm";
	
	/* Constructor. */
	
	/** Instantiates a default controller for user management. */
	public UserManagerController() {
		// Default instantiation
	}
	
	/* URL invoked methods. */
	
	/**
	 * Shows a page which allows the password of the current user to be changed.
	 * 
	 * @return model and view to password change page
	 */
	@PreAuthorize("hasRole('USER_ACCOUNT_PASSWORD_CHANGE') or hasRole('ADMIN')")
	@RequestMapping(value = "/changePassword.html", method = RequestMethod.GET)
	public ModelAndView showChangePasswordForm() {
		ModelAndView mav = new ModelAndView(CHANGE_PASSWORD_VIEW_NAME);
		mav.addObject(PASSWORD_CHANGE_FORM_MODEL_KEY, new PasswordChangeForm());
		return mav;
	}
	
	/**
	 * Changes the password of the current account of the current user.
	 * 
	 * @param passwordChangeForm form containing old password and new and
	 * verification of new password
	 * @return model and view to password changed successfully page
	 * @throws IdenticalPasswordException if the new password is identical to
	 * the old one
	 * @throws PasswordUsedException if the password is already used 
	 * @throws IllegalArgumentException if the old password specified
	 * on the form does not match the current password or if the new and
	 * verification of new passwords do not match
	 */
	@PreAuthorize("hasRole('USER_ACCOUNT_PASSWORD_CHANGE') or hasRole('ADMIN')")
	@RequestMapping(value = "/changePassword.html", method = RequestMethod.POST)
	public ModelAndView changePassword(
			final PasswordChangeForm passwordChangeForm)
					throws PasswordUsedException, IdenticalPasswordException {
		
		// TODO: Not supported - consider removing
		throw new UnsupportedOperationException(
				"Password change not supported");
	}
}