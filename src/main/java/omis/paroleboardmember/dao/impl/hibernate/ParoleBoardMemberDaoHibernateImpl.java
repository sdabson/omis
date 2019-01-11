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
package omis.paroleboardmember.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardmember.dao.ParoleBoardMemberDao;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Hibernate implementation of the parole board member data access object.
 *
 * @author Josh Divine
 * @version 0.1.2 (Nov 28, 2018)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ParoleBoardMember>
		implements ParoleBoardMemberDao  {


	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findParoleBoardMember";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findParoleBoardMemberExcluding";
	
	private static final String FIND_BY_DATE_QUERY_NAME = 
			"findParoleBoardMemberByDate";
	
	private static final String FIND_BY_STAFF_WITHIN_DATE_RANGE_QUERY_NAME = 
			"findParoleBoardMemberByStaffAssignmentWithinDateRange";
	
	private static final String FIND_BY_STAFF_WITHIN_DATE_RANGE_EXCL_QUERY_NAME = 
			"findParoleBoardMemberByStaffAssignmentWithinDateRangeExcluding";
	
	/* Parameters. */
	
	private static final String STAFF_ASSIGNMENT_PARAM_NAME = "staffAssignment";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String EXCLUDED_MEMBER_PARAM_NAME = 
			"excludedParoleBoardMember";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  parole board member.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleBoardMemberDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember find(final StaffAssignment staffAssignment, 
			final Date startDate) {
		ParoleBoardMember paroleBoardMember = (ParoleBoardMember) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.uniqueResult();
		return paroleBoardMember;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember findExcluding(
			final StaffAssignment staffAssignment, final Date startDate,
			final ParoleBoardMember excludedParoleBoardMember) {
		ParoleBoardMember paroleBoardMember = (ParoleBoardMember) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(EXCLUDED_MEMBER_PARAM_NAME, 
						excludedParoleBoardMember)
				.uniqueResult();
		return paroleBoardMember;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMember> findBoardMembersByDate(
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMember> members = (List<ParoleBoardMember>) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_DATE_QUERY_NAME)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return members;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMember> findExistingWithinDateRange(
			final StaffAssignment staffAssignment, final Date startDate, 
			final Date endDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMember> members = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_STAFF_WITHIN_DATE_RANGE_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.list();
		return members;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMember> findExistingWithinDateRangeExcluding(
			final StaffAssignment staffAssignment, final Date startDate, 
			final Date endDate, 
			final ParoleBoardMember excludedParoleBoardMember) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMember> members = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_STAFF_WITHIN_DATE_RANGE_EXCL_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(EXCLUDED_MEMBER_PARAM_NAME, 
						excludedParoleBoardMember)
				.list();
		return members;
	}

}