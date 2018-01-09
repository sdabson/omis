package omis.health.validator;

import omis.health.validator.delegate.LabWorkAppointmentItemValidatorDelegate;
import omis.health.web.form.AssessExternalReferralForm;
import omis.health.web.form.LabWorkAppointmentItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to assess external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class AssessExternalReferralFormValidator
		implements Validator {
	
	private LabWorkAppointmentItemValidatorDelegate 
	labWorkAppointmentItemValidatorDelegate;

	/**
	 * Instantiates a validator for form to assess external referrals.
	 * 
	 * @param labWorkAppointmentItemValidatorDelegate delegate to validate
	 * lab work appointment items
	 */
	public AssessExternalReferralFormValidator(
			final LabWorkAppointmentItemValidatorDelegate 
				labWorkAppointmentItemValidatorDelegate) {
		this.labWorkAppointmentItemValidatorDelegate =
				labWorkAppointmentItemValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AssessExternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AssessExternalReferralForm form
			= (AssessExternalReferralForm) target;
		int labWorkAppointmentItemIndex = 0;
	    for (LabWorkAppointmentItem labWorkItem : form.getLabWork()) {
	    	this.labWorkAppointmentItemValidatorDelegate
	    		.validateLabWorkAppointmentItem(labWorkItem, errors, 
	    				labWorkAppointmentItemIndex);
	    	labWorkAppointmentItemIndex++;
	    }
	}
}