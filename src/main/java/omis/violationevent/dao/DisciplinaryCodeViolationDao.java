package omis.violationevent.dao;

import java.util.List;
import omis.dao.GenericDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * Disciplinary Code Violation Data Access Object.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Jul 26, 2018)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeViolationDao
	extends GenericDao<DisciplinaryCodeViolation> {
	
	/**
	 * Finds and returns a Disciplinary Code Violation with specified
	 * properties.
	 * @param disciplinaryCode - DisciplinaryCode
	 * @param violationEvent - Violation Event
	 * @param details - details
	 * @return DisciplinaryCodeViolation with specified properties
	 */
	DisciplinaryCodeViolation find(DisciplinaryCode disciplinaryCode,
			ViolationEvent violationEvent, String details);
	
	/**
	 * Finds and returns a Disciplinary Code Violation with specified properties
	 * excluding specified Disciplinary Code Violation.
	 * @param excludedDisciplinaryCodeViolation - Disciplinary Code Violation
	 * to exclude
	 * @param disciplinaryCode - Disciplinary Code
	 * @param violationEvent - Violation Event
	 * @param details - details
	 * @return Disciplinary Code Violation with specified properties excluding
	 * specified Disciplinary Code Violation
	 */
	DisciplinaryCodeViolation findExcluding(
			DisciplinaryCodeViolation excludedDisciplinaryCodeViolation,
			DisciplinaryCode disciplinaryCode, ViolationEvent violationEvent,
			String details);
	
	/**
	 * Finds and returns a list of Disciplinary Code Violations found by 
	 * specified violation event.
	 * @param violationEvent - Violation Event
	 * @return list of Disciplinary Code Violations found by 
	 * specified violation Event
	 */
	List<DisciplinaryCodeViolation> findByViolationEvent(
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of Disciplinary Code Violation with no 
	 * Infraction/resolution association by specified Violation Event.
	 * @param violationEvent - Violation Event
	 * @return List of Disciplinary Code Violations with no 
	 * Infraction/resolution association by specified Violation Event
	 */
	List<DisciplinaryCodeViolation> findUnresolvedByViolationEvent(
			ViolationEvent violationEvent);
	
}
