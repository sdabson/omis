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
package omis.staff.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.media.domain.Photo;
import omis.person.domain.Person;
import omis.staff.dao.StaffPhotoAssociationDao;
import omis.staff.domain.StaffPhotoAssociation;
import omis.staff.exception.StaffPhotoAssociationExistsException;

/**
 * Delegate for associations of staff photos.
 * 
 * <p>Manages association of a single photo to a staff member.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class StaffPhotoAssociationDelegate {
	
	private final InstanceFactory<StaffPhotoAssociation>
	staffPhotoAssociationInstanceFactory;
	
	private final StaffPhotoAssociationDao staffPhotoAssociationDao;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates delegate for associations of staff photos.
	 * 
	 * @param staffPhotoAssociationInstanceFactory instance factory for
	 * associations of staff photos
	 * @param staffPhotoAssociationDao data access object for associations
	 * of staff photos
	 * @param auditComponentRetriever audit component retriever
	 */
	public StaffPhotoAssociationDelegate(
			final InstanceFactory<StaffPhotoAssociation>
				staffPhotoAssociationInstanceFactory,
			final StaffPhotoAssociationDao staffPhotoAssociationDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.staffPhotoAssociationInstanceFactory
			= staffPhotoAssociationInstanceFactory;
		this.staffPhotoAssociationDao = staffPhotoAssociationDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates association of photo to staff member.
	 * 
	 * @param staffMember staff member
	 * @param photo photo
	 * @return association of photo to staff member
	 * @throws StaffPhotoAssociationExistsException if staff member is
	 * already associated with a photo
	 */
	public StaffPhotoAssociation create(
			final Person staffMember,
			final Photo photo)
					throws StaffPhotoAssociationExistsException {
		if (this.staffPhotoAssociationDao.countByStaffMember(staffMember) > 0) {
			throw new StaffPhotoAssociationExistsException(
					"Photo already exists for staff member");
		}
		StaffPhotoAssociation association
			= this.staffPhotoAssociationInstanceFactory.createInstance();
		association.setPerson(staffMember);
		association.setPhoto(photo);
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.staffPhotoAssociationDao.makePersistent(association);
	}
	
	/**
	 * Returns association of photo to staff member.
	 * 
	 * @param staffMember staff member
	 * @return association of photo to staff member
	 * @throws IllegalArgumentException if the staff member has more than
	 * one photo association
	 */
	public StaffPhotoAssociation findByStaffMember(final Person staffMember) {
		long count = this.staffPhotoAssociationDao
				.countByStaffMember(staffMember);
		if (count > 1) {
			throw new IllegalArgumentException(String.format(
					"Staff member has %d photos - only 1 is allowed",
					count));
		} else if (count == 1) {
			return this.staffPhotoAssociationDao
					.findByStaffMember(staffMember).get(0);
		} else {
			return null;
		}
	}

	/**
	 * Removes staff photo association.
	 * 
	 * @param association staff photo association to remove
	 */
	public void remove(StaffPhotoAssociation association) {
		this.staffPhotoAssociationDao.makeTransient(association);
	}
}