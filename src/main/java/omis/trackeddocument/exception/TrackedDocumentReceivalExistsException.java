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
package omis.trackeddocument.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Exception thrown when document exists.
 *
 * @author Yidong Li
 * @
 * @version 0.0.1 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class TrackedDocumentReceivalExistsException
		extends DuplicateEntityFoundException {
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when document receival exists. */
	public TrackedDocumentReceivalExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public TrackedDocumentReceivalExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param message message
	 */
	public TrackedDocumentReceivalExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param cause cause
	 */
	public TrackedDocumentReceivalExistsException(final Throwable cause) {
		super(cause);
	}
}