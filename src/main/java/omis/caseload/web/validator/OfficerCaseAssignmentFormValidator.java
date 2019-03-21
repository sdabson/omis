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
package omis.caseload.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.caseload.web.form.ItemOperation;
import omis.caseload.web.form.OfficerCaseAssignmentForm;
import omis.caseload.web.form.OfficerCaseAssignmentNoteItem;

/**
 * Officer case assignment form validator.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Mar 19, 2019)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OfficerCaseAssignmentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OfficerCaseAssignmentForm form = (OfficerCaseAssignmentForm) target;
		ValidationUtils.rejectIfEmpty(errors, "supervisionLevelCategory",
				"officerCaseAssignment.supervisionLevelCategory.empty");
		ValidationUtils.rejectIfEmpty(errors, "location",
				"officerCaseAssignment.location.empty");
		if (form.getSelectedOffender() == null) {
			errors.rejectValue("selectedOffender", 
					"officerCaseAssignment.offender.empty");
		}
		if (form.getOfficer() == null) {
			errors.rejectValue("officer", 
					"officerCaseAssignment.userAccount.empty");
		}
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"officerCaseAssignment.startDate.empty");
		}
		if (form.getStartTime() == null) {
			errors.rejectValue("startTime", 
					"officerCaseAssignment.startTime.empty");
		}
		if (form.getEndDate() != null && form.getEndTime() == null) {
			errors.rejectValue("endTime", 
					"officerCaseAssignment.endTime.empty");
		}
		if (form.getEndDate() == null && form.getEndTime() != null) {
			errors.rejectValue("endDate", 
					"officerCaseAssignment.endDate.empty");
		}
		if (form.getOfficerCaseAssignmentNoteItems() != null) {
			for (int i = 0; i < form.getOfficerCaseAssignmentNoteItems().size();
					i++) {
				OfficerCaseAssignmentNoteItem noteItem = 
						form.getOfficerCaseAssignmentNoteItems().get(i);
				if (noteItem.getOperation() != null && 
						!ItemOperation.REMOVE.equals(noteItem.getOperation())) {
					if (noteItem.getDate() == null) {
						errors.rejectValue("officerCaseAssignmentNoteItems[" + 
								i + "].date", 
								"officerCaseAssignmentNote.date.empty");
					}
					if (noteItem.getValue() == null || 
							noteItem.getValue().isEmpty()) {
						errors.rejectValue("officerCaseAssignmentNoteItems[" + 
								i + "].value", 
								"officerCaseAssignmentNote.description.empty");
					}
				}
			}
		}
		if (form.getAllowInterstateCompact()) {
			if (form.getInterstateCaseload() != null && 
					form.getInterstateCaseload()) {
				if (form.getInterstateCompactStatus() == null) {
					errors.rejectValue("interstateCompactStatus", 
							"interstateCompactAssignment.interstateCompactStatus.empty");
				}
				if (form.getInterstateCompactType() == null) {
					errors.rejectValue("interstateCompactType", 
							"interstateCompactAssignment.interstateCompactType.empty");
				}
			}
		}
		
	}
}