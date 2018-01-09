package omis.violationevent.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.disciplinaryCode.domain.DisciplinaryCode;

/**
 * DisciplinaryCodeViolation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeViolation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the DisciplinaryCodeViolation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the DisciplinaryCodeViolation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the disciplinaryCode for the DisciplinaryCodeViolation
	 * @return disciplinaryCode - DisciplinaryCode
	 */
	public DisciplinaryCode getDisciplinaryCode();
	
	/**
	 * Sets the disciplinaryCode for the DisciplinaryCodeViolation
	 * @param disciplinaryCode - DisciplinaryCode
	 */
	public void setDisciplinaryCode(DisciplinaryCode disciplinaryCode);
	
	/**
	 * Returns the ViolationEvent for the DisciplinaryCodeViolation
	 * @return ViolationEvent - ViolationEvent
	 */
	public ViolationEvent getViolationEvent();
	
	/**
	 * Sets the ViolationEvent for the DisciplinaryCodeViolation
	 * @param ViolationEvent - ViolationEvent
	 */
	public void setViolationEvent(ViolationEvent violationEvent);
	
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
