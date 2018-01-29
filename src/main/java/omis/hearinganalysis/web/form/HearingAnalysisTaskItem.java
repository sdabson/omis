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
package omis.hearinganalysis.web.form;

import java.io.Serializable;

import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;

/**
 * Hearing analysis task item.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 16, 2018)
 * @since OMIS 3.0
 *
 */
public class HearingAnalysisTaskItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HearingAnalysisTaskAssociation taskAssociation;
	
	private HearingAnalysisTaskItemOperation itemOperation;
	
	/**
	 * Instantiates a default hearing analysis task item on a hearing analysis 
	 * home form.
	 */
	public HearingAnalysisTaskItem() {
		// Default instantiation
	}

	/**
	 * Returns the hearing analysis task association.
	 * 
	 * @return hearing analysis task association
	 */
	public HearingAnalysisTaskAssociation getTaskAssociation() {
		return taskAssociation;
	}

	/**
	 * Sets the hearing analysis task association.
	 * 
	 * @param taskAssociation hearing analysis task association
	 */
	public void setTaskAssociation(
			final HearingAnalysisTaskAssociation taskAssociation) {
		this.taskAssociation = taskAssociation;
	}

	/**
	 * Returns the hearing analysis item operation.
	 * 
	 * @return hearing analysis item operation
	 */
	public HearingAnalysisTaskItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the hearing analysis item operation.
	 * 
	 * @param itemOperation hearing analysis item operation
	 */
	public void
		setItemOperation(
				final HearingAnalysisTaskItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
