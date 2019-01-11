package omis.violationevent.web.form;

import java.io.Serializable;

import omis.condition.domain.Condition;
import omis.violationevent.domain.ConditionViolation;

/**
 * Condition Violation Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.1 (Jul 26, 2018)
 *@since OMIS 3.0
 *
 */
public class ConditionViolationItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ConditionViolation conditionViolation;
	
	private Condition condition;
	
	private String details;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * Default Constructor for ConditionViolationItem.
	 */
	public ConditionViolationItem() {
	}
	
	/**
	 * Returns the conditionViolation.
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation() {
		return conditionViolation;
	}

	/**
	 * Sets the conditionViolation.
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
	}

	/**
	 * Returns the condition.
	 * @return condition - Condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Sets the condition.
	 * @param condition - Condition
	 */
	public void setCondition(final Condition condition) {
		this.condition = condition;
	}
	
	/**
	 * Returns the details.
	 * @return details - details
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * Sets the details.
	 * @param details the details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}

	/**
	 * Returns the itemOperation.
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(
			final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
