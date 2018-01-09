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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceDispositionStatusDao;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionStatus;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance disposition status data access object Hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionStatusDaoHibernateImpl 
		extends GenericHibernateDaoImpl<GrievanceDispositionStatus>	
		implements GrievanceDispositionStatusDao {

	/* Query names. */
	
	private static final String FIND_BY_LEVELS_QUERY_NAME
 		= "findGrievanceDispositionStatusesByLevels";

	private static final String FIND_BY_LEVELS_OR_NULL_QUERY_NAME
		= "findGrievanceDispositionStatusesByLevelsOrNull";
	
	private static final String FIND_QUERY_NAME
		= "findGrievanceDispositionStatus";
	
	private static final String FIND_LEVELS_OR_NULL_AND_CLOSED_QUERY_NAME
		= "findGrievanceDispositionStatusesByLevelsOrNullAndClosed";

	private static final String FIND_LEVELS_AND_CLOSED_QUERY_NAME
		= "findGrievanceDispositionStatusesAndClosedDate";
	
	private static final String FIND_PENDING_FOR_LEVEL_QUERY_NAME
		= "findGrievanceDispositionStatusForLevel";
	
	/* Parameter names. */
	
	private static final String LEVELS_PARAMETER_NAME = "levels";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String LEVEL_PARAM_NAME = "level";

	private static final String CLOSED_PARAM_NAME = "closed";

	/**
	 * Instantiates grievance disposition status data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceDispositionStatusDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionStatus> findByLevels(
			final GrievanceDispositionLevel... levels) {
		
		/*
		 * Two separate queries have to be run as SQL will not return null
		 * matches in the IN clause for the LEVEL.
		 */
		Set<GrievanceDispositionLevel> nullFreeLevels
			= new HashSet<GrievanceDispositionLevel>();
		boolean nullFound = false;
		for (GrievanceDispositionLevel level : levels) {
			if (level == null) {
				nullFound = true;
			} else {
				nullFreeLevels.add(level);
			}
		}
		Query query;
		if (nullFound) {
			query = getSessionFactory().getCurrentSession() 
				.getNamedQuery(FIND_BY_LEVELS_OR_NULL_QUERY_NAME);
		} else {
			query = getSessionFactory().getCurrentSession() 
				.getNamedQuery(FIND_BY_LEVELS_QUERY_NAME);
		}
		@SuppressWarnings("unchecked")
		List<GrievanceDispositionStatus> result 
			= (List<GrievanceDispositionStatus>) query
					.setParameterList(LEVELS_PARAMETER_NAME, levels).list();
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionStatus> findByLevelsAndWhetherClosed(
			final boolean closed,
			final GrievanceDispositionLevel... levels) {

		/*
		 * Two separate queries have to be run as SQL will not return
		 * null matches in the IN clause for the LEVEL.
		 */
		Set<GrievanceDispositionLevel> nullFreeLevels
			= new HashSet<GrievanceDispositionLevel>();
		boolean nullFound = false;
		for (GrievanceDispositionLevel level : levels) {
			if (level == null) {
				nullFound = true;
			} else {
				nullFreeLevels.add(level);
			}
		}
		Query query;
		if (nullFound) {
			query = getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_LEVELS_OR_NULL_AND_CLOSED_QUERY_NAME);
		} else {
			query = getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_LEVELS_AND_CLOSED_QUERY_NAME);
		}
		@SuppressWarnings("unchecked")
		List<GrievanceDispositionStatus> result 
			= (List<GrievanceDispositionStatus>) query
					.setParameterList(LEVELS_PARAMETER_NAME, levels)
					.setBoolean(CLOSED_PARAM_NAME, closed)
					.list();
		return result; 
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionStatus find(
			final String name, final GrievanceDispositionLevel level) {
		GrievanceDispositionStatus status = (GrievanceDispositionStatus)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.setParameter(LEVEL_PARAM_NAME, level)
					.uniqueResult();
		return status;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionStatus findPendingForLevel(
			final GrievanceDispositionLevel level) {
		GrievanceDispositionStatus status = (GrievanceDispositionStatus)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(
							FIND_PENDING_FOR_LEVEL_QUERY_NAME)
					.setParameter(LEVEL_PARAM_NAME, level)
					.uniqueResult();
		return status;
	}
}