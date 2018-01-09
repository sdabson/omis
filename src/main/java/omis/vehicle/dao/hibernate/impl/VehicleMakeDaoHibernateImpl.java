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
	public List<VehicleMake> findVehicleMake( ) {
		@SuppressWarnings("unchecked")
		List<VehicleMake> vehicleMakes = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_VEHICLE_MAKE)
			.list();
		return vehicleMakes;
	}
}

