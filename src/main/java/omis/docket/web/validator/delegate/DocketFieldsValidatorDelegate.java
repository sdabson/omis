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
package omis.docket.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.docket.web.form.DocketFields;

/**
 * Delegate for docket fields.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 31, 2018)
 * @since OMIS 3.0
 */
public class DocketFieldsValidatorDelegate {

	/** Instantiates validator for docket fields. */
	public DocketFieldsValidatorDelegate() {
		// Default instantiation
	}
	
	/**
	 * Validates docket fields.
	 * 
	 * @param fields fields
	 * @param docketFieldsPropertyName fields property name
	 * @param errors errors
	 */
	public void validate(final DocketFields fields,
			final String docketFieldsPropertyName,
			final Errors errors) {
		if (fields.getCourt() == null) {
			errors.rejectValue(
				String.format("%s.%s", docketFieldsPropertyName, "court"),
				"docket.court.empty");
		}
		if (fields.getValue() == null || fields.getValue().isEmpty()) {
			errors.rejectValue(
				String.format("%s.%s", docketFieldsPropertyName, "value"),
				"docket.value.empty");
		}
	}
}