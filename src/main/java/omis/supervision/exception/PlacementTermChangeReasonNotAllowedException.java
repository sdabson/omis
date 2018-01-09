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
 * Thrown when an attempt is made to use a placement term change reason
 * that is not allowed. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 12, 2015)
 * @since OMIS 3.0
 */
public class PlacementTermChangeReasonNotAllowedException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates default. */
	public PlacementTermChangeReasonNotAllowedException() {
		// Default instantiations
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PlacementTermChangeReasonNotAllowedException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public PlacementTermChangeReasonNotAllowedException(
			final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public PlacementTermChangeReasonNotAllowedException(
			final Throwable cause) {
		super(cause);
	}
}