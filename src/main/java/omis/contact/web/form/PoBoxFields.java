package omis.contact.web.form;

import java.io.Serializable;

import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Po Box fields.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 21, 2015)
 * @since OMIS 3.0
 */
public class PoBoxFields implements Serializable {
	private static final long serialVersionUID = 1L;
	private Country country;
	private State state;
	private City city;
	private Boolean newCity;
	private String cityName;
	private ZipCode zipCode;
	private String poBoxValue;
	private Boolean newZipCode;
	private String zipCodeValue;
	private String zipCodeExtension;
	private Boolean hasStates;
	
	/**
	 * Instantiates a default instance of address fields.
	 */
	public PoBoxFields() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of po box fields with the specified properties.
	 * 
	 * @param country country
	 * @param state state
	 * @param city city
	 * @param zipCode zip code
	 * @param poBoxValue po box value
	 */
	public PoBoxFields(final Country country, final State state, final City city, 
		final ZipCode zipCode, final String poBoxValue) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.poBoxValue = poBoxValue;
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
	 * Returns state.
	 * 
	 * @return state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Sets state.
	 * 
	 * @param state state
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
	 * Returns city exists or not.
	 * 
	 * @return city exists or not
	 */
	public Boolean getNewCity() {
		return this.newCity;
	}

	/**
	 * Sets city exists or not.
	 * 
	 * @param city exists or not
	 */
	public void setNewCity(final Boolean newCity) {
		this.newCity = newCity;
	}
	
	/**
	 * Returns city name.
	 * 
	 * @return city name
	 */
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * Sets city name.
	 * 
	 * @param city name
	 */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	
	/**
	 * Returns zip code.
	 * 
	 * @return zipCode zip code
	 */
	public ZipCode getZipCode() {
		return this.zipCode;
	}

	/**
	 * Sets zip code.
	 * 
	 * @param zipCode zip code
	 */
	public void setZipCode(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Returns po box value.
	 * 
	 * @return po box value
	 */
	public String getPoBoxValue() {
		return this.poBoxValue;
	}

	/**
	 * Sets po box value.
	 * 
	 * @param poBoxValue po box value
	 */
	public void setPoBoxValue(final String poBoxValue) {
		this.poBoxValue = poBoxValue;
	}
	
	/**
	 * Returns new zip code
	 * 
	 * @return newZipCode
	 */
	public Boolean getNewZipCode() {
		return this.newZipCode;
	}

	/**
	 * Sets new zip code.
	 * 
	 * @param newZipCode new zip code
	 */
	public void setNewZipCode(final Boolean newZipCode) {
		this.newZipCode = newZipCode;
	}
	
	/**
	 * Returns zip code value
	 * 
	 * @return zip code value
	 */
	public String getZipCodeValue() {
		return this.zipCodeValue;
	}

	/**
	 * Sets zip code value.
	 * 
	 * @param zipCodeValue zip code value
	 */
	public void setZipCodeValue(final String zipCodeValue) {
		this.zipCodeValue = zipCodeValue;
	}
	
	/**
	 * Returns zip code extension
	 * 
	 * @return zip code extension
	 */
	public String getZipCodeExtension() {
		return this.zipCodeExtension;
	}

	/**
	 * Sets zip code extension.
	 * 
	 * @param zipCodeExtension zip code extension
	 */
	public void setZipCodeExtension(final String zipCodeExtension) {
		this.zipCodeExtension = zipCodeExtension;
	}
	
	/**
	 * Returns hasState
	 * 
	 * @return hasState
	 */
	public Boolean getHasStates() {
		return this.hasStates;
	}

	/**
	 * Sets hasState.
	 * 
	 * @param hasState has state or not
	 */
	public void setHasState(final Boolean hasStates) {
		this.hasStates = hasStates;
	}
}