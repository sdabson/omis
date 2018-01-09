package omis.jail.domain;

import java.io.Serializable;

import omis.location.domain.Location;

/**
 * Jail.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public interface Jail
		extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets location.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
	/**
	 * Returns location.
	 * 
	 * @return location
	 */
	Location getLocation();
	
	/**
	 * Sets whether {@code this} is active.
	 * 
	 * @param active whether {@code this} is active
	 */
	void setActive(Boolean active);
	
	/**
	 * Returns whether {@code this} is active.
	 * 
	 * @return whether {@code this} is active
	 */
	Boolean getActive();
	
	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	void setCategory(JailCategory category);
	
	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	JailCategory getCategory();
	
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