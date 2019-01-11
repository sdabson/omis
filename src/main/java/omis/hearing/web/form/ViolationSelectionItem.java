package omis.hearing.web.form;

import java.io.Serializable;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * Violation Summary Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.1 (Aug 1, 2018)
 *@since OMIS 3.0
 *
 */
public class ViolationSelectionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ConditionViolation conditionViolation;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private Boolean selected;
	
	/**
	 * Default constructor for ViolationSelectionItem.
	 */
	public ViolationSelectionItem() {
	}

	/**
	 * Returns the conditionViolation.
	 * @return conditionViolation - ConditionViolation
	 */
	public ConditionViolation getConditionViolation() {
		return conditionViolation;
	}

	/**
	 * Sets the conditionViolation.
	 * @param conditionViolation - ConditionViolation
	 */
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
		this.conditionViolation = conditionViolation;
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
	 * Returns the selected.
	 * @return selected - Boolean
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 * @param selected - Boolean
	 */
	public void setSelected(final Boolean selected) {
		this.selected = selected;
	}
}
