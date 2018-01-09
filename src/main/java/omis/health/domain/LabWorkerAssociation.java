package omis.health.domain;

import java.io.Serializable;

/**
 * Lab Worker Association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkerAssociation 
	extends Serializable {

	/**
	 * Returns the ID of the lab worker association.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the lab worker association.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the lab worker assignment of the lab worker association.
	 * 
	 * @return assignment
	 */
	LabWorkerAssignment getAssignment();
	
	/**
	 * Sets the lab worker assignment of the lab worker association.
	 * 
	 * @param assignment lab worker assignment
	 */
	void setAssignment(LabWorkerAssignment assignment);
	
	/**
	 * Returns the lab work of the lab worker association.
	 * 
	 * @return lab work
	 */
	LabWork getLabWork();
	
	/**
	 * Sets the lab work of the lab worker association.
	 * 
	 * @param labWork lab work
	 */
	void setLabWork(LabWork labWork);
	
	/**
	 * Returns the primary status.
	 * 
	 * @return primary
	 */
	Boolean getPrimary();
	
	/**
	 * Sets the primary status.
	 * 
	 * @param primary primary
	 */
	void setPrimary(Boolean primary);
	
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