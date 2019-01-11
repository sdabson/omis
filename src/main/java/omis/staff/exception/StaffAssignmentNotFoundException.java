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
package omis.staff.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate that an expected staff member assignment does not exist.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentNotFoundException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public StaffAssignmentNotFoundException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public StaffAssignmentNotFoundException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public StaffAssignmentNotFoundException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public StaffAssignmentNotFoundException(final Throwable cause) {
		super(cause);
	}
}