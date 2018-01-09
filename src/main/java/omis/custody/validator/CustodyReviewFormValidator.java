package omis.custody.validator;

import omis.custody.web.form.CustodyReviewForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Custody Review Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 31, 2013)
 * @since OMIS 3.0
 */
public class CustodyReviewFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CustodyReviewForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CustodyReviewForm form = (CustodyReviewForm) target;
		
		if (form.getChangeReason() == null) {
			errors.rejectValue("changeReason", 
					"custodyReview.changeReason.empty");
		}
		if (form.getActionDate() == null) {
			errors.rejectValue("actionDate", 
					"custodyReview.actionDate.empty");
		}
		if (form.getCustodyLevel() == null) {
			errors.rejectValue("custodyLevel", 
					"custodyReview.custodyLevel.empty");
		}
		if (form.getActionDate() != null 
				&&  form.getNextReviewDate() != null) {
			if (form.getActionDate().getTime() 
					>  form.getNextReviewDate().getTime()) {
				errors.rejectValue("actionDate", 
						"custodyReview.actionDate.afterNextReviewDate");
			}
		}
	}
}