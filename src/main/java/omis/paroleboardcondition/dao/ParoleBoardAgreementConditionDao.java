package omis.paroleboardcondition.dao;

import omis.condition.domain.ConditionClause;
import omis.dao.GenericDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCondition;

/**
 * Parole Board Agreement Condition Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementConditionDao
		extends GenericDao<ParoleBoardAgreementCondition> {
	
	/**
	 * Finds a Parole Board Agreement Condition by the specified Condition
	 * Clause and Category.
	 * @param conditionClause - Condition Clause
	 * @param category - Parole Board Agreement Category
	 * @return Parole Board Agreement Condition found by the specified Condition
	 * Clause and Category.
	 */
	ParoleBoardAgreementCondition find(ConditionClause conditionClause,
			ParoleBoardAgreementCategory category);
}
