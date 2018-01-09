package omis.contact.report;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumberCategory;

/**
 * Summarizes telephone number details.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 19, 2015)
 * @since OMIS 3.0
 */
public interface TelephoneNumberSummarizable
		extends Serializable {

	/**
	 * Returns telephone number.
	 * 
	 * @return telephone number
	 */
	Long getTelephoneNumberValue();
	
	/**
	 * Returns telephone number extension.
	 * 
	 * @return telephone number extension
	 */
	Integer getTelephoneNumberExtension();
	
	/**
	 * Returns whether primary telephone number.
	 * 
	 * @return whether primary telephone number
	 */
	Boolean getTelephoneNumberPrimary();
	
	/**
	 * Returns whether telephone number is active.
	 * 
	 * @return whether telephone number is active
	 */
	Boolean getTelephoneNumberActive();
	
	/**
	 * Returns category of telephone number.
	 * 
	 * @return category of telephone number
	 */
	TelephoneNumberCategory getTelephoneNumberCategory();
}