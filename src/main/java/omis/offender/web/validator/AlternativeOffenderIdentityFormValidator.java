package omis.offender.web.validator;

import omis.offender.web.form.AlternativeOffenderIdentityForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for alternative offender identities.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 10, 2013)
 * @since OMIS 3.0
 */
public class AlternativeOffenderIdentityFormValidator
		implements Validator {

	/**
	 * Instantiates a validator for form for alternative offender identities.
	 */
	public AlternativeOffenderIdentityFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AlternativeOffenderIdentityForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AlternativeOffenderIdentityForm alternativeOffenderIdentityForm
				= (AlternativeOffenderIdentityForm) target;
		if (alternativeOffenderIdentityForm.getCategory() == null) {
			errors.rejectValue("category", "alternativeIdentityCategory.empty");
		}
		if (alternativeOffenderIdentityForm.getStartDate() != null
				&& alternativeOffenderIdentityForm.getEndDate() != null
				&& alternativeOffenderIdentityForm.getStartDate().getTime()
				> alternativeOffenderIdentityForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (alternativeOffenderIdentityForm.getBirthDate() == null
				&& alternativeOffenderIdentityForm.getBirthCountry() == null
				&& alternativeOffenderIdentityForm.getBirthPlace() == null
				&& alternativeOffenderIdentityForm.getSex() == null
				&& alternativeOffenderIdentityForm
					.getSocialSecurityNumber() == null) {
			errors.rejectValue("offenderIdentityGroup", "identity.empty");
		}
		if (alternativeOffenderIdentityForm.getSocialSecurityNumber() != null
				&& !"".equals(alternativeOffenderIdentityForm
						.getSocialSecurityNumber())) {
			String socialSecurityNumber = alternativeOffenderIdentityForm
					.getSocialSecurityNumber().replace("-", "");
			if (!socialSecurityNumber.matches("\\d+")) {
				errors.rejectValue("socialSecurityNumber",
						"socialSecurityNumber.invalid");
			} else if (socialSecurityNumber.length() != 9) {
				errors.rejectValue("socialSecurityNumber",
						"socialSecurityNumber.wrongLength");
			}
		}
		if (alternativeOffenderIdentityForm.getCreateNewBirthPlace() != null
				&& alternativeOffenderIdentityForm.getCreateNewBirthPlace()
				&& (alternativeOffenderIdentityForm.getBirthPlaceName() == null
					|| alternativeOffenderIdentityForm.getBirthPlaceName()
						.isEmpty())) {
			errors.rejectValue("birthPlace", "city.nameEmpty");
		}
	}
}