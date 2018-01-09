package omis.contact.report.delegate;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;

/**
 * Delegate to summarize telephone number.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 19, 2015)
 * @since OMIS 3.0
 */
public class TelephoneNumberSummaryDelegate
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long value;
	
	private final Integer extension;
	
	private final Boolean primary;
	
	private final Boolean active;
	
	private final TelephoneNumberCategory category;

	/**
	 * Instantiates delegate to summarize telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	public TelephoneNumberSummaryDelegate(
			final TelephoneNumber telephoneNumber) {
		if (telephoneNumber != null) {
			this.value = telephoneNumber.getValue();
			this.extension = telephoneNumber.getExtension();
			this.primary = telephoneNumber.getPrimary();
			this.active = telephoneNumber.getActive();
			this.category = telephoneNumber.getCategory();
		} else {
			this.value = null;
			this.extension = null;
			this.primary = null;
			this.active = null;
			this.category = null;
		}
	}

	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public Long getValue() {
		return this.value;
	}

	/**
	 * Returns extension.
	 * 
	 * @return extension
	 */
	public Integer getExtension() {
		return this.extension;
	}

	/**
	 * Returns whether primary.
	 * 
	 * @return whether primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}

	/**
	 * Returns whether active.
	 * 
	 * @return whether active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public TelephoneNumberCategory getCategory() {
		return category;
	}
}