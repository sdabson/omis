package omis.offender.report;

import java.io.Serializable;
import java.util.Date;

import omis.demographics.domain.Sex;

/**
 * Summary of offender information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 19, 2013)
 * @since OMIS 3.0
 */
public interface OffenderSummary
		extends Serializable {
	
	/**
	 * Returns the ID of the offender.
	 * 
	 * @return ID of offender
	 */
	Long getId();
	
	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	Integer getOffenderNumber();
	
	/**
	 * Returns the last name of the offender.
	 * 
	 * @return last name of offender
	 */
	String getLastName();
	
	/**
	 * Returns the first name of the offender.
	 * 
	 * @return first name of offender
	 */
	String getFirstName();
	
	/**
	 * Returns middle name of the offender.
	 * 
	 * @return middle name of the offender
	 */
	String getMiddleName();
	
	/**
	 * Returns suffix of the offender.
	 * 
	 * @return suffix of the offender
	 */
	String getSuffix();
	
	/**
	 * Returns the birth date.
	 * 
	 * @return birth date
	 */
	Date getBirthDate();
	
	/**
	 * Returns the sex.
	 * 
	 * @return sex
	 */
	Sex getSex();
	
	/**
	 * Returns the social security number of the offender 
	 * @return Integer - social security number of the offender
	 */
	Integer getSocialSecurityNumber();
	
}