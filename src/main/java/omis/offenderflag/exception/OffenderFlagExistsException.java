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
package omis.offenderflag.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.offenderflag.domain.OffenderFlag;

/**
 * Exception thrown when offender flag exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 22, 2019)
 * @since OMIS 3.0
 */
public class OffenderFlagExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	private final OffenderFlag offenderFlag;
	
	/**
	 * Instantiates exception thrown when offender flag exists.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param offenderFlag offender flag
	 */
	public OffenderFlagExistsException(
			final String message, final Throwable cause,
			final OffenderFlag offenderFlag) {
		super(message, cause);
		this.offenderFlag = offenderFlag;
	}
	
	/**
	 * Instantiates exception thrown when offender flag exists.
	 * 
	 * @param message message
	 */
	public OffenderFlagExistsException(final String message) {
		super(message);
		this.offenderFlag = null;
	}
	
	/**
	 * Instantiates exception thrown when offender flag exists.
	 * 
	 * @param cause cause
	 */
	public OffenderFlagExistsException(final Throwable cause) {
		super(cause);
		this.offenderFlag = null;
	}
	
	/**
	 * Instantiates exception thrown when offender flag exists.
	 * 
	 * @param offenderFlag offender flag
	 */
	public OffenderFlagExistsException(final OffenderFlag offenderFlag) {
		this.offenderFlag = offenderFlag;
	}
	
	/**
	 * Returns offender flag.
	 * 
	 * @return offender flag
	 */
	public OffenderFlag getOffenderFlag() {
		return this.offenderFlag;
	}
}