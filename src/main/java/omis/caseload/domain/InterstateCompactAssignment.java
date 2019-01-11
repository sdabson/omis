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
package omis.caseload.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.region.domain.State;

/**
 * Interstate compact assignment.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 6, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactAssignment extends Creatable, Updatable {

	/**
	 * Sets the ID of the interstate compact assignment.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the interstate compact assignment.
	 * 
	 * @return ID
	 */
	Long getId();

	/**
	 * Sets the officer case assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 */
	void setOfficerCaseAssignment(OfficerCaseAssignment officerCaseAssignment);
	
	/**
	 * Returns the officer case assignment.
	 * 
	 * @return officer case assignment
	 */
	OfficerCaseAssignment getOfficerCaseAssignment();
	
	/**
	 * Sets the interstate compact correctional status.
	 * 
	 * @param interstateCompactStatus interstate compact correctional status
	 */
	void setInterstateCompactStatus(
			InterstateCompactCorrectionalStatus interstateCompactStatus);
	
	/**
	 * Returns the interstate compact correctional status.
	 * 
	 * @return interstate compact correctional status
	 */
	InterstateCompactCorrectionalStatus getInterstateCompactStatus();
	
	/**
	 * Sets the interstate compact type category.
	 * 
	 * @param interstateCompactType interstate compact type category
	 */
	void setInterstateCompactType(
			InterstateCompactTypeCategory interstateCompactType);
	
	/**
	 * Returns the interstate compact type category.
	 * 
	 * @return interstate compact type category
	 */
	InterstateCompactTypeCategory getInterstateCompactType();
	
	/**
	 * Sets the interstate compact end reason category.
	 * 
	 * @param endReason interstate compact end reason category
	 */
	void setEndReason(InterstateCompactEndReasonCategory endReason);
	
	/**
	 * Returns the interstate compact end reason category.
	 * 
	 * @return interstate compact end reason category
	 */
	InterstateCompactEndReasonCategory getEndReason();
	
	/**
	 * Sets the jurisdiction.
	 * 
	 * @param jurisdiction state
	 */
	void setJurisdiction(State jurisdiction);
	
	/**
	 * Returns the jurisdiction.
	 * 
	 * @return state
	 */
	State getJurisdiction();
	
	/**
	 * Sets the jurisdiction state id.
	 * 
	 * @param jurisdictionStateId jurisdiction state id
	 */
	void setJurisdictionStateId(String jurisdictionStateId);
	
	/**
	 * Returns the jurisdiction state id.
	 * 
	 * @return jurisdiction state id
	 */
	String getJurisdictionStateId();
	
	/**
	 * Sets the projected end date.
	 * 
	 * @param projectedEndDate projected end date
	 */
	void setProjectedEndDate(Date projectedEndDate);
	
	/**
	 * Returns the projected end date.
	 * 
	 * @return projected end date
	 */
	Date getProjectedEndDate();
}