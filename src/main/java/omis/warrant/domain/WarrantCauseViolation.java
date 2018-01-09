package omis.warrant.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;

/**
 * WarrantCause.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantCauseViolation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the WarrantCause
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the WarrantCause
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Warrant for the WarrantCause
	 * @return warrant - Warrant
	 */
	public Warrant getWarrant();
	
	/**
	 * Sets the Warrant for the WarrantCause
	 * @param warrant - Warrant
	 */
	public void setWarrant(Warrant warrant);
	
	/**
	 * Returns the Cause for the WarrantCause
	 * @return cause - CourtCase
	 */
	public CourtCase getCause();
	
	/**
	 * Sets the Cause for the WarrantCause
	 * @param cause - CourtCase
	 */
	public void setCause(CourtCase cause);
	
	/**
	 * Returns the Condition for the WarrantViolation
	 * @return condition - Condition
	 */
	public Condition getCondition();
	
	/**
	 * Sets the Condition for the WarrantViolation
	 * @param condition - Condition
	 */
	public void setCondition(Condition condition);
	
	/**
	 * Returns the Description for the WarrantCauseViolation
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Sets the Description for the WarrantCauseViolation
	 * @param description - String
	 */
	public void setDescription(String description);
	
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
