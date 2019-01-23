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
package omis.religion.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.religion.domain.ReligiousPreference;

/**
 * Thrown when religious preference exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 22, 2019)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	private final ReligiousPreference religiousPreference;
	
	/**
	 * Instantiates exception thrown when religious preference exists.
	 */
	public ReligiousPreferenceExistsException() {
		this.religiousPreference = null;
	}
	
	/**
	 * Thrown when religious preference exists.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param religiousPreference religious preference
	 */
	public ReligiousPreferenceExistsException(
			final String message, final Throwable cause,
			final ReligiousPreference religiousPreference) {
		super(message, cause);
		this.religiousPreference = religiousPreference;
	}
	
	/**
	 * Instantiates exception thrown when religious preference exists.
	 * 
	 * @param message message
	 */
	public ReligiousPreferenceExistsException(final String message) {
		super(message);
		this.religiousPreference = null;
	}
	
	/**
	 * Instantiates exception thrown when religious preference exists.
	 * 
	 * @param cause cause
	 */
	public ReligiousPreferenceExistsException(final Throwable cause) {
		super(cause);
		this.religiousPreference = null;
	}
	
	/**
	 * Instantiates exception thrown when religious preference exists.
	 * 
	 * @param religiousPreference religious preference
	 */
	public ReligiousPreferenceExistsException(
			final ReligiousPreference religiousPreference) {
		this.religiousPreference = religiousPreference;
	}
	
	/**
	 * Returns religious preference.
	 * 
	 * @return religious preference
	 */
	public ReligiousPreference getReligiousPreference() {
		return this.religiousPreference;
	}
}