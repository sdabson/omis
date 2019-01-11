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

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Provider assignment data access object hibernate implementation.
 *
 * @author Joel Norris
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public class ProviderAssignmentDaoHibernateImpl
	extends GenericHibernateDaoImpl<ProviderAssignment>
	implements ProviderAssignmentDao {

	/* Query names. */

	private static final String FIND_PROVIDER_ASSIGNMENT_QUERY_NAME =
			"findProviderAssignment";

	private static final String COUNT_BY_DATE_RANGE_QUERY_NAME =
			"countProviderAssignmentsInDateRange";
	
	private static final String COUNT_BY_DATE_RANGE_EXCLUDING_QUERY_NAME =
			"countProviderAssignmentsInDateRangeExcluding";

	private static final String
	FIND_PROVIDER_ASSIGNMENTS_BY_FACILITY_QUERY_NAME =
			"findProviderAssignmentsByFacility";

	private static final String
	FIND_PROVIDER_ASSIGNMENTS_BY_INTERNAL_REFERRAL_QUERY_NAME =
		"findProviderAssignmentsByInternalReferral";
	
	private static final String
	FIND_PROVIDER_ASSIGNMENT_EXCLUDING_QUERY_NAME =
		"findProviderAssignmentExcluding";
	
	private static final String
	FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_BY_FACILITY_QUERY_NAME
		= "findExternalProviderAssignmentsByFacility";

	private static final String
	FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_BY_MEDICAL_FACILITY_QUERY_NAME
		= "findExternalProviderAssignmentsByMedicalFacility";
	
	private static final String
FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_FOR_FACILITY_BY_MEDICAL_FACILITY_QUERY_NAME
		= "findExternalProviderAssignmentsForFacilityByMedicalFacility";
	
	/* Parameter names. */

	private static final String PROVIDER_PARAMETER_NAME = "provider";

	private static final String FACILITY_PARAMETER_NAME = "facility";
	
	private static final String START_DATE_PARAMETER_NAME = "startDate";

	private static final String END_DATE_PARAMETER_NAME = "endDate";
	
	private static final String TITLE_PARAMETER_NAME = "title";

	private static final String DATE_PARAMETER_NAME = "date";
	
	private static final String PROIVDER_ASSIGNMENT_PARAMETER_NAME
		= "providerAssignment";

	private static final String MEDICAL_FACILITY_PARAMETER_NAME
		= "medicalFacility";

	private static final String
	FIND_INTERNAL_PROVIDER_ASSIGNMENTS_FOR_FACILITY_ON_DATE_QUERY_NAME
		= "findInternalProviderAssignmentForFacilityOnDate";
	
	private static final String
	FIND_EXISTING_PROVIDER_ASSIGNMENTS_QUERY_NAME
		= "findExistingProviderAssignment";

	/**
	 * Instantiates an instance of provider assignment data access object
	 * hibernate implementation with the specified session factory and entity
	 * name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment find(final Person provider,
			final Facility facility, final DateRange dateRange) {
		final ProviderAssignment providerAssignment = (ProviderAssignment)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PROVIDER_ASSIGNMENT_QUERY_NAME)
				.setParameter(PROVIDER_PARAMETER_NAME, provider)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setTimestamp(START_DATE_PARAMETER_NAME,
						dateRange.getStartDate())
				.setTimestamp(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
				.uniqueResult();
		return providerAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment findExcluding(final Person provider,
			final Facility facility, final DateRange dateRange,
			final ProviderAssignment assignment) {
		final ProviderAssignment providerAssignment = (ProviderAssignment)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PROVIDER_ASSIGNMENT_EXCLUDING_QUERY_NAME)
				.setParameter(PROVIDER_PARAMETER_NAME, provider)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setTimestamp(START_DATE_PARAMETER_NAME,
						dateRange.getStartDate())
				.setTimestamp(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
				.setParameter(PROIVDER_ASSIGNMENT_PARAMETER_NAME, assignment)
				.uniqueResult();
		return providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public Long countByDateRange(final Person provider,
			final DateRange dateRange, final Facility facility) {
		final Long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_DATE_RANGE_QUERY_NAME)
				.setParameter(PROVIDER_PARAMETER_NAME, provider)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setDate(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public Long countByDateRangeExcluding(final Person provider, 
			final DateRange dateRange, final Facility facility, 
			final ProviderAssignment providerAssignment) {
		final Long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_DATE_RANGE_EXCLUDING_QUERY_NAME)
				.setParameter(PROVIDER_PARAMETER_NAME, provider)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setParameter(PROIVDER_ASSIGNMENT_PARAMETER_NAME, 
						providerAssignment)
				.setDate(START_DATE_PARAMETER_NAME, dateRange.getStartDate())
				.setDate(END_DATE_PARAMETER_NAME, dateRange.getEndDate())
				.uniqueResult();
		return count;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findByFacility(
			final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		final
		List<ProviderAssignment> providerAssignments = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PROVIDER_ASSIGNMENTS_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.list();
		return providerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findByInternalReferral(
			final InternalReferral internalReferral) {
		@SuppressWarnings("unchecked")
		final
		List<ProviderAssignment> providerAssignments =
			(List<ProviderAssignment>) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
					FIND_PROVIDER_ASSIGNMENTS_BY_INTERNAL_REFERRAL_QUERY_NAME)
				.setParameter("internalReferral", internalReferral).list();

		return providerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findExternalByFacility(
			final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		final List<ProviderAssignment> providerAssignments
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.list();
		return providerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findExternalByMedicalFacility(
			final MedicalFacility medicalFacility, final Date date) {
		@SuppressWarnings("unchecked")
		final List<ProviderAssignment> providerAssignments
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_BY_MEDICAL_FACILITY_QUERY_NAME)
				.setParameter(MEDICAL_FACILITY_PARAMETER_NAME,
						medicalFacility)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.list();
		return providerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findExternalForFacilityByMedicalFacility(
			final Facility facility, final MedicalFacility medicalFacility,
			final Date date) {
		@SuppressWarnings("unchecked")
		final List<ProviderAssignment> providerAssignments
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
	FIND_EXTERNAL_PROVIDER_ASSIGNMENTS_FOR_FACILITY_BY_MEDICAL_FACILITY_QUERY_NAME)
				.setParameter(MEDICAL_FACILITY_PARAMETER_NAME,
						medicalFacility)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.list();
		return providerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findInternalByFacilityOnDate(
			final Facility facility, final Date date) {
		@SuppressWarnings("unchecked")
		final List<ProviderAssignment> providerAssignments
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
			FIND_INTERNAL_PROVIDER_ASSIGNMENTS_FOR_FACILITY_ON_DATE_QUERY_NAME)
				.setParameter(FACILITY_PARAMETER_NAME, facility)
				.setParameter(DATE_PARAMETER_NAME, date)
				.list();
		return providerAssignments;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment findExisting(final Person provider,
		final Facility facility, final ProviderTitle title) {
		final ProviderAssignment assignment
			= (ProviderAssignment) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_PROVIDER_ASSIGNMENTS_QUERY_NAME)
			.setParameter(FACILITY_PARAMETER_NAME, facility)
			.setParameter(TITLE_PARAMETER_NAME, title)
			.setParameter(PROVIDER_PARAMETER_NAME, provider)
			.setReadOnly(true)
			.uniqueResult();
		return assignment;
	}
}