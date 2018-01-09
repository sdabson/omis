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

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceNoteDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceNote;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance note data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (May 20, 2015)
 * @since OMIS 3.0
 */
public class GrievanceNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<GrievanceNote>	
	implements GrievanceNoteDao {

	/* Query names. */
	private static final String FIND_BY_GRIEVANCE_QUERY_NAME 
		= "findGrievanceNoteByGrievance";
	private static final String FIND_QUERY_NAME	= "findNote";
	private static final String FIND_EXCLUDING_NOTE_QUERY_NAME
		= "findExcludingNote";

	/* Parameter names. */
	private static final String GRIEVANCE_PARAMETER_NAME = "grievance";
	private static final String DATE_PARAMETER_NAME = "date";
	private static final String VALUE_PARAMETER_NAME = "value";
	private static final String EXCLUDED_NOTE_PARAMETER_NAME = "excludedNotes";

	/**
	 * Instantiates an instance of grievance note data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceNoteDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceNote> findByGrievance(Grievance grievance){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_BY_GRIEVANCE_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance);
		@SuppressWarnings("unchecked")
		List<GrievanceNote> result 
			= (List<GrievanceNote>)q.list();
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceNote find(Grievance grievance, Date date, String value){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance)
			.setParameter(DATE_PARAMETER_NAME, date)
			.setParameter(VALUE_PARAMETER_NAME, value);
		GrievanceNote result 
			= (GrievanceNote)q.uniqueResult();
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceNote findExcluding(Grievance grievance, Date date, 
		String value, GrievanceNote... excludedNotes){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_NOTE_QUERY_NAME)
			.setParameter(GRIEVANCE_PARAMETER_NAME, grievance)
			.setParameter(DATE_PARAMETER_NAME, date)
			.setParameter(VALUE_PARAMETER_NAME, value)
			.setParameter(EXCLUDED_NOTE_PARAMETER_NAME, excludedNotes);
		GrievanceNote result = (GrievanceNote)q.uniqueResult();
		return result; 
	}
}

