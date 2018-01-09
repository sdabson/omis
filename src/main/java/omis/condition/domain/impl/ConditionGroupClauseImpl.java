package omis.condition.domain.impl;

import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionGroupClause;

/**
 * Condition Group Clause Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.0 (Jul 6, 2016)
 * @since OMIS 3.0
 */
public class ConditionGroupClauseImpl implements ConditionGroupClause{

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_CONDITION_CLAUSE_MSG = "Condition Clause"
			+ "Required.";
	private static final String EMPTY_CONDITION_GROUP_MSG = "Condition Group"
			+ "Required";

	private Long id;
	private ConditionClause conditionClause;
	private ConditionGroup conditionGroup;
	

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id =id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public ConditionClause getConditionClause() {
		return this.conditionClause;
	}

	/** {@inheritDoc} */
	@Override
	public void setConditionClause(final ConditionClause conditionClause) {
		this.conditionClause = conditionClause;
	}

	/** {@inheritDoc} */
	@Override
	public ConditionGroup getConditionGroup() {
		return this.conditionGroup;
	}

	/** {@inheritDoc} */
	@Override
	public void setConditionGroup(final ConditionGroup conditionGroup) {
		this.conditionGroup = conditionGroup;
	}


	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof ConditionGroupClause) {
			this.checkState();
			ConditionGroupClause that 
				= (ConditionGroupClause) obj;
			if (this.getConditionClause().equals(that.getConditionClause())
					&&this.getConditionGroup().equals(that.getConditionGroup()))
			{
				result = true;
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getConditionClause().hashCode();
		hashCode = 29 * hashCode + this.getConditionGroup().hashCode();
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getConditionClause() == null) {
			throw new IllegalStateException(EMPTY_CONDITION_CLAUSE_MSG);
		}
		if (this.getConditionGroup() == null) {
			throw new IllegalStateException(EMPTY_CONDITION_GROUP_MSG);
		}
	}

	
}
