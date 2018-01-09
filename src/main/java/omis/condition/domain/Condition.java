package omis.condition.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Condition
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0 
 * */
public interface Condition extends Creatable, Updatable{

	/**
	 * Sets the ID of the Condition
	 * @return id - Long
	 */
	public Long getId();
	
	/**
	 * Returns the ID of the Condition
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Gets the clause of the Condition.
	 * 
	 * @return The clause of the Condition.
	 */
	public String getClause();

	/**
	 * Sets the clause of the Condition.
	 * 
	 * @param clause -- The clause of the Condition.
	 */
	public void setClause(String clause);
	
	/**
	 * Gets the condition clause of the condition.
	 * 
	 * @return The condition clause of the condition.
	 */
	public ConditionClause getConditionClause();
	
	/**
	 * sets the condition clause of the condition.
	 * 
	 * @param conditionClause The condition clause of the condition.
	 */
	public void setConditionClause(ConditionClause conditionClause);
	
	/**
	 * Gets the agreement of the Condition.
	 * 
	 * @return The agreement of the Condition.
	 */
	public Agreement getAgreement();
	
	/**
	 * sets the Agreement of the Condition.
	 * 
	 * @param agreement The Agreement of the Condition.
	 */
	public void setAgreement(Agreement agreement);
	
	/**
	 * Returns the Category of the Condition
	 * @return category - ConditionCategory
	 */
	public ConditionCategory getCategory();
	
	/**
	 * Sets the Category of the Condition
	 * @param category - ConditionCategory
	 */
	public void setCategory(ConditionCategory category);
	
	/**
	 * Returns the Waived of the Condition
	 * @return waived - Boolean
	 */
	public Boolean getWaived();
	
	/**
	 * Sets the Waived of the Condition
	 * @param waived - Boolean
	 */
	public void setWaived(Boolean waived);
	
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
