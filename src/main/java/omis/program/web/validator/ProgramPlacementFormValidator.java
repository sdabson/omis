package omis.program.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.program.web.form.ProgramPlacementForm;

/**
 * Validator for form for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementFormValidator
		implements Validator {

	/** Instantiates validator for form for program placements. */
	public ProgramPlacementFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProgramPlacementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ProgramPlacementForm programPlacementForm
			= (ProgramPlacementForm) target;
		if (programPlacementForm.getProgram() == null) {
			errors.rejectValue("program", "placementProgram.empty");
		}
		if (programPlacementForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (programPlacementForm.getStartDate() != null
				&& programPlacementForm.getEndDate() != null
				&& programPlacementForm.getStartDate()
					.after(programPlacementForm.getEndDate())) {
			errors.rejectValue(
					"startDate", "dateRange.startDateGreaterThanEndDate");
		}
	}
}