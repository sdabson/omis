package omis.offenderflag.web.form;

import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Offender flag category form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2017)
 * @since OMIS 3.0
 */
public class OffenderFlagCategoryForm {
	
	private String name;
	
	private String description;
	
	private Boolean required;
	
	private Short sortOrder;
	
	private FlagUsage usage;
	
	private OffenderFlagCategory category;

	/** Instantiates an implementation of OffenderFlagCategoryForm */
	public OffenderFlagCategoryForm() {
		// Default constructor.
	}

	/**
	 * Returns name of offender flag category.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the offender flag category.
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the offender flag category.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the offender flag category.
	 *
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the required boolean of the offender flag category.
	 *
	 * @return the required
	 */
	public Boolean getRequired() {
		return this.required;
	}

	/**
	 * Sets the required boolean of the offender flag category.
	 *
	 * @param required required
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * Returns the sort order of the offender flag category.
	 *
	 * @return the sortOrder
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * Sets the sort order of the offender flag category.
	 *
	 * @param sortOrder sortOrder
	 */
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 *
	 *
	 * @return the usage
	 */
	public FlagUsage getUsage() {
		return this.usage;
	}

	/**
	 *
	 *
	 * @param usage usage
	 */
	public void setUsage(FlagUsage usage) {
		this.usage = usage;
	}

	/**
	 * Returns the category.
	 *
	 * @return the category
	 */
	public OffenderFlagCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category category
	 */
	public void setCategory(OffenderFlagCategory category) {
		this.category = category;
	}
}