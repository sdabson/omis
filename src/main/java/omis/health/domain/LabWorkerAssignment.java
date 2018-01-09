package omis.health.domain;

import java.io.Serializable;

/**
 * Lab Worker Assignment.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkerAssignment 
	extends Serializable {

	/**
	 * Returns the ID of the lab worker assignment.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the lab worker assignment.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the provider assignment of the lab worker assignment.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the provider assignment of the lab worker assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
	/**
	 * Returns the lab of the lab worker assignment.
	 * 
	 * @return lab
	 */
	Lab getLab();
	
	/**
	 * Sets the lab of the lab worker assignment.
	 * 
	 * @param lab lab
	 */
	void setLab(Lab lab);
	
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