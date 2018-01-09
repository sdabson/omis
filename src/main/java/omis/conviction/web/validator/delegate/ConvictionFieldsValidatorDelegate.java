package omis.conviction.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.conviction.web.form.ConvictionFields;

/**
 * Delegate to validate conviction fields.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ConvictionFieldsValidatorDelegate {
	
	private static final String FIELDS_PROPERTY_FORMAT = "%s.%s";

	/** Instantiates delegate to validate conviction fields. */
	public ConvictionFieldsValidatorDelegate() {
		// Default instantiation
	}
	
	/**
	 * Validates conviction fields.
	 * 
	 * <p>Returns count of rejected values.
	 * 
	 * @param fields fields
	 * @param convictionFieldsPropertyName fields property name
	 * @param errors errors
	 * @return count of rejected values
	 */
	public int validate(final ConvictionFields fields,
			final String convictionFieldsPropertyName,
			final Errors errors) {
		int count = 0;
		if (fields.getSeverity() == null) {
			errors.rejectValue(
					String.format(FIELDS_PROPERTY_FORMAT,
							convictionFieldsPropertyName, "severity"),
					"conviction.severity.empty");
			count++;
		}
		if (fields.getOffense() == null) {
			errors.rejectValue(
					String.format(FIELDS_PROPERTY_FORMAT,
							convictionFieldsPropertyName, "offense"),
					"conviction.offense.empty");
			count++;
		}
		if (fields.getDate() == null) {
			errors.rejectValue(
					String.format(FIELDS_PROPERTY_FORMAT,
							convictionFieldsPropertyName, "date"),
					"conviction.date.empty");
			count++;
		}
		if (fields.getCounts() == null) {
			errors.rejectValue(
					String.format(FIELDS_PROPERTY_FORMAT,
							convictionFieldsPropertyName, "counts"),
					"conviction.counts.empty");
			count++;
		}
		return count;
	}
}