package omis.offenseterm.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenseterm.web.form.OffenseTermDocketForm;

/**
 * Validator for offense term docket form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public class OffenseTermDocketFormValidator 
		implements Validator {

	/** Instantiates a default offense term docket form validator. */
	public OffenseTermDocketFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return OffenseTermDocketForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		OffenseTermDocketForm form = (OffenseTermDocketForm) target;
		if (form.getCourt() == null) {
			errors.rejectValue("court", "offenseTermForm.court.empty");
		}
		if (form.getDocketValue() == null || form.getDocketValue().isEmpty()) {
			errors.rejectValue("docketValue", "offenseTermForm.docket.empty");
		}
	}

}
