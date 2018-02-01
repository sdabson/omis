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
package omis.chronologicalnote.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Chronological note category association exists exception.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 29, 2018) 
 * @since OMIS 3.0
 */
public class ChronologicalNoteCategoryAssociationExistsException extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;

	/** 
	 * Instantiates a default instance of chronological note category association exists exception.
	 */
	public ChronologicalNoteCategoryAssociationExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a chronological note category association exists exception with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ChronologicalNoteCategoryAssociationExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a chronological note category association exists exception with the specified message.
	 * 
	 * @param message message
	 */
	public ChronologicalNoteCategoryAssociationExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a chronological note category association exists exception with the specified cause.
	 * 
	 * @param cause cause
	 */
	public ChronologicalNoteCategoryAssociationExistsException(final Throwable cause) {
		super(cause);
	}
}