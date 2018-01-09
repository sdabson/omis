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
package omis.paroleboardcondition.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.condition.domain.ConditionCategory;
import omis.paroleboardcondition.web.form.ConditionItem;
import omis.paroleboardcondition.web.form.ParoleBoardAgreementConditionSelectForm;

/**
 * Parole Board Agreement Condition Select Form Validator.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 20, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementConditionSelectFormValidator
		implements Validator {
	
	private static final String CLAUSE_REQUIRED_MSG_KEY =
			"paroleBoardCondition.clause.empty";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ParoleBoardAgreementConditionSelectForm.class
				.isAssignableFrom(clazz);
	}
	
	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ParoleBoardAgreementConditionSelectForm form =
				(ParoleBoardAgreementConditionSelectForm) target;
		for (int i = 0; i < form.getConditionItems().size(); i++) {
			if (ConditionCategory.SPECIAL.equals(form.getConditionCategory())) {
				ConditionItem item = form.getConditionItems().get(i);
				if (item.getActive()) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"conditionItems[" + i + "].clause",
							CLAUSE_REQUIRED_MSG_KEY);
				}
			}
		}
	}
}
