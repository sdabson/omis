package omis.hearing.web.form;

import omis.hearing.domain.Infraction;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * InfractionItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class InfractionItem {
	
	private Infraction infraction;
	
	private ConditionViolation conditionViolation;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	/**
	 * 
	 */
	public InfractionItem() {
	}

	/**
	 * Returns the infraction
	 * @return infraction - Infraction
	 */
	public Infraction getInfraction() {
		return infraction;
	}

	/**
	 * Sets the infraction
	 * @param infraction - Infraction
	 */
	public void setInfraction(final Infraction infraction) {
		this.infraction = infraction;
	}

	/**
	 * Returns the conditionViolation
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation() {
		return conditionViolation;
	}

	/**
	 * Sets the conditionViolation
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
	}

	/**
	 * Returns the disciplinaryCodeViolation
	 * @return disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public DisciplinaryCodeViolation getDisciplinaryCodeViolation() {
		return disciplinaryCodeViolation;
	}

	/**
	 * Sets the disciplinaryCodeViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public void setDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
	}
	
	
	
}
