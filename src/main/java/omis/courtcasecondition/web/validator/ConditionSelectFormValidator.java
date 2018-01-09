package omis.courtcasecondition.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.condition.domain.ConditionCategory;
import omis.courtcasecondition.web.form.ConditionItem;
import omis.courtcasecondition.web.form.ConditionSelectForm;

/**
 * ConditionSelectFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 11, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionSelectFormValidator implements Validator {

	private static final String CLAUSE_REQUIRED_MSG_KEY =
			"courtCaseCondition.clause.empty";

	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ConditionSelectForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ConditionSelectForm form = (ConditionSelectForm) target;
		for(int i = 0; i < form.getConditionItems().size(); i++) {
			if(ConditionCategory.SPECIAL.equals(form.getConditionCategory())) {
				ConditionItem item = form.getConditionItems().get(i);
				if(item.getActive()) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"conditionItems["+i+"].clause",
							CLAUSE_REQUIRED_MSG_KEY);
				}
			}
		}
	}
}
