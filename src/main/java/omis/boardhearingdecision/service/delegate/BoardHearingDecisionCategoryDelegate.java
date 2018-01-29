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
package omis.boardhearingdecision.service.delegate;

import java.util.List;

import omis.boardhearingdecision.dao.BoardHearingDecisionCategoryDao;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for board hearing decision category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionCategoryDelegate {

	/* Data access objects. */
	
	private final BoardHearingDecisionCategoryDao 
			boardHearingDecisionCategoryDao;

	/* Instance factories. */
	
	private final InstanceFactory<BoardHearingDecisionCategory> 
			boardHearingDecisionCategoryInstanceFactory;
	
	/**
	 * Instantiates an implementation of board hearing decision category 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param boardHearingDecisionCategoryDao board hearing decision category 
	 * data access object
	 * @param boardHearingDecisionCategoryInstanceFactory board hearing decision 
	 * category instance factory
	 */
	public BoardHearingDecisionCategoryDelegate(
			final BoardHearingDecisionCategoryDao 
					boardHearingDecisionCategoryDao,
			final InstanceFactory<BoardHearingDecisionCategory> 
			boardHearingDecisionCategoryInstanceFactory) {
		this.boardHearingDecisionCategoryDao = boardHearingDecisionCategoryDao;
		this.boardHearingDecisionCategoryInstanceFactory = 
				boardHearingDecisionCategoryInstanceFactory;
	}
	
	/**
	 * Creates a new board hearing decision category.
	 * 
	 * @param name name
	 * @param category decision category
	 * @param valid valid
	 * @return board hearing decision category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardHearingDecisionCategory create(final String name, 
			final DecisionCategory category, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.boardHearingDecisionCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Board hearing decision category already exists");
		}
		BoardHearingDecisionCategory boardHearingDecisionCategory = 
				this.boardHearingDecisionCategoryInstanceFactory.createInstance();
		populateBoardHearingDecisionCategory(boardHearingDecisionCategory, name, 
				category, valid);
		return this.boardHearingDecisionCategoryDao.makePersistent(
				boardHearingDecisionCategory);
	}
	
	/**
	 * Updates an existing board hearing decision category.
	 * 
	 * @param boardHearingDecisionCategory board hearing decision category
	 * @param name name
	 * @param category decision category
	 * @param valid valid
	 * @return board hearing decision category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardHearingDecisionCategory update(
			final BoardHearingDecisionCategory boardHearingDecisionCategory, 
			final String name, final DecisionCategory category, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.boardHearingDecisionCategoryDao.findExcluding(name, 
				boardHearingDecisionCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Board hearing decision category already exists");
		}
		populateBoardHearingDecisionCategory(boardHearingDecisionCategory, name, 
				category, valid);
		return this.boardHearingDecisionCategoryDao.makePersistent(
				boardHearingDecisionCategory);
	}

	/**
	 * Removes a board hearing decision category.
	 * 
	 * @param boardHearingDecisionCategory board hearing decision category
	 */
	public void remove(
			final BoardHearingDecisionCategory boardHearingDecisionCategory) {
		this.boardHearingDecisionCategoryDao.makeTransient(
				boardHearingDecisionCategory);
	}

	/**
	 * Returns a list of board hearing decision categories for the specified 
	 * decision category.
	 * 
	 * @param decision decision category
	 * @return list of board hearing decision categories
	 */
	public List<BoardHearingDecisionCategory> findByDecision(
			final DecisionCategory decision) {
		return this.boardHearingDecisionCategoryDao.findByDecision(decision);
	}

	// Populates a board hearing decision category
	private void populateBoardHearingDecisionCategory(
			final BoardHearingDecisionCategory boardHearingDecisionCategory,
			final String name, final DecisionCategory category, 
			final Boolean valid) {
		boardHearingDecisionCategory.setName(name);
		boardHearingDecisionCategory.setDecision(category);
		boardHearingDecisionCategory.setValid(valid);
	}
}