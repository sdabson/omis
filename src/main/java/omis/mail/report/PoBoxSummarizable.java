package omis.mail.report;

import java.io.Serializable;

/**
 * PO box summary.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 21, 2015)
 * @since OMIS 3.0
 */
public interface PoBoxSummarizable
		extends Serializable {

	/**
	 * Returns PO box value.
	 * 
	 * @return PO box value
	 */
	String getPoBoxValue();
	
	/**
	 * Returns PO box ZIP code value.
	 * 
	 * @return PO box ZIP code value
	 */
	Integer getPoBoxZipCodeValue();
	
	/**
	 * Returns PO box ZIP code extension.
	 * 
	 * @return PO box ZIP code extension
	 */
	Integer getPoBoxZipCodeExtension();
	
	/**
	 * Returns name of PO box ZIP code country.
	 * 
	 * @return name of PO box ZIP code country
	 */
	String getPoBoxZipCodeCityName();
	
	/**
	 * Returns name of PO box ZIP code State.
	 * 
	 * @return name of PO box ZIP code State.
	 */
	String getPoBoxZipCodeStateName();
	
	/**
	 * Returns abbreviation of PO box ZIP code State.
	 * 
	 * @return abbreviation of PO box ZIP code State
	 */
	String getPoBoxZipCodeStateAbbreviation();
	
	/**
	 * Returns name of PO box ZIP code country.
	 * 
	 * @return name of PO box ZIP code country
	 */
	String getPoBoxZipCodeCountryName();
	
	/**
	 * Returns abbreviation of PO box ZIP code country.
	 * 
	 * @return abbreviation of PO box ZIP code country
	 */
	String getPoBoxZipCodeCountryAbbreviation();
}