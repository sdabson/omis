package omis.residence.domain;

import java.io.Serializable;

import omis.location.domain.Location;

/**
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 20, 2015)
 * @since  OMIS 3.0
 */
public interface AllowedResidentialLocationRule extends Serializable {
	

	/**
	 * Returns the id of the allowed residential location rule.
	 * 
	 * @return id
	 */
	Long getId();

	/**
	 * Sets the id of the allowed residential location rule.
	 * 
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Returns the status of the allowed residence.
	 * 
	 * @return the allowed residential status
	 */
	ResidenceStatus getStatus();
	
	/**
	 * Sets the allowed residence status.
	 * 
	 * @param status status
	 */
	void setStatus(ResidenceStatus status);
	
	/**
	 * Returns the location of the allowed residence.
	 * 
	 * @return the allowed residential location
	 */
	Location getLocation();
	
	/**
	 * Sets the allowed residence location.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
