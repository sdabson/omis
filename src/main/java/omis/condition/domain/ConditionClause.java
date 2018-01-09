package omis.condition.domain;

import java.io.Serializable;

/** Condition Clause
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.0 (Jul 5, 2016)
 * @since OMIS 3.0 
 * */
public interface ConditionClause extends Serializable{

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
	 * Gets the description of the condition clause.
	 * 
	 * @return The description of the condition clause.
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the condition clause.
	 * 
	 * @param description --  the description of the condition clause.
	 */
	public void setDescription(String description);

	
	/**
	 * Validity of the condition clause.
	 * 
	 * @return The validity of the condition clause.
	 */
	public Boolean isValid();
	
	/**
	 * Sets the description of the condition clause.
	 * 
	 * @param description --  the description of the condition clause.
	 */
	public void setValid(Boolean valid);
	
	/**
	 * Sets the title of the condition clause.
	 * 
	 * @param title --  the title of the condition clause.
	 */
	public void setConditionTitle(ConditionTitle conditionTitle);
	
	/**
	 * Gets the title of the condition clause.
	 * 
	 * @param title --  the title of the condition clause.
	 */
	public ConditionTitle getConditionTitle();
	

	
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
