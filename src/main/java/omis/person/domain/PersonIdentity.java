package omis.person.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Identity of a person.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 11, 2013)
 * @since OMIS 3.0
 */
public interface PersonIdentity
		extends Updatable, Creatable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return
	 */
	Long getId();
	
	/**
	 * Sets the person.
	 * 
	 * @param person person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the person.
	 * 
	 * @return person
	 */
	Person getPerson();
	
	/**
	 * Sets the social security number.
	 * 
	 * @param socialSecurityNumber social security number
	 */
	void setSocialSecurityNumber(Integer socialSecurityNumber);
	
	/**
	 * Returns the social security number.
	 * 
	 * @return social security number
	 */
	Integer getSocialSecurityNumber();
	
	/**
	 * Sets the State ID number.
	 * 
	 * @param stateIdNumber State ID number
	 */
	void setStateIdNumber(String stateIdNumber);
	
	/**
	 * Returns the State ID number.
	 * 
	 * @return State ID number
	 */
	String getStateIdNumber();
	
	/**
	 * Sets the birth date.
	 * 
	 * @param birthDate birth date
	 */
	void setBirthDate(Date birthDate);
	
	/**
	 * Returns the birth date.
	 * 
	 * @return birth date
	 */
	Date getBirthDate();
	
	/**
	 * Sets the birth country.
	 * 
	 * @param birthCountry birth country
	 */
	void setBirthCountry(Country birthCountry);
	
	/**
	 * Returns the birth country.
	 * 
	 * @return birth country
	 */
	Country getBirthCountry();
	
	/**
	 * Sets the birth State.
	 * 
	 * @param birthState birth State
	 */
	void setBirthState(State birthState);
	
	/**
	 * Returns the birth State.
	 * 
	 * @return birth State
	 */
	State getBirthState();
	
	/**
	 * Sets the city of birth.
	 * 
	 * @param birthPlace city of birth
	 */
	void setBirthPlace(City birthPlace);
	
	/**
	 * Returns the city of birth.
	 * 
	 * @return city of birth
	 */
	City getBirthPlace();
	
	/**
	 * Sets the sex.
	 * 
	 * @param sex sex
	 */
	void setSex(Sex sex);
	
	/**
	 * Returns the sex.
	 * 
	 * @return sex
	 */
	Sex getSex();
	
	/**
	 * Sets whether person is deceased.
	 * 
	 * @param deceased whether person is deceased
	 */
	void setDeceased(Boolean deceased);
	
	/**
	 * Returns whether person is deceased.
	 * 
	 * @return whether person is deceased
	 */
	Boolean getDeceased();
	
	/**
	 * Sets date of death.
	 * 
	 * @param deathDate date of death
	 */
	void setDeathDate(Date deathDate);
	
	/**
	 * Returns date of death.
	 * 
	 * @return date of death
	 */
	Date getDeathDate();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}