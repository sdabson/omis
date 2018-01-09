package omis.hearing.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.hearing.domain.HearingStatusCategory;
import omis.hearing.domain.ResolutionClassificationCategory;


/**
 * ResolutionForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2017)
 *@since OMIS 3.0
 *
 */
public class ResolutionForm {
	
	private List<ViolationItem> violationItems = new ArrayList<ViolationItem>();
	
	private HearingStatusCategory category;
	
	private Date date;
	
	private String statusDescription;
	
	private ResolutionClassificationCategory resolutionCategory;
	
	private ViolationItem violationItem;
	
	private Boolean groupEdit;
	
	/**
	 * 
	 */
	public ResolutionForm() {
	}

	/**
	 * Returns the violationItems
	 * @return violationItems - List<ViolationItem>
	 */
	public List<ViolationItem> getViolationItems() {
		return violationItems;
	}

	/**
	 * Sets the violationItems
	 * @param violationItems - List<ViolationItem>
	 */
	public void setViolationItems(final List<ViolationItem> violationItems) {
		this.violationItems = violationItems;
	}

	/**
	 * Returns the category
	 * @return category - HearingStatusCategory
	 */
	public HearingStatusCategory getCategory() {
		return category;
	}

	/**
	 * Sets the category
	 * @param category - HearingStatusCategory
	 */
	public void setCategory(final HearingStatusCategory category) {
		this.category = category;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the statusDescription
	 * @return statusDescription - String
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * Sets the statusDescription
	 * @param statusDescription - String
	 */
	public void setStatusDescription(final String statusDescription) {
		this.statusDescription = statusDescription;
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
	 * Returns the violationItem
	 * @return violationItem - ViolationItem
	 */
	public ViolationItem getViolationItem() {
		return violationItem;
	}

	/**
	 * Sets the violationItem
	 * @param violationItem - ViolationItem
	 */
	public void setViolationItem(final ViolationItem violationItem) {
		this.violationItem = violationItem;
	}

	/**
	 * Returns the groupEdit
	 * @return groupEdit - Boolean
	 */
	public Boolean getGroupEdit() {
		return groupEdit;
	}

	/**
	 * Sets the groupEdit
	 * @param groupEdit - Boolean
	 */
	public void setGroupEdit(final Boolean groupEdit) {
		this.groupEdit = groupEdit;
	}
	
	
	
}
