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
package omis.supervision.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervision.dao.PlacementTermNoteDao;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermNote;

/**
 * Hibernate implementation of data access object for placement term notes.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public class PlacementTermNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<PlacementTermNote>
		implements PlacementTermNoteDao {

	/* Queries. */
	
	private static final String FIND_BY_PLACEMENT_TERM_QUERY_NAME
		= "findPlacementTermNotesByPlacementTerm";
	private static final String FIND_QUERY_NAME = "findPlacementTermNote";
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findPlacementTermNoteExcluding";
	private static final String DELETE_BY_PLACEMENT_TERM_QUERY_NAME
	= "deletePlacementTermNotesByPlacementTerm";
	
	/* Parameter names. */
	
	private static final String PLACEMENT_TERM_PARAM_NAME = "placementTerm";
	private static final String VALUE_PARAM_NAME = "value";
	private static final String DATE_PARAM_NAME = "date";
	private static final String EXCLUDED_NOTES_PARAM_NAME = "excludedNotes";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for placement
	 * term notes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PlacementTermNoteDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public PlacementTermNote find(
			final PlacementTerm placementTerm, final Date date,
			final String value) {
		PlacementTermNote note = (PlacementTermNote) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(PLACEMENT_TERM_PARAM_NAME, placementTerm)
				.setParameter(VALUE_PARAM_NAME, value)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTermNote findExcluding(
			final PlacementTerm placementTerm,
			final Date date, final String value,
			final PlacementTermNote... excludedNotes) {
		PlacementTermNote note = (PlacementTermNote) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(PLACEMENT_TERM_PARAM_NAME, placementTerm)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameterList(EXCLUDED_NOTES_PARAM_NAME, excludedNotes)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermNote> findByPlacementTerm(
			final PlacementTerm placementTerm) {
		@SuppressWarnings("unchecked")
		List<PlacementTermNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_PLACEMENT_TERM_QUERY_NAME)
				.setParameter(PLACEMENT_TERM_PARAM_NAME, placementTerm)
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermNote> findAll() {
		throw new UnsupportedOperationException(
				"Finding all placement notes not supported");
	}

	/** {@inheritDoc} */
	@Override
	public int removeByPlacementTerm(PlacementTerm placementTerm) {
		return this.getSessionFactory().getCurrentSession()
				.getNamedQuery(DELETE_BY_PLACEMENT_TERM_QUERY_NAME)
				.setParameter(PLACEMENT_TERM_PARAM_NAME, placementTerm)
				.executeUpdate();
	}
}