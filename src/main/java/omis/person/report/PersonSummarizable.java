package omis.person.report;

import java.io.Serializable;
import java.util.Date;

import omis.demographics.domain.Sex;

/**
 * Summarizes person.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 21, 2015)
 * @since OMIS 3.0
 */
public interface PersonSummarizable
		extends Serializable {
	
	/**
	 * Returns ID of person.
	 * 
	 * @return ID of person
	 */
	Long getPersonId();
	
	/**
	 * Returns last name of person.
	 * 
	 * @return last name of person
	 */
	String getPersonLastName();
	
	/**
	 * Returns first name of person.
	 * 
	 * @return first name of person
	 */
	String getPersonFirstName();
	
	/**
	 * Returns middle name of person.
	 * 
	 * @return middle name of person
	 */
	String getPersonMiddleName();
	
	/**
	 * Returns suffix of person.
	 * 
	 * @return suffix of person
	 */
	String getPersonSuffix();
	
	/**
	 * Returns social security number of person.
	 * 
	 * @return social security number of person
	 */
	Integer getPersonSocialSecurityNumber();
	
	/**
	 * Returns State ID number of person.
	 * 
	 * @return State ID number of person
	 */
	String getPersonStateIdNumber();
	
	/**
	 * Returns birth date of person.
	 * 
	 * @return birth date of person
	 */
	Date getPersonBirthDate();
	
	/**
	 * Returns name of birth place of person.
	 * 
	 * <p>Birth place is the name of a city.
	 * 
	 * @return name of birth place of person
	 */
	String getPersonBirthPlaceName();
	
	/**
	 * Returns name of birth State of person.
	 * 
	 * @return name of birth State of person
	 */
	String getPersonBirthStateName();
	
	/**
	 * Returns abbreviation of birth State of person.
	 * 
	 * @return abbreviation of birth State of person
	 */
	String getPersonBirthStateAbbreviation();
	
	/**
	 * Returns name of birth country of person.
	 * 
	 * @return name of birth country of person.
	 */
	String getPersonBirthCountryName();
	
	/**
	 * Returns abbreviation of birth country of person.
	 * 
	 * @return abbreviation of birth country of person
	 */
	String getPersonBirthCountryAbbreviation();
	
	/**
	 * Returns sex of person.
	 * 
	 * @return sex of person
	 */
	Sex getPersonSex();
	
	/**
	 * Returns whether person is deceased.
	 * 
	 * @return whether person is deceased
	 */
	Boolean getPersonDeceased();
	
	/**
	 * Returns death date of person if deceased.
	 * 
	 * @return death date of person if deceased
	 */
	Date getPersonDeathDate();
}