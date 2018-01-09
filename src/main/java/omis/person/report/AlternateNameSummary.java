/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.person.report;

import java.io.Serializable;

/**
 * Alternate name summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 23, 2017)
 * @since OMIS 3.0
 */
public class AlternateNameSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final String suffix;
	private final String categoryName;
	
	/**
	 * Instantiates an instance of alternate name summary.
	 * 
	 * @param id id
	 * @param firstName first name
	 * @param middleName middle name
	 * @param lastName last name
	 * @param suffix suffix
	 * @param categoryName category name
	 */
	public AlternateNameSummary(final Long id,
		final String firstName, final String middleName,
		final String lastName, final String suffix, final String categoryName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix	= suffix;
		this.categoryName = categoryName;
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns the middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Returns the last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns the suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * Returns the category name.
	 * 
	 * @return category name
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
}
