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
package omis.health.dao.impl.hibernate;

import java.util.Arrays;
import java.util.Date;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.InternalReferralDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for internal referrals.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralDaoHibernateImpl
		extends GenericHibernateDaoImpl<InternalReferral>
		implements InternalReferralDao {

	// Queries
	private static final String FIND_BY_OFFENDER_APPOINTMENT_QUERY_NAME =
			"findItnernalReferralByOffenderAppointmentAssociation";

	private static final String
	FIND_BY_OFFENDER_APPOINTMENT_EXCLUDING_QUERY_NAME =
		"findInternalReferralByOffenderAppointmentAssocaitonExcluding";

	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER =
		"findInternalReferralByOffenderDateTimeProvider";

	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING =
		"findInternalReferralByOffenderDateTimeProviderExcluding";
	
	private static final String
	FIND_EXISTING_BY_OFFENDER_APPOINTMENT_ASSOCIATION =
		"findExistingInternalReferralByOffenderAppointmentAssociation";

	// Parameters
	private static final String OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME =
			"offenderAppointmentAssociation";

	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String EXCLUDE_PARAM_NAME = "exclude";

	private static final String DATE_PARAM_NAME = "date";

	private static final String START_TIME_PARAM_NAME = "startTime";

	private static final String PROVIDER_ASSIGNMENT_PARAM_NAME =
			"providerAssignment";
	
	private static final String OFFEDNER_APPOINTMENT_ASSOCIATION_PARAM_NAME
		="offenderAppointmentAssociation";

	// Constructors

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * internal referrals.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InternalReferralDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	// Other stuff

	/** {@inheritDoc} */
	@Override
	public InternalReferral find(
			final OffenderAppointmentAssociation
			offenderAppointmentAssociation) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_APPOINTMENT_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME,
						offenderAppointmentAssociation).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral find(final Offender offender, final Date date,
			 final Date startTime, final ProviderAssignment providerAssignment) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER)
				.setParameter(OFFENDER_PARAM_NAME, offender).setParameter(
						DATE_PARAM_NAME, date).setTimestamp(
								START_TIME_PARAM_NAME, startTime).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral findExcluding(
			final OffenderAppointmentAssociation offenderAppointmentAssociation,
			final InternalReferral exclude) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_APPOINTMENT_EXCLUDING_QUERY_NAME)
						.setParameter(
								OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME,
								offenderAppointmentAssociation)
								.setParameterList(EXCLUDE_PARAM_NAME,
										Arrays.asList(exclude)).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral findExcluding(final Offender offender,
			final Date date, final Date startTime,
			final ProviderAssignment providerAssignment,
			final InternalReferral internalReferral) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setParameter(DATE_PARAM_NAME, date).setTimestamp(
								START_TIME_PARAM_NAME, startTime).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).setParameterList(
												EXCLUDE_PARAM_NAME,
												Arrays.asList(internalReferral))
												.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferral findExisting(final OffenderAppointmentAssociation
		offenderAppointmentAssociation) {
		InternalReferral internalReferral
		= (InternalReferral) this.getSessionFactory()
		.getCurrentSession().getNamedQuery(FIND_EXISTING_BY_OFFENDER_APPOINTMENT_ASSOCIATION)
		.setParameter(OFFEDNER_APPOINTMENT_ASSOCIATION_PARAM_NAME,
			offenderAppointmentAssociation).setReadOnly(true)
		.uniqueResult();
		return 	internalReferral;
	}

}