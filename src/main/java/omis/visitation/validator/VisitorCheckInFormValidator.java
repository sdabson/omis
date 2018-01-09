package omis.visitation.validator;

import omis.visitation.web.form.VisitorCheckInForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Visitor check in form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 19, 2016)
 * @since OMIS 3.0
 */
public class VisitorCheckInFormValidator implements Validator {
	
	/* Field names */
	
	private static final String BADGE_NUMBER_FIELD_NAME = "badgeNumber";
	private static final String VISIT_METHOD_FIELD_NAME = "method";
	
	/* Error message keys */
	
	private static final String BADGE_NUMBER_FIELD_EMPTY_MEESAGE_KEY
		= "badgeNumber.empty";
	private static final String VISIT_METHOD_FIELD_EMPTY_MESSAGE_KEY
		= "method.empty";
	
	/**
	 * Instantiates a default instance of visitor check in form validator.
	 */
	public VisitorCheckInFormValidator() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VisitorCheckInForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VisitorCheckInForm form = (VisitorCheckInForm) target;
		if (form.getBadgeNumber().isEmpty()) {
			errors.rejectValue(BADGE_NUMBER_FIELD_NAME,
					BADGE_NUMBER_FIELD_EMPTY_MEESAGE_KEY);
		}
		if (form.getMethod() == null) {
			errors.rejectValue(VISIT_METHOD_FIELD_NAME,
					VISIT_METHOD_FIELD_EMPTY_MESSAGE_KEY);
		}
	}
}