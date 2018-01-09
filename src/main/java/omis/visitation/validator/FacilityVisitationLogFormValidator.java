package omis.visitation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.visitation.web.form.FacilityVisitationLogForm;

/**
 * Facility visitation log form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 28, 2017)
 * @since OMIS 3.0
 */
public class FacilityVisitationLogFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return FacilityVisitationLogForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		FacilityVisitationLogForm form = (FacilityVisitationLogForm) target;
		
		if (form.getDate() == null) {
			errors.rejectValue("date", "date.empty");
		}
	}
}
