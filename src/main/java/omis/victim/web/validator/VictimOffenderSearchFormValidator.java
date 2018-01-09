package omis.victim.web.validator;

import omis.offender.web.validator.delegate.OffenderSearchFieldsValidatorDelegate;
import omis.victim.web.form.VictimOffenderSearchForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to search for offenders.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 11, 2015)
 * @since OMIS 3.0
 */
public class VictimOffenderSearchFormValidator
		implements Validator {
	
	private final OffenderSearchFieldsValidatorDelegate
	offenderSearchFieldsValidatorDelegate;

	/**
	 * Instantiates validator for form to search for offenders.
	 * 
	 * @param offenderSearchFieldsValidatorDelegate delegate to validate
	 * offender search fields
	 */
	public VictimOffenderSearchFormValidator(
			final OffenderSearchFieldsValidatorDelegate
				offenderSearchFieldsValidatorDelegate) {
		this.offenderSearchFieldsValidatorDelegate
			= offenderSearchFieldsValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VictimOffenderSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VictimOffenderSearchForm victimOffenderSearchForm
			= (VictimOffenderSearchForm) target;
		this.offenderSearchFieldsValidatorDelegate.validate(
				victimOffenderSearchForm.getOffenderSearchFields(), errors);
	}	
}