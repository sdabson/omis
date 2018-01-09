package omis.vehicle.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleLicense;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.domain.VehicleOwnerAssociation;

/**
 * Vehicle association services.
 * 
 * @author: Yidong Li
 * @author: Ryan Johns
 * @version: 0.1.1 (Jun 1, 2016)
 * @since: OMIS 3.0
 */

public interface OffenderVehicleManager {

	/** Instantiate association between offender and vehicle.
	 * @param offender offender. 
	 * @param vehicleModel vehicle model.
	 * @param vehicleColor vehicle color.
	 * @param vehicleMake vehicle make.
	 * @param year year.
	 * @param ownerDescription ownerDescription.
	 * @param dateRange date range.
	 * @param comments comments.
	 * @param plateNumber plate number.
	 * @param state state 
	 * @return persisted vehicle association */
	VehicleAssociation associateVehicle(Offender offender, 
		VehicleModel vehicleModel, VehicleColor vehicleColor, Integer year, 
		String ownerDescription, DateRange dateRange, String comments, String plateNumber, 
		State state, VehicleMake vehicleMake)
			throws DuplicateEntityFoundException;

	
	/** Update vehicle association.
	 * @param vehicleAssociation vehicle association.  
	 * @param vehicleModel vehicle model.
	 * @param vehiclecolor vehicle color.
	 * @param year year.
	 * @param dateRange date range.
	 * @param comments comments.
	 * @param vehicleMake vehicle make.
	 * @param state state
	 * @param plateNumber plate number
	 * @param ownerDescription - owner description.
	 * @param offender offender
	 * @return persisted vehicle association */
	VehicleAssociation updateVehicle(VehicleAssociation vehicleAssociation, 
		VehicleModel vehicleModel, VehicleColor vehicleColor, Integer year, 
		DateRange dateRange, String comments, VehicleMake vehicleMake, 
		State state, String plateNumber, String ownerDescription, Offender offender)
		throws DuplicateEntityFoundException;

	/** Assign vehicle association to an owner.
	 * @param vehicleAssociation vehicle association.  
	 * @param ownerDescription - owner description. 
	 * @return persisted vehicle owner association */
	VehicleOwnerAssociation assignOwner(VehicleAssociation vehicleAssociation, 
		String ownerDescription);
	
	/** Assign vehicle license to an owner.
	 * @param vehicleAssociation vehicle association.  
	 * @param plateNumber vehicle plate number. 
	 * @param state state.
	 * @return persisted vehicle license */
	VehicleLicense assignLicense(VehicleAssociation vehicleAssociation, 
		String plateNumber, State state);

    /** Find the owner by the vehicle association.
     * @param vehicleAssociation vehicle association.  
     * @return persisted vehicle owner association */
	VehicleOwnerAssociation findOwnerByVehicleAssociation(VehicleAssociation 
		vehicleAssociation);
    
    /** Find the license by the vehicle association.
     * @param vehicleAssociation vehicle association.  
     * @return persisted vehicle license */
    VehicleLicense findLicenseByVehicleAssociation(VehicleAssociation 
    	vehicleAssociation);
    
    /** Remove vehicle license.
     * @param vehicleLicense vehicle license.  */
    void removeLicense(VehicleLicense vehicleLicense);
     
    /** Remove vehicle association.
     * @param vehicleAssociation vehicle association.  */
    void remove(VehicleAssociation vehicleAssociation);
      
    /** Remove vehicle owner.
     * @param vehicleOwnerAssociation vehicle owner association.  */
    void removeOwner(VehicleOwnerAssociation vehicleOwnerAssociation);

    /** Find vehicle color. 
     * @return persisted vehicle color list */
    List<VehicleColor> findColors(); 
       
    /** Find vehicle make. 
     * @return persisted vehicle make list*/
    List<VehicleMake> findVehicleMake(); 
       
    /** Find vehicle models based on vehicle make. 
     * @param vehicleMake vehicle make. 
     * @return persisted vehicle model list*/
    List<VehicleModel> findVehicleModelsByMake(VehicleMake vehicleMake); 
    
    /** Find all states.  
     * @return states */
    List<State> findStates( ); 
}
