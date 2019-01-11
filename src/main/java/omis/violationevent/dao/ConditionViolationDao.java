package omis.violationevent.dao;

import java.util.List;
import omis.condition.domain.Condition;
import omis.dao.GenericDao;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * Condition Violation Data Access Object.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Jul 26, 2018)
 *@since OMIS 3.0
 *
 */
public interface ConditionViolationDao extends GenericDao<ConditionViolation> {
	
	/**
	 * Finds and returns a Condition Violation with specified properties.
	 * @param condition - Condition
	 * @param violationEvent - Violation Event
	 * @param details - details
	 * @return Condition Violation found with specified properties
	 */
	ConditionViolation find(Condition condition, ViolationEvent violationEvent,
			String details);
	
	/**
	 * Finds and returns a Condition Violation with specified propertied,
	 * excluding specified Condition Violation.
	 * @param condition - Condition
	 * @param violationEvent - Violation Event
	 * @param details - details
	 * @param excludedConditionViolation - ConditionViolation to exclude
	 * @return Condition Violation found with specified propertied,
	 * that is not specified Condition Violation
	 */
	ConditionViolation findExcluding(Condition condition,
			ViolationEvent violationEvent,
			String details,
			ConditionViolation excludedConditionViolation);
	
	/**
	 * Finds and returns a list of Condition Violations by specified 
	 * Violation Event.
	 * @param violationEvent - ViolationEvent
	 * @return List of ConditionViolations by specified Violation Event
	 */
	List<ConditionViolation> findByViolationEvent(
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of Condition Violations with no 
	 * Infraction/resolution association by specified Violation Event.
	 * @param violationEvent - Violation Event
	 * @return List of ConditionViolations  with no 
	 * Infraction/resolution association by specified Violation Event
	 */
	List<ConditionViolation> findUnresolvedByViolationEvent(
			ViolationEvent violationEvent);
	
	
}
