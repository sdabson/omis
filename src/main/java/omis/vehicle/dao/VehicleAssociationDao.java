package omis.vehicle.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/**
 * Vehicle association data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jul 22, 2014)
 * @since OMIS 3.0
 */
public interface VehicleAssociationDao extends GenericDao<VehicleAssociation> {

	/**
	 * Returns true if an vehicle association is found with the specified
	 * properties.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param vehicleMake vehicle make
	 * @return vehicle found or not
	 */
	VehicleAssociation find(Offender offender, DateRange dateRange,
		VehicleModel vehicleModel, VehicleColor vehicleColor, Integer year,
		VehicleMake vehiclemake);

	/**
	 * Returns true if not the excluded vehicle association.
	 * 
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param comments comments
	 * @param vehicleMake vehicle make
	 * @param offender offender
	 * @param vehicleAssociation vehicle association
	 * @return vehicle association found or not; {@code null} if no such 
	 * vehicle association exists
	 */
	
	VehicleAssociation findExcluding(VehicleModel vehicleModel, 
		VehicleColor vehicleColor, Integer year, DateRange dateRange, 
		String comments, VehicleAssociation vehicleAssociation, 
		VehicleMake vehicleMake, Offender offender); 

	/**
	 * Returns true if not the excluded vehicle association for "save" button.
	 * 
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleMake vehicle make
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param comments comments
	 * @param offender offender
	 * @return vehicle association found or not; {@code null} if no such 
	 * vehicle association exists
	 */
	
	VehicleAssociation findExcludingSave(VehicleModel vehicleModel, 
		VehicleColor vehicleColor, Integer year, DateRange dateRange, 
		String comments, VehicleMake vehicleMake, Offender offender); 	
	
	/** Returns list of vehicle association by offender.
	 * @param offender offender.
	 * @return list of vehicle association. */
	List<VehicleAssociation> findByOffender(Offender offender);
}