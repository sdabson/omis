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
package omis.response.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseLevel;
import omis.response.report.ResponseSummary;
import omis.response.report.ResponseSummaryReportService;
import omis.response.service.ResponseService;
import omis.response.web.form.ResponseSearchForm;

/**
 * Controller for reporting responses.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 5, 2019)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/response")
public class ReportResponseController {

	/* View names. */
	
	private static final String VIEW_NAME = "response/list";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME = 
			"response/includes/responsesActionMenu";

	/* Model keys. */
	
	private static final String RESPONSE_SUMMARIES_MODEL_KEY = 
			"responseSummaries";
	
	private static final String RESPONSE_MODEL_KEY = "response";
	
	private static final String GRIDS_MODEL_KEY = "grids";
	
	private static final String RESPONSE_CATEGORIES_MODEL_KEY = 
			"responseCategories";
	
	private static final String RESPONSE_LEVELS_MODEL_KEY = "responseLevels";
	
	private static final String RESPONSE_SEARCH_FORM_MODEL_KEY = 
			"responseSearchForm";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("responsePropertyEditorFactory")
	private PropertyEditorFactory responsePropertyEditorFactory;
	
	@Autowired
	@Qualifier("gridPropertyEditorFactory")
	private PropertyEditorFactory gridPropertyEditorFactory;
	
	@Autowired
	@Qualifier("responseLevelPropertyEditorFactory")
	private PropertyEditorFactory responseLevelPropertyEditorFactory;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("responseSummaryReportService")
	private ResponseSummaryReportService responseSummaryReportService;
	
	/* Services. */
	
	@Autowired
	@Qualifier("responseService")
	private ResponseService responseService;
	
	/* Constructors. */
	
	/** 
	 * Instantiates controller for responses.
	 */
	public ReportResponseController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen that lists responses.
	 * 
	 * @return screen that lists responses
	 */
	@PreAuthorize("hasRole('RESPONSE_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		return prepareMav(new ResponseSearchForm(), 
				this.responseSummaryReportService.findResponseSummaries());
	}
	
	/**
	 * Updates the screen that lists responses.
	 * 
	 * @param form response search form
	 * @param result binding result
	 * @return model and view for responses list screen
	 */
	@PreAuthorize("hasRole('RESPONSE_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	public ModelAndView updateList(final ResponseSearchForm form,
				final BindingResult result) {
		return prepareMav(form, this.responseSummaryReportService
				.findResponseSummaries(form.getDescription(), form.getGrid(), 
						form.getCategory(), form.getLevel()));
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for responses.
	 * 
	 * @param response parole review
	 * @return action menu for responses
	 */
	@RequestMapping(value = "/responsesActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "response", required = false)
				final Response response) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		if (response != null) {
			mav.addObject(RESPONSE_MODEL_KEY, response);
		}
		return mav;
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(
			final ResponseSearchForm responseSearchForm, 
			final List<ResponseSummary> responses) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(RESPONSE_SEARCH_FORM_MODEL_KEY, responseSearchForm);
		mav.addObject(RESPONSE_SUMMARIES_MODEL_KEY, responses);
		mav.addObject(GRIDS_MODEL_KEY, this.responseService.findGrids());
		mav.addObject(RESPONSE_CATEGORIES_MODEL_KEY, 
				this.responseService.findResponseCategories());
		mav.addObject(RESPONSE_LEVELS_MODEL_KEY, 
				this.responseService.findResponseLevels());
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Response.class,
				this.responsePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Grid.class, this.gridPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ResponseLevel.class, 
				this.responseLevelPropertyEditorFactory
				.createPropertyEditor());
	}
}