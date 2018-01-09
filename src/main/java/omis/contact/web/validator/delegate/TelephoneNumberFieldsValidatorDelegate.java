package omis.contact.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.contact.web.form.TelephoneNumberFields;

/**
 * Telephone number fields validator delegate.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 26, 2016)
 * @since OMIS 3.0
 */
public class TelephoneNumberFieldsValidatorDelegate {
	
	/* Constructor. */

	/**
	 * Instantiates a default instance of telephone number fields validator.
	 */
	public TelephoneNumberFieldsValidatorDelegate() {
		// Default constructor.
	}

	/* Validation method. */
	
	public Errors validateTelephoneNumberFields(
			final TelephoneNumberFields telephoneNumberFields, 
			final String telephoneNumberFieldsVariableName,
			final Errors errors) {
		if (telephoneNumberFields.getValue() == null) {
			errors.rejectValue(telephoneNumberFieldsVariableName + ".value", 
					"telephoneNumberFields.value.empty");
		}
		//TODO is this where I need to verify if a primary already exists?
		/*if (telephoneNumberFields.getPrimary() == null) {
			errors.rejectValue(telephoneNumberFieldsVariableName, 
					"telephoneNumberFields.primary.empty");
		}*/
		return errors;
	}
}