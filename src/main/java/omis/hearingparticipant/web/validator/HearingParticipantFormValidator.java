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
package omis.hearingparticipant.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import omis.hearingparticipant.web.form.HearingParticipantForm;
import omis.hearingparticipant.web.form.HearingParticipantItemOperation;
import omis.hearingparticipant.web.form.HearingParticipantNoteItem;

/**
 * Hearing Participant Form Validator.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 17, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantFormValidator implements Validator {

	private static final String CATEGORY_REQUIRED_MSG_KEY = "category.empty";
	
	private static final String PERSON_REQUIRED_MSG_KEY = "person.empty";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";

	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return HearingParticipantForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "person", 
				PERSON_REQUIRED_MSG_KEY);
		HearingParticipantForm form = (HearingParticipantForm) target;
		if (form.getHearingParticipantNoteItems() != null) {
			int i = 0;
			for (HearingParticipantNoteItem item
					: form.getHearingParticipantNoteItems()) {
				if (HearingParticipantItemOperation.CREATE.equals(
							item.getItemOperation())
						|| HearingParticipantItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("hearingParticipantNoteItems[" + i 
								+ "].date", DATE_REQUIRED_MSG_KEY);
					}
					if (item.getDate() == null) {
						errors.rejectValue(
								"hearingParticipantNoteItems[" + i + "]"
										+ ".description",
								DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				i++;
			}
		}
	}

}
