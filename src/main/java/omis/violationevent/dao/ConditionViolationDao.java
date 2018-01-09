package omis.violationevent.dao;

import java.util.List;

import omis.condition.domain.Condition;
import omis.dao.GenericDao;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * ConditionViolationDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 15, 2017)
 *@since OMIS 3.0
 *
 */
public interface ConditionViolationDao extends GenericDao<ConditionViolation> {
	
	/**
	 * Finds and returns a ConditionViolation with specified properties
	 * @param condition - Condition
	 * @param violationEvent - ViolationEvent
	 * @return ConditionViolation found with specified properties
	 */
	ConditionViolation find(Condition condition, ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a ConditionViolation with specified propertied,
	 * excluding specified ConditionViolation
	 * @param condition - Condition
	 * @param violationEvent - ViolationEvent
	 * @param excludedConditionViolation - ConditionViolation to exclude
	 * @return ConditionViolation found with specified propertied,
	 * that is not specified ConditionViolation
	 */
	ConditionViolation findExcluding(Condition condition,
			ViolationEvent violationEvent,
			ConditionViolation excludedConditionViolation);
	
	/**
	 * Finds and returns a list of ConditionViolations by specified 
	 * ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of ConditionViolations by specified ViolationEvent
	 */
	List<ConditionViolation> findByViolationEvent(ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of ConditionViolations with no 
	 * Infraction/resolution association by specified ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of ConditionViolations  with no 
	 * Infraction/resolution association by specified ViolationEvent
	 */
	List<ConditionViolation> findUnresolvedByViolationEvent(
			ViolationEvent violationEvent);
	
	
}
