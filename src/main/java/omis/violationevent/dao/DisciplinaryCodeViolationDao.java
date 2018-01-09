package omis.violationevent.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * DisciplinaryCodeViolationDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeViolationDao
	extends GenericDao<DisciplinaryCodeViolation> {
	
	/**
	 * Finds and returns a DisciplinaryCodeViolation with specified properties
	 * @param disciplinaryCode - DisciplinaryCode
	 * @param violationEvent - ViolationEvent
	 * @return DisciplinaryCodeViolation with specified properties
	 */
	DisciplinaryCodeViolation find(DisciplinaryCode disciplinaryCode,
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a DisciplinaryCodeViolation with specified properties
	 * excluding specified DisciplinaryCodeViolation
	 * @param excludedDisciplinaryCodeViolation - DisciplinaryCodeViolation 
	 * to exclude
	 * @param disciplinaryCode - DisciplinaryCode
	 * @param violationEvent - ViolationEvent
	 * @return DisciplinaryCodeViolation with specified properties excluding
	 * specified DisciplinaryCodeViolation
	 */
	DisciplinaryCodeViolation findExcluding(
			DisciplinaryCodeViolation excludedDisciplinaryCodeViolation,
			DisciplinaryCode disciplinaryCode, ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of DisciplinaryCodeViolations found by 
	 * specified violationEvent
	 * @param violationEvent - ViolationEvent
	 * @return list of DisciplinaryCodeViolations found by 
	 * specified violationEvent
	 */
	List<DisciplinaryCodeViolation> findByViolationEvent(
			ViolationEvent violationEvent);
	
	/**
	 * Finds and returns a list of DisciplinaryCodeViolation with no 
	 * Infraction/resolution association by specified ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of DisciplinaryCodeViolation  with no 
	 * Infraction/resolution association by specified ViolationEvent
	 */
	List<DisciplinaryCodeViolation> findUnresolvedByViolationEvent(
			ViolationEvent violationEvent);
	
}
