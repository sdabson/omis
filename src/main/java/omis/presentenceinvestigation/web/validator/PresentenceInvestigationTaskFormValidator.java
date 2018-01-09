package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationTaskForm;
import omis.task.web.validator.delegate.TaskFieldsValidatorDelegate;

/**
 * PresentenceInvestigationTaskFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 12, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskFormValidator implements Validator {
	
	private static final String TASK_FIELDS_PROPERTY_NAME = "taskFields";
	
	private final TaskFieldsValidatorDelegate taskFieldsValidatorDelegate;

	/**
	 * @param taskFieldsValidatorDelegate
	 */
	public PresentenceInvestigationTaskFormValidator(
			final TaskFieldsValidatorDelegate taskFieldsValidatorDelegate) {
		this.taskFieldsValidatorDelegate = taskFieldsValidatorDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return PresentenceInvestigationTaskForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		PresentenceInvestigationTaskForm form =
				(PresentenceInvestigationTaskForm) target;
		this.taskFieldsValidatorDelegate.validate(form.getTaskFields(),
				TASK_FIELDS_PROPERTY_NAME, errors);
	}

}
