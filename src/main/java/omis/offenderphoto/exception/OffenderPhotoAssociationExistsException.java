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
package omis.offenderphoto.exception;

import omis.exception.DuplicateEntityFoundException;
import omis.offenderphoto.domain.OffenderPhotoAssociation;

/**
 * Thrown if offender photo association exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 22, 2019)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	private final OffenderPhotoAssociation offenderPhotoAssociation;
	
	/**
	 * Instantiates exception thrown if offender photo association exists.
	 */
	public OffenderPhotoAssociationExistsException() {
		this.offenderPhotoAssociation = null;
	}
	
	/**
	 * Instantiates exception thrown if offender photo association exists.
	 * 
	 * @param message message
	 * @param cause cause
	 * @param offenderPhotoAssociation offender photo association
	 */
	public OffenderPhotoAssociationExistsException(
			final String message, final Throwable cause,
			final OffenderPhotoAssociation offenderPhotoAssociation) {
		super(message, cause);
		this.offenderPhotoAssociation = offenderPhotoAssociation;
	}
	
	/**
	 * Instantiates exception thrown if offender photo association exists.
	 * 
	 * @param message message
	 */
	public OffenderPhotoAssociationExistsException(final String message) {
		super(message);
		this.offenderPhotoAssociation = null;
	}
	
	/**
	 * Instantiates exception thrown if offender photo association exists.
	 * 
	 * @param cause cause
	 */
	public OffenderPhotoAssociationExistsException(final Throwable cause) {
		super(cause);
		this.offenderPhotoAssociation = null;
	}
	
	/**
	 * Instantiates exception thrown if offender photo association exists.
	 * @param offenderPhotoAssociation offender photo association
	 */
	public OffenderPhotoAssociationExistsException(
			final OffenderPhotoAssociation offenderPhotoAssociation) {
		this.offenderPhotoAssociation = offenderPhotoAssociation;
	}
	
	/**
	 * Returns offender photo association.
	 * 
	 * @return offender photo association
	 */
	public OffenderPhotoAssociation getOffenderPhotoAssociation() {
		return this.offenderPhotoAssociation;
	}
}
