package omis.courtcasecondition.service;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.exception.DuplicateEntityFoundException;

/**
 * Court case agreement condition service.
 * 
 * @author Joel Norris
 * @author Annie Jacques
 * @version 0.1.0 (Oct 3, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseAgreementConditionService {

	/**
	 * Creates a condition with the specified properties.
	 * 
	 * @param agreement agreement
	 * @param clause clause
	 * @param conditionClause condition clause
	 * @param category condition category
	 * @param waived waived
	 * @return newly created condtion
	 * @throws DuplicateEntityFoundException Thrown when a condition already exists
	 * with the given clause and condition clause for the specified agreement 
	 */
	Condition createCondition(Agreement agreement, String clause,
			ConditionClause conditionClause, ConditionCategory category,
			Boolean waived) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified condition with a the specified properties.
	 * 
	 * @param condition condition to update
	 * @param clause clause
	 * @return Updated condition
	 * @throws DuplicateEntityFoundException Thrown when a condition already exists
	 * with the given clause and condition clause for the specified agreement 
	 */
	Condition updateCondition(Condition condition,
			ConditionClause conditionClause, String clause,
			ConditionCategory category, Boolean waived)
					throws DuplicateEntityFoundException;

	/**
	 * Removes the specified condition.
	 * @param condition condition to remove
	 */
	void removeCondition(Condition condition);
	
	/**
	 * Returns all condition clauses for the specified condition group.
	 * 
	 * @param conditionGroup condition group
	 * @return List of condition clauses
	 */
	List<ConditionClause>
		findConditionClausesByConditionGroup(ConditionGroup conditionGroup);
	
	/**
	 * Returns all condition clauses for the specified court case agreement category
	 * @param courtCaseAgreementCategory court case agreement category.
	 * 
	 * @return List of condition clauses
	 */
	List<ConditionClause> findConditionClausesByCourtCaseAgreementCategory(
			CourtCaseAgreementCategory courtCaseAgreementCategory);
	
	/**
	 * Returns a list of condition clauses with the specified
	 * court case agreement category excluding those for the specified
	 * court case agreement.
	 * 
	 * @param courtCaseAgreementCategory court case agreement category
	 * @param courtCaseAgreement court case agreement to use for exclusion
	 * @return List of condition clauses
	 */
	List<ConditionClause>
		findConditionClausesByCourtCaseAgreementCategoryExcludingAgreement(
			CourtCaseAgreementCategory courtCaseAgreementCategory,
			CourtCaseAgreement courtCaseAgreement);

	/**
	 * Returns a list of all condition groups.
	 * 
	 * @return List of condition groups
	 */
	List<ConditionGroup> findAllConditionGroups();
	
	/**
	 * Returns a list of waived conditions by specified agreement.
	 * 
	 * @param agreement agreement
	 * @return List of conditions
	 */
	List<Condition> findWaivedConditionsByAgreement(Agreement agreement);
	
	/**
	 * Returns condition clauses by a given court case agreement.
	 * 
	 * @param courtCaseAgreement court case agreement
	 * @return list of conditions
	 */
	List<Condition> findConditionsByAgreement(Agreement agreement);

	/**
	 * Returns a condition clause with the specified condition title.
	 * 
	 * @param conditionTitle condition title
	 * @return condition clause
	 */
	ConditionClause findConditionClauseByConditionTitle(
			ConditionTitle conditionTitle);
	
	/**
	 * Returns a list of ConditionGroups that are not being used by the specified
	 * Agreement
	 * 
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are not being used by the specified
	 * Agreement
	 */
	List<ConditionGroup> findUnusedConditionGroupsByAgreement(Agreement agreement);
	
	/**
	 * Returns a list of ConditionGroups that are being used by the specified
	 * Agreement
	 * 
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are being used by the specified
	 * Agreement
	 */
	List<ConditionGroup> findUsedConditionGroupsByAgreement(Agreement agreement);
}