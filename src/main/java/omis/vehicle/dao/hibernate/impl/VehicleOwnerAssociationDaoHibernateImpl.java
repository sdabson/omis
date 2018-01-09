package omis.vehicle.dao.hibernate.impl;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
/* import omis.datatype.DateRange; */
import omis.vehicle.dao.VehicleOwnerAssociationDao;
import omis.vehicle.domain.VehicleOwnerAssociation;
/* import omis.vehicle.dao.VehicleAssociationDao; */
import omis.vehicle.domain.VehicleAssociation;
/* import omis.vehicle.domain.VehicleColor; */
/* import omis.vehicle.domain.VehicleModel;
import omis.person.domain.Person; */

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Vehicle owner association data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 5, 2014)
 * @since OMIS 3.0
 */
public class VehicleOwnerAssociationDaoHibernateImpl 
extends GenericHibernateDaoImpl<VehicleOwnerAssociation>
implements VehicleOwnerAssociationDao {

	/* Query names. */
	private static final String FIND_OWNER_BY_VEHICLE_ASSOCIATION
 		= "findOwnerByVehicleAssociation";
	
	/* Parameter names. */
	private static final String VEHICLE_ASSOCIATION_PARAMETER_NAME 
		= "vehicleAssociation";
	
	/**
	 * Instantiates an instance of vehicle association data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VehicleOwnerAssociationDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public VehicleOwnerAssociation findOwnerByVehicleAssociation(
		VehicleAssociation vehicleAssociation){
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
			FIND_OWNER_BY_VEHICLE_ASSOCIATION);
		
		q.setParameter(VEHICLE_ASSOCIATION_PARAMETER_NAME, vehicleAssociation);
		
		VehicleOwnerAssociation result 
			= (VehicleOwnerAssociation) q.uniqueResult();
		
		return result;
	}
}