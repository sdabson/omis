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

import omis.dao.GenericDao;
import omis.facility.domain.Facility;
import omis.health.domain.HealthAppointment;
import omis.health.domain.InternalReferral;

/**
 * Health Appointment Data Access Object.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.1 (Nov 2, 2018)
 * @since OMIS 3.0
 */
public interface HealthAppointmentDao
	extends GenericDao<HealthAppointment> {

	/** find health appointment by internal referral.
	 * @param internalReferral internal referral.
	 * @return health appointment. */
	HealthAppointment findByInternalReferral(
			InternalReferral internalReferral);
	
	/** find existing health appointment by date, start time, facility.
	 * @param date date
	 * @param startTime start time
	 * @param facility facility.
	 * @return health appointment. */
	HealthAppointment findExisting(Date date, Date startTime,
		Facility facility);
}