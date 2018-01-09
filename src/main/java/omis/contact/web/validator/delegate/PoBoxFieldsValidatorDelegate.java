package omis.contact.web.validator.delegate;

import omis.contact.web.form.PoBoxFields;
import omis.web.validator.StringLengthChecks;

import org.springframework.validation.Errors;

/**
 * Delegate to handle validation of po box fields.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Jan 28, 2016)
 * @since OMIS 3.0
 */
public class PoBoxFieldsValidatorDelegate {
	
	private final StringLengthChecks stringLengthChecks;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of po box fields validator.
	 */
	public PoBoxFieldsValidatorDelegate(
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
	 * @param poBoxFields po box fields
	 * @param poBoxFieldsVariableName po box fields variable name.
	 * @param errors errors
	 * @return errors
	 */
	public Errors validatePoBoxFields(final PoBoxFields poBoxFields,
		final String poBoxFieldsVariableName, final Errors errors) {
			if (poBoxFields.getCountry() == null) {
				errors.rejectValue(poBoxFieldsVariableName + ".country",
						"poBoxFields.country.empty");
			}
			
			if (poBoxFields.getNewCity() != null 
					&& poBoxFields.getNewCity()) {
				if (poBoxFields.getCityName() == null 
						|| poBoxFields.getCityName().length() < 1) {
					errors.rejectValue(poBoxFieldsVariableName + ".cityName",
							"poBoxFields.cityName.empty");
				} else {
					this.stringLengthChecks.getSmallCheck()
						.check(poBoxFieldsVariableName + ".cityName",
								poBoxFields.getCityName(), errors);
				}
			} else {
				if (poBoxFields.getCity() == null) {
					errors.rejectValue(poBoxFieldsVariableName + ".city",
							"poBoxFields.city.empty");
				}
			}
			
			if (poBoxFields.getNewZipCode() != null 
					&& poBoxFields.getNewZipCode()) {
				if (poBoxFields.getZipCodeValue() == null 
						|| poBoxFields.getZipCodeValue().length() < 1) {
					errors.rejectValue(poBoxFieldsVariableName + ".zipCodeValue",
							"poBoxFields.zipCodeValue.empty");
				} else {
					this.stringLengthChecks.getSmallCheck()
						.check(poBoxFieldsVariableName + ".zipCodeValue",
								poBoxFields.getZipCodeValue(), errors);
				}
			} else {
				if (poBoxFields.getZipCode() == null) {
					errors.rejectValue(poBoxFieldsVariableName + ".zipCode",
							"poBoxFields.zipCode.empty");
				}
			}
			if (poBoxFields.getPoBoxValue() == null 
					|| poBoxFields.getPoBoxValue().length() < 1) {
				errors.rejectValue(poBoxFieldsVariableName + ".poBoxValue",
						"poBoxFields.poBoxValue.empty");
			} else {
				this.stringLengthChecks.getSmallCheck()
					.check(poBoxFieldsVariableName + ".poBoxValue",
							poBoxFields.getPoBoxValue(), errors);
			}
//		}
		return errors;
	}
}