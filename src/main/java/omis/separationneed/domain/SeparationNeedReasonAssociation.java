package omis.separationneed.domain;

import java.io.Serializable;

/**
 * Separation need reason association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public interface SeparationNeedReasonAssociation extends Serializable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	public void setId(Long id);
	
	/**
	 * Returns the separation need.
	 * 
	 * @return separation need
	 */
	public SeparationNeed getSeparationNeed();
	
	/**
	 * Sets the separation need.
	 * 
	 * @param separationNeed separation need
	 */
	public void setSeparationNeed(SeparationNeed separationNeed);
	
	/**
	 * Returns the separation need reason.
	 * 
	 * @return separation need reason
	 */
	public SeparationNeedReason getReason();
	
	/**
	 * Sets the separation need reason.
	 * 
	 * @param reason separation need reason
	 */
	public void setReason(SeparationNeedReason reason);
	
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