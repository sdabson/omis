package omis.user.web.validator;

import omis.user.web.form.UserSearchForm;
import omis.user.web.form.UserSearchType;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to search for users.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class UserSearchFormValidator
		implements Validator {

	/* Constructors. */
	
	/** Validator for form to search for users. */
	public UserSearchFormValidator() {
		// Default instantiation
	}
	
	/* Methods. */
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return UserSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		UserSearchForm userSearchForm = (UserSearchForm) target;
		if (userSearchForm.getType() == null) {
			errors.rejectValue("type", "userSearchType.empty");
		} else if (UserSearchType.NAME.equals(userSearchForm.getType())) {
			if ((userSearchForm.getLastName() == null
					|| userSearchForm.getLastName().isEmpty())
				&& (userSearchForm.getFirstName() == null
					|| userSearchForm.getFirstName().isEmpty())) {
				errors.rejectValue("lastName", "userSearchName.empty");
			}
		} else if (UserSearchType.USERNAME.equals(userSearchForm.getType())) {
			if (userSearchForm.getUsername() == null
					|| userSearchForm.getUsername().isEmpty()) {
				errors.rejectValue("username", "userSearchUsername.empty");
			}
		} else {
			throw new UnsupportedOperationException("Unknown search type");
		}
	}
}