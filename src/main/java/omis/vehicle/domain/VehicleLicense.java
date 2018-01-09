package omis.vehicle.domain;

import omis.region.domain.State;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Vehicle License
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Jul 24, 2014)
 * @since: OMIS 3.0
 */
public interface VehicleLicense extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the state.
	 * 
	 * @return state state
	 */
	State getState();
	
	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	void setState(State state);
	
	/**
	 * Returns the plateNumber.
	 * 
	 * @return plateNumber plate number
	 */
	String getPlateNumber();
	
	/**
	 * Sets the plateNumber.
	 * 
	 * @param plateNumber plate number
	 */
	void setPlateNumber(String plateNumber);
	
	/**
	 * Gets the Vehicle Association.
	 * 
	 * @return valid 
	 */
	public VehicleAssociation getVehicleAssociation(); 

	/**
	 * Sets the Vehicle Association.
	 * 
	 * @param vehicleAssociation vehicle association
	 */
	public void setVehicleAssociation(final VehicleAssociation vehicleAssociation); 
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}