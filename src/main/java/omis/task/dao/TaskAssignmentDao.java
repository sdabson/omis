package omis.task.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.user.domain.UserAccount;

/**
 * Data access object for tasks assignments.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskAssignmentDao
		extends GenericDao<TaskAssignment> {

	/**
	 * Returns assignments by tasks.
	 * 
	 * @param task task
	 * @return assignments by task
	 */
	List<TaskAssignment> findByTask(Task task);
	
	/**
	 * Returns task assignment.
	 * 
	 * <p>Returns {@code null} if task assignment does not exist
	 * 
	 * @param task task
	 * @param assigneeAccount user account of assignee
	 * @param assignedDate assigned date
	 * @return task assignment or {@code null} if non-existent
	 */
	TaskAssignment find(
			Task task, UserAccount assigneeAccount, Date assignedDate);
	
	/**
	 * Returns task assignment.
	 * 
	 * <p>Returns {@code null} if task assignment does not exist or if matching
	 * assignment is excluded.
	 * 
	 * @param task task
	 * @param assigneeAccount user account of assignee
	 * @param assignedDate assigned date
	 * @param excludedTaskAssignments task assignments to exclude
	 * @return task assignment or {@code null} if non-existent or excluded
	 */
	TaskAssignment findExcluding(
			Task task, UserAccount assigneeAccount, Date assignedDate,
			TaskAssignment... excludedTaskAssignments);
}