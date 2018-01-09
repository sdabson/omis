package omis.court.domain;

import java.io.Serializable;

import omis.region.domain.County;

/**
 * Associates a county with a judicial district.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2013)
 * @since OMIS 3.0
 */
// TODO: Rename this entity to JudicialDistrictCountyAssociation - SA
public interface JudicialDistrictCounty
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
	 * Sets the district.
	 * 
	 * @param district district
	 */
	void setDistrict(JudicialDistrict district);
	
	/**
	 * Returns the district.
	 * 
	 * @return district
	 */
	JudicialDistrict getDistrict();
	
	/**
	 * Sets the county.
	 * 
	 * @param county county
	 */
	void setCounty(County county);
	
	/**
	 * Returns the county.
	 * 
	 * @return county
	 */
	County getCounty();
	
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
