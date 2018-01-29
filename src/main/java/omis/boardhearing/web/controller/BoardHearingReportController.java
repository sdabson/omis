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
package omis.boardhearing.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import omis.beans.factory.PropertyEditorFactory;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.report.BoardHearingSummary;
import omis.boardhearing.report.BoardHearingSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/**
 * Board Hearing Report Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 2, 2018)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/boardHearing")
@PreAuthorize("hasRole('USER')")
public class BoardHearingReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME = "/boardHearing/list";
	
	private static final String BOARD_HEARINGS_ACTION_MENU_VIEW_NAME =
			"/boardHearing/includes/boardHearingsActionMenu";
	
	private static final String BOARD_HEARINGS_ROW_ACTION_MENU_VIEW_NAME = 
			"/boardHearing/includes/boardHearingsRowActionMenu";
	
	/* Model Keys */
	
	private static final String BOARD_HEARING_SUMMARIES_MODEL_KEY =
			"boardHearingSummaries";
	
	private static final String BOARD_HEARING_MODEL_KEY = "boardHearing";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("boardHearingPropertyEditorFactory")
	private PropertyEditorFactory boardHearingPropertyEditorFactory;
	
	/* Controller Model Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("boardHearingSummaryReportService")
	private BoardHearingSummaryReportService boardHearingSummaryReportService;
	
	/**
	 * Returns the Model And View for the Board Hearing list screen.
	 * 
	 * @param offender - Offender
	 * @return Model And View for the Board Hearing list screen.
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('BOARD_HEARING_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		List<BoardHearingSummary> summaries =
				this.boardHearingSummaryReportService
				.findBoardHearingSummariesByOffender(offender);
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(BOARD_HEARING_SUMMARIES_MODEL_KEY, summaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Action menus. */
	
	/**
	 * Returns the Model And View for the Board Hearings action menu.
	 * 
	 * @param offender - Offender
	 * @return Model And View for the Board Hearings action menu.
	 */
	@RequestMapping(value = "/boardHearingsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayBoardHearingsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				BOARD_HEARINGS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for a Board Hearings Row action menu.
	 * 
	 * @param boardHearing - Board Hearing
	 * @return Model and View for a Board Hearings Row action menu.
	 */
	@RequestMapping(value = "/boardHearingsRowActionMenu.html", 
		method = RequestMethod.GET)
	public ModelAndView displayParoleBoardConditionsRowActionMenu(
			@RequestParam(value = "boardHearing", required = true) 
				final BoardHearing boardHearing) {
		final ModelMap map = new ModelMap();
		map.put(BOARD_HEARING_MODEL_KEY, boardHearing);
		return new ModelAndView(
				BOARD_HEARINGS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(BoardHearing.class,
				this.boardHearingPropertyEditorFactory.createPropertyEditor());
	}
}
