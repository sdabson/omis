package omis.task.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;
import omis.user.domain.UserAccount;

/**
 * Data access object for tasks.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface TaskDao
		extends GenericDao<Task> {

	/**
	 * Returns task.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source user account
	 * @param originationDate origination date
	 * @return task or {@code null} if not found
	 */
	Task find(String controllerName, String methodName, String description,
			UserAccount sourceAccount, Date originationDate);
	
	/**
	 * Returns task.
	 * 
	 * <p>Returns {@code null} if not found or excluded.
	 * 
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source account
	 * @param originationDate origination date
	 * @return task or {@code null} if not found or excluded
	 */
	Task findExcluding(String controllerName, String methodName,
			String description, UserAccount sourceAccount, Date originationDate,
			Task... excludedTasks);
	
	/**
	 * Returns a list of Tasks found by specified TaskTemplate
	 * @param taskTemplate - TaskTemplate
	 * @return List of tasks found by specified TaskTemplate
	 */
	List<Task> findByTaskTemplate(TaskTemplate taskTemplate);
	
}