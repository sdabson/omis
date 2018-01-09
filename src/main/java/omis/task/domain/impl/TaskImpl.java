package omis.task.domain.impl;

import java.util.Date;

import omis.task.domain.Task;
import omis.user.domain.UserAccount;

/**
 * Implementation of task.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskImpl
		implements Task {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String controllerName;
	
	private String methodName;
	
	private String description;
	
	private UserAccount sourceAccount;
	
	private Date originationDate;
	
	private Date completionDate;
	
	/** Instantiates implementation of task. */
	public TaskImpl() {
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSourceAccount(final UserAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount getSourceAccount() {
		return this.sourceAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOriginationDate(final Date originationDate) {
		this.originationDate = originationDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getOriginationDate() {
		return this.originationDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getCompletionDate() {
		return this.completionDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Task)) {
			return false;
		}
		Task that = (Task) obj;
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
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getSourceAccount() == null) {
			throw new IllegalStateException("Source account required");
		}
		if (!this.getSourceAccount().equals(that.getSourceAccount())) {
			return false;
		}
		if (this.getOriginationDate() == null) {
			throw new IllegalStateException("Origination date required");
		}
		if (!this.getOriginationDate().equals(that.getOriginationDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getControllerName() == null) {
			throw new IllegalStateException("Controller name required");
		}
		if (this.getMethodName() == null) {
			throw new IllegalStateException("Method name required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getSourceAccount() == null) {
			throw new IllegalStateException("Source account required");
		}
		if (this.getOriginationDate() == null) {
			throw new IllegalStateException("Origination date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getControllerName().hashCode();
		hashCode = 29 * hashCode + this.getMethodName().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getSourceAccount().hashCode();
		hashCode = 29 * hashCode + this.getOriginationDate().hashCode();
		return hashCode;
	}
}