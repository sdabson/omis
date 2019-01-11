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
import omis.assessment.web.form.AssessmentForm;

/**
 * Assessment form validator.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Nov 20, 2018)
 * @since OMIS 3.0
 */
public class AssessmentFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AssessmentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AssessmentForm form = (AssessmentForm) target;
		if (form.getQuestionnaireType() == null) {
			errors.rejectValue("questionnaireType", 
					"assessment.questionnaireType.required");
		}
		if (form.getAssessDate() == null) {
			errors.rejectValue("assessDate", "assessment.assessDate.required");
		}
		if (form.getAssessTime() == null) {
			errors.rejectValue("assessTime", "assessment.assessTime.required");
		}
		if (form.getAssessor() == null) {
			errors.rejectValue("assessor", "assessment.assessor.required");
		}
	}
}