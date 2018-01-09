package omis.health.validator;

import omis.health.validator.delegate.LabWorkSampleItemValidatorDelegate;
import omis.health.web.form.LabWorkSampleItem;
import omis.health.web.form.ScheduleLabWorkReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Lab work form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 6, 2014)
 * @since OMIS 3.0
 */
public class ScheduleLabWorkReferralFormValidator implements Validator {

	private LabWorkSampleItemValidatorDelegate 
	labWorkSampleItemValidatorDelegate;
	
	/**
	 * Instantiates a schedule lab work referral form validator with the
	 * specified lab work sample item validator delegate.
	 * 
	 * @param labWorkSampleItemValidatorDelegate lab work sample item validator
	 * delegate
	 */
	public ScheduleLabWorkReferralFormValidator(
			final LabWorkSampleItemValidatorDelegate 
			labWorkSampleItemValidatorDelegate) {
		this.labWorkSampleItemValidatorDelegate = 
				labWorkSampleItemValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ScheduleLabWorkReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ScheduleLabWorkReferralForm form = (ScheduleLabWorkReferralForm) target;
		
		if (form.getOffender() == null && form.getOffenderRequired()) {
			errors.rejectValue("offender", "labWork.offender.empty");
		}
		if (form.getSampleDate() == null) {
			errors.rejectValue("sampleDate", "labWork.sampleDate.empty");
		}
		if (form.getLabWorkSampleItems() == null 
				|| form.getLabWorkSampleItems().size() < 1) {
			errors.rejectValue("labWorkSampleItems", 
					"labWork.sampleItems.empty");
		} else {
			int labWorkSampleItemIndex = 0;
			for (LabWorkSampleItem item : form.getLabWorkSampleItems()) {
				this.labWorkSampleItemValidatorDelegate
				.validateLabWorkSampleItem(item, errors, 
						labWorkSampleItemIndex);
				labWorkSampleItemIndex++;
			}
		}
	}
}