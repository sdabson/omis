package omis.employment.domain;

import java.io.Serializable;

import omis.location.domain.Location;

/**
 * Employer
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Feb 11, 2015)
 * @since: OMIS 3.0
 */
public interface Employer extends Serializable{
	/**
	 * Returns the employer id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets the employer id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the employer location.
	 * 
	 * @param location employer location
	 */
	void setLocation(Location location);
	
	/**
	 * Gets the employer location.
	 * 
	 * @returns employer location
	 */
	Location getLocation();
	
	/**
	 * Sets the telephone number.
	 * 
	 * @param telephone number
	 */
	void setTelephoneNumber(Long telephoneNumber);
	
	/**
	 * Gets the telephone number.
	 * 
	 * @returns telephone number
	 */
	Long getTelephoneNumber();
	
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