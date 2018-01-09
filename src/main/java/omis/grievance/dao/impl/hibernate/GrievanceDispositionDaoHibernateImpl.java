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
import omis.grievance.dao.GrievanceDispositionDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance disposition data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<GrievanceDisposition>	
	implements GrievanceDispositionDao {

	/* Query names. */
	private static final String FIND_BY_GRIEVANCE_QUERY_NAME
		= "findGrievanceDispositionByGrievance";
	private static final String FIND_GRIEVANCE_DISPOSITION_QUERY_NAME
		= "findGrievanceDisposition";
	private static final String FIND_EXCLUDING_GRIEVANCE_DISPOSITION_QUERY_NAME
	= "findExcludingGrievanceDisposition";

	/* Parameter names. */
	private static final String GRIEVANCE_PARAMETER_NAME = "grievance";
	private static final String GRIEVANCE_DISPOSITION_LEVEL_PARAMETER_NAME 
		= "grievanceDispositionLevel";
	private static final String EXCLUDED_GRIEVANCE_PARAM_NAME 
		= "excludedDispositions";
	
	/**
	 * Instantiates an instance of grievance disposition data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceDispositionDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceDisposition> findByGrievance(Grievance grievance){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_BY_GRIEVANCE_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance);
		@SuppressWarnings("unchecked")
		List<GrievanceDisposition> result = (List<GrievanceDisposition>)q.list();
		return result; 
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition find(Grievance grievance,
		GrievanceDispositionLevel level){
		GrievanceDisposition result = (GrievanceDisposition)this.getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_GRIEVANCE_DISPOSITION_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance)
			.setParameter(GRIEVANCE_DISPOSITION_LEVEL_PARAMETER_NAME, level)
			.uniqueResult(); 
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition findExcluding(Grievance grievance,
			GrievanceDispositionLevel level, GrievanceDisposition...
			excludedDispositions){
		GrievanceDisposition result = (GrievanceDisposition)this.getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_GRIEVANCE_DISPOSITION_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance)
			.setParameter(GRIEVANCE_DISPOSITION_LEVEL_PARAMETER_NAME, level)
			.setParameter(EXCLUDED_GRIEVANCE_PARAM_NAME , excludedDispositions)
			.uniqueResult(); 
			return result;
	}
}

