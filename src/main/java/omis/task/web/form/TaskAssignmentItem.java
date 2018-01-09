package omis.task.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.task.domain.TaskAssignment;
import omis.user.domain.UserAccount;

/**
 * TaskAssignmentItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 13, 2017)
 *@since OMIS 3.0
 *
 */
public class TaskAssignmentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TaskAssignment taskAssignment;
	
	private Date assignedDate;
	
	private UserAccount assigneeAccount;
	
	private TaskItemOperation taskItemOperation;
	
	/**
	 * 
	 */
	public TaskAssignmentItem() {
	}

	/**
	 * Returns the taskAssignment
	 * @return taskAssignment - TaskAssignment
	 */
	public TaskAssignment getTaskAssignment() {
		return taskAssignment;
	}

	/**
	 * Sets the taskAssignment
	 * @param taskAssignment - TaskAssignment
	 */
	public void setTaskAssignment(final TaskAssignment taskAssignment) {
		this.taskAssignment = taskAssignment;
	}

	/**
	 * Returns the assignedDate
	 * @return assignedDate - Date
	 */
	public Date getAssignedDate() {
		return assignedDate;
	}

	/**
	 * Sets the assignedDate
	 * @param assignedDate - Date
	 */
	public void setAssignedDate(final Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**
	 * Returns the assigneeAccount
	 * @return assigneeAccount - UserAccount
	 */
	public UserAccount getAssigneeAccount() {
		return assigneeAccount;
	}

	/**
	 * Sets the assigneeAccount
	 * @param assigneeAccount - UserAccount
	 */
	public void setAssigneeAccount(final UserAccount assigneeAccount) {
		this.assigneeAccount = assigneeAccount;
	}

	/**
	 * Returns the taskItemOperation
	 * @return taskItemOperation - TaskItemOperation
	 */
	public TaskItemOperation getTaskItemOperation() {
		return taskItemOperation;
	}

	/**
	 * Sets the taskItemOperation
	 * @param taskItemOperation - TaskItemOperation
	 */
	public void setTaskItemOperation(final TaskItemOperation taskItemOperation) {
		this.taskItemOperation = taskItemOperation;
	}
}
