package omis.contact.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.contact.web.form.ContactFields;

/**
 * Delegate to handle validation of contact fields.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 21, 2015)
 * @since OMIS 3.0
 */
public class ContactFieldsValidatorDelegate {

	/* Constructor. */
	
	/**
	 * Instantiates a default instance of contact fields validator.
	 */
	public ContactFieldsValidatorDelegate() {
		// Default Constructor
	}

	/* Validation method. */
	
	/**
	 * 
	 * @param contactFields contact fields
	 * @param contactFieldsVariableName contact fields variable name
	 * @param errors errors
	 * @return error
	 */
	public Errors validateContactFields(final ContactFields contactFields, 
			final String contactFieldsVariableName, final Errors errors) {
		
		if (contactFields.getMailingAddressFields().getValue() == null
				|| contactFields.getMailingAddressFields()
				.getValue().isEmpty()) {
			errors.rejectValue(contactFieldsVariableName 
					+ ".mailingAddressFields", 
					"contactFields.mailingAddressFields.houseNumber.empty");
		}
		if (contactFields.getMailingAddressFields().getCountry() == null) {
			errors.rejectValue(contactFieldsVariableName 
					+ ".mailingAddressFields.country", 
					"contactFields.mailingAddressFields.country.empty");
		}
		if (contactFields.getMailingAddressFields().getState() == null) {
			errors.rejectValue(contactFieldsVariableName 
					+ ".mailingAddressFields.state", 
					"contactFields.mailingAddressFields.state.empty");
		}
		if (contactFields.getMailingAddressFields().getNewCity()) {
			if (contactFields.getMailingAddressFields().getCityName() == null
					|| contactFields.getMailingAddressFields()
					.getCityName().length() < 1) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".mailingAddressFields.city", 
						"contactFields.mailingAddressFields.cityName.empty");
			} 
		} else {
			if (contactFields.getMailingAddressFields().getCity() == null) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".mailingAddressFields.city", 
						"contactFields.mailingAddressFields.city.empty");
			}
		}
		if (contactFields.getMailingAddressFields().getNewZipCode() != null
				&& contactFields.getMailingAddressFields().getNewZipCode()) {
			if (contactFields.getMailingAddressFields()
					.getZipCodeValue() == null
					|| contactFields.getMailingAddressFields()
					.getZipCodeValue().length() < 1) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".mailingAddressFields.zipCodeValue", 
						"contactFields.mailingAddressFields"
						+ ".zipCodeValue.empty");
			} 	
		} else {
			if (contactFields.getMailingAddressFields()
					.getZipCodeValue() == null) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".mailingAddressFields.zipCode", 
						"contactFields.mailingAddressFields.zipCode.empty");
			}
		}
		if (contactFields.getPoBoxFields().getCountry() == null) {
			errors.rejectValue(contactFieldsVariableName 
					+ ".poBoxFields.country", 
					"contactFields.poBoxFields.country.empty");			
		}
		if (contactFields.getPoBoxFields().getState() == null) {
			errors.rejectValue(contactFieldsVariableName + ".poBoxFields.state",
					"contactFields.poBoxFields.state.empty");
		}
		if (contactFields.getPoBoxFields().getCity() == null) {
			errors.rejectValue(contactFieldsVariableName + ".poBoxFields.city", 
					"contactFields.poBoxFields.city.empty");
		}
		if (contactFields.getPoBoxFields().getNewZipCode() != null
				&& contactFields.getPoBoxFields().getNewZipCode()) {
			if (contactFields.getPoBoxFields().getZipCodeValue() == null
					|| contactFields.getPoBoxFields()
					.getZipCodeValue().length() < 1) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".poBoxFields.zipCodeValue", 
						"contactFields.poBoxFields.zipCodeValue.empty");
			} 
		} else {
			if (contactFields.getPoBoxFields().getZipCodeValue() == null) {
				errors.rejectValue(contactFieldsVariableName 
						+ ".poBoxFields.zipCode", 
						"contactFields.poBoxFields.zipCode.empty");
			}
		}	
		return errors;
	}
}