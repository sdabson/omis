/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
	
	/**
	 * Returns person identities with State ID number.
	 * 
	 * @param stateIdNumber State ID number by which to return person identities
	 * @return person identities by State ID number
	 */
	List<PersonIdentity> findByStateIdNumber(String stateIdNumber);
	
	/**
	 * Returns person identities with State ID number excluding supplied
	 * person identities.
	 * 
	 * @param stateIdNumber State ID number by which to return person identities
	 * @param excludedIdentities person identities to exclude
	 * @return person identities with State ID number excluding supplied person
	 * identities
	 */
	List<PersonIdentity> findByStateIdNumberExcluding(
			String stateIdNumber, PersonIdentity... excludedIdentities);
}