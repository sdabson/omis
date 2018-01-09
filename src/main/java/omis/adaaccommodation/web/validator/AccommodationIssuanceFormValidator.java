package omis.adaaccommodation.web.validator;

import omis.adaaccommodation.web.form.AccommodationIssuanceForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Accommodation form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 29, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceFormValidator implements Validator {

	/** Instantiates a validator for accommodation issuance forms. */
	public AccommodationIssuanceFormValidator() {
		// Default constructor
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AccommodationIssuanceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AccommodationIssuanceForm form = (AccommodationIssuanceForm) target;

		if (form.getDay() == null) {
			errors.rejectValue("day", "accommodationIssuance.day.empty");
		}
		if (form.getTime() == null) {
			errors.rejectValue("time", 
					"accommodationIssuance.time.empty");
		}
		if (form.getText() == "") {
			errors.rejectValue("text", 
					"accommodationIssuance.description.empty");
		}
		if (form.getIssuer() == null) {
			errors.rejectValue("issuer", "accommodationIssuance.issuer.empty");
		}
	}	
}