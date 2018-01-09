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
import omis.grievance.dao.GrievanceComplaintCategoryDao;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceSubject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance complaint category data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceComplaintCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<GrievanceComplaintCategory>	
	implements GrievanceComplaintCategoryDao {

	/* Query names. */
	
	private static final String FIND_BY_SUBJECT_QUERY_NAME
 		= "findGrievanceComplaintCategoriesBySubject";

	private static final String FIND_QUERY_NAME
		= "findGrievanceComplaintCategory";
	
	/* Parameter names. */
	
	private static final String SUBJECT_PARAMETER_NAME = "subject";

	private static final String NAME_PARAM_NAME = "name";

	/**
	 * Instantiates an instance of grievance complaint category data access 
	 * object with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceComplaintCategoryDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceComplaintCategory> findBySubject(final GrievanceSubject 
		subject){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_BY_SUBJECT_QUERY_NAME)
			.setParameter(SUBJECT_PARAMETER_NAME, subject);
		@SuppressWarnings("unchecked")
		List<GrievanceComplaintCategory> result 
			= (List<GrievanceComplaintCategory>)q.list();
		return result; 
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceComplaintCategory find(final String name) {
		GrievanceComplaintCategory complaintCategory
				= (GrievanceComplaintCategory) this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return complaintCategory;
	}
}