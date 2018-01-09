package omis.condition.domain;

import java.io.Serializable;

/** ConditionClause
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (Sep 26, 2017)
 * @since OMIS 3.0 
 * */
public interface ConditionGroup extends Serializable{

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
	 * Gets the name of the Condition Group.
	 * @return The name of the Condition Group.
	 */
	public String getName();
	
	
	/**
	 * Sets the name of the Condition Group.
	 * @param The name of the Condition Group.
	 */
	public void setName(String name);

	
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
