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
package omis.courtdocument.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.report.CourtDocumentAssociationSummaryReportService;
import omis.courtdocument.service.CourtDocumentAssociationService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller for reporting court document associations.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 6, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/courtCase/document")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ReportCourtDocumentAssociationController {

	/* Views. */
	
	private static final String LIST_VIEW_NAME = 
			"/courtDocument/list";
	
	private static final String ACTION_MENU_VIEW_NAME = 
			"courtDocument/includes/actionMenu";
	
	private static final String COURT_DOCUMENT_ACTION_MENU_VIEW_NAME =
			"courtDocument/includes/documentActionMenu";
	
	/* Model Keys. */
	
	private static final String SUMMARIES_MODEL_KEY = 
			"courtDocumentAssociationSummaries";
	
	private static final String SUMMARIES_WITHOUT_DOCKET_MODEL_KEY = 
			"courtDocAssocSummariesWithoutDocket";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String COURT_DOCUMENT_ASSOCIATION_MODEL_KEY =
			"courtDocumentAssociation";
	
	private static final String DOCKETS_MODEL_KEY = "dockets";
	
	/* Services. */
	
	@Autowired
	private CourtDocumentAssociationSummaryReportService 
			courtDocumentAssociationSummaryReportService;
	
	@Autowired
	private CourtDocumentAssociationService courtDocumentAssociationService;
	
	/* Delegates. */
	
	@Autowired 
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property Editor Factories. */
	
	@Autowired 
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired 
	private PropertyEditorFactory courtDocumentCategoryPropertyEditorFactory;
	
	@Autowired 
	private PropertyEditorFactory 
			courtDocumentAssociationPropertyEditorFactory;

	/* Report names. */
	
	private static final String COURT_DOCUMENT_LISTING_REPORT_NAME = 
			"/Legal/CourtDocuments/Court_Document_Listing";
	
	private static final String COURT_DOCUMENT_DETAILS_REPORT_NAME = 
			"/Legal/CourtDocuments/Court_Document_Details";	
	
	/* Report parameters. */
	
	private static final String COURT_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME = 
			"OFFENDER_ID";
	
	private static final String COURT_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME = 
			"COURT_DOC_ID";	
	
	/* Report runners. */	
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/** Constructor. */
	public ReportCourtDocumentAssociationController() { 
		// Default constructor
	}

	/** 
	 * Lists court document associations by offender.
	 * 
	 * @param offender offender
	 * @return list of offenders court document associations
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap modelMap = new ModelMap();
		ModelAndView mav;
		this.offenderSummaryModelDelegate.add(modelMap, offender);
		modelMap.put(SUMMARIES_MODEL_KEY, this
				.courtDocumentAssociationSummaryReportService
				.findByOffender(offender));
		modelMap.put(SUMMARIES_WITHOUT_DOCKET_MODEL_KEY, this
				.courtDocumentAssociationSummaryReportService
				.findByOffenderWithoutDocket(offender));
		mav = new ModelAndView(LIST_VIEW_NAME, modelMap);
		return mav;
	}
	
	/** 
	 * Court document association profile action menu.
	 * 
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "courtDocumentAssociationActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true) 
				final Offender offender) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		modelMap.put(OFFENDER_MODEL_KEY, offender);
		mav = new ModelAndView(ACTION_MENU_VIEW_NAME, modelMap);
		return mav;
	}

	/** 
	 * Court document association action menu.
	 * 
	 * @param courtDocumentAssociation - court document association
	 * @return action menu
	 */
	@RequestMapping(value = "courtDocumentAssociationRowActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showDocumentActionMenu(
			@RequestParam(value = "courtDocumentAssociation", required = true)
			final CourtDocumentAssociation courtDocumentAssociation) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		modelMap.put(COURT_DOCUMENT_ASSOCIATION_MODEL_KEY, 
				courtDocumentAssociation);
		if (courtDocumentAssociation.getDocket() == null) {
			modelMap.put(DOCKETS_MODEL_KEY, this.courtDocumentAssociationService
					.findDocketsByOffender(courtDocumentAssociation.getOffender()));
		}
		mav = new ModelAndView(COURT_DOCUMENT_ACTION_MENU_VIEW_NAME, 
				modelMap);
		return mav;
	}
	
	/**
	 * Returns the report for the specified offenders associated court documents.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/courtDocumentListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCourtDocumentListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COURT_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getId()));
		byte[] doc = this.reportRunner.runReport(
				COURT_DOCUMENT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}

	/**
	 * Returns the report for the specified court document.
	 * 
	 * @param courtDocumentAssociation court document association
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/courtDocumentDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCourtDocumentDetails(@RequestParam(
			value = "courtDocumentAssociation", required = true)
			final CourtDocumentAssociation courtDocumentAssociation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COURT_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(courtDocumentAssociation.getId()));
		byte[] doc = this.reportRunner.runReport(
				COURT_DOCUMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/** Init binder. 
	 * @param binder - binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(CourtDocumentCategory.class, 
				this.courtDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(CourtDocumentAssociation.class,
				this.courtDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
	}
}