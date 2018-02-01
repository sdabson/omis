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
package omis.person.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.instance.factory.InstanceFactory;
import omis.person.dao.PersonDao;
import omis.person.dao.PersonIdentityDao;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.domain.PersonName;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Delegate for persons.
 *
 * @author Stephen Abson
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.0.3 (Jan 4, 2016)
 * @since OMIS 3.0
 */
public class PersonDelegate {

	/* Data access objects. */
	
	private final PersonDao personDao;
	
	private final PersonIdentityDao personIdentityDao;
	
	/* Delegate. */
	private final PersonIdentityDelegate personIdentityDelegate;
	
	private final PersonNameDelegate personNameDelegate;
	
	/* Instance factories. */
	
	private final InstanceFactory<Person> personInstanceFactory;
	
	private final InstanceFactory<PersonName> personNameInstanceFactory;
	
	private final InstanceFactory<PersonIdentity> personIdentityInstanceFactory;
	
	/* Component retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate for persons.
	 * 
	 * @param personDao data access object for persons
	 * @param personIdentityDao person identity data access object
	 * @param personInstanceFactory instance factory for persons
	 * @param personNameInstanceFactory instance factory for person names
	 * @param auditComponentRetriever
	 * @param personNameDelegate person name delegate
	 */
	public PersonDelegate(
			final PersonDao personDao,
			final PersonIdentityDao personIdentityDao,
			final PersonIdentityDelegate personIdentityDelegate,
			final InstanceFactory<Person> personInstanceFactory,
			final InstanceFactory<PersonName> personNameInstanceFactory,
			final InstanceFactory<PersonIdentity> personIdentityInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever,
			final PersonNameDelegate personNameDelegate) {
		this.personDao = personDao;
		this.personIdentityDao = personIdentityDao;
		this.personIdentityDelegate = personIdentityDelegate;
		this.personInstanceFactory = personInstanceFactory;
		this.personNameInstanceFactory = personNameInstanceFactory;
		this.personIdentityInstanceFactory = personIdentityInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.personNameDelegate = personNameDelegate;
	}
	
	/**
	 * Creates person.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return newly created person
	 */
	public Person create(final String lastName, final String firstName,
			final String middleName, final String suffix) {
		Person person = this.personInstanceFactory.createInstance();
		person.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		person.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		PersonName personName = this.personNameInstanceFactory.createInstance();
		personName.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personName.setPerson(person);
		this.populatePersonName(personName, firstName, lastName, middleName, suffix);
		person.setName(personName);
		return this.personDao.makePersistent(person);
	}
	
	/**
	 * Creates person with a person identity.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param sex sex
	 * @param birthDate date of birth
	 * @param birthCountry country of birth
	 * @param birthState State of birth
	 * @param birthCity city of birth
	 * @param socialSecurityNumber social security number
	 * @param stateId State ID
	 * @param deceased whether deceased
	 * @param deathDate date of death
	 * @return newly created person with identity
	 */
	public Person createWithIdentity(final String lastName,
			final String firstName, final String middleName,
			final String suffix, final Sex sex, final Date birthDate,
			final Country birthCountry, final State birthState,
			final City birthCity, final Integer socialSecurityNumber,
			final String stateId, final Boolean deceased,
			final Date deathDate) {
		Person person = this.personInstanceFactory.createInstance();
		person.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		person.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		PersonName personName = this.personNameInstanceFactory.createInstance();
		personName.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personName.setPerson(person);
		this.populatePersonName(personName, firstName, lastName, middleName, suffix);
		person.setName(personName);
		this.personDao.makePersistent(person);
		PersonIdentity personIdentity = this.personIdentityInstanceFactory
				.createInstance();
		personIdentity.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personIdentity.setPerson(person);
		this.populatePersonIdentity(personIdentity, sex, birthDate, birthState,
				birthCity, socialSecurityNumber, stateId, deceased, deathDate,
				birthCountry);
		this.personIdentityDao.makePersistent(personIdentity);
		person.setIdentity(personIdentity);
		return this.personDao.makePersistent(person);
	}
	
