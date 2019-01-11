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

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.staff.dao.StaffTitleDao;
import omis.staff.domain.StaffTitle;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 21, 2013)
 * @since OMIS 3.0
 */
public class StaffTitleDaoHibernateImpl
		extends GenericHibernateDaoImpl<StaffTitle>
		implements StaffTitleDao {

	/* Queries */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllStaffTitles";
	
	private static final String FIND_MAX_SORT_ORDER_QUERY_NAME
		= "findMaxStaffTitleSortOrder";

	private static final String FIND_QUERY_NAME = "findStaffTitle";

	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findStaffTitleExcluding";
	
	/* Param names */
	
	private static final String NAME_PARAM_NAME = "name";

	private static final String EXCLUDED_STAFF_TITLES_PARAM_NAME
		= "excludedStaffTitles";

	/* Constructor */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * staff titles with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StaffTitleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public List<StaffTitle> findAll() {
		@SuppressWarnings("unchecked")
		List<StaffTitle> titles = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return titles;
	}

	/** {@inheritDoc} */
	@Override
	public short findHighestSortOrder() {
		short result = (Short) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_MAX_SORT_ORDER_QUERY_NAME).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle find(final String name) {
		StaffTitle staffTitle = (StaffTitle) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return staffTitle;
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle findExcluding(final String name,
			final StaffTitle... excludedStaffTitles) {
		StaffTitle staffTitle = (StaffTitle) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameterList(EXCLUDED_STAFF_TITLES_PARAM_NAME,
						excludedStaffTitles)
				.uniqueResult();
		return staffTitle;
	}
}