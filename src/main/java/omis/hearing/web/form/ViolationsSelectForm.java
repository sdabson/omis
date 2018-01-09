package omis.hearing.web.form;

import java.util.List;

import omis.hearing.domain.Hearing;
import omis.hearing.domain.ResolutionClassificationCategory;
import omis.offender.domain.Offender;
import omis.violationevent.domain.ViolationEventCategory;

/**
 * SelectForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 20, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationsSelectForm {
	
	private List<ViolationSelectionItem> violationSelectionItems;
	
	private Offender offender;
	
	private Hearing hearing;
	
	private ResolutionClassificationCategory resolutionCategory;
	
	private ViolationEventCategory violationCategory;
	
	
	/**
	 * 
	 */
	public ViolationsSelectForm() {
	}
	
	/**
	 * Returns the violationSelectionItems
	 * @return violationSelectionItems - List<ViolationSelectionItem>
	 */
	public List<ViolationSelectionItem> getViolationSelectionItems() {
		return violationSelectionItems;
	}

	/**
	 * Sets the violationSelectionItems
	 * @param violationSelectionItems - List<ViolationSelectionItem>
	 */
	public void setViolationSelectionItems(
			final List<ViolationSelectionItem> violationSelectionItems) {
		this.violationSelectionItems = violationSelectionItems;
	}

	/**
	 * Returns the offender
	 * @return offender - Offender
	 */
	public Offender getOffender() {
		return offender;
	}

	/**
	 * Sets the offender
	 * @param offender - Offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	
	/**
	 * Returns the hearing
	 * @return hearing - Hearing
	 */
	public Hearing getHearing() {
		return hearing;
	}

	/**
	 * Sets the hearing
	 * @param hearing - Hearing
	 */
	public void setHearing(final Hearing hearing) {
		this.hearing = hearing;
	}

	/**
	 * Returns the resolutionCategory
	 * @return resolutionCategory - ResolutionClassificationCategory
	 */
	public ResolutionClassificationCategory getResolutionCategory() {
		return resolutionCategory;
	}

	/**
	 * Sets the resolutionCategory
	 * @param resolutionCategory - ResolutionClassificationCategory
	 */
	public void setResolutionCategory(
			final ResolutionClassificationCategory resolutionCategory) {
		this.resolutionCategory = resolutionCategory;
	}

	/**
	 * Returns the violationCategory
	 * @return violationCategory - ViolationEventCategory
	 */
	public ViolationEventCategory getViolationCategory() {
		return violationCategory;
	}

	/**
	 * Sets the violationCategory
	 * @param violationCategory - ViolationEventCategory
	 */
	public void setViolationCategory(
			final ViolationEventCategory violationCategory) {
		this.violationCategory = violationCategory;
	}

	
	
}
