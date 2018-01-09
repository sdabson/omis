package omis.offender.report;

import java.util.Date;

/**
 * Activity Summary.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ActivitySummary implements Comparable<ActivitySummary> {
	
	private final String updateUserLastName;
	
	private final String updateUserMiddleName;
	
	private final String updateUserFirstName;
	
	private final String updateUserUsername;
	
	private final Date updateDate;
	
	private final String moduleName;
	
	private final ActivityCategory category;

	/**
	 * @param updateUserLastName - String
	 * @param updateUserMiddleName - String
	 * @param updateUserFirstName - String
	 * @param updateUserUsername - String
	 * @param updateDate - Date
	 * @param moduleName - String
	 * @param category - String
	 */
	public ActivitySummary(final String updateUserLastName,
			final String updateUserMiddleName, final String updateUserFirstName,
			final String updateUserUsername, final Date updateDate,
			final String moduleName, final String category) {
		this.updateUserLastName = updateUserLastName;
		this.updateUserMiddleName = updateUserMiddleName;
		this.updateUserFirstName = updateUserFirstName;
		this.updateUserUsername = updateUserUsername;
		this.updateDate = updateDate;
		this.moduleName = moduleName;
		this.category = ActivityCategory.valueOf(category);
	}

	/**
	 * Returns the updateUserLastName.
	 * @return updateUserLastName - String
	 */
	public String getUpdateUserLastName() {
		return updateUserLastName;
	}

	/**
	 * Returns the updateUserMiddleName.
	 * @return updateUserMiddleName - String
	 */
	public String getUpdateUserMiddleName() {
		return updateUserMiddleName;
	}

	/**
	 * Returns the updateUserFirstName.
	 * @return updateUserFirstName - String
	 */
	public String getUpdateUserFirstName() {
		return updateUserFirstName;
	}

	/**
	 * Returns the updateUserUsername.
	 * @return updateUserUsername - String
	 */
	public String getUpdateUserUsername() {
		return updateUserUsername;
	}

	/**
	 * Returns the updateDate.
	 * @return updateDate - Date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Returns the moduleName.
	 * @return moduleName - String
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Returns the category.
	 * @return category - ActivityCategory
	 */
	public ActivityCategory getCategory() {
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public int compareTo(final ActivitySummary o) {
		return o.getUpdateDate().compareTo(this.updateDate);
	}
}
