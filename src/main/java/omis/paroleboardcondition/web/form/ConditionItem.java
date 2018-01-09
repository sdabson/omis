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

import java.io.Serializable;

import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;

/**
 * Condition Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionItem  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String clause;
	
	private Condition condition;
	
	private ConditionClause conditionClause;
	
	private Boolean active;
	
	private ConditionCategory conditionCategory;
	
	private ParoleBoardConditionItemOperation itemOperation;
	
	/**
	 * Default Constructor for ConditionItem. 
	 */
	public ConditionItem() {
	}

	/**
	 * Returns the clause.
	 * @return clause - String
	 */
	public String getClause() {
		return this.clause;
	}

	/**
	 * Sets the clause.
	 * @param clause - String
	 */
	public void setClause(final String clause) {
		this.clause = clause;
	}

	/**
	 * Returns the condition.
	 * @return condition - Condition
	 */
	public Condition getCondition() {
		return this.condition;
	}

	/**
	 * Sets the condition.
	 * @param condition - Condition
	 */
	public void setCondition(final Condition condition) {
		this.condition = condition;
	}

	/**
	 * Returns the conditionClause.
	 * @return conditionClause - ConditionClause
	 */
	public ConditionClause getConditionClause() {
		return this.conditionClause;
	}

	/**
	 * Sets the conditionClause.
	 * @param conditionClause - ConditionClause
	 */
	public void setConditionClause(final ConditionClause conditionClause) {
		this.conditionClause = conditionClause;
	}

	/**
	 * Returns the active.
	 * @return active - Boolean
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 * @param active - Boolean
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * Returns the conditionCategory.
	 * @return conditionCategory - ConditionCategory
	 */
	public ConditionCategory getConditionCategory() {
		return this.conditionCategory;
	}

	/**
	 * Sets the conditionCategory.
	 * @param conditionCategory - ConditionCategory
	 */
	public void setConditionCategory(
			final ConditionCategory conditionCategory) {
		this.conditionCategory = conditionCategory;
	}

	/**
	 * Returns the itemOperation.
	 * @return itemOperation - ParoleBoardConditionItemOperation
	 */
	public ParoleBoardConditionItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - ParoleBoardConditionItemOperation
	 */
	public void setItemOperation(
			final ParoleBoardConditionItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
