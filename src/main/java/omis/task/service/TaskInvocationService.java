package omis.task.service;

import java.util.List;

import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;

/**
 * Service for task invocation.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskInvocationService {

	/**
	 * Returns parameter values for task.
	 * 
	 * @param task task
	 * @return parameter values for task
	 */
	List<TaskParameterValue> findParameterValues(Task task);

	/**
	 * Returns task parameter value for task with order. 
	 * 
	 * @param task task
	 * @param order order
	 * @return task parameter value for task with order
	 */
	TaskParameterValue findParameterValueByOrder(Task task, Short order);
}