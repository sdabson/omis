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

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestDocumentAssociation;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestExternalOpposition;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;
import omis.earlyreleasetracking.domain.InternalApprovalDecisionCategory;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestDocumentAssociationExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestExistsException;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestExternalOppositionExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestInternalApprovalExists;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestNoteAssociationExistsException;
import omis.earlyreleasetracking.service.EarlyReleaseRequestService;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestDocumentAssociationItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestExternalOppositionItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestForm;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestInternalApprovalItem;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestItemOperation;
import omis.earlyreleasetracking.web.form.EarlyReleaseRequestNoteAssociationItem;
import omis.earlyreleasetracking.web.validator.EarlyReleaseRequestFormValidator;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Early Release Request Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 12, 2019)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/earlyReleaseTracking")
@PreAuthorize("hasRole('USER')")
public class EarlyReleaseRequestController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME = "/earlyReleaseTracking/edit";
	
	private static final String EARLY_RELEASE_REQUEST_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/earlyReleaseRequestActionMenu";
	
	private static final String
		EARLY_RELEASE_REQUEST_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestNoteItemsActionMenu";
	
	private static final String
		EARLY_RELEASE_REQUEST_NOTE_ITEM_ROW_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestNoteAssociationItemTableRow";
	
	private static final String
		EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_ROW_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestDocumentAssociationItemContent";
	
	private static final String DOCUMENT_TAG_ITEM_ROW_VIEW_NAME =
			"/earlyReleaseTracking/includes/documentTagItemContent";
	
	
	private static final String
		EXTERNAL_OPPOSITION_ITEMS_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestExternalOppositionItemsActionMenu";

	private static final String
		INTERNAL_APPROVAL_ITEMS_ACTION_MENU_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestInternalApprovalItemsActionMenu";
	
	private static final String
		EXTERNAL_OPPOSITION_ITEM_ROW_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestExternalOppositionItemTableRow";
	
	private static final String
		INTERNAL_APPROVAL_ITEM_ROW_VIEW_NAME =
			"/earlyReleaseTracking/includes/"
			+ "earlyReleaseRequestInternalApprovalItemTableRow";
	
	private static final String LIST_VIEW_REDIRECT =
			"redirect:/earlyReleaseTracking/list.html?offender=%d";;
	
	/* Model Keys */
	
	private static final String EARLY_RELEASE_REQUEST_MODEL_KEY =
			"earlyReleaseRequest";
	
	private static final String EARLY_RELEASE_REQUEST_CATEGORY_MODEL_KEY =
			"earlyReleaseRequestCategory";
	
	private static final String DOCKETS_MODEL_KEY = "dockets";
	
	private  static final String REQUEST_STATUS_CATEGORIES_MODEL_KEY =
			"earlyReleaseStatusCategories";
	
	private static final String JUDGE_RESPONSE_CATEGORIES_MODEL_KEY =
			"earlyReleaseJudgeResponseCategories";
	
	private static final String DECISION_CATEGORIES_MODEL_KEY =
			"internalApprovalDecisionCategories";
	
	private static final String PARTY_CATEGORIES_MODEL_KEY =
			"externalOppositionPartyCategories";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String
		EARLY_RELEASE_REQUEST_NOTE_ASSOC_ITEM_MODEL_KEY =
			"earlyReleaseRequestNoteAssociationItem";

	private static final String EARLY_RELEASE_REQUEST_DOC_ASSOC_ITEM_MODEL_KEY =
			"earlyReleaseRequestDocumentAssociationItem";
	
	private static final String EXTERNAL_OPPOSITION_ITEM_MODEL_KEY =
			"earlyReleaseRequestExternalOppositionItem";

	private static final String INTERNAL_APPROVAL_ITEM_MODEL_KEY =
			"earlyReleaseRequestInternalApprovalItem";
	
	private static final String
		EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_INDEX_MODEL_KEY =
			"earlyReleaseRequestDocumentAssociationItemIndex";
	
	private static final String
		EARLY_RELEASE_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY =
			"earlyReleaseRequestNoteAssociationItemIndex";
	
	private static final String EXTERNAL_OPPOSITION_ITEM_INDEX_MODEL_KEY =
			"earlyReleaseRequestExternalOppositionItemIndex";
	
	private static final String INTERNAL_APPROVAL_ITEM_INDEX_MODEL_KEY =
			"earlyReleaseRequestInternalApprovalItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY =
			"documentTagItem";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	private static final String FORM_MODEL_KEY = "earlyReleaseRequestForm";
	
	/* Message Keys */
	
	private static final String EARLY_RELEASE_REQUEST_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequest.exists";

	private static final String EARLY_RELEASE_REQUEST_DOC_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequestDocumentAssociation.exists";

	private static final String EARLY_RELEASE_REQUEST_NOTE_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequestNoteAssociation.exists";

	private static final String EARLY_RELEASE_REQ_INT_APP_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequestInternalApproval.exists";

	private static final String EARLY_RELEASE_REQ_EXT_OP_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequestExternalOpposition.exists";
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"earlyReleaseRequest.entity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.earlyreleasetracking.msgs.form";
	
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("earlyReleaseRequestPropertyEditorFactory")
	private PropertyEditorFactory earlyReleaseRequestPropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseRequestNoteAssociationPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseRequestNoteAssociationPropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseRequestDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseRequestDocumentAssociationropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseRequestInternalApprovalPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseRequestInternalApprovalPropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseRequestExternalOppositionPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseRequestExternalOppositionPropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseJudgeResponseCategoryPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseJudgeResponseCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("earlyReleaseStatusCategoryPropertyEditorFactory")
	private PropertyEditorFactory
				earlyReleaseStatusCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalOppositionPartyCategoryPropertyEditorFactory")
	private PropertyEditorFactory
				externalOppositionPartyCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;

	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;

	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
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
	@Qualifier("earlyReleaseRequestDocumentPersister")
	private DocumentPersister earlyReleaseRequestDocumentPersister;
	
	@Autowired
	@Qualifier("earlyReleaseRequestDocumentRetriever")
	private DocumentRetriever earlyReleaseRequestDocumentRetriever;
	
	@Autowired
	@Qualifier("earlyReleaseRequestDocumentRemover")
	private FileRemover earlyReleaseRequestDocumentRemover;
	
	/* Service */
	
	@Autowired
	@Qualifier("earlyReleaseRequestService")
	private EarlyReleaseRequestService earlyReleaseRequestService;
	
	/* Validator */
	
	@Autowired
	@Qualifier("earlyReleaseRequestFormValidator")
	private EarlyReleaseRequestFormValidator earlyReleaseRequestFormValidator;
	
	/**
	 * Default constructor for EarlyReleaseRequestController.
	 */
	public EarlyReleaseRequestController() {
	}
	
	/**
	 * Returns the Model and View for creating an Early Release Request.
	 * 
	 * @param offender Offender
	 * @param category Category
	 * @return Model and View for creating an Early Release Request.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EARLY_RELEASE_REQUEST_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "category", required = true)
				final EarlyReleaseRequestCategory category) {
		return this.prepareEditMav(offender, category);
	}

	/**
	 * Creates an Early Release Request and returns to the list screen, or back
	 * to the create screen on form error.
	 * 
	 * @param offender Offender
	 * @param category Category
	 * @param form Early Release Request Form
	 * @param bindingResult ginding Result
	 * @return ModelAndView for list screen, or back to create screen on form
	 * error
	 * @throws EarlyReleaseRequestDocumentAssociationExists When an Early
	 * Release Request Document Association already exists
	 * @throws EarlyReleaseRequestNoteAssociationExistsException When an Early
	 * Release Request Note Association already exists
	 * @throws EarlyReleaseRequestExternalOppositionExists When an Early Release
	 * Request External Opposition already exists
	 * @throws EarlyReleaseRequestInternalApprovalExists When an Early Release
	 * Request Internal Approval already exists
	 * @throws EarlyReleaseRequestExistsException When a Early Release Request
	 * already exists
	 * @throws DuplicateEntityFoundException When a Document or Document Tag
	 * already exist
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('EARLY_RELEASE_REQUEST_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "category", required = true)
				final EarlyReleaseRequestCategory category,
				final EarlyReleaseRequestForm form,
				final BindingResult bindingResult)
						throws EarlyReleaseRequestDocumentAssociationExists,
						EarlyReleaseRequestNoteAssociationExistsException,
						EarlyReleaseRequestExternalOppositionExists,
						EarlyReleaseRequestInternalApprovalExists,
						EarlyReleaseRequestExistsException,
						DuplicateEntityFoundException {
		this.earlyReleaseRequestFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(offender, category, form);
		} else {
			EarlyReleaseRequest earlyReleaseRequest =
					this.earlyReleaseRequestService.createEarlyReleaseRequest(
							form.getDocket(), form.getRequestDate(), category,
							form.getReleaseStatus(), form.getJudgeResponse(),
							form.getRequestBy(), form.getApprovalDate(),
							form.getComments());
			this.processDocumentItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestDocumentAssociationItems());
			this.processNoteItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestNoteAssociationItems());
			this.processExternalOppositionItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestExternalOppositionItems());
			this.processInternalApprovalItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestInternalApprovalItems());
		}
		
		return new ModelAndView(String.format(LIST_VIEW_REDIRECT,
				offender.getId()));
	}
	
	/**
	 * Returns the Model and View for editing an Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request to edit
	 * @return Model and View for editing an Early Release Request
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EARLY_RELEASE_REQUEST_EDIT') "
			+ "or hasRole('EARLY_RELEASE_REQUEST_VIEW')"
			+ "or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "earlyReleaseRequest", required = true)
				final EarlyReleaseRequest earlyReleaseRequest) {
		return this.prepareEditMav(earlyReleaseRequest);
	}
	
	/**
	 * Updates an Early Release Request and returns to the list screen, or back
	 * to the edit screen on form error.
	 * 
	 * @param earlyReleaseRequest Early Release Request to update
	 * @param form Early Release Request Form
	 * @param bindingResult ginding Result
	 * @return ModelAndView for list screen, or back to edit screen on form
	 * error
	 * @throws EarlyReleaseRequestDocumentAssociationExists When an Early
	 * Release Request Document Association already exists
	 * @throws EarlyReleaseRequestNoteAssociationExistsException When an Early
	 * Release Request Note Association already exists
	 * @throws EarlyReleaseRequestExternalOppositionExists When an Early Release
	 * Request External Opposition already exists
	 * @throws EarlyReleaseRequestInternalApprovalExists When an Early Release
	 * Request Internal Approval already exists
	 * @throws EarlyReleaseRequestExistsException When a Early Release Request
	 * already exists
	 * @throws DuplicateEntityFoundException When a Document or Document Tag
	 * already exist
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('EARLY_RELEASE_REQUEST_EDIT')"
			+ "or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "earlyReleaseRequest", required = true)
				final EarlyReleaseRequest earlyReleaseRequest,
				final EarlyReleaseRequestForm form,
				final BindingResult bindingResult)
						throws EarlyReleaseRequestDocumentAssociationExists,
						EarlyReleaseRequestNoteAssociationExistsException,
						EarlyReleaseRequestExternalOppositionExists,
						EarlyReleaseRequestInternalApprovalExists,
						EarlyReleaseRequestExistsException,
						DuplicateEntityFoundException {
		this.earlyReleaseRequestFormValidator.validate(form, bindingResult);
				
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(earlyReleaseRequest, form);
		} else {
			this.earlyReleaseRequestService.updateEarlyReleaseRequest(
					earlyReleaseRequest, form.getDocket(),
					form.getRequestDate(), earlyReleaseRequest.getCategory(),
					form.getReleaseStatus(), form.getJudgeResponse(),
					form.getRequestBy(), form.getApprovalDate(),
					form.getComments());
			this.processDocumentItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestDocumentAssociationItems());
			this.processNoteItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestNoteAssociationItems());
			this.processExternalOppositionItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestExternalOppositionItems());
			this.processInternalApprovalItems(earlyReleaseRequest,
					form.getEarlyReleaseRequestInternalApprovalItems());
		}
		
		return new ModelAndView(String.format(LIST_VIEW_REDIRECT,
				earlyReleaseRequest.getDocket().getPerson().getId()));
	}
	
	/**
	 * Removes an Early Release Request and associated entities and returns
	 * to the list screen.
	 * 
	 * @param earlyReleaseRequest Early Release Request to remove.
	 * @return Returns to the Early Release Request List screen after
	 * successful removal.
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EARLY_RELEASE_REQUEST_REMOVE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "earlyReleaseRequest", required = true)
				final EarlyReleaseRequest earlyReleaseRequest) {
		for (EarlyReleaseRequestNoteAssociation note
				: this.earlyReleaseRequestService
				.findEarlyReleaseRequestNoteAssociationsByEarlyReleaseRequest(
						earlyReleaseRequest)) {
			this.earlyReleaseRequestService
				.removeEarlyReleaseRequestNoteAssociation(note);
		}
		for (EarlyReleaseRequestDocumentAssociation documentAssociation
				: this.earlyReleaseRequestService
			.findEarlyReleaseRequestDocumentAssociationsByEarlyReleaseRequest(
					earlyReleaseRequest)) {
			for (DocumentTag documentTag : this.earlyReleaseRequestService
					.findDocumentTagsByDocument(
							documentAssociation.getDocument())) {
				this.earlyReleaseRequestService.removeDocumentTag(
						documentTag);
			}
			this.earlyReleaseRequestService
				.removeEarlyReleaseRequestDocumentAssociation(
						documentAssociation);
			this.earlyReleaseRequestDocumentRemover.remove(
						documentAssociation.getDocument().getFilename());
			this.earlyReleaseRequestService.removeDocument(
						documentAssociation.getDocument());
		}
		for (EarlyReleaseRequestInternalApproval approval 
				: this.earlyReleaseRequestService
				.findEarlyReleaseRequestInternalApprovalsByEarlyReleaseRequest(
						earlyReleaseRequest)) {
			this.earlyReleaseRequestService
				.removeEarlyReleaseRequestInternalApproval(approval);
		}
		for (EarlyReleaseRequestExternalOpposition opposition
				: this.earlyReleaseRequestService
			.findEarlyReleaseRequestExternalOppositionsByEarlyReleaseRequest(
					earlyReleaseRequest)) {
			this.earlyReleaseRequestService
				.removeEarlyReleaseRequestExternalOpposition(opposition);
		}
		this.earlyReleaseRequestService
				.removeEarlyReleaseRequest(earlyReleaseRequest);
		
		return new ModelAndView(String.format(LIST_VIEW_REDIRECT,
				earlyReleaseRequest.getDocket().getPerson().getId()));
	}
	
	/* Action Menu Views */
	
	/**
	 * Returns the Model and View for the Early Release Request action menu.
	 * 
	 * @param offender Offender
	 * @return Model and View for the Early Release Request action menu.
	 */
	@RequestMapping(value = "/earlyReleaseRequestActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				EARLY_RELEASE_REQUEST_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for the Early Release Request Note Association
	 * Items action menu.
	 * 
	 * @param earlyReleaseRequestNoteItemIndex Early Release Request Note Item
	 * Index
	 * @return Model and View for the Early Release Request Note Association
	 * Items action menu
	 */
	@RequestMapping(value =
			"/earlyReleaseRequestNoteAssociationItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestNoteItemsActionMenu(
			@RequestParam(value = "earlyReleaseRequestNoteAssociationItemIndex",
			required = true) final Integer earlyReleaseRequestNoteItemIndex) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestNoteItemIndex);
		return new ModelAndView(
				EARLY_RELEASE_REQUEST_NOTE_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns the Model and View for the Early Release Request External
	 * Opposition Items action menu.
	 * 
	 * @param earlyReleaseRequestExternalOppositionItemIndex Early Release
	 * Request External Opposition Item Index
	 * @return Model and View for the Early Release Request External Opposition
	 * Items action menu
	 */
	@RequestMapping(value =
			"/earlyReleaseRequestExternalOppositionItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView
				displayEarlyReleaseRequestExternalOppositionItemsActionMenu(
			@RequestParam(value =
			"earlyReleaseRequestExternalOppositionItemIndex", required = true)
			final Integer earlyReleaseRequestExternalOppositionItemIndex) {
		ModelMap map = new ModelMap();
		map.addAttribute(EXTERNAL_OPPOSITION_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestExternalOppositionItemIndex);
		return new ModelAndView(
				EXTERNAL_OPPOSITION_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for the Early Release Request Internal
	 * Approval Items action menu.
	 * 
	 * @param earlyReleaseRequestInternalApprovalItemIndex Early Release
	 * Request Internal Approval Item Index
	 * @return Model and View for the Early Release Request Internal Approval
	 * Items action menu
	 */
	@RequestMapping(value =
			"/earlyReleaseRequestInternalApprovalItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView
				displayEarlyReleaseRequestInternalApprovalItemsActionMenu(
			@RequestParam(value =
			"earlyReleaseRequestInternalApprovalItemIndex", required = true)
			final Integer earlyReleaseRequestInternalApprovalItemIndex) {
		ModelMap map = new ModelMap();
		map.addAttribute(INTERNAL_APPROVAL_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestInternalApprovalItemIndex);
		return new ModelAndView(
				INTERNAL_APPROVAL_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* AJAX views */
	
	/**
	 * Returns the Model and View for a Early Release Request Note Association
	 * Item Row.
	 * 
	 * @param earlyReleaseRequestNoteAssociationItemIndex Early Release
	 * Request Note Association Item Index
	 * @return Model and View for a Early Release Request Note Association
	 * Item Row.
	 */
	@RequestMapping(value = "createEarlyReleaseRequestNoteAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestNoteAssociationItemRow(
			@RequestParam(value = "earlyReleaseRequestNoteAssociationItemIndex",
			required = true)
			final Integer earlyReleaseRequestNoteAssociationItemIndex) {
		ModelMap map = new ModelMap();
		EarlyReleaseRequestNoteAssociationItem noteItem =
				new EarlyReleaseRequestNoteAssociationItem();
		noteItem.setItemOperation(EarlyReleaseRequestItemOperation.CREATE);
		map.addAttribute(EARLY_RELEASE_REQUEST_NOTE_ASSOC_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(EARLY_RELEASE_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestNoteAssociationItemIndex);
		return new ModelAndView(
				EARLY_RELEASE_REQUEST_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for a Early Release Request Document
	 * Association Item Row.
	 * 
	 * @param earlyReleaseRequestDocumentAssociationItemIndex Early Release
	 * Request Document Association Item Index
	 * @return Model and View for a Early Release Request Document Association
	 * Item Row.
	 */
	@RequestMapping(
			value = "createEarlyReleaseRequestDocumentAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestDocumentAssociationItemRow(
			@RequestParam(
					value = "earlyReleaseRequestDocumentAssociationItemIndex",
					required = true)
			final Integer earlyReleaseRequestDocumentAssociationItemIndex) {
		ModelMap map = new ModelMap();
		EarlyReleaseRequestDocumentAssociationItem documentItem =
				new EarlyReleaseRequestDocumentAssociationItem();
		documentItem.setDate(new Date());
		documentItem.setItemOperation(EarlyReleaseRequestItemOperation.CREATE);
		map.addAttribute(EARLY_RELEASE_REQUEST_DOC_ASSOC_ITEM_MODEL_KEY,
				documentItem);
		map.addAttribute(EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestDocumentAssociationItemIndex);
		return new ModelAndView(
				EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_ROW_VIEW_NAME, map);
	}
	
	
	/**
	 * Returns the Model and View for a Document Tag Item Row.
	 * 
	 * @param earlyReleaseRequestDocumentAssociationItemIndex Early Release
	 * Request Document Association Item Index
	 * @param documentTagItemIndex Document Tag Item Index
	 * @return Model and View for a Document Tag Item Row.
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "earlyReleaseRequestDocumentAssociationItemIndex",
			required = true)
				final Integer earlyReleaseRequestDocumentAssociationItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setDocumentTagOperation(DocumentTagOperation.CREATE);
		map.addAttribute(EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestDocumentAssociationItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_ROW_VIEW_NAME,
				map);
	}
	
	/**
	 * Returns the Model and View for a Early Release Request Internal Approval
	 * Item Row.
	 * 
	 * @param earlyReleaseRequestInternalApprovalItemIndex Early Release
	 * Request Internal Approval Item Index
	 * @return Model and View for a Early Release Request Internal Approval
	 * Item Row.
	 */
	@RequestMapping(value =
			"createEarlyReleaseRequestInternalApprovalItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestInternalApprovalItemRow(
			@RequestParam(value =
			"earlyReleaseRequestInternalApprovalItemIndex",
			required = true)
			final Integer earlyReleaseRequestInternalApprovalItemIndex) {
		ModelMap map = new ModelMap();
		EarlyReleaseRequestInternalApprovalItem approvalItem =
				new EarlyReleaseRequestInternalApprovalItem();
		approvalItem.setItemOperation(EarlyReleaseRequestItemOperation.CREATE);
		map.addAttribute(DECISION_CATEGORIES_MODEL_KEY,
				InternalApprovalDecisionCategory.values());
		map.addAttribute(INTERNAL_APPROVAL_ITEM_MODEL_KEY, approvalItem);
		map.addAttribute(INTERNAL_APPROVAL_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestInternalApprovalItemIndex);
		return new ModelAndView(INTERNAL_APPROVAL_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns the Model and View for a Early Release Request External
	 * Opposition Item Row.
	 * 
	 * @param earlyReleaseRequestExternalOppositionItemIndex Early Release
	 * Request External Opposition Item Index
	 * @return Model and View for a Early Release Request External Opposition
	 * Item Row.
	 */
	@RequestMapping(value =
			"createEarlyReleaseRequestExternalOppositionItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEarlyReleaseRequestExternalOppositionItemRow(
			@RequestParam(value =
			"earlyReleaseRequestExternalOppositionItemIndex",
			required = true)
			final Integer earlyReleaseRequestExternalOppositionItemIndex) {
		ModelMap map = new ModelMap();
		EarlyReleaseRequestExternalOppositionItem oppositionItem =
				new EarlyReleaseRequestExternalOppositionItem();
		oppositionItem.setItemOperation(
				EarlyReleaseRequestItemOperation.CREATE);
		map.addAttribute(PARTY_CATEGORIES_MODEL_KEY,
				this.earlyReleaseRequestService
				.findExternalOppositionPartyCategories());
		map.addAttribute(EXTERNAL_OPPOSITION_ITEM_MODEL_KEY, oppositionItem);
		map.addAttribute(EXTERNAL_OPPOSITION_ITEM_INDEX_MODEL_KEY,
				earlyReleaseRequestExternalOppositionItemIndex);
		return new ModelAndView(EXTERNAL_OPPOSITION_ITEM_ROW_VIEW_NAME, map);
	}
	
	/* Helper Methods */
	
	private ModelAndView prepareEditMav(final Offender offender,
			final EarlyReleaseRequestCategory category) {
		return this.prepareEditMav(offender, category,
				new EarlyReleaseRequestForm(), new ModelMap());
	}

	private ModelAndView prepareEditMav(
			final EarlyReleaseRequest earlyReleaseRequest) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_MODEL_KEY, earlyReleaseRequest);
		
		return this.prepareEditMav(
				(Offender) earlyReleaseRequest.getDocket().getPerson(),
				earlyReleaseRequest.getCategory(),
				this.populateForm(earlyReleaseRequest), map);
	}

	private ModelAndView prepareEditMav(final Offender offender,
			final EarlyReleaseRequestCategory category,
			final EarlyReleaseRequestForm form) {
		return this.prepareEditMav(offender, category, form, new ModelMap());
	}

	private ModelAndView prepareEditMav(
			final EarlyReleaseRequest earlyReleaseRequest,
			final EarlyReleaseRequestForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(EARLY_RELEASE_REQUEST_MODEL_KEY, earlyReleaseRequest);
		
		return this.prepareEditMav(
				(Offender) earlyReleaseRequest.getDocket().getPerson(),
				earlyReleaseRequest.getCategory(), form, map);
	}
	
	private ModelAndView prepareEditMav(final Offender offender,
			final EarlyReleaseRequestCategory category,
			final EarlyReleaseRequestForm form, final ModelMap map) {
		map.addAttribute(EARLY_RELEASE_REQUEST_CATEGORY_MODEL_KEY, category);
		map.addAttribute(DOCKETS_MODEL_KEY, this.earlyReleaseRequestService
				.findDocketsByOffender(offender));
		map.addAttribute(DECISION_CATEGORIES_MODEL_KEY,
				InternalApprovalDecisionCategory.values());
		map.addAttribute(JUDGE_RESPONSE_CATEGORIES_MODEL_KEY,
				this.earlyReleaseRequestService
				.findEarlyReleaseJudgeResponseCategories());
		map.addAttribute(REQUEST_STATUS_CATEGORIES_MODEL_KEY,
				this.earlyReleaseRequestService
				.findEarlyReleaseStatusCategories());
		map.addAttribute(PARTY_CATEGORIES_MODEL_KEY,
				this.earlyReleaseRequestService
				.findExternalOppositionPartyCategories());
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(EARLY_RELEASE_REQUEST_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				form.getEarlyReleaseRequestDocumentAssociationItems().size());
		map.addAttribute(EARLY_RELEASE_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getEarlyReleaseRequestNoteAssociationItems().size());
		map.addAttribute(EXTERNAL_OPPOSITION_ITEM_INDEX_MODEL_KEY, 
				form.getEarlyReleaseRequestExternalOppositionItems().size());
		map.addAttribute(INTERNAL_APPROVAL_ITEM_INDEX_MODEL_KEY,
				form.getEarlyReleaseRequestInternalApprovalItems().size());
		List<Integer> dtIndexes = new ArrayList<Integer>();
		for (int i = 0;
				 i < form.getEarlyReleaseRequestDocumentAssociationItems()
					.size();
				 i++) {
			dtIndexes.add(i,
					form.getEarlyReleaseRequestDocumentAssociationItems()
					.get(i).getDocumentTagItems().size());
		}
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, dtIndexes);
		map.addAttribute(FORM_MODEL_KEY, form);

		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	private EarlyReleaseRequestForm populateForm(
			final EarlyReleaseRequest earlyReleaseRequest) {
		EarlyReleaseRequestForm form = new EarlyReleaseRequestForm();
		
		form.setApprovalDate(earlyReleaseRequest.getApprovalDate());
		form.setComments(earlyReleaseRequest.getComments());
		form.setDocket(earlyReleaseRequest.getDocket());
		form.setJudgeResponse(earlyReleaseRequest.getJudgeResponse());
		form.setReleaseStatus(earlyReleaseRequest.getRequestStatus());
		form.setRequestBy(earlyReleaseRequest.getRequestBy());
		form.setRequestDate(earlyReleaseRequest.getRequestDate());
		
		List<EarlyReleaseRequestNoteAssociationItem> noteAssociationItems =
				new ArrayList<EarlyReleaseRequestNoteAssociationItem>();
		List<EarlyReleaseRequestDocumentAssociationItem>
				documentAssociationItems =
					new ArrayList<EarlyReleaseRequestDocumentAssociationItem>();
		List<EarlyReleaseRequestInternalApprovalItem> internalApprovalItems =
				new ArrayList<EarlyReleaseRequestInternalApprovalItem>();
		List<EarlyReleaseRequestExternalOppositionItem>
				externalOppositionItems =
					new ArrayList<EarlyReleaseRequestExternalOppositionItem>();
		
		for (EarlyReleaseRequestNoteAssociation note
				: this.earlyReleaseRequestService
				.findEarlyReleaseRequestNoteAssociationsByEarlyReleaseRequest(
						earlyReleaseRequest)) {
			EarlyReleaseRequestNoteAssociationItem item =
					new EarlyReleaseRequestNoteAssociationItem();
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setEarlyReleaseRequestNoteAssociation(note);
			item.setItemOperation(EarlyReleaseRequestItemOperation.UPDATE);
			noteAssociationItems.add(item);
		}
		for (EarlyReleaseRequestDocumentAssociation documentAssociation
				: this.earlyReleaseRequestService
			.findEarlyReleaseRequestDocumentAssociationsByEarlyReleaseRequest(
					earlyReleaseRequest)) {
			EarlyReleaseRequestDocumentAssociationItem item =
					new EarlyReleaseRequestDocumentAssociationItem();
			List<DocumentTagItem> documentTagItems =
					new ArrayList<DocumentTagItem>();
			for (DocumentTag tag : this.earlyReleaseRequestService
					.findDocumentTagsByDocument(documentAssociation
							.getDocument())) {
				DocumentTagItem tagItem = new DocumentTagItem();
				
				tagItem.setDocumentTag(tag);
				tagItem.setName(tag.getName());
				tagItem.setDocumentTagOperation(DocumentTagOperation.UPDATE);
				
				documentTagItems.add(tagItem);
			}
			item.setDate(documentAssociation.getDocument().getDate());
			item.setDocument(documentAssociation.getDocument());
			item.setDocumentTagItems(documentTagItems);
			item.setEarlyReleaseRequestDocumentAssociation(documentAssociation);
			item.setFileExtension(documentAssociation.getDocument()
					.getFileExtension());
			item.setTitle(documentAssociation.getDocument().getTitle());
			item.setItemOperation(EarlyReleaseRequestItemOperation.UPDATE);
			item.setData(this.earlyReleaseRequestDocumentRetriever.retrieve(
					documentAssociation.getDocument()));
			documentAssociationItems.add(item);
		}
		for (EarlyReleaseRequestInternalApproval approval 
				: this.earlyReleaseRequestService
				.findEarlyReleaseRequestInternalApprovalsByEarlyReleaseRequest(
						earlyReleaseRequest)) {
			EarlyReleaseRequestInternalApprovalItem item =
					new EarlyReleaseRequestInternalApprovalItem();
			
			item.setDate(approval.getDate());
			item.setDecision(approval.getDecision());
			item.setEarlyReleaseRequestInternalApproval(approval);
			item.setName(approval.getName());
			item.setNarrative(approval.getNarrative());
			item.setItemOperation(EarlyReleaseRequestItemOperation.UPDATE);
			
			internalApprovalItems.add(item);
		}
		for (EarlyReleaseRequestExternalOpposition opposition
				: this.earlyReleaseRequestService
			.findEarlyReleaseRequestExternalOppositionsByEarlyReleaseRequest(
					earlyReleaseRequest)) {
			EarlyReleaseRequestExternalOppositionItem item =
					new EarlyReleaseRequestExternalOppositionItem();
			
			item.setDate(opposition.getDate());
			item.setEarlyReleaseRequestExternalOpposition(opposition);
			item.setNarrative(opposition.getNarrative());
			item.setParty(opposition.getParty());
			item.setItemOperation(EarlyReleaseRequestItemOperation.UPDATE);
			
			externalOppositionItems.add(item);
		}
		
		form.setEarlyReleaseRequestDocumentAssociationItems(
				documentAssociationItems);
		form.setEarlyReleaseRequestExternalOppositionItems(
				externalOppositionItems);
		form.setEarlyReleaseRequestInternalApprovalItems(internalApprovalItems);
		form.setEarlyReleaseRequestNoteAssociationItems(noteAssociationItems);
		
		return form;
	}
	
	private void processExternalOppositionItems(
			final EarlyReleaseRequest earlyReleaseRequest,
			final List<EarlyReleaseRequestExternalOppositionItem> items)
					throws EarlyReleaseRequestExternalOppositionExists {
		for (EarlyReleaseRequestExternalOppositionItem item : items) {
			if (EarlyReleaseRequestItemOperation.CREATE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.createEarlyReleaseRequestExternalOpposition(
						earlyReleaseRequest, item.getParty(), item.getDate(),
						item.getNarrative());
			} else if (EarlyReleaseRequestItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.updateEarlyReleaseRequestExternalOpposition(
						item.getEarlyReleaseRequestExternalOpposition(),
						earlyReleaseRequest, item.getParty(), item.getDate(),
						item.getNarrative());
			} else if (EarlyReleaseRequestItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.removeEarlyReleaseRequestExternalOpposition(
						item.getEarlyReleaseRequestExternalOpposition());
			}
		}
	}
	
	private void processInternalApprovalItems(
			final EarlyReleaseRequest earlyReleaseRequest,
			final List<EarlyReleaseRequestInternalApprovalItem> items)
					throws EarlyReleaseRequestInternalApprovalExists {
		for (EarlyReleaseRequestInternalApprovalItem item : items) {
			if (EarlyReleaseRequestItemOperation.CREATE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.createEarlyReleaseRequestInternalApproval(
							earlyReleaseRequest, item.getName(), item.getDate(),
							item.getDecision(), item.getNarrative());
			} else if (EarlyReleaseRequestItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.updateEarlyReleaseRequestInternalApproval(
						item.getEarlyReleaseRequestInternalApproval(),
						earlyReleaseRequest, item.getName(), item.getDate(),
						item.getDecision(), item.getNarrative());
			} else if (EarlyReleaseRequestItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.removeEarlyReleaseRequestInternalApproval(
						item.getEarlyReleaseRequestInternalApproval());
			}
		}
	}
	
	private void processNoteItems(
			final EarlyReleaseRequest earlyReleaseRequest,
			final List<EarlyReleaseRequestNoteAssociationItem> items)
					throws EarlyReleaseRequestNoteAssociationExistsException {
		for (EarlyReleaseRequestNoteAssociationItem item : items) {
			if (EarlyReleaseRequestItemOperation.CREATE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.createEarlyReleaseRequestNoteAssociation(
							earlyReleaseRequest, item.getDescription(),
							item.getDate());
			} else if (EarlyReleaseRequestItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				if (this.isNoteChanged(
						item.getEarlyReleaseRequestNoteAssociation(),
						item.getDate(), item.getDescription())) {
					this.earlyReleaseRequestService
						.updateEarlyReleaseRequestNoteAssociation(
							item.getEarlyReleaseRequestNoteAssociation(),
							earlyReleaseRequest, item.getDescription(),
							item.getDate());
				}
			} else if (EarlyReleaseRequestItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				this.earlyReleaseRequestService
					.removeEarlyReleaseRequestNoteAssociation(
						item.getEarlyReleaseRequestNoteAssociation());
			}
		}
	}
	
	private void processDocumentItems(
			final EarlyReleaseRequest earlyReleaseRequest,
			final List<EarlyReleaseRequestDocumentAssociationItem> items)
					throws EarlyReleaseRequestDocumentAssociationExists,
							DuplicateEntityFoundException {
		for (EarlyReleaseRequestDocumentAssociationItem item : items) {
			if (EarlyReleaseRequestItemOperation.CREATE.equals(
					item.getItemOperation())) {
				final String fileExtension = item.getFileExtension();
				this.documentFilenameGenerator
						.setExtension(fileExtension);
				final String filename =
						this.documentFilenameGenerator.generate();
				Document document = this.earlyReleaseRequestService
						.createDocument(item.getDate(),
								filename, item.getFileExtension(),
								item.getTitle());
				this.earlyReleaseRequestDocumentPersister
						.persist(document, item.getData());
				this.earlyReleaseRequestService
					.createEarlyReleaseRequestDocumentAssociation(document,
							earlyReleaseRequest);
				this.processDocumentTags(item.getDocumentTagItems(), document);
			} else if (EarlyReleaseRequestItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				Document document = this.earlyReleaseRequestService
						.updateDocument(item.getDocument(),
								item.getDate(), item.getTitle());
				this.processDocumentTags(item.getDocumentTagItems(), document);
			} else if (EarlyReleaseRequestItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				for (DocumentTag documentTag : this.earlyReleaseRequestService
						.findDocumentTagsByDocument(item.getDocument())) {
					this.earlyReleaseRequestService.removeDocumentTag(
							documentTag);
				}
				this.earlyReleaseRequestService
					.removeEarlyReleaseRequestDocumentAssociation(
							item.getEarlyReleaseRequestDocumentAssociation());
				this.earlyReleaseRequestDocumentRemover.remove(
						item.getDocument().getFilename());
				this.earlyReleaseRequestService.removeDocument(
						item.getDocument());
			}
		}
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
					this.earlyReleaseRequestService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (DocumentTagOperation.CREATE.equals(
						documentTagOperation)) {
					this.earlyReleaseRequestService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (DocumentTagOperation.REMOVE.equals(
						documentTagOperation)) {
					this.earlyReleaseRequestService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}

	/**
	 * Checks if an Early Release Request Note Association has been changed and
	 * returns true if it has.
	 * 
	 * @param note - Early Release Request Note Association to check for change
	 * @param date - Note Date from Form
	 * @param description - Note Description from Form
	 * @return Boolean - true if either the form's note date or value is
	 * different from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(
			final EarlyReleaseRequestNoteAssociation note,
			final Date date, final String description) {
		if (!note.getDescription().equals(description)) {
			return true;
		}
		if (!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
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
		final byte[] bytes = this.earlyReleaseRequestDocumentRetriever
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
	
	/* Exception Handlers */
	
	/**
	 * Handles {@code EarlyReleaseRequestExistsException}.
	 * 
	 * @param exception Early Release Request Exists Exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(EarlyReleaseRequestExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final EarlyReleaseRequestExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				EARLY_RELEASE_REQUEST_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}

	/**
	 * Handles {@code EarlyReleaseRequestNoteAssociationExistsException}.
	 * 
	 * @param exception Early Release Request Note Association Exists Exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(EarlyReleaseRequestNoteAssociationExistsException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final EarlyReleaseRequestNoteAssociationExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				EARLY_RELEASE_REQUEST_NOTE_EXISTS_MESSAGE_KEY,
				ERROR_BUNDLE_NAME, exception);
	}

	/**
	 * Handles {@code EarlyReleaseRequestDocumentAssociationExists}.
	 * 
	 * @param exception Early Release Request Document Association Exists
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(EarlyReleaseRequestDocumentAssociationExists.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final EarlyReleaseRequestDocumentAssociationExists exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				EARLY_RELEASE_REQUEST_DOC_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}

	/**
	 * Handles {@code EarlyReleaseRequestInternalApprovalExists}.
	 * 
	 * @param exception Early Release Request Internal Approval Exists
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(EarlyReleaseRequestInternalApprovalExists.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final EarlyReleaseRequestInternalApprovalExists exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				EARLY_RELEASE_REQ_INT_APP_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}

	/**
	 * Handles {@code EarlyReleaseRequestExternalOppositionExists}.
	 * 
	 * @param exception Early Release Request External Opposition Exists
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(EarlyReleaseRequestExternalOppositionExists.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final EarlyReleaseRequestExternalOppositionExists exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				EARLY_RELEASE_REQ_EXT_OP_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
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
				ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Init binder.
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
		binder.registerCustomEditor(EarlyReleaseRequestNoteAssociation.class,
				this.earlyReleaseRequestNoteAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				EarlyReleaseRequestDocumentAssociation.class,
				this.earlyReleaseRequestDocumentAssociationropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EarlyReleaseRequestInternalApproval.class,
				this.earlyReleaseRequestInternalApprovalPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EarlyReleaseRequestExternalOpposition.class,
				this.earlyReleaseRequestExternalOppositionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EarlyReleaseJudgeResponseCategory.class,
				this.earlyReleaseJudgeResponseCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EarlyReleaseStatusCategory.class,
				this.earlyReleaseStatusCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ExternalOppositionPartyCategory.class,
				this.externalOppositionPartyCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class,
				this.documentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Docket.class,
				this.docketPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}
