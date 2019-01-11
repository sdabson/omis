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
import org.springframework.validation.Validator;

import omis.caseload.web.form.OfficerCaseAssignmentTransferForm;

/**
 * Validator for officer case assignment transfer form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jul 31, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentTransferFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OfficerCaseAssignmentTransferForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OfficerCaseAssignmentTransferForm form = 
				(OfficerCaseAssignmentTransferForm) target;
		if (form.getUserAccountFrom() == null) {
			errors.rejectValue("userAccountFrom", 
					"officerCaseAssignmentTransfer.userAccountFrom.empty");
		} else {
			if (form.getUserAccountFrom().equals(form.getUserAccountTo())) {
				errors.rejectValue("userAccountTo", 
						"officerCaseAssignmentTransfer.userAccountTo.mustBeDifferent");
			}
		}
		if (form.getUserAccountTo() == null) {
			errors.rejectValue("userAccountTo", 
					"officerCaseAssignmentTransfer.userAccountTo.empty");
		}
		if (form.getDate() == null) {
			errors.rejectValue("date", 
					"officerCaseAssignmentTransfer.date.empty");
		}
	}
}