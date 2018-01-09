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

import omis.supervision.web.form.SupervisoryOrganizationTermForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for supervisory organization terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 9, 2014)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationTermFormValidator
		implements Validator {

	/** Instantiates a validator for form for supervisory organization terms. */
	public SupervisoryOrganizationTermFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SupervisoryOrganizationTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SupervisoryOrganizationTermForm supervisoryOrganizationTermForm
			= (SupervisoryOrganizationTermForm) target;
		if (supervisoryOrganizationTermForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (supervisoryOrganizationTermForm.getStartTime() == null) {
			errors.rejectValue("startTime", "startTime.empty");
		}
		if (supervisoryOrganizationTermForm.getStartDate() != null
				&& supervisoryOrganizationTermForm.getEndDate() != null
				&& (supervisoryOrganizationTermForm.getStartDate().getTime())
					> supervisoryOrganizationTermForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (supervisoryOrganizationTermForm
				.getSupervisoryOrganization() == null) {
			errors.rejectValue("supervisoryOrganization",
					"supervisoryOrganization.empty");
		}
		if (supervisoryOrganizationTermForm.getEndDate() != null
				&& supervisoryOrganizationTermForm.getEndTime() == null) {
			errors.rejectValue("endTime", "endTime.empty");
		}
		if (supervisoryOrganizationTermForm.getEndDate() == null
				&& supervisoryOrganizationTermForm.getEndTime() != null) {
			errors.rejectValue("endDate", "endDate.empty");
		}
	}
}