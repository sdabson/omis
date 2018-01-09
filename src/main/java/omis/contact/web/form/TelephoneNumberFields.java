package omis.contact.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumberCategory;

/**
 * Telephone number fields.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 26, 2015)
 * @since OMIS 3.0
 */
public class TelephoneNumberFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long value;
	
	private Integer extension;
	
	private Boolean primary;
	
	private TelephoneNumberCategory category;
	
	private Boolean active;
	

	/**
	 * Instantiates a default instance of telephone number fields.
	 */
	public TelephoneNumberFields() {
		// Default constructor.
	}
	
	/**
	 * Telephone number fields.
	 * 
	 * @param value value 
	 * @param extension extension
	 * @param primary primary
	 * @param category category
	 */
	public TelephoneNumberFields(final Long value, final Integer extension,
			final Boolean primary, final TelephoneNumberCategory category) {
	this.value = value;
	this.extension = extension;
	this.primary = primary;
	this.category = category;
	}
	
	/**
	 * Telephone number fields.
	 * 
	 * @param value value
	 * @param extension extension
	 * @param primary primary
	 * @param category category
	 * @param active active
	 */
	public TelephoneNumberFields(final Long value, final Integer extension,
			final Boolean primary, final TelephoneNumberCategory category,
			final Boolean active) {
		this(value, extension, primary, category);
		this.active = active;
	}
	

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public Long getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final Long value) {
		this.value = value;
	}

	/**
	 * Returns the extension.
	 * 
	 * @return extension
	 */
	public Integer getExtension() {
		return this.extension;
	}

	/**
	 * Sets the extension.
	 * 
	 * @param extension extension
	 */
	public void setExtension(final Integer extension) {
		this.extension = extension;
	}

	/**
	 * Returns whether primary applies.
	 * 
	 * @return primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}

	/**
	 * Sets whether primary applies.
	 * 
	 * @param primary primary
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/**
	 * Returns the telephone number category.
	 * 
	 * @return telephone number category
	 */
	public TelephoneNumberCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the telephone number category.
	 * 
	 * @param category category
	 */
	public void setCategory(final TelephoneNumberCategory category) {
		this.category = category;
	}
	
	/**
	 * Returns the active flag.
	 * 
	 * @return active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets the active flag.
	 * 
	 * @param active active
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}
}