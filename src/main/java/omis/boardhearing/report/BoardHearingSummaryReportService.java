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
package omis.boardhearing.report;

import java.util.List;
import omis.boardhearing.domain.BoardHearing;
import omis.offender.domain.Offender;

/**
 * Board Hearing Summary Report Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 29, 2017)
 *@since OMIS 3.0
 *
 */
public interface BoardHearingSummaryReportService {
	
	/**
	 * Returns a list of Board Hearing Summaries by the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Board Hearing Summaries for the specified Offender
	 */
	List<BoardHearingSummary> findBoardHearingSummariesByOffender(
			Offender offender);

	/**
	 * Returns a summary of the specified Board Hearing.
	 * 
	 * @param boardHearing - Board Hearing to summarize.
	 * @return BoardHearingSummary - summary of the specified Board Hearing.
	 */
	BoardHearingSummary summarize(BoardHearing boardHearing);
	
}
