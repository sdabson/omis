package omis.placement.web.validator;

import omis.placement.web.form.ProgramPlacementChangeForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to change program placement.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 2, 2016)
 * @since OMIS 3.0
 */
public class ProgramPlacementChangeFormValidator
		implements Validator {

	/** Instantiates validator for form to change program placements. */
	public ProgramPlacementChangeFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProgramPlacementChangeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ProgramPlacementChangeForm programPlacementChangeForm
			= (ProgramPlacementChangeForm) target;
		if (programPlacementChangeForm.getProgram() == null) {
			errors.rejectValue("program", "placementProgram.empty");
		}
	}
}