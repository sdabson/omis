package omis.placementscreening.web.validator;

import omis.placementscreening.web.form.PrereleaseScreeningItem;
import omis.placementscreening.web.form.ScreeningItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** Validator for pre release screening items.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 1, 2015)
 * @since OMIS 3.0 */
public class PrereleaseScreeningItemValidator implements Validator {
	private static final String ORDER_EMPTY = "order.empty"; 
	private static final String REFERRAL_CENTER_EMPTY = 
			"referralScreeningCenter.empty";
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return PrereleaseScreeningItem.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		if (((PrereleaseScreeningItem) obj).getItemOperation() 
				!= ScreeningItemOperation.REMOVE) {
			ValidationUtils.rejectIfEmpty(errors, "order", ORDER_EMPTY);
			ValidationUtils.rejectIfEmpty(errors, "referralScreeningCenter",
					REFERRAL_CENTER_EMPTY);
		}
	}
}
