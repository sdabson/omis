package omis.facility.domain;

import java.io.Serializable;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 06 2013)
 * @since OMIS 3.0
 */
public interface RoomDetail extends Serializable {

	/**
	 * Returns the id of the room detail.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the room detail.
	 * 
	 * @param id the id to set
	 */
	void setId(final Long id);
	
	/**
	 * Return the occupancy of the room detail.
	 * 
	 * @return the occupancy
	 */
	Integer getOccupancy();

	/**
	 * Set the occupancy of the room detail.
	 * 
	 * @param occupancy the occupancy to set
	 */
	void setOccupancy(Integer occupancy);

	/**
	 * Return the permanent value of the room detail.
	 * 
	 * @return the permanent
	 */
	Boolean getPermanent();

	/**
	 * Set the permanent value of the room detail.
	 * 
	 * @param permanent the permanent to set
	 */
	void setPermanent(Boolean permanent);
		
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
}
