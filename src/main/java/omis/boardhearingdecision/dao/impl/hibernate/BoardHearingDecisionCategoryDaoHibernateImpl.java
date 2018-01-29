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
package omis.boardhearingdecision.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.boardhearingdecision.dao.BoardHearingDecisionCategoryDao;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the board hearing decision category data access 
 * object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionCategoryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<BoardHearingDecisionCategory>
		implements BoardHearingDecisionCategoryDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findBoardHearingDecisionCategory";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findBoardHearingDecisionCategoryExcluding";
	
	private static final String FIND_BY_DECISION_QUERY_NAME = 
			"findBoardHearingDecisionCategoryByDecision";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String 
			EXCLUDED_BOARD_HEARING_DECISION_CATEGORY_PARAM_NAME = 
			"excludedBoardHearingDecisionCategory";

	private static final String DECISION_PARAM_NAME = "decision";
	
	/** Instantiates a hibernate implementation of the data access object for 
	 *  board hearing decision category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BoardHearingDecisionCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecisionCategory find(final String name) {
		BoardHearingDecisionCategory category = 
				(BoardHearingDecisionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecisionCategory findExcluding(final String name,
			final BoardHearingDecisionCategory 
					excludedBoardHearingDecisionCategory) {
		BoardHearingDecisionCategory category = 
				(BoardHearingDecisionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(
						EXCLUDED_BOARD_HEARING_DECISION_CATEGORY_PARAM_NAME, 
						excludedBoardHearingDecisionCategory)
				.uniqueResult();
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardHearingDecisionCategory> findByDecision(
			final DecisionCategory decision) {
		@SuppressWarnings("unchecked")
		List<BoardHearingDecisionCategory> categories = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DECISION_QUERY_NAME)
				.setParameter(DECISION_PARAM_NAME, decision)
				.list();
		return categories;
	}
}