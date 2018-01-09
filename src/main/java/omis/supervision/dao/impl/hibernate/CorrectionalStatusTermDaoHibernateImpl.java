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
import omis.supervision.dao.CorrectionalStatusTermDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;

import org.hibernate.SessionFactory;
/**
 * Hibernate implementation of data access object for correctional status terms.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public class CorrectionalStatusTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CorrectionalStatusTerm> 
	implements CorrectionalStatusTermDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findCorrectionalStatusTermsByOffender";
	
	private static final String FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "findCorrectionalStatusTermForOffenderOnDate";
	
	private static final String FIND_BY_OFFENDER_BETWEEN_DATES_QUERY_NAME
		= "countCorrectionalStatusTermsForOffenderBetweenDates";
	
	private static final String
	FIND_BY_OFFENDER_BETWEEN_DATES_EXCLUDING_QUERY_NAME
		= "countCorrectionalStatusTermsForOffenderBetweenDatesExcluding";
	
	private static final String FIND_FOR_OFFENDER_WITH_END_DATE_QUERY_NAME
		= "findCorrectionalStatusTermForOffenderWithEndDate";
	
	private static final String FIND_FOR_OFFENDER_WITH_START_DATE_QUERY_NAME
		= "findCorrectionalStatusTermForOffenderWithStartDate";
	
	private static final String FIND_QUERY_NAME = "findCorrectionalStatusTerm";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findCorrectionalStatusTermExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String DATE_PARAM_NAME = "date";
	
	private static final String START_DATE_PARAM_NAME = "startDate";

	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String EXCLUDE_CORRECTIONAL_STATUS_TERMS_PARAM_NAME
		= "excludeCorrectionalStatusTerms";

	private static final String CORRECTIONAL_STATUS_PARAM_NAME
		= "correctionalStatus";

	private static final String
	EXCLUDED_CORRECTIONAL_STATUS_TERMS_PARAM_NAME
		= "excludedCorrectionalStatusTerms";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * placement terms with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CorrectionalStatusTermDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CorrectionalStatusTerm> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CorrectionalStatusTerm> terms = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return terms;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatusTerm findForOffenderOnDate(
			final Offender offender, final Date date) {
		CorrectionalStatusTerm term = (CorrectionalStatusTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date).uniqueResult();
		return term;
	}

	/** {@inheritDoc} */
	@Override
	public long countForOffenderBetweenDates(final Offender offender,
			final Date startDate, final Date endDate) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_BETWEEN_DATES_QUERY_NAME)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public long countForOffenderBetweenDatesExcluding(final Offender offender,
			final Date startDate, final Date endDate,
			final CorrectionalStatusTerm... excludeCorrectionalStatusTerms) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_BETWEEN_DATES_EXCLUDING_QUERY_NAME)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(EXCLUDE_CORRECTIONAL_STATUS_TERMS_PARAM_NAME,
						excludeCorrectionalStatusTerms)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatusTerm findForOffenderWithEndDate(
			final Offender offender, final Date endDate) {
		CorrectionalStatusTerm result = (CorrectionalStatusTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_WITH_END_DATE_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatusTerm findForOffenderWithStartDate(
			final Offender offender, final Date startDate) {
		CorrectionalStatusTerm result = (CorrectionalStatusTerm)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_FOR_OFFENDER_WITH_START_DATE_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatusTerm find(final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final Date startDate, final Date endDate) {
		CorrectionalStatusTerm result = (CorrectionalStatusTerm)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
							correctionalStatus)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatusTerm findExcluding(final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final Date startDate, final Date endDate,
			final CorrectionalStatusTerm... excludedCorrectionalStatusTerms) {
		CorrectionalStatusTerm result = (CorrectionalStatusTerm)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
							correctionalStatus)
					.setTimestamp(START_DATE_PARAM_NAME, startDate)
					.setTimestamp(END_DATE_PARAM_NAME, endDate)
					.setParameterList(
							EXCLUDED_CORRECTIONAL_STATUS_TERMS_PARAM_NAME,
							excludedCorrectionalStatusTerms).uniqueResult();
		return result;
	}
}