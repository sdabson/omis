package omis.address.domain.component;

import java.io.Serializable;

import omis.address.domain.AddressUnitDesignator;

/**
 * Address unit.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (May 27, 2015)
 * @since OMIS 3.0
 */
public class AddressUnit
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;

	private AddressUnitDesignator designator;

	/** Instantiates a default address unit. */
	public AddressUnit() {
		// Default instantiation
	}
	
	/**
	 * Instantiates an address unit with the specified value and address
	 * unit designator.
	 * 
	 * @param value unit value
	 * @param designator address unit designator
	 */
	public AddressUnit(final String value, 
			final AddressUnitDesignator designator) {
		this.value = value;
		this.designator = designator;
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
	 * Sets the designator.
	 * 
	 * @param designator designator
	 */
	public void setDesignator(final AddressUnitDesignator designator) {
		this.designator = designator;
	}
	
	/**
	 * Returns the designator.
	 * 
	 * @return designator
	 */
	public AddressUnitDesignator getDesignator() {
		return this.designator;
	}
}