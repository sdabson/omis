package omis.vehicle.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Vehicle Owner Association
 * @author: Yidong Li
 * @author: Ryan Johns
 * @version: 0.1.1 (Jun 1, 2016)
 * @since: OMIS 3.0
 */
public interface VehicleOwnerAssociation extends Creatable, Updatable {

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
	 * Returns the owner description.
	 * 
	 * @return ownerDescription - owner description.
	 */
	String getOwnerDescription();
	
	/**
	 * Sets the owner description.
	 * 
	 * @param ownerDescription - owner description
	 */
	void setOwnerDescription(String ownerDescription);
	
	/**
	 * Gets the Vehicle Association.
	 * 
	 * @return vehicleAssociation vehicle association 
	 */
	VehicleAssociation getVehicleAssociation(); 
	
	/**
	 * Sets the Vehicle Association.
	 * 
	 * @param vehicleAssociation vehicle association
	 */
	void setVehicleAssociation(VehicleAssociation vehicleAssociation); 

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