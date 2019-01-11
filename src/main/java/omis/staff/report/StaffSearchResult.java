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
package omis.staff.report;

import omis.search.report.PersonSearchResult;

public class StaffSearchResult extends PersonSearchResult {
	private static final long serialVersionUID = 1L;
	final private Long staffId;
	final private String titleName;
	final private String organizationName;


	/** Constructor. */
	public StaffSearchResult(final Long staffId, final String titleName,
			final String organizationName, final Long nameId,
			final Long personId, final String firstName,
			final String middleName, final String lastName, 
			final String suffixName) {
		super(nameId, personId, firstName, middleName, lastName, suffixName);
		this.staffId = staffId;
		this.titleName = titleName;
		this.organizationName = organizationName;
	}

	/** Gets staff id.
	 * @return staffId. */
	public Long getStaffId() { return this.staffId; }

	/** Gets title name.
	 * @return title name. */
	public String getTitleName() { return this.titleName; }

	/** Gets organization name.
	 * @return organization name. */
	public String getOrganizationName() { return this.organizationName; }
}
