package omis.task.web.validator.delegate;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import omis.task.web.form.TaskFields;
import omis.task.web.form.TaskItemOperation;

/**
 * TaskFieldsValidatorDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 21, 2017)
 *@since OMIS 3.0
 *
 */
public class TaskFieldsValidatorDelegate {
	
	
	private static final String ORIGINATION_DATE_REQUIRED_MSG_KEY =
			"task.originationDate.empty";
	
	private static final String ORIGINATION_TIME_REQUIRED_MSG_KEY =
			"task.originationTime.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"task.description.empty";
	
	private static final String ASSIGNED_DATE_REQUIRED_MSG_KEY =
			"task.assignedDate.empty";
	
	private static final String ASSIGNEE_ACCOUNT_REQUIRED_MSG_KEY =
			"task.assigneeAccount.empty";
	
	private static final String GROUP_REQUIRED_MSG_KEY =
			"task.group.empty";
	
	private static final String TASK_REQUIRED_MSG_KEY =
			"task.task.empty";

	/**
	 * 
	 */
	public TaskFieldsValidatorDelegate() {
	}
	
	public void validate(final TaskFields fields,
			final String taskFieldsPropertyName, final Errors errors) {
		if(fields.getAllowGroup() != null && fields.getAllowGroup()) {
			ValidationUtils.rejectIfEmpty(errors, String.format("%s.%s",
					taskFieldsPropertyName, "group"),
					GROUP_REQUIRED_MSG_KEY);
		}
		if(fields.getAllowTask() != null && fields.getAllowTask()) {
			ValidationUtils.rejectIfEmpty(errors, String.format("%s.%s",
					taskFieldsPropertyName, "task"),
					TASK_REQUIRED_MSG_KEY);
			
		}
		for(int i = 0; i < fields.getTaskAssignmentItems().size(); i++) {
			if(EnumSet.of(TaskItemOperation.CREATE, TaskItemOperation.UPDATE)
					.contains(fields.getTaskAssignmentItems().get(i)
							.getTaskItemOperation())) {
				ValidationUtils.rejectIfEmpty(errors,
						String.format("%s.%s[%d].%s", taskFieldsPropertyName,
								"taskAssignmentItems", i, "assignedDate"),
						ASSIGNED_DATE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
						String.format("%s.%s[%d].%s", taskFieldsPropertyName,
								"taskAssignmentItems", i, "assigneeAccount"),
						ASSIGNEE_ACCOUNT_REQUIRED_MSG_KEY);
				
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, String.format("%s.%s",
				taskFieldsPropertyName, "description"),
				DESCRIPTION_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, String.format("%s.%s",
				taskFieldsPropertyName, "originationDate"),
				ORIGINATION_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, String.format("%s.%s",
				taskFieldsPropertyName, "originationTime"),
				ORIGINATION_TIME_REQUIRED_MSG_KEY);
		
	}
	
}
