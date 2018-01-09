package omis.condition.domain.impl;

import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionTitle;

/**
 * Condition Clause Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.0 (Aug 29, 2017)
 * @since OMIS 3.0
 */
public class ConditionClauseImpl implements ConditionClause{
	
	private static final long serialVersionUID = 1L;
	private static final String EMPTY_DESCRIPTION_MSG = "Description Required";
	private static final String EMPTY_CONDITION_TITLE_MSG = "Condition Title "
			+ "Required";
	private static final String EMPTY_VALID_MSG = "Valid Required";
	private Long id;
	private String description;
	private Boolean valid;
	private ConditionTitle conditionTitle;
	

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean isValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setConditionTitle(final ConditionTitle conditionTitle) {
		this.conditionTitle = conditionTitle;
	}

	/** {@inheritDoc} */
	@Override
	public ConditionTitle getConditionTitle() {
		return this.conditionTitle;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof ConditionClause) {
			this.checkState();
			ConditionClause that 
				= (ConditionClause) obj;
			if (this.getConditionTitle().equals(that.getConditionTitle())){
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

		hashCode = 29 * hashCode + this.getConditionTitle().hashCode();
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {
		if (this.getConditionTitle() == null) {
			throw new IllegalStateException(EMPTY_CONDITION_TITLE_MSG);
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException(EMPTY_DESCRIPTION_MSG);
		}
		if (this.isValid() == null) {
			throw new IllegalStateException(EMPTY_VALID_MSG);
		}
	}
}
