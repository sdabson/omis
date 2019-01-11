package omis.violationevent.web.form;

import java.io.Serializable;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * Disciplinary Code Violation Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.1 (Jul 26, 2018)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeViolationItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private DisciplinaryCode disciplinaryCode;
	
	private String details;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * 
	 */
	public DisciplinaryCodeViolationItem() {
	}
	
	/**
	 * Returns the disciplinaryCodeViolation.
	 * @return disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public DisciplinaryCodeViolation getDisciplinaryCodeViolation() {
		return disciplinaryCodeViolation;
	}



	/**
	 * Sets the disciplinaryCodeViolation.
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 */
	public void setDisciplinaryCodeViolation(
			final DisciplinaryCodeViolation disciplinaryCodeViolation) {
		this.disciplinaryCodeViolation = disciplinaryCodeViolation;
	}

	/**
	 * Returns the disciplinaryCode.
	 * @return disciplinaryCode - DisciplinaryCode
	 */
	public DisciplinaryCode getDisciplinaryCode() {
		return disciplinaryCode;
	}

	/**
	 * Sets the disciplinaryCode.
	 * @param disciplinaryCode - DisciplinaryCode
	 */
	public void setDisciplinaryCode(final DisciplinaryCode disciplinaryCode) {
		this.disciplinaryCode = disciplinaryCode;
	}
	
	/**
	 * Returns the details.
	 * @return details - details
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * Sets the details.
	 * @param details the details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}

	/**
	 * Returns the itemOperation.
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(
			final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
