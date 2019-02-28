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
package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationRequestSearchForm;

/**
 * Validator for Presentence Investigation Request Search Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 26, 2019)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestSearchFormValidator
		implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return PresentenceInvestigationRequestSearchForm.class
				.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		PresentenceInvestigationRequestSearchForm form =
				(PresentenceInvestigationRequestSearchForm) target;
		if (form.getUserSearch() != null && !form.getUserSearch()) {
			if (form.getLastName().isEmpty() && form.getFirstName().isEmpty()) {
				errors.rejectValue("firstName",
						"request.searchName.empty");
			}
		}
	}

}
