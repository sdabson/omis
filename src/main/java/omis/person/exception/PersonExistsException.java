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
package omis.person.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;

/**
 * Thrown when person exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class PersonExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	private final Person person;
	
	/** Instantiates exception thrown when person exists. */
	public PersonExistsException() {
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when person exists.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param person existing person
	 */
	public PersonExistsException(
			final String message, final Throwable cause, final Person person) {
		super(message, cause);
		this.person = person;
	}
	
	/**
	 * Instantiates exception thrown when person exists.
	 * 
	 * @param message message
	 */
	public PersonExistsException(final String message) {
		super(message);
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when person exists.
	 * 
	 * @param cause cause
	 */
	public PersonExistsException(final Throwable cause) {
		super(cause);
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when person exists.
	 * 
	 * @param person person
	 */
	public PersonExistsException(final Person person) {
		this.person = person;
	}
	
	/**
	 * Returns person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}
}