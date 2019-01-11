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
package omis.staff.web.validator;

import omis.staff.web.form.StaffTitleForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffTitleFormValidator
		implements Validator {

	/** Instantiates a validator for staff title form. */
	public StaffTitleFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return StaffTitleForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		StaffTitleForm staffTitleForm = (StaffTitleForm) target;
		if (staffTitleForm.getName() == null
				|| staffTitleForm.getName().length() == 0) {
			errors.rejectValue("name", "name.empty");
		}
		if (staffTitleForm.getSortOrder() == null) {
			errors.rejectValue("sortOrder", "sortOrder.empty");
		}
		if (staffTitleForm.getValid() == null) {
			errors.rejectValue("valid", "valid.empty");
		}
	}
}