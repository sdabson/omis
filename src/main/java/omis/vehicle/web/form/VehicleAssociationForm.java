package omis.vehicle.web.form;

import java.util.Date;

import omis.region.domain.State;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/** Form object for vehicle association.
 * @author: Ryan Johns
 * @author: Yidong Li
 * @version 0.1.1 (Jun 1, 2016)
 * @since OMIS 3.0 */
public class VehicleAssociationForm {
	private Integer year;
	private VehicleModel vehicleModel;
	private VehicleColor vehicleColor;
	private VehicleMake vehicleMake;  
	private Date startDate;
	private Date endDate;
	private String comments;
	private State state;
	private String plateNumber;
	private String ownerDescription;
	
	/** Instantiates a vehicle association form. */
	public VehicleAssociationForm() {
		// Default instantiation
	}
	
	public Integer getYear() {
		return this.year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	 
	public VehicleMake getVehicleMake() {
		return this.vehicleMake;
	}
	public void setVehicleMake(VehicleMake vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public VehicleModel getVehicleModel() {
		return this.vehicleModel;
	}
	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public VehicleColor getVehicleColor() {
		return this.vehicleColor;
	}
	public void setVehicleColor(VehicleColor vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getComments() {
		return this.comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}	
	public State getState() {
		return this.state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getPlateNumber() {
		return this.plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getOwnerDescription() {
		return this.ownerDescription;
	}
	public void setOwnerDescription(final String ownerDescription) {
		this.ownerDescription = ownerDescription;
	}
}