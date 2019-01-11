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
package omis.caseload.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.OfficerCaseAssignmentDao;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.ippo.domain.InstitutionalProbationAndParoleOffice;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Hibernate implementation of data access object for officer case assignment.
 * 
 * @author Josh Divine
 * @version 0.1.1 (Jul 31, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<OfficerCaseAssignment> 
		implements OfficerCaseAssignmentDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findOfficerCaseAssignment";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findOfficerCaseAssignmentExcluding";
	
	private static final String FIND_WITHIN_DATE_RANGE_QUERY_NAME = 
			"findOfficerCaseAssignmentWithinDateRange";
	
	private static final String FIND_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME = 
			"findOfficerCaseAssignmentWithinDateRangeExcluding";
	
	private static final String FIND_BY_USER_ACCOUNT_ON_DATE_QUERY_NAME = 
			"findOfficerCaseAssignmentsByUserAccountOnDate";
	
	private static final String FIND_BY_OFFENDER_ON_DATE_QUERY_NAME = 
			"findOfficerCaseAssignmentByOffenderOnDate";
	
	private static final String 
		FIND_COMMUNITY_SUPERVISION_AND_IPPO_OFFICES_QUERY_NAME =
			"findCommunitySupervisionAndInstitutionalProbationAndParoleOffices";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String OFFICER_PARAM_NAME = "officer";
	
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	
	private static final String EXCLUDED_OFFICER_CASE_ASSIGNMENT_PARAM_NAME 
					= "excludedOfficerCaseAssignment";	
	
	private static final String DATE_PARAMETER_NAME = "date";
	
	private static final String COMMUNITY_SUPERVISION_OFFICE_PARAM_NAME
			= "communitySupervisionOffice";
	
	private static final String IPPO_PARAM_NAME 
			= "institutionalProbationAndParoleOffice";
	
	/** 
	 * Instantiates a hibernate implementation of officer case assignment data 
	 * access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OfficerCaseAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment find(final Offender offender, 
			final UserAccount officer, final Date startDate, final Date endDate) {
		OfficerCaseAssignment officerCaseAssignment = 
				(OfficerCaseAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(OFFICER_PARAM_NAME, officer)
				.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
				.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
				.uniqueResult();
		return officerCaseAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment findExcluding(final Offender offender, 
			final UserAccount officer, final Date startDate, final Date endDate,
			final OfficerCaseAssignment excludedOfficerCaseAssignment) {
		OfficerCaseAssignment officerCaseAssignment = 
				(OfficerCaseAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(OFFICER_PARAM_NAME, officer)
				.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
				.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
				.setParameter(EXCLUDED_OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						excludedOfficerCaseAssignment)
				.uniqueResult();
		return officerCaseAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignment> findWithinDateRange(
			final Offender offender, final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<OfficerCaseAssignment> officerCaseAssignments = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_WITHIN_DATE_RANGE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
				.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
				.list();
		return officerCaseAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignment> findWithinDateRangeExcluding(
			final Offender offender, final Date startDate, final Date endDate,
			final OfficerCaseAssignment excludedOfficerCaseAssignment) {
		@SuppressWarnings("unchecked")
		List<OfficerCaseAssignment> officerCaseAssignments = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
				.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
				.setParameter(EXCLUDED_OFFICER_CASE_ASSIGNMENT_PARAM_NAME, 
						excludedOfficerCaseAssignment)
				.list();
		return officerCaseAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignment> findByUserAccountOnDate(
			final UserAccount userAccount, final Date date) {
		@SuppressWarnings("unchecked")
		List<OfficerCaseAssignment> officerCaseAssignments = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_USER_ACCOUNT_ON_DATE_QUERY_NAME)
				.setParameter(OFFICER_PARAM_NAME, userAccount)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.list();
		return officerCaseAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment findByOffenderOnDate(final Offender offender, 
			final Date date) {
		OfficerCaseAssignment caseAssignment = (OfficerCaseAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAMETER_NAME, date)
				.uniqueResult();
		return caseAssignment;
	}
	
	/** {@inheritDoc] */
	@Override
	public List<Location> 
		findCommunitySupervisionAndInstitutionalProbationAndParoleOffices(
				final CommunitySupervisionOffice comOffice, 
				final InstitutionalProbationAndParoleOffice ippoOffice) {
		@SuppressWarnings("unchecked")
		List<Location> offices = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
					FIND_COMMUNITY_SUPERVISION_AND_IPPO_OFFICES_QUERY_NAME)
				.setParameter(COMMUNITY_SUPERVISION_OFFICE_PARAM_NAME, comOffice)
				.setParameter(IPPO_PARAM_NAME, ippoOffice)
				.list();
		return offices;
	}
}