package omis.address.web.validator.delegate;

import omis.address.web.form.AddressFields;
import omis.web.validator.StringLengthChecks;

import org.springframework.validation.Errors;

/**
 * Delegate to handle validation of person fields.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 9, 2015)
 * @since OMIS 3.0
 */
public class AddressFieldsValidatorDelegate {
	
	private final StringLengthChecks stringLengthChecks;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of address fields validator.
	 */
	public AddressFieldsValidatorDelegate(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/* Validation method. */

	/**
	 * Returns {@code errors} with any additional rejected values for the
	 * specified address fields. The {@code addressFieldsVariableName} should be
	 * the same as the variable name used on the command object in which the
	 * addressFields component is included.
	 * 
	 * @param addressFields address fields
	 * @param addressFieldsVariableName address fields variable name.
	 * @param errors errors
	 * @return errors
	 */
	public Errors validateAddressFields(final AddressFields addressFields,
		final String addressFieldsVariableName, final Errors errors) {
		if (addressFields.getValue() == null 
				|| addressFields.getValue().length() < 1) {
			errors.rejectValue(addressFieldsVariableName + ".value",
					"addressFields.value.empty");
		}
		if (addressFields.getCountry() == null) {
			errors.rejectValue(addressFieldsVariableName + ".country",
					"addressFields.country.empty");
		}
		if (addressFields.getNewCity()) {
			if (addressFields.getCityName() == null
					|| addressFields.getCityName().length() < 1) {
				errors.rejectValue(addressFieldsVariableName + ".cityName",
						"addressFields.cityName.empty");
			} else {
				this.stringLengthChecks.getMediumCheck().check(
						addressFieldsVariableName + ".cityName",
						addressFields.getCityName(), errors);
			}
		} else {
			if (addressFields.getCity() == null) {
				errors.rejectValue(addressFieldsVariableName + ".city",
						"addressFields.city.empty");
			}
		}
		if (addressFields.getNewZipCode() != null 
				&& addressFields.getNewZipCode()) {
			if (addressFields.getZipCodeValue() == null 
					|| addressFields.getZipCodeValue().length() < 1) {
				errors.rejectValue(addressFieldsVariableName + ".zipCodeValue",
						"addressFields.zipCodeValue.empty");
			} else {
				this.stringLengthChecks.getSmallCheck()
					.check(addressFieldsVariableName + ".zipCodeValue",
							addressFields.getZipCodeValue(), errors);
			}
		} else {
			if (addressFields.getZipCode() == null) {
				errors.rejectValue(addressFieldsVariableName + ".zipCode",
						"addressFields.zipCode.empty");
			}
		}
		return errors;
	}
}