	/**
	 * Updates person with identity.
	 * 
	 * @param person person
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param sex sex
	 * @param birthDate birth date
	 * @param birthCountry birth country
	 * @param birthState birth State
	 * @param birthCity birth city
	 * @param socialSecurityNumber social security number
	 * @param stateId State ID
	 * @param deceased deceased
	 * @param deathDate death date
	 * @return updated person identity
	 * @throws PersonIdentityExistsException if person identity exists for
	 * person
	 * @throws PersonNameExistsException if person name exists for person
	 */
	public Person updateWithIdentity(final Person person, final String lastName,
			final String firstName, final String middleName,
			final String suffix, final Sex sex, final Date birthDate,
			final Country birthCountry, final State birthState,
			final City birthCity, final Integer socialSecurityNumber,
			final String stateId, final Boolean deceased,
			final Date deathDate)
					throws PersonIdentityExistsException, PersonNameExistsException {
		if(person.getIdentity()!=null) {
			this.personIdentityDelegate.update(person.getIdentity(), sex, 
			birthDate, birthCountry, birthState, birthCity, socialSecurityNumber, 
			stateId, deceased, deathDate);
			this.personNameDelegate.update(person.getName(), lastName, 
				firstName, middleName, suffix);
		} else {
			PersonIdentity personIdentity = this.personIdentityDelegate.create(
				person, sex, birthDate,	birthCountry, birthState, birthCity, 
				socialSecurityNumber, stateId, deceased, deathDate);
			person.setIdentity(personIdentity);
			PersonName personName= this.personNameDelegate.update(person.getName(), 
				lastName, firstName, middleName, suffix);
			person.setName(personName);
			person.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
			this.personIdentityDao.makePersistent(personIdentity);
			personDao.makePersistent(person);
		}
		return person;
	}
	
	/**
	 * Updates person.
	 * 
	 * @param person person
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return updated person
	 */
	public Person update(final Person person, final String lastName,
			final String firstName, final String middleName,
			final String suffix) {
		PersonName personName = person.getName();
		this.populatePersonName(personName, firstName, lastName, middleName,
				suffix);
		return this.personDao.makePersistent(person);
	}
	
	/**
	 * Removes person.
	 * 
	 * <p>Throws {@code UnsupportedOperationException} if the removal of a
	 * person is not supported.
	 * @param person person
	 * @throws UnsupportedOperationException if removal of Person is not allowed
	 */
	public void remove(final Person person) {
		throw new UnsupportedOperationException("Cannot remove person");
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified person name with the specified properties and
	 * update signature.
	 * 
	 * @param personName person name
	 * @param firstName first name
	 * @param lastName last name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return populated person name
	 */
	private PersonName populatePersonName(final PersonName personName, 
			final String firstName, final String lastName,
			final String middleName, final String suffix) {
		personName.setFirstName(firstName);
		personName.setLastName(lastName);
		personName.setMiddleName(middleName);
		personName.setSuffix(suffix);
		personName.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return personName;
	}
	
	/*
	 * Populates the specified person identity.
	 * 
	 * @param personIdentity person identity
	 * @param sex sex
	 * @param birthDate birth date
	 * @param birthState birth state
	 * @param birthCity birth city
	 * @param socialSecurityNumber social security number
	 * @param stateId state identification number
	 * @param deceased whether deceased applies
	 * @param deathDate date of death
	 * @return person identity
	 */
	private PersonIdentity populatePersonIdentity(
			final PersonIdentity personIdentity, final Sex sex,
			final Date birthDate, final State birthState, 
			final City birthCity, final Integer socialSecurityNumber,
			final String stateId,  final Boolean deceased,
			final Date deathDate, final Country birthCountry) {
		personIdentity.setSex(sex);
		personIdentity.setBirthDate(birthDate);
		personIdentity.setBirthState(birthState);
		personIdentity.setBirthPlace(birthCity);
		personIdentity.setSocialSecurityNumber(socialSecurityNumber);
		personIdentity.setStateIdNumber(stateId);
		personIdentity.setDeceased(deceased);
		personIdentity.setDeathDate(deathDate);
		personIdentity.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		personIdentity.setBirthCountry(birthCountry);
		return personIdentity;
	}
	
}