package omis.userpreference.web.validator;

import omis.userpreference.web.form.UserPreferenceForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User preference form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public class UserPreferenceFormValidator implements Validator {
	
	/**
	 * Instantiates a default instance of user preference form validator.
	 */
	public UserPreferenceFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return UserPreferenceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		UserPreferenceForm form = (UserPreferenceForm) target;
		
		if(form.getBackgroundHue() == null) {
			errors.rejectValue("backgroundHue",
					"userPreference.backgroundHue.empty");
		}
		if(form.getBackgroundSaturation() == null) {
			errors.rejectValue("backgroundSaturation",
					"userPreference.backgroundSaturation.empty");
		}
		if(form.getForegroundHue() == null) {
			errors.rejectValue("foregroundHue",
					"userPreference.foregroundHue.empty");
		}
		if(form.getForegroundSaturation() == null) {
			errors.rejectValue("foregroundSaturation",
					"userPreference.foregroundSaturation.empty");
		}
	}
}