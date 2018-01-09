/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.paroleboardcondition.web.form;

import java.util.List;

import omis.condition.domain.ConditionCategory;

/**
 * Condition Select Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementConditionSelectForm {
	
	private List<ConditionItem> conditionItems;
	
	private ConditionCategory conditionCategory;
	
	/**
	 * 
	 */
	public ParoleBoardAgreementConditionSelectForm() {
	}

	/**
	 * Returns the Condition Items.
	 * @return conditionItems - List of ConditionItems
	 */
	public List<ConditionItem> getConditionItems() {
		return this.conditionItems;
	}

	/**
	 * Sets the Condition Items.
	 * @param conditionItems - List of ConditionItems
	 */
	public void setConditionItems(final List<ConditionItem> conditionItems) {
		this.conditionItems = conditionItems;
	}

	/**
	 * Returns the Condition Category.
	 * @return conditionCategory - ConditionCategory
	 */
	public ConditionCategory getConditionCategory() {
		return this.conditionCategory;
	}

	/**
	 * Sets the Condition Category.
	 * @param conditionCategory - ConditionCategory
	 */
	public void setConditionCategory(
			final ConditionCategory conditionCategory) {
		this.conditionCategory = conditionCategory;
	}
	
	
	
}
