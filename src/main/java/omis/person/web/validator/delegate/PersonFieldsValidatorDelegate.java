package omis.person.web.validator.delegate;

import omis.person.web.form.PersonFields;
import omis.web.validator.StringLengthChecks;

import org.springframework.validation.Errors;

/**
 * Delegate to handle validation of person fields.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 9, 2015)
 * @since OMIS 3.0
 */
public class PersonFieldsValidatorDelegate {

	private final StringLengthChecks stringLengthChecks;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of person fields validator.
	 */
	public PersonFieldsValidatorDelegate(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/* Validation method. */

	/**
	 * Returns {@code errors} with any additional rejected values for the
	 * specified person fields. The {@code personFieldsVariableName} should be
	 * the same as the variable name used on the command object in which the
	 * personFields component is included.
	 * 
	 * @param personFields person fields
	 * @param personFieldsVariableName person fields variable name.
	 * @param errors errors
	 * @return errors
	 */
	public Errors validatePersonFields(final PersonFields personFields,
		final String personFieldsVariableName, final Errors errors) {
		if (personFields.getFirstName() == null || 
				personFields.getFirstName().length() < 1) {
			errors.rejectValue(personFieldsVariableName + ".firstName",
					"personFields.firstName.empty");
		}
		if (personFields.getLastName() == null ||
				personFields.getLastName().length() < 1) {
			errors.rejectValue(personFieldsVariableName + ".lastName",
					"personFields.lastName.empty");
		}
		if (personFields.getNewCity()) {
			if (personFields.getCityName() == null
					|| personFields.getCityName().length() < 1) {
				errors.rejectValue(personFieldsVariableName + ".cityName",
						"personFields.cityName.empty");
			} else {
				this.stringLengthChecks.getMediumCheck().check(
						personFieldsVariableName + ".cityName",
						personFields.getCityName(), errors);
			}
		}
		return errors;
	}
}