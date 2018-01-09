package omis.conviction.validator;

import omis.conviction.web.form.ConvictionForm;
import omis.conviction.web.form.ConvictionItem;
import omis.conviction.web.form.ConvictionOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates conviction form.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (May 16, 2017)
 * @since OMIS 3.0
 */
public class ConvictionFormValidator
		implements Validator {

	/** Instantiations a default conviction form validator. */
	public ConvictionFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ConvictionForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ConvictionForm convictionForm = (ConvictionForm) target;
		if (convictionForm.getConvictionItems() == null
				|| convictionForm.getConvictionItems().isEmpty()) {
			errors.rejectValue("convictionItems", "convictionItems.empty");
		}
		int index = 0;
		int convicted = 0;
		for (ConvictionItem convictionItem
				: convictionForm.getConvictionItems()) {
			if (ConvictionOperation.EDIT
					.equals(convictionItem.getOperation())
					|| ConvictionOperation.CREATE
						.equals(convictionItem.getOperation())) {
				if (convictionItem.getOffense() == null) {
					errors.rejectValue("convictionItems[" + index + "].offense",
							"convictionItem.offense.empty");
				}
				if (convictionItem.getCounts() == null) {
					errors.rejectValue("convictionItems[" + index + "].counts",
							"convictionItem.count.empty");
				}
				if (convictionItem.getDate() == null) {
					errors.rejectValue("convictionItems[" + index + "].date",
							"convictionItem.date.empty");
				}
				if (convictionItem.getSeverity() == null) {
					errors.rejectValue("convictionItems[" + index + 
							"].severity", "convictionItem.severity.empty");
				}
				convicted++;
			}
			index++;
		}
		if (convicted == 0) {
			errors.rejectValue("convictionItems", "convictionItems.empty");
		}
	}
}