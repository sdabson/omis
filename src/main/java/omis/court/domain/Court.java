package omis.court.domain;

import java.io.Serializable;

import omis.location.domain.Location;

/**
 * Court.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Oct 30, 2014)
 * @since OMIS 3.0
 */
public interface Court
		extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Sets the physical location of the court.
	 * 
	 * @param location physical location of court
	 */
	void setLocation(Location location);
	
	/**
	 * Returns the physical location of the court.
	 * 
	 * @return physical location of court
	 */
	Location getLocation();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(CourtCategory category);
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	CourtCategory getCategory();
	
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