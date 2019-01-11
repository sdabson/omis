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

import omis.staff.web.form.StaffAssignmentForm;
import omis.staff.web.form.StaffAssignmentPersonOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for staff assignments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentFormValidator
		implements Validator {

	/** Instantiates a validator for staff assignment forms. */
	public StaffAssignmentFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return StaffAssignmentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		StaffAssignmentForm staffAssignmentForm =
				(StaffAssignmentForm) target;
		if (StaffAssignmentPersonOperation.CREATE
				.equals(staffAssignmentForm.getPersonOperation())
						|| StaffAssignmentPersonOperation.UPDATE
							.equals(staffAssignmentForm.getPersonOperation())) {
			if (staffAssignmentForm.getLastName() == null
					|| staffAssignmentForm.getLastName().isEmpty()) {
				errors.rejectValue("lastName", "lastName.empty");
			}
			if (staffAssignmentForm.getFirstName() == null
					|| staffAssignmentForm.getFirstName().isEmpty()) {
				errors.rejectValue("firstName", "firstName.empty");
			}
			if (staffAssignmentForm.getBirthDate() == null) {
				errors.rejectValue("birthDate", "birthDate.empty");
			}
			if (staffAssignmentForm.getSex() == null) {
				errors.rejectValue("sex", "sex.empty");
			}
		} else if (!StaffAssignmentPersonOperation.USE_EXISTING
				.equals(staffAssignmentForm.getPersonOperation())) {
			throw new UnsupportedOperationException(
					String.format("Unsupported operation: %s",
							staffAssignmentForm.getPersonOperation()));
		}
		if (staffAssignmentForm.getStaffId() == null
				|| staffAssignmentForm.getStaffId().isEmpty()) {
			errors.rejectValue("staffId", "staffId.empty");
		}
		if (staffAssignmentForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (staffAssignmentForm.getTitle() == null) {
			errors.rejectValue("title", "staffTitle.empty");
		}
		if (staffAssignmentForm.getSupervisoryOrganization() == null) {
			errors.rejectValue("supervisoryOrganization",
					"supervisoryOrganization.empty");
		}
		if (staffAssignmentForm.getStartDate() != null
				&& staffAssignmentForm.getEndDate() != null
				&& staffAssignmentForm.getStartDate().getTime() >
					staffAssignmentForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (staffAssignmentForm.getPhotoDate() == null
				&& (staffAssignmentForm.getPhotoData() != null
						&& staffAssignmentForm.getPhotoData().length > 0)) {
			errors.rejectValue("photoDate", "photoDate.empty");
		} else if (staffAssignmentForm.getPhotoDate() != null
				&& (staffAssignmentForm.getPhotoData() == null
						|| staffAssignmentForm.getPhotoData().length == 0)) {
			errors.rejectValue("photoData", "photo.empty");
		}
	}
}