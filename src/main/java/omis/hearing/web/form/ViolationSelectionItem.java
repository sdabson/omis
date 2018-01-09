package omis.hearing.web.form;

import omis.hearing.report.ViolationSummary;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * ViolationSummaryItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 24, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationSelectionItem {
	
	private ConditionViolation conditionViolation;
	
	private DisciplinaryCodeViolation disciplinaryCodeViolation;
	
	private Boolean selected;
	
	private ViolationSummary violationSummary;
	
	/**
	 * 
	 */
	public ViolationSelectionItem() {
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
	public void setConditionViolation(
			final ConditionViolation conditionViolation) {
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



	/**
	 * Returns the selected
	 * @return selected - Boolean
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * Sets the selected
	 * @param selected - Boolean
	 */
	public void setSelected(final Boolean selected) {
		this.selected = selected;
	}

	/**
	 * Returns the violationSummary
	 * @return violationSummary - ViolationSummary
	 */
	public ViolationSummary getViolationSummary() {
		return violationSummary;
	}

	/**
	 * Sets the violationSummary
	 * @param violationSummary - ViolationSummary
	 */
	public void setViolationSummary(final ViolationSummary violationSummary) {
		this.violationSummary = violationSummary;
	}
	
	
	
}
