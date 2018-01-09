package omis.task.service.impl;

import java.util.List;

import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.service.TaskInvocationService;
import omis.task.service.delegate.TaskParameterValueDelegate;

/**
 * Implementation of service to invoke tasks.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskInvocationServiceImpl
		implements TaskInvocationService {
	
	private final TaskParameterValueDelegate taskParameterValueDelegate;

	/**
	 * Instantiates implementation of service to invoke tasks.
	 * 
	 * @param taskParameterValueDelegate delegate for task parameter value
	 */
	public TaskInvocationServiceImpl(
			final TaskParameterValueDelegate taskParameterValueDelegate) {
		this.taskParameterValueDelegate = taskParameterValueDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TaskParameterValue> findParameterValues(final Task task) {
		return this.taskParameterValueDelegate.findByTask(task);
	}

	/** {@inheritDoc} */
	@Override
	public TaskParameterValue findParameterValueByOrder(
			final Task task, final Short order) {
		return this.taskParameterValueDelegate.findByTaskAndOrder(task, order);
	}
}