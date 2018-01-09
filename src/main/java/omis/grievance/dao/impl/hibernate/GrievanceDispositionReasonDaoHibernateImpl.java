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
package omis.grievance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceDispositionReasonDao;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;

import org.hibernate.SessionFactory;

/**
 * Grievance disposition reason data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<GrievanceDispositionReason>
		implements GrievanceDispositionReasonDao {

	/* Query names. */
	
	private static final String FIND_REASONS_QUERY_NAME 
		= "findGrievanceDispositionReasons";
	
	private static final String FIND_REASONS_BY_STATUS_QUERY_NAME
		= "findGrievanceDispositionReasonsByStatus";
	
	private static final String FIND_QUERY_NAME
		= "findGrievanceDispositionReason";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String STATUS_PARAM_NAME = "status";
	
	/* Constructors. */
	
	/**
	 * Instantiates a grievance disposition reason data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceDispositionReasonDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionReason> findAll() {
		@SuppressWarnings("unchecked")
		List<GrievanceDispositionReason> reasons = 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_REASONS_QUERY_NAME)
			.list();
		return reasons;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionReason find(final String name) {
		GrievanceDispositionReason reason = (GrievanceDispositionReason)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionReason> findByStatus(
			final GrievanceDispositionStatus status) {
		@SuppressWarnings("unchecked")
		List<GrievanceDispositionReason> reasons
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_REASONS_BY_STATUS_QUERY_NAME)
				.setParameter(STATUS_PARAM_NAME, status)
				.list();
		return reasons;
	}
}