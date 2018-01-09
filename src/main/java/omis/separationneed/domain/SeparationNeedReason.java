/**
 * 
 */
package omis.separationneed.domain;

import java.io.Serializable;

/**
 * Separation Need Reason.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public interface SeparationNeedReason extends Serializable {

	/**
	 * Return the id.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Return the valid value.
	 * 
	 * @return the valid
	 */
	Boolean getValid();

	/**
	 * Set the valid value.
	 * 
	 * @param valid the valid to set
	 */
	void setValid(Boolean valid);
	
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