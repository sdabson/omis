package omis.facility.domain;

import java.io.Serializable;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 06 2013)
 * @since OMIS 3.0
 */
public interface Bed extends Serializable {
	
	/**
	 * Return the id of the bed.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the bed.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the room of the bed.
	 * 
	 * @return the room
	 */
	Room getRoom();

	/**
	 * Set the room of the bed.
	 * 
	 * @param room the room to set
	 */
	void setRoom(Room room);
	
	/**
	 * Returns the number of the bed.
	 * 
	 * @return the number
	 */
	Integer getNumber();

	/**
	 * Sets the number of the bed.
	 * 
	 * @param number the number to set
	 */
	void setNumber(Integer number);

	/**
	 * Returns the active value.
	 * 
	 * @return active
	 */
	Boolean getActive();
	
	/**
	 * Sets the active value.
	 * 
	 * @param active active
	 */
	void setActive(Boolean active);
	
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
