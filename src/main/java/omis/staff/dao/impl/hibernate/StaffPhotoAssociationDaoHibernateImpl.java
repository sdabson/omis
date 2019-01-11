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

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.staff.dao.StaffPhotoAssociationDao;
import omis.staff.domain.StaffPhotoAssociation;

/**
 * Hibernate implementation of data access object for staff photo associations.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 9, 2018)
 * @since OMIS 3.0
 */
public class StaffPhotoAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<StaffPhotoAssociation>
		implements StaffPhotoAssociationDao {
	
	/* Query names. */
	
	private String FIND_BY_STAFF_MEMBER_QUERY_NAME
		= "findStaffPhotoAssociationsByStaffMember";
	
	private String COUNT_BY_STAFF_MEMBER_QUERY_NAME
		= "countStaffPhotoAssociationsByStaffMember";
	
	/* Parameter names. */
	
	private String STAFF_MEMBER_PARAM_NAME = "staffMember";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for staff
	 * photo associations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StaffPhotoAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<StaffPhotoAssociation>
	findByStaffMember(final Person staffMember) {
		@SuppressWarnings("unchecked")
		List<StaffPhotoAssociation> associations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_STAFF_MEMBER_QUERY_NAME)
				.setParameter(STAFF_MEMBER_PARAM_NAME, staffMember)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public long countByStaffMember(final Person staffMember) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_STAFF_MEMBER_QUERY_NAME)
				.setParameter(STAFF_MEMBER_PARAM_NAME, staffMember)
				.uniqueResult();
		return count;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffPhotoAssociation> findAll() {
		throw new UnsupportedOperationException(
				"Finding all staff photo associations not supported");
	}
}