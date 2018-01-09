package omis.education.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;

/**
 * EducationalAchievementItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EducationalAchievement achievement;
	
	private EducationalAchievementCategory category;
	
	private Date date;
	
	private String description;
	
	private ItemOperation operation;
	
	private List<EducationalAchievementCategory> achievementCategories;
	
	/**
	 * Default constructor for EducationalAchievementItem
	 */
	public EducationalAchievementItem(){
		
	}

	/**
	 * Returns the educational achievement associated with this item
	 * @return the achievement
	 */
	public EducationalAchievement getAchievement() {
		return achievement;
	}

	/**
	 * Sets the educational achievement to be associated with this item
	 * @param achievement the educationalAchievement to set
	 */
	public void setAchievement(EducationalAchievement achievement) {
		this.achievement = achievement;
	}

	/**
	 * Returns the achievement's category
	 * @return the category
	 */
	public EducationalAchievementCategory getCategory() {
		return category;
	}

	/**
	 * Sets the achievement's category
	 * @param category the category to set
	 */
	public void setCategory(EducationalAchievementCategory category) {
		this.category = category;
	}

	/**
	 * Returns the achievement's date
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the achievement's date
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the achievement's description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the achievement's description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the item's operation (enum)
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the item's operation (enum(
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns a list of achievement categories
	 * @return the achievementCategories
	 */
	public List<EducationalAchievementCategory> getAchievementCategories() {
		return achievementCategories;
	}

	/**
	 * Sets the list of achievement categories
	 * @param achievementCategories the achievementCategories to set
	 */
	public void setAchievementCategories(List<EducationalAchievementCategory> 
			achievementCategories) {
		this.achievementCategories = achievementCategories;
	}
	
	
	
}
