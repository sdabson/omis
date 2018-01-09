package omis.person.report.delegate;

import java.io.Serializable;
import java.util.Date;

import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.domain.PersonName;

/**
 * Delegate for person summary.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 21, 2015)
 * @since OMIS 3.0
 */
public class PersonSummaryDelegate
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String suffix;
	
	private final Integer socialSecurityNumber;
	
	private final String stateIdNumber;
	
	private final Date birthDate;
	
	private final String birthPlaceName;
	
	private final String birthStateName;
	
	private final String birthStateAbbreviation;
	
	private final String birthCountryName;
	
	private final String birthCountryAbbreviation;
	
	private final Sex sex;
	
	private final Boolean deceased;
	
	private final Date deathDate;
	
	/**
	 * Instantiates person delegate.
	 * 
	 * @param person person
	 * @param personName person name
	 * @param personIdentity person identity
	 */
	public PersonSummaryDelegate(
			final Person person,
			final PersonName personName,
			final PersonIdentity personIdentity) {
		this.id = person.getId();
		this.lastName = personName.getLastName();
		this.firstName = personName.getFirstName();
		this.middleName = personName.getMiddleName();
		this.suffix = personName.getSuffix();
		if (personIdentity != null) {
			this.socialSecurityNumber = personIdentity.getSocialSecurityNumber();
			this.stateIdNumber = personIdentity.getStateIdNumber();
			this.birthDate = personIdentity.getBirthDate();
			if (personIdentity.getBirthPlace() != null) {
				this.birthPlaceName = personIdentity.getBirthPlace().getName();
			} else {
				this.birthPlaceName = null;
			}
			if (personIdentity.getBirthState() != null) {
				this.birthStateName = personIdentity.getBirthState().getName();
				this.birthStateAbbreviation = personIdentity.getBirthState()
						.getAbbreviation();
			} else {
				this.birthStateName = null;
				this.birthStateAbbreviation = null;
			}
			if (personIdentity.getBirthCountry() != null) {
				this.birthCountryName = personIdentity.getBirthCountry()
						.getName();
				this.birthCountryAbbreviation = personIdentity.getBirthCountry()
						.getAbbreviation();
			} else {
				this.birthCountryName = null;
				this.birthCountryAbbreviation = null;
			}
			this.sex = personIdentity.getSex();
			this.deceased = personIdentity.getDeceased();
			this.deathDate = personIdentity.getDeathDate();
		} else {
			this.socialSecurityNumber = null;
			this.stateIdNumber = null;
			this.birthDate = null;
			this.birthPlaceName = null;
			this.birthStateName = null;
			this.birthStateAbbreviation = null;
			this.birthCountryName = null;
			this.birthCountryAbbreviation = null;
			this.sex = null;
			this.deceased = null;
			this.deathDate = null;
		}
	}

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Returns middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	
	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}
	
	/**
	 * Returns social security number.
	 * 
	 * @return social security number
	 */
	public Integer getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}
	
	/**
	 * Returns State ID number.
	 * 
	 * @return State ID number
	 */
	public String getStateIdNumber() {
		return this.stateIdNumber;
	}
	
	/**
	 * Returns birth date.
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	/**
	 * Returns name of birth place.
	 * 
	 * @return name of birth place
	 */
	public String getBirthPlaceName() {
		return this.birthPlaceName;
	}
	
	/**
	 * Returns name of birth State.
	 * 
	 * @return name of birth State
	 */
	public String getBirthStateName() {
		return this.birthStateName;
	}
	
	/**
	 * Returns abbreviation of birth State.
	 * 
	 * @return abbreviation of birth State
	 */
	public String getBirthStateAbbreviation() {
		return this.birthStateAbbreviation;
	}
	
	/**
	 * Returns name of birth country.
	 * 
	 * @return name of birth country
	 */
	public String getBirthCountryName() {
		return this.birthCountryName;
	}
	
	/**
	 * Returns abbreviation of birth country.
	 * 
	 * @return abbreviation of birth country
	 */
	public String getBirthCountryAbbreviation() {
		return this.birthCountryAbbreviation;
	}
	
	/**
	 * Returns sex.
	 * 
	 * @return sex
	 */
	public Sex getSex() {
		return this.sex;
	}
	
	/**
	 * Returns whether person is deceased.
	 * 
	 * @return whether person is deceased
	 */
	public Boolean getDeceased() {
		return this.deceased;
	}
	
	/**
	 * Returns death date.
	 * 
	 * @return death date
	 */
	public Date getDeathDate() {
		return this.deathDate;
	}
}