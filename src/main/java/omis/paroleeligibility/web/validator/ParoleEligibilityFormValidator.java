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
package omis.paroleeligibility.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.paroleeligibility.domain.EligibilityStatusCategory;
import omis.paroleeligibility.web.form.ParoleEligibilityForm;

/**
 * Validator for the form for parole eligibilities.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Dec 5, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityFormValidator implements Validator {
	
	/** Instantiates a validator for the form for parole eligibilities. */
	public ParoleEligibilityFormValidator() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	public boolean supports(final Class<?> clazz) {
		return ParoleEligibilityFormValidator.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	public void validate(final Object target, final Errors errors) {
		ParoleEligibilityForm paroleEligibilityForm 
			= (ParoleEligibilityForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"hearingEligibilityDate", "hearingEligibilityDate.empty");
		
		if (EligibilityStatusCategory.INELIGIBLE.equals(paroleEligibilityForm
				.getEligibilityStatusCategory()) || EligibilityStatusCategory
				.WAIVED.equals(paroleEligibilityForm
						.getEligibilityStatusCategory())) {
			if(paroleEligibilityForm.getEligibilityStatusReason() == null) {
				errors.rejectValue("eligibilityStatusReason", 
						"eligibilityStatusReason.empty");
			}
			
		}
				
	}

}
