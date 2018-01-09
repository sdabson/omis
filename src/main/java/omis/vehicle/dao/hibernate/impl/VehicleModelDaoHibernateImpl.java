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
	
	/* Parameter names. */
	private static final String VEHICLE_MAKE_PARAMETER_NAME = "vehicleMake";
	
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
}
