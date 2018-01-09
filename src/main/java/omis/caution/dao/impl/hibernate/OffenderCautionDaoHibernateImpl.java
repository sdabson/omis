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
package omis.caution.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.caution.dao.OffenderCautionDao;
import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for offender cautions.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Nov 18, 2013)
 * @since OMIS 3.0
 */
public class OffenderCautionDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenderCaution>
		implements OffenderCautionDao {

	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findCautionsByOffender";
	
	private static final String FIND_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "findCautionsByOffenderOnDate";
	
	private static final String FIND_QUERY_NAME = "findCaution";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findCautionExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String SOURCE_PARAM_NAME = "source";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EXCLUDED_CAUTION_PARAM_NAME = "excludedCaution";
	
	private static final String DATE_PARAM_NAME = "date";

	/* Constructor. */
	
	/**
	 * Instantiates a data access object for offender cautions with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderCautionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderCaution> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderCaution> cautions =
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return cautions;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaution> findByOffenderOnDate(
			final Offender offender, final Date date) {
		@SuppressWarnings("unchecked")
		List<OffenderCaution> cautions =
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date).list();
		return cautions;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaution find(final Offender offender,
			final DateRange dateRange, final CautionSource source,
			final CautionDescription description) {
		OffenderCaution caution = (OffenderCaution)
			this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
				.setParameter(SOURCE_PARAM_NAME, source)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		return caution;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaution findExcluding(final Offender offender,
			final DateRange dateRange, final CautionSource source,
			final CautionDescription description,
			final OffenderCaution excludedCaution) {
		OffenderCaution caution = (OffenderCaution)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
					.setParameter(OFFENDER_PARAM_NAME, offender)
					.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
					.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
					.setParameter(SOURCE_PARAM_NAME, source)
					.setParameter(DESCRIPTION_PARAM_NAME, description)
					.setParameter(EXCLUDED_CAUTION_PARAM_NAME, excludedCaution)
					.uniqueResult();
		return caution;
	}
}