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
package omis.health.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.MedicalFacility;
import omis.offender.domain.Offender;

/**
 * Data access object for external referral authorization requests.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 29, 2018)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorizationRequestDao
		extends GenericDao<ExternalReferralAuthorizationRequest> {

	/**
	 * Returns requests by facility.
	 * 
	 * @param facility facility
	 * @return requests by facility
	 */
	List<ExternalReferralAuthorizationRequest> findByFacility(
			Facility facility);
	
	/**
	 * Finds request.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param medicalFacility medical facility
	 * @return request
	 */
	ExternalReferralAuthorizationRequest find(Offender offender,
		Facility facility, Date date, MedicalFacility medicalFacility);
}