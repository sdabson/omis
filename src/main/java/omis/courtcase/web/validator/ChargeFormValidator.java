package omis.courtcase.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.courtcase.web.form.ChargeForm;

/**
 * Validator for charge form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public class ChargeFormValidator implements Validator {

	/** 
	 * Instantiates a default charge form validator. 
	 */
	public ChargeFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ChargeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ChargeForm chargeForm = (ChargeForm) target;
		if (chargeForm.getOffense() == null) {
			errors.rejectValue("offense", "courtCase.charge.offense.empty");
		}
		if (chargeForm.getDate() == null) {
			errors.rejectValue("date", "courtCase.charge.date.empty");
		}
		if (chargeForm.getCounts() == null) {
			errors.rejectValue("counts", "courtCase.charge.count.empty");
		}
	}

}
