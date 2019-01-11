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
package omis.staff.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.person.domain.Person;
import omis.staff.dao.StaffAssignmentDao;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Hibernate implementation of data access object for staff assignments.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Josh Divine
 * @author Yidong Li
 * @version 0.1.1 (Sept. 20, 2018)
 * @since OMIS 3.0
 */
public class StaffAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<StaffAssignment>
		implements StaffAssignmentDao {

	/* Queries. */
	
	private static final String
		FIND_BY_SUPERVISORY_ORGANIZATION_ON_DATE_QUERY_NAME = 
			"findStaffAssignmentsBySupervisoryOrganizationOnDate";
	
	private static final String FIND_ON_DATE_QUERY_NAME = 
			"findStaffAssignmentsOnDate";

	private static final String FIND_STAFF_MEMBERS_ON_DATE_QUERY_NAME = 
			"findStaffMembersUsingAssignmentsOnDate";

	private static final String
		FIND_SUPERVISORY_STAFF_MEMBERS_ON_DATE_QUERY_NAME = 
			"findSupervisoryStaffMembersUsingAssignmentsOnDate";
	
	private static final String FIND_ALL_QUERY_NAME = "findAllStaffAssignments";
	
	private static final String FIND_ASSIGNMENT_ON_DATE_QUERY_NAME =
			"findStaffAssignmentOnDate";
	
	private static final String FIND_STAFF_ASSIGNMENT_QUERY_NAME = 
			"findStaffAssignment";
	
	private static final String FIND_STAFF_ASSIGNMENT_EXCLUDING_QUERY_NAME = 
			"findStaffAssignmentExcluding";
	
	private static final String
		FIND_STAFF_ASSIGNMENTS_BY_STAFF_MEMBER_QUERY_NAME = 
		"findStaffAssignmentsByStaffMember";
	
	/* Parameters. */
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME = 
			"supervisoryOrganization";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String EXCLUDED_ASSIGNMENT_PARAM_NAME = 
			"excludedAssignment";
	
	private static final String STAFF_MEMBER_PARAM_NAME = "staffMember";
	
	/* Property names. */
	
	private static final String LOCATION_PROPERTY_NAME = "location";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * staff assignments with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StaffAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffAssignment> findBySupervisoryOrganizationOnDate(
			final SupervisoryOrganization supervisoryOrganization,
			final Date date) {
		@SuppressWarnings("unchecked")
		List<StaffAssignment> assignments = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_SUPERVISORY_ORGANIZATION_ON_DATE_QUERY_NAME)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
						supervisoryOrganization)
				.setDate(DATE_PARAM_NAME, date).list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffAssignment> findOnDate(final Date date) {
		@SuppressWarnings("unchecked")
		List<StaffAssignment> assignments = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ON_DATE_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date).list();
		return assignments;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffAssignment> findAll() {
		@SuppressWarnings("unchecked")
		List<StaffAssignment> assignments = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<Person> findStaffMembersOnDate(final Date date) {
		@SuppressWarnings("unchecked")
		List<Person> staffMembers = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_STAFF_MEMBERS_ON_DATE_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date).list();
		return staffMembers;
	}

	/** {@inheritDoc} */
	@Override
	public List<Person> findSupervisoryStaffMembersOnDate(final Date date) {
		@SuppressWarnings("unchecked")
		List<Person> supervisoryStaffMembers = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_SUPERVISORY_STAFF_MEMBERS_ON_DATE_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date).list();
		return supervisoryStaffMembers;
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment findOnDate(final Date date, final Person person,
			final SupervisoryOrganization organization) {
		StaffAssignment assignment = (StaffAssignment) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ASSIGNMENT_ON_DATE_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME, organization)
				.uniqueResult();
		return assignment;
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment find(final Person staffMember, 
			final SupervisoryOrganization supervisoryOrganization, 
			final Location location, final Date startDate, final Date endDate) {
		StaffAssignment staffAssignment = (StaffAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_STAFF_ASSIGNMENT_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, staffMember)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME, 
						supervisoryOrganization)
				.setParameter(LOCATION_PARAM_NAME, location, 
						this.getEntityPropertyType(LOCATION_PROPERTY_NAME))
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.uniqueResult();
		return staffAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment findExcluding(final Person staffMember, 
			final SupervisoryOrganization supervisoryOrganization,
			final Location location, final Date startDate, final Date endDate, 
			final StaffAssignment excludedStaffAssignment) {
		StaffAssignment staffAssignment = (StaffAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_STAFF_ASSIGNMENT_EXCLUDING_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, staffMember)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME, 
						supervisoryOrganization)
				.setParameter(LOCATION_PARAM_NAME, location, 
						this.getEntityPropertyType(LOCATION_PROPERTY_NAME))
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.setParameter(EXCLUDED_ASSIGNMENT_PARAM_NAME, 
						excludedStaffAssignment)
				.uniqueResult();
		return staffAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffAssignment> findByStaffMember(
		final Person staffMember) {
		@SuppressWarnings("unchecked")
		List<StaffAssignment> staffAssignments
		= (List<StaffAssignment>) this.getSessionFactory()
		.getCurrentSession().getNamedQuery(
		FIND_STAFF_ASSIGNMENTS_BY_STAFF_MEMBER_QUERY_NAME)
		.setParameter(STAFF_MEMBER_PARAM_NAME, staffMember)
		.list();
		return staffAssignments;
	}
}