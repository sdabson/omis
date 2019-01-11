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
package omis.staff.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.staff.domain.StaffPhotoAssociation;

/**
 * Data access object for staff photo association.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public interface StaffPhotoAssociationDao
		extends GenericDao<StaffPhotoAssociation> {
	
	/**
	 * Returns photo associations by staff member.
	 * 
	 * @param staffMember staff member
	 * @return photo associations by staff member
	 */
	List<StaffPhotoAssociation> findByStaffMember(Person staffMember);
	
	/**
	 * Counts photo associations by staff member.
	 * 
	 * @param staffMember staff member
	 * @return photo associations by staff member
	 */
	long countByStaffMember(Person staffMember);

	/**
	 * Throws {@code UnsupportedOperationException} as returning all staff
	 * photos is not allowed.
	 * 
	 * @return does not return
	 * @throws UnsupportedOperationException always
	 */
	@Override
	List<StaffPhotoAssociation> findAll();
}