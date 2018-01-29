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
package omis.boardhearing.dao;

import java.util.Date;
import java.util.List;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingNote;
import omis.dao.GenericDao;

/**
 * Board Hearing Note Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 28, 2017)
 *@since OMIS 3.0
 *
 */
public interface BoardHearingNoteDao extends GenericDao<BoardHearingNote> {
	
	/**
	 * Returns a Board Hearing Note with the specified properties.
	 * 
	 * @param hearing - Board Hearing
	 * @param description - String Description
	 * @param date - Date
	 * @return Board Hearing Note with the specified properties.
	 */
	BoardHearingNote find(BoardHearing hearing, String description, Date date);
	
	/**
	 * Returns a Board Hearing Note with the given properties excluding the
	 * specifed Board Hearing Note.
	 * 
	 * @param hearing - Board Hearing
	 * @param description - String Description
	 * @param date - Date
	 * @param boardHearingNoteExcluded - Board Hearing Note to exclude
	 * @return Board Hearing Note with the given properties excluding the
	 * specifed Board Hearing Note.
	 */
	BoardHearingNote findExcluding(BoardHearing hearing, String description,
			Date date, BoardHearingNote boardHearingNoteExcluded);
	
	/**
	 * Returns a list of Board Hearing Notes from the specified Board Hearing.
	 * 
	 * @param hearing - Board Hearing
	 * @return List of Board Hearing Notes from the specified Board Hearing.
	 */
	List<BoardHearingNote> findByHearing(BoardHearing hearing);
	
}
