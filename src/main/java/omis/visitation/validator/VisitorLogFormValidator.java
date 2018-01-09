package omis.visitation.validator;

import omis.visitation.web.form.VisitorLogForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Visitor Log Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 23, 2013)
 * @since OMIS 3.0
 */
public class VisitorLogFormValidator implements Validator {
	
	/** Instantiates a default instance of visitor log form validator. */
	public VisitorLogFormValidator() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VisitorLogForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VisitorLogForm form = (VisitorLogForm) target;
		
		if (form.getSingleDate()) {
			if (form.getStartDate() == null) {
				errors.rejectValue("startDate", "firstDate.empty");
			}
		} else {
			if (form.getStartDate() != null && form.getEndDate() != null) {
				if (form.getStartDate().getTime() > form.getEndDate().getTime()) {
					errors.rejectValue("startDate", "startDate.afterEndDate");
				}
			}
		}
	}
}