package omis.victim.web.validator;

import omis.victim.web.form.VictimNoteForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 26, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteFormValidator
		implements Validator {

	/** Instantiates validator for form for victim notes.  */
	public VictimNoteFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VictimNoteForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VictimNoteForm victimNoteForm = (VictimNoteForm) target;
		if (victimNoteForm.getCategory() == null) {
			errors.rejectValue("category", "victimNoteCategory.empty");
		}
		if (victimNoteForm.getDate() == null) {
			errors.rejectValue("date", "victimNoteDate.empty");
		}
		if (victimNoteForm.getValue() == null
				|| victimNoteForm.getValue().isEmpty()) {
			errors.rejectValue("value", "victimNoteValue.empty");
		}
	}
}