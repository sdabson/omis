package omis.address.web.form;

import java.io.Serializable;

import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Form for editing addresses.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 8, 2014)
 * @since OMIS 3.0
 */
public class AddressForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;
	
	private Country country;
	
	private State state;
	
	private City city;
	
	private ZipCode zipCode;
	
	/** Instantiates an address form. */
	public AddressForm() {
		// Default instantiation
	}

	/**
	 * Returns the value.
	 * @return value
	 */
	public String getValue() {
		return this.value;
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
	 * Returns country.
	 * 
	 * @return country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Sets country.
	 * 
	 * @param country country
	 */
	public void setCountry(final Country country) {
		this.country = country;
	}

	/**
	 * Returns State.
	 * 
	 * @return State
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets State.
	 * 
	 * @param state State
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Returns city.
	 * 
	 * @return city
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Sets city.
	 * 
	 * @param city city
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Returns the ZIP code.
	 * 
	 * @return ZIP code
	 */
	public ZipCode getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the ZIP code.
	 * 
	 * @param zipCode ZIP code
	 */
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}
}