package omis.condition.dao;

import java.util.List;

import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.GenericDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Data access object for Condition Clauses.
 * 
 * @author Jonny Santy
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public interface ConditionClauseDao 
		extends GenericDao<ConditionClause> {
	
	/**
	 * Returns a List of ConditionClauses with the specified ConditionGroup.
	 * @param conditionGroup - ConditionGroup
	 * @return List of ConditionClauses with the specified ConditionGroup
	 */
	List<ConditionClause> findByConditionGroup(ConditionGroup conditionGroup);

	/**
	 * Returns a list of ConditionClauses with the specified
	 * CourtCaseAgreementCategory.
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @return List of ConditionClauses with the specified
	 * CourtCaseAgreementCategory
	 */
	List<ConditionClause> findByCourtCaseAgreementCategory(
			CourtCaseAgreementCategory courtCaseAgreementCategory);
	
	/**
	 * Returns a list of Condition Clauses by the specified Parole Board
	 * Agreement Category.
	 * @param category - Parole Board Agreement Category
	 * @return List of Condition Clauses by the specified Parole Board
	 * Agreement Category.
	 */
	List<ConditionClause> findByParoleBoardAgreementCategory(
			ParoleBoardAgreementCategory category);
	
	/**
	 * Returns a list of ConditionClauses with the specified
	 * CourtCaseAgreementCategory excluding those for the specified
	 * CourtCaseAgreement.
	 * @param courtCaseAgreementCategory - CourtCaseAgreementCategory
	 * @param courtCaseAgreement - CourtCaseAgreement to use for exclusion
	 * @return List of ConditionClauses with the specified
	 * CourtCaseAgreementCategory
	 */
	List<ConditionClause> findByCourtCaseAgreementCategoryExcludingAgreement(
			CourtCaseAgreementCategory courtCaseAgreementCategory,
			CourtCaseAgreement courtCaseAgreement);
	
	/**
	 * Returns a Condition Clause found with the specified Condition Title.
	 * @param conditionTitle - Condition Title
	 * @return conditionClause - Condition Clause found with the specified
	 * Condition Title
	 */
	ConditionClause findByConditionTitle(ConditionTitle conditionTitle);
}