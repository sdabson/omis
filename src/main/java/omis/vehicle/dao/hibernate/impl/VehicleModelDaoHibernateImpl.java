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
import omis.vehicle.dao.VehicleModelDao;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

import org.hibernate.SessionFactory;

/**
 * Vehicle model data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */
public class VehicleModelDaoHibernateImpl extends
		GenericHibernateDaoImpl<VehicleModel> implements VehicleModelDao {
	
	/* Query names. */
	private static final String FIND_VEHICLE_MODELS_BY_MAKE_QUERY_NAME
		= "findVehicleModelsByMake";
	private static final String FIND_VEHICLE_MODEL = "findVehicleModel";
	
	/* Parameter names. */
	private static final String VEHICLE_MAKE_PARAMETER_NAME = "vehicleMake";
	private static final String VEHICLE_MAKE_NAME_PARAMETER_NAME = "vehicleMakeName";
	
	/**
	 * Instantiates an instance of vehicle model data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VehicleModelDaoHibernateImpl(final SessionFactory sessionFactory,
		final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<VehicleModel> findVehicleMoldelByMake(final VehicleMake 
		vehicleMake) {
		@SuppressWarnings("unchecked")
		List<VehicleModel> vehicleModels = getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_VEHICLE_MODELS_BY_MAKE_QUERY_NAME)
		.setParameter(VEHICLE_MAKE_PARAMETER_NAME, vehicleMake)
		.list();
		return vehicleModels;
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleModel find(final VehicleMake vehicleMake,
		final String name) {
		VehicleModel vehicleModel = (VehicleModel) getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_VEHICLE_MODEL)
		.setParameter(VEHICLE_MAKE_PARAMETER_NAME, vehicleMake)
		.setParameter(VEHICLE_MAKE_NAME_PARAMETER_NAME, name)
		.uniqueResult();
		return vehicleModel;
	}
}
