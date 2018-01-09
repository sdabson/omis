package omis.task.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.task.domain.Task;
import omis.task.domain.TaskTemplateGroup;

/**
 * TaskFields.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 13, 2017)
 *@since OMIS 3.0
 *
 */
public class TaskFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private Date originationDate;
	
	private Date originationTime;
	
	private Date completionDate;
	
	private Boolean allowGroup;
	
	private Boolean allowTask;
	
	private TaskTemplateGroup group;
	
	private Task task;
	
	private List<TaskAssignmentItem> taskAssignmentItems =
			new ArrayList<TaskAssignmentItem>();
	
	/**
	 * 
	 */
	public TaskFields() {
	}

	/**
	 * Returns the description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the originationDate
	 * @return originationDate - Date
	 */
	public Date getOriginationDate() {
		return originationDate;
	}

	/**
	 * Sets the originationDate
	 * @param originationDate - Date
	 */
	public void setOriginationDate(final Date originationDate) {
		this.originationDate = originationDate;
	}
	
	/**
	 * Returns the originationTime
	 * @return originationTime - Date
	 */
	public Date getOriginationTime() {
		return originationTime;
	}

	/**
	 * Sets the originationTime
	 * @param originationTime - Date
	 */
	public void setOriginationTime(final Date originationTime) {
		this.originationTime = originationTime;
	}

	/**
	 * Returns the completionDate
	 * @return completionDate - Date
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * Sets the completionDate
	 * @param completionDate - Date
	 */
	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * Returns the allowGroup
	 * @return allowGroup - Boolean
	 */
	public Boolean getAllowGroup() {
		return allowGroup;
	}

	/**
	 * Sets the allowGroup
	 * @param allowGroup - Boolean
	 */
	public void setAllowGroup(final Boolean allowGroup) {
		this.allowGroup = allowGroup;
	}

	/**
	 * Returns the allowTask
	 * @return allowTask - Boolean
	 */
	public Boolean getAllowTask() {
		return allowTask;
	}

	/**
	 * Sets the allowTask
	 * @param allowTask - Boolean
	 */
	public void setAllowTask(final Boolean allowTask) {
		this.allowTask = allowTask;
	}

	/**
	 * Returns the group
	 * @return group - TaskTemplateGroup
	 */
	public TaskTemplateGroup getGroup() {
		return group;
	}

	/**
	 * Sets the group
	 * @param group - TaskTemplateGroup
	 */
	public void setGroup(final TaskTemplateGroup group) {
		this.group = group;
	}

	/**
	 * Returns the task
	 * @return task - Task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * Sets the task
	 * @param task - Task
	 */
	public void setTask(final Task task) {
		this.task = task;
	}

	/**
	 * Returns the taskAssignmentItems
	 * @return taskAssignmentItems - List&lt;TaskAssignmentItem&gt;
	 */
	public List<TaskAssignmentItem> getTaskAssignmentItems() {
		return taskAssignmentItems;
	}

	/**
	 * Sets the taskAssignmentItems
	 * @param taskAssignmentItems - List&lt;TaskAssignmentItem&gt;
	 */
	public void
		setTaskAssignmentItems(
				final List<TaskAssignmentItem> taskAssignmentItems) {
		this.taskAssignmentItems = taskAssignmentItems;
	}
	
	
}
