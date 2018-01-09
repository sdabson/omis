package omis.vehicle.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary report for offender vehicle.
 * 
 * @author Yidong Li
 * @author Ryan Johns
 * @version 0.1.1 (Jun 1, 2016)
 * @since OMIS 3.0
 */
public class OffenderVehicleSummary
	implements Serializable, Comparable<OffenderVehicleSummary> {
	private static final long serialVersionUID = 1;
	private final Long vehicleAssociationId;
	private final String plateNumber;
	private final String ownerDescription;
	private final String vehicleColorName;
	private final String vehicleMakeName;
	private final String vehicleModelName;
	private final Integer year;
	private final Date startDate;
	private final Date endDate;
	private final String stateName;
	
	/**
	 * Instantiates a offender vehicle summary.
	 * 
	 * @param vehicleAssociationId ID of vehicle association
	 * @param plateNumber vehicle plate number
	 * @param ownerDescription - owner description.
	 * @param vehicleColorName vehicle color name
	 * @param vehicleMakeName vehicle make name
	 * @param vehicleModelName vehicle model name
	 * @param year year
	 * @param startDate start date
	 * @param endDate end date
	 * @param String stateName
	 */
	public OffenderVehicleSummary(
			final Long vehicleAssociationId,
			final String plateNumber,
			final String ownerDescription,
			final String vehicleColorName,
			final String vehicleMakeName,
			final String vehicleModelName,
			final Integer year,
			final Date startDate,
			final Date endDate,
			final String stateName
			) {
		this.vehicleAssociationId = vehicleAssociationId;
		this.plateNumber = plateNumber;
		this.ownerDescription = ownerDescription;
		this.vehicleColorName = vehicleColorName;
		this.vehicleMakeName = vehicleMakeName;
		this.vehicleModelName = vehicleModelName;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stateName = stateName;
	}
	
	/**
	 * Returns the ID of the vehicle association.
	 * 
	 * @return ID of vehicle association
	 */
	public Long getVehicleAssociationId() {
		return this.vehicleAssociationId;
	}
	
	/**
	 * Returns the offender vehicle plate number.
	 * 
	 * @return offender vehicle plate number
	 */
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	/**
	 * Returns the owner description.
	 * 
	 * @return owner description
	 */
	public String getOwnerDescription() {
		return this.ownerDescription;
	}
	
	/**
	 * Returns the vehicle color name.
	 * 
	 * @return vehicle color name
	 */
	public String getVehicleColorName() {
		return this.vehicleColorName;
	}
	
	/**
	 * Returns the vehicle make name.
	 * 
	 * @return vehicle make name
	 */
	public String getVehicleMakeName() {
		return this.vehicleMakeName;
	}
	
	/**
	 * Returns the vehicle model name.
	 * 
	 * @return vehicle model name
	 */
	public String getVehicleModelName() {
		return this.vehicleModelName;
	}
	
	/**
	 * Returns the year.
	 * 
	 * @return year 
	 */
	public Integer getYear() {
		return this.year;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date 
	 */
	public Date getStartDate() {
		return this.startDate;
	} 

	/**
	 * Returns the end date.
	 * 
	 * @return end date 
	 */
	public Date getEndDate() {
		return this.endDate;
	} 

	/**
	 * Returns the state name.
	 * 
	 * @return state 
	 */
	
	public String getStateName() {
		return this.stateName;
	} 

	/** {@inheritDoc} */
	@Override
	public int compareTo(final OffenderVehicleSummary o) {
		int result = 0;

		if (this.getOwnerDescription() != null) {
			result = this.getOwnerDescription()
				.compareTo(o.getOwnerDescription());
			if (result != 0) {
				return result;
			}
		}
		if (this.getStartDate()!= null) {
			result = this.getStartDate()
				.compareTo(o.getStartDate());
			if (result != 0) {
				return result;
			}
		}
		return result;
	}
}