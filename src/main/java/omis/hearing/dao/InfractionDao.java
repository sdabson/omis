package omis.hearing.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.Infraction;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * InfractionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface InfractionDao extends GenericDao<Infraction> {
	
	/**
	 * Returns an Infraction found with specified properties
	 * @param hearing - Hearing
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @return Infraction found with specified properties
	 */
	public Infraction find(Hearing hearing,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation);
	
	/**
	 * Returns an Infraction found with specified properties excluding
	 * specified Infraction
	 * @param hearing - Hearing
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param infractionExcluded - Infraction to exclude from search
	 * @return Infraction found with specified properties excluding specified
	 * Infraction
	 */
	public Infraction findExcluding(Hearing hearing,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation,
			Infraction infractionExcluded);
	
	/**
	 * Returns a list of Infractions by specified Hearing
	 * @param hearing - Hearing
	 * @return List of Infractions by specified Hearing
	 */
	public List<Infraction> findByHearing(Hearing hearing);
	
}
