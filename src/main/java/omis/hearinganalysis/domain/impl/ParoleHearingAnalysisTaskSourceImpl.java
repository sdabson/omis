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
package omis.hearinganalysis.domain.impl;

import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.hearinganalysis.domain.ParoleHearingTaskCategory;
import omis.task.domain.TaskTemplate;

/**
 * Implementation of parole hearing analysis task source.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingAnalysisTaskSourceImpl 
	implements ParoleHearingAnalysisTaskSource {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private TaskTemplate taskTemplate;
	
	private ParoleHearingTaskCategory category;
	
	/** 
	 * Instantiates an implementation of parole hearing analysis task source. 
	 */
	public ParoleHearingAnalysisTaskSourceImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setTaskTemplate(final TaskTemplate taskTemplate) {
		this.taskTemplate = taskTemplate;
	}

	/** {@inheritDoc} */
	@Override
	public TaskTemplate getTaskTemplate() {
		return taskTemplate;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ParoleHearingTaskCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingTaskCategory getCategory() {
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleHearingAnalysisTaskSource)) {
			return false;
		}
		ParoleHearingAnalysisTaskSource that = 
				(ParoleHearingAnalysisTaskSource) obj;
		if (this.getTaskTemplate() == null) {
			throw new IllegalStateException("Task template required");
		}
		if (!this.getTaskTemplate().equals(that.getTaskTemplate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getTaskTemplate() == null) {
			throw new IllegalStateException("Task template required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTaskTemplate().hashCode();
		return hashCode;
	}
}
