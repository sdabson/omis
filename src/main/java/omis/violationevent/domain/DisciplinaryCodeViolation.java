package omis.violationevent.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.disciplinaryCode.domain.DisciplinaryCode;

/**
 * Disciplinary Code Violation.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Jul 26, 2018)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeViolation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Disciplinary Code Violation..
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Disciplinary Code Violation..
	 * @param id - ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the disciplinaryCode for the Disciplinary Code Violation..
	 * @return disciplinaryCode - Disciplinary Code
	 */
	DisciplinaryCode getDisciplinaryCode();
	
	/**
	 * Sets the disciplinaryCode for the Disciplinary Code Violation..
	 * @param disciplinaryCode - Disciplinary Code
	 */
	void setDisciplinaryCode(DisciplinaryCode disciplinaryCode);
	
	/**
	 * Returns the ViolationEvent for the Disciplinary Code Violation..
	 * @return violationEvent - Violation Event
	 */
	ViolationEvent getViolationEvent();
	
	/**
	 * Sets the ViolationEvent for the Disciplinary Code Violation.
	 * @param violationEvent - Violation Event
	 */
	void setViolationEvent(ViolationEvent violationEvent);
	
	/**
	 * Returns the Details for the Disciplinary Code Violation.
	 * @return details - details
	 */
	String getDetails();
	
	/**
	 * Sets the Details for the Disciplinary Code Violation.
	 * @param details - details
	 */
	void setDetails(String details);
	
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
