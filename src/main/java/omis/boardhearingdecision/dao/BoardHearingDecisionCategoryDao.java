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
package omis.boardhearingdecision.dao;

import java.util.List;

import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.dao.GenericDao;

/**
 * Data access object for board hearing decision category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface BoardHearingDecisionCategoryDao 
	extends GenericDao<BoardHearingDecisionCategory> {

	/**
	 * Returns the board hearing decision category with the specified name.
	 * 
	 * @param name name 
	 * @return board hearing decision category
	 */
	BoardHearingDecisionCategory find(String name);

	/**
	 * Returns the board hearing decision category with the specified name 
	 * excluding the specified board hearing decision category.
	 * 
	 * @param name name 
	 * @param excludedBoardHearingDecisionCategory board hearing decision 
	 * category
	 * @return board hearing decision category
	 */
	BoardHearingDecisionCategory findExcluding(String name, 
			BoardHearingDecisionCategory excludedBoardHearingDecisionCategory);

	/**
	 * Returns a list of board hearing decision categories with the specified 
	 * decision category.
	 * 
	 * @param decision decision category
	 * @return list of board hearing decision categories
	 */
	List<BoardHearingDecisionCategory> findByDecision(
			DecisionCategory decision);

}
