package omis.placementscreening.web.validator;

import omis.placementscreening.web.form.ScreeningItemOperation;
import omis.placementscreening.web.form.TreatmentScreeningItem;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** Validator for treatment screening items.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 1, 2015)
 * @since OMIS 3.0 */
public class TreatmentScreeningItemValidator implements Validator {
	private static final String ORDER_EMPTY = "order.empty";
	private static final String REFERRAL_CENTER_EMPTY = 
			"referralScreeningCenter.empty";
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return TreatmentScreeningItem.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		if (((TreatmentScreeningItem) obj).getItemOperation() 
				!= ScreeningItemOperation.REMOVE) {
			ValidationUtils.rejectIfEmpty(errors, "order", ORDER_EMPTY);
			ValidationUtils.rejectIfEmpty(errors, "referralScreeningCenter",
					REFERRAL_CENTER_EMPTY);
		}
	}
}
