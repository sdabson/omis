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

import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.domain.ParoleHearingTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * Implementation of parole hearing task group.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingTaskGroupImpl implements ParoleHearingTaskGroup {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private HearingAnalysisCategory hearingAnalysisCategory;
	
	private TaskTemplateGroup group;
	
	/** 
	 * Instantiates an implementation of parole hearing task group. 
	 */
	public ParoleHearingTaskGroupImpl() {
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
	public void setHearingAnalysisCategory(
			final HearingAnalysisCategory hearingAnalysisCategory) {
		this.hearingAnalysisCategory = hearingAnalysisCategory;
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisCategory getHearingAnalysisCategory() {
		return hearingAnalysisCategory;
	}

	/** {@inheritDoc} */
	@Override
	public void setGroup(final TaskTemplateGroup group) {
		this.group = group;
	}

	/** {@inheritDoc} */
	@Override
	public TaskTemplateGroup getGroup() {
		return group;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleHearingTaskGroup)) {
			return false;
		}
		ParoleHearingTaskGroup that = (ParoleHearingTaskGroup) obj;
		if (this.getHearingAnalysisCategory() == null) {
			throw new IllegalStateException(
					"Hearing analysis category required");
		}
		if (!this.getHearingAnalysisCategory().equals(
				that.getHearingAnalysisCategory())) {
			return false;
		}
		if (this.getGroup() == null) {
			throw new IllegalStateException(
					"Task template group required");
		}
		if (!this.getGroup().equals(that.getGroup())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getHearingAnalysisCategory() == null) {
			throw new IllegalStateException(
					"Hearing analysis category required");
		}
		if (this.getGroup() == null) {
			throw new IllegalStateException(
					"Task template group required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHearingAnalysisCategory().hashCode();
		hashCode = 29 * hashCode + this.getGroup().hashCode();
		return hashCode;
	}
}
