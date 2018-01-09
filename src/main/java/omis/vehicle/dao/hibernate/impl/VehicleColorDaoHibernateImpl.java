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
}
