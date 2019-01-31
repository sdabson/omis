package omis.health.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.datatype.DateRange;
import omis.health.web.form.ReferralCenterFilterForm;

/**
 * Referral center filter form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 23, 2019)
 * @since OMIS 3.0
 */
public class ReferralCenterFilterFormValidator implements Validator {

	/** Instantiates a default instance of referral center filter form validator. */
	private ReferralCenterFilterFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ReferralCenterFilterForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ReferralCenterFilterForm form = (ReferralCenterFilterForm) target;
		if(form.getFilterByOffender() == null) {
			if(form.getFilterByStartDate() == null) {
				errors.rejectValue("filterByStartDate", "referralCenterFilterForm.startDate.empty");
			}
			if(form.getFilterByEndDate() == null) {
				errors.rejectValue("filterByEndDate", "referralCenterFilterForm.endDate.empty");
			}
			if(form.getFilterByStartDate() != null && form.getFilterByEndDate() != null) {
				if(DateRange.countDaysExactly(form.getFilterByStartDate(), form.getFilterByEndDate()) > 30) {
					errors.rejectValue("filterByStartDate",
							"referralCenterFilterForm.startDate.distanceFromEndDate");
				}
			}
		}
	}
}
