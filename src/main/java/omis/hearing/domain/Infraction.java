package omis.hearing.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.hearing.domain.component.Resolution;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * Infraction.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface Infraction extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Infraction
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the Infraction
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the ConditionViolation for the Infraction
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation();
	
	/**
	 * Sets the ConditionViolation for the Infraction
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(ConditionViolation conditionViolation);
	
	/**
	 * Returns the DisciplinaryCodeViolation for the Infraction
	 * @return disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public DisciplinaryCodeViolation getDisciplinaryCodeViolation();
	
	/**
	 * Sets the DisciplinaryCodeViolation for the Infraction
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public void setDisciplinaryCodeViolation(
			DisciplinaryCodeViolation disciplinaryCodeViolation);
	
	/**
	 * Returns the Hearing for the Infraction
	 * @return hearing - Hearing
	 */
	public Hearing getHearing();
	
	/**
	 * Sets the Hearing for the Infraction
	 * @param hearing - Hearing
	 */
	public void setHearing(Hearing hearing);
	
	/**
	 * Returns the Resolution for the Infraction
	 * @return resolution - Resolution
	 */
	public Resolution getResolution();
	
	/**
	 * Sets the Resolution for the Infraction
	 * @param resolution - Resolution
	 */
	public void setResolution(Resolution resolution);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
