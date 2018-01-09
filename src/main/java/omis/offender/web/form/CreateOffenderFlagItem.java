package omis.offender.web.form;

import java.io.Serializable;

import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Offender flag create form item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class CreateOffenderFlagItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OffenderFlagCategory category;
	
	private CreateOffenderFlagItemValue value;
	
	/** Instantiates an offender flag create form item. */
	public CreateOffenderFlagItem() {
		// Default instantiation
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
	public CreateOffenderFlagItemValue getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final CreateOffenderFlagItemValue value) {
		this.value = value;
	}
}