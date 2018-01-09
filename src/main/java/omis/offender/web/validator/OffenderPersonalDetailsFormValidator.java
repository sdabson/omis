package omis.offender.web.validator;

import omis.offender.web.form.OffenderPersonalDetailsForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for offender personal details form.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 16, 2013)
 * @since OMIS 3.0
 */
public class OffenderPersonalDetailsFormValidator
		implements Validator {

	/** Instantiates a default validator for offender personal details form. */
	public OffenderPersonalDetailsFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderPersonalDetailsForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderPersonalDetailsForm offenderPersonalDetailsForm
			= (OffenderPersonalDetailsForm) target;
		if (offenderPersonalDetailsForm.getLastName() == null
				|| offenderPersonalDetailsForm.getLastName().length() == 0) {
			errors.rejectValue("lastName", "lastName.empty");
		}
		if (offenderPersonalDetailsForm.getFirstName() == null
				|| offenderPersonalDetailsForm.getFirstName().length() == 0) {
			errors.rejectValue("firstName", "firstName.empty");
		}
		if (offenderPersonalDetailsForm.getBirthDate() == null) {
			errors.rejectValue("birthDate", "birthDate.empty");
		}
		if (offenderPersonalDetailsForm.getBirthCountry() == null) {
			errors.rejectValue("birthCountry", "birthCountry.empty");
		}
		if (offenderPersonalDetailsForm.getCreateNewBirthPlace() != null
				&& offenderPersonalDetailsForm.getCreateNewBirthPlace()
				&& (offenderPersonalDetailsForm.getBirthPlaceName() == null
					|| offenderPersonalDetailsForm.getBirthPlaceName()
						.isEmpty())) {
			errors.rejectValue("birthPlace", "city.nameEmpty");
		}
		if (offenderPersonalDetailsForm.getValidateSocialSecurityNumber()) {
			if (offenderPersonalDetailsForm.getSocialSecurityNumber() != null
					&& !"".equals(offenderPersonalDetailsForm
							.getSocialSecurityNumber())) {
				String socialSecurityNumber = offenderPersonalDetailsForm
						.getSocialSecurityNumber().replace("-", "");
				if (!socialSecurityNumber.matches("\\d+")) {
					errors.rejectValue("socialSecurityNumber",
							"socialSecurityNumber.invalid");
				} else if (socialSecurityNumber.length() != 9) {
					errors.rejectValue("socialSecurityNumber",
							"socialSecurityNumber.wrongLength");
				}
			}
		}
		if (offenderPersonalDetailsForm.getSex() == null) {
			errors.rejectValue("sex", "sex.empty");
		}
		if ((offenderPersonalDetailsForm.getDeceased() == null
				|| !offenderPersonalDetailsForm.getDeceased())
				&& offenderPersonalDetailsForm.getDeathDate() != null) {
			errors.rejectValue("deathDate", "deathDate.notAllowedUnlessDead");
		}
	}
}