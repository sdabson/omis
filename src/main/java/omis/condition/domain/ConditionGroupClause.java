package omis.condition.domain;

import java.io.Serializable;

/** ConditionGroupClause
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.0 (Jul 5, 2016)
 * @since OMIS 3.0 
 * */
public interface ConditionGroupClause extends Serializable{

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
	 * Gets the condition group clause.
	 * 
	 * return The condition group clause.
	 */
	public ConditionClause getConditionClause();


	/**
	 * Sets the title of the condition clause.
	 * 
	 * @param conditionClause -- The condition clause.
	 */
	public void setConditionClause(ConditionClause conditionClause);
	


	/**
	 * Gets the condition group.
	 * 
	 * return The condition group.
	 */
	public ConditionGroup getConditionGroup();


	/**
	 * Sets the Condition Group.
	 * 
	 * @param conditionGroup -- The condition group.
	 */
	public void setConditionGroup(ConditionGroup conditionGroup);
	
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
