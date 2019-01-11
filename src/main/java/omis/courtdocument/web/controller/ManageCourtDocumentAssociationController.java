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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.courtdocument.domain.CourtDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.service.CourtDocumentAssociationService;
import omis.courtdocument.web.form.CourtDocumentAssociationForm;
import omis.courtdocument.web.validator.CourtDocumentAssociationFormValidator;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/** 
 * Controller for managing court document associations.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Jan 9, 2019)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/courtCase/document")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ManageCourtDocumentAssociationController {
	
	/** Document association type. */
	
	public static final String DOCUMENT_ASSOCIATION_TYPE = "courtCase";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT_URL = 
			"redirect:/courtCase/document/list.html?offender=%d";
	
	/* Views. */
	
	private static final String EDIT_VIEW_NAME = "/courtDocument/edit";
	
	private static final String CREATE_ACTION_MENU_VIEW_NAME =
			"courtDocument/includes/createActionMenu";
	
	/* Model Keys. */
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY =
			"form";
	
	private static final String DOCKETS_MODEL_KEY = "dockets";
	
	private static final String COURT_DOCUMENT_ASSOCIATION_MODEL_KEY =
			"courtDocumentAssociation";
	
	private static final String EDITING_MODEL_KEY = "editing";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Services. */
	
	@Autowired 
	private CourtDocumentAssociationService 
			courtDocumentAssociationService;
	
	/* Validators. */
	
	@Autowired 
	private CourtDocumentAssociationFormValidator 
			courtDocumentAssociationFormValidator;
	
	/* Delegates. */
	
	@Autowired 
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Document Persistence. */
	
	@Autowired 
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired 
	@Qualifier("documentPersistor")
	private DocumentPersister documentPersister;
	
	@Autowired
	@Qualifier("offenderDocumentRemover")
	private FileRemover documentRemover;
	
	/* Property Editor Factories. */
	
	@Autowired 
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired 
	private PropertyEditorFactory courtDocumentCategoryPropertyEditorFactory;
	
	@Autowired 
	private PropertyEditorFactory 
			courtDocumentAssociationPropertyEditorFactory;
	
	@Autowired 
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	@Autowired 
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	private PropertyEditorFactory docketPropertyEditorFactory;
	
	/** Constructor. */
	public ManageCourtDocumentAssociationController() { 
		// Default constructor
	}
	
	/** 
	 * Create view for court document association offender.
	 * 
	 * @param offender offender
	 * @param filter profile filter
	 * @return court document association edit
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true) 
				final Offender offender,
			@RequestParam(value = "filter", required = false)
				final String filter) {
		ModelMap modelMap = new ModelMap();

		final CourtDocumentAssociationForm courtDocumentAssociationForm
			= new CourtDocumentAssociationForm();
		this.prepareEditMap(modelMap, courtDocumentAssociationForm, 
				offender);
		return new ModelAndView(EDIT_VIEW_NAME, modelMap);
	}
	
	/** 
	 * Saves court case document association.
	 * 
	 * @param offender offender
	 * @param filter profile filter
	 * @param courtDocumentAssociationForm court document association form
	 * @param bindingResult binding result
	 * @return court document association list.
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 * @throws IOException if file cannot be saved
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_SAVE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "filter", required = false)
				final String filter,
			final CourtDocumentAssociationForm 
				courtDocumentAssociationForm,
			final BindingResult bindingResult)
	throws DuplicateEntityFoundException, IOException {
		final ModelAndView mav;
		this.courtDocumentAssociationFormValidator.validate(
				courtDocumentAssociationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap modelMap = new ModelMap();
			this.prepareErrorsMap(modelMap, bindingResult);
			this.prepareEditMap(modelMap, courtDocumentAssociationForm, 
					offender);
			mav = new ModelAndView(EDIT_VIEW_NAME, modelMap);
		} else {
			final String fileExtension = courtDocumentAssociationForm
					.getFileExtension();
			this.documentFilenameGenerator.setExtension(fileExtension);
			final String filename = this.documentFilenameGenerator.generate();
			final Document document = this.courtDocumentAssociationService
					.createDocument(new Date(), 
							filename, fileExtension,
							courtDocumentAssociationForm.getTitle());
			this.documentPersister.persist(document, 
					courtDocumentAssociationForm.getData());
			this.courtDocumentAssociationService
					.createCourtDocumentAssociation(
							courtDocumentAssociationForm.getDocket(), 
							offender, document, 
							courtDocumentAssociationForm.getDate(),
							courtDocumentAssociationForm
							.getCourtDocumentCategory());
			this.processDocumentTags(courtDocumentAssociationForm
					.getDocumentTagItems(), document);
			mav = new ModelAndView(String.format(LIST_REDIRECT_URL, 
					offender.getId()));
		}
		return mav;
	}
	
	/** 
	 * Edits court document association.
	 * 
	 * @param courtDocumentAssociation court document association
	 * @return court document association edit
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "courtDocumentAssociation", 
				required = true)
			final CourtDocumentAssociation courtDocumentAssociation) {
		final ModelMap modelMap = new ModelMap();
		CourtDocumentAssociationForm courtDocumentAssociationForm =
				new CourtDocumentAssociationForm();
	
		this.prepareForm(courtDocumentAssociationForm, 
				courtDocumentAssociation.getDocket(), 
				courtDocumentAssociation.getDate(),
				courtDocumentAssociation.getCategory(),
				courtDocumentAssociation.getDocument().getTitle(), 
				this.courtDocumentAssociationService
				.findDocumentTagsByDocument(courtDocumentAssociation
						.getDocument()));
		this.prepareEditMap(modelMap, courtDocumentAssociationForm, 
				courtDocumentAssociation.getOffender(), 
				courtDocumentAssociation, 
				this.courtDocumentAssociationService
				.findDocumentTagsByDocument(courtDocumentAssociation
						.getDocument()));
		modelMap.put(EDITING_MODEL_KEY, true);
		return new ModelAndView(EDIT_VIEW_NAME, modelMap);
	}
	
	/** 
	 * Updates court document association.
	 * 
	 * @param courtDocumentAssociation court document association
	 * @param courtDocumentAssociationForm court document association form
	 * @param bindingResult binding result 
	 * @return court document association list
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "courtDocumentAssociation", 
				required = true)
				final CourtDocumentAssociation courtDocumentAssociation,
			final CourtDocumentAssociationForm courtDocumentAssociationForm, 
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException {
		final ModelAndView mav;
		this.courtDocumentAssociationFormValidator.validate(
				courtDocumentAssociationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap modelMap = new ModelMap();
			this.prepareErrorsMap(modelMap, bindingResult);
			this.prepareEditMap(modelMap, courtDocumentAssociationForm, 
					courtDocumentAssociation.getOffender());
			modelMap.put(COURT_DOCUMENT_ASSOCIATION_MODEL_KEY, 
					courtDocumentAssociation);
			modelMap.put(EDITING_MODEL_KEY, true);
			mav = new ModelAndView(EDIT_VIEW_NAME, modelMap);
		} else {
			Document document = courtDocumentAssociation.getDocument();
			this.courtDocumentAssociationService.updateDocument(document, 
					courtDocumentAssociationForm.getTitle(),
					courtDocumentAssociationForm.getDate());
			this.courtDocumentAssociationService
				.updateCourtDocumentAssociation(
					courtDocumentAssociation,
					courtDocumentAssociationForm.getDocket(),
					document, courtDocumentAssociationForm.getDate(), 
					courtDocumentAssociationForm
					.getCourtDocumentCategory());
			this.processDocumentTags(
					courtDocumentAssociationForm.getDocumentTagItems(), 
					document);
			mav = new ModelAndView(String.format(LIST_REDIRECT_URL, 
					courtDocumentAssociation.getOffender().getId()));
		}
		return mav;
	}
	
	/** 
	 * Court document association action menu.
	 * 
	 * @param courtDocumentAssociation - court document association
	 * @return action menu
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@RequestMapping(value = "associateDocket.html", 
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_DOCUMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView associateDocket(
			@RequestParam(value = "courtDocumentAssociation", required = true)
				final CourtDocumentAssociation courtDocumentAssociation,
			@RequestParam(value = "docket", required = true)
				final Docket docket) throws DuplicateEntityFoundException {
		this.courtDocumentAssociationService.updateCourtDocumentAssociation(
				courtDocumentAssociation, docket, 
				courtDocumentAssociation.getDocument(), 
				courtDocumentAssociation.getDate(), 
				courtDocumentAssociation.getCategory());
		return new ModelAndView(String.format(LIST_REDIRECT_URL, 
				docket.getPerson().getId()));
	}
	
	/** 
	 * Removes court document association.
	 * 
	 * @param courtDocumentAssociation court document association.
	 * @return document associations profile
	 */
	@RequestMapping(value = "remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DOCUMENT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "courtDocumentAssociation", required = true)
				final CourtDocumentAssociation courtDocumentAssociation) {
		final ModelAndView mav;
		long id = courtDocumentAssociation.getOffender().getId();
		List<DocumentTag> tags = this.courtDocumentAssociationService
				.findDocumentTagsByDocument(courtDocumentAssociation
						.getDocument());
		for (DocumentTag documentTag : tags) {
			this.courtDocumentAssociationService.removeTag(documentTag);
		}
		Document document = courtDocumentAssociation.getDocument();
		this.courtDocumentAssociationService
				.removeCourtDocumentAssociation(courtDocumentAssociation);
		this.documentRemover.remove(document.getFilename());
		this.courtDocumentAssociationService.removeDocument(document);
		mav = new ModelAndView(String.format(LIST_REDIRECT_URL, id));
		return mav;
	}
	
	/** 
	 * Court document edit action menu.
	 * 
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "createActionMenu.html", method = RequestMethod.GET)
	public ModelAndView createActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		this.prepareOffenderModel(modelMap, offender);
		mav = new ModelAndView(CREATE_ACTION_MENU_VIEW_NAME, modelMap);
		return mav;
	}
		
	
	/* Prepares form. */
	private void  prepareForm(
			final CourtDocumentAssociationForm courtDocumentAssociationForm, 
			final Docket docket, final Date date, 
			final CourtDocumentCategory courtDocumentCategory, 
			final String title, final List<DocumentTag> documentTags) {
		courtDocumentAssociationForm.setDocket(docket);
		courtDocumentAssociationForm.setDate(date);
		courtDocumentAssociationForm.setCourtDocumentCategory(
				courtDocumentCategory);
		courtDocumentAssociationForm.setTitle(title);
		List<DocumentTagItem> documentTagItems = 
				new ArrayList<DocumentTagItem>();
		for (int x = 0; x < documentTags.size(); x++) {
			final DocumentTagItem documentTagItem = new DocumentTagItem();
			documentTagItem.setDocumentTag(documentTags.get(x));
			documentTagItem.setDocumentTagOperation(
					DocumentTagOperation.UPDATE);
			documentTagItem.setName(documentTags.get(x).getName());
			documentTagItems.add(documentTagItem);
		}
		courtDocumentAssociationForm.setDocumentTagItems(documentTagItems);
	}
	
	/* Prepares model for view. */
	private void prepareEditMap(final ModelMap modelMap, 
			final CourtDocumentAssociationForm courtDocumentAssociationForm, 
			final Offender offender) {
		modelMap.put(DOCKETS_MODEL_KEY, this.courtDocumentAssociationService
				.findDocketsByOffender(offender));
		modelMap.put(CATEGORIES_MODEL_KEY, 
				this.courtDocumentAssociationService.findCategories());
		modelMap.put(COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY, 
				courtDocumentAssociationForm);
		this.prepareOffenderSummarModel(modelMap, offender);
	}
	
	/* Prepared model for view. */
	private void prepareEditMap(final ModelMap modelMap,
			final CourtDocumentAssociationForm courtDocumentAssociationForm, 
			final Offender offender, 
			final CourtDocumentAssociation courtDocumentAssociation,
			final List<DocumentTag> documentTags) {
		this.prepareEditMap(modelMap, courtDocumentAssociationForm, offender);
		modelMap.put(COURT_DOCUMENT_ASSOCIATION_MODEL_KEY, 
				courtDocumentAssociation);
	}
	
	/* Prepares errors for view. */
	private void prepareErrorsMap(final ModelMap modelMap, 
			final BindingResult bindingResult) {
		modelMap.addAttribute(BindingResult.MODEL_KEY_PREFIX 
				+ COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY, 
				bindingResult);
	}
	
	/* Prepares model with offender. */
	private void prepareOffenderModel(final ModelMap modelMap, 
			final Offender offender) {
		modelMap.put(OFFENDER_MODEL_KEY, offender);
	}
	
	/* Prepares offender summary model. */
	private void prepareOffenderSummarModel(final ModelMap modelMap, 
			final Offender offender) {
		this.offenderSummaryModelDelegate.add(modelMap, offender);
	}
	
	/* Process document tag items. */
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
				if (documentTagOperation == DocumentTagOperation.UPDATE) {
					this.courtDocumentAssociationService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.CREATE) {
					this.courtDocumentAssociationService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.REMOVE) {
					this.courtDocumentAssociationService.removeTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}

	/** Init binder. 
	 * @param binder - binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Docket.class,
				this.docketPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(CourtDocumentCategory.class, 
				this.courtDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(CourtDocumentAssociation.class,
				this.courtDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, 
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory.createPropertyEditor());
	}
		
}