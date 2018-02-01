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
package omis.offenseterm.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenseterm.web.form.OffenseTermDocketForm;

/**
 * Validator for offense term docket form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public class OffenseTermDocketFormValidator 
		implements Validator {

	/** Instantiates a default offense term docket form validator. */
	public OffenseTermDocketFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return OffenseTermDocketForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		OffenseTermDocketForm form = (OffenseTermDocketForm) target;
		if (form.getCourt() == null) {
			errors.rejectValue("court", "offenseTermForm.court.empty");
		}
		if (form.getDocketValue() == null || form.getDocketValue().isEmpty()) {
			errors.rejectValue("docketValue", "offenseTermForm.docket.empty");
		}
	}
}