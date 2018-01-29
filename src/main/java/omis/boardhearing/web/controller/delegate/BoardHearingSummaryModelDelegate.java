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
package omis.boardhearing.web.controller.delegate;

import java.util.Map;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.report.BoardHearingSummary;
import omis.boardhearing.report.BoardHearingSummaryReportService;

/**
 * Board Hearing Summary Model Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 19, 2018)
 *@since OMIS 3.0
 *
 */
public class BoardHearingSummaryModelDelegate {
	
	private static final String SUMMARY_MODEL_KEY = "boardHearingSummary";
	
	private final BoardHearingSummaryReportService
			boardHearingSummaryReportService;

	/**
	 * @param boardHearingSummaryReportService - Board Hearing Summary
	 * Report Service
	 */
	public BoardHearingSummaryModelDelegate(
			final BoardHearingSummaryReportService
				boardHearingSummaryReportService) {
		this.boardHearingSummaryReportService =
				boardHearingSummaryReportService;
	}
	
	/**
	 * Adds the summary for the specified board hearing to the given Model Map.
	 * 
	 * @param map - Model Map
	 * @param boardHearing - Board Hearing to summarize
	 */
	public void add(final Map<String, Object> map,
			final BoardHearing boardHearing) {
		BoardHearingSummary summary = this.boardHearingSummaryReportService
				.summarize(boardHearing);
		map.put(SUMMARY_MODEL_KEY, summary);
	}
	
	
}
