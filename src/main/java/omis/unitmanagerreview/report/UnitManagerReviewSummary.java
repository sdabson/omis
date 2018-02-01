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
package omis.unitmanagerreview.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Unit manager review summary.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	
	private final String staffMemberLastName;
	
	private final String staffMemberFirstName;
	
	private final String staffMemberMiddleName;
	
	private final String staffTitle;
	
	private final Date date;
	
	/**
	 * Instantiates an implementation of unit manager review summary.
	 * 
	 * @param id unit manager review id
	 * @param staffMemberLastName staff member last name
	 * @param staffMemberFirstName staff member first name
	 * @param staffMemberMiddleName staff member middle name
	 * @param staffTitle staff title
	 * @param date date
	 */
	public UnitManagerReviewSummary(final Long id,
			final String staffMemberLastName,
			final String staffMemberFirstName,
			final String staffMemberMiddleName,
			final String staffTitle,
			final Date date) {
		this.id = id;
		this.staffMemberFirstName = staffMemberFirstName;
		this.staffMemberLastName = staffMemberLastName;
		this.staffMemberMiddleName = staffMemberMiddleName;
		this.staffTitle = staffTitle;
		this.date = date;
	}

	/**
	 * Returns the id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the staff member last name.
	 *
	 * @return staff member last name
	 */
	public String getStaffMemberLastName() {
		return staffMemberLastName;
	}

	/**
	 * Returns the staff member first name.
	 *
	 * @return staff member first name
	 */
	public String getStaffMemberFirstName() {
		return staffMemberFirstName;
	}

	/**
	 * Returns the staff member middle name.
	 *
	 * @return staff member middle name
	 */
	public String getStaffMemberMiddleName() {
		return staffMemberMiddleName;
	}

	/**
	 * Returns the staff title.
	 *
	 * @return staff title
	 */
	public String getStaffTitle() {
		return staffTitle;
	}

	/**
	 * Returns the date.
	 *
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
}