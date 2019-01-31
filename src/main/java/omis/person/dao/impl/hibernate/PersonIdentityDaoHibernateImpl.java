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
package omis.person.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.country.domain.Country;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.domain.Sex;
import omis.person.dao.PersonIdentityDao;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.region.domain.City;

/**
 * Hibernate implementation of data access object for person identities.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.2 (Jan 17, 2019)
 * @since OMIS 3.0
 */
public class PersonIdentityDaoHibernateImpl
		extends GenericHibernateDaoImpl<PersonIdentity>
		implements PersonIdentityDao {

	/* Query names. */
	
	private static final String FIND_ALTERNATIVE_IDENTITIES_QUERY_NAME
		= "findAlternativePersonIdentities";
	
	private static final String FIND_IDENTITY_QUERY_NAME = "findPersonIdentity";
	
	private static final String FIND_IDENTITY_EXCLUDING_QUERY_NAME
		= "findPersonIdentityExcluding";
	
	private static final String FIND_IDENTITY_BY_PERSON_QUERY_NAME 
		= "findPersonIdentityByPerson";
	
	private static final String FIND_BY_STATE_ID_NUMBER_QUERY_NAME
		= "findPersonIdentitiesByStateIdNumber";

	private static final String FIND_BY_STATE_ID_NUMBER_EXCLUDING_QUERY_NAME
		= "findPersonIdentitiesByStateIdNumberExcluding";
	
	private static final String FIND_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME
		= "findPersonIdentitiesBySocialSecurityNumber";
	
	private static final String
	FIND_BY_SOCIAL_SECURITY_NUMBER_EXCLUDING_QUERY_NAME
		= "findPersonIdentitiesBySocialSecurityNumberExcluding";
	
	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String BIRTH_DATE_PARAM_NAME = "birthDate";
	
	private static final String BIRTH_CITY_PARAM_NAME = "birthCity";
	
	private static final String BIRTH_COUNTRY_PARAM_NAME = "birthCountry";
	
	private static final String PERSON_IDENTITY_PARAM_NAME = "personIdentity";
	
	private static final String SOCIAL_SECURITY_NUMBER_PARAM_NAME 
		= "socialSecurityNumber";
	
	private static final String SEX_PARAM_NAME = "sex";

	private static final String STATE_ID_NUMBER_PARAM_NAME = "stateIdNumber";
	
	private static final String EXCLUDED_IDENTITIES_PARAM_NAME
		= "excludedIdentities";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * person identities.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PersonIdentityDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<PersonIdentity> findAlternativeIdentities(final Person person) {
		@SuppressWarnings("unchecked")
		List<PersonIdentity> personIdentities = (List<PersonIdentity>)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ALTERNATIVE_IDENTITIES_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.list();
		return personIdentities;
	}

	/** {@inheritDoc} */
	@Override
	public PersonIdentity find(final Person person, final Date birthDate, 
			final City birthCity, final Country birthCountry, 
			final Integer socialSecurityNumber, final Sex sex) {
		PersonIdentity identity = (PersonIdentity) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_IDENTITY_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME, socialSecurityNumber)
				.setDate(BIRTH_DATE_PARAM_NAME, birthDate)
				.setParameter(BIRTH_CITY_PARAM_NAME, birthCity)
				.setParameter(BIRTH_COUNTRY_PARAM_NAME, birthCountry)
				.setParameter(SEX_PARAM_NAME, sex)
				.uniqueResult();
		return identity;
	}

	/** {@inheritDoc} */
	@Override
	public PersonIdentity findExcluding(final PersonIdentity personIdentity,
			final Integer socialSecurityNumber, final Date birthDate, 
			final City birthCity, final Country birthCountry, final Sex sex) {
		PersonIdentity identity = (PersonIdentity) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_IDENTITY_EXCLUDING_QUERY_NAME)
				.setParameter(PERSON_IDENTITY_PARAM_NAME, personIdentity)
				.setParameter(PERSON_PARAM_NAME, personIdentity.getPerson())
				.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME, socialSecurityNumber)
				.setDate(BIRTH_DATE_PARAM_NAME, birthDate)
				.setParameter(BIRTH_CITY_PARAM_NAME, birthCity)
				.setParameter(BIRTH_COUNTRY_PARAM_NAME, birthCountry)
				.setParameter(SEX_PARAM_NAME, sex)
				.uniqueResult();
		return identity;
	}
	
	/** {@inheritDoc} */
	@Override
	public PersonIdentity findByPerson(final Person person) {
		PersonIdentity identity = (PersonIdentity) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_IDENTITY_BY_PERSON_QUERY_NAME)
			.setParameter(PERSON_PARAM_NAME, person)
			.uniqueResult();
		return identity;
	}

	/** {@inheritDoc} */
	@Override
	public List<PersonIdentity> findByStateIdNumber(
			final String stateIdNumber) {
		@SuppressWarnings("unchecked")
		List<PersonIdentity> identities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_BY_STATE_ID_NUMBER_QUERY_NAME)
			.setParameter(STATE_ID_NUMBER_PARAM_NAME, stateIdNumber)
			.list();
		return identities;
	}

	/** {@inheritDoc} */
	@Override
	public List<PersonIdentity> findByStateIdNumberExcluding(
			final String stateIdNumber,
			final PersonIdentity... excludedIdentities) {
		@SuppressWarnings("unchecked")
		List<PersonIdentity> identities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_BY_STATE_ID_NUMBER_EXCLUDING_QUERY_NAME)
			.setParameter(STATE_ID_NUMBER_PARAM_NAME, stateIdNumber)
			.setParameterList(EXCLUDED_IDENTITIES_PARAM_NAME,
					excludedIdentities)
			.list();
		return identities;
	}

	/** {@inheritDoc} */
	@Override
	public List<PersonIdentity> findBySocialSecurityNumber(
			final Integer socialSecurityNumber) {
		@SuppressWarnings("unchecked")
		List<PersonIdentity> identities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME)
			.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME,
					socialSecurityNumber)
			.list();
		return identities;
	}

	/** {@inheritDoc} */
	@Override
	public List<PersonIdentity> findBySocialSecurityNumberExcluding(
			final Integer socialSecurityNumber,
			final PersonIdentity... excludedIdentities) {
		@SuppressWarnings("unchecked")
		List<PersonIdentity> identities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_BY_SOCIAL_SECURITY_NUMBER_EXCLUDING_QUERY_NAME)
			.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME,
					socialSecurityNumber)
			.setParameterList(EXCLUDED_IDENTITIES_PARAM_NAME,
					excludedIdentities)
			.list();
		return identities;
	}
}