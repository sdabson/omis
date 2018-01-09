package omis.health.validator;

import omis.health.validator.delegate.LabWorkAssessmentItemValidatorDelegate;
import omis.health.web.form.AssessLabWorkReferralForm;
import omis.health.web.form.LabWorkAssessmentItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for assess lab work referral form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 13, 2014)
 * @since OMIS 3.0
 */
public class AssessLabWorkReferralFormValidator 
	implements Validator {

	private LabWorkAssessmentItemValidatorDelegate 
	labWorkAssessmentItemValidatorDelegate;
	
	/**
	 * Instantiates a default instance of assess lab work referral form 
	 * validator.
	 */
	public AssessLabWorkReferralFormValidator(
			final LabWorkAssessmentItemValidatorDelegate 
			labWorkAssessmentItemValidatorDelegate) {
		this.labWorkAssessmentItemValidatorDelegate = 
				labWorkAssessmentItemValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AssessLabWorkReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AssessLabWorkReferralForm form = (AssessLabWorkReferralForm) target;
		if (form.getLabWorkAssessmentItems().size() < 1) {
			errors.rejectValue("labWorkAssessmentItems", 
					"assessLabWorkReferral.labWorkAssessmentItems.empty");
		} else {
			int labWorkAssessmentItemIndex = 0;
			for (LabWorkAssessmentItem item : 
				form.getLabWorkAssessmentItems()) {
				this.labWorkAssessmentItemValidatorDelegate
				.validateLabWorkAssessmentItem(item, errors, 
						labWorkAssessmentItemIndex);
				labWorkAssessmentItemIndex++;
			}
		}
	}
}