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
package omis.offenderrelationship.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offenderrelationship.report.OffenderRelationshipReportService;
import omis.offenderrelationship.report.OffenderRelationshipSummary;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;

/**
 * Hibernate implementation of service to report offender relationships
 * summaries.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.0.2 (Feb 14, 2018)
 * @since OMIS 3.0
 */
public class OffenderRelationshipReportServiceHibernateImpl
		implements OffenderRelationshipReportService {
	
	/* Query names. */
	
	private static final String 
		SUMMARIZE_BY_FIRSTNAME_LASTNAME_APPROPXIMATE_QUERY_NAME
		= "summarizeOffenderRelationshipsByFirstNameLastNameAppropximate";
	
	private static final String SUMMARIZE_BY_FIRSTNAME_LASTNAME_EXACT_QUERY_NAME
		= "summarizeOffenderRelationshipsByFirstNameLastNameExact";
	
	private static final String SUMMARIZE_BY_OFFENDER_NUMBER_QUERY_NAME
		= "summarizeOffenderRelationshipsByOffenderNumber";
	
	private static final String SUMMARIZE_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME
		= "summarizeOffenderRelationshipsBySocialSecurityNumber";
	
	private static final String SUMMARIZE_BY_BIRTH_DATE_QUERY_NAME
		= "summarizeOffenderRelationshipsByBirthDate";	
	
	private static final String 
		COUNT_APPROPRIMATE_BY_FIRSTNAME_LASTNAME_QUERY_NAME
		= "countApproximateByFirstNameAndLastName";
	
	private static final String COUNT_EXACT_BY_FIRSTNAME_LASTNAME_QUERY_NAME
		= "countExactByFirstNameAndLastName";
	
	private static final String COUNT_APPROPRIMATE_BY_LASTNAME_QUERY_NAME
		= "countApproximateByLastName";
	
	private static final String COUNT_APPROPRIMATE_BY_FIRSTNAME_QUERY_NAME
		= "countApproximateByFirstName";
	
	private static final String COUNT_EXACT_BY_FIRSTNAME_QUERY_NAME
		= "countExactByFirstName";
	
	private static final String COUNT_EXACT_BY_LASTNAME_QUERY_NAME
		= "countExactByLastName";
	
	private static final String SUMMARIZE_BY_FIRSTNAME_APPROPXIMATE_QUERY_NAME
		= "summarizeOffenderRelationshipsByFirstNameAppropximate";
	
	private static final String SUMMARIZE_BY_LASTNAME_APPROPXIMATE_QUERY_NAME
		= "summarizeOffenderRelationshipsByLastNameAppropximate";
	
	private static final String SUMMARIZE_BY_LASTNAME_EXACT_QUERY_NAME
		= "summarizeOffenderRelationshipsByLastNameExact";
	
	private static final String SUMMARIZE_BY_FIRSTNAME_EXACT_QUERY_NAME
		= "summarizeOffenderRelationshipsByFirstNameExact";
		
	private static final String 
		COUNT_RELATIONSHIPS_BY_OFFENDER_RELATION_QUERY_NAME 
			= "countRelationshipsByOffenderRelation";
	
	private static final String FIND_RELAIONSHIP_BY_OFFENDER_RELATION_QUERY_NAME 
		= "findRelationshipsByOffenderRelation";
	
	private static final String 
		SUMMARIZE_RELATIONSHIPS_BY_TELEPHONE_NUMBER_QUERY_NAME
			= "summarizeRelationshipsByTelephoneNumber";

	private static final String SUMMARIZE_RELATIONSHIPS_BY_ONLINE_ACCOUNT_QUERY_NAME
		= "summarizeRelationshipsByOnlineAccount";
	
	private static final String SUMMARIZE_RELATIONSHIPS_BY_ADDRESS_QUERY_NAME
		= "summarizeRelationshipsByAddress";
		
	/* Parameter names. */
	
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	
	private static final String FIRST_NAME_PARAM_NAME = "firstName";

	private static final String OFFENDER_NUMBER_PARAM_NAME = "offenderNumber";
	
	private static final String BIRTH_DATE_PARAM_NAME = "birthDate";
	
	private static final String SOCIAL_SECURITY_NUMBER_PARAM_NAME
		= "socialSecurityNumber";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String RELATION_PARAM_NAME = "relation";

	private static final String TELEPHONE_NUMBER_VALUE_PARAM_NAME 
		= "telephoneNumberValue";

	private static final String ONLINE_ACCOUNT_NAME_PARAM_NAME = "onlineAccountName";

	private static final String ADDRESS_VALUE_PARAM_NAME = "addressValue";
	
	private final static String CITY_NAME_PARAM_NAME = "cityName";
	
	private final static String STATE_NAME_PARAM_NAME = "stateName";
	
	private final static String ZIP_CODE_VALUE_PARAM_NAME = "zipCodeValue";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	private final Integer maximumResults;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of service to report offender
	 * relationship summaries. 
	 * 
	 * @param sessionFactory session factory
	 * @param maximumResults maximum results 
	 */
	public OffenderRelationshipReportServiceHibernateImpl(
			final SessionFactory sessionFactory,
			final Integer maximumResults) {
		this.sessionFactory = sessionFactory;
		this.maximumResults = maximumResults;
	}

	/* Method implementation. */
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByName(
			final String lastName, final String firstName,
			final Date effectiveDate, final Boolean approximateMatch) {
		String queryName;
		if (approximateMatch) {
			if ((lastName != null && !lastName.isEmpty())
				&& (firstName != null && !firstName.isEmpty())) {
				queryName 
					= SUMMARIZE_BY_FIRSTNAME_LASTNAME_APPROPXIMATE_QUERY_NAME;
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries = 
					this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			} else if ((lastName != null && !lastName.isEmpty())
				&& (firstName == null || firstName.isEmpty())) {
				queryName = SUMMARIZE_BY_LASTNAME_APPROPXIMATE_QUERY_NAME;	
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries = 
					this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			} else {
				queryName = SUMMARIZE_BY_FIRSTNAME_APPROPXIMATE_QUERY_NAME;
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries = 
					this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			}
		} else {
			if (((lastName != null && !lastName.isEmpty())
				&& (firstName != null && !firstName.isEmpty()))) {
				queryName = SUMMARIZE_BY_FIRSTNAME_LASTNAME_EXACT_QUERY_NAME;
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries 
					= this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			} else if	((lastName != null && !lastName.isEmpty())
				&& (firstName == null || firstName.isEmpty())) {
				queryName = SUMMARIZE_BY_LASTNAME_EXACT_QUERY_NAME;	
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries 
					= this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			} else {
				queryName = SUMMARIZE_BY_FIRSTNAME_EXACT_QUERY_NAME;
				@SuppressWarnings("unchecked")
				List<OffenderRelationshipSummary> summaries 
					= this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
					.setReadOnly(true)
					.list();
				return summaries;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByOffenderNumber(
			final Integer offenderNumber, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
					SUMMARIZE_BY_OFFENDER_NUMBER_QUERY_NAME)
			.setParameter(OFFENDER_NUMBER_PARAM_NAME, offenderNumber)
			.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
			.setReadOnly(true)
			.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeBySocialSecurityNumber(
			final Integer socialSecurityNumber, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
					SUMMARIZE_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME)
			.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME,
					socialSecurityNumber)
			.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
			.setReadOnly(true)
			.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByBirthDate(
			final Date birthDate, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
					SUMMARIZE_BY_BIRTH_DATE_QUERY_NAME)
			.setTimestamp(BIRTH_DATE_PARAM_NAME, birthDate)
			.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
			.setReadOnly(true)
			.list();
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer countByPerson(final String lastName,
		final String firstName, final Boolean approximateMatch) {
		String queryName;
		if (approximateMatch) {
			if ((lastName != null && !lastName.isEmpty())
				&& (firstName != null && !firstName.isEmpty())) {
				queryName = COUNT_APPROPRIMATE_BY_FIRSTNAME_LASTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			} else if ((lastName != null && !lastName.isEmpty())
				&& (firstName == null || firstName.isEmpty())) {
				queryName = COUNT_APPROPRIMATE_BY_LASTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(
						COUNT_APPROPRIMATE_BY_LASTNAME_QUERY_NAME)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			} else {
				queryName = COUNT_APPROPRIMATE_BY_FIRSTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			}
		} else {
			if ((lastName != null && !lastName.isEmpty())
				&& (firstName != null && !firstName.isEmpty())) {
				queryName = COUNT_EXACT_BY_FIRSTNAME_LASTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			} else if ((lastName != null && !lastName.isEmpty())
				&& (firstName == null || firstName.isEmpty())) {
				queryName = COUNT_EXACT_BY_LASTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(LAST_NAME_PARAM_NAME, lastName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			} else {
				queryName = COUNT_EXACT_BY_FIRSTNAME_QUERY_NAME;
				Long count = (Long) this.sessionFactory
					.getCurrentSession().getNamedQuery(queryName)
					.setParameter(FIRST_NAME_PARAM_NAME, firstName)
					.setReadOnly(true)
					.uniqueResult();
				return count.intValue();
			}
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getMaximumResults() {
		return this.maximumResults;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean exceedsMaximumResults(final String lastName, 
		final String firstName, final Boolean approximateMatch) {
		return countByPerson(lastName, firstName, approximateMatch) 
			> 
			this.maximumResults;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean relationshipExists(
			final Offender offender, final Person relation) {
		final Boolean query = (Boolean) this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						COUNT_RELATIONSHIPS_BY_OFFENDER_RELATION_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(RELATION_PARAM_NAME, relation)
				.uniqueResult();
		return query;
	}

	/** {@inheritDoc} */
	@Override
	public Relationship findRelationship(Offender offender, Person relation) {
		Relationship relationship = (Relationship) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_RELAIONSHIP_BY_OFFENDER_RELATION_QUERY_NAME)
				.setParameter(RELATION_PARAM_NAME, relation)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return relationship;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByTelephoneNumber(
			final Long telephoneNumberValue) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						SUMMARIZE_RELATIONSHIPS_BY_TELEPHONE_NUMBER_QUERY_NAME)
				.setParameter(TELEPHONE_NUMBER_VALUE_PARAM_NAME, telephoneNumberValue)
				.setReadOnly(true)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByOnlineAccount(
			final String onlineAccountName) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_RELATIONSHIPS_BY_ONLINE_ACCOUNT_QUERY_NAME)
				.setParameter(ONLINE_ACCOUNT_NAME_PARAM_NAME, onlineAccountName)
				.setReadOnly(true)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByAddress(
			final String addressValue, final String cityName, final String stateName, final String zipCodeValue) {	
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries = this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_RELATIONSHIPS_BY_ADDRESS_QUERY_NAME)
				.setParameter(ADDRESS_VALUE_PARAM_NAME, addressValue)
				.setParameter(CITY_NAME_PARAM_NAME, cityName)
				.setParameter(STATE_NAME_PARAM_NAME, stateName)
				.setParameter(ZIP_CODE_VALUE_PARAM_NAME, zipCodeValue)
				.setReadOnly(true)
				.list();
		return summaries;
	}
}