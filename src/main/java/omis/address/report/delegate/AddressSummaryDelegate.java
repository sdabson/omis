package omis.address.report.delegate;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;

/**
 * Delegate to summarize addresses.
 *
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.0.1 (Mar 11, 2015)
 * @since OMIS 3.0
 */
public class AddressSummaryDelegate
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String value;
	
	private final String designator;
	
	private final String coordinates;
	
	private final BuildingCategory category;
	
	private final String cityName;
	
	private final String stateName;
	
	private final String stateAbbreviation;
	
	private final String zipCodeValue;
	
	private final String zipCodeExtension;
	
	private final String countryName;
	
	private final String countryAbbreviation;
	
	/**
	 * Instantiates delegate to summarize addresses.
	 * 
	 * @param address address to summarize
	 */
	public AddressSummaryDelegate(final Address address) {
		if (address != null) {
			this.value = address.getValue();
			this.designator = address.getDesignator();
			this.coordinates = address.getCoordinates();
			this.category = address.getBuildingCategory();
			
			this.cityName = address.getZipCode().getCity().getName();
			
			if(address.getZipCode().getCity().getState()!=null){
				this.stateName = address.getZipCode().getCity().getState()
					.getName();
				this.stateAbbreviation
					= address.getZipCode().getCity().getState().getAbbreviation();
				this.countryName = address.getZipCode().getCity().getState()
					.getCountry().getName();
				this.countryAbbreviation = address.getZipCode().getCity()
					.getState().getCountry().getAbbreviation();
			}
			else {
				this.stateName = null;
				this.stateAbbreviation = null;
				this.countryName = address.getZipCode().getCity().getCountry()
					.getName();
				this.countryAbbreviation = address.getZipCode().getCity()
					.getCountry().getAbbreviation();
			}

			this.zipCodeValue = address.getZipCode().getValue();
			this.zipCodeExtension = address.getZipCode().getExtension();
			
		} else {
			this.value = null;
			this.designator = null;
			this.coordinates = null;
			this.category = null;
			this.cityName = null;
			this.stateName = null;
			this.stateAbbreviation = null;
			this.zipCodeValue = null;
			this.zipCodeExtension = null;
			this.countryName = null;
			this.countryAbbreviation = null;
		}
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
	 * Returns designator.
	 * 
	 * @return designator
	 */
	public String getDesignator() {
		return this.designator;
	}

	/**
	 * Returns coordinates.
	 * 
	 * @return coordinates
	 */
	public String getCoordinates() {
		return this.coordinates;
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public BuildingCategory getCategory() {
		return this.category;
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
	 * Returns State name.
	 * 
	 * @return State name
	 */
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * Returns State abbreviation.
	 * 
	 * @return State abbreviation
	 */
	public String getStateAbbreviation() {
		return this.stateAbbreviation;
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
	 * Returns country name.
	 * 
	 * @return country name
	 */
	public String getCountryName() {
		return this.countryName;
	}

	/**
	 * Returns country abbreviation.
	 * 
	 * @return country abbreviation
	 */
	public String getCountryAbbreviation() {
		return this.countryAbbreviation;
	}
}