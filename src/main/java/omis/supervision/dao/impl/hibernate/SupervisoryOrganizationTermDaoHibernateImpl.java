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
package omis.supervision.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.supervision.dao.SupervisoryOrganizationTermDao;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for supervisory
 * organization terms.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationTermDaoHibernateImpl 
		extends GenericHibernateDaoImpl<SupervisoryOrganizationTerm>
		implements SupervisoryOrganizationTermDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findSupervisoryOrganizationTermsByOffender";
	
	private static final String FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "findSupervisoryOrganizationTermForOffenderOnDate";
	
	private static final String COUNT_FOR_OFFENDER_BETWEEN_DATES_QUERY_NAME
		= "countSupervisoryOrganizationTermsForOffenderBetweenDates";
	
	private static final String
	COUNT_FOR_OFFENDER_BETWEEN_DATES_EXCLUDING_QUERY_NAME
		= "countSupervisoryOrganizationTermsForOffenderBetweenDatesExcluding";
	
	private static final String FIND_FOR_OFFENDER_WITH_START_DATE_QUERY_NAME
		= "findSupervisoryOrganizationTermForOffenderWithStartDate";

	private static final String FIND_FOR_OFFENDER_WITH_END_DATE_QUERY_NAME
		= "findSupervisoryOrganizationTermForOffenderWithEndDate";
	
	private static final String FIND_QUERY_NAME
		= "findSupervisoryOrganizationTerm";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSupervisoryOrganizationTermExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String DATE_PARAM_NAME = "date";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String
	EXCLUDE_SUPERVISORY_ORGANIZATION_TERMS_PARAM_NAME
		= "excludeSupervisoryOrganizationTerms";

	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";

	private static final String
	EXCLUDED_SUPERVISORY_ORGANIZATION_TERMS_PARAM_NAME
		= "excludedSupervisoryOrganizationTerms";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * supervisory organization terms with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SupervisoryOrganizationTermDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganizationTerm> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganizationTerm> terms = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return terms;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganizationTerm findForOffenderOnDate(
			final Offender offender, final Date date) {
		SupervisoryOrganizationTerm term = (SupervisoryOrganizationTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date).uniqueResult();
		return term;
	}

	/** {@inheritDoc} */
	@Override
	public long countForOffenderBetweenDates(
			final Offender offender, final Date startDate, final Date endDate) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_FOR_OFFENDER_BETWEEN_DATES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public long countForOffenderBetweenDatesExcluding(final Offender offender,
			final Date startDate, final Date endDate,
			final SupervisoryOrganizationTerm...
				excludeSupervisoryOrganizationTerms) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						COUNT_FOR_OFFENDER_BETWEEN_DATES_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setParameterList(
						EXCLUDE_SUPERVISORY_ORGANIZATION_TERMS_PARAM_NAME,
						excludeSupervisoryOrganizationTerms)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganizationTerm findForOffenderWithEndDate(
			final Offender offender, final Date endDate) {
		SupervisoryOrganizationTerm result = (SupervisoryOrganizationTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_WITH_END_DATE_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganizationTerm findForOffenderWithStartDate(
			final Offender offender, final Date startDate) {
		SupervisoryOrganizationTerm result = (SupervisoryOrganizationTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_WITH_START_DATE_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganizationTerm find(final Offender offender,
			final SupervisoryOrganization supervisoryOrganization,
			final Date startDate, final Date endDate) {
		SupervisoryOrganizationTerm result = (SupervisoryOrganizationTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
							supervisoryOrganization).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganizationTerm findExcluding(final Offender offender,
			final SupervisoryOrganization supervisoryOrganization,
			final Date startDate, final Date endDate,
			final SupervisoryOrganizationTerm...
			excludedSupervisoryOrganizationTerms) {
		SupervisoryOrganizationTerm result = (SupervisoryOrganizationTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_EXCLUDING_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
							supervisoryOrganization)
					.setParameterList(
							EXCLUDED_SUPERVISORY_ORGANIZATION_TERMS_PARAM_NAME,
							excludedSupervisoryOrganizationTerms)
					.uniqueResult();
		return result;
	}
}