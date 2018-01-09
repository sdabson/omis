package omis.mail.report.delegate;

import java.io.Serializable;

import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.mail.domain.PoBox;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Delegate for PO boxes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 22, 2015)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public class PoBoxSummaryDelegate
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String value;
	
	private final String zipCodeValue;
	
	private final String zipCodeExtension;
	
	private final String zipCodeCityName;
	
	private final String zipCodeStateName;
	
	private final String zipCodeStateAbbreviation;
	
	private final String zipCodeCountryName;
	
	private final String zipCodeCountryAbbreviation;
	
	/**
	 * Delegate for PO box summary.
	 * 
	 * @param poBox PO box
	 * @param zipCode ZIP code
	 * @param city city
	 * @param state State
	 * @param country country
	 */
	public PoBoxSummaryDelegate(
			final PoBox poBox,
			final ZipCode zipCode,
			final City city,
			final State state,
			final Country country) {
		this.value = poBox.getValue();
		this.zipCodeValue = zipCode.getValue();
		this.zipCodeExtension = zipCode.getExtension();
		this.zipCodeCityName = city.getName();
		this.zipCodeStateName = state.getName();
		this.zipCodeStateAbbreviation = state.getAbbreviation();
		this.zipCodeCountryName = country.getName();
		this.zipCodeCountryAbbreviation = country.getAbbreviation();
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Returns ZIP code value.
	 * 
	 * @return ZIP code value
	 */
	public String getZipCodeValue() {
		return this.zipCodeValue;
	}
	
	/**
	 * Returns ZIP code extension.
	 * 
	 * @return ZIP code extension
	 */
	public String getZipCodeExtension() {
		return this.zipCodeExtension;
	}
	
	/**
	 * Returns ZIP code city name.
	 * 
	 * @return ZIP code city name
	 */
	public String getZipCodeCityName() {
		return this.zipCodeCityName;
	}
	
	/**
	 * Returns ZIP code State name.
	 * 
	 * @return ZIP code State name
	 */
	public String getZipCodeStateName() {
		return this.zipCodeStateName;
	}
	
	/**
	 * Returns ZIP code State abbreviation.
	 * 
	 * @return ZIP code State abbreviation
	 */
	public String getZipCodeStateAbbreviation() {
		return this.zipCodeStateAbbreviation;
	}
	
	/**
	 * Returns ZIP code country name.
	 * 
	 * @return ZIP code country name
	 */
	public String getZipCodeCountryName() {
		return this.zipCodeCountryName;
	}
	
	/**
	 * Returns ZIP code country abbreviation.
	 * 
	 * @return ZIP code country abbreviation
	 */
	public String getZipCodeCountryAbbreviation() {
		return this.zipCodeCountryAbbreviation;
	}
}