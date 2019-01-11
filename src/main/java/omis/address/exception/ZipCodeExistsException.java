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
package omis.address.exception;

import omis.address.domain.ZipCode;
import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown if zip code exists.
 * 
 * @author Yidong Li
 * @version 0.0.1 (Jan 8, 2019)
 * @since OMIS 3.0
 */
public class ZipCodeExistsException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;
	
	private final ZipCode zipCode; 

	/** Instantiates exception thrown if zip code exists. */
	public ZipCodeExistsException() {
		// Default instantiation
		this.zipCode = null;
	}
	
	/**
	 * Instantiates exception thrown if zip code exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ZipCodeExistsException(final String message, final Throwable cause) {
		super(message, cause);
		this.zipCode = null;
	}
	
	/**
	 * Instantiates exception thrown if zip code exists with message.
	 * 
	 * @param message message
	 */
	public ZipCodeExistsException(final String message) {
		super(message);
		this.zipCode = null;
	}
	
	/**
	 * Instantiates exception thrown if zip code exists with cause.
	 * 
	 * @param cause cause
	 */
	public ZipCodeExistsException(final Throwable cause) {
		super(cause);
		this.zipCode = null;
	}
	
	/**
	 * Instantiates exception thrown if zip code exists with cause.
	 * 
	 * @param cause cause
	 * @param message message
	 * @param zipCode zip code
	 */
	public ZipCodeExistsException(final String message, final Throwable cause,
		final ZipCode zipCode) {
		super(message, cause);
		this.zipCode = zipCode;
	}
	
	/**
	 * Instantiates exception thrown if zip code exists with cause.
	 * 
	 * @param zipCode zip code
	 */
	public ZipCodeExistsException(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * Returns city.
	 * 
	 * @return city
	 */
	public ZipCode getZipCode() {
		return this.zipCode;
	}
}