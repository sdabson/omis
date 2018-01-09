package omis.health.validator;

import omis.health.validator.delegate.LabWorkAppointmentItemValidatorDelegate;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.health.web.form.ScheduleInternalReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Schedule request form validator.
 *
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Apr 23, 2014)
 * @since OMIS 3.0
 */
public class ScheduleInternalReferralFormValidator implements Validator {
	
	private LabWorkAppointmentItemValidatorDelegate 
	labWorkAppointmentItemValidatorDelegate;
	
	/**
	 * Instantiates an instance of schedule internal referral form validator
	 * with the specified delegate.
	 * 
	 * @param labWorkAppointmentItemValidatorDelegate lab work appointment item
	 * validator delegate
	 */
	public ScheduleInternalReferralFormValidator(
			final LabWorkAppointmentItemValidatorDelegate 
			labWorkAppointmentItemValidatorDelegate) {
		this.labWorkAppointmentItemValidatorDelegate =
				labWorkAppointmentItemValidatorDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ScheduleInternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ScheduleInternalReferralForm form =
				(ScheduleInternalReferralForm) target;

		if (form.getProviderAssignment() == null) {
			errors.rejectValue("providerAssignment", 
					"providerAssignment.empty");
		}
		if (form.getReason() == null) {
			errors.rejectValue("reason", "internalReferralReason.empty");
		}
		if (form.getScheduleDate() == null) {
			errors.rejectValue("scheduleDate", "scheduleDate.empty");
		}
		if (form.getOffenderRequired() && form.getOffender() == null) {
			errors.rejectValue("offender", "offender.empty");
		}
		if (form.getStatusReasonRequired() && form.getStatusReason() == null) {
			errors.rejectValue("statusReason", "statusReason.empty");
		}
		int labWorkAppointmentItemIndex = 0;
		for (LabWorkAppointmentItem labWorkItem : form.getLabWork()) {
			this.labWorkAppointmentItemValidatorDelegate
			.validateLabWorkAppointmentItem(labWorkItem, errors, 
					labWorkAppointmentItemIndex);
			labWorkAppointmentItemIndex++;
		}
	}
}