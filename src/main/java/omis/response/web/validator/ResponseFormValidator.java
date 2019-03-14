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
package omis.response.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.response.web.form.ResponseForm;

/**
 * Response form validator.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 5, 2019)
 * @since OMIS 3.0
 */
public class ResponseFormValidator implements Validator{

	/**
	 * Instantiates a default response form validator. 
	 */
	public ResponseFormValidator() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ResponseForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", 
				"response.description.empty");
		ValidationUtils.rejectIfEmpty(errors, "grid", 
				"response.grid.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", 
				"response.category.empty");
		ValidationUtils.rejectIfEmpty(errors, "level", 
				"response.level.empty");
		ValidationUtils.rejectIfEmpty(errors, "valid", 
				"response.valid.empty");
	}
}