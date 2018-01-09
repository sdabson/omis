package omis.family.web.validator;

import omis.family.web.form.FamilyAssociationForm;
import omis.family.web.validator.delegate.FamilyAssociationFieldsValidatorDelegate;
import omis.health.web.form.AssessExternalReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for family association form.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 18, 2016)
 * @since OMIS 3.0
 */
public class FamilyAssociationFormValidator
		implements Validator {
	private FamilyAssociationFieldsValidatorDelegate 
	 familyAssociationFieldsValidatorDelegate;

	/**
	 * Instantiates a validator for family association form.
	 * 
	 * @param familyAssociationFieldsValidatorDelegate family association fields
	 * validator delegate
	 */
	public FamilyAssociationFormValidator(
			final FamilyAssociationFieldsValidatorDelegate 
			familyAssociationFieldsValidatorDelegate) {
		this.familyAssociationFieldsValidatorDelegate =
			familyAssociationFieldsValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AssessExternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		FamilyAssociationForm familyAssociationFields 
			= (FamilyAssociationForm) target;
		this.familyAssociationFieldsValidatorDelegate.validate(
			familyAssociationFields, errors);
	}
}