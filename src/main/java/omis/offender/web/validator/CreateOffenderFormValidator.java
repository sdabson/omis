package omis.offender.web.validator;

import omis.offender.web.form.CreateOffenderFlagItem;
import omis.offender.web.form.CreateOffenderForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to create offenders.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2014)
 * @since OMIS 3.0
 */
public class CreateOffenderFormValidator
		implements Validator {

	/** Instantiates a validator for form to create offenders. */
	public CreateOffenderFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CreateOffenderForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CreateOffenderForm createOffenderForm = (CreateOffenderForm) target;
		if (createOffenderForm.getLastName() == null
				|| "".equals(createOffenderForm.getLastName())) {
			errors.rejectValue("lastName", "lastName.empty");
		}
		if (createOffenderForm.getFirstName() == null
				|| "".equals(createOffenderForm.getFirstName())) {
			errors.rejectValue("firstName", "firstName.empty");
		}
		if (createOffenderForm.getBirthDate() == null) {
			errors.rejectValue("birthDate", "birthDate.empty");
		}
		if (createOffenderForm.getBirthCountry() == null) {
			errors.rejectValue("birthCountry", "birthCountry.empty");
		}
		if (createOffenderForm.getCreateNewBirthPlace() != null
				&& createOffenderForm.getCreateNewBirthPlace()
				&& (createOffenderForm.getBirthPlaceName() == null
					|| createOffenderForm.getBirthPlaceName().isEmpty())) {
			errors.rejectValue("birthPlace", "city.nameEmpty");
		}
		if (createOffenderForm.getSocialSecurityNumber() != null
				&& !"".equals(createOffenderForm.getSocialSecurityNumber())) {
			String socialSecurityNumber = createOffenderForm
					.getSocialSecurityNumber().toString().replace("-", "");
			if (!socialSecurityNumber.matches("\\d+")) {
				errors.rejectValue("socialSecurityNumber",
						"socialSecurityNumber.invalid");
			} else if (socialSecurityNumber.length() != 9) {
				errors.rejectValue("socialSecurityNumber",
						"socialSecurityNumber.wrongLength");
			}
		}
		if (createOffenderForm.getEyeColor() == null) {
			errors.rejectValue("eyeColor", "eyeColor.empty");
		}
		if (createOffenderForm.getHairColor() == null) {
			errors.rejectValue("hairColor", "hairColor.empty");
		}
		if (createOffenderForm.getRace() == null) {
			errors.rejectValue("race", "race.empty");
		}
		if (createOffenderForm.getMaritalStatus() == null) {
			errors.rejectValue("maritalStatus", "maritalStatus.empty");
		}
		if (createOffenderForm.getSex() == null) {
			errors.rejectValue("sex", "sex.empty");
		}
		if (createOffenderForm.getPhotoData() != null
				&& createOffenderForm.getPhotoData().length > 0
				&& createOffenderForm.getPhotoDate() == null) {
			errors.rejectValue("photoDate", "photoDate.empty");
		}
		if ((createOffenderForm.getPhotoData() == null
				|| createOffenderForm.getPhotoData().length == 0)
				&& createOffenderForm.getPhotoDate() != null) {
			errors.rejectValue("photoData", "photo.empty");
		}
		if (createOffenderForm.getFlagItems() != null) {
			for (int flagItemIndex = 0;
					flagItemIndex < createOffenderForm.getFlagItems().size();
					flagItemIndex++) {
				CreateOffenderFlagItem flagItem =
						createOffenderForm.getFlagItems().get(flagItemIndex);
				if (flagItem != null) {
					if (flagItem.getValue() == null) {
						errors.rejectValue("flagItems[" + flagItemIndex
								+ "]", "offenderFlag.empty");
					}
				}
;			}
		}
	}
}