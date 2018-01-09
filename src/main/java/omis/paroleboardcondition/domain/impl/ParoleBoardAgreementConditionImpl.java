package omis.paroleboardcondition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.condition.domain.ConditionClause;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCondition;

/**
 * Parole Board Agreement Condition Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementConditionImpl
	implements ParoleBoardAgreementCondition {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private ConditionClause conditionClause;
	
	private ParoleBoardAgreementCategory category;
	
	private CreationSignature creationSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public ConditionClause getConditionClause() {
		return this.conditionClause;
	}

	/**{@inheritDoc} */
	@Override
	public void setConditionClause(final ConditionClause conditionClause) {
		this.conditionClause = conditionClause;
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreementCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final ParoleBoardAgreementCategory category) {
		this.category = category;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleBoardAgreementCondition)) {
			return false;
		}
		
		ParoleBoardAgreementCondition that =
				(ParoleBoardAgreementCondition) obj;
		
		if (this.getConditionClause() == null) {
			throw new IllegalStateException("ConditionClause required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}
		
		if (!this.getConditionClause().equals(that.getConditionClause())) {
			return false;
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getConditionClause() == null) {
			throw new IllegalStateException("ConditionClause required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getConditionClause().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
}
