package omis.region.domain;

import java.io.Serializable;

import omis.country.domain.Country;

/**
 * City.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 25, 2013)
 * @since OMIS 3.0
 */
public interface City
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
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets whether the city is valid.
	 * 
	 * @param valid whether city is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the city is valid.
	 * 
	 * @return whether city is valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	void setState(State state);
	
	/**
	 * Returns the state.
	 * 
	 * @return state
	 */
	State getState();
	
	/**
	 * Sets the county.
	 * 
	 * @param county county
	 */
	void setCountry(Country country);
	
	/**
	 * Returns the county.
	 * 
	 * @return county
	 */
	Country getCountry();
	
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