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
package omis.contact.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown if contact exists.
 * 
 * @author Yidong Li
 * @version 0.0.1 (Jan 25, 2018)
 * @since OMIS 3.0
 */
public class ContactExistsException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown if zip code exists. */
	public ContactExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown if contact exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ContactExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown if contact exists with message.
	 * 
	 * @param message message
	 */
	public ContactExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown if contact exists with cause.
	 * 
	 * @param cause cause
	 */
	public ContactExistsException(final Throwable cause) {
		super(cause);
	}
}