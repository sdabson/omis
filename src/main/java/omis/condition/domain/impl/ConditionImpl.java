package omis.condition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;

/** Agreement
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0 
 * */
public class ConditionImpl implements Condition{
	
	private static final long serialVersionUID = 1L;

	private static final String EMPTY_CONDITION_CLAUSE_MSG =
			"Condition Clause Required";

	private static final String EMPTY_CLAUSE_MSG = "Clause Required";

	private static final String EMPTY_AGREEMENT_MSG = "Agreement Required";

	private static final String EMPTY_WAIVED_MSG = "Waived Required";

	private static final String EMPTY_CATEGORY_MSG = "Category Required";
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private String clause;
	
	private ConditionClause conditionClause;
	
	private Agreement agreement;
	
	private ConditionCategory category;
	
	private Boolean waived;
	
	/** {@inheritDoc} */
	@Override
	public ConditionCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ConditionCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature=creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public String getClause() {
		return this.clause;
	}

	/** {@inheritDoc} */
	@Override
	public void setClause(final String clause) {
		this.clause = clause;
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
	public Agreement getAgreement() {
		return this.agreement;
	}

	/** {@inheritDoc} */
	@Override
	public void setAgreement(final Agreement agreement) {
		this.agreement = agreement;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}


	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof Condition) {
			this.checkState();
			Condition that 
				= (Condition) obj;
			if (this.getConditionClause().equals(that.getConditionClause())
					&& this.getClause().equals(that.getClause())
					&& this.getAgreement().equals(that.getAgreement()))
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
		hashCode = 29 * hashCode + this.getClause().hashCode();
		hashCode = 29 * hashCode + this.getAgreement().hashCode();
		
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getConditionClause() == null) {
			throw new IllegalStateException(EMPTY_CONDITION_CLAUSE_MSG);
		}
		if (this.getClause() == null) {
			throw new IllegalStateException(EMPTY_CLAUSE_MSG);
		}
		if (this.getAgreement() == null) {
			throw new IllegalStateException(EMPTY_AGREEMENT_MSG);
		}
		if (this.getWaived() == null) {
			throw new IllegalStateException(EMPTY_WAIVED_MSG);
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException(EMPTY_CATEGORY_MSG);
		}
	}

	@Override
	public Boolean getWaived() {
		return this.waived;
	}

	@Override
	public void setWaived(Boolean waived) {	
		this.waived = waived;
	}
}
