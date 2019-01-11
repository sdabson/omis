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
package omis.health.service.delegate;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.exception.HealthAppointmentExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for health appointment.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 2, 2018)
 * @since OMIS 3.0
 */
public class HealthAppointmentDelegate {
	private final InstanceFactory<HealthAppointment>
		healthAppointmentInstanceFactory;
	private final HealthAppointmentDao healthAppointmentDao;
	
	/**
	 * Instantiates a delegate for health appointment.
	 * @param healthAppointmentInstanceFactory
	 * health appointment instance factory
	 * @param healthAppointmentDao health appointment dao
	 * 
	 */
	public HealthAppointmentDelegate(
		final InstanceFactory<HealthAppointment>
		healthAppointmentInstanceFactory,
		final HealthAppointmentDao healthAppointmentDao) {
		this.healthAppointmentInstanceFactory
			= healthAppointmentInstanceFactory;
		this.healthAppointmentDao = healthAppointmentDao;
	}

	/**
	 * Creates and persists a health appointment.
	 * 
	 * @param date date
	 * @param startTime start time
	 * @param endTime end time
	 * @param status health appointment status
	 * @param timeKept time kept
	 * @param facility facility
	 * @throws HealthAppointmentExistsException if already exists
	 * @return Health appointment
	 */
	public HealthAppointment create(
		final Date date,
		final Date startTime,
		final Date endTime,
		final HealthAppointmentStatus status,
		final Date timeKept,
		final Facility facility)
			throws HealthAppointmentExistsException {
		if(this.healthAppointmentDao.findExisting(date, startTime,
			facility)!=null) {
			throw new HealthAppointmentExistsException("Health"
					+ "appointment already exists"); 
		}
		HealthAppointment healthAppointment
		= this.healthAppointmentInstanceFactory.createInstance();

		return this.healthAppointmentDao.makePersistent(
			healthAppointment);
	} 
}