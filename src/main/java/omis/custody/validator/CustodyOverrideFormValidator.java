package omis.custody.validator;

import omis.custody.web.form.CustodyOverrideForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Custody Override Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 31, 2013)
 * @since OMIS 3.0
 */
public class CustodyOverrideFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CustodyOverrideForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CustodyOverrideForm form = (CustodyOverrideForm) target;
		
		if (form.getCustodyLevel() == null) {
			errors.rejectValue("custodyLevel",
					"custodyOverride.custodyLevel.empty");
		}
	}
}