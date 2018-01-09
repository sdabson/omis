package omis.vehicle.dao.hibernate.impl;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.vehicle.dao.VehicleAssociationDao;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Vehicle association data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jul 22, 2014)
 * @since OMIS 3.0
 */
public class VehicleAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VehicleAssociation>
	implements VehicleAssociationDao {

	/* Query names. */
	
	private static final String FIND_VEHICLE_ASSOCIATION_QUERY_NAME 
		= "findVehicleAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
 		= "findVehicleAssociationExluding";
	
	private static final String FIND_EXCLUDING_SAVE_QUERY_NAME
 		= "findVehicleAssociationSaveExluding";
	
	private static final String FIND_VEHICLE_ASSOCIATION_BY_OFFENDER_QUERY_NAME
		= "findVehicleAssociationByOffender";

	/* Parameter names. */
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	private static final String VEHICLE_MODEL_PARAMETER_NAME = "vehicleModel";
	private static final String VEHICLE_MAKE_PARAMETER_NAME = "vehicleMake";
	private static final String VEHICLE_COLOR_PARAMETER_NAME = "vehicleColor";
	private static final String YEAR_PARAMETER_NAME = "year";
	private static final String VEHICLE_ASSOCIATION_EXCLUDING_PARAMETER_NAME 
		= "excludedVehicleAssociation";
	
	/**
	 * Instantiates an instance of vehicle association data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VehicleAssociationDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public VehicleAssociation find(final Offender offender, final DateRange dateRange,
		final VehicleModel vehicleModel, final VehicleColor vehicleColor, 
		final Integer year, final VehicleMake vehicleMake){
		VehicleAssociation vehicleAssociation;
		vehicleAssociation = (VehicleAssociation) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_VEHICLE_ASSOCIATION_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.setParameter(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
			.setParameter(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
			.setParameter(VEHICLE_MODEL_PARAMETER_NAME, vehicleModel)
			.setParameter(VEHICLE_COLOR_PARAMETER_NAME, vehicleColor)
			.setParameter(YEAR_PARAMETER_NAME, year)
			.setParameter(VEHICLE_MAKE_PARAMETER_NAME, vehicleMake);
		return vehicleAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public VehicleAssociation findExcluding(final VehicleModel vehicleModel, 
		final VehicleColor vehicleColor, final Integer year, 
		final DateRange dateRange, final String comments, 
		final VehicleAssociation excludedVehicleAssociation, 
		final VehicleMake vehicleMake, final Offender offender) {
		VehicleAssociation vehicleAssociation;
		vehicleAssociation = (VehicleAssociation) getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
			.setParameter(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
			.setParameter(VEHICLE_MODEL_PARAMETER_NAME, vehicleModel)
			.setParameter(VEHICLE_COLOR_PARAMETER_NAME, vehicleColor)
			.setParameter(YEAR_PARAMETER_NAME, year)
			.setParameter(VEHICLE_ASSOCIATION_EXCLUDING_PARAMETER_NAME,
				excludedVehicleAssociation)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.uniqueResult();
		return vehicleAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public VehicleAssociation findExcludingSave(final VehicleModel vehicleModel, 
		final VehicleColor vehicleColor, final Integer year, 
		final DateRange dateRange, final String comments, 
		final VehicleMake vehicleMake, final Offender offender) {
		VehicleAssociation vehicleAssociation;
		vehicleAssociation = (VehicleAssociation) getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_SAVE_QUERY_NAME)
			.setParameter(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
			.setParameter(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
			.setParameter(VEHICLE_MODEL_PARAMETER_NAME, vehicleModel)
			.setParameter(VEHICLE_COLOR_PARAMETER_NAME, vehicleColor)
			.setParameter(YEAR_PARAMETER_NAME, year)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.uniqueResult();
		return vehicleAssociation;
	}	
	
	/** {@inheritDoc} */
	@Override
	public List<VehicleAssociation> findByOffender(final Offender offender) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
			FIND_VEHICLE_ASSOCIATION_BY_OFFENDER_QUERY_NAME);
		
		q.setParameter(OFFENDER_PARAMETER_NAME, offender);
		
		@SuppressWarnings("unchecked")
		List<VehicleAssociation> result = (List<VehicleAssociation>) q.list();
		
		return result;
	}
}