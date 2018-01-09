package omis.health.domain;

import java.io.Serializable;

import omis.facility.domain.Unit;

/**
 * Provider unit assignment.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface ProviderUnitAssignment 
	extends Serializable {

	/**
	 * Returns the id of the provider unit assignment.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the provider unit assignment.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the unit of the provider unit assignment.
	 * 
	 * @return unit
	 */
	Unit getUnit();
	
	/**
	 * Sets the unit of the provider unit assignment.
	 * 
	 * @param unit unit
	 */
	void setUnit(Unit unit);
	
	/**
	 * Returns the provider assignment for the provider unit assignment.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the provider assignment for the provider unit assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
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