package omis.condition.dao;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionGroup;
import omis.dao.GenericDao;

/**
 * Data access object for Condition Groups.
 * 
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Oct 5, 2017)
 * @since OMIS 3.0
 */
public interface ConditionGroupDao 
extends GenericDao<ConditionGroup> {
	
	/**
	 * Returns a list of all ConditionGroups
	 * @return List of all ConditionGroups
	 */
	List<ConditionGroup> findAll();
	
	/**
	 * Returns a list of ConditionGroups that are not being used by the specified
	 * Agreement
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are not being used by the specified
	 * Agreement
	 */
	List<ConditionGroup> findUnusedByAgreement(Agreement agreement);
	
	/**
	 * Returns a list of ConditionGroups that are being used by the specified
	 * Agreement
	 * @param agreement - Agreement
	 * @return List of ConditionGroups that are being used by the specified
	 * Agreement
	 */
	List<ConditionGroup> findUsedByAgreement(Agreement agreement);
	
}