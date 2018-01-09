package omis.presentenceinvestigation.web.form;

import omis.task.web.form.TaskFields;

/**
 * PresentenceInvestigationTaskForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 12, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskForm {
	
	private TaskFields taskFields;
	
	/**
	 * 
	 */
	public PresentenceInvestigationTaskForm() {
	}

	/**
	 * Returns the taskFields
	 * @return taskFields - TaskFields
	 */
	public TaskFields getTaskFields() {
		return taskFields;
	}

	/**
	 * Sets the taskFields
	 * @param taskFields - TaskFields
	 */
	public void setTaskFields(final TaskFields taskFields) {
		this.taskFields = taskFields;
	}
	
	
	
}
