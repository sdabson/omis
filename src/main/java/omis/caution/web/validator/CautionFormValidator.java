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
package omis.caution.web.validator;

import omis.caution.web.form.CautionForm;
import omis.web.validator.StringLengthChecks;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for offender cautions.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Oct 25, 2012)
 * @since OMIS 3.0
 */
public class CautionFormValidator
		implements Validator {
	
	private final StringLengthChecks stringLengthChecks;
	
	/**
	 * Instantiates a default caution form validator.
	 * 
	 * @param stringLengthChecks check for string length
	 */
	public CautionFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CautionForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CautionForm cautionForm = (CautionForm) target;
		if (cautionForm.getStartDate() != null
				&& cautionForm.getEndDate() != null
				&& cautionForm.getStartDate().getTime()
					> cautionForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (cautionForm.getDescription() == null) {
			errors.rejectValue("description", "caution.description.empty");
		}
		if (cautionForm.getSource() == null) {
			errors.rejectValue("source", "caution.source.empty");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"startDate", "caution.startDate.empty");
		this.stringLengthChecks.getVeryHugeCheck().check("sourceComment",
				cautionForm.getSourceComment(), errors);
		this.stringLengthChecks.getVeryHugeCheck().check("comment",
				cautionForm.getComment(), errors);
	}
}