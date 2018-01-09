package omis.person.report;

import java.io.Serializable;
import java.util.Date;

import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.domain.PersonName;
import omis.person.report.delegate.PersonSummaryDelegate;

/**
 * Summary of person.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 9, 2016)
 * @since OMIS 3.0
 */
public class PersonSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final PersonSummaryDelegate delegate;
	
	/**
	 * Instantiates summary of person.
	 * 
	 * @param person person
	 * @param name name
	 * @param identity identity
	 */
	public PersonSummary(
			final Person person, final PersonName name,
			final PersonIdentity identity) {
		this.delegate = new PersonSummaryDelegate(person, name, identity);
	}

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.delegate.getId();
	}
	
	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.delegate.getLastName();
	}
	
	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.delegate.getFirstName();
	}
	
	/**
	 * Returns middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.delegate.getMiddleName();
	}
	
	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.delegate.getSuffix();
	}
	
	/**
	 * Returns social security number.
	 * 
	 * @return social security number
	 */
	public Integer getSocialSecurityNumber() {
		return this.delegate.getSocialSecurityNumber();
	}
	
	/**
	 * Returns State ID number.
	 * 
	 * @return State ID number
	 */
	public String getStateIdNumber() {
		return this.delegate.getStateIdNumber();
	}
	
	/**
	 * Returns birth date.
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.delegate.getBirthDate();
	}
	
	/**
	 * Returns name of birth place.
	 * 
	 * @return name of birth place
	 */
	public String getBirthPlaceName() {
		return this.delegate.getBirthPlaceName();
	}
	
	/**
	 * Returns name of birth State.
	 * 
	 * @return name of birth State
	 */
	public String getBirthStateName() {
		return this.delegate.getBirthStateName();
	}
	
	/**
	 * Returns abbreviation of birth State.
	 * 
	 * @return abbreviation of birth State
	 */
	public String getBirthStateAbbreviation() {
		return this.delegate.getBirthStateAbbreviation();
	}
	
	/**
	 * Returns name of birth country.
	 * 
	 * @return name of birth country
	 */
	public String getBirthCountryName() {
		return this.delegate.getBirthCountryName();
	}
	
	/**
	 * Returns abbreviation of birth country.
	 * 
	 * @return abbreviation of birth country
	 */
	public String getBirthCountryAbbreviation() {
		return this.delegate.getBirthCountryAbbreviation();
	}
	
	/**
	 * Returns sex.
	 * 
	 * @return sex
	 */
	public Sex getSex() {
		return this.delegate.getSex();
	}
	
	/**
	 * Returns whether person is deceased.
	 * 
	 * @return whether person is deceased
	 */
	public Boolean getDeceased() {
		return this.delegate.getDeceased();
	}
	
	/**
	 * Returns death date.
	 * 
	 * @return death date
	 */
	public Date getDeathDate() {
		return this.delegate.getDeathDate();
	}
}