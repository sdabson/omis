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
package omis.task.web.form;

import java.io.Serializable;

/**
 * Task completion form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 18, 2018)
 * @since OMIS 3.0
 */
public class TaskCompletionForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean completed;
	
	/**
	 * Instantiates a default instance of the task completion form.
	 */
	public TaskCompletionForm() {
		//Default constructor
	}

	/**
	 * Returns whether the task is completed.
	 *
	 * @return whether the task is completed
	 */
	public Boolean getCompleted() {
		return completed;
	}

	/**
	 * Sets whether the task is completed.
	 *
	 * @param completed whether the task is completed
	 */
	public void setCompleted(final Boolean completed) {
		this.completed = completed;
	}
}