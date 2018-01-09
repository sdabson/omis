package omis.courtcasecondition.domain;

import java.io.Serializable;

import omis.condition.domain.ConditionClause;

/** CourtCaseCondition
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 22, 2017)
 * @since OMIS 3.0 
 * */
public interface CourtCaseCondition extends Serializable{

	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @return id. */
	public void setId(Long id);
	
	/**
	 * Get the conditionClause.
	 * @return conditionClause.
	 */
	public ConditionClause getConditionClause();
	
	/**
	 * Set the conditionClause.
	 * @return conditionClause.
	 */
	public void setConditionClause(ConditionClause conditionClause);
	
	/**
	 * Get Court Case Agreement Category
	 * @return Court Case Agreement Category
	 */
	public CourtCaseAgreementCategory getCourtCaseAgreementCategory();
	
	/**
	 * Set Court Case Agreement Category
	 * @param courtCaseAgreementCategory Court Case Agreement Category
	 */
	public void setCourtCaseAgreementCategory(CourtCaseAgreementCategory 
			courtCaseAgreementCategory);
	
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