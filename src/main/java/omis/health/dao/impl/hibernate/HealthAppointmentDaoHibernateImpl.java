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
package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.InternalReferral;

import java.util.Date;

import org.hibernate.SessionFactory;

/**
 * Health Appointment Data Access Object Hibernate implementation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.1 (Nov 2, 2018)
 * @since OMIS 3.0
 */
public class HealthAppointmentDaoHibernateImpl
	extends GenericHibernateDaoImpl<HealthAppointment>
	implements HealthAppointmentDao {
	/* Queries */
	private static final String FIND_HEALTH_APPOINTMENT_BY_INTERNAL_REFERRAL
	= "findHealthAppointmentByInternalReferral";
	private static final String FIND_EXISTING_HEALTH_APPOINTMENT
	= "finsExistingHealthAppointment";
	
	/* Parameters. */
	private static final String DATE_PARAM_NAME = "date";
	private static final String START_TIME_PARAM_NAME = "startTime";
	private static final String FACILITY_PARAM_NAME = "facility";

	/**
	 * Instantiates an instance of health appointment data access object
	 * hibernate implementation.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthAppointmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointment findByInternalReferral(
			final InternalReferral intennalReferral) {
		return (HealthAppointment) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_HEALTH_APPOINTMENT_BY_INTERNAL_REFERRAL)
				.setParameter("internalReferral", intennalReferral)
				.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthAppointment findExisting(final Date date,
		final Date startTime, final Facility facility) {
		return (HealthAppointment) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_HEALTH_APPOINTMENT)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(START_TIME_PARAM_NAME, startTime)
			.setParameter(FACILITY_PARAM_NAME, facility)
			.setReadOnly(true)
			.uniqueResult();
	}
}