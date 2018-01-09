package omis.contact.domain.component;

import java.io.Serializable;

import omis.address.domain.ZipCode;

/**
 * Po box.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Jan 4, 2016)
 * @since OMIS 3.0
 */
public class PoBox
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;

	private ZipCode zipCode;

	/** Instantiates a default po box. */
	public PoBox() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a po box with the specified value and zip code
	 * 
	 * @param value unit value
	 * @param zipCode zip code
	 */
	public PoBox(final String value, final ZipCode zipCode) {
		this.value = value;
		this.zipCode = zipCode;
	}
	
	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the zip code.
	 * 
	 * @param zipCode zipCode
	 */
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * Returns the zipCode.
	 * 
	 * @return zip code
	 */
	public ZipCode getZipCode() {
		return this.zipCode;
	}
}