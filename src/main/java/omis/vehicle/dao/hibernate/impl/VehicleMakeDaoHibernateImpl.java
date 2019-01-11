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

package omis.vehicle.dao.hibernate.impl;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.vehicle.dao.VehicleMakeDao;
import omis.vehicle.domain.VehicleMake;

import org.hibernate.SessionFactory;

/**
 * Vehicle make data access object.
 * 
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */

public class VehicleMakeDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VehicleMake> implements VehicleMakeDao  {
	/* Query names. */
	private static final String FIND_VEHICLE_MAKE = "findVehicleMake";
	
	private static final String FIND_EXISTING_VEHICLE_MAKE = "findExistingVehicleMake";
	
	/* Parameters. */
	private static final String VEHICLE_MAKE_PARAM_NAME = "vehicleMakeName";

	/**
	 * Instantiates an instance of vehicle make data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VehicleMakeDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleMake find(final String name) {
		VehicleMake vehicleMake = (VehicleMake) getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_EXISTING_VEHICLE_MAKE)
		.setParameter(VEHICLE_MAKE_PARAM_NAME, name)
		.uniqueResult();
		return vehicleMake;
	}

	@Override
	public List<VehicleMake> findVehicleMake() {
		@SuppressWarnings("unchecked")
		List<VehicleMake> vehicleMakes = getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_VEHICLE_MAKE)
		.list();
		return vehicleMakes;
	}
}

