package omis.courtcasecondition.web.form;

import java.util.List;

import omis.condition.domain.ConditionCategory;

/**
 * ConditionSelectForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 5, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionSelectForm {
	
	private List<ConditionItem> conditionItems;
	
	private ConditionCategory conditionCategory;
	
	/**
	 * Default Constructor for ConditionSelectForm
	 */
	public ConditionSelectForm() {
	}

	/**
	 * Returns the conditionItems
	 * @return conditionItems - List of Condition Items
	 */
	public List<ConditionItem> getConditionItems() {
		return conditionItems;
	}

	/**
	 * Sets the conditionItems
	 * @param conditionItems - List of Condition Items
	 */
	public void setConditionItems(final List<ConditionItem> conditionItems) {
		this.conditionItems = conditionItems;
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

	
	
}
