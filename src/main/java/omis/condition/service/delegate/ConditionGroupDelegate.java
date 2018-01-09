package omis.condition.service.delegate;

import java.util.List;

import omis.condition.dao.ConditionGroupDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionGroup;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate class for ConditionGroups
 * @author Jonny Santy
 * @version 0.1.0 (July 28, 2016)
 * @since OMIS 3.0
 *
 */
public class ConditionGroupDelegate {
	
	private final ConditionGroupDao conditionGroupDao;
	
	/**
	 * Instantiates the conditionClause with a relevant dao.
	 * @param courtCaseAgreementCategoryDao
	 * @param conditionClauseFactory
	 */
	public ConditionGroupDelegate(ConditionGroupDao 
			conditionGroupDao,
			InstanceFactory<ConditionGroup> conditionGroupInstanceFactory) {
		this.conditionGroupDao = conditionGroupDao;
	}
	
	/**
	 * Finds all Court Case Agreement Categories
	 * @return all Court Case Agreement Categories
	 */
	public List<ConditionGroup> findAll(){
		return this.conditionGroupDao.findAll();
	}
	
	/**
	 * Returns a list of ConditionGroups that are not being used by the specified
	 * Agreement
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are not being used by the specified
	 * Agreement
	 */
	public List<ConditionGroup> findUnusedByAgreement(final Agreement agreement){
		return this.conditionGroupDao.findUnusedByAgreement(agreement);
	}
	
	/**
	 * Returns a list of ConditionGroups that are being used by the specified
	 * Agreement
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are being used by the specified
	 * Agreement
	 */
	public List<ConditionGroup> findUsedByAgreement(final Agreement agreement){
		return this.conditionGroupDao.findUsedByAgreement(agreement);
	}
	
}
