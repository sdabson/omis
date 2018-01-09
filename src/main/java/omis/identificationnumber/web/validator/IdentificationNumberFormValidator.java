package omis.identificationnumber.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.identificationnumber.web.form.IdentificationNumberForm;
import omis.web.validator.StringLengthChecks;

/**
 * Validator for form for identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberFormValidator
		implements Validator {
	
	/* Resources */
	
	private final StringLengthChecks stringLengthChecks;

	/* Constructors. */
	
	/**
	 * Instantiates validator for form for identification numbers.
	 *
	 * @param stringLengthChecks string length checks
	 */
	public IdentificationNumberFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return IdentificationNumberForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		IdentificationNumberForm form = (IdentificationNumberForm) target;
		if (form.getIssuer() == null) {
			errors.rejectValue("issuer", "identificationNumber.issuerRequired");
		}
		if (form.getCategory() == null) {
			errors.rejectValue(
					"category", "identificationNumber.categoryRequired");
		}
		if (form.getValue() == null || "".equals(form.getValue())) {
			errors.rejectValue("value", "identificationNumber.valueRequired");
		}
		if (form.getExpireDate() != null && form.getIssueDate() != null
				&& form.getExpireDate().before(form.getIssueDate()))  {
			errors.rejectValue("expireDate",
					"identificationNumber.expireDateBeforeIssueDate");
		}
		this.stringLengthChecks.getMediumCheck().check(
				"value", form.getValue(), errors);
	}
}