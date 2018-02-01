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
package omis.unitmanagerreview.dao;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.domain.UnitManagerReview;

/**
 * Data access object for unit manager review.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public interface UnitManagerReviewDao extends GenericDao<UnitManagerReview> {

	/**
	 * Returns the specified unit manager review for the specified staff 
	 * assignment and offender.
	 * 
	 * @param staffAssignment staff assignment
	 * @param offender offender
	 * @return unit manager review
	 */
	UnitManagerReview find(StaffAssignment staffAssignment, Offender offender);
	
	/**
	 * Returns the specified unit manager review for the specified staff 
	 * assignment and offender excluding the specified unit manger review.
	 * 
	 * @param staffAssignment staff assignment
	 * @param offender offender
	 * @param excludedUnitManagerReview excluded unit manger review
	 * @return unit manager review
	 */
	UnitManagerReview findExcluding(StaffAssignment staffAssignment, 
			Offender offender, UnitManagerReview excludedUnitManagerReview);
}