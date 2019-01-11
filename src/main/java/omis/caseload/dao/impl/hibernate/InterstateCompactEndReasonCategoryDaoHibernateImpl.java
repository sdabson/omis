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
package omis.caseload.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.InterstateCompactEndReasonCategoryDao;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for interstate compact end 
 * reason category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactEndReasonCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<InterstateCompactEndReasonCategory> 
		implements InterstateCompactEndReasonCategoryDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findInterstateCompactEndReasonCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findInterstateCompactEndReasonCategoryExcluding";
	
	private static final String FIND_ACTIVE_QUERY_NAME = 
			"findInterstateCompactEndReasonCategories";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String 
			EXCLUDED_INTERSTATE_COMPACT_END_REASON_CATEGORY_PARAM_NAME = 
					"excludedInterstateCompactEndReasonCategory";	
	
	/** 
	 * Instantiates a hibernate implementation of interstate compact 
	 * end reason category data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InterstateCompactEndReasonCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactEndReasonCategory find(final String name) {
		InterstateCompactEndReasonCategory endReason = 
				(InterstateCompactEndReasonCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return endReason;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactEndReasonCategory findExcluding(final String name,
			final InterstateCompactEndReasonCategory excludedEndReason) {
		InterstateCompactEndReasonCategory endReason = 
				(InterstateCompactEndReasonCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(
						EXCLUDED_INTERSTATE_COMPACT_END_REASON_CATEGORY_PARAM_NAME, 
						excludedEndReason)
				.uniqueResult();
		return endReason;
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactEndReasonCategory> findActive() {
		@SuppressWarnings("unchecked")
		List<InterstateCompactEndReasonCategory> categories = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACTIVE_QUERY_NAME)
				.list();
		return categories;
	}
}