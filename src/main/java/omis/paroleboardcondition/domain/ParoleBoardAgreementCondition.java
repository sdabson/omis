package omis.paroleboardcondition.domain;

import omis.audit.domain.Creatable;
import omis.condition.domain.ConditionClause;

/**
 * Parole Board Agreement Condition.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementCondition extends Creatable {
	
	/**
	 * Returns the ID of the Parole Board Agreement Condition.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Parole Board Agreement Condition.
	 * @param id - Long
	 */
	void setId(Long id);
	
	/**
	 * Returns the ConditionClause for the Parole Board Agreement Condition.
	 * @return conditionClause - ConditionClause
	 */
	ConditionClause getConditionClause();
	
	/**
	 * Sets the ConditionClause for the Parole Board Agreement Condition.
	 * @param conditionClause - ConditionClause
	 */
	void setConditionClause(ConditionClause conditionClause);
	
	/**
	 * Returns the Category for the Parole Board
	 * Agreement Condition.
	 * @return paroleBoardAgreementCategory - ParoleBoardAgreementCategory
	 */
	ParoleBoardAgreementCategory getCategory();
	
	/**
	 * Sets the category for the Parole Board Agreement Condition.
	 * @param category - ParoleBoardAgreementCategory
	 */
	void setCategory(ParoleBoardAgreementCategory category);
	
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
