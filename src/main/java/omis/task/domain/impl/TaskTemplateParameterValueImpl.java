/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.task.domain.impl;

import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Implementation of task template parameter value.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class TaskTemplateParameterValueImpl
		implements TaskTemplateParameterValue {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private TaskTemplate template;
	
	private Short order;
	
	private String typeName;
	
	/** Instantiates implementation of task template parameter value. */
	public TaskTemplateParameterValueImpl() {
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
	public void setTemplate(final TaskTemplate template) {
		this.template = template;
	}

	/** {@inheritDoc} */
	@Override
	public TaskTemplate getTemplate() {
		return this.template;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrder(final Short order) {
		this.order = order;
	}

	/** {@inheritDoc} */
	@Override
	public Short getOrder() {
		return this.order;
	}

	/** {@inheritDoc} */
	@Override
	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	/** {@inheritDoc} */
	@Override
	public String getTypeName() {
		return this.typeName;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TaskTemplateParameterValue)) {
			return false;
		}
		TaskTemplateParameterValue that = (TaskTemplateParameterValue) obj;
		if (this.getTemplate() == null) {
			throw new IllegalStateException("Template required");
		}
		if (!this.getTemplate().equals(that.getTemplate())) {
			return false;
		}
		if (this.getTypeName() == null) {
			throw new IllegalStateException("Type name required");
		}
		if (!this.getTypeName().equals(that.getTypeName())) {
			return false;
		}
		if (this.getOrder() == null) {
			throw new IllegalStateException("Order required");
		}
		if (!this.getOrder().equals(that.getOrder())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getTypeName() == null) {
			throw new IllegalStateException("Type name required");
		}
		if (this.getTemplate() == null) {
			throw new IllegalStateException("Template required");
		}
		if (this.getOrder() == null) {
			throw new IllegalStateException("Order required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTypeName().hashCode();
		hashCode = 29 * hashCode + this.getTemplate().hashCode();
		hashCode = 29 * hashCode + this.getOrder().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("%d - [%s] %d %s", this.getId(),
				this.getTemplate(), this.getOrder(), this.getTypeName());
	}
}