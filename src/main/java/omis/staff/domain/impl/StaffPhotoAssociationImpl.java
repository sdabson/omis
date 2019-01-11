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
package omis.staff.domain.impl;

import omis.personphoto.domain.impl.AbstractPersonPhotoAssociation;
import omis.staff.domain.StaffPhotoAssociation;

/**
 * Implementation of association of photo to staff member.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class StaffPhotoAssociationImpl
		extends AbstractPersonPhotoAssociation
		implements StaffPhotoAssociation {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates implementation of association of photo to staff member.
	 */
	public StaffPhotoAssociationImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		StaffPhotoAssociation that = (StaffPhotoAssociation) obj;
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		return hashCode;
	}
	
	/**
	 * Returns string representation of staff photo association including
	 * details of staff member and photo.
	 * 
	 * @return string representation of staff photo association including
	 * details of staff member and photo
	 */
	@Override
	public String toString() {
		final String personLastName;
		final String personFirstName;
		if (this.getPerson() != null && this.getPerson().getName() != null) {
			personLastName = this.getPerson().getName().getLastName();
			personFirstName = this.getPerson().getName().getFirstName();
		} else {
			personLastName = null;
			personFirstName = null;
		}
		final String photoFilename;
		if (this.getPhoto() != null) {
			photoFilename = this.getPhoto().getFilename();
		} else {
			photoFilename = null;
		}
		return String.format("#%d: person: %s, %s; photo: %s", this.getId(),
				personLastName, personFirstName, photoFilename);
	}
}