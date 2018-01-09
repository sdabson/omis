package omis.user.web.validator;

import omis.user.web.form.UserAccountForm;
import omis.util.StringUtility;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for user account form.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 8, 2016)
 * @since OMIS 3.0
 */
public class UserAccountFormValidator
		implements Validator {
	
	/** Instantiates default validator for user account form. */
	public UserAccountFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		UserAccountForm userAccountForm = (UserAccountForm) target;
		if (userAccountForm.getAllowUser() != null
				&& userAccountForm.getAllowUser()) {
			if (StringUtility.isNullOrEmpty(userAccountForm.getLastName())) {
				errors.rejectValue("lastName", "lastName.empty");
			}
			if (StringUtility.isNullOrEmpty(userAccountForm.getFirstName())) {
				errors.rejectValue("firstName", "firstName.empty");
			}
			if (StringUtility.isNullOrEmpty(userAccountForm.getUsername())) {
				errors.rejectValue("username", "username.empty");
			}
		}
		if (userAccountForm.getAllowPassword() != null
				&& userAccountForm.getAllowPassword()) {
			
			// TODO: Implement - SA
			throw new UnsupportedOperationException("Not yet implemented");
		}
	}
}