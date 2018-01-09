package omis.violationevent.web.form;

import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * DisciplinaryCodeViolationItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 19, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeViolationItem {
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private DisciplinaryCode disciplinaryCode;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * 
	 */
	public DisciplinaryCodeViolationItem() {
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

	/**
	 * Returns the disciplinaryCode
	 * @return disciplinaryCode - DisciplinaryCode
	 */
	public DisciplinaryCode getDisciplinaryCode() {
		return disciplinaryCode;
	}

	/**
	 * Sets the disciplinaryCode
	 * @param disciplinaryCode - DisciplinaryCode
	 */
	public void setDisciplinaryCode(final DisciplinaryCode disciplinaryCode) {
		this.disciplinaryCode = disciplinaryCode;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(
			final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
}
