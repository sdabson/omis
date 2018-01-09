package omis.offenderflag.web.form;

import java.io.Serializable;

import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Offender flag item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OffenderFlagCategory category;
	
	private OffenderFlagItemValue value;
	
	/** Instantiates an offender flag item. */
	public OffenderFlagItem() {
		// Default instantiation
	}
	
	/**
	 * Instantiates an offender flag item.
	 * 
	 * @param category category
	 * @param value value
	 */
	public OffenderFlagItem(final OffenderFlagCategory category,
			final OffenderFlagItemValue value) {
		this.category = category;
		this.value = value;
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public OffenderFlagCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	public void setCategory(final OffenderFlagCategory category) {
		this.category = category;
	}
	
	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public OffenderFlagItemValue getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final OffenderFlagItemValue value) {
		this.value = value;
	}
}