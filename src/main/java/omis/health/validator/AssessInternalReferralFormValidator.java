package omis.health.validator;

import omis.health.validator.delegate.LabWorkAppointmentItemValidatorDelegate;
import omis.health.web.form.AssessInternalReferralForm;
import omis.health.web.form.LabWorkAppointmentItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to assess internal referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0
 */
public class AssessInternalReferralFormValidator
		implements Validator {
	
	private LabWorkAppointmentItemValidatorDelegate 
	labWorkAppointmentItemValidatorDelegate;

	/**
	 * Instantiates a validator for form to assess internal referrals.
	 * 
	 * @param labWorkAppointmentItemValidatorDelegate lab work appointment item
	 * validator delegate
	 */
	public AssessInternalReferralFormValidator(
			final LabWorkAppointmentItemValidatorDelegate 
			labWorkAppointmentItemValidatorDelegate) {
		this.labWorkAppointmentItemValidatorDelegate =
				labWorkAppointmentItemValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AssessInternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		final AssessInternalReferralForm form =
				(AssessInternalReferralForm) target;
		int labWorkAppointmentItemIndex = 0;
	    for (LabWorkAppointmentItem labWorkItem : form.getLabWork()) {
	    	this.labWorkAppointmentItemValidatorDelegate
	    		.validateLabWorkAppointmentItem(labWorkItem, errors, 
	    				labWorkAppointmentItemIndex);
	    	labWorkAppointmentItemIndex++;
	    }
	}
}