package omis.courtcase.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.courtcase.web.form.CourtCaseFields;

/**
 * Delegate to validate fields for court case.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseFieldsValidatorDelegate {

	/** Instantiates validator for fields for court case. */
	public CourtCaseFieldsValidatorDelegate() {
		// Default instantiation
	}
	
	/**
	 * Validates court case fields.
	 * 
	 * @param fields fields
	 * @param courtCaseFieldsPropertyName fields property name
	 * @param errors errors
	 */
	public void validate(final CourtCaseFields fields,
			final String courtCaseFieldsPropertyName,
			final Errors errors) {
		if (fields.getAllowCourt() != null
				&& fields.getAllowCourt()) {
			if (fields.getCourt() == null) {
				errors.rejectValue(
						String.format("%s.%s", courtCaseFieldsPropertyName,
								"court"), "courtCase.court.empty");
			}
		}
		if (fields.getAllowDocket() != null
				&& fields.getAllowDocket()) {
			if (fields.getDocketValue() == null
					|| fields.getDocketValue().isEmpty()) {
				errors.rejectValue(
					String.format("%s.%s", courtCaseFieldsPropertyName,
						"docketValue"), "courtCase.docket.value.empty");
			}
		}
		if (fields.getJudge() == null) {
			errors.rejectValue(String.format("%s.%s", courtCaseFieldsPropertyName,
						"judge"), "courtCase.judge.empty");
		}
	}
}