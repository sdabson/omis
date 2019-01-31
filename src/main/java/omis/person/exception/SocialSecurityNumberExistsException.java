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
 * Instantiates exception thrown when social security number is already used.
 * 
 * <p>Used social security number is reported using the
 * {@code getSocialSecurityNumber()} method; the person using the social
 * security number is reported using the {@code getPerson()} method.
 * 
 * <p>If both {@code person} and {@code socialSecurityNumber} are provided,
 * the following must evaluate to true:
 * 
 * <pre>
 *   getSocialSecurityNumber().equals(getPerson().getIdentity()
 *       .getSocialSecurityNumber())
 * </pre>
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 23, 2019)
 * @since OMIS 3.0
 */
public class SocialSecurityNumberExistsException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	private final Integer socialSecurityNumber;
	
	private final Person person;
	
	/**
	 * Instantiates exception thrown when social security number is already
	 * used.
	 */
	public SocialSecurityNumberExistsException() {
		this.socialSecurityNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when social security number is already
	 * used.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param person person
	 * @param socialSecurityNumber social security number
	 */
	public SocialSecurityNumberExistsException(
			final String message, final Throwable cause,
			final Person person, final Integer socialSecurityNumber) {
		super(message, cause);
		if (socialSecurityNumber != null && person != null) {
			this.checkSocialSecurityNumberMatches(socialSecurityNumber, person);
		}
		this.socialSecurityNumber = socialSecurityNumber;
		this.person = person;
	}

	/**
	 * Instantiates exception thrown when social security number is used.
	 * 
	 * @param message message
	 */
	public SocialSecurityNumberExistsException(final String message) {
		super(message);
		this.socialSecurityNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when social security number is used.
	 * 
	 * @param cause cause
	 */
	public SocialSecurityNumberExistsException(final Throwable cause) {
		super(cause);
		this.socialSecurityNumber = null;
		this.person = null;
	}
	
	/**
	 * Instantiates exception thrown when social security number is used.
	 * 
	 * @param socialSecurityNumber social security number
	 * @param person person
	 */
	public SocialSecurityNumberExistsException(
			final Integer socialSecurityNumber, final Person person) {
		super();
		if (socialSecurityNumber != null && person != null) {
			this.checkSocialSecurityNumberMatches(socialSecurityNumber, person);
		}
		this.socialSecurityNumber = socialSecurityNumber;
		this.person = person;
	}
	
	/**
	 * Returns social security number.
	 * 
	 * @return social security number
	 */
	public Integer getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}
	
	/**
	 * Returns person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}
	
	// Ensures that if person has an identity and social security number is
	// provided that it matches that supplied
	private void checkSocialSecurityNumberMatches(
			final Integer socialSecurityNumber, final Person person) {
		if (person.getIdentity() == null
				|| !socialSecurityNumber.equals(person.getIdentity()
						.getSocialSecurityNumber())) {
			throw new IllegalArgumentException(
					"Social security number does not match that of person");
		}
	}
}