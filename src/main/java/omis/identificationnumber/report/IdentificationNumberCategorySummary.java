package omis.identificationnumber.report;

/**
 * IdentificationNumberCategorySummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategorySummary {
	
	private final Long identificationNumberCategoryId;
	
	private final String name;
	
	private final Boolean valid;

	/**
	 * @param identificationNumberCategoryId
	 * @param name
	 * @param valid
	 */
	public IdentificationNumberCategorySummary(
			final Long identificationNumberCategoryId, final String name,
			final Boolean valid) {
		this.identificationNumberCategoryId = identificationNumberCategoryId;
		this.name = name;
		this.valid = valid;
	}

	/**
	 * Returns the identificationNumberCategoryId
	 * @return identificationNumberCategoryId - Long
	 */
	public Long getIdentificationNumberCategoryId() {
		return identificationNumberCategoryId;
	}

	/**
	 * Returns the name
	 * @return name - String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the valid
	 * @return valid - Boolean
	 */
	public Boolean getValid() {
		return valid;
	}
}
