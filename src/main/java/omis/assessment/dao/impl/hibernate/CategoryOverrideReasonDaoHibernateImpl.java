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
package omis.assessment.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.assessment.dao.CategoryOverrideReasonDao;
import omis.assessment.domain.CategoryOverrideReason;
import omis.assessment.domain.RatingCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the category override reason data access object.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Jan 31, 2019)
 * @since OMIS 3.0
 */
public class CategoryOverrideReasonDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CategoryOverrideReason>
		implements CategoryOverrideReasonDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findCategoryOverrideReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findCategoryOverrideReasonExcluding";
	
	private static final String FIND_ALL_QUERY_NAME =
			"findAllCategoryOverrideReasons";
	
	private static final String FIND_BY_RATING_CATEGORY_QUERY_NAME =
			"findCategoryOverrideReasonsByRatingCategory";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String RATING_CATEGORY_PARAM_NAME = "ratingCategory";
	
	private static final String EXCLUDED_CATEGORY_OVERRIDE_REASON_PARAM_NAME = 
					"excludedCategoryOverrideReason";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * category override reason.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CategoryOverrideReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CategoryOverrideReason find(final String name, 
			final RatingCategory ratingCategory) {
		CategoryOverrideReason categoryOverrideReason = (CategoryOverrideReason)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.uniqueResult();
		return categoryOverrideReason;
	}

	/** {@inheritDoc} */
	@Override
	public CategoryOverrideReason findExcluding(final String name, 
			final RatingCategory ratingCategory,
			final CategoryOverrideReason excludedCategoryOverrideReason) {
		CategoryOverrideReason categoryOverrideReason = (CategoryOverrideReason)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.setParameter(EXCLUDED_CATEGORY_OVERRIDE_REASON_PARAM_NAME, 
						excludedCategoryOverrideReason)
				.uniqueResult();
		return categoryOverrideReason;
	}

	/** {@inheritDoc} */
	@Override
	public List<CategoryOverrideReason> findAllReasons() {
		@SuppressWarnings("unchecked")
		List<CategoryOverrideReason> reasons =
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_QUERY_NAME)
			.list();
		
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public List<CategoryOverrideReason> findByRatingCategory(
			final RatingCategory ratingCategory) {
		@SuppressWarnings("unchecked")
		List<CategoryOverrideReason> reasons =
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_RATING_CATEGORY_QUERY_NAME)
			.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
			.list();
		
		return reasons;
	}
}