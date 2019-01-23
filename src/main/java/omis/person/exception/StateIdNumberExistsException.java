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

import omis.exception.BusinessException;
import omis.person.domain.Person;

/**
 * Instantiates exception thrown when State ID number is already used.
 * 
 * <p>Used State ID number is reported using the {@code getStateIdNumber()}
 * method; the person using the State ID number is reported using the
 * {@code getPerson()} method.
 * 
 * <p>If both {@code person} and {@code stateIdNumber} are provided, the
 * following must evaluate to true:
 * 
 * <pre>
 *   getStateIdNumber().equals(getPerson().getIdentity().getStateIdNumber())}
 * </pre>
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 18, 2019)
 * @since OMIS 3.0
 */
public class StateIdNumberExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	private final String stateIdNumber;
	
	private final Person person;
	
	/**
	 * Instantiates exception thrown when State ID number is already used.
	 */
	public StateIdNumberExistsException() {
		this.stateIdNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when State ID number is already used.
	 * 
	 * <p>If both {person} and {stateIdNumber} and {@person} has an identity,
	 * {@code stateIdNumber} must match that of {@code person.identity}.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param person person
	 * @param stateIdNumber State ID number
	 * @throws IllegalArgumentException if State ID number does not match
	 * that of person; person must have an identity for it to match
	 */
	public StateIdNumberExistsException(
			final String message, final Throwable cause,
			final Person person, final String stateIdNumber) {
		super(message, cause);
		if (stateIdNumber != null && person != null) {
			this.checkStateIdNumberMatches(stateIdNumber, person);
		}
		this.stateIdNumber = null;
		this.person = person;
	}
	
	/**
	 * Instantiates exception thrown when State ID number is already used.
	 * 
	 * @param message message
	 */
	public StateIdNumberExistsException(final String message) {
		super(message);
		this.stateIdNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when State ID number is already used.
	 * 
	 * @param cause cause
	 */
	public StateIdNumberExistsException(final Throwable cause) {
		super(cause);
		this.stateIdNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when State ID number is already used.
	 * 
	 * If both {person} and {stateIdNumber} and {@person} has an identity,
	 * {@code stateIdNumber} must match that of {@code person.identity}.
	 * 
	 * @param stateIdNumber State ID number
	 * @param person person
	 * @throws IllegalArgumentException if State ID number does not match
	 * that of person; person must have an identity for it to match
	 */
	public StateIdNumberExistsException(
			final String stateIdNumber, final Person person) {
		super();
		if (stateIdNumber != null && person != null) {
			this.checkStateIdNumberMatches(stateIdNumber, person);
		}
		this.stateIdNumber = stateIdNumber;
		this.person = person;
	}
	
	/**
	 * Returns State ID number.
	 * 
	 * @return State ID number
	 */
	public String getStateIdNumber() {
		return this.stateIdNumber;
	}
	
	/**
	 * Returns person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}
	
	// Ensures that if person has an identity and State ID is provided
	private void checkStateIdNumberMatches(
			final String stateIdNumber, final Person person) {
		if (person.getIdentity() == null
				|| !stateIdNumber.equals(
						person.getIdentity().getStateIdNumber())) {
			throw new IllegalArgumentException(
					"State ID number does not match that of person");
		}
	}
}