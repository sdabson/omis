package omis.vehicle.dao.hibernate.impl;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.vehicle.dao.VehicleLicenseDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleLicense;
/* import omis.region.domain.State; */


import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Vehicle license data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 5, 2014)
 * @since OMIS 3.0
 */
public class VehicleLicenseDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VehicleLicense>
	implements VehicleLicenseDao {

	/* Query names. */
	private static final String FIND_LICENSE_BY_VEHICLE_ASSOCIATION_QUERY_NAME
		= "findLicenseByVehicleAssociation";
	
	/* Parameter names. */
	private static final String VEHICLE_ASSOCIATION_PARAMETER_NAME 
		= "vehicleAssociation";
	
	/**
	 * Instantiates an instance of vehicle license data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VehicleLicenseDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleLicense findLicenseByVehicleAssociation(
		final VehicleAssociation vehicleAssociation){
			final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LICENSE_BY_VEHICLE_ASSOCIATION_QUERY_NAME);
			q.setParameter(VEHICLE_ASSOCIATION_PARAMETER_NAME,
				vehicleAssociation);
		
			final VehicleLicense result = (VehicleLicense) q.uniqueResult();
			return result;
	}
}