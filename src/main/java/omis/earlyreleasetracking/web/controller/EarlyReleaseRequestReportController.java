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
package omis.earlyreleasetracking.web.controller;

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
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.report.EarlyReleaseRequestSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/**
 * Early Release Request Report Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 12, 2019)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/earlyReleaseTracking")
@PreAuthorize("hasRole('USER')")
public class EarlyReleaseRequestReportController {
	
	private static final String LIST_VIEW_NAME = "/earlyReleaseTracking/list";
	
	private static final String EARLY_RELEASE_REQUESTS_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/earlyReleaseRequestsActionMenu";
	
	private static final String
		EARLY_RELEASE_REQUESTS_ROW_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/earlyReleaseRequestsRowActionMenu";
	
	/* Model Keys */
	
	private static final String EARLY_RELEASE_REQUEST_SUMMARIES_MODEL_KEY =
			"earlyReleaseRequestSummaries";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String EARLY_RELEASE_REQUEST_CATEGORIES_MODEL_KEY =
			"earlyReleaseRequestCategories";
	
	private static final String EARLY_RELEASE_REQUEST_MODEL_KEY =
			"earlyReleaseRequest";
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("earlyReleaseRequestPropertyEditorFactory")
	private PropertyEditorFactory earlyReleaseRequestPropertyEditorFactory;

	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Service */
	
	@Autowired
	@Qualifier("earlyReleaseRequestSummaryReportService")
	private EarlyReleaseRequestSummaryReportService
			earlyReleaseRequestSummaryReportService;
	
	/* Model Delegate */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**
	 * Default constructor for Early Release Request Report Controller.
	 */
	public EarlyReleaseRequestReportController() {
	}
	
	/**
	 * Returns the Model and View for the Early Release Request list screen.
	 * 
	 * @param offender Offender
	 * @return Model and View for the Early Release Request list screen.
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EARLY_RELEASE_TRACKING_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_SUMMARIES_MODEL_KEY,
				this.earlyReleaseRequestSummaryReportService
				.findByOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for the Early Release Requests Action Menu.
	 * 
	 * @param offender Offender
	 * @return Model and View for the Early Release Requests Action Menu.
	 */
	@RequestMapping(value = "/earlyReleaseRequestsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestsActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_CATEGORIES_MODEL_KEY,
				EarlyReleaseRequestCategory.values());
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				EARLY_RELEASE_REQUESTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for the Early Release Requests
	 * Row Action Menu.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return Model and View for the Early Release Requests
	 * Row Action Menu.
	 */
	@RequestMapping(value = "/earlyReleaseRequestsRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestsRowActionMenu(
			@RequestParam(value = "earlyReleaseRequest", required = true)
			final EarlyReleaseRequest earlyReleaseRequest) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_MODEL_KEY, earlyReleaseRequest);
		return new ModelAndView(
				EARLY_RELEASE_REQUESTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Init binder.
	 * 
	 * @param binder - web data binder.
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(EarlyReleaseRequest.class,
				this.earlyReleaseRequestPropertyEditorFactory
				.createPropertyEditor());
	}
}
