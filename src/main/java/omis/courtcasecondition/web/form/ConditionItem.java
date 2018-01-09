package omis.courtcasecondition.web.form;

import java.io.Serializable;

import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
/**
 * 
 * SpecialConditionItem.java
 * 
 *@author Unsigned
 *@author Annie Jacques 
 *@version 0.1.1 (May 25, 2017)
 *@since OMIS 3.0
 */
public class ConditionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String clause;
	
	private Condition condition;
	
	private ConditionClause conditionClause;
	
	private Boolean active;
	
	private ConditionCategory conditionCategory;
	
	private ConditionItemOperation operation;

	public ConditionItem(){
	}
	
	/**
	 * @param clause
	 * @param condition
	 * @param conditionClause
	 * @param waived
	 * @param conditionCategory
	 * @param operation
	 */
	public ConditionItem(final String clause, final Condition condition,
			final ConditionClause conditionClause,
			final Boolean active,
			final ConditionCategory conditionCategory,
			final ConditionItemOperation operation) {
		this.clause = clause;
		this.condition = condition;
		this.conditionClause = conditionClause;
		this.active = active;
		this.conditionCategory = conditionCategory;
		this.operation = operation;
	}

	/**
	 * Returns the customClause
	 * @return customClause - String
	 */
	public String getClause() {
		return clause;
	}

	/**
	 * Sets the customClause
	 * @param customClause - String
	 */
	public void setClause(final String customClause) {
		this.clause = customClause;
	}

	/**
	 * Returns the condition
	 * @return condition - Condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Sets the condition
	 * @param condition - Condition
	 */
	public void setCondition(final Condition condition) {
		this.condition = condition;
	}

	/**
	 * Returns the conditionClause
	 * @return conditionClause - ConditionClause
	 */
	public ConditionClause getConditionClause() {
		return conditionClause;
	}

	/**
	 * Sets the conditionClause
	 * @param conditionClause - ConditionClause
	 */
	public void setConditionClause(final ConditionClause conditionClause) {
		this.conditionClause = conditionClause;
	}
	
	/**
	 * Returns the active
	 * @return active - Boolean
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active
	 * @param active - Boolean
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * Returns the conditionCategory
	 * @return conditionCategory - ConditionCategory
	 */
	public ConditionCategory getConditionCategory() {
		return conditionCategory;
	}

	/**
	 * Sets the conditionCategory
	 * @param conditionCategory - ConditionCategory
	 */
	public void setConditionCategory(final ConditionCategory conditionCategory) {
		this.conditionCategory = conditionCategory;
	}
	
	/**
	 * Returns the operation
	 * @return operation - ConditionItemOperation
	 */
	public ConditionItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the operation
	 * @param operation - ConditionItemOperation
	 */
	public void setOperation(final ConditionItemOperation operation) {
		this.operation = operation;
	}

	
}
