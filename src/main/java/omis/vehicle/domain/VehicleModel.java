package omis.vehicle.domain;

import java.io.Serializable;

/**
 * Vehicle Model.
 * 
 * @author: Ryan Johns
 * @author: Yidong Li
 * @version: 0.1.0 (Jul 22, 2014)
 * @since: OMIS 3.0
 */
public interface VehicleModel extends Serializable {
	
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
	 * Returns the vehicle model name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the vehicle model name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the status of valid.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the status of valid.
	 * 
	 * @param valid status of valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns the vehicle make.
	 * 
	 * @return vehicle make
	 */
	VehicleMake getVehicleMake();
	
	/**
	 * Sets the vehicle make.
	 * 
	 * @param vehicleMake vehicle make
	 */
	void setVehicleMake(VehicleMake vehicleMake);

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