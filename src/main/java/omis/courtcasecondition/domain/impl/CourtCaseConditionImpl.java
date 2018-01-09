package omis.courtcasecondition.domain.impl;

import omis.condition.domain.ConditionClause;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.domain.CourtCaseCondition;

/**
 * 
 * CourtCaseConditionImpl.java
 *
 *@author unsigned
 *@author Annie Jacques 
 *@version 0.1.1 (May 22, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseConditionImpl implements CourtCaseCondition{

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_COURT_CASE_AGREEMENT_CAT_MSG =
			"Court case agreement category";

	private static final String EMPTY_CONDITION_CLAUSE_MSG =
			"Condition Clause Required";

	private Long id;
	private ConditionClause conditionClause;
	private CourtCaseAgreementCategory courtCaseAgreementCategory;

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
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
	public CourtCaseAgreementCategory getCourtCaseAgreementCategory() {
		return this.courtCaseAgreementCategory;
	}

	/** {@inheritDoc} */
	@Override
	public void setCourtCaseAgreementCategory(
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		this.courtCaseAgreementCategory = courtCaseAgreementCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof CourtCaseCondition) {
			this.checkState();
			CourtCaseCondition that 
				= (CourtCaseCondition) obj;
			if (this.getConditionClause().equals(that.getConditionClause())
					&& this.getCourtCaseAgreementCategory()
					.equals(that.getCourtCaseAgreementCategory()))
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
		hashCode = 29 * hashCode + this.getCourtCaseAgreementCategory()
			.hashCode();
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getConditionClause() == null) {
			throw new IllegalStateException(EMPTY_CONDITION_CLAUSE_MSG);
			
		}
		if (this.getCourtCaseAgreementCategory() == null) {
			throw new IllegalStateException(EMPTY_COURT_CASE_AGREEMENT_CAT_MSG);

	}
}
}
