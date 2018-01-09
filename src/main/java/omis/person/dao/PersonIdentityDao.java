package omis.person.dao;

import java.util.Date;
import java.util.List;

import omis.country.domain.Country;
import omis.dao.GenericDao;
import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.region.domain.City;

/**
 * Data access object for person identities.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (June 1, 2015)
 * @since OMIS 3.0
 */
public interface PersonIdentityDao
		extends GenericDao<PersonIdentity> {

	/**
	 * Returns alternative identities for person.
	 * 
	 * <p>Alternative person identities are not equal to
	 * {@code person.getIdentity()}.
	 * 
	 * @param person person
	 * @return alternative identities for person
	 */
	List<PersonIdentity> findAlternativeIdentities(Person person);

	/**
	 * Returns the person identity with the matching properties.
	 * 
	 * @param person person
	 * @param socialSecurityNumber social security number
	 * @param birthDate birth date
	 * @param birthCity birth city
	 * @param birthCountry birth country 
	 * @param sex sex
	 * @return matching person identity
	 */
	PersonIdentity find(Person person, Date birthDate, City birthCity, 
		Country birthCountry, Integer socialSecurityNumber, Sex sex);

	/**
	 * Returns the person identity with the matching properties, except
	 * for the specified person identity.
	 * 
	 * @param personIdentity person identity
	 * @param socialSecurityNumber social security number
	 * @param birthDate birth date
	 * @param birthCity birth city
	 * @param birthCountry birth country
	 * @param sex sex
	 * @return matching person identity
	 */
	PersonIdentity findExcluding(PersonIdentity personIdentity, 
		Integer socialSecurityNumber, Date birthDate, City birthCity, 
		Country country, Sex sex);
	
	/**
	 * Returns a person identity found by person.
	 * 
	 * @param person person
	 * @return a person identity
	 */
	PersonIdentity findByPerson(Person person);
}