/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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