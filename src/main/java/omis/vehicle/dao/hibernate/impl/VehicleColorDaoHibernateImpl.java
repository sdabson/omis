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
import omis.vehicle.dao.VehicleColorDao;
import omis.vehicle.domain.VehicleColor;

import org.hibernate.SessionFactory;

/**
 * vehicle color data access object hibernate implementation..
 * 
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */

public class VehicleColorDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VehicleColor> implements VehicleColorDao  {
	/* Query names. */
	
	private static final String FIND_VEHICLE_COLOR = "findVehicleColor";
	
	private static final String FIND_EXISTING_VEHICLE_COLOR = "findExistingVehicleColor";
	
	/* Parameters. */
	private static final String VEHICLE_COLOR_PARAM_NAME = "vehicleColorName";
	
	public VehicleColorDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleColor> findColors( ) {
		@SuppressWarnings("unchecked")
		List<VehicleColor> vehicleColors = getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_VEHICLE_COLOR)
		.list();
		return vehicleColors;
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleColor find(final String name ) {
		VehicleColor vehicleColor = (VehicleColor) getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_EXISTING_VEHICLE_COLOR)
		.setParameter(VEHICLE_COLOR_PARAM_NAME, name)
		.uniqueResult();
		return vehicleColor;
	}
}
