package omis.offenderflag.report;

import java.io.Serializable;

/**
 * Offender flag category summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 31, 2017)
 * @since OMIS 3.0
 */
public class OffenderFlagCategorySummary implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private final Long offenderFlagCategoryId;
	
	private final String name;
	
	private final String description;
	
	private final Boolean required;
	
	private final Short sortOrder;
	
	public OffenderFlagCategorySummary(final Long offenderFlagCategoryId,
			final String name, final String description, final Boolean required,
			final Short sortOrder) {
		this.offenderFlagCategoryId = offenderFlagCategoryId;
		this.name = name;
		this.description = description;
		this.required = required;
		this.sortOrder = sortOrder;
	}

	/** Instantiates an implementation of OffenderFlagCategorySummary */
	public OffenderFlagCategorySummary() {
		this.offenderFlagCategoryId = null;
		this.name = null;
		this.description = null;
		this.required = null;
		this.sortOrder = null;
	}

	/**
	 * Return offender flag category ID.
	 *
	 * @return the offenderFlagCategoryId
	 */
	public Long getOffenderFlagCategoryId() {
		return this.offenderFlagCategoryId;
	}

	/**
	 * Return name of offender flag category.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the description of offender flag category.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Return whether offender flag category is required.
	 *
	 * @return the required
	 */
	public Boolean getRequired() {
		return this.required;
	}

	/**
	 * Return the sort order of the offender flag category.
	 *
	 * @return the sortOrder
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}
}