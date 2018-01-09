package omis.address.web.form;

import java.io.Serializable;

import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Address fields.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 5, 2015)
 * @since OMIS 3.0
 */
public class AddressFields implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;
	
	private Country country;
	
	private State state;
	
	private City city;
	
	private ZipCode zipCode;
	
	private Boolean newCity;
	
	private String cityName;
	
	private Boolean newZipCode;
	
	private String zipCodeValue;
	
	private String zipCodeExtension;
	
	/**
	 * Instantiates a default instance of address fields.
	 */
	public AddressFields() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of address fields with the specified properties.
	 * 
	 * @param value value
	 * @param state state
	 * @param city city
	 * @param zipCode zip code
	 * @param newCity new city
	 * @param cityName city name
	 * @param newZipCode new zip code
	 * @param zipCodeValue zip code value
	 * @param zipCodeExtension zip code extension
	 */
	public AddressFields(final String value, final State state, final City city,
			final ZipCode zipCode, final Boolean newCity, final String cityName,
			final Boolean newZipCode, final String zipCodeValue,
			final String zipCodeExtension) {
		this.value = value;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.newCity = newCity;
		this.cityName = cityName;
		this.newZipCode = newZipCode;
		this.zipCodeValue = zipCodeValue;
		this.zipCodeExtension = zipCodeExtension;
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
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Returns the country.
	 * 
	 * @return country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country country
	 */
	public void setCountry(final Country country) {
		this.country = country;
	}

	/**
	 * Returns the state.
	 * 
	 * @return state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state state
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Returns the city.
	 * 
	 * @return city
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city city
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Returns the zip code.
	 * 
	 * @return zip code
	 */
	public ZipCode getZipCode() {
		return this.zipCode;
	}

	/**
	 * Sets the zip code.
	 *  
	 * @param zipCode zip code
	 */
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Returns whether new city applies.
	 * 
	 * @return new city
	 */
	public Boolean getNewCity() {
		return this.newCity;
	}

	/**
	 * Sets whether new city applies.
	 * 
	 * @param newCity new city
	 */
	public void setNewCity(final Boolean newCity) {
		this.newCity = newCity;
	}

	/**
	 * Returns the city name.
	 * 
	 * @return city name
	 */
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * Sets the city name.
	 * 
	 * @param cityName city name
	 */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Returns whether new zip code applies.
	 * 
	 * @return new zip code
	 */
	public Boolean getNewZipCode() {
		return this.newZipCode;
	}

	/**
	 * Sets whether new zip code applies.
	 * 
	 * @param newZipCode new zip code
	 */
	public void setNewZipCode(final Boolean newZipCode) {
		this.newZipCode = newZipCode;
	}

	/**
	 * Returns the zip code value.
	 * 
	 * @return zip code value
	 */
	public String getZipCodeValue() {
		return this.zipCodeValue;
	}

	/**
	 * Sets the zip code value.
	 * 
	 * @param zipCodeValue zip code value
	 */
	public void setZipCodeValue(final String zipCodeValue) {
		this.zipCodeValue = zipCodeValue;
	}

	/**
	 * Returns the zip code extension.
	 * 
	 * @return zip code extension
	 */
	public String getZipCodeExtension() {
		return this.zipCodeExtension;
	}

	/**
	 * Sets the zip code extension.
	 * 
	 * @param zipCodeExtension zip code extension
	 */
	public void setZipCodeExtension(final String zipCodeExtension) {
		this.zipCodeExtension = zipCodeExtension;
	}
}