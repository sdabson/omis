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

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.probationparole.domain.ProbationParoleDocumentAssociation;
import omis.probationparole.domain.ProbationParoleDocumentCategory;
import omis.probationparole.service.ProbationParoleDocumentService;
import omis.probationparole.web.form.ProbationParoleDocumentForm;
import omis.probationparole.web.validator.ProbationParoleDocumentFormValidator;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Probation Parole Document Controller.
 * 
 *@author Annie Wahl 
 *@author Sierra Haynes
 *@version 0.1.0 (Dec 7, 2018)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/probationParole/document")
@PreAuthorize("hasRole('USER')")
public class ProbationParoleDocumentController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/probationParole/document/edit";
	
	private static final String
		PROBATION_PAROLE_DOCUMENT_ACTION_MENU_VIEW_NAME =
			"/probationParole/document/includes/"
			+ "probationParoleDocumentActionMenu";

	/* REDIRECT VIEW NAMES */
	
	private static final String LIST_REDIRECT =
			"redirect:/probationParole/document/list.html?offender=%d";
	
	/* Model Keys */
	
	private static final String FORM_MODEL_KEY = "probationParoleDocumentForm";
	
	private static final String PROBATION_PAROLE_DOCUMENT_MODEL_KEY =
			"probationParoleDocumentAssociation";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	/* Report names. */
	
	private static final String P_P_DOCUMENT_LISTING_REPORT_NAME 
		= "/CaseManagement/P_PDocuments/Probation_Parole_Document_Listing";
	private static final String P_P_DOCUMENT_DETAILS_REPORT_NAME 
		= "/CaseManagement/P_PDocuments/Probation_Parole_Document_Details";	
	
	/* Report parameter names. */
	
	private static final String P_P_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME
		= "DOC_ID";
	private static final String P_P_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME
		= "PNP_DOC_ID";	

	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"probationParoleDocumentAssociation.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.probationparole.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("probationParoleDocumentService")
	private ProbationParoleDocumentService probationParoleDocumentService;
	
	/* Property editor factories. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("probationParoleDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
			probationParoleDocumentAssociationPropertyEditorFactory;

	@Autowired
	@Qualifier("probationParoleDocumentCategoryPropertyEditorFactory")
	private PropertyEditorFactory
			probationParoleDocumentCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("documentFilenameGenerator")
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("probationParoleDocumentPersister")
	private DocumentPersister probationParoleDocumentPersister;
	
	@Autowired
	@Qualifier("probationParoleDocumentRetriever")
	private DocumentRetriever probationParoleDocumentRetriever;
	
	@Autowired
	@Qualifier("probationParoleDocumentRemover")
	private FileRemover probationParoleDocumentRemover;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;	
	
	/* Validator */
	
	@Autowired
	@Qualifier("probationParoleDocumentFormValidator")
	private ProbationParoleDocumentFormValidator
			probationParoleDocumentFormValidator;
	
	/**
	 * Default constructor for ProbationParoleDocumentController.
	 */
	public ProbationParoleDocumentController() {
	
	}
	
	/**
	 * Displays the Model and View for the Probation Parole Document
	 * create screen.
	 * 
	 * @param offender - offender
	 * @return Model and View for the Probation Parole Document
	 * create screen.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return this.prepareEditMav(offender);
	}
	
	/**
	 * Saves a Probation Parole Document Association and returns to the
	 * list screen.
	 * 
	 * @param offender - offender
	 * @param form - Probation Parole Document Form
	 * @param bindingResult - Binding Result
	 * @return Model And View for list screen on successful save, or back
	 * to create screen on form error.
	 * @throws DuplicateEntityFoundException - When a duplicate Probation Parole
	 * Document Association, Document, or Document Tag already exists.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(value = "offender", required = true)
			final Offender offender, final ProbationParoleDocumentForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.probationParoleDocumentFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return prepareEditMav(offender, form);
		} else {
			final String fileExtension = form.getFileExtension();
			this.documentFilenameGenerator
					.setExtension(fileExtension);
			final String filename =
					this.documentFilenameGenerator.generate();
			Document document = this.probationParoleDocumentService
					.createDocument(form.getDate(), filename, fileExtension,
							form.getTitle());
			this.probationParoleDocumentPersister.persist(
					document, form.getData());
			this.processDocumentTags(form.getDocumentTagItems(), document);
			
			this.probationParoleDocumentService
				.createProbationParoleDocumentAssociation(document,
						offender, form.getDate(), form.getCategory());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					offender.getId()));
		}
	}
	
	/**
	 * Displays the Model and View for the Probation Parole Document
	 * edit screen.
	 * 
	 * @param documentAssociation - Probation Parole Document Association
	 * @return Model and View for the Probation Parole Document
	 * edit screen.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_VIEW') "
			+ "or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "probationParoleDocumentAssociation",
			required = true)
				final ProbationParoleDocumentAssociation documentAssociation) {
		return this.prepareEditMav(documentAssociation);
	}
	
	/**
	 * Updates a Probation Parole Document Association and returns to the
	 * list screen.
	 * 
	 * @param documentAssociation - Probation Parole Document Association
	 * @param form - Probation Parole Document Form
	 * @param bindingResult - Binding Result
	 * @return Model And View for list screen on successful update, or back
	 * to edit screen on form error.
	 * @throws DuplicateEntityFoundException - When a duplicate Probation Parole
	 * Document Association, Document, or Document Tag already exists.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_EDIT') "
			+ "or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "probationParoleDocumentAssociation",
			required = true)
				final ProbationParoleDocumentAssociation documentAssociation,
				final ProbationParoleDocumentForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.probationParoleDocumentFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return prepareEditMav(documentAssociation, form);
		} else {
			Document document = this.probationParoleDocumentService
					.updateDocument(documentAssociation.getDocument(),
							form.getDate(), form.getTitle());
			this.processDocumentTags(form.getDocumentTagItems(), document);
			this.probationParoleDocumentService
				.updateProbationParoleDocumentAssociation(documentAssociation,
						document, documentAssociation.getOffender(), 
						form.getDate(), form.getCategory());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					documentAssociation.getOffender().getId()));
		}
	}
	
	/**
	 * Removes the specified Probation Parole Document Association and returns
	 * to the Probation Parole Document list screen.
	 * 
	 * @param documentAssociation - Probation Parole Document Association
	 * @return Returns to the Probation Parole Document list screen.
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_REMOVE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "probationParoleDocumentAssociation",
			required = true)
				final ProbationParoleDocumentAssociation documentAssociation) {
		List<DocumentTag> documentTags =
				this.probationParoleDocumentService
					.findDocumentTagsByDocument(documentAssociation
						.getDocument());
		for (DocumentTag documentTag : documentTags) {
			this.probationParoleDocumentService
				.removeDocumentTag(documentTag);
		}
		this.probationParoleDocumentService
			.removeProbationParoleDocumentAssociation(documentAssociation);
		this.probationParoleDocumentRemover.remove(documentAssociation
				.getDocument().getFilename());
		this.probationParoleDocumentService.removeDocument(
				documentAssociation.getDocument());
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				documentAssociation.getOffender().getId()));
	}
	
	/* Action Menu */
	
	/**
	 * Returns the model and view for the Probation Parole Document Action
	 * Menu.
	 * 
	 * @param offender - Offender
	 * @return Model and view for the Probation Parole Document Action
	 * Menu.
	 */
	@RequestMapping(value = "/probationParoleDocumentActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProbationParoleDocumentActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				PROBATION_PAROLE_DOCUMENT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Helper Methods */
	
	/**
	 * Returns a prepared Model and View for creating a Probation Parole
	 * Document.
	 * 
	 * @param offender - Offender
	 * @return Prepared Model and View for creating a Probation Parole
	 * Document.
	 */
	private ModelAndView prepareEditMav(final Offender offender) {
		return this.prepareEditMav(
				offender, new ProbationParoleDocumentForm(), new ModelMap());
	}
	
	/**
	 * Returns a prepared Model and View for creating a Probation Parole
	 * Document.
	 * 
	 * @param offender - Offender
	 * @param form - Probation Parole Document Form
	 * @return Prepared Model and View for creating a Probation Parole
	 * Document.
	 */
	private ModelAndView prepareEditMav(final Offender offender,
			final ProbationParoleDocumentForm form) {
		return this.prepareEditMav(offender, form, new ModelMap());
	}
	
	/**
	 * Returns a prepared Model and View for editing a Probation Parole
	 * Document.
	 * 
	 * @param documentAssociation - Probation Parole Document Association
	 * @return Prepared Model and View for editing a Probation Parole
	 * Document.
	 */
	private ModelAndView prepareEditMav(
			final ProbationParoleDocumentAssociation documentAssociation) {
		ModelMap map = new ModelMap();
		map.addAttribute(PROBATION_PAROLE_DOCUMENT_MODEL_KEY,
				documentAssociation);
		return this.prepareEditMav(documentAssociation.getOffender(),
				this.prepareForm(documentAssociation), map);
	}
	
	/**
	 * Returns a prepared Model and View for editing a Probation Parole
	 * Document.
	 * 
	 * @param documentAssociation - Probation Parole Document Association
	 * @param form - Probation Parole Document Form
	 * @return Prepared Model and View for editing a Probation Parole
	 * Document.
	 */
	private ModelAndView prepareEditMav(
			final ProbationParoleDocumentAssociation documentAssociation,
			final ProbationParoleDocumentForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(PROBATION_PAROLE_DOCUMENT_MODEL_KEY,
				documentAssociation);
		return this.prepareEditMav(documentAssociation.getOffender(),
				form, map);
	}

	/**
	 * Returns a prepared Model and View for creating or editing a Probation
	 * Parole Document.
	 * 
	 * @param offender - Offender
	 * @param form - Probation Parole Document Form
	 * @param map - Model Map
	 * @return Prepared Model and View for creating or editing a Probation
	 * Parole Document.
	 */
	private ModelAndView prepareEditMav(final Offender offender,
			final ProbationParoleDocumentForm form, final ModelMap map) {
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(CATEGORIES_MODEL_KEY,
				this.probationParoleDocumentService.findCategories());
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Returns a prepared Probation Parole Document form populated from the
	 * specified Probation Parole Document Association.
	 * 
	 * @param documentAssociation - Probation Parole Document Association.
	 * @return Prepared Probation Parole Document form populated from the
	 * specified Probation Parole Document Association.
	 */
	private ProbationParoleDocumentForm prepareForm(
			final ProbationParoleDocumentAssociation documentAssociation) {
		ProbationParoleDocumentForm form = new ProbationParoleDocumentForm();
		List<DocumentTag> documentTags =
				this.probationParoleDocumentService
				.findDocumentTagsByDocument(documentAssociation.getDocument());
		List<DocumentTagItem> documentTagItems =
				new ArrayList<DocumentTagItem>();
		for (DocumentTag tag : documentTags) {
			DocumentTagItem item = new DocumentTagItem();
			item.setDocumentTag(tag);
			item.setName(tag.getName());
			item.setDocumentTagOperation(DocumentTagOperation.UPDATE);
			
			documentTagItems.add(item);
		}
		form.setCategory(documentAssociation.getCategory());
		form.setDate(documentAssociation.getDate());
		form.setDocument(documentAssociation.getDocument());
		form.setTitle(documentAssociation.getDocument().getTitle());
		form.setFileExtension(documentAssociation.getDocument()
				.getFileExtension());
		form.setDocumentTagItems(documentTagItems);
		form.setData(this.probationParoleDocumentRetriever.retrieve(
				documentAssociation.getDocument()));
		
		return form;
	}
	
	/**
	 * Processes a list of documentTag items for creation, updating, or removal.
	 * 
	 * @param documentTagItems - List of DocumentTagItems
	 * @param document - Document for which the documentTags are being processed
	 * for
	 * @throws DuplicateEntityFoundException - When a document tag already
	 * exists with given name and document
	 */
	private void processDocumentTags(
			final List<DocumentTagItem> documentTagItems, 
			final Document document) throws DuplicateEntityFoundException {
		if (documentTagItems != null) {
			Iterator<DocumentTagItem> documentTagIterator = 
					documentTagItems.iterator();
			while (documentTagIterator.hasNext()) {
				final DocumentTagItem documentTagItem =
						documentTagIterator.next();
				final DocumentTagOperation documentTagOperation = 
						documentTagItem.getDocumentTagOperation();
				if (DocumentTagOperation.UPDATE.equals(
						documentTagOperation)) {
					this.probationParoleDocumentService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (DocumentTagOperation.CREATE.equals(
						documentTagOperation)) {
					this.probationParoleDocumentService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (DocumentTagOperation.REMOVE.equals(
						documentTagOperation)) {
					this.probationParoleDocumentService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}
	
	/**
	 * Retrieves document file.
	 * 
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "/retrieveFile.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.probationParoleDocumentRetriever
				.retrieve(document);
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition", 
				"attachment; filename=\"" + document.getFilename() + "\"");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException(String.format(ERROR_WRITING_FILE_MSG, 
					document.getFilename()));
		}
	}
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
	}
	
/* Reports. */
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/ppDocListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPPDocListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(P_P_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				P_P_DOCUMENT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified offender p&p document.
	 * 
	 * @param probationParoleDocumentAssociation - Probation Parole Document Association
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/ppDocDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROBATION_PAROLE_DOCUMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPPDocDetails(@RequestParam(
			value = "probationParoleDocumentAssociation", required = true)
			final ProbationParoleDocumentAssociation probationParoleDocumentAssociation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(P_P_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(probationParoleDocumentAssociation.getId()));
		byte[] doc = this.reportRunner.runReport(
				P_P_DOCUMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}	
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class, this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(ProbationParoleDocumentAssociation.class,
				this.probationParoleDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProbationParoleDocumentCategory.class,
				this.probationParoleDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class,
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
