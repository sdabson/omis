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
package omis.hearinganalysis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.hearinganalysis.web.form.HearingAnalysisTaskForm;
import omis.task.web.validator.delegate.TaskFieldsValidatorDelegate;

/**
 * Validator for hearing analysis task.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 17, 2018)
 * @since OMIS 3.0
 *
 */
public class HearingAnalysisTaskFormValidator implements Validator {
	
	private static final String TASK_FIELDS_PROPERTY_NAME = "taskFields";
	
	private final TaskFieldsValidatorDelegate taskFieldsValidatorDelegate;

	/**
	 * Instantiates a hearing analysis task form validator with the specified 
	 * delegate.
	 * 
	 * @param taskFieldsValidatorDelegate task fields validator delegate
	 */
	public HearingAnalysisTaskFormValidator(
			final TaskFieldsValidatorDelegate taskFieldsValidatorDelegate) {
		this.taskFieldsValidatorDelegate = taskFieldsValidatorDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return HearingAnalysisTaskForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		HearingAnalysisTaskForm form =
				(HearingAnalysisTaskForm) target;
		this.taskFieldsValidatorDelegate.validate(form.getTaskFields(),
				TASK_FIELDS_PROPERTY_NAME, errors);
	}

}
