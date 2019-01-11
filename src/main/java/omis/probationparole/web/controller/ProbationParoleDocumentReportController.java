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
package omis.probationparole.web.controller;

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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.report.ProbationParoleDocumentAssociationSummaryReportService;

/**
 * Probation Parole Document Report Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 7, 2018)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/probationParole/document")
@PreAuthorize("hasRole('USER')")
public class ProbationParoleDocumentReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME =
			"/probationParole/document/list";
	
	private static final String
		PROBATION_PAROLE_DOCUMENTS_ACTION_MENU_VIEW_NAME =
			"/probationParole/document/includes/"
			+ "probationParoleDocumentsActionMenu";
	
	private static final String
		PROBATION_PAROLE_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME =
			"/probationParole/document/includes/"
			+ "probationParoleDocumentsRowActionMenu";
	
	/* Model Keys */
	
	private static final String PROBATION_PAROLE_DOCUMENT_SUMMARIES_MODEL_KEY =
			"probationParoleDocumentAssociationSummaries";
	
	private static final String PROBATION_PAROLE_DOCUMENT_MODEL_KEY =
			"probationParoleDocumentAssociation";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Controller Model Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("probationParoleDocumentAssociationSummaryReportService")
	private ProbationParoleDocumentAssociationSummaryReportService
				probationParoleDocumentAssociationSummaryReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("probationParoleDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
			probationParoleDocumentAssociationPropertyEditorFactory;
	
	/**
	 * Default constructor for ProbationParoleDocumentReportController.
	 */
	public ProbationParoleDocumentReportController() {
		
	}
	
	/**
	 * Returns the model and view for the Probation Parole Documents list.
	 * 
	 * @param offender - Offender
	 * @return Model and view for the Probation Parole Documents list.
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_LIST') "
			+ "or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(PROBATION_PAROLE_DOCUMENT_SUMMARIES_MODEL_KEY,
				this.probationParoleDocumentAssociationSummaryReportService
				.findByOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Action Menus */
	
	/**
	 * Returns the model and view for the Probation Parole Documents Action
	 * Menu.
	 * 
	 * @param offender - Offender
	 * @return Model and view for the Probation Parole Documents Action
	 * Menu.
	 */
	@RequestMapping(value = "/probationParoleDocumentsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProbationParoleDocumentsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				PROBATION_PAROLE_DOCUMENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the model and view for the Probation Parole Documents Row Action
	 * Menu.
	 * 
	 * @param probationParoleDocumentAssociation - Probation Parole Document
	 * Association
	 * @return Model and view for the Probation Parole Documents Row Action
	 * Menu.
	 */
	@RequestMapping(value = "/probationParoleDocumentsRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProbationParoleDocumentsRowActionMenu(
			@RequestParam(value = "probationParoleDocumentAssociation",
				required = true) final ProbationParoleDocumentAssociation
					probationParoleDocumentAssociation) {
		ModelMap map = new ModelMap();
		map.addAttribute(PROBATION_PAROLE_DOCUMENT_MODEL_KEY, 
				probationParoleDocumentAssociation);
		return new ModelAndView(
				PROBATION_PAROLE_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME, map);
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
		binder.registerCustomEditor(ProbationParoleDocumentAssociation.class,
				this.probationParoleDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
	}
}
