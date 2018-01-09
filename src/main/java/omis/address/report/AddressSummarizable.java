package omis.address.report;

import java.io.Serializable;

import omis.address.domain.BuildingCategory;

/**
 * Summarizes address details.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 11, 2015)
 * @since OMIS 3.0
 */
public interface AddressSummarizable
		extends Serializable {

	/**
	 * Returns address number.
	 * 
	 * @return address number
	 */
	String getAddressValue();
	
	/**
	 * Returns address designator.
	 * 
	 * @return address designator
	 */
	String getAddressDesignator();
	
	/**
	 * Returns address coordinates.
	 * 
	 * @return address coordinates
	 */
	String getAddressCoordinates();
	
	/**
	 * Returns address category.
	 * 
	 * @return address category
	 */
	BuildingCategory getAddressCategory();
	
	/**
	 * Returns address city name.
	 * 
	 * @return address city name
	 */
	String getAddressCityName();
	
	/**
	 * Return address State name.
	 * 
	 * @return address State name
	 */
	String getAddressStateName();
	
	/**
	 * Returns address State abbreviation.
	 *  
	 * @return address State abbreviation
	 */
	String getAddressStateAbbreviation();
	
	/**
	 * Returns address ZIP code value.
	 * 
	 * @return address ZIP code value
	 */
	String getAddressZipCodeValue();
	
	/**
	 * Returns address ZIP code extension.
	 * 
	 * @return address ZIP code extension
	 */
	String getAddressZipCodeExtension();
	
	/**
	 * Returns address country name.
	 * 
	 * @return address country name
	 */
	String getAddressCountryName();
	
	/**
	 * Returns address country abbreviation.
	 * 
	 * @return address country abbreviation
	 */
	String getAddressCountryAbbreviation();
}