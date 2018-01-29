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

import omis.boardhearing.domain.BoardHearing;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.dao.GenericDao;

/**
 * Data access object for board hearing decision.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface BoardHearingDecisionDao 
	extends GenericDao<BoardHearingDecision> {

	/**
	 * Returns the board hearing decision with the specified hearing.
	 * 
	 * @param hearing board hearing
	 * @return board hearing decision
	 */
	BoardHearingDecision find(BoardHearing hearing);

	/**
	 * returns the board hearing decision with the specified hearing excluding 
	 * the specified board hearing decision.
	 * 
	 * @param hearing board hearing
	 * @param excludedBoardDecision board hearing decision
	 * @return board hearing decision
	 */
	BoardHearingDecision findExcluding(BoardHearing hearing, 
			BoardHearingDecision excludedBoardDecision);
}
