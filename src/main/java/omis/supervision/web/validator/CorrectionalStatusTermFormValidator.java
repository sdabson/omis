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
package omis.supervision.web.validator;

import omis.supervision.web.form.CorrectionalStatusTermForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for correctional status terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 9, 2014)
 * @since OMIS 3.0
 */
public class CorrectionalStatusTermFormValidator
		implements Validator {

	/** Instantiates a validator for form for correctional status terms. */
	public CorrectionalStatusTermFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CorrectionalStatusTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CorrectionalStatusTermForm correctionalStatusTermForm
			= (CorrectionalStatusTermForm) target;
		if (correctionalStatusTermForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (correctionalStatusTermForm.getStartTime() == null) {
			errors.rejectValue("startTime", "startTime.empty");
		}
		if (correctionalStatusTermForm.getStartDate() != null
				&& correctionalStatusTermForm.getEndDate() != null
				&& (correctionalStatusTermForm.getStartDate().getTime()
						> correctionalStatusTermForm.getEndDate().getTime())) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (correctionalStatusTermForm.getCorrectionalStatus() == null) {
			errors.rejectValue("correctionalStatus",
					"correctionalStatus.empty");
		}
		if (correctionalStatusTermForm.getEndDate() != null
				&& correctionalStatusTermForm.getEndTime() == null) {
			errors.rejectValue("endTime", "endTime.empty");
		}
		if (correctionalStatusTermForm.getEndDate() == null
				&& correctionalStatusTermForm.getEndTime() != null) {
			errors.rejectValue("endDate", "endDate.empty");
		}
	}
}