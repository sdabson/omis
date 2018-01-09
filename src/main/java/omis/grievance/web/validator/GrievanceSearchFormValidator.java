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
package omis.grievance.web.validator;

import omis.grievance.web.form.GrievanceSearchForm;
import omis.grievance.web.form.GrievanceSearchType;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to search grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 7, 2016)
 * @since OMIS 3.0
 */
public class GrievanceSearchFormValidator
		implements Validator {

	/** Instantiates validator for form to search grievances. */
	public GrievanceSearchFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return GrievanceSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		GrievanceSearchForm grievanceSearchForm = (GrievanceSearchForm) target;
		if (grievanceSearchForm.getType() != null) {
			if (GrievanceSearchType.OFFENDER
					.equals(grievanceSearchForm.getType())) {
				if (grievanceSearchForm.getOffender() == null) {
					errors.rejectValue(
							"query", "grievanceSearchOffender.empty");
				}
			}
			if (GrievanceSearchType.LOCATION
					.equals(grievanceSearchForm.getType())) {
				if (grievanceSearchForm.getLocation() == null) {
					errors.rejectValue(
							"query", "grievanceSearchLocation.empty");
				}
			}
		} else {
			errors.rejectValue("type", "grievanceSearchType.empty");
		}
	}
}