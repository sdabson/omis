package omis.violationevent.web.form;

import omis.condition.domain.Condition;
import omis.violationevent.domain.ConditionViolation;

/**
 * ConditionViolationItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 16, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionViolationItem {
	
	private ConditionViolation conditionViolation;
	
	private Condition condition;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * Default Constructor for ConditionViolationItem
	 */
	public ConditionViolationItem() {
	}
	
	/**
	 * Returns the conditionViolation
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation() {
		return conditionViolation;
	}

	/**
	 * Sets the conditionViolation
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
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
	 * Returns the itemOperation
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(
			final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
	
}
