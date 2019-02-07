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
package omis.assessment.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.assessment.web.form.AssessmentItemOperation;
import omis.assessment.web.form.RatingNoteForm;
import omis.assessment.web.form.RatingNoteItem;

/**
 * Rating Note Form Validator.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 6, 2019)
 *@since OMIS 3.0
 *
 */
public class RatingNoteFormValidator implements Validator {
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return RatingNoteForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		RatingNoteForm form = (RatingNoteForm) target;
		
		if (form.getRatingNoteItems() != null) {
			int i = 0;
			for (RatingNoteItem item : form.getRatingNoteItems()) {
				if (AssessmentItemOperation.CREATE.equals(
							item.getItemOperation())
						|| AssessmentItemOperation.UPDATE.equals(
							item.getItemOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("ratingNoteItems[" + i 
								+ "].date", DATE_REQUIRED_MSG_KEY);
					}
					if (item.getDate() == null) {
						errors.rejectValue(
								"ratingNoteItems[" + i + "].description",
								DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				i++;
			}
		}
		
	}

}
