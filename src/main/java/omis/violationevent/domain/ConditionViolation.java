package omis.violationevent.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.condition.domain.Condition;

/**
 * ConditionViolation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 15, 2017)
 *@since OMIS 3.0
 *
 */
public interface ConditionViolation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the ConditionViolation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the ConditionViolation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Condition for the ConditionViolation
	 * @return condition - Condition
	 */
	public Condition getCondition();
	
	/**
	 * Sets the Condition for the ConditionViolation
	 * @param condition - Condition
	 */
	public void setCondition(Condition condition);
	
	/**
	 * Returns the ViolationEvent for the ConditionViolation
	 * @return violationEvent - ViolationEvent
	 */
	public ViolationEvent getViolationEvent();
	
	/**
	 * Sets the ViolationEvent for the ConditionViolation
	 * @param violationEvent - ViolationEvent
	 */
	public void setViolationEvent(ViolationEvent violationEvent);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
	
	
	
	
	
	
}
