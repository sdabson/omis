package omis.address.web.validator;

import omis.address.web.form.AddressForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for address forms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 8, 2014)
 * @since OMIS 3.0
 */
public class AddressFormValidator
		implements Validator {

	/** Instantiates a validator for address forms. */
	public AddressFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AddressForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AddressForm addressForm = (AddressForm) target;
		if (addressForm.getValue().isEmpty()) {
			errors.rejectValue("value", "address.value.empty");
		}
		if (addressForm.getZipCode() == null) {
			errors.rejectValue("zipCode", "address.zipCode.empty");
		}
	}
}