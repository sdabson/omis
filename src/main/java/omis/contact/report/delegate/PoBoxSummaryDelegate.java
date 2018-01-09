package omis.contact.report.delegate;

import java.io.Serializable;

import omis.contact.domain.component.PoBox;

/**
 * Delegate to summarize PO boxes. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2016)
 * @since OMIS 3.0
 */
public class PoBoxSummaryDelegate
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final String value;
	
	private final String cityName;
	
	private final String stateName;
	
	private final String stateAbbreviation;
	
	private final String zipCodeValue;
	
	private final String zipCodeExtension;
	
	private final String countryName;
	
	private final String countryAbbreviation;
	
	/**
	 * Instantiates delegate to summarize PO boxes.
	 * 
	 * @param poBox PO box
	 */
	public PoBoxSummaryDelegate(final PoBox poBox) {
		if (poBox != null) {
			this.value = poBox.getValue();
			this.cityName = poBox.getZipCode().getCity().getName();
			if (poBox.getZipCode().getCity().getState() != null) {
				this.stateName = poBox.getZipCode().getCity().getState()
						.getName();
				this.stateAbbreviation = poBox.getZipCode().getCity().getState()
						.getAbbreviation();
			} else {
				this.stateName = null;
				this.stateAbbreviation = null;
			}
			this.zipCodeValue = poBox.getZipCode().getValue();
			this.zipCodeExtension = poBox.getZipCode().getExtension();
			this.countryName = poBox.getZipCode().getCity().getCountry()
					.getName();
			this.countryAbbreviation = poBox.getZipCode().getCity().getCountry()
					.getAbbreviation();
		} else {
			this.value = null;
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
	 * Returns name of city.
	 * 
	 * @return name of city
	 */
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * Returns name of State.
	 * 
	 * @return name of State
	 */
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * Returns abbreviation of State. 
	 * 
	 * @return abbreviation of State
	 */
	public String getStateAbbreviation() {
		return this.stateAbbreviation;
	}

	/**
	 * Returns value of ZIP code.
	 * 
	 * @return value of ZIP code
	 */
	public String getZipCodeValue() {
		return this.zipCodeValue;
	}

	/**
	 * Returns extension of ZIP code.
	 * 
	 * @return extension of ZIP code
	 */
	public String getZipCodeExtension() {
		return this.zipCodeExtension;
	}

	/**
	 * Returns name of country.
	 * 
	 * @return name of country
	 */
	public String getCountryName() {
		return this.countryName;
	}

	/**
	 * Returns abbreviation of country.
	 * 
	 * @return abbreviation of country
	 */
	public String getCountryAbbreviation() {
		return this.countryAbbreviation;
	}
}