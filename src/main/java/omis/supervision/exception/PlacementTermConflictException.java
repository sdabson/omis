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

import java.util.List;

import omis.exception.BusinessException;
import omis.supervision.domain.PlacementTerm;

/**
 * Thrown to indicate that two or more placement terms conflict.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 5, 2015)
 * @since OMIS 3.0
 */
public class PlacementTermConflictException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	private final List<PlacementTerm> conflicts;
	
	/** Default constructor. */
	public PlacementTermConflictException() {
		this.conflicts = null;
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 * @param conflicts conflicting placement terms
	 */
	public PlacementTermConflictException(
			final String message, final Throwable cause,
			final List<PlacementTerm> conflicts) {
		super(message, cause);
		this.conflicts = conflicts;
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public PlacementTermConflictException(final String message) {
		super(message);
		this.conflicts = null;
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public PlacementTermConflictException(final Throwable cause) {
		super(cause);
		this.conflicts = null;
	}
	
	/**
	 * Instantiates with conflicting placement terms.
	 * 
	 * @param conflicts conflicting placement terms
	 */
	public PlacementTermConflictException(final List<PlacementTerm> conflicts) {
		this.conflicts = conflicts;
	}
	
	/**
	 * Returns conflicting placement terms.
	 * 
	 * @return conflicting placement terms
	 */
	public List<PlacementTerm> getConflicts() {
		return this.conflicts;
	}
}