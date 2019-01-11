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

import org.hibernate.SessionFactory;

import omis.assessment.dao.RatingRankDao;
import omis.assessment.domain.RatingRank;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the rating rank data access object.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Aug 15, 2018)
 * @since OMIS 3.0
 */
public class RatingRankDaoHibernateImpl 
		extends GenericHibernateDaoImpl<RatingRank> implements RatingRankDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findRatingRank";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findRatingRankExcluding";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_RATING_CATEGORY_PARAM_NAME = 
			"excludedRatingRank";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * rating rank.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RatingRankDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public RatingRank find(final String name) {
		RatingRank ratingRank = (RatingRank) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return ratingRank;
	}

	/** {@inheritDoc} */
	@Override
	public RatingRank findExcluding(final String name,
			
			final RatingRank excludedRatingRank) {
		RatingRank ratingRank = (RatingRank) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_RATING_CATEGORY_PARAM_NAME, 
						excludedRatingRank)
				.uniqueResult();
		return ratingRank;
	}
}