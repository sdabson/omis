package omis.condition.service.delegate;

import java.util.List;

import omis.condition.dao.ConditionClauseDao;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.instance.factory.InstanceFactory;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Delegate class for ConditionClauses.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.3 (May 15, 2017)
 * @since OMIS 3.0
 *
 */
public class ConditionClauseDelegate {
	
	private final ConditionClauseDao conditionClauseDao;
	
	private final InstanceFactory<ConditionClause>
		conditionClauseInstanceFactory;
	
	/**
	 * Instantiates the conditionClause with a relevant dao.
	 * @param conditionClauseDao - Condition Clause DAO
	 * @param conditionClauseInstanceFactory - Condition Clause Instance Factory
	 */
	public ConditionClauseDelegate(final ConditionClauseDao conditionClauseDao,
			final InstanceFactory<ConditionClause>
				conditionClauseInstanceFactory) {
		this.conditionClauseDao = conditionClauseDao;
		this.conditionClauseInstanceFactory = conditionClauseInstanceFactory;
	}
	
	/**
	 * Finds all Condition Clauses.
	 * @return all Condition Clauses
	 */
	public List<ConditionClause> findAllConditionClauses() {
		return this.conditionClauseDao.findAll();
	}

	/**
	 * Returns all ConditionClauses by specified ConditionGroup.
	 * @param conditionGroup - ConditionGroup
	 * @return List of ConditionClauses by specified ConditionGroup
	 */
	public List<ConditionClause> findConditionClausesByConditionGroup(
			final ConditionGroup conditionGroup) {
		return this.conditionClauseDao.findByConditionGroup(
				conditionGroup);
	}

	/**
	 * Returns all ConditionClauses by specified CourtCaseAgreementCategory.
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @return List of ConditionClauses by specified CourtCaseAgreementCategory
	 */
	public List<ConditionClause> findByCourtCaseAgreementCategory(
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		return this.conditionClauseDao.findByCourtCaseAgreementCategory(
				courtCaseAgreementCategory);
	}

	
	/**
	 * Returns a list of Condition Clauses by the specified Parole Board
	 * Agreement Category.
	 * @param category - Parole Board Agreement Category
	 * @return List of Condition Clauses by the specified Parole Board
	 * Agreement Category.
	 */
	public List<ConditionClause> findByParoleBoardAgreementCategory(
			final ParoleBoardAgreementCategory category) {
		return this.conditionClauseDao
				.findByParoleBoardAgreementCategory(category);
	}
	
	/**
	 * Returns a list of ConditionClauses with the specified
	 * CourtCaseAgreementCategory excluding those for the specified
	 * CourtCaseAgreement.
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @param courtCaseAgreement - CourtCaseAgreement to use for exclusion
	 * @return List of ConditionClauses with the specified
	 * CourtCaseAgreementCategory
	 */
	public List<ConditionClause>
		findByCourtCaseAgreementCategoryExcludingAgreement(
			final CourtCaseAgreementCategory courtCaseAgreementCategory,
			final CourtCaseAgreement courtCaseAgreement) {
		return this.conditionClauseDao
				.findByCourtCaseAgreementCategoryExcludingAgreement(
						courtCaseAgreementCategory, courtCaseAgreement);
	}
	
	/**
	 * Creates a ConditionClause (for use in unit testing).
	 * @param description - String
	 * @param conditionTitle - ConditionTitle
	 * @param valid - Boolean
	 * @return Created ConditionClause
	 */
	public ConditionClause create(final String description,
			final ConditionTitle conditionTitle, final Boolean valid) {
		ConditionClause conditionClause = this.conditionClauseInstanceFactory
				.createInstance();
		
		conditionClause.setDescription(description);
		conditionClause.setConditionTitle(conditionTitle);
		conditionClause.setValid(valid);
		
		return this.conditionClauseDao.makePersistent(conditionClause);
	}
	
	/**
	 * Returns a Condition Clause found with the specified Condition Title.
	 * @param conditionTitle - Condition Title
	 * @return conditionClause - Condition Clause found with the specified
	 * Condition Title
	 */
	public ConditionClause findByConditionTitle(
			final ConditionTitle conditionTitle) {
		return this.conditionClauseDao.findByConditionTitle(conditionTitle);
	}
	
}
