/**
 * 
 */
package omis.task.domain.impl;

import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Implementation of task template.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskTemplateImpl
		implements TaskTemplate {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private TaskTemplateGroup group;
	
	private String name;
	
	private String controllerName;
	
	private String methodName;

	/** Instantiates implementation of task template. */
	public TaskTemplateImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setGroup(final TaskTemplateGroup group) {
		this.group = group;
	}

	/** {@inheritDoc} */
	@Override
	public TaskTemplateGroup getGroup() {
		return this.group;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setControllerName(final String controllerName) {
		this.controllerName = controllerName;
	}

	/** {@inheritDoc} */
	@Override
	public String getControllerName() {
		return this.controllerName;
	}

	/** {@inheritDoc} */
	@Override
	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	/** {@inheritDoc} */
	@Override
	public String getMethodName() {
		return this.methodName;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TaskTemplate)) {
			return false;
		}
		TaskTemplate that = (TaskTemplate) obj;
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		if (!this.getGroup().equals(that.getGroup())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getControllerName() == null) {
			throw new IllegalStateException("Controller name required");
		}
		if (!this.getControllerName().equals(that.getControllerName())) {
			return false;
		}
		if (this.getMethodName() == null) {
			throw new IllegalStateException("Method name required");
		}
		if (!this.getMethodName().equals(that.getMethodName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getControllerName() == null) {
			throw new IllegalStateException("Controller name required");
		}
		if (this.getMethodName() == null) {
			throw new IllegalStateException("Method name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getGroup().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getControllerName().hashCode();
		hashCode = 29 * hashCode + this.getMethodName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d - [%s] %s %s %s", this.getId(),
				this.getGroup(), this.getName(), this.getControllerName(),
				this.getMethodName());
	}
}