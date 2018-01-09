package omis.offenderflag.web.validator;

import omis.offenderflag.web.form.OffenderFlagForm;
import omis.offenderflag.web.form.OffenderFlagItem;
import omis.offenderflag.web.form.OffenderFlagItemValue;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for offender flags.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagFormValidator
		implements Validator {
	
	/** Instantiates a default validator for form for offender flags. */
	public OffenderFlagFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderFlagForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderFlagForm offenderFlagForm = (OffenderFlagForm) target;
		if (offenderFlagForm.getFlagItems() != null) {
			for (int flagItemIndex = 0;
					flagItemIndex < offenderFlagForm.getFlagItems().size();
					flagItemIndex++) {
				OffenderFlagItem flagItem
					= offenderFlagForm.getFlagItems().get(flagItemIndex);
				if ((flagItem.getValue() == null
							|| OffenderFlagItemValue.NOT_SET
								.equals(flagItem.getValue()))
						&& flagItem.getCategory().getRequired() != null
						&& flagItem.getCategory().getRequired()) {
					errors.rejectValue("flagItems["
						+ flagItemIndex + "]", "offenderFlag.empty");
				}
			}
		}
	}
}