package omis.contact.report;

import java.io.Serializable;

/**
 * Summarizes PO box details.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2016)
 * @since OMIS 3.0
 */
public interface PoBoxSummarizable
		extends Serializable {
	
	/**
	 * Returns value of PO box.
	 * 
	 * @return value of PO box
	 */
	String getPoBoxValue();
	
	/**
	 * Returns name of city of PO box.
	 * 
	 * @return name of city of PO box.
	 */
	String getPoBoxCityName();
	
	/**
	 * Returns name of State of PO box.
	 * 
	 * @return name of State of PO box
	 */
	String getPoBoxStateName();
	
	/**
	 * Returns abbreviation of State of PO box.
	 * 
	 * @return abbreviation of State of PO box
	 */
	String getPoBoxStateAbbreviation();
	
	/**
	 * Returns value of ZIP code of PO box.
	 * 
	 * @return value of ZIP code of PO box
	 */
	String getPoBoxZipCodeValue();
	
	/**
	 * Returns extension of ZIP code of PO box.
	 * 
	 * @return extension of ZIP code of PO box
	 */
	String getPoBoxZipCodeExtension();
	
	/**
	 * Returns name of country of PO box.
	 * 
	 * @return name of country of PO box
	 */
	String getPoBoxCountryName();
	
	/**
	 * Returns abbreviation of country of PO box.
	 * 
	 * @return abbreviation of country of PO box
	 */
	String getPoBoxCountryAbbreviation();
}