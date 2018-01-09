package omis.facility.domain;

import java.io.Serializable;

/**
 * Section.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface Section extends Serializable {

	/**
	 * Returns the id of the section.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the section.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Returns the name of the section.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name of the section.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);

	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	Facility getFacility();
	
	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);
	
	/**
	 * Returns the complex.
	 * 
	 * @return complex
	 */
	Complex getComplex();
	
	/**
	 * Sets the complex.
	 * 
	 * @param complex complex
	 */
	void setComplex(Complex complex);
	
	/**
	 * Returns the unit.
	 * 
	 * @return unit
	 */
	Unit getUnit();
	
	/**
	 * Sets the unit.
	 * 
	 * @param unit unit
	 */
	void setUnit(Unit unit);
	
	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	Level getLevel();
	
	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	void setLevel(Level level);
	
	/**
	 * Returns the abbreviation.
	 * 
	 * @return abbreviation
	 */
	String getAbbreviation();
	
	/**
	 * Sets the abbreviation.
	 * 
	 * @param abbreviation abbreviation
	 */
	void setAbbreviation(String abbreviation);
	
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
