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
package omis.courtcase.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.courtcase.web.form.CourtCaseFields;

/**
 * Delegate to validate fields for court case.
 *
 * <p>Docket fields are not validated to allow clients screens to use existing
 * dockets (thereby not requiring validation of docket fields). Clients should
 * therefore perform their own validation of docket fields when needed.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseFieldsValidatorDelegate {

	/** Instantiates validator for fields for court case. */
	public CourtCaseFieldsValidatorDelegate() {
		// Default instantiation
	}
	
	/**
	 * Validates court case fields.
	 * 
	 * @param fields fields
	 * @param courtCaseFieldsPropertyName fields property name
	 * @param errors errors
	 */
	public void validate(final CourtCaseFields fields,
			final String courtCaseFieldsPropertyName,
			final Errors errors) {
		if (fields.getJudge() == null) {
			errors.rejectValue(String.format("%s.%s", courtCaseFieldsPropertyName,
						"judge"), "courtCase.judge.empty");
		}
	}
}