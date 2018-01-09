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
package omis.supervision.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate the existence of a placement term before a null start date 
 * placement term.
 *
 * @author Josh Divine
 * @version 0.0.1 (July 7, 2017)
 * @since OMIS 3.0
 */
public class PlacementTermExistsBeforeException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates default exception. */
	public PlacementTermExistsBeforeException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PlacementTermExistsBeforeException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public PlacementTermExistsBeforeException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public PlacementTermExistsBeforeException(final Throwable cause) {
		super(cause);
	}
}