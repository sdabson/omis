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

import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.task.domain.Task;

/**
 * Implementation of hearing analysis task association.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskAssociationImpl 
	implements HearingAnalysisTaskAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Task task;
	
	private HearingAnalysis hearingAnalysis;
	
	private ParoleHearingAnalysisTaskSource taskSource;

	/** 
	 * Instantiates an implementation of hearing analysis task association. 
	 */
	public HearingAnalysisTaskAssociationImpl() {
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
	public void setTask(final Task task) {
		this.task = task;
	}

	/** {@inheritDoc} */
	@Override
	public Task getTask() {
		return task;
	}

	/** {@inheritDoc} */
	@Override
	public void setHearingAnalysis(final HearingAnalysis hearingAnalysis) {
		this.hearingAnalysis = hearingAnalysis;
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysis getHearingAnalysis() {
		return hearingAnalysis;
	}

	/** {@inheritDoc} */
	@Override
	public void setTaskSource(final ParoleHearingAnalysisTaskSource taskSource) {
		this.taskSource = taskSource;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingAnalysisTaskSource getTaskSource() {
		return taskSource;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HearingAnalysisTaskAssociation)) {
			return false;
		}
		HearingAnalysisTaskAssociation that = 
				(HearingAnalysisTaskAssociation) obj;
		if (this.getTask() == null) {
			throw new IllegalStateException("Task required");
		}
		if (!this.getTask().equals(that.getTask())) {
			return false;
		}
		if (this.getTaskSource() == null) {
			throw new IllegalStateException("Task source required");
		}
		if (!this.getTaskSource().equals(that.getTaskSource())) {
			return false;
		}
		if (this.getHearingAnalysis() == null) {
			throw new IllegalStateException("Hearing analysis required");
		}
		if (!this.getHearingAnalysis().equals(that.getHearingAnalysis())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getTask() == null) {
			throw new IllegalStateException("Task required");
		}
		if (this.getTaskSource() == null) {
			throw new IllegalStateException("Task source required");
		}
		if (this.getHearingAnalysis() == null) {
			throw new IllegalStateException("Hearing analysis required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTask().hashCode();
		hashCode = 29 * hashCode + this.getTaskSource().hashCode();
		hashCode = 29 * hashCode + this.getHearingAnalysis().hashCode();
		return hashCode;
	}
}
