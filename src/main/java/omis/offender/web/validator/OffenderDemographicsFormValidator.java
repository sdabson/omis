package omis.offender.web.validator;

import omis.offender.web.form.OffenderDemographicsForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for offender demographics.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public class OffenderDemographicsFormValidator
		implements Validator {

	/** Instantiates a default validator for form for offender demographics. */
	public OffenderDemographicsFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderDemographicsForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderDemographicsForm offenderDemographicsForm
			= (OffenderDemographicsForm) target;
		if (offenderDemographicsForm.getEyeColor() == null) {
			errors.rejectValue("eyeColor", "eyeColor.empty");
		}
		if (offenderDemographicsForm.getHairColor() == null) {
			errors.rejectValue("hairColor", "hairColor.empty");
		}
		if (offenderDemographicsForm.getRace() == null) {
			errors.rejectValue("race", "race.empty");
		}
		if (offenderDemographicsForm.getMaritalStatus() == null) {
			errors.rejectValue("maritalStatus", "maritalStatus.empty");
		}
	}
}