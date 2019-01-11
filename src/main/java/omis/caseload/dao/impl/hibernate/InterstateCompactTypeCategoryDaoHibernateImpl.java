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

import omis.caseload.dao.InterstateCompactTypeCategoryDao;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for interstate compact type 
 * category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactTypeCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<InterstateCompactTypeCategory> 
		implements InterstateCompactTypeCategoryDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findInterstateCompactTypeCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findInterstateCompactTypeCategoryExcluding";
	
	private static final String FIND_ACTIVE_QUERY_NAME = 
			"findInterstateCompactTypeCategories";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String 
			EXCLUDED_INTERSTATE_COMPACT_END_REASON_CATEGORY_PARAM_NAME = 
					"excludedInterstateCompactTypeCategory";	
	
	/** 
	 * Instantiates a hibernate implementation of interstate compact 
	 * type category data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InterstateCompactTypeCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactTypeCategory find(final String name) {
		InterstateCompactTypeCategory endReason = 
				(InterstateCompactTypeCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return endReason;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactTypeCategory findExcluding(final String name,
			final InterstateCompactTypeCategory excludedType) {
		InterstateCompactTypeCategory endReason = 
				(InterstateCompactTypeCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(
						EXCLUDED_INTERSTATE_COMPACT_END_REASON_CATEGORY_PARAM_NAME, 
						excludedType)
				.uniqueResult();
		return endReason;
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactTypeCategory> findActive() {
		@SuppressWarnings("unchecked")
		List<InterstateCompactTypeCategory> categories = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACTIVE_QUERY_NAME)
				.list();
		return categories;
	}
}