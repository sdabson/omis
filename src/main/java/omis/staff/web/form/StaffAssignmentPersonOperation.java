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
package omis.staff.web.form;

/**
 * Operation to be performed to associate person to staff assignment.
 * 
 * <p>Persons can be created or updated or an existing person can be used.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 27, 2018)
 * @since OMIS 3.0
 */
public enum StaffAssignmentPersonOperation {

	/** Create person. */
	CREATE,
	
	/** Update person. */
	UPDATE,
	
	/** Use existing. */
	USE_EXISTING;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}