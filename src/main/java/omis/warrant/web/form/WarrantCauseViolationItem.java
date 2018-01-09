package omis.warrant.web.form;

import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.warrant.domain.WarrantCauseViolation;

/**
 * WarrantCauseViolationItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCauseViolationItem {
	
	private WarrantCauseViolation warrantCauseViolation;
	
	private CourtCase courtCase;
	
	private Condition condition;
	
	private String description;
	
	private WarrantItemOperation itemOperation;
	
	/**
	 * 
	 */
	public WarrantCauseViolationItem() {
	}

	/**
	 * Returns the warrantCauseViolation
	 * @return warrantCauseViolation - WarrantCauseViolation
	 */
	public WarrantCauseViolation getWarrantCauseViolation() {
		return warrantCauseViolation;
	}

	/**
	 * Sets the warrantCauseViolation
	 * @param warrantCauseViolation - WarrantCauseViolation
	 */
	public void setWarrantCauseViolation(
			final WarrantCauseViolation warrantCauseViolation) {
		this.warrantCauseViolation = warrantCauseViolation;
	}

	/**
	 * Returns the courtCase
	 * @return courtCase - CourtCase
	 */
	public CourtCase getCourtCase() {
		return courtCase;
	}

	/**
	 * Sets the courtCase
	 * @param courtCase - CourtCase
	 */
	public void setCourtCase(final CourtCase courtCase) {
		this.courtCase = courtCase;
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
	 * Returns the description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - WarrantItemOperation
	 */
	public WarrantItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - WarrantItemOperation
	 */
	public void setItemOperation(final WarrantItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
}
