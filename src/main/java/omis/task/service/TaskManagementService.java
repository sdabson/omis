package omis.task.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.task.domain.TaskParameterValue;
import omis.user.domain.UserAccount;

/**
 * Service for task management.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskManagementService {

	/**
	 * Creates task.
	 * 
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source account
	 * @param originationDate origination date
	 * @param completionDate completion date
	 * @return created task
	 * @throws DuplicateEntityFoundException if task exists
	 */
	Task create(String controllerName, String methodName, String description,
			UserAccount sourceAccount, Date originationDate,
			Date completionDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates task.
	 * 
	 * @param task task
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source account
	 * @param originationDate origination date
	 * @param completionDate completion date
	 * @return updated task
	 * @throws DuplicateEntityFoundException if task exists
	 */
	Task update(Task task, String controllerName, String methodName,
			String description, UserAccount sourceAccount,
			Date originationDate, Date completionDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes task.
	 * 
	 * @param task task
	 */
	void remove(Task task);
	
	/**
	 * Returns tasks by source account.
	 * 
	 * @param sourceAccount source account
	 * @return tasks by source account
	 */
	Task findBySourceAccount(UserAccount sourceAccount);

	/**
	 * Creates task parameter value.
	 * 
	 * @param task task
	 * @param order order
	 * @param instanceIdValue instance ID value
	 * @param entityName entity name
	 * @return created task parameter value
	 * @throws DuplicateEntityFoundException if task parameter value exists
	 */
	TaskParameterValue createParameterValue(Task task, Short order,
			Long instanceIdValue, String entityName)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates task parameter value.
	 * 
	 * @param parameterValue parameter value
	 * @param order order
	 * @param instanceIdValue instance ID value
	 * @param entityName entity name
	 * @return updated task parameter value
	 * @throws DuplicateEntityFoundException if task parameter value exists 
	 */
	TaskParameterValue updateParameterValue(TaskParameterValue parameterValue,
			Short order, Long instanceIdValue, String entityName)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes task parameter value.
	 * 
	 * @param parameterValue task parameter value to remove
	 */
	void removeParameterValue(TaskParameterValue parameterValue);
	
	/**
	 * Returns parameter values by task.
	 * 
	 * @param task task
	 * @return parameter values by task
	 */
	List<TaskParameterValue> findParameterValuesByTask(Task task);
	
	/**
	 * Creates assignment.
	 * 
	 * @param task task
	 * @param assigneeAccount assignee user account
	 * @param assignedDate assigned date
	 * @return created assignment
	 * @throws DuplicateEntityFoundException if assignment exists
	 */
	TaskAssignment createAssignment(Task task, UserAccount assigneeAccount,
			Date assignedDate) throws DuplicateEntityFoundException;
	
	/**
	 * Updates assignment.
	 * 
	 * @param taskAssignment assignment
	 * @param assigneeAccount assignee user account
	 * @param assignedDate assigned date
	 * @return updates assignment
	 * @throws DuplicateEntityFoundException if assignment exists
	 */
	TaskAssignment updateAssignment(TaskAssignment taskAssignment,
			UserAccount assigneeAccount, Date assignedDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes assignment.
	 * 
	 * @param taskAssignment assignment to remove
	 */
	void removeAssignment(TaskAssignment taskAssignment);
	
	/**
	 * Returns assignments by task.
	 * 
	 * @param task task
	 * @return assignments by task
	 */
	List<TaskAssignment> findAssignmentsByTask(Task task);
	
	/**
	 * Returns user account.
	 * 
	 * @param username username of account
	 * @return user account
	 */
	UserAccount findUserAccount(String username);
}