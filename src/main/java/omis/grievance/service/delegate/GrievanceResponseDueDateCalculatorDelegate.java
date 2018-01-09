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
package omis.grievance.service.delegate;

import java.util.Date;

import omis.util.DateManipulator;

/**
 * Delegate to calculate grievance response due date.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class GrievanceResponseDueDateCalculatorDelegate {
	
	private final Integer daysUntilResponseDue;

	/**
	 * Instantiates delegate to calculate grievance response due date.
	 * 
	 * @param daysUntilResponseDue days until response due
	 */
	public GrievanceResponseDueDateCalculatorDelegate(
			final Integer daysUntilResponseDue) {
		this.daysUntilResponseDue = daysUntilResponseDue;
	}
	
	/**
	 * Returns response due date calculated from opened date.
	 * 
	 * @param openedDate opened date
	 * @return response due date calculated from opened date 
	 */
	public Date calculate(final Date openedDate) {
		DateManipulator manipulator
			= new DateManipulator(openedDate);
		manipulator.changeDate(this.daysUntilResponseDue);
		return manipulator.getDate();
	}
}